package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.consultas.KardexConsulta;
import mx.com.ga.cosmonaut.common.dto.imss.tectel.PersonaAfiliadaKardex;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoKardexColaborador;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class KardexRepository {


    private final JdbcOperations jdbcOperations;

    protected KardexRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT = "SELECT kardex.kardex_colaborador_id AS kardex_id,kardex.movimiento_imss_id AS movimiento_id,movimiento.descripcion_movimiento AS movimiento,kardex.fecha_movimiento AS fecha_movimiento,registro.registro_patronal_id AS registro_id,registro.registro_patronal AS registro_descripcion, politica.politica_id AS politica_id,politica.nombre AS politica_descripcion,historico.salario_diario AS salario_diario,historico.salario_diario_integrado AS salario_diario_integrado,historico.salario_base_cotizacion AS salario_base_cotizacion,colaborador.es_activo AS es_activo ";

    private static final String FROM = "FROM nco_kardex_colaborador kardex INNER JOIN nco_cat_movimiento_imss movimiento ON kardex.movimiento_imss_id = movimiento.movimiento_imss_id INNER JOIN nco_contrato_colaborador colaborador ON kardex.fecha_contrato = colaborador.fecha_contrato AND kardex.persona_id = colaborador.persona_id AND kardex.centroc_cliente_id = colaborador.centroc_cliente_id INNER JOIN ncl_politica politica ON colaborador.politica_id = politica.politica_id INNER JOIN ncl_registro_patronal registro on colaborador.centroc_cliente_id = registro.centroc_cliente_id INNER JOIN nco_historico_compensacion historico on historico.historico_compensacion_id = kardex.historico_compensacion_id ";

    private static final String WHERE = "WHERE kardex.fecha_contrato = ? AND kardex.persona_id = ? AND kardex.centroc_cliente_id = ? ORDER BY kardex.kardex_colaborador_id DESC ";

    private static final String SELECT_STATUS_KARDEX = " select pp1.persona_id ,pp1.nombre ,pp1.apellido_pat ,pp1.apellido_mat ,nkc.kardex_colaborador_id ,cc1.numero_lote,cc1.id_proceso from nco_persona pp1 \n" +
            " inner join nco_kardex_colaborador nkc on pp1.persona_id  = nkc.persona_id  and nss  = ? \n" +
            "and  (CONCAT(nombre,apellido_pat,coalesce(apellido_mat,''))) like concat('%',replace(?,' ',''),'%')\n" +
            "inner join nco_kardex_estatus_idse cc1 on nkc.kardex_colaborador_id  = cc1.kardex_colaborador_id \n" +
            "where cc1.numero_lote = ?";

    @Transactional
    public List<KardexConsulta> consultaEmpleado(NcoKardexColaborador kardexColaborador) throws ServiceException {
        try {
            java.sql.Date fecha = new java.sql.Date(kardexColaborador.getFechaContrato().getTime());
            return jdbcOperations.prepareStatement(SELECT + FROM + WHERE, statement -> {
                statement.setDate(1, fecha);
                statement.setInt(2, kardexColaborador.getPersonaId().getPersonaId());
                statement.setInt(3, kardexColaborador.getCentrocClienteId().getCentrocClienteId());
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, KardexConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaEmpleado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<PersonaAfiliadaKardex> getPersonaAfiliadasKardex(String nss,String nombreCompleto,String numeroLote) throws ServiceException {
        try {

            return jdbcOperations.prepareStatement(this.SELECT_STATUS_KARDEX, statement -> {
                statement.setString(1,nss);
                statement.setString(2,nombreCompleto);
                statement.setString(3,numeroLote);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, PersonaAfiliadaKardex.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaEmpleado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
