package mx.com.ga.cosmonaut.common.repository.administracion;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.administracion.NmmConfiguraPercepcion;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.Date;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NmmConfiguraPercepcionRepository extends CrudRepository<NmmConfiguraPercepcion, Integer> {
    
    List<NmmConfiguraPercepcion> findByConceptoPercepcionIdConceptoPercepcionId(Integer conceptoPercepcionId);
        
    List<NmmConfiguraPercepcion> findByConceptoPercepcionIdConceptoPercepcionIdAndPersonaIdPersonaId(Integer conceptoPercepcionId, Integer personaId);
    
    List<NmmConfiguraPercepcion> findByTipoPercepcionIdTipoPercepcionIdAndPersonaIdPersonaId(String tipoPercepcionId, Integer personaId);

    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoPercepcionId", alias = "cs")
    @Join(value = "conceptoPercepcionId", alias = "nmmp")
    @Join(value = "personaId", alias = "ncp")
    List<NmmConfiguraPercepcion> findByPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteId(Integer personaId, Integer clienteId);

    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoPercepcionId", alias = "cs")
    @Join(value = "conceptoPercepcionId", alias = "nmmp")
    @Join(value = "personaId", alias = "ncp")
    List<NmmConfiguraPercepcion> findByPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteIdAndEsActivo(Integer personaId, Integer clienteId, boolean esActivo);

    @Query(value = "SELECT nmm_configura_percepcion_.\"configura_percepcion_id\",nmm_configura_percepcion_.\"tipo_percepcion_id\",nmm_configura_percepcion_.\"concepto_percepcion_id\",nmm_configura_percepcion_.\"persona_id\",nmm_configura_percepcion_.\"politica_id\",nmm_configura_percepcion_.\"fecha_contrato\",nmm_configura_percepcion_.\"centroc_cliente_id\",nmm_configura_percepcion_.\"valor\",nmm_configura_percepcion_.\"es_activo\",nmm_configura_percepcion_.\"base_calculo_id\",nmm_configura_percepcion_.\"pp_referencia\",nmm_configura_percepcion_.\"pp_fecha_inicio\",nmm_configura_percepcion_.\"pp_monto_total\",nmm_configura_percepcion_.\"pp_numero_periodos\",nmm_configura_percepcion_.\"tope_integracion_sdi\",nmm_configura_percepcion_.\"pp_monto_xperiodo\",nmm_configura_percepcion_.\"especializacion\",cs.\"descripcion\" AS csdescripcion,cs.\"fecha_inicio\" AS csfecha_inicio,cs.\"es_activo\" AS cses_activo,cs.\"fecha_fin\" AS csfecha_fin,cs.\"tipo_concepto\" AS cstipo_concepto,cs.\"integra_sdi\" AS csintegra_sdi,cs.\"tope_integra_sdi\" AS cstope_integra_sdi,cs.\"tipo_periodicidad\" AS cstipo_periodicidad,cs.\"es_por_defecto\" AS cses_por_defecto,cs.\"integra_isr\" AS csintegra_isr,cs.\"tope_integra_isr\" AS cstope_integra_isr,cs.\"integra_isn\" AS csintegra_isn,cs.\"tipo_valor_referencia_id\" AS cstipo_valor_referencia_id,cs.\"c_tipo_otro_pago\" AS csc_tipo_otro_pago,cs.\"tipo_pago\" AS cstipo_pago,cs.\"periodicidad_pago_id_xisr\" AS csperiodicidad_pago_id_xisr,cs.\"tipo_calculo_tope\" AS cstipo_calculo_tope,cs.\"no_editable\" AS csno_editable,cs.\"base_calculo_id\" AS csbase_calculo_id,cs.\"valor\" AS csvalor,cs.\"especializacion\" AS csespecializacion,cs.\"aplica_prevision\" AS csaplica_prevision,cs.\"es_configurablex\" AS cses_configurablex,nclc.\"curp\" AS nclccurp,nclc.\"rfc\" AS nclcrfc,nclc.\"razon_social\" AS nclcrazon_social,nclc.\"nombre\" AS nclcnombre,nclc.\"certificado_sello_digital_id\" AS nclccertificado_sello_digital_id,nclc.\"cc_url_logo\" AS nclccc_url_logo,nclc.\"fecha_constitucion\" AS nclcfecha_constitucion,nclc.\"fecha_alta\" AS nclcfecha_alta,nclc.\"es_activo\" AS nclces_activo,nclc.\"multiempresa\" AS nclcmultiempresa,nclc.\"em_c_regimenfiscal\" AS nclcem_c_regimenfiscal,nclc.\"base_periodo_id\" AS nclcbase_periodo_id,nclc.\"c_actividad_economica\" AS nclcc_actividad_economica,nclc.\"centro_costos_centroc_cliente_id\" AS nclccentro_costos_centroc_cliente_id,ncp.\"iba_num_hijos\" AS ncpiba_num_hijos,ncp.\"ce_telefono\" AS ncpce_telefono,ncp.\"ci_telefono\" AS ncpci_telefono,ncp.\"ci_extension\" AS ncpci_extension,ncp.\"celular\" AS ncpcelular,ncp.\"curp\" AS ncpcurp,ncp.\"rfc\" AS ncprfc,ncp.\"nombre\" AS ncpnombre,ncp.\"apellido_pat\" AS ncpapellido_pat,ncp.\"apellido_mat\" AS ncpapellido_mat,ncp.\"email_corp\" AS ncpemail_corp,ncp.\"genero\" AS ncpgenero,ncp.\"nss\" AS ncpnss,ncp.\"ce_nombre\" AS ncpce_nombre,ncp.\"ce_e_mail\" AS ncpce_e_mail,ncp.\"rl_url_firma\" AS ncprl_url_firma,ncp.\"ci_email_personal\" AS ncpci_email_personal,ncp.\"ce_apellido_paterno\" AS ncpce_apellido_paterno,ncp.\"ce_apellido_materno\" AS ncpce_apellido_materno,ncp.\"iba_estado_civil\" AS ncpiba_estado_civil,ncp.\"url_linkedin\" AS ncpurl_linkedin,ncp.\"poder_notarial\" AS ncppoder_notarial,ncp.\"ci_puesto\" AS ncpci_puesto,ncp.\"url_imagen\" AS ncpurl_imagen,ncp.\"fecha_nace\" AS ncpfecha_nace,ncp.\"fecha_alta\" AS ncpfecha_alta,ncp.\"es_activo\" AS ncpes_activo,ncp.\"tiene_curp\" AS ncptiene_curp,ncp.\"tiene_rfc\" AS ncptiene_rfc,ncp.\"tiene_nss\" AS ncptiene_nss,ncp.\"iba_tiene_hijos\" AS ncpiba_tiene_hijos,ncp.\"invitar_empleado\" AS ncpinvitar_empleado,ncp.\"iba_nacionalidad_id\" AS ncpiba_nacionalidad_id,ncp.\"tipo_persona_id\" AS ncptipo_persona_id,ncp.\"tipo_representante_id\" AS ncptipo_representante_id,ncp.\"representante_legal_centroc_cliente_id\" AS ncprepresentante_legal_centroc_cliente_id,ncp.\"ce_parentesco_id\" AS ncpce_parentesco_id,ncp.\"facultad_poder_id\" AS ncpfacultad_poder_id,nmmp.\"tipo_percepcion_id\" AS nmmptipo_percepcion_id,nmmp.\"nombre\" AS nmmpnombre,nmmp.\"tipo_periodicidad\" AS nmmptipo_periodicidad,nmmp.\"cuenta_contable\" AS nmmpcuenta_contable,nmmp.\"tipo_concepto\" AS nmmptipo_concepto,nmmp.\"grava_isn\" AS nmmpgrava_isn,nmmp.\"grava_isr\" AS nmmpgrava_isr,nmmp.\"integra_imss\" AS nmmpintegra_imss,nmmp.\"es_activo\" AS nmmpes_activo,nmmp.\"centroc_cliente_id\" AS nmmpcentroc_cliente_id,nmmp.\"c_tipo_otro_pago\" AS nmmpc_tipo_otro_pago,nmmp.\"tipo_pago\" AS nmmptipo_pago,nmmp.\"periodicidad_pago_id\" AS nmmpperiodicidad_pago_id,nmmp.\"especializacion\" AS nmmpespecializacion\n" +
            "FROM \"nmm_configura_percepcion\" nmm_configura_percepcion_\n" +
            "    INNER JOIN \"cs_tipo_percepcion\" cs ON nmm_configura_percepcion_.\"tipo_percepcion_id\"=cs.\"tipo_percepcion_id\"\n" +
            "    INNER JOIN \"ncl_centroc_cliente\" nclc ON nmm_configura_percepcion_.\"centroc_cliente_id\"=nclc.\"centroc_cliente_id\"\n" +
            "    INNER JOIN \"nco_persona\" ncp ON nmm_configura_percepcion_.\"persona_id\"=ncp.\"persona_id\"\n" +
            "    INNER JOIN \"nmm_concepto_percepcion\" nmmp ON nmm_configura_percepcion_.\"concepto_percepcion_id\"=nmmp.\"concepto_percepcion_id\"\n" +
            "WHERE ncp.\"persona_id\" = :personaId\n" +
            "AND nclc.\"centroc_cliente_id\" = :clienteId\n" +
            "AND nmm_configura_percepcion_.\"es_activo\" = :esActivo\n" +
            "AND :fechaInicio BETWEEN nmm_configura_percepcion_.pp_fecha_inicio AND :fechaFin",nativeQuery = true)
    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoPercepcionId", alias = "cs")
    @Join(value = "conceptoPercepcionId", alias = "nmmp")
    @Join(value = "personaId", alias = "ncp")
    List<NmmConfiguraPercepcion> findByPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteIdAndEsActivoAndFechaInicioBetween(Integer personaId, Integer clienteId, boolean esActivo, Date fechaInicio, Date fechaFin);

    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoPercepcionId", alias = "cs")
    @Join(value = "conceptoPercepcionId", alias = "nmmp")
    @Join(value = "politicaId", alias = "nclp")
    List<NmmConfiguraPercepcion> findByPoliticaIdPoliticaIdAndCentrocClienteIdCentrocClienteId(Integer politicaId, Integer clienteId);

    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoPercepcionId", alias = "cs")
    @Join(value = "conceptoPercepcionId", alias = "nmmp")
    @Join(value = "politicaId", alias = "nclp")
    List<NmmConfiguraPercepcion> findByPoliticaIdPoliticaIdAndCentrocClienteIdCentrocClienteIdAndEsActivo(Integer politicaId, Integer clienteId, boolean esActivo);

    @Query(value = "SELECT nmm_configura_percepcion_.\"configura_percepcion_id\",nmm_configura_percepcion_.\"tipo_percepcion_id\",nmm_configura_percepcion_.\"concepto_percepcion_id\",nmm_configura_percepcion_.\"persona_id\",nmm_configura_percepcion_.\"politica_id\",nmm_configura_percepcion_.\"fecha_contrato\",nmm_configura_percepcion_.\"centroc_cliente_id\",nmm_configura_percepcion_.\"valor\",nmm_configura_percepcion_.\"es_activo\",nmm_configura_percepcion_.\"base_calculo_id\",nmm_configura_percepcion_.\"pp_referencia\",nmm_configura_percepcion_.\"pp_fecha_inicio\",nmm_configura_percepcion_.\"pp_monto_total\",nmm_configura_percepcion_.\"pp_numero_periodos\",nmm_configura_percepcion_.\"tope_integracion_sdi\",nmm_configura_percepcion_.\"pp_monto_xperiodo\",nmm_configura_percepcion_.\"especializacion\",cs.\"descripcion\" AS csdescripcion,cs.\"fecha_inicio\" AS csfecha_inicio,cs.\"es_activo\" AS cses_activo,cs.\"fecha_fin\" AS csfecha_fin,cs.\"tipo_concepto\" AS cstipo_concepto,cs.\"integra_sdi\" AS csintegra_sdi,cs.\"tope_integra_sdi\" AS cstope_integra_sdi,cs.\"tipo_periodicidad\" AS cstipo_periodicidad,cs.\"es_por_defecto\" AS cses_por_defecto,cs.\"integra_isr\" AS csintegra_isr,cs.\"tope_integra_isr\" AS cstope_integra_isr,cs.\"integra_isn\" AS csintegra_isn,cs.\"tipo_valor_referencia_id\" AS cstipo_valor_referencia_id,cs.\"c_tipo_otro_pago\" AS csc_tipo_otro_pago,cs.\"tipo_pago\" AS cstipo_pago,cs.\"periodicidad_pago_id_xisr\" AS csperiodicidad_pago_id_xisr,cs.\"tipo_calculo_tope\" AS cstipo_calculo_tope,cs.\"no_editable\" AS csno_editable,cs.\"base_calculo_id\" AS csbase_calculo_id,cs.\"valor\" AS csvalor,cs.\"especializacion\" AS csespecializacion,cs.\"aplica_prevision\" AS csaplica_prevision,cs.\"es_configurablex\" AS cses_configurablex,nclp.\"nombre\" AS nclpnombre,nclp.\"nombre_corto\" AS nclpnombre_corto,nclp.\"dias_economicos\" AS nclpdias_economicos,nclp.\"prima_aniversario\" AS nclpprima_aniversario,nclp.\"descuenta_faltas\" AS nclpdescuenta_faltas,nclp.\"descuenta_incapacidades\" AS nclpdescuenta_incapacidades,nclp.\"costo_vales_restaurante\" AS nclpcosto_vales_restaurante,nclp.\"descuento_prop_7o_dia\" AS nclpdescuento_prop_7o_dia,nclp.\"es_activo\" AS nclpes_activo,nclp.\"centroc_cliente_id\" AS nclpcentroc_cliente_id,nclp.\"es_estandar\" AS nclpes_estandar,nclp.\"calculo_antiguedadx_id\" AS nclpcalculo_antiguedadx_id,nclc.\"curp\" AS nclccurp,nclc.\"rfc\" AS nclcrfc,nclc.\"razon_social\" AS nclcrazon_social,nclc.\"nombre\" AS nclcnombre,nclc.\"certificado_sello_digital_id\" AS nclccertificado_sello_digital_id,nclc.\"cc_url_logo\" AS nclccc_url_logo,nclc.\"fecha_constitucion\" AS nclcfecha_constitucion,nclc.\"fecha_alta\" AS nclcfecha_alta,nclc.\"es_activo\" AS nclces_activo,nclc.\"multiempresa\" AS nclcmultiempresa,nclc.\"em_c_regimenfiscal\" AS nclcem_c_regimenfiscal,nclc.\"base_periodo_id\" AS nclcbase_periodo_id,nclc.\"c_actividad_economica\" AS nclcc_actividad_economica,nclc.\"centro_costos_centroc_cliente_id\" AS nclccentro_costos_centroc_cliente_id,nmmp.\"tipo_percepcion_id\" AS nmmptipo_percepcion_id,nmmp.\"nombre\" AS nmmpnombre,nmmp.\"tipo_periodicidad\" AS nmmptipo_periodicidad,nmmp.\"cuenta_contable\" AS nmmpcuenta_contable,nmmp.\"tipo_concepto\" AS nmmptipo_concepto,nmmp.\"grava_isn\" AS nmmpgrava_isn,nmmp.\"grava_isr\" AS nmmpgrava_isr,nmmp.\"integra_imss\" AS nmmpintegra_imss,nmmp.\"es_activo\" AS nmmpes_activo,nmmp.\"centroc_cliente_id\" AS nmmpcentroc_cliente_id,nmmp.\"c_tipo_otro_pago\" AS nmmpc_tipo_otro_pago,nmmp.\"tipo_pago\" AS nmmptipo_pago,nmmp.\"periodicidad_pago_id\" AS nmmpperiodicidad_pago_id,nmmp.\"especializacion\" AS nmmpespecializacion \n" +
            "FROM \"nmm_configura_percepcion\" nmm_configura_percepcion_ \n" +
            "    INNER JOIN \"cs_tipo_percepcion\" cs ON nmm_configura_percepcion_.\"tipo_percepcion_id\"=cs.\"tipo_percepcion_id\" \n" +
            "    INNER JOIN \"ncl_politica\" nclp ON nmm_configura_percepcion_.\"politica_id\"=nclp.\"politica_id\" \n" +
            "    INNER JOIN \"ncl_centroc_cliente\" nclc ON nmm_configura_percepcion_.\"centroc_cliente_id\"=nclc.\"centroc_cliente_id\" \n" +
            "    INNER JOIN \"nmm_concepto_percepcion\" nmmp ON nmm_configura_percepcion_.\"concepto_percepcion_id\"=nmmp.\"concepto_percepcion_id\" \n" +
            "WHERE nclp.\"politica_id\" = :politicaId \n" +
            "  AND nclc.\"centroc_cliente_id\" = :clienteId\n" +
            "  AND nmm_configura_percepcion_.\"es_activo\" = :esActivo\n" +
            "  AND :fechaInicio BETWEEN nmm_configura_percepcion_.pp_fecha_inicio AND :fechaFin",nativeQuery = true)
    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoPercepcionId", alias = "cs")
    @Join(value = "conceptoPercepcionId", alias = "nmmp")
    @Join(value = "politicaId", alias = "nclp")
    List<NmmConfiguraPercepcion> findByPoliticaIdPoliticaIdAndCentrocClienteIdCentrocClienteIdAndEsActivo(Integer politicaId, Integer clienteId,boolean esActivo, Date fechaInicio, Date fechaFin);

    void update(@Id Integer configuraPercepcionId, boolean esActivo);

    List<NmmConfiguraPercepcion> findByConceptoPercepcionIdConceptoPercepcionIdAndPoliticaIdPoliticaId(Integer conceptoPercepcionId, Integer politicaId);

    boolean existsByTipoPercepcionIdTipoPercepcionIdAndEspecializacionAndConceptoPercepcionIdConceptoPercepcionId(String tipoPercepcionId, String especializacion, Integer conceptoPercepcionId);


}