package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import java.util.List;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatCalculoAntiguedad;
import mx.com.ga.cosmonaut.common.entity.cliente.BeneficioXpolitica;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

@Data
@Introspected
public class NclPoliticaDto {

    private Integer politicaId;    
    private String nombre;
    private String nombreCorto;
    private Short diasEconomicos;
    private Boolean primaAniversario;
    private Boolean descuentaFaltas;
    private Boolean descuentaIncapacidades;
    private Boolean costoValesRestaurante;
    private boolean descuentoPropDia;
    private boolean esActivo;
    private NclCentrocCliente centrocClienteId;
    private String calculoAntiguedadx;
    private CatCalculoAntiguedad calculoAntiguedadId;
    private boolean esEstandar;
    private List<BeneficioXpolitica> beneficiosXPolitica;
}
