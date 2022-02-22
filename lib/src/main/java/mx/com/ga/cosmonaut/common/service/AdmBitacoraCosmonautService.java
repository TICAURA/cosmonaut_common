package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.consultas.BitacoraCosmonautConsulta;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmBitacoraCosmonaut;
import mx.com.ga.cosmonaut.common.exception.ServiceException;

public interface AdmBitacoraCosmonautService {

    RespuestaGenerica guardar(AdmBitacoraCosmonaut bitacoraCosmonaut) throws ServiceException;

    RespuestaGenerica listar(BitacoraCosmonautConsulta bitacora) throws ServiceException;

}
