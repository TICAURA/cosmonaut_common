package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.dto.cliente.ListarCompaniaSimpleResponse;
import mx.com.ga.cosmonaut.common.dto.cliente.ListarEmpresaSimpleResponse;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclCentrocClienteRepository extends CrudRepository<NclCentrocCliente, Integer> {

    List<NclCentrocCliente> findByRfc(String rfc);

    List<NclCentrocCliente> findByCentrocClienteIdAndRfc(Integer centrocClienteId, String rfc);

    List<NclCentrocCliente> findByCentroCostosCentrocClienteIdCentroCostosCentrocClienteIdIsNullOrderByfechaAltaDesc();

    @Query(value = "select * from ncl_centroc_cliente "
            + "WHERE centro_costos_centroc_cliente_id IS NULL ORDER BY centroc_cliente_id desc ",
            nativeQuery = true)
    List<NclCentrocCliente> findByCentroCostoCliente();

    @Query(value = "select * from ncl_centroc_cliente "
            + "WHERE centro_costos_centroc_cliente_id IS NULL LIMIT :numeroRegistros OFFSET :pagina ",
            nativeQuery = true)
    List<NclCentrocCliente> findByCentroCostoClientePaginado(Integer numeroRegistros, Integer pagina);

    List<NclCentrocCliente> findByCentroCostosCentrocClienteIdCentrocClienteId(Integer centrocClienteId);

    @Override
    List<NclCentrocCliente> findAll();

    void update(@Id Integer id, Boolean esActivo);

    boolean existsByCentrocClienteId(Integer centrocClienteId);

    boolean existsByRfcAndCentroCostosCentrocClienteIdCentrocClienteId(String rfc, Integer centrocClienteId);

    boolean existsByRfc(String rfc);

    // PROJECTIONS
    @Query(value = "SELECT ncc.centroc_cliente_id, ncc.rfc, ncc.razon_social, ncc.nombre, ncc.es_activo FROM ncl_centroc_cliente ncc "
            + "WHERE ncc.centro_costos_centroc_cliente_id IS NULL AND ncc.es_activo = true " +
            "ORDER BY ncc.centroc_cliente_id DESC ", nativeQuery = true)
    List<ListarCompaniaSimpleResponse> findByEsActivoAndCentroCostosCentrocClienteIdCentrocClienteIdIsNull(Boolean esActivo);
    @Query(value = "SELECT ncc.centroc_cliente_id, ncc.rfc, ncc.razon_social, ncc.nombre, ncc.es_activo FROM ncl_centroc_cliente ncc "
            + "WHERE ncc.centro_costos_centroc_cliente_id IS NOT NULL AND ncc.es_activo = true " +
            "ORDER BY ncc.centroc_cliente_id DESC ", nativeQuery = true)
    List<ListarEmpresaSimpleResponse> findByEsActivoAndCentroCostosCentrocClienteIdCentrocClienteIdNotNull(Boolean esActivo);
    // PROJECTIONS

}