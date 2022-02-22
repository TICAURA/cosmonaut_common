package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.SqlDto;
import mx.com.ga.cosmonaut.common.dto.consultas.ContratoColaboradorConsulta;
import mx.com.ga.cosmonaut.common.dto.reportes.EmpleadoDto;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatMetodoPago;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatMotivoBaja;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoBaja;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstado;
import mx.com.ga.cosmonaut.common.entity.cliente.*;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoContratoColaborador;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Sql;
import mx.com.ga.cosmonaut.common.util.Validar;
import org.apache.commons.lang3.StringUtils;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class ContratoColaboradorRepository {

    private final JdbcOperations jdbcOperations;

    protected ContratoColaboradorRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    private static final Boolean VERDADERO = true;

    private static final String SELECT_CONSULTA_DINAMICA = "SELECT persona.persona_id AS id_persona, persona.nombre AS nombre, persona.apellido_pat AS apellido_paterno, persona.apellido_mat AS apellido_materno,contrato.fecha_contrato AS fecha_contrato, contrato.num_empleado AS numero_empleado, empresa.centroc_cliente_id AS id_empresa, empresa.nombre AS empresa, empresa.razon_social AS razon_social, puesto.puesto_id AS id_puesto, puesto.descripcion AS puesto, area.area_id AS id_area, area.descripcion AS area, sede.sede_id AS id_sede, sede.descripcion AS sede, contrato.es_activo AS estatus, null AS porcentaje, contrato.fecha_alta_imss AS fecha_alta_imss, null AS url, persona.url_imagen AS url_imagen ";

    private static final String SELECT = "SELECT\n" +
            "    contrato.num_empleado AS id,\n" +
            "    persona.apellido_pat AS apellido_paterno,\n" +
            "    persona.apellido_mat AS apellido_materno,\n" +
            "    persona.nombre AS nombre,\n" +
            "    area.descripcion AS area,\n" +
            "    puesto.descripcion AS puesto,\n" +
            "    null AS resgistro_patronal,\n" +
            "    contrato.fecha_inicio AS fecha_inicio_contrato,\n" +
            "    contrato.fecha_contrato AS fecha_Antiguedad,\n" +
            "    null AS fecha_baja,\n" +
            "    null AS estatus,\n" +
            "    null AS tipo_empleado,\n" +
            "    tipo_contrato.descripcion AS tipo_contrato,\n" +
            "    grupo.nombre AS grupo_nomina,\n" +
            "    politica.nombre AS politicas,\n" +
            "    jornada.nombre AS jornada,\n" +
            "    contrato.de_es_sindicalizado AS sindicalizado,\n" +
            "    persona.rfc AS rfc,\n" +
            "    persona.curp AS curp,\n" +
            "    persona.nss AS seguro_social,\n" +
            "    contrato.sueldo_bruto_mensual AS sueldo_bruto_mensual,\n" +
            "    contrato.salario_diario AS salario_diario_integrado,\n" +
            "    contrato.sbc AS salario_base_cotizacion,\n" +
            "    compensacion.descripcion AS tipo_compensacion,\n" +
            "    estado.d_estado AS estado_republica,\n" +
            "    geografica.nombre_corto AS area_geofrafica,\n" +
            "    null AS metodo_pago,\n" +
            "    banco.descripcion AS banco,\n" +
            "    banco.num_cuenta AS numero_cuenta,\n" +
            "    banco.clabe AS clabe,\n" +
            "    contrato.num_empleado AS numero_cliente,\n" +
            "    persona.fecha_alta AS fecha_nacimiento,\n" +
            "    persona.genero AS genero,\n" +
            "    domicilio.calle AS direccion,\n" +
            "    persona.ci_telefono AS telefono,\n" +
            "    persona.ci_email_personal AS email_personal,\n" +
            "    persona.email_corp AS email_corporativo,\n" +
            "    null AS estado_civil,\n" +
            "    persona.iba_num_hijos AS numero_hijos,\n" +
            "    null AS reporta,\n" +
            "    null AS dulce_salado,\n" +
            "    null AS cafe_te,\n" +
            "    null AS bebida,\n" +
            "    null AS dulce,\n" +
            "    null AS color,\n" +
            "    null AS alergias,\n" +
            "    null AS email_parentesco,\n" +
            "    null AS nombre_perfil_linkedin,\n" +
            "    null AS alimentacion,\n" +
            "    persona.ce_telefono AS celular,\n" +
            "    persona.tiene_curp AS tiene_curp,\n" +
            "    contrato.fecha_fin AS fecha_vencimiento,\n" +
            "    persona.fecha_alta AS fecha_emision,\n" +
            "    null AS sincronizar_email_slack,\n" +
            "    contrato.su_es_subcontratado AS empleado_sub_contratado,\n" +
            "    puesto.descripcion AS departamento,\n" +
            "    null AS nivel,\n" +
            "    null AS categoria,\n" +
            "    centro.razon_social AS razon_social,\n" +
            "    null AS discapacidades,\n" +
            "    centro.nombre AS empresa,\n" +
            "    null AS empleo_anterior,\n" +
            "    null AS nombre_empresa_anterior,\n" +
            "    null AS antecedentes_medicos,\n" +
            "    null AS detalle_sub_contratacion ";

    @Transactional
    public List<EmpleadoDto> consultaListaEmpleado(Integer id) throws ServiceException {
        try {
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT).append( "FROM nco_contrato_colaborador contrato ").
            append("INNER JOIN nco_persona persona ON contrato.persona_id = persona.persona_id ").
            append("INNER JOIN nma_domicilio domicilio on persona.persona_id = domicilio.persona_id ").
            append("INNER JOIN nma_cuenta_banco banco on persona.persona_id = banco.persona_id ").
            append("INNER JOIN ncl_centroc_cliente centro on contrato.centroc_cliente_id = centro.centroc_cliente_id ").
            append("INNER JOIN cat_estado estado on contrato.de_c_estado = estado.c_estado ").
            append("LEFT JOIN ncl_sede sede on contrato.sede_id = sede.sede_id ").
            append("INNER JOIN ncl_area area on contrato.area_id = area.area_id ").
            append("INNER JOIN ncl_puesto puesto on contrato.puesto_id = puesto.puesto_id ").
            append("INNER JOIN ncl_jornada jornada on contrato.de_jornada_id = jornada.jornada_id and contrato.de_tipo_jornada_id = jornada.tipo_jornada_id ").
            append("INNER JOIN cat_area_geografica geografica on contrato.de_area_geografica_id = geografica.area_geografica_id ").
            append("INNER JOIN cat_tipo_compensacion compensacion on contrato.tipo_compensacion_id = compensacion.tipo_compensacion_id ").
            append("INNER JOIN ncl_politica politica on contrato.politica_id = politica.politica_id ").
            append("INNER JOIN cs_tipo_contrato tipo_contrato on contrato.de_tipo_contrato_id = tipo_contrato.tipo_contrato_id ").
            append("INNER JOIN cat_metodo_pago metodo on contrato.metodo_pago_id = metodo.metodo_pago_id ").
            append("INNER JOIN ncl_grupo_nomina grupo on contrato.grupo_nomina_id = grupo.grupo_nomina_id ").
            append("WHERE contrato.persona_id = ?");

            return jdbcOperations.prepareStatement(query.toString(), statement -> {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, EmpleadoDto.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaListaEmpleado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<ContratoColaboradorConsulta> consultaDimanicaPaginado(NcoContratoColaborador contratoColaborador,Integer numeroRegistros, Integer pagina) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamico(contratoColaborador);
            sqlDto.setQuery(Sql.eliminarUltimoAnd(sqlDto.getQuery()));
            StringBuilder query = new StringBuilder(100);
            query.append(sqlDto.getQuery()).append(" LIMIT ? ").append(" OFFSET ? ");
            sqlDto.setQuery(query.toString());
            sqlDto.getListaDatos().add(numeroRegistros);
            sqlDto.getListaDatos().add(pagina);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, ContratoColaboradorConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDimanica " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<ContratoColaboradorConsulta> consultaDimanica(NcoContratoColaborador contratoColaborador) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamico(contratoColaborador);
            sqlDto.setQuery(agregaOrderBy(Sql.eliminarUltimoAnd(sqlDto.getQuery())));
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, ContratoColaboradorConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDimanica " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private String agregaOrderBy(String sql){
        String orderBy=sql+" ORDER BY persona.nombre, persona.apellido_pat, persona.apellido_mat";
        return orderBy;
    }

    private SqlDto generaQueryDinamico(NcoContratoColaborador contratoColaborador){
        SqlDto sqlDto = new SqlDto();
        sqlDto.setListaDatos(new ArrayList<>());
        StringBuilder query = new StringBuilder(100);
        query.append(SELECT_CONSULTA_DINAMICA).append("FROM nco_contrato_colaborador contrato ").
        append("INNER JOIN (SELECT persona_id,MAX(fecha_contrato) fecha FROM nco_contrato_colaborador GROUP BY persona_id) contrato_fecha ").
        append("ON contrato.fecha_contrato = contrato_fecha.fecha AND contrato.persona_id = contrato_fecha.persona_id ").
        append("INNER JOIN nco_persona persona on contrato.persona_id = persona.persona_id ").
        append("INNER JOIN ncl_centroc_cliente empresa on contrato.centroc_cliente_id = empresa.centroc_cliente_id ").
        append("INNER JOIN ncl_puesto puesto on contrato.puesto_id = puesto.puesto_id ").
        append("INNER JOIN ncl_area area on contrato.area_id = area.area_id ").
        append("LEFT JOIN ncl_sede sede on contrato.sede_id = sede.sede_id ").
        append("WHERE ");

        if (validaPersonaNombre(contratoColaborador.getPersonaId())){
            query.append(" persona.nombre ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getPersonaId().getNombre() + "%");
        }

        if (validaPersonaApellidoPaterno(contratoColaborador.getPersonaId())){
            query.append(" persona.apellido_pat ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getPersonaId().getApellidoPaterno() + "%");
        }

        if (validaPersonaApellidoMaterno(contratoColaborador.getPersonaId())){
            query.append(" persona.apellido_mat ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getPersonaId().getApellidoMaterno() + "%");
        }

        if (contratoColaborador.getCentrocClienteId() != null
                && Validar.validaTexto(contratoColaborador.getCentrocClienteId().getRazonSocial())){
            query.append(" empresa.razon_social ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getCentrocClienteId().getRazonSocial() + "%");
        }

        if (contratoColaborador.getCentrocClienteId() != null
                && Validar.validaTexto(contratoColaborador.getCentrocClienteId().getNombre())){
            query.append(" empresa.nombre ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getCentrocClienteId().getNombre() + "%");
        }

        if (contratoColaborador.getCentrocClienteId() != null
                && contratoColaborador.getCentrocClienteId().getCentrocClienteId() != null){
            query.append(" contrato.centroc_cliente_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getCentrocClienteId().getCentrocClienteId());
        }

        if (Validar.validaTexto(contratoColaborador.getNumEmpleado())){
            query.append(" contrato.num_empleado ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getNumEmpleado() + "%");
        }

        if (contratoColaborador.getEsActivo() != null){
            query.append(" contrato.es_activo = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getEsActivo());
        }

        if (validaObjetoId(contratoColaborador.getAreaId())){
            query.append(" area.area_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getAreaId().getAreaId());
        }

        if (validaObjetoId(contratoColaborador.getPuestoId())){
            query.append(" puesto.puesto_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getPuestoId().getPuestoId());
        }

        if (validaObjetoId(contratoColaborador.getSedeId())){
            query.append(" sede.sede_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(contratoColaborador.getSedeId().getSedeId());



        }



        sqlDto.setQuery(query.toString());
        return sqlDto;
    }

    @Transactional
    public boolean update(NcoContratoColaborador contratoColaborador) throws ServiceException {
        try {
            String query = generaQueryUpdate(contratoColaborador);
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.execute();
                return true;
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" update " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public boolean updateCompensacion(NcoContratoColaborador contratoColaborador) throws ServiceException {
        try {
            String query = generaQueryCompensacion(contratoColaborador);
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.execute();
                return true;
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" update " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private String generaQueryUpdate(NcoContratoColaborador contratoColaborador){
        StringBuilder query = new StringBuilder(100);
        query.append("UPDATE nco_contrato_colaborador SET ");
        query.append("de_es_sindicalizado = ").append(contratoColaborador.isEsSindicalizado()).append(",");

        if (validaObjetoId(contratoColaborador.getAreaId())){
            query.append("area_id = ").append(contratoColaborador.getAreaId().getAreaId()).append(",");
        }

        if (validaObjetoId(contratoColaborador.getPuestoId())){
            query.append("puesto_id = ").append(contratoColaborador.getPuestoId().getPuestoId()).append(",");
        }

        if (validaObjetoId(contratoColaborador.getSedeId())){
            query.append("sede_id = ").append(contratoColaborador.getSedeId().getSedeId()).append(",");
        }

        if (validaObjetoId(contratoColaborador.getEstadoId())){
            query.append("de_c_estado = ").append(contratoColaborador.getEstadoId().getEstadoId()).append(",");
        }

        if (validaObjetoId(contratoColaborador.getPoliticaId())){
            query.append("politica_id = ").append(contratoColaborador.getPoliticaId().getPoliticaId()).append(",");
        }

        if (validaObjetoBaja(contratoColaborador.getJornadaId())){
            query.append("de_jornada_id = ").append(contratoColaborador.getJornadaId().getJornadaId()).append(",");
            query.append("de_tipo_jornada_id = '").append(contratoColaborador.getJornadaId().getTipoJornadaId().getTipoJornadaId()).append("' ").append(",");
        }

        if (contratoColaborador.getFechaAntiguedad() != null){
            query.append("de_fecha_antiguedad = '").append(fechaTexto(contratoColaborador.getFechaAntiguedad())).append("' ").append(",");
        }

        if (contratoColaborador.getFechaInicio() != null){
            query.append("fecha_inicio = '").append(fechaTexto(contratoColaborador.getFechaInicio())).append("' ").append(",");
        }

        if (contratoColaborador.getFechaFin() != null){
            query.append("fecha_fin = '").append(fechaTexto(contratoColaborador.getFechaFin())).append("' ").append(",");
        }

        if (validaObjetoBaja(contratoColaborador.getTipoBajaId())){
            query.append("ba_tipo_baja_id = '").append(contratoColaborador.getTipoBajaId().getTipoBajaId()).append("' ").append(",");
        }

        if (validaObjetoBaja(contratoColaborador.getMotivoBajaId())){
            query.append("ba_motivo_baja_id = '").append(contratoColaborador.getMotivoBajaId().getMotivoBajaId()).append("' ").append(",");
        }

        if (contratoColaborador.getUltimoDia() != null){
            query.append("ba_ultimo_dia = '").append(fechaTexto(contratoColaborador.getUltimoDia())).append("' ").append(",");
        }

        if (Validar.validaTexto(contratoColaborador.getFechaParaCalculo())){
            query.append("ba_fecha_para_calculo = '").append(contratoColaborador.getFechaParaCalculo()).append("' ").append(",");
        }

        if (Validar.validaTexto(contratoColaborador.getNotas())){
            query.append("ba_notas = '").append(contratoColaborador.getNotas()).append("' ").append(",");
        }

        if (validaObjetoBaja(contratoColaborador.getMetodoPagoId())){
            query.append("metodo_pago_id = '").append(contratoColaborador.getMetodoPagoId().getMetodoPagoId()).append("' ").append(",");
        }

        if (contratoColaborador.getFechaAltaImss() != null){
            query.append("fecha_alta_imss = '").append(fechaTexto(contratoColaborador.getFechaAltaImss())).append("' ").append(",");
        }

        if (contratoColaborador.getEsActivo() != null){
            query.append("es_activo = '").append(contratoColaborador.getEsActivo().booleanValue()).append("' ").append(",");
        }

        if (contratoColaborador.getEstatusBajaId() != null){
            query.append("estatus_baja_id = ").append(contratoColaborador.getEstatusBajaId()).append(",");
        }

        if (contratoColaborador.getFechaFinUltimoPago() != null){
            query.append("fecha_fin_ultimo_pago = '").append(fechaTexto(contratoColaborador.getFechaFinUltimoPago())).append("' ");
        }

        query = new StringBuilder(StringUtils.removeEnd(query.toString(), ","));
        query.append(" WHERE fecha_contrato = '").append(fechaTexto(contratoColaborador.getFechaContrato())).append("' ").
        append(" AND persona_id = ").append(contratoColaborador.getPersonaId().getPersonaId()).
        append(" AND centroc_cliente_id = ").append(contratoColaborador.getCentrocClienteId().getCentrocClienteId());

        return query.toString();
    }

    private String generaQueryCompensacion(NcoContratoColaborador contratoColaborador){
        StringBuilder query = new StringBuilder(100);
        query.append("UPDATE nco_contrato_colaborador SET ");

        if (contratoColaborador.getGrupoNominaId() != null
                && contratoColaborador.getGrupoNominaId().getGrupoNominaId() != null){
            query.append("grupo_nomina_id = ").append(contratoColaborador.getGrupoNominaId().getGrupoNominaId()).append(",");
        }

        if (contratoColaborador.getTipoCompensacionId() != null
                && contratoColaborador.getTipoCompensacionId().getTipoCompensacionId() != null){
            query.append("tipo_compensacion_id = ").append(contratoColaborador.getTipoCompensacionId().getTipoCompensacionId()).append(",");
        }

        if (contratoColaborador.getSueldoBrutoMensual() != null){
            query.append("sueldo_bruto_mensual = ").append(contratoColaborador.getSueldoBrutoMensual()).append(",");
        }

        if (contratoColaborador.getSueldoNetoMensual() != null){
            query.append("sueldo_neto_mensual = ").append(contratoColaborador.getSueldoNetoMensual()).append(",");
        }

        if (contratoColaborador.getSalarioDiario() != null){
            query.append("salario_diario = ").append(contratoColaborador.getSalarioDiario()).append(",");
        }

        if (contratoColaborador.getSalarioDiarioIntegrado() != null){
            query.append("salario_diario_integrado = ").append(contratoColaborador.getSalarioDiarioIntegrado()).append(",");
        }

        if (contratoColaborador.getSbc() != null){
            query.append("sbc = ").append(contratoColaborador.getSbc()).append(",");
        }
        query = new StringBuilder(StringUtils.removeEnd(query.toString(), ","));
        query.append(" WHERE fecha_contrato = '").append(fechaTexto(contratoColaborador.getFechaContrato())).append("' ").
                append(" AND persona_id = ").append(contratoColaborador.getPersonaId().getPersonaId()).
                append(" AND centroc_cliente_id = ").append(contratoColaborador.getCentrocClienteId().getCentrocClienteId());

        return query.toString();
    }


    private String fechaTexto(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(fecha);
    }

    private boolean validaObjetoId(Object objeto){
        boolean es = false;
        if (objeto instanceof NclArea){
            NclArea area = (NclArea) objeto;
            if (area.getAreaId() != null){
                es = VERDADERO;
            }
        }else if (objeto instanceof NclPuesto){
            NclPuesto puesto = (NclPuesto) objeto;
            if (puesto.getPuestoId() != null){
                es = VERDADERO;
            }
        }else if (objeto instanceof NclSede){
            NclSede sede = (NclSede) objeto;
            if (sede.getSedeId() != null){
                es = VERDADERO;
            }
        }else if (objeto instanceof CatEstado){
            CatEstado estado = (CatEstado) objeto;
            if (estado.getEstadoId() != null){
                es = VERDADERO;
            }
        }else if (objeto instanceof NclPolitica){
            NclPolitica politica = (NclPolitica) objeto;
            if (politica.getPoliticaId() != null){
                es = VERDADERO;
            }
        }
        return es;
    }

    private boolean validaObjetoBaja(Object objeto){
        boolean es = false;
        if (objeto instanceof NclJornada){
            NclJornada jornada = (NclJornada) objeto;
            if (jornada.getJornadaId() != null
                && jornada.getTipoJornadaId() != null
                && Validar.validaTexto(jornada.getTipoJornadaId().getTipoJornadaId())){
                es = VERDADERO;
            }
        }else if (objeto instanceof CatTipoBaja){
            CatTipoBaja tipoBaja = (CatTipoBaja) objeto;
            if (tipoBaja.getTipoBajaId() != null){
                es = VERDADERO;
            }
        }else if (objeto instanceof CatMotivoBaja){
            CatMotivoBaja motivoBaja = (CatMotivoBaja) objeto;
            if (motivoBaja.getMotivoBajaId() != null){
                es = VERDADERO;
            }
        }else if (objeto instanceof CatMetodoPago){
            CatMetodoPago metodoPago = (CatMetodoPago) objeto;
            if (metodoPago.getMetodoPagoId() != null){
                es = VERDADERO;
            }
        }

        return es;
    }

    private boolean validaPersonaNombre(NcoPersona persona){
        boolean es = false;
        if (persona != null
                && Validar.validaTexto(persona.getNombre())){
            es = VERDADERO;
        }
        return es;
    }

    private boolean validaPersonaApellidoPaterno(NcoPersona persona){
        boolean es = false;
        if (persona != null
                && Validar.validaTexto(persona.getApellidoPaterno())){
            es = VERDADERO;
        }
        return es;
    }

    private boolean validaPersonaApellidoMaterno(NcoPersona persona){
        boolean es = false;
        if (persona != null
                && Validar.validaTexto(persona.getApellidoMaterno())){
            es = VERDADERO;
        }
        return es;
    }
}
