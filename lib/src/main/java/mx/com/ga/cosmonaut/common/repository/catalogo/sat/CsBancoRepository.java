package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsBanco;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsBancoRepository extends CrudRepository<CsBanco, Long> {
    
    @Override
    List<CsBanco> findAll();

    CsBanco findByRazonSocial (String razonSocial);

    List<CsBanco> findByEsActivo(Boolean activo);
    @Query(value = "select * from cs_banco where upper(trim(nombre_corto)) = :nombreCorto;",nativeQuery=true)
    List<CsBanco> findByNombreCortoValida(String nombreCorto);

    List<CsBanco> findByNombreCorto(String nombreCorto);

    List<CsBanco> findByEsActivoOrderByBancoId(Boolean activo);
    
    Optional<CsBanco> findByCodBanco(String codBanco);

    List<CsBanco> findByNombreCortoIlike(String nombreCorto);

}