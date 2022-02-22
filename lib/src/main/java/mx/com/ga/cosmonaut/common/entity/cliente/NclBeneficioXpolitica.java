package mx.com.ga.cosmonaut.common.entity.cliente;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@MappedEntity(value = "ncl_beneficio_xpolitica")
public class NclBeneficioXpolitica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value="beneficio_xpolitica_id")
    private Integer beneficioPolitica;
    @MappedProperty(value = "anios_antiguedad")
    private Short aniosAntiguedad;
    @MappedProperty(value = "dias_aguinaldo")
    private Short diasAguinaldo;
    @MappedProperty(value = "dias_vacaciones")
    private Short diasVacaciones;
    @MappedProperty(value = "prima_vacacional")
    private BigDecimal primaVacacional;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="politica_id")
    @MappedProperty(value = "politica_id")
    private NclPolitica nclPolitica;
    
    
}
