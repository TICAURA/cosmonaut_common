package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmCatModulos;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmCatModulosRepository extends CrudRepository<AdmCatModulos, Integer> {

    @Join(value = "submodulos", alias = "s")
    @Join(value = "submodulos.permisos", alias = "p")
    List<AdmCatModulos> findByEsActivo(Boolean activo);

    @Join(value = "submodulos", alias = "s")
    @Join(value = "submodulos.permisos", alias = "p")
    List<AdmCatModulos> findByEsActivoOrderBySecuencia(Boolean activo);

    @Join(value = "submodulos", alias = "s")
    @Join(value = "submodulos.permisos", alias = "p")
    @Join(value = "submodulos.permisos.permisosXsubmodulos", alias = "pxs")
    AdmCatModulos findBySubmodulosPermisosPermisosXsubmodulosPermisoXsubmoduloId(Integer id);

}
