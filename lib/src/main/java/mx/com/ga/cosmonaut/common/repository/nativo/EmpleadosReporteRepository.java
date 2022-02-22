package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.consultas.*;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class EmpleadosReporteRepository {
    private final JdbcOperations jdbcOperations;
    protected EmpleadosReporteRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Transactional
    public List<EmpleadosReporte> consultaEmpleados(Integer idEmpresa) throws ServiceException {
        String select = "", from = "", union = "", where = "";
        select = " select distinct ncc2.razon_social, ncc.num_empleado,  " +
                "np.nombre, np.apellido_pat as primer_apellido ,np.apellido_mat as segundo_apellido, " +
                "to_char( np.fecha_nace, 'dd-mm-YYYY') as fecha_nacimiento,  " +
                "case when np.genero = 'F' then 'Femenino' else 'Masculino' end as genero, " +
                "np.rfc, np.curp, np.nss as seguro_social, nrp.registro_patronal,  " +
                "case when np.iba_estado_civil = 'S' then 'Solter@'  " +
                "when np.iba_estado_civil = 'C'then 'Casad@'  " +
                "when np.iba_estado_civil = 'D' then 'Divorciad@'  " +
                "else 'Viud@' end as estado_civil, " +
                "np.iba_num_hijos, " +
                "concat('Calle: ',nd.calle,', Número: ',nd.num_exterior,', Municipio: ',cm.d_mnpio, " +
                "', Entidad federativa: ',ce.d_estado,', Código postal: ',nd.d_codigo) as domicilio_empleado, " +
                "np.ci_telefono, np.celular, np.email_corp as correo_empresarial, np.ci_email_personal as correo_personal,  " +
                "na.descripcion as descripcion_area, " +
                "np2.descripcion as descripcion_puesto, " +
                "concat(npjefe.nombre,' ',npjefe.apellido_pat , ' ', npjefe.apellido_mat) as nombre_jefe, " +
                "to_char(ncc.fecha_contrato, 'dd-mm-YYYY') as fecha_inicio, " +
                "to_char(ncc.fecha_fin, 'dd-mm-YYYY') as fecha_fin,  " +
                "to_char(ncc.de_fecha_antiguedad, 'dd-mm-YYYY') as de_fecha_antiguedad,  " +
                "to_char(ncc.ba_ultimo_dia, 'dd-mm-YYYY') as ba_ultimo_dia,  " +
                "case when ncc.es_activo = 'true' then 'Activo' " +
                "else 'Inactivo' end as es_activo, " +
                "ctrc.descripcion as nombre_regimen_contratacion,  " +
                "ctc2.descripcion as nombre_tipo_contrato,  " +
                "ngn.nombre as nombre_grupo_nomina,  " +
                "np3.nombre as nombre_politica, nj.nombre as nombre_jornada,  " +
                "case when ncc.de_es_sindicalizado = 'true' then 'Si' else 'No' end as es_sindicalizado,  " +
                "trunc(ncc.sueldo_bruto_mensual,2) as sueldo_bruto_mensual, " +
                "trunc(ncc.salario_diario,2) as salario_diario,  " +
                "trunc(ncc.salario_diario_integrado,2) as salario_diario_integrado,  " +
                "trunc(ncc.sbc,2) as sbc,  " +
                "trunc(ncc.sueldo_neto_mensual,2) as sueldo_neto_mensual, " +
                "ctc.descripcion as nombre_compensacion, " +
                "ce2.d_estado as estado_empresa, cag.descripcion as area_geografica,  " +
                "cmp.descripcion as nombre_metodo_pago,  " +
                "cb.nombre_corto as banco, ncb.num_cuenta, " +
                "ncb.clabe as em_cuenta_clabe_stp,  " +
                "ncb.num_informacion as cuenta_banco_id,  " +
                "concat(np.ce_nombre,' ',np.ce_apellido_paterno, ' ', np.ce_apellido_materno) " +
                "as nombre_contacto, cp.descripcion as ce_parentesco_id,  " +
                "np.ce_e_mail as email_contacto "; 
        from = " from nco_contrato_colaborador ncc  ";
        union = " left join nco_persona np on (ncc.persona_id = np.persona_id) " +
                "left join cat_parentesco cp on (cp.parentesco_id = np.ce_parentesco_id) " +
                "left join ncl_centroc_cliente ncc2 on (ncc2.centroc_cliente_id = ncc.centroc_cliente_id) " +
                "left JOIN (SELECT persona_id,MAX(fecha_contrato) fecha FROM nco_contrato_colaborador GROUP BY persona_id) colaborador_fecha  " +
                "ON ncc.fecha_contrato = colaborador_fecha.fecha AND ncc.persona_id = colaborador_fecha.persona_id  " +
                "left join ncl_registro_patronal nrp on (nrp.centroc_cliente_id = ncc.centroc_cliente_id) " +
                "left join nma_domicilio nd on (nd.persona_id = ncc.persona_id) " +
                "left JOIN cat_estado ce on ncc.de_c_estado = ce.c_estado " +
                "left join cat_municipio cm on (cm.c_mnpio = nd.c_mnpio and cm.c_estado = ce.c_estado) " +
                "left join nma_cuenta_banco ncb on (ncb.persona_id = ncc.persona_id and ncb.centroc_cliente_id = ncc.centroc_cliente_id) " +
                "left JOIN ncl_area na ON ncc.area_id=na.area_id " +
                "left join ncl_puesto np2 on (np2.puesto_id = ncc.puesto_id) " +
                "left JOIN ncl_grupo_nomina ngn on ncc.grupo_nomina_id = ngn.grupo_nomina_id " +
                "left join cat_estado ce2 on (ce2.c_estado = ncc.de_c_estado) " +
                "left join ncl_politica np3 on (np3.politica_id = ncc.politica_id) " +
                "left join ncl_jornada nj on (nj.jornada_id = ncc.de_jornada_id) " +
                "left join cat_tipo_compensacion ctc on (ctc.tipo_compensacion_id = ncc.tipo_compensacion_id) " +
                "left join cat_metodo_pago cmp on (cmp.metodo_pago_id = ncc.metodo_pago_id) " +
                "left join cs_tipo_regimen_contratacion ctrc on (ctrc.tipo_regimen_contratacion_id = ncc.de_tipo_regimen_contratacion_id) " +
                "left join cs_tipo_contrato ctc2 on (ctc2.tipo_contrato_id = ncc.de_tipo_contrato_id) " +
                "left join cat_area_geografica cag on (cag.area_geografica_id = ncc.de_area_geografica_id) " +
                "left join nco_persona npjefe on (ncc.jefe_inmediato_persona_id = npjefe.persona_id) " +
                "left join cs_banco cb on (cb.banco_id = ncb.banco_id) ";
        where = " where ncc.centroc_cliente_id = ? " +
                " AND ncc.fecha_contrato = (select max(a.fecha_contrato) from nco_contrato_colaborador a where a.persona_id = ncc.persona_id and a.centroc_cliente_id= ncc.centroc_cliente_id) " +
                " order by ncc.num_empleado  ";

        try {
            return jdbcOperations
                    .prepareStatement(select.concat(from).concat(union).concat(where), statement -> {
                        statement.setInt(1, idEmpresa);
                        ResultSet resultSet = statement.executeQuery();
                return jdbcOperations
                        .entityStream(resultSet, EmpleadosReporte.class)
                        .collect(Collectors.toList());
            });
        }catch (Exception e){
                throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                        + Constantes.ERROR_METODO +" consultaEmpleados " + Constantes.ERROR_EXCEPCION, e);
            }
    }


    @Transactional
    public List<ColaboradorPagoComplementarioConsulta> colaboradorPagoComplementario(Integer idEmpresa) throws ServiceException {
        String consulta = " select np.persona_id, ncc.num_empleado as id_empleado , np.nombre ,  " +
                "np.apellido_pat , np.apellido_mat , np.rfc , ncc.ppp_monto_complementario ,  " +
                "ncb.clabe as clabe, np.curp , np.nss , cb.cod_banco as instituto, np.email_corp as correo   " +
                "from nco_contrato_colaborador ncc   " +
                "inner join ncl_grupo_nomina ngn on ngn.centroc_cliente_id = ncc.centroc_cliente_id and ngn.grupo_nomina_id = ncc.grupo_nomina_id  " +
                "inner join nco_persona np on (ncc.persona_id = np.persona_id)  " +
                "left join nma_cuenta_banco ncb on (ncb.persona_id = ncc.persona_id)  " +
                "left join cs_banco cb on (cb.banco_id = ncb.banco_id)  " +
                "Inner JOIN (SELECT persona_id,MAX(fecha_contrato) fecha  FROM nco_contrato_colaborador   " +
                "GROUP BY persona_id) colaborador_fecha   ON ncc.fecha_contrato = colaborador_fecha.fecha  " +
                "AND ncc.persona_id = colaborador_fecha.persona_id  " +
                "where ncc.centroc_cliente_id = ?  " +
                "and ngn.pago_complementario = true " +
                "order by np.persona_id  ";
        try {
            return jdbcOperations
                    .prepareStatement(consulta, statement -> {
                        statement.setInt(1, idEmpresa);
                        ResultSet resultSet = statement.executeQuery();
                        return jdbcOperations
                                .entityStream(resultSet, ColaboradorPagoComplementarioConsulta.class)
                                .collect(Collectors.toList());
                    });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" colaboradorPagoComplementario " + Constantes.ERROR_EXCEPCION, e);
        }
    }


    /** Metodo encargado de obtener todas las empresa correspondientes al cliente principal. */
    @Transactional
    public List<EmpresaClientePrincipalConsulta> empresasClientePrincipal(Integer clientePrincipal) throws ServiceException {
        String consulta = "select ncc.centroc_cliente_id as id_empresa, " +
                "ncc.razon_social as nombre_empresa " +
                "from ncl_centroc_cliente ncc " +
                "where ncc.centro_costos_centroc_cliente_id = ? " +
                "order by razon_social ";
        try {

            return jdbcOperations
                    .prepareStatement(consulta, statement -> {
                        statement.setInt(1, clientePrincipal);
                        ResultSet resultSet = statement.executeQuery();
                        return jdbcOperations
                                .entityStream(resultSet, EmpresaClientePrincipalConsulta.class)
                                .collect(Collectors.toList());
                    });

        } catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" empresasClientePrincipal " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    /** Metodo encargado de obtener todas los grupo nomina por empresa. */
    @Transactional
    public List<GrupoNominaEmpresaConsulta> grupoNominaEmpresa(Integer idEmpresa)
            throws ServiceException {
        String consulta = " select ngn.grupo_nomina_id as id_grupo_nomina,  " +
                "ngn.nombre as nombre_grupo_nomina  " +
                "from ncl_grupo_nomina ngn   " +
                "where ngn.centroc_cliente_id  = ? and ngn.pago_complementario = true " +
                "and ngn.es_activo = true  order by ngn.nombre ";
        try {
            return jdbcOperations
                    .prepareStatement(consulta, statement -> {
                        statement.setInt(1, idEmpresa);
                        ResultSet resultSet = statement.executeQuery();
                        return jdbcOperations
                                .entityStream(resultSet, GrupoNominaEmpresaConsulta.class)
                                .collect(Collectors.toList());
                    });
        } catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" grupoNominaEmpresa " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    /** Metodo encargado de obtener los registros de IDSE. */
    @Transactional
    public List<IdseConsulta> idseConsulta(Integer idEmpresa)
            throws ServiceException {
        String consulta = " select distinct  " +
                "nkc.kardex_colaborador_id as kardex_id, " +
                "nkc.centroc_cliente_id as id_empresa, " +
                "nkc.movimiento_imss_id as imss_id, " +
                "nrp.registro_patronal,  " +
                "np.nss,  " +
                "upper(np.apellido_pat) as primer_apellido,  " +
                "upper(coalesce(np.apellido_mat,'')) as segundo_apellido,  " +
                "upper(np.nombre) as nombres, " +
                "nhc.salario_base_cotizacion as sbc, " +
                "'1' as tipo_trabajador, " +
                "ncc.tipo_compensacion_id as tipo_salario, " +
                "'0' as jornada_reducida, " +
                "to_char(nkc.fecha_movimiento, 'ddmmYYYY') as fecha_movimiento, " +
                "'000' as medicina_familiar, " +
                "' ' as filler_blanco, " +
                "ncmi.clave as tipo_movimiento, " +
                "'00000' as guia, " +
                "nhc.num_empleado as clave_trabajador, " +
                "np.curp, " +
                "'9' as identificador_formato, " +
                "cmb.motivo_baja_id as motivo_baja " +
                "from nco_kardex_colaborador nkc  " +
                "inner join ncl_registro_patronal nrp on nrp.centroc_cliente_id = nkc.centroc_cliente_id  " +
                "inner join nco_persona np on np.persona_id = nkc.persona_id  " +
                "inner join nco_historico_compensacion nhc  on nhc.historico_compensacion_id = nkc.historico_compensacion_id  " +
                "inner join nco_contrato_colaborador ncc on ncc.centroc_cliente_id = nkc.centroc_cliente_id and ncc.persona_id = nkc.persona_id  and ncc.fecha_contrato = nkc.fecha_contrato " +
                "left join cat_motivo_baja cmb on cmb.motivo_baja_id = ncc.ba_motivo_baja_id  " +
                "inner join nco_cat_movimiento_imss ncmi on ncmi.movimiento_imss_id = nkc.movimiento_imss_id  " +
                "where nkc.centroc_cliente_id = ? and nkc.es_activo = true  " +
                "order by np.nss   ";
        try {
            return jdbcOperations
                    .prepareStatement(consulta, statement -> {
                        statement.setInt(1, idEmpresa);
                        ResultSet resultSet = statement.executeQuery();
                        return jdbcOperations
                                .entityStream(resultSet, IdseConsulta.class)
                                .collect(Collectors.toList());
                    });
        } catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" idseConsulta " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    /** Metodo encargado de obtener los registros de IDSE. */
    @Transactional
    public List<IdseConsulta> idseConsultaByIdsKardex(Integer idEmpresa,List<Long> idsKardex)
            throws ServiceException {
        String consulta = " select distinct  " +
                "nkc.kardex_colaborador_id as kardex_id, " +
                "nkc.centroc_cliente_id as id_empresa, " +
                "nkc.movimiento_imss_id as imss_id, " +
                "nrp.registro_patronal,  " +
                "np.nss,  " +
                "upper(np.apellido_pat) as primer_apellido,  " +
                "upper(coalesce(np.apellido_mat,'')) as segundo_apellido,  " +
                "upper(np.nombre) as nombres, " +
                "trim(to_char(trunc(ncc.sbc, 2),'999999999.99')) as sbc, " +
                "'1' as tipo_trabajador, " +
                "ncc.tipo_compensacion_id as tipo_salario, " +
                "'0' as jornada_reducida, " +
                "to_char(nkc.fecha_movimiento, 'ddmmYYYY') as fecha_movimiento, " +
                "'000' as medicina_familiar, " +
                "' ' as filler_blanco, " +
                "ncmi.clave as tipo_movimiento, " +
                "'00000' as guia, " +
                "nhc.num_empleado as clave_trabajador, " +
                "np.curp, " +
                "'9' as identificador_formato, " +
                "cmb.motivo_baja_id as motivo_baja " +
                "from nco_kardex_colaborador nkc  " +
                "inner join ncl_registro_patronal nrp on nrp.centroc_cliente_id = nkc.centroc_cliente_id  " +
                "inner join nco_persona np on np.persona_id = nkc.persona_id  " +
                "inner join nco_historico_compensacion nhc  on nhc.historico_compensacion_id = nkc.historico_compensacion_id  " +
                "inner join nco_contrato_colaborador ncc on ncc.centroc_cliente_id = nkc.centroc_cliente_id and ncc.persona_id = nkc.persona_id  " +
                "left join cat_motivo_baja cmb on cmb.motivo_baja_id = ncc.ba_motivo_baja_id  " +
                "inner join nco_cat_movimiento_imss ncmi on ncmi.movimiento_imss_id = nkc.movimiento_imss_id  " +
                "where nkc.centroc_cliente_id = ? and nkc.es_activo = true  and nkc.kardex_colaborador_id in (%1$s) " +
                "order by np.nss   ";
        try {

            String kardex = "";
            for(Long ids : idsKardex){
                kardex += ids.toString().concat(",");
            }
            kardex = kardex.substring(0,kardex.length()-1);
            consulta = String.format(consulta,kardex);

            return jdbcOperations
                    .prepareStatement(consulta, statement -> {
                        statement.setInt(1, idEmpresa);
                        ResultSet resultSet = statement.executeQuery();
                        return jdbcOperations
                                .entityStream(resultSet, IdseConsulta.class)
                                .collect(Collectors.toList());
                    });
        } catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" idseConsulta " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    /** Metodo encargado de obtener los registros de SUA altas. */
    @Transactional
    public List<SuaAltasConsulta> suaAltas(Integer idEmpresa)
            throws ServiceException {
        String consulta = " select   " +
                "nkc.kardex_colaborador_id as kardex_id, " +
                "nkc.centroc_cliente_id as id_empresa, " +
                "nrp.registro_patronal,  " +
                "np.nss,  " +
                "np.rfc, " +
                "np.curp " +
                ",np.apellido_pat" +
                ",np.apellido_mat" +
                ",np.nombre as nombre_empleado," +
                "'1' as tipo_trabajador, " +
                "'0' as jornada_reducida, " +
                "to_char(nkc.fecha_movimiento, 'dd-mm-YYYY HH24:mi:ss') as fecha_movimiento, " +
                "round(nhc.salario_base_cotizacion,2) as sdi, " +
                "'' as clave_subdelegacion, " +
                "'' as cred_infonavit, " +
                "'' as inicio_descuento, " +
                "'' as tipo_descuento, " +
                "'' as valor_descuento " +
                "from nco_kardex_colaborador nkc  " +
                "inner join ncl_registro_patronal nrp on nrp.centroc_cliente_id = nkc.centroc_cliente_id  " +
                "inner join nco_persona np on np.persona_id = nkc.persona_id  " +
                "inner join nco_historico_compensacion nhc  on nhc.historico_compensacion_id = nkc.historico_compensacion_id  " +
                "where nkc.centroc_cliente_id = ? and nkc.es_activo = true  " +
                "order by np.nss  ";
        try {
            return jdbcOperations
                    .prepareStatement(consulta, statement -> {
                        statement.setInt(1, idEmpresa);
                        ResultSet resultSet = statement.executeQuery();
                        return jdbcOperations
                                .entityStream(resultSet, SuaAltasConsulta.class)
                                .collect(Collectors.toList());
                    });
        } catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" suaAltas " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    /** Metodo encargado de obtener los registros de SUA modificacion. */
    @Transactional
    public List<SuaModificacionConsulta> suaModificacion(Integer idEmpresa)
            throws ServiceException {
        String consulta = " select   " +
                "nkc.kardex_colaborador_id as kardex_id, " +
                "nkc.centroc_cliente_id as id_empresa, " +
                "nrp.registro_patronal,  " +
                "np.nss,  " +
                " ncmi.clave as tipo_movimiento, " +
                "to_char(nkc.fecha_movimiento, 'dd-mm-YYYY HH24:mi:ss') as fecha_movimiento, " +
                "'       ' as filler_blanco_ocho, " +
                "'00' as numero_dias, " +
                "round(nhc.salario_base_cotizacion,2) as sbc " +
                "from nco_kardex_colaborador nkc  " +
                "inner join ncl_registro_patronal nrp on nrp.centroc_cliente_id = nkc.centroc_cliente_id  " +
                "inner join nco_persona np on np.persona_id = nkc.persona_id  " +
                "inner join nco_historico_compensacion nhc  on nhc.historico_compensacion_id = nkc.historico_compensacion_id " +
                " inner join nco_cat_movimiento_imss ncmi on nkc.movimiento_imss_id = ncmi.movimiento_imss_id " +
                "where nkc.centroc_cliente_id = ? and nkc.es_activo = true  " +
                "order by np.nss  ";
        try {
            return jdbcOperations
                    .prepareStatement(consulta, statement -> {
                        statement.setInt(1, idEmpresa);
                        ResultSet resultSet = statement.executeQuery();
                        return jdbcOperations
                                .entityStream(resultSet, SuaModificacionConsulta.class)
                                .collect(Collectors.toList());
                    });
        } catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" suaModificacion " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    /** Metodo encargado de obtener los registros de folios fiscales. */
    @Transactional
    public List<FolioFiscalConsulta> foliofiscales(Integer nominaXperiodoId)
            throws ServiceException {
        String consulta = " select distinct   " +
                "nnx.nomina_xperiodo_id,   " +
                "np.persona_id,   " +
                "nnx.nombre_nomina,  " +
                "nnx.clave_periodo,   " +
                "to_char( nnx.fecha_inicio, 'dd-mm-YYYY') as fecha_inicio,   " +
                "to_char( nnx.fecha_fin, 'dd-mm-YYYY') as fecha_fin,  " +
                "np.rfc,   " +
                "coalesce (np.apellido_pat,'') as primer_apellido ,   " +
                "coalesce (np.apellido_mat,'') as segundo_apellido,   " +
                "coalesce (np.nombre,'') as nombre ,  " +
                "coalesce (nt.uuid,'') as folio_fiscal, to_char(nt.fecha_timbrado::timestamp::date,'dd-mm-YYYY') as fecha_timbrado " +
                "from ncr_nomina_xperiodo nnx   " +
                "inner join ncr_empleado_xnomina nex on nex.nomina_xperiodo_id = nnx.nomina_xperiodo_id and nex.centroc_cliente_id = nnx.centroc_cliente_id   " +
                "inner join nco_persona np on np.persona_id = nex.persona_id   " +
                "inner join ncr_timbre nt on nt.nomina_xperiodo_id = nnx.nomina_xperiodo_id and nt.persona_id = nex.persona_id  " +
                "where nnx.nomina_xperiodo_id = ? and nt.es_actual = true  " +
                "order by np.persona_id    ";
        try {
            return jdbcOperations
                    .prepareStatement(consulta, statement -> {
                        statement.setInt(1, nominaXperiodoId);
                        ResultSet resultSet = statement.executeQuery();
                        return jdbcOperations
                                .entityStream(resultSet, FolioFiscalConsulta.class)
                                .collect(Collectors.toList());
                    });
        } catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" suaModificacion " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<IdseConsulta> idseConsultaCentroCostoPadre(Integer idEmpresa)
            throws ServiceException {
        String consulta = " select distinct  " +
                " nkc.kardex_colaborador_id as kardex_id,  " +
                " nkc.centroc_cliente_id as id_empresa, nkc.movimiento_imss_id as imss_id,  " +
                " nrp.registro_patronal,  np.nss,  np.apellido_pat as primer_apellido,  np.apellido_mat as segundo_apellido,   " +
                " np.nombre as nombres, nhc.salario_base_cotizacion as sbc, " +
                " '1' as tipo_trabajador, nhc.tipo_compensacion_id as tipo_salario,  " +
                " '5' as jornada_reducida, to_char(nkc.fecha_movimiento, 'ddmmYYYY') as fecha_movimiento, " +
                " '000' as medicina_familiar, ' ' as filler_blanco, ncmi.clave as tipo_movimiento, '00000' as guia,  " +
                " nhc.num_empleado as clave_trabajador, np.curp, '9' as identificador_formato,  " +
                " cmb.motivo_baja_id as motivo_baja  " +
                " from nco_kardex_colaborador nkc   " +
                " inner join ncl_registro_patronal nrp on nrp.centroc_cliente_id = nkc.centroc_cliente_id   " +
                " inner join nco_persona np on np.persona_id = nkc.persona_id   " +
                " inner join nco_historico_compensacion nhc  on nhc.historico_compensacion_id = nkc.historico_compensacion_id   " +
                " inner join nco_contrato_colaborador ncc on ncc.centroc_cliente_id = nkc.centroc_cliente_id and ncc.persona_id = nkc.persona_id and ncc.fecha_contrato = nkc.fecha_contrato  " +
                " left join cat_motivo_baja cmb on cmb.motivo_baja_id = ncc.ba_motivo_baja_id   " +
                " inner join nco_cat_movimiento_imss ncmi on ncmi.movimiento_imss_id = nkc.movimiento_imss_id  " +
                " inner join ncl_centroc_cliente ncc2 on ncc2.centroc_cliente_id = nkc.centroc_cliente_id  " +
                " where  " +
                " ncc2.centro_costos_centroc_cliente_id =  ? " +
                " and nkc.es_activo = true  order by np.nss    ";
        try {
            return jdbcOperations
                    .prepareStatement(consulta, statement -> {
                        statement.setInt(1, idEmpresa);
                        ResultSet resultSet = statement.executeQuery();
                        return jdbcOperations
                                .entityStream(resultSet, IdseConsulta.class)
                                .collect(Collectors.toList());
                    });
        } catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" idseConsultaCentroCostoPadre " + Constantes.ERROR_EXCEPCION, e);
        }
    }
}
