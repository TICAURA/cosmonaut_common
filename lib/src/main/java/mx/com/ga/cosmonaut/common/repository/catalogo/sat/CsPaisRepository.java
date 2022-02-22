package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsPais;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsPaisRepository extends CrudRepository<CsPais, String> {

    List<CsPais> findByEsActivo(Boolean activo);
}