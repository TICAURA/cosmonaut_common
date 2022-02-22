package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrVariabilidad;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrVariabilidadRepository extends CrudRepository<NcrVariabilidad, Integer> {

    boolean existsByEstadoVariabilidadIdActualAndAnioFiscalAndBimestre(Short estadoVariabilidadIdActual, Integer anioFiscal, Integer bimestre);

    boolean existsByEstadoVariabilidadIdActualNotEqualsAndAnioFiscalAndBimestreAndCentrocClienteId(Short estadoVariabilidadIdActual, Integer anioFiscal, Integer bimestre,Integer centrocClienteId);

    void updateByVariabilidadId(Integer variabilidadId,Short estadoVariabilidadIdActual);

}
