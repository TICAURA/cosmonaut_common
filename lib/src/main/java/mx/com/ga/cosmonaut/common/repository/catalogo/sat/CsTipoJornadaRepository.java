package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoJornada;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsTipoJornadaRepository extends CrudRepository<CsTipoJornada, String> {

    @Query(value = "SELECT tipo_jornada_id, descripcion, " +
            "fecha_inicio, fecha_fin, es_activo " +
            "FROM cs_tipo_jornada " +
            "WHERE es_activo = :activo " +
            "ORDER BY descripcion", nativeQuery = true)
    List<CsTipoJornada> findByEsActivo(Boolean activo);
}