package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTasaAplicableIsn;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstado;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatTasaAplicableIsnRepository extends CrudRepository<CatTasaAplicableIsn, Integer> {
    
    List<CatTasaAplicableIsn> findByEsActivo(Boolean activo);

    @Query(value="SELECT * FROM cat_tasa_aplicable_isn cta WHERE cta.tasa_aplicable_isn_id = :id and es_activo = true ", nativeQuery = true)
    CatTasaAplicableIsn findByTasaAplicableIsnIdAndAndEsActivo(Integer id);

    @Join(value="cestado", alias ="edo")
    List<CatTasaAplicableIsn> findByEsActivoOrderByTasaAplicableIsnId(Boolean esActivo);
    
    @Query(value="select * from cat_tasa_aplicable_isn where limite_inferior = :limiteInferior  and c_estado = :estado", nativeQuery = true)
    List<CatTasaAplicableIsn> obtenPorLimiteInferior(BigDecimal limiteInferior, Integer estado);

    @Join(value="cestado", alias ="edo")
    Set<CatEstado> findCestado();

    @Join(value="cestado", alias ="edo")
    List<CatTasaAplicableIsn> findByEsActivoAndCestadoEstadoIdOrderByTasaAplicableIsnId(boolean esActivo, Integer estadoId);

    @Query("SELECT * FROM cat_tasa_aplicable_isn cta WHERE cta.es_activo = :esActivo AND cta.c_estado = :estadoId ORDER BY cta.tasa_aplicable_isn_id ")
    List<CatTasaAplicableIsn> findByEsActivoAndCestadoEstadoIdOrderByTasaAplicableIsnIdEsp(
            boolean esActivo, Integer estadoId);

    @Query(value = "SELECT * FROM cat_tasa_aplicable_isn tasa WHERE tasa.c_estado = :estado " +
            "AND :sbm between tasa.limite_inferior and tasa.limite_superior", nativeQuery = true)
    CatTasaAplicableIsn findByCestadoEstadoIdAndSbm(Integer estado, BigDecimal sbm);

    @Query(value = "SELECT * FROM cat_tasa_aplicable_isn tasa WHERE tasa.c_estado = :estado " +
            "AND :sbm between tasa.limite_inferior and tasa.limite_superior " +
            "AND tasa.fecha_inicio <= :fecha", nativeQuery = true)
    Optional<CatTasaAplicableIsn> findByCestadoEstadoIdAndSbm(Integer estado, BigDecimal sbm, Date fecha);

    @Query(value = "SELECT * FROM cat_tasa_aplicable_isn tasa WHERE tasa.c_estado in (:listaEstado) ", nativeQuery = true)
    List<CatTasaAplicableIsn> existsByIdCestadoEstadoId(List<Integer> listaEstado);

    @Query(value = "SELECT * FROM cat_tasa_aplicable_isn tasa WHERE tasa.c_estado in (:listaEstado) AND tasa.fecha_inicio <= :fecha ", nativeQuery = true)
    List<CatTasaAplicableIsn> existsByIdCestadoEstadoIdAndFecha(List<Integer> listaEstado, Date fecha);

    @Query(value = "SELECT *\n" +
            "FROM cat_tasa_aplicable_isn tasa \n" +
            "WHERE tasa.c_estado = :estadoId\n" +
            "AND (:limiteInferior BETWEEN limite_inferior AND limite_superior OR :limiteSuperior BETWEEN limite_inferior AND limite_superior) ", nativeQuery = true)
    List<CatTasaAplicableIsn> existsByIdCestadoEstadoIdAndLimite(Integer estadoId,Double limiteInferior,Double limiteSuperior,Double cuotaFija);

    @Query(value = "SELECT *\n" +
            "FROM cat_tasa_aplicable_isn tasa \n" +
            "WHERE (tasa.c_estado = :estadoId \n" +
            "AND tasa.cuota_fija = :cuotaFija \n" +
            "AND :limiteInferior BETWEEN limite_inferior AND limite_superior)\n" +
            "OR (tasa.c_estado = :estadoId \n" +
            "AND tasa.cuota_fija = :cuotaFija \n" +
            "AND :limiteSuperior BETWEEN limite_inferior AND limite_superior) ", nativeQuery = true)
    List<CatTasaAplicableIsn> findByIdEstadoEstadoIdAndLimitesAndCuotaFija(Integer estadoId,Double limiteInferior,Double limiteSuperior,Double cuotaFija);


    @Query(value = "SELECT * FROM cat_tasa_aplicable_isn cta WHERE   ((:fechaInicio  between cta.fecha_inicio and  cta.fecha_fin) \n" +
            " or (:fechaFin  between cta.fecha_inicio and  cta.fecha_fin) or \n" +
            " (cta.fecha_inicio <= :fechaInicio \n" +
            " and   cta.fecha_fin >= :fechaFin)) and \n" +
            " cta.c_estado = :estadoId and cta.es_activo = true ORDER BY cta.tasa_aplicable_isn_id ", nativeQuery = true)
    List<CatTasaAplicableIsn> findByIdEstadoEstadoTraslaparFechas(Integer estadoId,Date fechaInicio,Date fechaFin);


    @Query(value = "SELECT * FROM cat_tasa_aplicable_isn cta WHERE   ((:fechaInicio  between cta.fecha_inicio and  cta.fecha_fin) \n" +
            " or (:fechaFin  between cta.fecha_inicio and  cta.fecha_fin) or \n" +
            " (cta.fecha_inicio <= :fechaInicio) \n" +
            " and cta.fecha_fin >= :fechaFin or (cta.fecha_inicio >= :fechaInicio) \n" +
            " and cta.fecha_fin <= :fechaFin ) and \n" +
            " cta.c_estado = :estadoId and cta.es_activo = true \n" +
            " EXCEPT SELECT * FROM cat_tasa_aplicable_isn cta \n" +
            " WHERE cta.fecha_inicio = :fechaInicioOld and cta.fecha_fin = :fechaFinOld and cta.c_estado = :estadoId and cta.es_activo = true", nativeQuery = true)
    List<CatTasaAplicableIsn> findByIdEstadoEstadoTraslaparFechasEditar(Integer estadoId,Date fechaInicio,Date fechaFin,Date fechaInicioOld,Date fechaFinOld);




    @Query(value = "SELECT *\n" +
            "FROM cat_tasa_aplicable_isn tasa \n" +
            "WHERE (tasa.c_estado = :estadoId \n" +
            "AND fecha_inicio = :fechaInicio\n" +
            "AND :limiteInferior BETWEEN limite_inferior AND limite_superior)\n" +
            "OR (tasa.c_estado = :estadoId \n" +
            "AND fecha_inicio = :fechaInicio \n" +
            "AND :limiteSuperior BETWEEN limite_inferior AND limite_superior) " , nativeQuery = true)
    List<CatTasaAplicableIsn> findByIdEstadoEstadoIdAndLimitesAndCuotaFija(Integer estadoId,Double limiteInferior,Double limiteSuperior,Double cuotaFija,Date fechaInicio);


}