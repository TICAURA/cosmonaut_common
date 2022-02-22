package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.cliente.NclJornada;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPolitica;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclJornadaRepository extends CrudRepository<NclJornada, Integer> {

    @Join(value = "tipoJornadaId",alias="tj")
    @Join(value="sumaHorasJornadaId",alias="shj")
    @Join(value="centrocClienteId",alias="c")
    NclJornada findByJornadaId(Integer jornadaId);
    
    void update(@Id Integer jornadaId, boolean esActivo);

    List<NclJornada> findByEsActivoOrderByNombre(Boolean activo);

    NclJornada findByJornadaIdAndTipoJornadaIdTipoJornadaId(Integer jornadaId, String tipoJornadaId);

    List<NclJornada> findByEsActivoAndCentrocClienteIdCentrocClienteIdOrderByJornadaId(
            boolean activo,Integer centroCClienteId);

    boolean existsByJornadaIdAndCentrocClienteIdCentrocClienteId(Integer jornadaId, Integer centrocClienteId);

    @Query(value = "select * from ncl_jornada nj join ncl_centroc_cliente ncc on nj.centroc_cliente_id = ncc.centroc_cliente_id  and ncc.centroc_cliente_id = :idCliente and nj.jornada_id = :idJoranda", nativeQuery = true)
    List<NclJornada> CentrocClienteIdCentrocClienteIdAndNombreAndEsActivoEditar(Integer idCliente, Integer idJoranda);

    @Query(value = "select nj.* from ncl_jornada nj join ncl_centroc_cliente ncc on nj.centroc_cliente_id = ncc.centroc_cliente_id  and ncc.centroc_cliente_id = :idCliente and upper(nj.nombre) = :nombre and nj.es_activo = true", nativeQuery = true)
    List<NclJornada> centrocClienteIdCentrocClienteIdAndNombreAndEsActivoRepetidos(Integer idCliente, String nombre);

}
