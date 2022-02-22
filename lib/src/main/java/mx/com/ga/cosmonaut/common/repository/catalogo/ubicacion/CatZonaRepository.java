package mx.com.ga.cosmonaut.common.repository.catalogo.ubicacion;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatZona;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatZonaRepository extends CrudRepository<CatZona,Integer> {

    @Query(value = "SELECT c_zona, d_zona, es_activo, fecha_alta " +
            "FROM public.cat_zona " +
            "where es_activo = :activo " +
            "order by d_zona ", nativeQuery = true)
    List<CatZona> findByEsActivo(Boolean activo);
}
