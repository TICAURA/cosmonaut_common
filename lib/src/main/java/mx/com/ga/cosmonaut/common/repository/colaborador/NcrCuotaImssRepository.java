package mx.com.ga.cosmonaut.common.repository.colaborador;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrCuotaImss;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrCuotaImssRepository extends CrudRepository<NcrCuotaImss, Integer> {

    @Join(value = "tipoCuotaImssId", alias = "tipoCuotaImssId")
    List<NcrCuotaImss> findByEsActivo(boolean esActivo);

}
