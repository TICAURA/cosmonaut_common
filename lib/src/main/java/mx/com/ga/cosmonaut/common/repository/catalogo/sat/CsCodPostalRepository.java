package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Join;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsCodPostal;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsCodPostalRepository extends CrudRepository<CsCodPostal, String> {
    
    @Override
    @Join(value = "estadoId", alias="E")
    @Join(value = "localidadId", alias="L")
    @Join(value = "municipioId", alias="M")
    List<CsCodPostal> findAll();
    
    
    List<CsCodPostal> findByCodigoPostal(String codigoPostal);

}