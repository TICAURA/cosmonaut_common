package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmUsuariosRepository extends CrudRepository<AdmUsuarios, Integer> {



    @NonNull
    @Join(value = "rolId", alias = "r")
    Optional<AdmUsuarios> findByEmail(@NonNull @NotNull String email);

    void update(@Id Integer id, Date fechaBaja, Boolean esActivo);

    void update(@Id Integer id, String password, Boolean passwordProvisional, Date fechaUltimoPassword);

    @Join(value = "rolId", alias = "r")
    List<AdmUsuarios> findByEsActivoOrderByEmail(Boolean activo);

    @Join(value = "rolId", alias = "r")
    List<AdmUsuarios> findByEsActivoOrderByEmail(Boolean activo, Pageable pageable);

    long countByRolIdRolId(Integer idRol);

    @Query(value = "SELECT COUNT(au.usuario_id) FROM adm_usuarios au " +
            "INNER JOIN adm_usuarioxcliente auxc ON au.usuario_id = auxc.usuario_id " +
            "WHERE auxc.es_activo = true " +
            "AND au.rol_id = :idRol AND auxc.centroc_cliente_id = :cliente ", nativeQuery = true)
    long countByRolIdRolIdAndCentrocClientesCentrocClienteId(Integer idRol, Integer cliente);

    @Query(value = "SELECT au.usuario_id, au.nombre, au.apellido_pat, au.apellido_mat, au.email, " +
            "CASE WHEN au.password is null THEN '--' ELSE '--' END as password, rol_id, au.fecha_alta, au.fecha_baja, au.es_activo, fecha_ultimo_password, " +
            "au.password_provisional, au.es_multicliente " +
            "FROM adm_usuarios au INNER JOIN adm_usuarioxcliente auxc ON au.usuario_id = auxc.usuario_id WHERE auxc.es_activo = true " +
            "AND au.rol_id = :idRol AND auxc.centroc_cliente_id = :cliente order by au.nombre asc", nativeQuery = true)
    List<AdmUsuarios> listByRolIdRolIdAndCentrocClientesCentrocClienteId(Integer cliente,Integer idRol);

    @Query(value = "SELECT au.usuario_id, au.nombre, au.apellido_pat, au.apellido_mat, email,CASE WHEN au.password is null THEN '--' ELSE '--' END as password, rol_id, au.fecha_alta, au.fecha_baja, " +
            "au.es_activo, fecha_ultimo_password, au.password_provisional, au.es_multicliente FROM adm_usuarios au " +
            "INNER JOIN adm_usuarioxcliente auxc ON au.usuario_id = auxc.usuario_id " +
            "INNER JOIN ncl_centroc_cliente ncc ON auxc.centroc_cliente_id = ncc.centroc_cliente_id " +
            "WHERE auxc.es_activo = true " +
            "AND au.rol_id = :idRol AND (ncc.centroc_cliente_id = :cliente OR ncc.centro_costos_centroc_cliente_id = :cliente) order by au.nombre asc", nativeQuery = true)
    List<AdmUsuarios> countByRolIdRolIdAndCentrocClientesCentrocClienteIdOrCentrocClientesCentroCostosCentrocClienteIdUsers(Integer idRol, Integer cliente);


    @Query(value = "SELECT au.usuario_id, au.nombre, au.apellido_pat, au.apellido_mat, email,CASE WHEN au.password is null THEN '--' ELSE '--' END as password, rol_id, au.fecha_alta, " +
            "au.fecha_baja, au.es_activo, fecha_ultimo_password, au.password_provisional, au.es_multicliente  FROM adm_usuarios au " +
            "INNER JOIN adm_usuarioxcliente auxc ON au.usuario_id = auxc.usuario_id " +
            "INNER JOIN ncl_centroc_cliente ncc ON auxc.centroc_cliente_id = ncc.centroc_cliente_id " +
            "WHERE auxc.es_activo = true " +
            "AND au.rol_id = :idRol AND ncc.centro_costos_centroc_cliente_id is null order by au.nombre asc", nativeQuery = true)
    List<AdmUsuarios>  cantidadRolesVersionCosmonautUsers(Integer idRol);

    @Query(value = "SELECT COUNT(au.usuario_id) FROM adm_usuarios au " +
            "INNER JOIN adm_usuarioxcliente auxc ON au.usuario_id = auxc.usuario_id " +
            "INNER JOIN ncl_centroc_cliente ncc ON auxc.centroc_cliente_id = ncc.centroc_cliente_id " +
            "WHERE auxc.es_activo = true " +
            "AND au.rol_id = :idRol AND (ncc.centroc_cliente_id = :cliente OR ncc.centro_costos_centroc_cliente_id = :cliente) ", nativeQuery = true)
    long countByRolIdRolIdAndCentrocClientesCentrocClienteIdOrCentrocClientesCentroCostosCentrocClienteId(Integer idRol, Integer cliente);

    @Query(value = "SELECT COUNT(au.usuario_id) FROM adm_usuarios au " +
            "INNER JOIN adm_usuarioxcliente auxc ON au.usuario_id = auxc.usuario_id " +
            "INNER JOIN ncl_centroc_cliente ncc ON auxc.centroc_cliente_id = ncc.centroc_cliente_id " +
            "WHERE auxc.es_activo = true " +
            "AND au.rol_id = :idRol AND ncc.centro_costos_centroc_cliente_id is null ", nativeQuery = true)
    long cantidadRolesVersionCosmonaut(Integer idRol);
}
