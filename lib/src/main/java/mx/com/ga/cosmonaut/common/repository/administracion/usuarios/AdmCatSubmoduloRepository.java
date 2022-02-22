package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmCatSubmodulo;

import javax.validation.constraints.NotNull;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmCatSubmoduloRepository extends CrudRepository<AdmCatSubmodulo, Integer> {

    @Join(value = "moduloId", alias = "m")
    @Join(value = "permisos", alias = "p")
    List<AdmCatSubmodulo> findByEsActivoOrderByNombreSubmodulo(Boolean activo);

    @Join(value = "moduloId", alias = "m")
    @Join(value = "permisos", alias = "p")
    AdmCatSubmodulo findBySubmoduloId(@NotNull Integer id);

    @Join(value = "moduloId", alias = "m")
    @Join(value = "permisos", alias = "p")
    List<AdmCatSubmodulo> findByEsActivoOrderByModuloIdAndSecuenciaAndNombreSubmodulo(Boolean activo);

}
