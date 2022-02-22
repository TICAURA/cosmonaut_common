package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.imss.IMSSFiltradoRequest;
import mx.com.ga.cosmonaut.common.dto.imss.IMSSFiltradoResponse;
import mx.com.ga.cosmonaut.common.dto.imss.VariabilidadFiltradoRequest;
import mx.com.ga.cosmonaut.common.dto.imss.VariabilidadFiltradoResponse;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class IMSSRepository {

    private final JdbcOperations jdbcOperations;

    protected IMSSRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String PORC = "%";

    private static final String SELECT = "SELECT np.nombre as vigencia_movimiento, nkc.kardex_colaborador_id, np.nombre, np.nombre2, np.apellido_pat, np.apellido_mat, nhc.salario_base_cotizacion as sbc, ncmi.movimiento_imss_id, ncmi.descripcion_movimiento as movimiento, nkc.fecha_movimiento, nrp.registro_patronal, np2.nombre as politica, nhc.salario_diario, nhc.salario_diario_integrado, ncc.es_activo, nkc.es_imss,nrp.credenciales_imss_id , coalesce(nkei.estatus_idse_id ,0) as id_estatus,coalesce (cei.descripcion,'Pendiente') as estatus, nhc.tipo_compensacion_id,  np.persona_id ";
    private static final String FROM = "FROM nco_kardex_colaborador nkc INNER JOIN nco_historico_compensacion nhc ON nkc.historico_compensacion_id = nhc.historico_compensacion_id INNER JOIN nco_persona np ON nkc.persona_id = np.persona_id INNER JOIN nco_cat_movimiento_imss ncmi ON nkc.movimiento_imss_id = ncmi.movimiento_imss_id INNER JOIN ncl_registro_patronal nrp ON nkc.centroc_cliente_id = nrp.centroc_cliente_id INNER JOIN ncl_politica np2 ON nhc.politica_id = np2.politica_id INNER JOIN nco_contrato_colaborador ncc ON nkc.fecha_contrato = ncc.fecha_contrato and nkc.persona_id = ncc.persona_id and nkc.centroc_cliente_id = ncc.centroc_cliente_id left join nco_kardex_estatus_idse nkei on nkc.kardex_colaborador_id  = nkei.kardex_colaborador_id left join cat_estatus_idse cei on nkei.estatus_idse_id = cei.estatus_idse_id  ";
    private static final String WHERE = "WHERE nkc.es_activo = true AND nkc.centroc_cliente_id = ? ";

    private static final String SELECT_VAR = "SELECT nv.variabilidad_id, ncc.razon_social, nv.anio_fiscal, nv.bimestre, nv.fecha_inicio, nv.fecha_fin, CONCAT('Del ', nv.fecha_inicio, ' al ', nv.fecha_fin) as periodo_calculo, nv.fecha_aplicacion, nv.total_empleados, nv.dias_bimestre, CASE WHEN nv.estado_variabilidad_id_actual = 2 THEN true ELSE false END AS recalcula ";
    private static final String FROM_VAR = "FROM ncr_variabilidad nv INNER JOIN ncl_centroc_cliente ncc ON nv.centroc_cliente_id = ncc.centroc_cliente_id ";
    private static final String WHERE_VAR = "WHERE 1=1 ";

    private static final String SELECT_VAR2 = "SELECT nv.variabilidad_id ";
    private static final String FROM_VAR2 = "FROM ncr_variabilidad nv ";
    private static final String WHERE_VAR2 = "WHERE nv.estado_variabilidad_id_actual = 3 AND nv.anio_fiscal = ? AND nv.bimestre = ? ";

    @Transactional
    public List<IMSSFiltradoResponse> filtrar(IMSSFiltradoRequest request) throws ServiceException {
        try {
            StringBuilder sb = new StringBuilder(SELECT).append(FROM).append(WHERE);

            List<Object> statements = new ArrayList<>();
            statements.add(request.getClienteId());
            if (request.getRegistroPatronal() != null) {
                sb.append("AND nrp.registro_patronal = ? ");
                statements.add(request.getRegistroPatronal());
            }
            if (request.getNombre() != null) {
                sb.append("AND np.nombre ILIKE ? ");
                statements.add(request.getNombre().concat(PORC));
            }
            if (request.getNombre2() != null) {
                sb.append("AND np.nombre2 ILIKE ? ");
                statements.add(request.getNombre2().concat(PORC));
            }
            if (request.getApellidoPat() != null) {
                sb.append("AND np.apellido_pat ILIKE ? ");
                statements.add(request.getApellidoPat().concat(PORC));
            }
            if (request.getApellidoMat() != null) {
                sb.append("AND np.apellido_mat ILIKE ? ");
                statements.add(request.getApellidoMat().concat(PORC));
            }
            if (request.getMovimiento() != null) {
                sb.append("AND nkc.movimiento_imss_id = ? ");
                statements.add(request.getMovimiento());
            }
            if (request.getFechaMovimiento() != null) {
                sb.append("AND nkc.fecha_movimiento BETWEEN ?::TIMESTAMP AND ?::TIMESTAMP ");
                statements.add(request.getFechaMovimiento()+" 00:00:00");
                statements.add(request.getFechaMovimiento()+" 23:59:59");
            }
            if (request.getFechaMin() != null && request.getFechaMax() != null) {
                sb.append("AND nkc.fecha_movimiento BETWEEN ?::TIMESTAMP AND ?::TIMESTAMP ");
                statements.add(request.getFechaMin()+" 00:00:00");
                statements.add(request.getFechaMax()+" 23:59:59");
            }

            return jdbcOperations.prepareStatement(sb.toString(), statement -> {
                int i = 1;
                for (Object obj : statements) {
                    statement.setObject(i++, obj);
                }

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, IMSSFiltradoResponse.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" filtrar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<VariabilidadFiltradoResponse> filtroVariabilidad(VariabilidadFiltradoRequest request) throws ServiceException {
        try {
            StringBuilder sb = new StringBuilder(SELECT_VAR).append(FROM_VAR).append(WHERE_VAR);

            List<Object> statements = new ArrayList<>();
            //statements.add(request.getClienteId());
            if (request.getClienteId() != null) {
                sb.append("AND nv.centroc_cliente_id = ? ");
                statements.add(request.getClienteId());
            }
            if (request.getAnio() != null) {
                sb.append("AND nv.anio_fiscal = ? ");
                statements.add(request.getAnio());
            }
            if (request.getBimestre() != null) {
                sb.append("AND nv.bimestre = ? ");
                statements.add(request.getBimestre());
            }

            sb.append(" ORDER BY nv.anio_fiscal, nv.bimestre ");

            return jdbcOperations.prepareStatement(sb.toString(), statement -> {
                int i = 1;
                for (Object obj : statements) {
                    statement.setObject(i++, obj);
                }

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, VariabilidadFiltradoResponse.class)
                        .collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" filtrarVariabilidad " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public boolean validaVariabilidad(Integer anio, Integer bimestre) throws ServiceException {
        try {
            return jdbcOperations.prepareStatement(SELECT_VAR2 + FROM_VAR2 + WHERE_VAR2, statement -> {
                statement.setInt(1, anio);
                statement.setInt(2, bimestre);

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, Integer.class).count() == 0;
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" validaVariabilidad " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
