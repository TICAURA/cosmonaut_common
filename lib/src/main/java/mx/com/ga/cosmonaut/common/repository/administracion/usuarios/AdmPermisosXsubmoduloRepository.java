package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import lombok.NonNull;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmCatSubmodulo;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmPermisosXsubmodulo;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmPermisosXsubmoduloRepository extends CrudRepository<AdmPermisosXsubmodulo, Integer> {

    @Join(value = "permisoId", alias = "p")
    @Join(value = "submoduloId", alias="s")
    List<AdmPermisosXsubmodulo> findBySubmoduloIdAndSubmoduloIdEsActivoAndPermisoIdEsActivo(AdmCatSubmodulo submoduloId, Boolean esActivo, Boolean esActivo2);

    @NonNull
    Optional<AdmPermisosXsubmodulo> findBySubmoduloIdSubmoduloIdAndPermisoIdPermisoId(
            @NonNull @NotNull Integer submoduloId, @NonNull @NotNull Integer permisoId);


}
