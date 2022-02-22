package mx.com.ga.cosmonaut.common.repository.calculo;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrVariabilidadXcolaborador;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrVariabilidadXcolaboradorPK;

import javax.validation.constraints.NotNull;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrVariabilidadXcolaboradorRepository extends CrudRepository<NcrVariabilidadXcolaborador, NcrVariabilidadXcolaboradorPK> {

    List<NcrVariabilidadXcolaborador> findByVariabilidadIdAndDiferenciaNotEquals(Integer variabilidadId, Double diferencia);

    void deleteByVariabilidadId(@NonNull @NotNull Integer integer);

}
