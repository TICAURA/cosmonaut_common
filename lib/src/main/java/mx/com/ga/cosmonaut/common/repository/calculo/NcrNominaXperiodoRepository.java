package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrNominaXperiodo;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrNominaXperiodoRepository extends CrudRepository<NcrNominaXperiodo, Integer> {

    @Query(value = "SELECT * FROM ncr_nomina_xperiodo " +
            "WHERE fecha_inicio BETWEEN :fechaInicio AND :fechaFin " +
            "AND estado_nomina_id_actual IN (:listaEstadoNominaIdActual)", nativeQuery = true)
    List<NcrNominaXperiodo> findByFechaInicioAndFechaFinAndEstadoNominaIdActual(Date fechaInicio, Date fechaFin, List<Integer> listaEstadoNominaIdActual);

    @Query(value = "SELECT * FROM ncr_nomina_xperiodo periodo \n" +
            "INNER JOIN ncr_empleado_xnomina empleado ON empleado.nomina_xperiodo_id = periodo.nomina_xperiodo_id \n" +
            "WHERE periodo.fecha_inicio BETWEEN :fechaInicio AND :fechaFin \n" +
            "AND empleado.persona_id = :personaId \n" +
            "AND periodo.estado_nomina_id_actual IN (:listaEstadoNominaIdActual) \n" , nativeQuery = true)
    List<NcrNominaXperiodo> findByFechaInicioAndFechaFinAndEstadoNominaIdActualAndPersonaId(Date fechaInicio, Date fechaFin, List<Integer> listaEstadoNominaIdActual, Integer personaId);


    @Query(value = "SELECT * FROM ncr_nomina_xperiodo " +
            "WHERE fecha_inicio BETWEEN :fechaInicio AND :fechaFin ", nativeQuery = true)
    List<NcrNominaXperiodo> findByFechaInicioAndFechaFin(Date fechaInicio, Date fechaFin);

    @Query(value = "SELECT * FROM ncr_nomina_xperiodo " +
            "WHERE tipo_nomina_id = 1 AND es_activo = true AND grupo_nomina_id = :grupoNominaId " +
            "AND estado_nomina_id_actual IN (3,4,5) " +
            "ORDER BY fecha_fin DESC LIMIT 1 ", nativeQuery = true)
    Optional<NcrNominaXperiodo> findUltimaNominaProcesada(Integer grupoNominaId);

    @Query(value = "SELECT * FROM ncr_nomina_xperiodo " +
            "WHERE es_activo = true AND grupo_nomina_id = :grupoNominaId " +
            "AND estado_nomina_id_actual IN (1,2,3) " +
            "ORDER BY fecha_fin DESC LIMIT 1 ", nativeQuery = true)
    Set<NcrNominaXperiodo> findAllNominasEnProceso(Integer grupoNominaId);

    @Query(value = "SELECT * FROM ncr_nomina_xperiodo " +
            "WHERE es_activo = true AND grupo_nomina_id = :grupoNominaId AND centroc_cliente_id = :centrocClienteId " +
            "AND fecha_inicio = :fechaInicio :: date ", nativeQuery = true)
    Set<NcrNominaXperiodo> findByFechaInicioAndGrupoNominaId(String fechaInicio, Integer grupoNominaId, Integer centrocClienteId);

    boolean existsByNominaXperiodoIdAndEstadoNominaIdActualInList(Integer nominaXperiodoId, List<Integer> estadoNominaIdActual);

    long countByCentrocClienteId(Integer centrocClienteId);

    @Query(value = "SELECT coalesce(count(1),0) + 1 FROM ncr_nomina_xperiodo nomina, cat_tipo_nomina tipo_nomina " +
            "WHERE nomina.tipo_nomina_id = tipo_nomina.tipo_nomina_id " +
            "AND nomina.centroc_cliente_id = :centrocClienteId " +
            "AND nomina.anio_fiscal = :anioFiscal " +
            "AND nomina.tipo_nomina_id = tipo_nomina.tipo_nomina_id " +
            "AND tipo_nomina.tipo_nomina_id = :tipoNominaId ",nativeQuery = true)
    long obtenNumeroClave(Integer centrocClienteId, Integer tipoNominaId, Integer anioFiscal);

    @Query(value = "SELECT COUNT(*) FROM (\n" +
            "\tSELECT nomina.nomina_xperiodo_id\n" +
            "\tFROM ncr_nomina_xperiodo nomina\n" +
            "\tWHERE nomina.grupo_nomina_id = :grupoNominaId\n" +
            "\tAND nomina.fecha_inicio BETWEEN :fechaInicio AND :fechaFin\n" +
            "\tUNION\n" +
            "\tSELECT nomina.nomina_xperiodo_id\n" +
            "\tFROM ncr_nomina_xperiodo nomina\n" +
            "\tWHERE nomina.grupo_nomina_id = :grupoNominaId\n" +
            "\tAND nomina.fecha_fin BETWEEN :fechaInicio AND :fechaFin\n" +
            ") AS X",nativeQuery = true)
    Optional<Long> countNominaGrupoPeriodo(Integer grupoNominaId, Date fechaInicio, Date fechaFin);

    @Query(value = "SELECT COUNT(*) FROM (\n" +
            "\tSELECT nomina.nomina_xperiodo_id\n" +
            "\tFROM ncr_nomina_xperiodo nomina\n" +
            "\tWHERE nomina.grupo_nomina_id = :grupoNominaId\n" +
            "\tAND nomina.tipo_nomina_id = :tipoNomina\n" +
            "\tAND nomina.fecha_inicio BETWEEN :fechaInicio AND :fechaFin\n" +
            "\tUNION\n" +
            "\tSELECT nomina.nomina_xperiodo_id\n" +
            "\tFROM ncr_nomina_xperiodo nomina\n" +
            "\tWHERE nomina.grupo_nomina_id = :grupoNominaId\n" +
            "\tAND nomina.tipo_nomina_id = :tipoNomina\n" +
            "\tAND nomina.fecha_fin BETWEEN :fechaInicio AND :fechaFin\n" +
            ") AS X",nativeQuery = true)
    Optional<Long> countNominaGrupoPeriodo(Integer grupoNominaId,Integer tipoNomina, Date fechaInicio, Date fechaFin);

    void updateByNominaXperiodoId(Integer nominaXperiodoId, Integer estadoNominaIdActual);

    boolean existsByCentrocClienteIdAndAnioFiscalAndTipoNominaId(Integer centrocClienteId, Integer anioFiscal,Integer tipoNominaId);

    @Join(value = "grupoNominaId", alias = "grupoNominaId")
    Optional<NcrNominaXperiodo> findByNominaXperiodoId(Integer nominaXperiodoId);


    @Query(value = "SELECT COUNT(*) FROM ncr_nomina_xperiodo periodo \n" +
            "INNER JOIN ncr_empleado_xnomina empleado ON empleado.nomina_xperiodo_id = periodo.nomina_xperiodo_id \n" +
            "WHERE :fechaInicio BETWEEN periodo.fecha_inicio AND periodo.fecha_fin \n" +
            "AND empleado.persona_id = :personaId\n" +
            "AND periodo.estado_nomina_id_actual <> 1 \n" +
            "AND periodo.estado_nomina_id_actual <> 2" , nativeQuery = true)
    Long countFechaInicioAndFechaFinAndEstadoNominaIdActualAndPersonaId(Date fechaInicio, Integer personaId);

    @Query(value = "SELECT COUNT(*)\n" +
            "FROM ncr_empleado_xnomina empleado\n" +
            "INNER JOIN ncr_nomina_xperiodo nomina on empleado.nomina_xperiodo_id = nomina.nomina_xperiodo_id \n" +
            "WHERE empleado.fecha_contrato_nogrupo = :fechaContrato\n" +
            "AND empleado.persona_id = :personaId\n" +
            "AND empleado.centroc_cliente_id = :centrocClienteId\n" +
            "AND nomina.fecha_inicio = :fechaInicio\n" +
            "AND nomina.fecha_fin = :fechaFin", nativeQuery = true)
    Long countFechaContratoAndPersonaIdAndCentroClienteIdAndFechaInicioAndFechaFin(Date fechaContrato,Integer personaId,Integer centrocClienteId, Date fechaInicio, Date fechaFin);

}