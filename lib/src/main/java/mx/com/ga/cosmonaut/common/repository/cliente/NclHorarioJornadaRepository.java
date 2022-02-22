package mx.com.ga.cosmonaut.common.repository.cliente;


import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import mx.com.ga.cosmonaut.common.entity.cliente.NclHorarioJornada;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclHorarioJornadaRepository extends CrudRepository<NclHorarioJornada, Integer> {


    List<NclHorarioJornada> findByNclJornadaJornadaId(Integer jornadaId);

    List<NclHorarioJornada> findByNclJornadaJornadaIdAndEsActivo(Integer jornadaId, boolean esActivo);
    
    void update(@Id Integer horarioJornadaId, boolean esActivo);

    boolean existsByNclJornadaJornadaIdAndDiaAndEsActivo(Integer jornadaId, Integer dia, boolean esActivo);


}