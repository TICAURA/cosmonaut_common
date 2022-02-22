package mx.com.ga.cosmonaut.common.repository.temporal;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.temporal.CargaMasivaIncidencias;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CargaMasivaIncidenciasRepository extends CrudRepository<CargaMasivaIncidencias, Integer> {

    List<CargaMasivaIncidencias> findByCentrocClienteIdAndEsCorrecto(Integer centrocClienteId, Boolean esCorrecto);

    List<CargaMasivaIncidencias> findByCentrocClienteId(Integer centrocClienteId);

    void deleteByCentrocClienteId(Integer centrocClienteId);

    void update(@Id Integer id, boolean esCorrecto, String errores);

    boolean existsByCentrocClienteIdAndEsCorrecto(Integer centrocClienteId, Boolean esCorrecto);
}
