package mx.com.ga.cosmonaut.common.repository.administracion;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.administracion.NmmConceptoDeduccion;
import mx.com.ga.cosmonaut.common.entity.administracion.NmmConceptoPercepcion;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NmmConceptoPercepcionRepository extends CrudRepository<NmmConceptoPercepcion, Integer> {
    
    
    List<NmmConceptoPercepcion> findByNombreAndCentrocClienteIdCentrocClienteId(String nombre, Integer clienteId);
    
    List<NmmConceptoPercepcion> findByTipoPercepcionIdTipoPercepcionIdAndCentrocClienteIdCentrocClienteId(String tipoPercepcionId, Integer clienteId);
    
    @Join(value = "tipoPercepcionId" , alias = "tp")
    List<NmmConceptoPercepcion> findByCentrocClienteIdCentrocClienteId(Integer clienteId);
    
    void update(@Id Integer conceptoPercepcionId, boolean esActivo);

    @Join(value = "tipoPercepcionId" , alias = "tp")
    @Join(value = "centrocClienteId", alias = "cl")
    List<NmmConceptoPercepcion> findByCentrocClienteIdCentrocClienteIdAndTipoPeriodicidad(Integer centrocClienteId, String tipoPeriodicidad);

    @Join(value = "tipoPercepcionId" , alias = "tp")
    @Join(value = "centrocClienteId", alias = "cl")
    List<NmmConceptoPercepcion> findByCentrocClienteIdCentrocClienteIdAndTipoPeriodicidadAndEsActivo(Integer centrocClienteId, String tipoPeriodicidad, boolean esActivo);

    @Query(value = "SELECT ncp.*, tp.tipo_percepcion_id AS tptipo_percepcion_id, tp.descripcion AS tpdescripcion, " +
            "tp.fecha_inicio AS tpfecha_inicio, tp.es_activo AS tpes_activo, tp.fecha_fin AS tpfecha_fin, " +
            "tp.tipo_concepto AS tptipo_concepto, tp.integra_sdi AS tpintegra_sdi, tp.tope_integra_sdi AS tptope_integra_sdi, " +
            "tp.tipo_periodicidad AS tptipo_periodicidad, tp.es_por_defecto AS tpes_por_defecto, tp.integra_isr AS tpintegra_isr, " +
            "tp.tope_integra_isr AS tptope_integra_isr, tp.integra_isn AS tpintegra_isn, tp.tipo_valor_referencia_id AS tptipo_valor_referencia_id, " +
            "tp.c_tipo_otro_pago AS tpc_tipo_otro_pago, tp.tipo_pago AS tptipo_pago, tp.periodicidad_pago_id_xisr AS tpperiodicidad_pago_id_xisr, " +
            "tp.tipo_calculo_tope AS tptipo_calculo_tope, tp.no_editable AS tpno_editable, tp.base_calculo_id AS tpbase_calculo_id, " +
            "tp.valor AS tpvalor, tp.especializacion AS tpespecializacion, tp.aplica_prevision AS tpaplica_prevision, tp.es_configurablex AS tpes_configurablex " +
            "FROM nmm_concepto_percepcion ncp INNER JOIN cs_tipo_percepcion tp " +
            "ON ncp.tipo_percepcion_id = tp.tipo_percepcion_id AND ncp.especializacion = tp.especializacion " +
            "WHERE centroc_cliente_id = :clienteId ORDER BY ncp.concepto_percepcion_id ASC "
            , nativeQuery = true)
    @Join(value = "tipoPercepcionId", alias = "tp")
    List<NmmConceptoPercepcion> findByCentrocClienteIdCentrocClienteIdJoinByIdAndEspecializacion(Integer clienteId);

    NmmConceptoPercepcion findByTipoPercepcionIdTipoPercepcionIdAndEspecializacionAndCentrocClienteIdCentrocClienteId(String tipoDeduccionId, String especializacion, Integer centrocClienteId);


}