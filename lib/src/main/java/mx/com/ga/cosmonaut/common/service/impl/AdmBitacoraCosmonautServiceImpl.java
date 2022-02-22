package mx.com.ga.cosmonaut.common.service.impl;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.consultas.BitacoraCosmonautConsulta;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmBitacoraCosmonaut;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.repository.administracion.usuarios.AdmBitacoraCosmonautRepository;
import mx.com.ga.cosmonaut.common.repository.nativo.BitacoraCosmonautRepository;
import mx.com.ga.cosmonaut.common.service.AdmBitacoraCosmonautService;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AdmBitacoraCosmonautServiceImpl implements AdmBitacoraCosmonautService {

    @Inject
    private AdmBitacoraCosmonautRepository admBitacoraCosmonautRepository;

    @Inject
    private BitacoraCosmonautRepository bitacoraCosmonautRepository;

    @Override
    public RespuestaGenerica guardar(AdmBitacoraCosmonaut bitacoraCosmonaut) throws ServiceException {
        try{
            RespuestaGenerica respuesta = validaCamposObligatorios(bitacoraCosmonaut);
            if (respuesta.isResultado()){
                respuesta.setDatos(admBitacoraCosmonautRepository.save(bitacoraCosmonaut));
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" guardar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica listar(BitacoraCosmonautConsulta bitacora) throws ServiceException {
        try{
            return new RespuestaGenerica(bitacoraCosmonautRepository.consultaDimanica(bitacora), Constantes.RESULTADO_EXITO, Constantes.EXITO);
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" guardar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private RespuestaGenerica validaCamposObligatorios(AdmBitacoraCosmonaut bitacoraCosmonaut) throws ServiceException {
        try{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            if(bitacoraCosmonaut.getUsuarioId() == null
                    || bitacoraCosmonaut.getUsuarioId().getUsuarioId() == null
                    || bitacoraCosmonaut.getAccion() == null
                    || bitacoraCosmonaut.getAccion().isEmpty()){
                respuesta.setResultado(Constantes.RESULTADO_ERROR);
                respuesta.setMensaje(Constantes.CAMPOS_REQUERIDOS);
            }else {
                respuesta.setResultado(Constantes.RESULTADO_EXITO);
                respuesta.setMensaje(Constantes.EXITO);
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" validaCamposObligatorios " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
