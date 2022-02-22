package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.cms.Datos;
import mx.com.ga.cosmonaut.common.dto.cms.Entradas;
import mx.com.ga.cosmonaut.common.exception.ServiceException;

public interface GestionContenidoService {

    RespuestaGenerica obtenerCatalogoMultimedios() throws ServiceException;

    RespuestaGenerica obtenerCatalogoTipoDocumento() throws ServiceException;

    RespuestaGenerica guardarCatalogoTipoDocumento(Entradas entradas) throws ServiceException;

    RespuestaGenerica eliminarCatalogoTipoDocumento(Entradas entradas) throws ServiceException;

    RespuestaGenerica guardarExpediente(Datos datos) throws ServiceException;

    RespuestaGenerica obtenerExpediente(String clave) throws ServiceException;

    RespuestaGenerica guardarDocumento(Entradas entrada) throws ServiceException;

    RespuestaGenerica versionarDocumento(Entradas entrada) throws ServiceException;

    RespuestaGenerica remplazarDocumento(Entradas entrada) throws ServiceException;

    RespuestaGenerica obtenerDocumento(Integer documentoId) throws ServiceException;

    RespuestaGenerica eliminarDocumento(Integer documentoId) throws ServiceException;

}
