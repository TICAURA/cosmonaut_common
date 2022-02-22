package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrNominaXperiodo;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrProcesoNomina;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatEstadoProcesoNomina;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrProcesoNominaRepository extends CrudRepository<NcrProcesoNomina, Integer> {

    List<NcrProcesoNomina> findByEstadoProcesoNominaIdEstadoProcesoNominaId(Integer estadoProcesoNominaId);

    @Query(value = "SELECT * FROM ncr_proceso_nomina proceso\n" +
            "INNER JOIN ncr_nomina_xperiodo nomina on nomina.nomina_xperiodo_id = proceso.nomina_xperiodo_id \n" +
            "WHERE nomina.centroc_cliente_id = :centrocClienteId \n" +
            "AND proceso.visto = :visto \n" +
            "AND proceso.estado_proceso_nomina_id IN (3,4)",nativeQuery = true)
    List<NcrProcesoNomina> findBycentrocClienteIdAndVisto(Integer centrocClienteId, boolean visto);

    Optional<NcrProcesoNomina> findByNominaXperiodoIdNominaXperiodoIdAndEstadoProcesoNominaIdEstadoProcesoNominaId(Integer nominaXperiodoId, Integer estadoProcesoNominaId);

    Optional<NcrProcesoNomina> findByNominaXperiodoIdNominaXperiodoId(Integer nominaXperiodoId);

    @Query(value = "SELECT count(*) FROM ncr_proceso_nomina proceso WHERE proceso.estado_proceso_nomina_id = :estadoProcesoNominaId ",nativeQuery = true)
    long countByEstadoProcesoNominaIdEstadoProcesoNominaId(Integer estadoProcesoNominaId);

    void updateByNominaXperiodoId(NcrNominaXperiodo nominaXperiodoId, CatEstadoProcesoNomina estadoProcesoNominaId);

    void updateByProcesoNominaId(Integer procesoNominaId, CatEstadoProcesoNomina estadoProcesoNominaId);

    void updateByNominaXperiodoId(NcrNominaXperiodo nominaXperiodoId, boolean visto);

    void deleteByNominaXperiodoId(NcrNominaXperiodo nominaXperiodoId);

}
