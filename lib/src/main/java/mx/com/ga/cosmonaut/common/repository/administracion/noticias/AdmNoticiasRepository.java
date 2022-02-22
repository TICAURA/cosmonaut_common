package mx.com.ga.cosmonaut.common.repository.administracion.noticias;


import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.noticias.AdmNoticias;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmNoticiasRepository extends CrudRepository<AdmNoticias, Integer> {
    @Join(value = "categoriaId",type = Join.Type.FETCH)
    List<AdmNoticias> findByCentrocClienteIdCentrocClienteIdInList(List<Integer> lista);

    @Join(value = "categoriaId",type = Join.Type.FETCH)
    List<AdmNoticias> findByPersonasIdPersonaIdInList(List<Integer> lista);


    @Join(value = "categoriaId",type = Join.Type.FETCH)
    List<AdmNoticias> findByClienteIdInList(List<Integer> lista);


    @Join(value = "categoriaId",type = Join.Type.FETCH)
    @Join(value = "centrocClienteId",type = Join.Type.LEFT_FETCH,alias = "micr")
    @Join(value = "personasId",type = Join.Type.LEFT_FETCH,alias = "personaiid")
    Optional<AdmNoticias> findById(Integer id);

}
