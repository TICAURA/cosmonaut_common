package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.PagosLiquidacionColaborador;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.*;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoContrato;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoRegimenContratacion;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatAreaGeografica;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstado;
import mx.com.ga.cosmonaut.common.entity.cliente.*;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class NcoContratoColaboradorDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaContrato;
    private BigDecimal salarioDiario;
    private Timestamp fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaFin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAntiguedad;
    private boolean esSindicalizado;
    private boolean esSubcontratado;
    private BigDecimal porcentaje;
    private BigDecimal sueldoBrutoMensual;
    private String numEmpleado;
    private BigDecimal sbc;
    private Short diasVacaciones;
    private String fechaParaCalculo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ultimoDia;
    private String notas;
    private Boolean esActivo;
    private String tipoJornadaId;
    private BigDecimal sueldoNetoMensual;
    private String subcontratista;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAltaImss;
    private BigDecimal salarioDiarioIntegrado;
    private BigDecimal pppSalarioBaseMensual;
    private BigDecimal pppMontoComplementario;
    private BigDecimal pppSnm;
    private Integer estatusBajaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaFinUltimoPago;

    private CsTipoRegimenContratacion tipoRegimenContratacionId;
    private CatAreaGeografica areaGeograficaId;
    private CatEstado estadoId;
    private CatMetodoPago metodoPagoId;
    private CatMotivoBaja motivoBajaId;
    private CatSubcontratista subcontratistaId;
    private NclPuesto puestoId;
    private NclCentrocCliente centrocClienteId;
    private NclJornada jornadaId;
    private NclPolitica politicaId;
    private NclGrupoNomina grupoNominaId;
    private CatTipoBaja tipoBajaId;
    private CatTipoCompensacion tipoCompensacionId;
    private NclSede sedeId;
    private CsTipoContrato tipoContratoId;
    private NclArea areaId;
    private NcoPersona personaId;
    private NcoPersona jefeInmediatoId;
    private List<PagosLiquidacionColaborador> pagosLiquidacionColaborador;
    private URL url;
}
