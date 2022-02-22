package mx.com.ga.cosmonaut.common.repository.cliente;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocClienteXproveedor;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclCentrocClienteXproveedorRepository extends CrudRepository<NclCentrocClienteXproveedor, Integer> {

    @NonNull
    @Override
    @Join(value = "centrocClienteId", alias = "c")
    @Join(value = "proveedorDispersionId", alias = "d", type = Join.Type.LEFT_FETCH)
    @Join(value = "proveedorTimbradoId", alias = "t", type = Join.Type.LEFT_FETCH)
    Optional<NclCentrocClienteXproveedor> findById(@NonNull @NotNull Integer integer);

    @NonNull
    @Override
    @Join(value = "centrocClienteId", alias = "c")
    @Join(value = "proveedorDispersionId", alias = "d", type = Join.Type.LEFT_FETCH)
    @Join(value = "proveedorTimbradoId", alias = "t", type = Join.Type.LEFT_FETCH)
    Iterable<NclCentrocClienteXproveedor> findAll();

    @Join(value = "centrocClienteId", alias = "c")
    @Join(value = "proveedorDispersionId", alias = "d", type = Join.Type.LEFT_FETCH)
    @Join(value = "proveedorTimbradoId", alias = "t", type = Join.Type.LEFT_FETCH)
    Optional<NclCentrocClienteXproveedor> findByCentrocClienteIdCentrocClienteId(Integer clienteId);

    @Join(value = "centrocClienteId", alias = "c")
    @Join(value = "proveedorDispersionId", alias = "d", type = Join.Type.LEFT_FETCH)
    @Join(value = "proveedorTimbradoId", alias = "t", type = Join.Type.LEFT_FETCH)
    Set<NclCentrocClienteXproveedor> findByCentrocClienteIdIn(Set<NclCentrocCliente> clientes);

}