package mx.com.ga.cosmonaut.common.repository.colaborador;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcoPersonaRepository extends CrudRepository<NcoPersona, Integer> {
    NcoPersona findByPersonaId (Integer personaId);

    @Query(value = "select p.* from nco_persona p join nco_contrato_colaborador c on (p.persona_id=c.persona_id) where c.centroc_cliente_id = :centro and p.curp= :curp",
            nativeQuery = true)
    List<NcoPersona> findCentroAndCurp(Integer centro, String curp);

    @Query(value = "select p.* from nco_persona p join nco_contrato_colaborador c on (p.persona_id=c.persona_id) where c.centroc_cliente_id = :centro and p.rfc= :rfc",
            nativeQuery = true)
    List<NcoPersona> findCentroAndRfc(Integer centro, String rfc);

    @Query(value = "select p.* from nco_persona p join nco_contrato_colaborador c on (p.persona_id=c.persona_id) where c.centroc_cliente_id = :centro and p.nss= :nss",
            nativeQuery = true)
    List<NcoPersona> findCentroAndNSS(Integer centro, String nss);

    @Query(value = "SELECT * FROM nco_persona ncop WHERE ncop.ci_email_personal = :contactoInicialEmailPersonal ",
            nativeQuery = true)
    List<NcoPersona> findByContactoInicialEmailPersonal(String contactoInicialEmailPersonal);

    @Join(value = "centrocClienteId", type = Join.Type.FETCH)
    List<NcoPersona> findByEmailCorporativo(String emailCorporativo);

    @Query(value = "SELECT * FROM nco_persona ncop WHERE ncop.persona_id = :personaId "
            + " AND ci_email_personal = :contactoInicialEmailPersonal",
            nativeQuery = true)
    List<NcoPersona> findByPersonaIdAndContactoInicialEmailPersonal(Integer personaId, String contactoInicialEmailPersonal);

    @Query(value = "SELECT * FROM nco_persona ncop WHERE ncop.persona_id = :personaId "
            + " AND ncop.email_corp = :emailCorporativo",
            nativeQuery = true)
    List<NcoPersona> findByPersonaIdAndEmailCorporativo(Integer personaId, String emailCorporativo);

    @Join(value = "centrocClienteId", type = Join.Type.FETCH)
    List<NcoPersona> findByTipoPersonaIdTipoPersonaIdOrderByPersonaIdDesc(Integer tipoPersonaId);

    @Join(value = "nacionalidadId", type = Join.Type.FETCH)
    @Join(value = "centrocClienteId", type = Join.Type.FETCH)
    List<NcoPersona> findByCentrocClienteIdCentrocClienteIdAndTipoPersonaIdTipoPersonaIdOrderByPersonaIdDesc(Integer centrocClienteId, Integer tipoPersonaId);

    @Join(value = "centrocClienteId", type = Join.Type.FETCH)
    List<NcoPersona> findByCentrocClienteIdCentrocClienteIdOrderByPersonaIdDesc(Integer centrocClienteId);

    @Join(value = "nacionalidadId", type = Join.Type.FETCH)
    @Join(value = "centrocClienteId", type = Join.Type.FETCH)
    List<NcoPersona> findByCentrocClienteIdCentrocClienteIdAndTipoPersonaIdTipoPersonaIdAndEsActivoOrderByPersonaIdDesc(Integer centrocClienteId, Integer tipoPersonaId, boolean activo);

    void update(@Id Integer id, boolean esActivo);

    @Query(value = "SELECT * FROM nco_persona persona " +
            "WHERE NOT EXISTS (SELECT NULL FROM nco_contrato_colaborador colaborador WHERE colaborador.persona_id = persona.persona_id) " +
            "AND persona.tipo_persona_id = 5 " +
            "AND persona.representante_legal_centroc_cliente_id = :centroClienteId " +
            "ORDER BY persona.fecha_alta, persona.nombre",nativeQuery = true)
    List<NcoPersona> findByColaboradorNotExists(Integer centroClienteId);

    boolean existsByPersonaId(Integer personaId);

    boolean existsByRfc(String rfc);

    boolean existsByCurp(String curp);

    boolean existsByNss(String nss);

    boolean existsByRfcAndCentrocClienteIdCentrocClienteId(String rfc,Integer centrocClienteId);

    boolean existsByCurpAndCentrocClienteIdCentrocClienteId(String curp,Integer centrocClienteId);

    boolean existsByNssAndCentrocClienteIdCentrocClienteId(String nss,Integer centrocClienteId);

    boolean existsByEmailCorporativo(String emailCorporativo);

    @Query(value = "SELECT exists(SELECT 1 FROM nco_persona WHERE ci_email_personal = :contactoInicialEmailPersonal )"
            ,nativeQuery = true)
    boolean existsByContactoInicialEmailPersonal(String contactoInicialEmailPersonal);

    boolean existsByPersonaIdAndEmailCorporativoAndTipoPersonaIdTipoPersonaId(Integer personaId, String emailCorporativo,Integer tipoPersonaId);

    boolean existsByEmailCorporativoAndTipoPersonaIdTipoPersonaId(String emailCorporativo,Integer tipoPersonaId);

    boolean existsByEmailCorporativoAndTipoPersonaIdTipoPersonaIdAndCentrocClienteIdCentrocClienteId(String emailCorporativo,Integer tipoPersonaId, Integer centrocClienteId);

    @Query(value = "SELECT exists(SELECT 1 FROM nco_persona WHERE ci_email_personal = :contactoInicialEmailPersonal "
            + " AND tipo_persona_id = :tipoPersonaId )" ,nativeQuery = true)
    boolean existsByContactoInicialEmailPersonalAndTipoPersonaIdTipoPersonaId(String contactoInicialEmailPersonal, Integer tipoPersonaId);


    @Query(value = "SELECT exists(SELECT 1 FROM nco_persona WHERE ci_email_personal = :contactoInicialEmailPersonal "
            + " AND tipo_persona_id = :tipoPersonaId AND representante_legal_centroc_cliente_id = :centrocClienteId )" ,nativeQuery = true)
    boolean existsByContactoInicialEmailPersonalAndTipoPersonaIdTipoPersonaIdAndCentrocClienteIdcentrocClienteId(String contactoInicialEmailPersonal, Integer tipoPersonaId,Integer centrocClienteId);

    @Query(value = "SELECT exists(SELECT 1 FROM nco_persona WHERE persona_id = :personaId "
            + " AND ci_email_personal = :contactoInicialEmailPersonal "
            + " AND tipo_persona_id = :tipoPersonaId )" ,nativeQuery = true)
    boolean existsByPersonaIdAndContactoInicialEmailPersonalAndTipoPersonaIdTipoPersonaId(Integer personaId, String contactoInicialEmailPersonal, Integer tipoPersonaId);

    @Query(value = " select np.persona_id, ncc.num_empleado as curp, rfc, nombre, " +
            "apellido_pat, apellido_mat, fecha_nace, " +
            "email_corp, genero, nss, iba_nacionalidad_id, " +
            "iba_estado_civil, iba_tiene_hijos, iba_num_hijos, " +
            "ce_nombre, ce_parentesco_id, ce_e_mail, ce_telefono, " +
            "ci_email_personal, ci_telefono, ci_extension, " +
            "representante_legal_centroc_cliente_id, tipo_representante_id, " +
            "rl_url_firma, np.es_activo, fecha_alta, tipo_persona_id, tiene_curp, " +
            "tiene_rfc, tiene_nss, ce_apellido_paterno, ce_apellido_materno, " +
            "invitar_empleado, url_linkedin, celular, facultad_poder_id, " +
            "poder_notarial, ci_puesto, url_imagen, nombre2 " +
            "from nco_persona np   " +
            "inner join nco_contrato_colaborador ncc  " +
            "Inner JOIN (SELECT persona_id,MAX(fecha_inicio) fecha FROM nco_contrato_colaborador GROUP BY persona_id) colaborador_fecha  ON ncc.fecha_inicio = colaborador_fecha.fecha AND ncc.persona_id = colaborador_fecha.persona_id   " +
            "on (ncc.persona_id = np.persona_id)   where ncc.centroc_cliente_id =:idEmpresa ", nativeQuery = true)
    List<NcoPersona> findByPersona(Integer idEmpresa);

    Optional<NcoPersona>  findByEmailCorporativoAndCentrocClienteIdCentrocClienteId(String correo, Integer centrocClienteId);

    @Query(value = "select mm1.descripcion from nco_contrato_colaborador cc1\n" +
            "  inner join cat_metodo_pago mm1 on cc1.metodo_pago_id = mm1.metodo_pago_id  where persona_id  = :idPersona\n" +
            "  order by fecha_contrato desc limit 1 ",nativeQuery = true)
    String getMetodoPagoByPersona(Long idPersona);
}