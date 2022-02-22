package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoRegimenContratacion;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsTipoRegimenContratacionRepository extends CrudRepository<CsTipoRegimenContratacion, String> {

    @Query(value = "SELECT tipo_regimen_contratacion_id, CASE WHEN length(tipo_regimen_contratacion_id) = 1  " +
            "THEN concat(tipo_regimen_contratacion_id , '  - ' , descripcion) ELSE concat(tipo_regimen_contratacion_id , ' - ' , descripcion) " +
            "END as descripcion  , fecha_inicio, es_activo, fecha_fin FROM cs_tipo_regimen_contratacion where es_activo = :activo order by cast(tipo_regimen_contratacion_id as numeric) ", nativeQuery = true)
    List<CsTipoRegimenContratacion> findByEsActivo(Boolean activo);

    
    @Query(value = "SELECT * FROM cs_tipo_regimen_contratacion " +
            "where es_activo = :activo " +
            "order by cast(tipo_regimen_contratacion_id as numeric) ", nativeQuery = true)
    List<CsTipoRegimenContratacion> findByEsActivoNoConcat(Boolean activo);
    
    List<CsTipoRegimenContratacion> findByDescripcion(String descripcion);

    List<CsTipoRegimenContratacion> findByTipoRegimenContratacionId(String tipoRegimenContratacionId);

    List<CsTipoRegimenContratacion> findByDescripcionIlike(String descripcion);

}