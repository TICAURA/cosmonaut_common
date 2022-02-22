package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsActividadEconomica;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsActividadEconomicaRepository extends CrudRepository<CsActividadEconomica, Integer> {

    @Query(value = "SELECT c_actividad_economica, "
            + "concat(c_actividad_economica , '-', descripcion) as descripcion , "
            + "fec_inicio, fec_fin, es_activo, sector_c_actividad_economica, nivel "
            + "FROM cs_actividad_economica "
            + "WHERE es_activo = :esActivo AND nivel = :nivel "
            + "ORDER BY c_actividad_economica ", nativeQuery = true)
    public List<CsActividadEconomica> findByEsActivoAndNivel(boolean esActivo, Integer nivel);

    @Query(value = "SELECT * FROM cs_actividad_economica "
            + "WHERE es_activo = :esActivo AND nivel = :nivel "
            + "ORDER BY c_actividad_economica ", nativeQuery = true)
    public List<CsActividadEconomica> findByEsActivoAndNivelNoConcat(boolean esActivo, Integer nivel);

    @Query(value = "SELECT c_actividad_economica, "
            + "concat(c_actividad_economica , '-', descripcion) as descripcion , "
            + "fec_inicio, fec_fin, es_activo, sector_c_actividad_economica, nivel "
            + "FROM cs_actividad_economica "
            + "WHERE es_activo = :esActivo AND nivel = :nivel "
            + "AND sector_c_actividad_economica = :sector "
            + "ORDER BY c_actividad_economica ", nativeQuery = true)
    public List<CsActividadEconomica> findByEsActivoAndNivel(boolean esActivo, Integer nivel, Integer sector);

    @Query(value = "SELECT * FROM cs_actividad_economica "
            + "WHERE es_activo = :esActivo AND nivel = :nivel "
            + "AND sector_c_actividad_economica = :sector "
            + "ORDER BY c_actividad_economica ", nativeQuery = true)
    public List<CsActividadEconomica> findByEsActivoAndNivelNoConcat(boolean esActivo, Integer nivel, Integer sector);

    @Query(value = "select cae.c_actividad_economica, "
            + "concat(cae .c_actividad_economica,' ', cae.descripcion) as descripcion , "
            + "cae.es_activo, cae.fec_fin, cae.fec_inicio, "
            + "cae.nivel, cae.sector_c_actividad_economica "
            + "from cs_actividad_economica cae "
            + "where cae.es_activo = :activo "
            + "order by cae.c_actividad_economica ", nativeQuery = true)
    List<CsActividadEconomica> findByEsActivo(Boolean activo);

    @Query(value = "select cae.* from cs_actividad_economica cae "
            + "where cae.es_activo = :activo "
            + "order by cae.c_actividad_economica ", nativeQuery = true)
    List<CsActividadEconomica> findByEsActivoNoConcat(Boolean activo);

    List<CsActividadEconomica> findByDescripcion(String descripcion);
}
