package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatValorReferencia;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatValorReferenciaRepository extends CrudRepository<CatValorReferencia, Long> {
    
    
    @Join(value="tipoValorReferenciaId", alias= "tvr")
    List<CatValorReferencia> findByEsActivoOrderByValorReferenciaId(Boolean esActivo);
    
    @Join(value="anioLey", alias= "al")
    @Join(value="tipoValorReferenciaId", alias= "tvr")
    List<CatValorReferencia> findByEsActivoAndAnioLeyAnioLeyOrderByValorReferenciaId(Boolean esActivo, Long anioLey);

    @Query("SELECT * FROM cat_valor_referencia cvr WHERE cvr.fecha_fin < NOW()::DATE AND cvr.es_activo = :esActivo " +
            "AND cvr.anio_ley = :anioLey ORDER BY cvr.valor_referencia_id ")
    List<CatValorReferencia> findByEsActivoAndAnioLeyAnioLeyOrderByValorReferenciaIdEspecial(
            Boolean esActivo, Long anioLey);

    List<CatValorReferencia> findByValor(BigDecimal valor);

    List<CatValorReferencia> findByValorAndTipoValorReferenciaIdTipoValorReferenciaIdAndEsActivo(BigDecimal valor,Long idReferencia,boolean esActivo);

    CatValorReferencia findByAnioLeyAnioLeyAndTipoValorReferenciaIdTipoValorReferenciaId(Long anioLey, Long tipoValorReferenciaId);

    @Query("SELECT * FROM cat_valor_referencia valor_referencia " +
            "INNER JOIN  cat_tipo_valor_referencia tipo_valor_referencia ON valor_referencia.tipo_valor_referencia_id = tipo_valor_referencia.tipo_valor_referencia_id\n" +
            "WHERE valor_referencia.tipo_valor_referencia_id = :tipoValorReferenciaId\n" +
            "AND :fecha BETWEEN valor_referencia.fecha_inicio and valor_referencia.fecha_fin ")
    CatValorReferencia findByTipoValorReferenciaIdTipoValorReferenciaIdAndFechaBetween(Long tipoValorReferenciaId, Date fecha);

    @Query("SELECT COUNT(*) FROM cat_valor_referencia referencia WHERE ( :fechaInicio BETWEEN referencia.fecha_inicio AND referencia.fecha_fin or :fechaFin BETWEEN referencia.fecha_inicio AND referencia.fecha_fin) and tipo_valor_referencia_id = :tipoValorReferencia and es_activo = true")
    Long findByFechaBetween(Date fechaInicio,Date fechaFin,Long tipoValorReferencia);

    @Query("SELECT * FROM cat_valor_referencia referencia WHERE ( :fechaInicio BETWEEN referencia.fecha_inicio AND referencia.fecha_fin or :fechaFin BETWEEN referencia.fecha_inicio AND referencia.fecha_fin) and tipo_valor_referencia_id = :tipoValorReferencia and es_activo = true")
    List<CatValorReferencia> findByFechaBetweenObj(Date fechaInicio,Date fechaFin,Long tipoValorReferencia);

    @Query("SELECT COUNT(*) FROM cat_valor_referencia referencia WHERE ( :fechaInicio BETWEEN referencia.fecha_inicio AND referencia.fecha_fin or :fechaFin BETWEEN referencia.fecha_inicio AND referencia.fecha_fin) and tipo_valor_referencia_id = :tipoValorReferencia and es_activo = true and valor = :valor")
    Long valorRepetidoPorFechasTipoValorReferenciaId(Date fechaInicio,Date fechaFin,Long tipoValorReferencia,BigDecimal valor);

    @Query(value = "SELECT * from cat_valor_referencia referencia \n" +
            "WHERE :fecha between referencia.fecha_inicio AND referencia.fecha_fin\n" +
            "AND referencia.tipo_valor_referencia_id IN (:listaTipoReferencia)", nativeQuery = true)
    List<CatValorReferencia> findByBetweenFechaAndListaTipoReferencia(Date fecha,List<Integer> listaTipoReferencia);

    @Override
    List<CatValorReferencia> findAll();

    @Join(value="tipoValorReferenciaId", alias= "tipo")
    List<CatValorReferencia> findByTipoValorReferenciaIdDescripcionIlike(String descripcion);

    @Query(value = "SELECT COUNT(*) FROM cat_valor_referencia referencia \n" +
            "WHERE tipo_valor_referencia_id = :tipoValorReferencia \n" +
            "AND valor = :valor\n" +
            "AND es_activo = true\n" +
            "AND ( :fechaInicio BETWEEN referencia.fecha_inicio AND referencia.fecha_fin \n" +
            "OR :fechaFin BETWEEN referencia.fecha_inicio AND referencia.fecha_fin)",nativeQuery = true)
    Long findByFechaBetweenAndValor(Date fechaInicio,Date fechaFin,Long tipoValorReferencia,Double valor);


}