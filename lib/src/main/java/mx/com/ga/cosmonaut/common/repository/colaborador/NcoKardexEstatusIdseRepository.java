package mx.com.ga.cosmonaut.common.repository.colaborador;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoKardexEstatusIdse;

import java.util.List;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcoKardexEstatusIdseRepository extends CrudRepository<NcoKardexEstatusIdse, Long> {

    Set<String> findIdProcesoByEstatusIdseIdEstatusIdseId(Long estatusIdseId);

    List<NcoKardexEstatusIdse> findByIdProceso(String idProceso);

    Set<String> findNumeroLoteByEstatusIdseIdEstatusIdseId(Long estatusIdseId);

    List<NcoKardexEstatusIdse> findByNumeroLote(String numeroLote);

}
