package mx.com.ga.cosmonaut.common.service.impl;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoHistoricoCompensacion;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.repository.colaborador.NcoHistoricoCompensacionRepository;
import mx.com.ga.cosmonaut.common.service.HistoricoCompensacionService;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HistoricoCompensacionServiceImpl implements HistoricoCompensacionService {

    @Inject
    private NcoHistoricoCompensacionRepository ncoHistoricoCompensacionRepository;

    @Override
    public RespuestaGenerica guardar(NcoHistoricoCompensacion ncoHistoricoCompensacion) throws ServiceException {
        try{
            RespuestaGenerica respuesta = validarCamposObligatorios(ncoHistoricoCompensacion);
            if (respuesta.isResultado()){
                ncoHistoricoCompensacion.setEstatusEmpleado(Constantes.ESTATUS_ACTIVO);
                respuesta.setDatos(ncoHistoricoCompensacionRepository.save(ncoHistoricoCompensacion));
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" guardar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private RespuestaGenerica validarCamposObligatorios(NcoHistoricoCompensacion ncoHistoricoCompensacion) throws ServiceException {
        try{
            RespuestaGenerica respuesta =  new RespuestaGenerica();
            if(ncoHistoricoCompensacion.getCentrocClienteId() == null
                || ncoHistoricoCompensacion.getCentrocClienteId().getCentrocClienteId() == null
                    || ncoHistoricoCompensacion.getPersonaId() == null
                    || ncoHistoricoCompensacion.getPersonaId().getPersonaId() == null
                    || ncoHistoricoCompensacion.getFechaContrato() == null
                    || ncoHistoricoCompensacion.getGrupoNominaId() == null
                    || ncoHistoricoCompensacion.getGrupoNominaId().getGrupoNominaId() == null
                    || ncoHistoricoCompensacion.getPoliticaId() == null
                    || ncoHistoricoCompensacion.getPoliticaId().getPoliticaId() == null){
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

}
