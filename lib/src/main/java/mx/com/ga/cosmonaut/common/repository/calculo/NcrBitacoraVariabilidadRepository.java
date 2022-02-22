package mx.com.ga.cosmonaut.common.repository.calculo;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrBitacoraVariabilidad;

import javax.validation.constraints.NotNull;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrBitacoraVariabilidadRepository extends CrudRepository<NcrBitacoraVariabilidad, Integer> {

    void deleteByVariabilidadId(@NonNull @NotNull Integer integer);
}
