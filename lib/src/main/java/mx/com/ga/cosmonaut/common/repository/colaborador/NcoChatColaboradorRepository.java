package mx.com.ga.cosmonaut.common.repository.colaborador;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoChatColaborador;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcoChatColaboradorRepository extends CrudRepository<NcoChatColaborador, Long> {

    @Join(value = "usuarioId", alias = "usuarioId")
    List<NcoChatColaborador> findByCentrocClienteIdCentrocClienteId(Integer centrocClienteId);

    @Join(value = "usuarioId", alias = "usuarioId")
    List<NcoChatColaborador> findByCentrocClienteIdCentrocClienteIdAndUsuarioIdUsuarioId(Integer centrocClienteId, Integer usuarioId);

    boolean existsByUsuarioIdUsuarioId(Integer usuarioId);

    void update(@Id Long chatColaboradorId, boolean esActual);

    void update(@Id Long chatColaboradorId, String mensajes, boolean esActual);

    void updateByConversacionId(String conversacionId, String mensajes);


    NcoChatColaborador findByUsuarioIdUsuarioId(Integer usuarioId);

}
