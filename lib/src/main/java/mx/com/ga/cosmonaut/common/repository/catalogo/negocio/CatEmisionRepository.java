package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatEmision;

import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatEmisionRepository extends CrudRepository<CatEmision, Integer> {

    @Join(value = "idTipoEmision", alias = "te")
    Set<CatEmision> findByIdTipoEmisionIdTipoEmision(Integer idTipoEmision);

}