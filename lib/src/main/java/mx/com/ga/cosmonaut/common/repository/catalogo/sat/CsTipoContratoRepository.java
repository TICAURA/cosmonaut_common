package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoContrato;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsTipoContratoRepository extends CrudRepository<CsTipoContrato, String> {

    @Query(value = "SELECT tipo_contrato_id, " +
            "concat(tipo_contrato_id , '-', descripcion) as descripcion , " +
            "fecha_inicio, es_activo, fecha_fin " +
            "FROM cs_tipo_contrato " +
            "where es_activo = :activo " +
            "order by cast(tipo_contrato_id as numeric) ", nativeQuery = true)
    List<CsTipoContrato> findByEsActivo(Boolean activo);

    @Query(value = "SELECT * FROM cs_tipo_contrato " +
            "where es_activo = :activo " +
            "order by cast(tipo_contrato_id as numeric) ", nativeQuery = true)
    List<CsTipoContrato> findByEsActivoNoConcat(Boolean activo);


    @Query(value = "SELECT * FROM cs_tipo_contrato where (tipo_contrato_id = :id or upper(descripcion) = :descipcion)", nativeQuery = true)
    List<CsTipoContrato> findByIdDescripcionRepetido(String id, String descipcion);

    List<CsTipoContrato> findByDescripcion(String descripcion);

    List<CsTipoContrato> findByDescripcionIlike(String descripcion);

}