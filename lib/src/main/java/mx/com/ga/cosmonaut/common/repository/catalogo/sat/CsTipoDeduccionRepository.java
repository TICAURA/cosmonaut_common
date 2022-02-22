package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoDeduccion;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsTipoDeduccionRepository extends CrudRepository<CsTipoDeduccion, String> {

    @Query(value = "SELECT tipo_deduccion_id, " +
            "concat(tipo_deduccion_id , '-', descripcion) as descripcion , " +
            "fecha_inicio, es_activo, fecha_fin, es_por_defecto, no_editable, base_calculo_id, valor, especializacion " +
            "FROM public.cs_tipo_deduccion " +
            "where es_activo = :activo " +
            "order by tipo_deduccion_id ", nativeQuery = true)
    List<CsTipoDeduccion> findByEsActivo(Boolean activo);

    @Query(value = "SELECT * FROM public.cs_tipo_deduccion " +
            "where es_activo = :activo " +
            "order by tipo_deduccion_id ", nativeQuery = true)
    List<CsTipoDeduccion> findByEsActivoNoConcat(Boolean activo);
    
    List<CsTipoDeduccion> findByPorDefecto(Boolean porDefecto);
    
    List<CsTipoDeduccion> findByDescripcion(String descripcion);

    List<CsTipoDeduccion> findByTipoDeduccionId(String descripcion);

    Optional<CsTipoDeduccion> findByTipoDeduccionIdAndEspecializacion(String tipoDeduccionId, String especializacion);

    List<CsTipoDeduccion> findByDescripcionIlike(String descripcion);

}