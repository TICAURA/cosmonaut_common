package mx.com.ga.cosmonaut.common.repository.administracion;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaDomicilio;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NmaDomicilioRepository extends CrudRepository<NmaDomicilio, Integer> {

    @Join(value = "personaId", alias = "persona")
    List<NmaDomicilio> findByPersonaIdPersonaId(Integer personaId);

    @Join(value = "sedeId", alias = "sede")
    List<NmaDomicilio> findByCentrocClienteIdCentrocClienteId(Integer centrocClienteId);

    @Query(value = "SELECT * FROM nma_domicilio where centroc_cliente_id = :centrocClienteId and sede_id is null", nativeQuery = true)
    List<NmaDomicilio> findByCentrocClienteId(Integer centrocClienteId);

    boolean existsByCentrocClienteIdCentrocClienteId(Integer centrocClienteId);

    boolean existsByPersonaIdPersonaId(Integer personaId);

}