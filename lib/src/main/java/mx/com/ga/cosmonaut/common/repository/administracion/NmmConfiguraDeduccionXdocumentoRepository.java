package mx.com.ga.cosmonaut.common.repository.administracion;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaClienteXcentroCostos;
import mx.com.ga.cosmonaut.common.entity.administracion.NmmConfiguraDeduccionXdocumento;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NmmConfiguraDeduccionXdocumentoRepository extends CrudRepository<NmmConfiguraDeduccionXdocumento, Integer> {

    @NonNull
    Optional<NmmConfiguraDeduccionXdocumento> findByConfiguraDeduccionIdConfiguraDeduccionId(@NonNull @NotNull Integer integer);

    @Query(value = "UPDATE nmm_configura_deduccion_xdocumento SET documento_suspension_id = NULL " +
            "WHERE documento_suspension_id = :documentoId ", nativeQuery = true)
    void updateSetNullByDocumentoSuspensionId(Integer documentoId);

    @Query(value = "UPDATE nmm_configura_deduccion_xdocumento SET documento_retencion_id = NULL " +
            "WHERE documento_retencion_id = :documentoId ", nativeQuery = true)
    void updateSetNullByDocumentoRetencionId(Integer documentoId);

}