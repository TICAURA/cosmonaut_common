package mx.com.ga.cosmonaut.common.repository.temporal;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.temporal.TmpCargaPTU;

import java.util.List;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CargaPTURepository extends CrudRepository<TmpCargaPTU, Integer> {

    @Query(value = "select tcp.* from tmp_carga_ptu tcp " +
            "inner join nco_contrato_colaborador ncc  " +
            "on (ncc.num_empleado = tcp.numeroempleado) " +
            "where ncc.centroc_cliente_id =:centrocClienteId",nativeQuery = true)
    List<TmpCargaPTU> findByCentroClienteId(Integer centrocClienteId) ;

}
