package mx.com.ga.cosmonaut.common.repository.colaborador;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoKardexColaborador;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcoKardexColaboradorRepository extends CrudRepository<NcoKardexColaborador, Long> {

    @Join(value = "centrocClienteId", alias = "centrocClienteId")
    @Join(value = "personaId", alias = "personaId")
    List<NcoKardexColaborador> findByCentrocClienteIdCentrocClienteIdAndPersonaIdPersonaId(Integer centrocClienteId, Integer personaId);

    void update(@Id Integer id, boolean esActivo);

    void updateByKardexColaboradorId(@Id Integer id, boolean esImss);

    @Query(value = "SELECT nkc.* FROM nco_kardex_colaborador nkc " +
            "INNER JOIN nco_persona np ON nkc.persona_id = np.persona_id " +
            "INNER JOIN nco_cat_moviminto_imss ncmi ON nkc.movimiento_imss_id = ncmi.movimiento_imss_id " +
            "WHERE nkc.fecha_movimiento::TEXT LIKE CONCAT(:fecha, '%') " +
            "AND np.nss = :nss " +
            "AND ncmi.clave = :clave ", nativeQuery = true)
    Optional<NcoKardexColaborador> findByNssClaveMovimientoFechaMovimiento(String nss, String clave, String fecha);

}
