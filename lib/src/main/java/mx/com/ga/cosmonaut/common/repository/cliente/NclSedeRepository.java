package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import mx.com.ga.cosmonaut.common.entity.cliente.NclSede;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclSedeRepository extends CrudRepository<NclSede, Integer> {

    @Join(value = "centrocClienteId", type = Join.Type.FETCH, alias = "cliente")
    List<NclSede> findByCentrocClienteIdCentrocClienteIdAndEsActivo(Integer centrocClienteId, Boolean esActivo);

    void update(@Id Integer id, Boolean esActivo);

    List<NclSede> findByEsActivoOrderByDescripcion(Boolean activo);

    boolean existsBySedeIdAndCentrocClienteIdCentrocClienteId(Integer sedeId, Integer centrocClienteId);
}