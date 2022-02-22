package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.cliente.NclGuardaPuestos;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclGuardaPuestoRepository extends CrudRepository<NclGuardaPuestos, Integer> {
    
    
    Integer update(@Id Integer puestoId, boolean esActivo);

}