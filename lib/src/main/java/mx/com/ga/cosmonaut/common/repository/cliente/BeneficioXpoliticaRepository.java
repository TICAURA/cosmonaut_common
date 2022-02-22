package mx.com.ga.cosmonaut.common.repository.cliente;


import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;
import mx.com.ga.cosmonaut.common.entity.cliente.BeneficioXpolitica;



@JdbcRepository(dialect = Dialect.POSTGRES)
public interface BeneficioXpoliticaRepository extends CrudRepository<BeneficioXpolitica, Integer> {
    
    @Join(value="politicaId", alias = "np")
    List<BeneficioXpolitica> findByPoliticaIdPoliticaIdAndPoliticaIdEsEstandar(Integer politicaId, Boolean esEstandar);
    
    @Join(value="politicaId", alias = "np")
    List<BeneficioXpolitica> findByPoliticaIdPoliticaId(Integer politicaId);

    @Query(value = "SELECT * FROM ncl_beneficio_xpolitica beneficioId\n" +
            "    INNER JOIN ncl_politica politicaId on beneficioId.politica_id = politicaId.politica_id\n" +
            "    WHERE politicaId.politica_id  = :politicaId\n" +
            "    AND beneficioId.anios_antiguedad = (SELECT min(anios_antiguedad)\n" +
            "    FROM ncl_beneficio_xpolitica politica_beneficio\n" +
            "    WHERE politica_beneficio.anios_antiguedad >= aniosAntiguedad\n" +
            "    AND politica_beneficio.politica_id = :politicaId\n" +
            "    AND politica_beneficio.es_activo = TRUE)\n" +
            "    AND politicaId.es_activo = TRUE\n" +
            "    AND beneficioId.es_activo = TRUE ",nativeQuery = true)
    @Join(value="politicaId", alias = "np")
    BeneficioXpolitica findByPoliticaIdPoliticaIdAndAniosAntiguedad(Integer politicaId, Integer aniosAntiguedad);


}