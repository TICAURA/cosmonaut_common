package mx.com.ga.cosmonaut.common.repository.catalogo.ubicacion;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatCiudad;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatCiudadRepository extends CrudRepository<CatCiudad, Short> {

    @Query(value = "SELECT c_cve_ciudad, d_ciudad, es_activo, fecha_alta, c_estado " +
            "FROM cat_ciudad " +
            "where es_activo = :activo " +
            "order by d_ciudad ", nativeQuery = true)
    List<CatCiudad> findByEsActivo(Boolean activo);

}
