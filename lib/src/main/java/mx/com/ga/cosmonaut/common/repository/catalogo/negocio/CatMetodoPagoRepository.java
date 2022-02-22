package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatMetodoPago;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatMetodoPagoRepository extends CrudRepository<CatMetodoPago, Integer> {

    @Query(value = "SELECT * FROM cat_metodo_pago WHERE metodo_pago_id <> 4 AND metodo_pago_id <> 5" +
            "AND es_activo = :activo ORDER BY descripcion ASC", nativeQuery = true)
    List<CatMetodoPago> findByEsActivoEspecial(Boolean activo);

    List<CatMetodoPago> findByEsActivoOrderByDescripcion(Boolean activo);

    List<CatMetodoPago> findByDescripcion(String descripcion);

    List<CatMetodoPago> findByDescripcionIlikeOrderByDescripcion(String descripcion);

}