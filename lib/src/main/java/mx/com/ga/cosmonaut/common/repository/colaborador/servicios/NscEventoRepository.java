package mx.com.ga.cosmonaut.common.repository.colaborador.servicios;

import mx.com.ga.cosmonaut.common.entity.colaborador.servicios.NscEvento;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NscEventoRepository extends CrudRepository<NscEvento, Integer> {

}