package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrEmpleadoXnomina;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrNominaXperiodo;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrEmpleadoXnominaRepository extends CrudRepository<NcrEmpleadoXnomina, Integer> {

    List<NcrEmpleadoXnomina> findByNominaXperiodoIdNominaXperiodoId(Integer nominaXperiodoId);

    @Query(value = "SELECT * FROM ncr_empleado_xnomina empleado\n" +
            "    INNER JOIN nco_contrato_colaborador colaborador ON empleado.fecha_contrato_nogrupo = colaborador.fecha_contrato\n" +
            "                                                              AND empleado.persona_id = colaborador.persona_id\n" +
            "                                                              AND empleado.centroc_cliente_id = colaborador.centroc_cliente_id\n" +
            "WHERE empleado.nomina_xperiodo_id = :nominaXperiodoId \n" +
            "AND colaborador.ba_tipo_baja_id IS NULL",nativeQuery = true)
    List<NcrEmpleadoXnomina> findByNominaXperiodoIdNominaXperiodoIdAndBaja(Integer nominaXperiodoId);

    @Join(value = "nominaXperiodoId", type = Join.Type.FETCH)
    @Join(value = "personaId", type = Join.Type.FETCH)
    @Join(value = "centrocClienteId", type = Join.Type.FETCH)
    NcrEmpleadoXnomina findByNominaXperiodoIdNominaXperiodoIdAndPersonaIdPersonaId(Integer nominaXperiodoId, Integer personaId);

    long countByPersonaIdPersonaId(Integer personaId);

    long countByNominaXperiodoIdNominaXperiodoIdAndEstadoTimbreId(Integer nominaXperiodoId, Integer estadoTimbreId);

    long countByNominaXperiodoIdNominaXperiodoId(Integer nominaXperiodoId);

    @Query(value = "SELECT nexn.* FROM ncr_empleado_xnomina nexn INNER " +
            "JOIN ncr_nomina_xperiodo nnxp ON nexn.nomina_xperiodo_id = nnxp.nomina_xperiodo_id  " +
            "WHERE nexn.persona_id = :personaId AND nexn.centroc_cliente_id = :clienteId " +
            "AND nnxp.es_activo = true AND nnxp.estado_nomina_id_actual IN (1,2,3) " +
            "ORDER BY nnxp.fecha_fin DESC LIMIT 1 ", nativeQuery = true)
    List<NcrEmpleadoXnomina> findAllNominasEnProceso(Integer personaId, Integer clienteId);

    @Query(value = "SELECT nnxp.* FROM ncr_empleado_xnomina nexn INNER " +
            "JOIN ncr_nomina_xperiodo nnxp ON nexn.nomina_xperiodo_id = nnxp.nomina_xperiodo_id  " +
            "WHERE nexn.persona_id = :personaId AND nexn.centroc_cliente_id = :clienteId " +
            "AND nnxp.es_activo = true AND nnxp.estado_nomina_id_actual NOT IN (5,8) " +
            "ORDER BY nnxp.fecha_fin DESC LIMIT 1 ", nativeQuery = true)
    Optional<NcrNominaXperiodo> findUltimaNominaProcesada(Integer personaId, Integer clienteId);

    @Join(value = "nominaXperiodoId", type = Join.Type.FETCH)
    List<NcrEmpleadoXnomina> findByNominaXperiodoIdAnioFiscalAndPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteIdAndFechaContrato(Integer anioFiscal, Integer personaId, Integer centrocClienteId, Date fechaContrato);

    @Query(value = "SELECT * FROM ncr_empleado_xnomina empleado\n" +
            "INNER JOIN ncr_nomina_xperiodo nomina ON empleado.nomina_xperiodo_id = nomina.nomina_xperiodo_id\n" +
            "WHERE empleado.fecha_contrato_nogrupo = :fechaContrato\n" +
            "AND empleado.persona_id = :personaId\n" +
            "AND nomina.tipo_nomina_id = 1\n" +
            "AND empleado.centroc_cliente_id = :centrocClienteId\n" +
            "AND :fecha BETWEEN nomina.fecha_inicio AND nomina.fecha_fin", nativeQuery = true)
    Optional<NcrEmpleadoXnomina> findByFechaContratoAndPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteIdAndFechaInicio(Date fechaContrato, Integer personaId, Integer centrocClienteId, Date fecha);

    NcrEmpleadoXnomina findByFechaContratoAndPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteIdAndNominaXperiodoIdNominaXperiodoId(Date fechaContrato, Integer personaId, Integer centrocClienteId, Integer nominaXperiodoId);

    @Query(value = "SELECT * FROM ncr_nomina_xperiodo nomina\n" +
            "INNER JOIN ncr_empleado_xnomina empleado ON nomina.nomina_xperiodo_id = empleado.nomina_xperiodo_id\n" +
            "WHERE nomina.fecha_inicio >= :fechaInicio\n" +
            "AND nomina.fecha_fin <= :fechaFin\n" +
            "AND empleado.fecha_contrato_nogrupo = :fechaContrato\n" +
            "AND empleado.persona_id = :personaId\n" +
            "AND empleado.centroc_cliente_id = :centrocClienteId", nativeQuery = true)
    List<NcrEmpleadoXnomina> findByFechaContratoAndPersonaIdAndCentrocClienteIdAndFechaInicioAndFechaFin(Date fechaContrato, Integer personaId, Integer centrocClienteId, Date fechaInicio,Date fechaFin);


    void updateByFechaContratoAndPersonaIdAndCentrocClienteIdAndNominaXperiodoId(
            Date fechaContrato, NcoPersona personaId, NclCentrocCliente centrocClienteId,NcrNominaXperiodo nominaXperiodoId, BigDecimal totalPercepciones,
            BigDecimal totalExento, BigDecimal totalGravable, BigDecimal totalDeducciones, BigDecimal totalNeto,
            BigDecimal provisionIsn, Integer tarifaAplicableSubsidioId, BigDecimal diasAguinaldo, BigDecimal diasLaborados,
            Integer tasaAplicableIsnId, Date fechaAntiguedad);

    void deleteByNominaXperiodoId(NcrNominaXperiodo nominaXperiodoId);

    void updateByNominaXperiodoIdAndPersonaId(NcrNominaXperiodo nominaXperiodoId, NcoPersona personaId, Integer estadoPagoId,@Nullable Integer estadoTimbreId);

    @Join(value = "personaId", type = Join.Type.FETCH)
    @Join(value = "centrocClienteId", type = Join.Type.FETCH)
    List<NcrEmpleadoXnomina> findByFechaContratoAndPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteId(Date fechaContrato, Integer personaId, Integer centrocClienteId);

    Long countByCentrocClienteIdCentrocClienteIdAndNominaXperiodoIdNominaXperiodoIdAndEstadoPagoId(Integer centrocClienteId, Integer nominaXperiodoId,Integer estadoPagoId);

    Long countByCentrocClienteIdCentrocClienteIdAndNominaXperiodoIdNominaXperiodoIdAndEstadoTimbreId(Integer centrocClienteId, Integer nominaXperiodoId,Integer estadoTimbreId);

    Optional<Double> findSumTotalNetoByCentrocClienteIdCentrocClienteIdAndNominaXperiodoIdNominaXperiodoIdAndEstadoPagoId(Integer centrocClienteId, Integer nominaXperiodoId,Integer estadoPagoId);

    @Query(value = "SELECT COUNT(*) \n" +
            "FROM \"ncr_empleado_xnomina\" ncr_empleado_xnomina_ \n" +
            "INNER JOIN \"ncl_centroc_cliente\" ncr_empleado_xnomina_centroc_cliente_id_ ON ncr_empleado_xnomina_.\"centroc_cliente_id\"=ncr_empleado_xnomina_centroc_cliente_id_.\"centroc_cliente_id\" \n" +
            "INNER JOIN \"ncr_nomina_xperiodo\" ncr_empleado_xnomina_nomina_xperiodo_id_ ON ncr_empleado_xnomina_.\"nomina_xperiodo_id\"=ncr_empleado_xnomina_nomina_xperiodo_id_.\"nomina_xperiodo_id\" \n" +
            "WHERE (ncr_empleado_xnomina_centroc_cliente_id_.\"centroc_cliente_id\" = :centrocClienteId\n" +
            "AND ncr_empleado_xnomina_nomina_xperiodo_id_.\"nomina_xperiodo_id\" = :nominaXperiodoId)",nativeQuery = true)
    Long countByConcluirEmpleados(Integer centrocClienteId, Integer nominaXperiodoId);

    @Query(value = "SELECT SUM(ncr_empleado_xnomina_.\"total_neto\") \n" +
            "FROM \"ncr_empleado_xnomina\" ncr_empleado_xnomina_ \n" +
            "INNER JOIN \"ncl_centroc_cliente\" ncr_empleado_xnomina_centroc_cliente_id_ ON ncr_empleado_xnomina_.\"centroc_cliente_id\"=ncr_empleado_xnomina_centroc_cliente_id_.\"centroc_cliente_id\" \n" +
            "INNER JOIN \"ncr_nomina_xperiodo\" ncr_empleado_xnomina_nomina_xperiodo_id_ ON ncr_empleado_xnomina_.\"nomina_xperiodo_id\"=ncr_empleado_xnomina_nomina_xperiodo_id_.\"nomina_xperiodo_id\" \n" +
            "WHERE (ncr_empleado_xnomina_centroc_cliente_id_.\"centroc_cliente_id\" = :centrocClienteId \n" +
            "AND ncr_empleado_xnomina_nomina_xperiodo_id_.\"nomina_xperiodo_id\" = :nominaXperiodoId)",nativeQuery = true)
    Optional<Double> findSumConcluirTotalNeto(Integer centrocClienteId, Integer nominaXperiodoId);

    @Query(value = "SELECT * FROM ncr_empleado_xnomina \n" +
            "WHERE persona_id = :personaId \n" +
            "AND nomina_xperiodo_id = (SELECT max(nomina.nomina_xperiodo_id) \n" +
            "FROM ncr_nomina_xperiodo nomina\n" +
            "INNER JOIN ncr_empleado_xnomina empleado on nomina.nomina_xperiodo_id = empleado.nomina_xperiodo_id \n" +
            "WHERE empleado.persona_id = :personaId \n" +
            "AND nomina.fecha_fin = (SELECT max(nomina.fecha_fin) \n" +
            "FROM ncr_empleado_xnomina empleado\n" +
            "INNER JOIN ncr_nomina_xperiodo nomina on nomina.nomina_xperiodo_id = empleado.nomina_xperiodo_id\n" +
            "WHERE empleado.persona_id = :personaId \n" +
            "AND nomina.es_extraordinaria = false))",nativeQuery = true)
    Optional<NcrEmpleadoXnomina> findByMaxEmpleadoNomina(Integer personaId);

    @Query(value = "SELECT nexn.* FROM ncr_empleado_xnomina nexn INNER \n" +
            "JOIN ncr_nomina_xperiodo nnxp ON nexn.nomina_xperiodo_id = nnxp.nomina_xperiodo_id \n" +
            "WHERE nexn.persona_id = :personaId AND nexn.centroc_cliente_id = :clienteId \n" +
            "AND nnxp.es_activo = true AND nnxp.estado_nomina_id_actual NOT IN (4,5) \n" +
            "ORDER BY nnxp.fecha_fin DESC LIMIT 1 ", nativeQuery = true)
    List<NcrEmpleadoXnomina> findByNominasEnProceso(Integer personaId, Integer clienteId);


    @Query(value = "SELECT nexn.* FROM ncr_empleado_xnomina nexn INNER \n" +
            "JOIN ncr_nomina_xperiodo nnxp ON nexn.nomina_xperiodo_id = nnxp.nomina_xperiodo_id \n" +
            "WHERE nexn.persona_id = :personaId AND nexn.centroc_cliente_id = :clienteId \n" +
            "AND nnxp.es_activo = true AND nnxp.estado_nomina_id_actual NOT IN (4,5) \n" +
            "AND nnxp.tipo_nomina_id = 3 \n" +
            "ORDER BY nnxp.fecha_fin DESC LIMIT 1 ", nativeQuery = true)
    List<NcrEmpleadoXnomina> findByNominasEnProcesoFiniquito(Integer personaId, Integer clienteId);

    boolean existsByFechaContratoAndPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteId(Date FechaContrato, Integer personaId, Integer centrocClienteId);

}