package mx.com.ga.cosmonaut.common.repository.catalogo.ubicacion;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import java.util.List;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatAsentamiento;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatAsentamientoRepository extends CrudRepository<CatAsentamiento, String> {

     @Query(value = "select *, cm.d_mnpio as dMnpio, ce.d_estado as dEdo from cat_asentamiento ca \n"
            + "inner join cat_municipio cm  on ca.c_mnpio = cm.c_mnpio and ca.c_estado = cm.c_estado \n"
            + "inner join cat_estado ce on ce.c_estado = ca.c_estado and cm.c_estado = ce.c_estado \n"
            + "where ca.d_codigo = :codigo ", nativeQuery = true)
    List<CatAsentamiento> obtieneAsentamientos(String codigo);
    
    @Join(value= "catmnpio", alias ="cm", type = Join.Type.LEFT)
    @Join(value= "edo", alias ="dedo", type = Join.Type.LEFT)
    List<CatAsentamiento> findByCodigo(String codigo);

    @Query(value = "SELECT d_codigo, c_mnpio, id_asenta_cpcons, " +
            "d_asenta, d_cp, c_oficina, c_cp, c_tipo_asenta, c_zona, " +
            "es_activo, fecha_alta, c_estado " +
            "FROM cat_asentamiento " +
            "where es_activo = :activo " +
            "order by d_asenta ", nativeQuery = true)
    List<CatAsentamiento> findByEsActivo(Boolean activo);
    
    List<CatAsentamiento> findByCodigoAndAsentamientoCpCons(String codigo, Integer asentamientoCpCons);

}
