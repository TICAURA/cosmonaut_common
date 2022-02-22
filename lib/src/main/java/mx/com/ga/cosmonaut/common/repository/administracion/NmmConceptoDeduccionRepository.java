package mx.com.ga.cosmonaut.common.repository.administracion;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.administracion.NmmConceptoDeduccion;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NmmConceptoDeduccionRepository extends CrudRepository<NmmConceptoDeduccion, Integer> {
    
    List<NmmConceptoDeduccion> findByNombreAndCentrocClienteIdCentrocClienteId(String nombre, Integer clienteId);
    
    List<NmmConceptoDeduccion> findByTipoDeduccionIdTipoDeduccionIdAndCentrocClienteIdCentrocClienteId(String tipoDeduccionId, Integer clienteId);
    
    @Join(value = "tipoDeduccionId" , alias = "td")
    List<NmmConceptoDeduccion> findByCentrocClienteIdCentrocClienteId(Integer clienteId);


    @Query(value = "SELECT nmm_concepto_deduccion_.concepto_deduccion_id,nmm_concepto_deduccion_.tipo_deduccion_id,nmm_concepto_deduccion_.nombre," +
            " nmm_concepto_deduccion_.cuenta_contable, nmm_concepto_deduccion_.es_activo,nmm_concepto_deduccion_.centroc_cliente_id," +
            "nmm_concepto_deduccion_.especializacion,td.descripcion AS tddescripcion,td.fecha_inicio AS tdfecha_inicio,td.es_activo " +
            " AS tdes_activo,td.fecha_fin AS tdfecha_fin,td.es_por_defecto AS tdes_por_defecto,td.no_editable " +
            "AS tdno_editable,td.base_calculo_id AS tdbase_calculo_id,td.valor AS tdvalor, td.especializacion AS tdespecializacion " +
            "FROM nmm_concepto_deduccion nmm_concepto_deduccion_ INNER JOIN cs_tipo_deduccion td ON " +
            " nmm_concepto_deduccion_.especializacion=td.especializacion INNER JOIN ncl_centroc_cliente nmm_concepto_deduccion_centroc_cliente_id_ " +
            "ON nmm_concepto_deduccion_.centroc_cliente_id=nmm_concepto_deduccion_centroc_cliente_id_.centroc_cliente_id " +
            "WHERE nmm_concepto_deduccion_.centroc_cliente_id = :clienteId and nmm_concepto_deduccion_.es_activo = true", nativeQuery=true)
    @Join(value = "tipoDeduccionId" , alias = "td")
    List<NmmConceptoDeduccion> findByCentrocClienteIdCentrocClienteIdEspecialidad(Integer clienteId);


    @Join(value = "tipoDeduccionId" , alias = "td")
    List<NmmConceptoDeduccion> findByCentrocClienteIdCentrocClienteIdAndEsActivo(Integer clienteId, boolean esActivo);
    
    void update(@Id Integer conceptoDeduccionId, boolean esActivo);

    NmmConceptoDeduccion findByTipoDeduccionIdTipoDeduccionIdAndEspecializacionAndCentrocClienteIdCentrocClienteId(String tipoDeduccionId, String especializacion, Integer centrocClienteId);

}