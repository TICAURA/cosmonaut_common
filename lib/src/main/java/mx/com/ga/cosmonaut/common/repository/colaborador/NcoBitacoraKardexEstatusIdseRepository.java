package mx.com.ga.cosmonaut.common.repository.colaborador;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoBitacoraKardexEstatusIdse;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcoBitacoraKardexEstatusIdseRepository extends CrudRepository<NcoBitacoraKardexEstatusIdse, Long> {

}
