package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.NcoChatColaboradorDto;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class NcoChatColaboradorCustom {
    private final JdbcOperations jdbcOperations;

    public NcoChatColaboradorCustom(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<NcoChatColaboradorDto> chatListado(Integer centro) throws ServiceException {
        String query =  "select au.nombre, au.apellido_pat, au.apellido_mat, nc.*, na.nombre_corto , na.descripcion  from nco_chat_colaborador nc  join adm_usuarios au " +
        "on (nc.usuario_id = au.usuario_id) join nco_persona cp" +
                " on (cp.email_corp=au.email) join nco_contrato_colaborador ncc on (ncc.persona_id=cp.persona_id) join" +
                " ncl_area na on (na.area_id=ncc.area_id) " +
                "where nc.centroc_cliente_id = ? and ncc.es_activo = true";
        try {
            StringBuilder sb = new StringBuilder(query);
            return jdbcOperations.prepareStatement(sb.toString(), statement -> {
                statement.setInt(1, centro);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NcoChatColaboradorDto.class)
                        .collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" Chat Listado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
