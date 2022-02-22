package mx.com.ga.cosmonaut.common.service.impl;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoKardexColaborador;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.repository.colaborador.NcoKardexColaboradorRepository;
import mx.com.ga.cosmonaut.common.repository.nativo.KardexRepository;
import mx.com.ga.cosmonaut.common.service.KardexColaboradorService;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class KardexColaboradorServiceImpl implements KardexColaboradorService {

    @Inject
    private NcoKardexColaboradorRepository ncoKardexColaboradorRepository;

    @Inject
    private KardexRepository kardexRepository;

    @Override
    public RespuestaGenerica guardar(NcoKardexColaborador kardexColaborador) throws ServiceException {
        try{
            RespuestaGenerica respuesta = validarCamposObligatorios(kardexColaborador);
            if (respuesta.isResultado()){
                respuesta.setDatos(ncoKardexColaboradorRepository.save(kardexColaborador));
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" guardar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica listar(NcoKardexColaborador kardexColaborador) throws ServiceException {
        try{
            RespuestaGenerica respuesta = validarEmpleado(kardexColaborador);
            if (respuesta.isResultado()){
                return new RespuestaGenerica(kardexRepository.consultaEmpleado(kardexColaborador),
                        Constantes.RESULTADO_EXITO,Constantes.EXITO);
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" listar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica listarTipoMovimiento(NcoKardexColaborador kardexColaborador) throws ServiceException {
        try{
            RespuestaGenerica respuesta = validarCamposObligatorios(kardexColaborador);
            if (respuesta.isResultado()){
                return new RespuestaGenerica(kardexRepository.consultaEmpleado(kardexColaborador)
                        .stream()
                        .filter(kardex -> kardex.getMovimientoId().equals(kardexColaborador.getMovimientoImssId()))
                        .collect(Collectors.toList()),
                        Constantes.RESULTADO_EXITO,Constantes.EXITO);
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" listarTipoMovimiento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private RespuestaGenerica validarCamposObligatorios(NcoKardexColaborador kardexColaborador) throws ServiceException {
        try{
            RespuestaGenerica respuesta =  validarEmpleado(kardexColaborador);
            if(!respuesta.isResultado()
                    || kardexColaborador.getMovimientoImssId() == null){
                respuesta.setResultado(Constantes.RESULTADO_ERROR);
                respuesta.setMensaje(Constantes.CAMPOS_REQUERIDOS);
            }else{
                respuesta.setResultado(Constantes.RESULTADO_EXITO);
                respuesta.setMensaje(Constantes.EXITO);
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" validarCamposObligatorios " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private RespuestaGenerica validarEmpleado(NcoKardexColaborador kardexColaborador) throws ServiceException {
        try{
            RespuestaGenerica respuesta =  new RespuestaGenerica();
            if(kardexColaborador.getCentrocClienteId() == null
                    || kardexColaborador.getCentrocClienteId().getCentrocClienteId() == null
                    || kardexColaborador.getPersonaId() == null
                    || kardexColaborador.getPersonaId().getPersonaId() == null
                    || kardexColaborador.getFechaContrato() == null){
                respuesta.setResultado(Constantes.RESULTADO_ERROR);
                respuesta.setMensaje(Constantes.CAMPOS_REQUERIDOS);
            }else{
                respuesta.setResultado(Constantes.RESULTADO_EXITO);
                respuesta.setMensaje(Constantes.EXITO);
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" validarEmpleado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
