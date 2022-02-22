package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmCatSubmodulo;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmVersionCosmonaut;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmVersionCosmonautXsubmodulo;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmVersionCosmonautXsubmoduloRepository extends CrudRepository<AdmVersionCosmonautXsubmodulo, Integer> {

    @Join(value = "submoduloId", alias = "s")
    @Join(value = "versionCosmonautId", alias = "v")
    List<AdmVersionCosmonautXsubmodulo> findByVersionCosmonautIdAndVersionCosmonautIdEsActivo(AdmVersionCosmonaut admVersionCosmonaut, Boolean activo);

    @Join(value = "submoduloId", alias = "s")
    Set<AdmCatSubmodulo> findSubmoduloIdByVersionCosmonautIdVersionCosmonautIdAndVersionCosmonautIdEsActivo(
            @NotNull Integer versionId, @NotNull Boolean esActivo);

    @Join(value = "submoduloId", alias = "s")
    Set<AdmCatSubmodulo> findSubmoduloIdByVersionCosmonautIdVersionCosmonautIdAndVersionCosmonautIdEsActivoOrderBySubmoduloIdModuloIdAndSubmoduloIdSecuencia(
            @NotNull Integer versionId, @NotNull Boolean esActivo);

}
