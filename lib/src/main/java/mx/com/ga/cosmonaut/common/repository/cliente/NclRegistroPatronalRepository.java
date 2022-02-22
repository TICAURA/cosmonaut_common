package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.cliente.NclRegistroPatronal;

import java.util.Optional;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclRegistroPatronalRepository extends CrudRepository<NclRegistroPatronal, Integer> {

    @Join(value = "centrocClienteId", type = Join.Type.FETCH, alias = "cliente")
    Optional<NclRegistroPatronal> findByCentrocClienteIdCentrocClienteId(Integer centrocClienteId);

    boolean existsByCentrocClienteIdCentrocClienteId(Integer centrocClienteId);

    @Join(value = "centrocClienteId", type = Join.Type.FETCH, alias = "cliente")
    Set<NclRegistroPatronal> findByCentrocClienteId(NclCentrocCliente centrocClienteId);

}