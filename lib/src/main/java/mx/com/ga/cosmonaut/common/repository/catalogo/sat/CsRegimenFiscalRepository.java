package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsRegimenFiscal;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsRegimenFiscalRepository extends CrudRepository<CsRegimenFiscal, String> {

    @Query(value = "select crf.c_regimenfiscal, " +
            "concat(crf.c_regimenfiscal , '-', crf.descripcion) as descripcion , " +
            "crf.es_activo , crf.fec_fin , " +
            "crf.fec_inicio , crf.ind_persona_fisica , crf.ind_persona_moral " +
            "from cs_regimen_fiscal crf " +
            "where crf.es_activo = :activo " +
            "order by crf.c_regimenfiscal ", nativeQuery = true)
    List<CsRegimenFiscal> findByActivo(Boolean activo);

    @Query(value = "select crf.* from cs_regimen_fiscal crf " +
            "where crf.es_activo = :activo " +
            "order by crf.c_regimenfiscal ", nativeQuery = true)
    List<CsRegimenFiscal> findByActivoNoConcat(Boolean activo);
    
    List<CsRegimenFiscal> findByDescripcion(String descripcion);

    List<CsRegimenFiscal> findByRegimenfiscalId(String regimenfiscalId);

    List<CsRegimenFiscal> findByDescripcionIlike(String descripcion);

}