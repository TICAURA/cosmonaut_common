package mx.com.ga.cosmonaut.common.repository.administracion;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaCuentaBanco;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NmaCuentaBancoRepository extends CrudRepository<NmaCuentaBanco, Long> {

    @Override
    @Join(value = "bancoId", alias = "cb")
    @Join(value = "nclCentrocCliente", alias="ncc")
    List<NmaCuentaBanco> findAll();

    @Query(value = "select * from nma_cuenta_banco where centroc_cliente_id = :idCentrocCliente", nativeQuery=true)
    List<NmaCuentaBanco> obtenerCuentaCliente(Integer idCentrocCliente);

    @Join(value = "bancoId", alias = "cb")
    @Join(value = "funcionCuentaId", alias = "fn")
    List<NmaCuentaBanco> findByNclCentrocClienteCentrocClienteId(Integer centrocClienteId);
    
    @Join(value = "bancoId", alias = "cb")
    @Join(value = "funcionCuentaId", alias = "fn")
    List<NmaCuentaBanco> findByNclCentrocClienteCentrocClienteIdAndUsaStpIsNullOrUsaStp(Integer centrocClienteId, Boolean usaStp);
    
    @Join(value = "bancoId", alias = "cb")
    NmaCuentaBanco findByNclCentrocClienteCentrocClienteIdAndUsaStp(Integer clienteId, Boolean usaStp);
   
    @Query(value = "select * from nma_cuenta_banco where num_cuenta = :numeroCuenta", nativeQuery=true)
    NmaCuentaBanco findByNumeroCuenta(String numeroCuenta);

    @Query(value="SELECT * FROM nma_cuenta_banco where num_cuenta = :numeroCuenta and banco_id = :bancoId and centroc_cliente_id = :clienteId and es_activo", nativeQuery = true)
    List<NmaCuentaBanco> findByNumeroCuenta(String numeroCuenta, Long bancoId,Integer clienteId);

    List<NmaCuentaBanco> findByClabe(String clabe);

    List<NmaCuentaBanco> findByClabeAndNclCentrocClienteCentrocClienteId(String clabe,Integer clienteId);

    @Query(value="update nma_cuenta_banco set es_activo=false where clabe = :clabe", nativeQuery = true)
    NmaCuentaBanco update(String clabe);
    
    void update(@Id Integer cuentaBancoId, boolean esActivo);

    @Query(value="select * from nma_cuenta_banco ncb where em_usa_stp = true and centroc_cliente_id = :idCliente", nativeQuery = true)
     List<NmaCuentaBanco> obtieneSTP(Integer idCliente);

    boolean existsByNclCentrocClienteCentrocClienteId(Integer centrocClienteId);

    @Join(value = "bancoId", alias = "banco")
    @Join(value = "nclCentrocCliente", alias="ncc")
    @Join(value = "ncoPersona", alias = "np")
    Optional<NmaCuentaBanco>  findByNcoPersonaPersonaId(Integer personaId);

    boolean existsByNcoPersonaPersonaId(Integer personaId);

    List<NmaCuentaBanco> findByEsActivoOrderByDescripcion(Boolean activo);
    
    
    List<NmaCuentaBanco> findByNclCentrocClienteCentrocClienteIdAndFuncionCuentaIdFuncionCuentaIdIn(Integer centrocClienteId, List<Integer> funcionCuentaId);

    boolean existsByNumeroCuentaAndBancoIdBancoId(String numeroCuenta, Integer bancoId);

    boolean existsByNumeroCuentaAndBancoIdBancoIdAndNclCentrocClienteCentrocClienteId(String numeroCuenta, Integer bancoId,Integer centrocClienteId);

    boolean existsByClabe(String clabe) ;


    boolean existsByClabeAndNclCentrocClienteCentrocClienteId(String clabe, Integer centrocClienteId);

    @Join(value = "bancoId", alias = "cb")
    @Join(value = "funcionCuentaId", alias = "fn")
    List<NmaCuentaBanco> findByNumeroCuentaIlikeAndClabeIlikeAndBancoIdNombreCortoIlikeAndNclCentrocClienteCentrocClienteIdAndUsaStpIsNullOrUsaStp(String nombreCuenta, String clabe, String nombreCorto, Integer centrocClienteId, Boolean usaStp);

    @Query(value="update nma_cuenta_banco set es_activo=false, clabe='', num_cuenta='' where centroc_cliente_id = :clienteId and persona_id = :personaId", nativeQuery = true)
    NmaCuentaBanco updateEliminarCLABE(Integer clienteId,Integer personaId);
}
