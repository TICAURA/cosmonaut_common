package mx.com.ga.cosmonaut.common.repository;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.PagosLiquidacionColaborador;

import java.util.Date;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface PagosLiquidacionColaboradorRepository extends CrudRepository<PagosLiquidacionColaborador, Integer>  {

    @Join(value = "pagosLiquidacionId", alias = "pagosLiquidacionId")
    List<PagosLiquidacionColaborador> findByFechaContratoAndPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteId(Date fechaContrato, Integer personaId, Integer centrocClienteId);

}
