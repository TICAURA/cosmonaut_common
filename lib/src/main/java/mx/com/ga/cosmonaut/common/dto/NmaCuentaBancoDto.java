package mx.com.ga.cosmonaut.common.dto;

import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatFuncionCuenta;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

@Data
public class NmaCuentaBancoDto {

    private static final long serialVersionUID = 1L;
    private Integer cuentaBancoId;
    private String clabe;
    private String numeroCuenta;
    private String nombreCuenta;
    private String numSucursal;
    private String numInformacion;
    private String descripcion;
    private Long bin;
    private Boolean esActivo;
    private Boolean usaStp;
    private String cuentaStp;
    private String clabeStp;
    private NcoPersona ncoPersona;
    private CsBancoDto bancoId ;
    private NclCentrocClienteDto nclCentrocCliente;
    private CatFuncionCuenta funcionCuentaId;

}
