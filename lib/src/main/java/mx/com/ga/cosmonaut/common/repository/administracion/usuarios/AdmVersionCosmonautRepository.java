package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmVersionCosmonaut;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmVersionCosmonautRepository extends CrudRepository<AdmVersionCosmonaut, Integer> {

    List<AdmVersionCosmonaut> findByEsActivoOrderByNombreVersion(Boolean activo);

}
