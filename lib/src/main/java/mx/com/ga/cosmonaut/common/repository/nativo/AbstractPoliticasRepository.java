package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;
import mx.com.ga.cosmonaut.common.dto.Politica;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPoliticaXColaborador;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class AbstractPoliticasRepository {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractPoliticasRepository.class);
    private final JdbcOperations jdbcOperations;

    protected AbstractPoliticasRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<NcoPoliticaXColaborador> consultaPoliticaXEmpPol(Integer idPolitica, Integer idCliente) throws ServiceException {
        try {
            String query = "SELECT np.*,np.nombre, np.apellido_pat , np.apellido_mat, np2.es_estandar, ncc.num_empleado, ncc.politica_id,\n" +
                    " np3.descripcion as puesto, ncc.centroc_cliente_id from nco_contrato_colaborador ncc \n" +
                    " INNER JOIN (SELECT persona_id,MAX(fecha_inicio) fecha FROM nco_contrato_colaborador GROUP BY persona_id) colaborador_fecha \n" +
                    " ON ncc.fecha_inicio = colaborador_fecha.fecha AND ncc.persona_id = colaborador_fecha.persona_id \n" +
                    " INNER JOIN nco_persona np on ncc.persona_id = np.persona_id  \n" +
                    " INNER JOIN ncl_politica np2 on np2.politica_id = ncc.politica_id \n" +
                    " INNER JOIN ncl_puesto np3 on np3.puesto_id = ncc.puesto_id \n" +
                    " INNER JOIN ncl_centroc_cliente ncc2 on ncc2.centroc_cliente_id = ncc.centroc_cliente_id\n" +
                    "AND ncc.es_activo = true\n" +
                    "and ncc.centroc_cliente_id = ?\n" +
                    "and np2.politica_id = ?";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, idCliente);
                statement.setInt(2, idPolitica);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NcoPoliticaXColaborador.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    
    @Transactional
    public List<Politica> consultaPoliticasXEmpresa(Integer idCliente) throws ServiceException {
        try {
            String query = "select np.politica_id, np.nombre, np.nombre_corto, np.dias_economicos, np.prima_aniversario,\n"
                    + "np.descuenta_faltas, np.descuenta_incapacidades, np.costo_vales_restaurante,\n"
                    + "np.descuento_prop_7o_dia as descuento_prop_dia , np.es_activo, np.centroc_cliente_id, ncc.razon_social, \n"
                    + "np.calculo_antiguedadx_id as calculo_antiguedad_id, np.es_estandar, count(ncc2.politica_id)  \n"
                    + "from ncl_politica np \n"
                    + "join ncl_centroc_cliente ncc on np.centroc_cliente_id = ncc.centroc_cliente_id \n"
                    + "and ncc.centroc_cliente_id = ? and np.es_activo = true\n"
                    + "left join nco_contrato_colaborador ncc2 on ncc2.centroc_cliente_id = ncc.centroc_cliente_id \n"
                    + " and ncc2.politica_id = np.politica_id and ncc2.es_activo = true and ncc2.centroc_cliente_id = np.centroc_cliente_id \n"
                    + " group by np.politica_id, ncc.razon_social";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, idCliente);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, Politica.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

}
