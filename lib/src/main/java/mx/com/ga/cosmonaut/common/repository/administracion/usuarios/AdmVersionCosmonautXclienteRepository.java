package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmVersionCosmonaut;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmVersionCosmonautXcliente;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmVersionCosmonautXclienteRepository extends CrudRepository<AdmVersionCosmonautXcliente, Integer> {

    AdmVersionCosmonautXcliente findByCentrocClienteIdAndVersionCosmonautId(NclCentrocCliente nclCentrocCliente, AdmVersionCosmonaut admVersionCosmonaut);

    @Join(value = "versionCosmonautId", alias = "v")
    @Join(value = "centrocClienteId", alias = "c")
    AdmVersionCosmonautXcliente findByCentrocClienteId(NclCentrocCliente nclCentrocCliente);

    @Query(value = "select version_cosmonaut_id from adm_version_cosmonautxcliente where centroc_cliente_id  = :idcliente",nativeQuery = true)
    Integer encontrarVersionPorCliente(Integer idcliente);

}
