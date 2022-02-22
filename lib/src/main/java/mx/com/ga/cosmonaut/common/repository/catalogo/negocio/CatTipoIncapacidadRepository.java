package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoIncapacidad;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatTipoIncapacidadRepository extends CrudRepository<CatTipoIncapacidad, Integer> {
    List<CatTipoIncapacidad> findByEsActivoOrderByDescripcion(Boolean activo);

     List<CatTipoIncapacidad> findByDescripcion(String descripcion);

     @Query(value = "select concat(tipo_incapacidad_id,'-',descripcion) as descripcion from cat_tipo_incapacidad " +
             "where es_activo =:activo " +
             "order by tipo_incapacidad_id ", nativeQuery = true)
     List<String> findByEsActivo(Boolean activo);

    List<CatTipoIncapacidad> findByDescripcionIlikeOrderByDescripcion(String descripcion);

}