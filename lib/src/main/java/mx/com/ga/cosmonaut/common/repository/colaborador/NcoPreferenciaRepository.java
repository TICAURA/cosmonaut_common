package mx.com.ga.cosmonaut.common.repository.colaborador;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPreferencia;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcoPreferenciaRepository extends CrudRepository<NcoPreferencia, Integer> {

    void update(@Id Integer id, boolean esActivo);

    @Join(value = "tipoPreferenciaId", alias = "preferencia")
    @Join(value = "personaId", alias = "persona")
    List<NcoPreferencia> findByPersonaIdPersonaId(Integer personaId);

}