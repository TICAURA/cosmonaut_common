package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrEmpleadoXnominaPK;
import mx.com.ga.cosmonaut.common.entity.colaborador.EmpleadoXnomina;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface EmpleadoXnominaRepository extends CrudRepository<EmpleadoXnomina, Integer> {

    void deleteByNcrEmpleadoXnominaPK(NcrEmpleadoXnominaPK ncrEmpleadoXnominaPK);

}