package mx.com.ga.cosmonaut.common.repository.temporal;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.temporal.CargaMasivaEmpleado;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CargaMasivaRepository extends CrudRepository<CargaMasivaEmpleado, Integer> {

    List<CargaMasivaEmpleado> findByCentrocClienteIdAndEsCorrecto(Integer centrocClienteId, Boolean esCorrecto);

    List<CargaMasivaEmpleado> findByCentrocClienteId(Integer centrocClienteId);

    void deleteByCentrocClienteId(Integer centrocClienteId);

    boolean existsByCentrocClienteIdAndEsCorrecto(Integer centrocClienteId, Boolean esCorrecto);
}
