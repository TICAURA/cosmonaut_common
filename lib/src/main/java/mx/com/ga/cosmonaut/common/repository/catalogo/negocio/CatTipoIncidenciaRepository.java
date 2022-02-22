package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoIncidencia;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatTipoIncidenciaRepository extends CrudRepository<CatTipoIncidencia, Integer> {

    @Query(value = "SELECT tipo_incidencia_id, descripcion, nombre_corto, " +
            "es_incidencia, es_activo, fecha_alta, " +
            "p_tipo_percepcion_id, p_especializacion, d_tipo_deduccion_id, d_especializacion " +
            "FROM cat_tipo_incidencia " +
            "WHERE es_activo = :activo AND es_incidencia = :activo " +
            "ORDER BY descripcion ", nativeQuery = true)
    List<CatTipoIncidencia> findByEsActivoAndEsIncidenciaOrderByDescripcion(Boolean activo);
    
    List<CatTipoIncidencia> findByDescripcion(String descripcion);

    @Query(value = "select concat(tipo_incidencia_id,'-',descripcion) as descripcion from cat_tipo_incidencia  " +
            "where es_activo =:activo " +
            "and es_incidencia =:activo " +
            "order by tipo_incidencia_id ", nativeQuery = true)
    List<String> findByEsActivoAndEsIncidencia(Boolean activo);

    @Join(value = "tipoDeduccionId", alias = "tipoDeduccionId")
    @Join(value = "tipoPercepcionId", alias = "tipoPercepcionId")
    Optional<CatTipoIncidencia> findByTipoDeduccionIdTipoDeduccionId(@NonNull String tipoDeduccionId);

    @Join(value = "tipoDeduccionId", alias = "tipoDeduccionId")
    @Join(value = "tipoPercepcionId", alias = "tipoPercepcionId")
    Optional<CatTipoIncidencia> findByTipoPercepcionIdTipoPercepcionId(@NonNull String tipoPercepcionId);

    @Override
    @Join(value = "tipoDeduccionId", alias = "tipoDeduccionId", type = Join.Type.LEFT)
    @Join(value = "tipoPercepcionId", alias = "tipoPercepcionId", type = Join.Type.LEFT)
    Optional<CatTipoIncidencia> findById(@NonNull Integer integer);

    List<CatTipoIncidencia> findByDescripcionIlike(String descripcion);

}