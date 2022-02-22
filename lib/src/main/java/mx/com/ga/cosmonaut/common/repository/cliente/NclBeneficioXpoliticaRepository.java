package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;
import java.util.Optional;

import mx.com.ga.cosmonaut.common.entity.cliente.NclBeneficioXpolitica;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclBeneficioXpoliticaRepository extends CrudRepository<NclBeneficioXpolitica, Integer> {
    
    @Query(value = "select nbx.*, np.centroc_cliente_id, np.es_estandar from ncl_beneficio_xpolitica nbx inner join ncl_politica np on np.politica_id = nbx.politica_id \n" +
                    "and nbx.politica_id = :idPolitica inner join ncl_centroc_cliente ncc on ncc.centroc_cliente_id = np.centroc_cliente_id and ncc.centroc_cliente_id = :idCliente \n"
                    + "and np.es_activo =true and nbx.es_activo = true order by nbx.beneficio_xpolitica_id", nativeQuery=true)
    List<NclBeneficioXpolitica> consultaBeneficiosPoliticaId(Integer idPolitica, Integer idCliente);

    @Query(value = "SELECT dias_vacaciones " +
            "FROM ncl_beneficio_xpolitica " +
            "where politica_id =:idPolitica and anios_antiguedad =:aniosAntiguedad", nativeQuery = true)
    Integer consultaDiasVacaciones(Integer idPolitica, Integer aniosAntiguedad);

    @Query(value = "SELECT * FROM ncl_beneficio_xpolitica WHERE politica_id = :idPolitica AND anios_antiguedad >= :aniosAntiguedad",
            nativeQuery=true)
    List<NclBeneficioXpolitica> findByPoliticaIdAndAniosAntiguedad(Integer idPolitica, Integer aniosAntiguedad);

    void update(@Id Integer id, boolean esActivo);

    Optional<NclBeneficioXpolitica> findFirstByNclPoliticaPoliticaIdAndAniosAntiguedadLessThanEqualsAndEsActivoOrderByAniosAntiguedadDesc(
            Integer id, Integer anios, Boolean esActivo);

    Optional<NclBeneficioXpolitica> findFirstByNclPoliticaPoliticaIdAndAniosAntiguedadGreaterThanEqualsAndEsActivoOrderByAniosAntiguedad(
            Integer id, Integer anios, Boolean esActivo);
}