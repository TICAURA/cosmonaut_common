package mx.com.ga.cosmonaut.common.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.DocumentosEmpleado;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrBitacoraTimbrado;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface DocumentosEmpleadoRepository extends CrudRepository<DocumentosEmpleado, Integer>  {

    Optional<String> findDistinctcmsExpedienteIdByCentrocClienteIdAndPersonaId(Integer centrocClienteId, Integer personaId);

    List<DocumentosEmpleado> findByCentrocClienteIdAndPersonaId(Integer centrocClienteId, Integer personaId);

    @Query("SELECT * FROM documentos_empleado WHERE persona_id = :personaId AND centroc_cliente_id = :clienteId " +
            "AND tipo_documento_id = 10 AND cms_tipo_multimedia = 1 ORDER BY fecha_carga DESC LIMIT 1")
    Optional<DocumentosEmpleado> findComprobantePDF(Integer personaId, Integer clienteId);


    @Query("SELECT * FROM documentos_empleado WHERE persona_id = :personaId AND centroc_cliente_id = :clienteId " +
            "AND tipo_documento_id = 10 AND cms_tipo_multimedia = 1 and nombre_archivo ilike concat('%',:nombre,'%') ORDER BY fecha_carga DESC LIMIT 1")
    Optional<DocumentosEmpleado> findComprobanteFiscalNominaxPeriodoEmpleadoPDF(Integer personaId, Integer clienteId,String nombre);

    @Query("SELECT * FROM documentos_empleado WHERE persona_id = :personaId AND centroc_cliente_id = :clienteId " +
            "AND tipo_documento_id = 10 AND cms_tipo_multimedia = 2 ORDER BY fecha_carga DESC LIMIT 1")
    Optional<DocumentosEmpleado> findComprobanteXML(Integer personaId, Integer clienteId);

}
