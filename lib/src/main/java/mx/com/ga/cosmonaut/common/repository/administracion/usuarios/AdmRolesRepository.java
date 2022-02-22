package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmRoles;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.util.Date;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmRolesRepository extends CrudRepository<AdmRoles, Integer> {

    void update(@Id Integer id, Date fechaBaja, Boolean esActivo);

    List<AdmRoles> findByEsActivoOrderByNombreRol(Boolean activo);

    List<AdmRoles> findByCentrocClientesCentrocClienteIdAndEsActivo(Integer centrocClienteId, boolean esActivo);
    @Query(value = "select  ar.* from adm_rol_xcentroc_cliente ac join adm_roles  ar on (ac.rol_id= ar.rol_id) where upper(ar.nombre_rol) =  :rol and ac.centroc_cliente_id = :centrocClienteId",
            nativeQuery = true)
    List<AdmRoles> findByRolCentro(Integer centrocClienteId, String rol);

    @Query(value = "select  ac.centroc_cliente_id as respuesta from adm_rol_xcentroc_cliente ac join adm_roles  ar on (ac.rol_id= ar.rol_id) where ac.rol_id = :rol",
            nativeQuery = true)
    Integer findByCentroCostoClienteByIdRol(Integer rol);

}
