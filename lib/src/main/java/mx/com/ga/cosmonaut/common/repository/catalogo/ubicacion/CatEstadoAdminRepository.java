package mx.com.ga.cosmonaut.common.repository.catalogo.ubicacion;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstado;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstadoAdmin;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatEstadoAdminRepository extends CrudRepository<CatEstadoAdmin, Integer> {

    List<CatEstadoAdmin> findByEsActivoOrderByEstado(Boolean esActivo);
    
    List<CatEstadoAdmin> findByEstado(String estado);
}