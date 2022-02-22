package mx.com.ga.cosmonaut.common.dto;

import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaCuentaBanco;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatBasePeriodo;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatEsquemaPago;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatMoneda;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatPeriodoAguinaldo;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsPeriodicidadPago;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

@Data
public class NclGrupoNominaDto {

    private Integer grupoNominaId;
    private Integer numeroEmpleado;

    private String nombre;
    private String nombreCorto;
    private String maneraCalcularSubsidio;

    private boolean esIsrAguinaldoReglamento;
    private boolean esAutomatica;
    private boolean esActivo;
    private boolean esPredeterminado;
    private boolean esAjusteMensualIsr;
    private boolean pagoComplementario;
    private boolean ajustarBaseGravableFaltantes;

    private CatBasePeriodo basePeriodoId;
    private CatEsquemaPago esquemaPagoId;
    private CatMoneda monedaId;
    private CatPeriodoAguinaldo periodoAguinaldoId;
    private CsPeriodicidadPago periodicidadPagoId;
    private NclCentrocCliente centrocClienteId;
    private NmaCuentaBanco clabe;
    private NmaCuentaBanco cuentaBancoId;

}