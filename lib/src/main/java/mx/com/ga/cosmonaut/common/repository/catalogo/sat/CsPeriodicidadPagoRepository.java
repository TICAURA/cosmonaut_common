package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsPeriodicidadPago;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsPeriodicidadPagoRepository extends CrudRepository<CsPeriodicidadPago, String> {

    @Override
    List<CsPeriodicidadPago> findAll();

    CsPeriodicidadPago findByDescripcion(String descripcion);

    List<CsPeriodicidadPago> findByEsActivoOrderByDescripcion(boolean esActivo);

    List<CsPeriodicidadPago> findByEsActivoOrderByNumeroDiasPeriodo(boolean esActivo);
}