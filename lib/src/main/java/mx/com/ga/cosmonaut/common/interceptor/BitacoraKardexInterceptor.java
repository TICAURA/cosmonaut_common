package mx.com.ga.cosmonaut.common.interceptor;

import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.http.HttpResponse;
import lombok.SneakyThrows;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmBitacoraCosmonaut;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoJornada;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoContratoColaborador;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoHistoricoCompensacion;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoKardexColaborador;
import mx.com.ga.cosmonaut.common.enums.CatMovimientoImss;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.repository.colaborador.NcoContratoColaboradorRepository;
import mx.com.ga.cosmonaut.common.service.AdmBitacoraCosmonautService;
import mx.com.ga.cosmonaut.common.service.HistoricoCompensacionService;
import mx.com.ga.cosmonaut.common.service.KardexColaboradorService;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.ObjetoMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BitacoraKardexInterceptor implements MethodInterceptor<Object,Object> {

    @Inject
    private KardexColaboradorService kardexColaboradorService;

    @Inject
    private HistoricoCompensacionService historicoCompensacionService;

    @Inject
    private AdmBitacoraCosmonautService admBitacoraCosmonautService;

    @SneakyThrows
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        try {
            HttpResponse<RespuestaGenerica> httRespuesta = (HttpResponse<RespuestaGenerica>) context.proceed();
            RespuestaGenerica respuesta = httRespuesta.getBody().get();
            if (respuesta.isResultado()) {

                String datosFlujo = (String) context.getParameterValues()[0];
                String datosSesion = (String) context.getParameterValues()[1];

                int b = datosSesion.indexOf("&");
                int c = datosSesion.indexOf("=");
                int d = datosSesion.lastIndexOf("=");
                String centroClienteId = datosSesion.substring(c + 1, b);
                String usuarioId = datosSesion.substring(d + 1);

                String metodo = context.getExecutableMethod().getMethodName();
                NcoContratoColaborador colaborador = ObjetoMapper.map(respuesta.getDatos(),NcoContratoColaborador.class);

                NcoKardexColaborador kardexColaborador = new NcoKardexColaborador();
                kardexColaborador.setCentrocClienteId(colaborador.getCentrocClienteId());
                kardexColaborador.setPersonaId(colaborador.getPersonaId());
                kardexColaborador.setFechaContrato(colaborador.getFechaContrato());

                if (CatMovimientoImss.GUARDAR.getDescripcion().equals(metodo)){
                    kardexColaborador.setMovimientoImssId(CatMovimientoImss.GUARDAR.getId());
                }else if (CatMovimientoImss.MODIFICAR.getDescripcion().equals(metodo)){
                    kardexColaborador.setMovimientoImssId(CatMovimientoImss.MODIFICAR.getId());
                }else if (CatMovimientoImss.BAJA.getDescripcion().equals(metodo)){
                    kardexColaborador.setMovimientoImssId(CatMovimientoImss.BAJA.getId());
                }else if (CatMovimientoImss.GUARDAR_REACTIVAR.getDescripcion().equals(metodo)){
                    kardexColaborador.setMovimientoImssId(CatMovimientoImss.GUARDAR.getId());
                }

                NcoHistoricoCompensacion historicoCompensacion = new NcoHistoricoCompensacion();
                historicoCompensacion.setCentrocClienteId(colaborador.getCentrocClienteId());
                historicoCompensacion.setPersonaId(colaborador.getPersonaId());
                historicoCompensacion.setFechaContrato(colaborador.getFechaContrato());
                historicoCompensacion.setGrupoNominaId(colaborador.getGrupoNominaId());
                historicoCompensacion.setPoliticaId(colaborador.getPoliticaId());
                historicoCompensacion.setSalarioDiario(colaborador.getSalarioDiario());
                historicoCompensacion.setSalarioDiarioIntegrado(colaborador.getSalarioDiarioIntegrado());
                historicoCompensacion.setSalarioBaseCotizacion(colaborador.getSbc());
                historicoCompensacion.setSalarioNetoMensual(colaborador.getSueldoNetoMensual());
                historicoCompensacion.setSalarioBrutoMensual(colaborador.getSueldoBrutoMensual());
                historicoCompensacion.setSalarioBrutoImss(colaborador.getPppSalarioBaseMensual());
                historicoCompensacion.setNumeroEmpleado(colaborador.getNumEmpleado());
                historicoCompensacion.setTipoCompensacionId(colaborador.getTipoCompensacionId());
                historicoCompensacion.setTipoJornadaId(new CsTipoJornada());
                historicoCompensacion.getTipoJornadaId().setTipoJornadaId(colaborador.getTipoJornadaId());

                AdmBitacoraCosmonaut bitacoraCosmonaut = new AdmBitacoraCosmonaut();
                bitacoraCosmonaut.setUsuarioId(new AdmUsuarios());
                bitacoraCosmonaut.getUsuarioId().setUsuarioId(Integer.valueOf(usuarioId));
                bitacoraCosmonaut.setAccion(metodo);
                bitacoraCosmonaut.setModulo(datosFlujo);
                bitacoraCosmonaut.setCentroClienteId(Integer.valueOf(centroClienteId));

                admBitacoraCosmonautService.guardar(bitacoraCosmonaut);
                RespuestaGenerica respuestaHistorico = historicoCompensacionService.guardar(historicoCompensacion);
                NcoHistoricoCompensacion historicoCompensacionGuardado = (NcoHistoricoCompensacion) respuestaHistorico.getDatos();
                kardexColaborador.setHistoricoCompensacionId(historicoCompensacionGuardado);
                kardexColaborador.setEsActivo(Constantes.ESTATUS_ACTIVO);
                kardexColaboradorService.guardar(kardexColaborador);
            }
            return httRespuesta;
        }catch (ServiceException e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" BitacoraKardexInterceptor " + Constantes.ERROR_EXCEPCION, e);
        }
    }
}
