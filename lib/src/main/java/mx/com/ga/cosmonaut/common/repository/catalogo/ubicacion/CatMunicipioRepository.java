package mx.com.ga.cosmonaut.common.repository.catalogo.ubicacion;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatMunicipio;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatMunicipioRepository extends CrudRepository<CatMunicipio, Integer> {

    @Query(value = "SELECT c_mnpio, d_mnpio, es_activo, fecha_alta, c_estado " +
            "FROM public.cat_municipio " +
            "where es_activo = :activo " +
            "order by d_mnpio ", nativeQuery = true)
    List<CatMunicipio> findByEsActivo(Boolean activo);
    
     List<CatMunicipio> findByDmnpio(String dmnpio);
    
    
}
