package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatDiaFestivo;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatDiaFestivoRepository extends CrudRepository<CatDiaFestivo, Date> {

     @Override
    List<CatDiaFestivo> findAll();
    
    @Override
    @Query("select * from cat_dia_festivo where dia_festivo_id = :id")
    Optional<CatDiaFestivo> findById(Date id);

    List<CatDiaFestivo> findByEsActivoOrderByNombre(Boolean activo);
}