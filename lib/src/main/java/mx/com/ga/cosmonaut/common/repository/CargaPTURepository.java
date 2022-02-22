package mx.com.ga.cosmonaut.common.repository;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.CargaPTU;

import javax.annotation.Nullable;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CargaPTURepository extends CrudRepository<CargaPTU, Long> {

    List<CargaPTU> findByNominaPeriodoIdAndIndProcesadoIsNull(Long nominaPeriodoId);

    List<CargaPTU> findByNominaPeriodoIdAndIndProcesadoIsNotNull(Long nominaPeriodoId);

    void deleteByCentrocClienteId(Long centrocClienteId);

    void updateByNominaPeriodoId(Long nominaPeriodoId, @Nullable Long indProcesado);

    void deleteByNominaPeriodoId(Long nominaPeriodoId);

}
