package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmPermisosXsubmoduloXrol;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmPreferenciasCliente;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmPreferenciasClienteRepository extends CrudRepository<AdmPreferenciasCliente, Integer> {
    public AdmPreferenciasCliente findByClienteId(Long clienteId);
}
