package mx.com.ga.cosmonaut.common.repository.colaborador.servicios;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstadoIncidencia;
import mx.com.ga.cosmonaut.common.entity.colaborador.servicios.NscIncidencia;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NscIncidenciaRepository extends CrudRepository<NscIncidencia, Integer> {

    @Query(value = "SELECT * FROM nsc_incidencia " +
            "WHERE incidencia_id = :incidenciaId " +
            "AND ultimo_estado_incidencia_id = :estadoIncidenciaId ", nativeQuery = true)
    Optional<NscIncidencia> findByIncidenciaIdEstadoId(Integer incidenciaId, Integer estadoIncidenciaId);

    @Query(value = "select * from nsc_incidencia nsc where nsc.cliente_id = :cliente and nsc.persona_id = :persona and fecha_inicio = :fechaInicio and nsc.es_activo=true and nsc.tipo_incidencia_id = :idIncidencia ", nativeQuery = true)
    List<NscIncidencia> findByIncidenciaDuplicado(Integer cliente, Integer persona,Timestamp fechaInicio, Integer idIncidencia);

    @Query(value = "SELECT * FROM nsc_incidencia " +
            "WHERE persona_id = :personaId " +
            "AND fecha_inicio BETWEEN :fechaInicio AND :fechaFin ", nativeQuery = true)
    List<NscIncidencia> findByPersonaIdAndFechaInicio(Integer personaId, Timestamp fechaInicio, Timestamp fechaFin);

    @Query(value = "SELECT * FROM nsc_incidencia " +
            "WHERE cliente_id = :clienteId " +
            "AND fecha_inicio BETWEEN :fechaInicio AND :fechaFin ", nativeQuery = true)
    List<NscIncidencia> findByClienteIdAndFechaInicio(Integer clienteId, Timestamp fechaInicio, Timestamp fechaFin);

    @Query(value = "SELECT * FROM nsc_incidencia " +
            "WHERE cliente_id = :clienteId and persona_id = :pesona and tipo_incidencia_id = :idIncidencia " +
            "AND fecha_inicio BETWEEN :fechaInicio AND :fechaFin ", nativeQuery = true)
    List<NscIncidencia> findByClienteIdAndFechaInicioincIdencia(Integer clienteId , Integer pesona, Integer idIncidencia, Timestamp fechaInicio, Timestamp fechaFin);

    void update(@Id Integer id, CatEstadoIncidencia ultimoEstadoIncidenciaId);

    @Query(value = "UPDATE nsc_incidencia SET ultimo_estado_incidencia_id = 2\n" +
            "WHERE fecha_aplicacion BETWEEN :fechaInicio AND :fechaFin AND ultimo_estado_incidencia_id in (4,5)", nativeQuery = true)
    void updateUltimoEstadoIncidenciaId(Date fechaInicio, Date fechaFin);

    @Query(value = "UPDATE nsc_incidencia SET ultimo_estado_incidencia_id = 2\n" +
            "WHERE persona_id = :personaId \n" +
            "AND fecha_aplicacion BETWEEN :fechaInicio AND :fechaFin \n" +
            "AND ultimo_estado_incidencia_id in (4,5)", nativeQuery = true)
    void updateUltimoEstadoIncidenciaIdPersonaId(Integer personaId, Date fechaInicio, Date fechaFin);

    void update(@Id Integer id, boolean esActivo);

    @Query(value = "SELECT * FROM nsc_incidencia " +
            "WHERE persona_id = :personaId AND tipo_incidencia_id IN (:incidenciasId) AND es_activo = :esActivo  ", nativeQuery = true)
    List<NscIncidencia> findByPersonaIdDetalleEvento(Long personaId, List<Integer> incidenciasId,boolean esActivo);

    @Query(value = "SELECT * FROM nsc_incidencia WHERE persona_id = :personaId AND tipo_incidencia_id IN (:incidenciasId) AND fecha_aplicacion BETWEEN :fechaInicio AND :fechaFin AND es_activo = :esActivo ", nativeQuery = true)
    List<NscIncidencia> findByPersonaIdAndInicenciasIdAndFecha(Integer personaId, List<Integer> incidenciasId, Date fechaInicio, Date fechaFin, boolean esActivo);

    @Query(value = "SELECT * FROM nsc_incidencia WHERE persona_id = :personaId AND tipo_incidencia_id = :incidenciasId AND fecha_aplicacion BETWEEN :fechaInicio AND :fechaFin AND ultimo_estado_incidencia_id = 2 AND es_activo = :esActivo", nativeQuery = true)
    List<NscIncidencia> findByPersonaIdAndInicenciasIdAndFechaAndEsActivo(Integer personaId, Integer incidenciasId, Date fechaInicio, Date fechaFin, boolean esActivo);

    @Query(value = "SELECT * FROM nsc_incidencia WHERE persona_id = :personaId AND fecha_aplicacion BETWEEN :fechaInicio AND :fechaFin AND ultimo_estado_incidencia_id = 2 AND es_activo = :esActivo", nativeQuery = true)
    List<NscIncidencia> findByPersonaIdAndFechaAndEsActivo(Integer personaId, Date fechaInicio, Date fechaFin, boolean esActivo);

    @Query("SELECT sum(incidencia.duracion)\n" +
            "FROM nsc_incidencia incidencia\n" +
            "WHERE incidencia.tipo_incidencia_id = :tipoIncidenciaId\n" +
            "AND incidencia.ultimo_estado_incidencia_id = :estadoIncidenciaId\n" +
            "AND incidencia.fecha_inicio BETWEEN :fechaInicio AND :fechaFin\n" +
            "AND incidencia.persona_id = :personaId and incidencia.es_activo=true ")
    Optional<Long> sumDuracionIncidencia(Integer tipoIncidenciaId, Integer estadoIncidenciaId, Date fechaInicio, Date fechaFin, Integer personaId);

    @Query("SELECT sum(incidencia.duracion)\n" +
            "FROM nsc_incidencia incidencia\n" +
            "WHERE incidencia.tipo_incidencia_id = :tipoIncidenciaId\n" +
            "AND incidencia.fecha_inicio BETWEEN :fechaInicio AND :fechaFin\n" +
            "AND incidencia.persona_id = :personaId and incidencia.es_activo=true ")
    Optional<Long> sumDuracionIncidenciaUltimoEstado(Integer tipoIncidenciaId, Date fechaInicio, Date fechaFin, Integer personaId);

    @Query(value = "SELECT * FROM nsc_incidencia " +
            "WHERE persona_id = :personaId " +
            "AND fecha_inicio BETWEEN :fechaInicio AND :fechaFin AND es_activo = :esActivo ", nativeQuery = true)
    List<NscIncidencia> findByPersonaIdAndFechaInicioAndEsActivo(Integer personaId, Timestamp fechaInicio, Timestamp fechaFin, boolean esActivo);

}