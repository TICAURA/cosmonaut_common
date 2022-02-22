package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatParentesco;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatParentescoRepository extends CrudRepository<CatParentesco, Integer> {

    List<CatParentesco> findByEsActivoOrderByDescripcion(Boolean activo);

    @Query(value = "SELECT * FROM cat_parentesco WHERE upper(descripcion) = :descripcion", nativeQuery = true)
    List<CatParentesco> findByDescripcionDuplicado(String descripcion);

    List<CatParentesco> findByDescripcion(String descripcion);

    List<CatParentesco> findByClave(String clave);

    @Query(value = "SELECT * FROM cat_parentesco WHERE (trim(clave) = :clave or upper(descripcion) = :descripcion)", nativeQuery = true)
    List<CatParentesco> findByClaveoOrDescripcionaAndEsActivoDuplicado(String clave, String descripcion);

    List<CatParentesco> findByDescripcionIlikeOrderByDescripcion(String descripcion);

}
