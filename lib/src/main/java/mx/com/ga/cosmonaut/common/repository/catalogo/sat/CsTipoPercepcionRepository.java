package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoPercepcion;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsTipoPercepcionRepository extends CrudRepository<CsTipoPercepcion, String> {

    @Query(value = "SELECT tipo_percepcion_id, " +
            "concat(tipo_percepcion_id , '-', descripcion) as descripcion , " +
            "fecha_inicio, es_activo, fecha_fin, tipo_concepto, integra_sdi, integra_isn, " +
            "tope_integra_sdi, tipo_periodicidad, es_por_defecto, integra_isr, tope_integra_isr, " +
            "tipo_valor_referencia_id, c_tipo_otro_pago, tipo_pago, periodicidad_pago_id_xisr, tipo_calculo_tope, " +
            "no_editable, base_calculo_id, valor, especializacion, aplica_prevision, es_configurablex  " +
            "FROM cs_tipo_percepcion " +
            "where es_activo = :activo " +
            "order by tipo_percepcion_id ", nativeQuery = true)
    List<CsTipoPercepcion> findByEsActivo(Boolean activo);

    @Query(value = "SELECT * FROM cs_tipo_percepcion " +
            "where es_activo = :activo " +
            "order by tipo_percepcion_id ", nativeQuery = true)
    List<CsTipoPercepcion> findByEsActivoNoConcat(Boolean activo);

    @Query(value = "SELECT tipo_percepcion_id, " +
            "concat(tipo_percepcion_id , '-', descripcion) as descripcion , " +
            "fecha_inicio, es_activo, fecha_fin, tipo_concepto, integra_sdi, integra_isn, " +
            "tope_integra_sdi, tipo_periodicidad, es_por_defecto, integra_isr, tope_integra_isr, " +
            "tipo_valor_referencia_id, c_tipo_otro_pago, tipo_pago, periodicidad_pago_id_xisr, tipo_calculo_tope, " +
            "no_editable, base_calculo_id, valor, especializacion, aplica_prevision, es_configurablex " +
            "FROM cs_tipo_percepcion " +
            "where es_activo = :activo and tipo_periodicidad = :tipoPeriodicidad order by tipo_percepcion_id", nativeQuery = true)
    List<CsTipoPercepcion> obtieneTipoPercepcionEmpleado(Boolean activo, String tipoPeriodicidad);

    @Query(value = "SELECT * FROM cs_tipo_percepcion " +
            "where es_activo = :activo and tipo_periodicidad = :tipoPeriodicidad order by tipo_percepcion_id", nativeQuery = true)
    List<CsTipoPercepcion> obtieneTipoPercepcionEmpleadoNoConcat(Boolean activo, String tipoPeriodicidad);

    List<CsTipoPercepcion> findByPorDefectoAndEsActivo(Boolean porDefecto, Boolean esActivo);

    List<CsTipoPercepcion> findByPorDefecto(Boolean porDefecto);

    @Query(value = "SELECT * FROM cs_tipo_percepcion " +
            "where upper(descripcion) = :descripcion or tipo_percepcion_id = :id", nativeQuery = true)
    List<CsTipoPercepcion> findByDescripcionAndIdRepetido(String descripcion,String id);

    List<CsTipoPercepcion> findByDescripcion(String descripcion);
    Optional<CsTipoPercepcion> findByTipoPercepcionIdAndEspecializacion(String tipoPercepcionId, String especializacion);

    @Query(value = "update  cs_tipo_percepcion set descripcion = :descripcion,fecha_inicio = :fechaInicio,es_activo=:esActivo,tipo_concepto=:tipoConcepto,integra_sdi=:integraSdi,tipo_periodicidad=:tipoPeriocidad,integra_isr=:integraIsr,integra_isn=:integraIsn,tipo_pago=:tipoPago,es_por_defecto=:porDefecto where tipo_percepcion_id = :idTipoPercepcion and especializacion  = :especializacion", nativeQuery = true)
     CsTipoPercepcion updateByEspecializacion(String descripcion, Date fechaInicio, Boolean esActivo,String tipoConcepto,String integraSdi,String tipoPeriocidad,String integraIsr,String integraIsn,String tipoPago,Boolean porDefecto,String idTipoPercepcion,String especializacion);

    List<CsTipoPercepcion> findByDescripcionIlike(String descripcion);

}
