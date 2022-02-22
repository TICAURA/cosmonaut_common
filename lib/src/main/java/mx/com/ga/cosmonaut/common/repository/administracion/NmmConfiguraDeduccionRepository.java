package mx.com.ga.cosmonaut.common.repository.administracion;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import mx.com.ga.cosmonaut.common.entity.administracion.NmmConfiguraDeduccion;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NmmConfiguraDeduccionRepository extends CrudRepository<NmmConfiguraDeduccion, Integer> {

     List<NmmConfiguraDeduccion> findByConceptoDeduccionIdConceptoDeduccionId(Integer conceptoDeduccionId);
    
    void update(@Id Integer configuraDeduccionId, boolean esActivo);
            
    List<NmmConfiguraDeduccion> findByConceptoDeduccionIdConceptoDeduccionIdAndPersonaIdPersonaId(Integer conceptoDeduccionId, Integer personaId);
    
    List<NmmConfiguraDeduccion> findByTipoDeduccionIdTipoDeduccionIdAndPersonaIdPersonaId(String tipoDeduccionId, Integer personaId);
    
    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoDeduccionId", alias = "cs")
    @Join(value = "conceptoDeduccionId", alias = "nmmd")
    @Join(value = "personaId", alias = "ncp")
    List<NmmConfiguraDeduccion> findByPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteId(Integer personaId, Integer clienteId);
    
    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoDeduccionId", alias = "cs")
    @Join(value = "conceptoDeduccionId", alias = "nmmd")
    @Join(value = "politicaId", alias = "nclp")
    List<NmmConfiguraDeduccion> findByPoliticaIdPoliticaIdAndCentrocClienteIdCentrocClienteId(Integer politicaId, Integer clienteId);

    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoDeduccionId", alias = "cs")
    @Join(value = "conceptoDeduccionId", alias = "nmmd")
    @Join(value = "personaId", alias = "ncp")
    @Join(value = "baseCalculoId", alias = "baseCalculoId")
    List<NmmConfiguraDeduccion> findByPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteIdAndEsActivo(Integer personaId, Integer clienteId, boolean esActivo);

    @Join(value = "centrocClienteId", alias = "nclc")
    @Join(value = "tipoDeduccionId", alias = "cs")
    @Join(value = "conceptoDeduccionId", alias = "nmmd")
    @Join(value = "politicaId", alias = "nclp")
    @Join(value = "baseCalculoId", alias = "baseCalculoId")
    List<NmmConfiguraDeduccion> findByPoliticaIdPoliticaIdAndCentrocClienteIdCentrocClienteIdAndEsActivo(Integer politicaId, Integer clienteId,boolean esActivo);

    List<NmmConfiguraDeduccion> findByConceptoDeduccionIdConceptoDeduccionIdAndPoliticaIdPoliticaId(Integer conceptoDeduccionId, Integer politicaId);

    List<NmmConfiguraDeduccion> findByFechaContratoAndPersonaIdPersonaIdAndCentrocClienteIdCentrocClienteIdAndTipoDeduccionIdTipoDeduccionId(Date fechaContrato, Integer personaId, Integer centrocClienteId, String tipoDeduccionId);

    boolean existsByTipoDeduccionIdTipoDeduccionIdAndEspecializacionAndConceptoDeduccionIdConceptoDeduccionId(String tipoDeduccionId, String especializacion, Integer conceptoDeduccionId);
}