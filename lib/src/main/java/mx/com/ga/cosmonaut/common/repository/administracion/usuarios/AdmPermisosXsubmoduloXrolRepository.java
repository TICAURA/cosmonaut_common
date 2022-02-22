package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import lombok.NonNull;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmPermisosXsubmodulo;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmPermisosXsubmoduloXrol;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmRoles;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmPermisosXsubmoduloXrolRepository extends CrudRepository<AdmPermisosXsubmoduloXrol, Integer> {

    @Join(value = "rolId", alias = "r")
    @Join(value = "permisoXsubmoduloId", alias = "pxs")
    @Join(value = "permisoXsubmoduloId.submoduloId", alias = "s")
    @Join(value = "permisoXsubmoduloId.submoduloId.moduloId", alias = "m")
    @Join(value = "permisoXsubmoduloId.permisoId", alias = "p")
    List<AdmPermisosXsubmoduloXrol> findByRolId(AdmRoles rolId);

    @NonNull
    Optional<AdmPermisosXsubmoduloXrol> findByRolIdAndPermisoXsubmoduloId(
            @NonNull @NotNull AdmRoles rolId, @NonNull @NotNull AdmPermisosXsubmodulo permisosXsubmoduloId);

    @Join(value = "permisoXsubmoduloId", alias = "pxs")
    @Join(value = "permisoXsubmoduloId.permisoId", alias = "p")
    Set<AdmPermisosXsubmodulo> findPermisoXsubmoduloIdByRolId(AdmRoles rolId);

}
