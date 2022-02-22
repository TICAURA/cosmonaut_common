package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.dto.DocumentosEmpleadoDto;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.exception.ServiceException;

public interface DocumentosEmpleadoService {

    RespuestaGenerica guardar(DocumentosEmpleadoDto documentosEmpleadoDto) throws ServiceException;

    RespuestaGenerica lista(Integer centroClienteId, Integer personaId) throws ServiceException;

    RespuestaGenerica listaTipoDocumento(Integer centroClienteId, Integer personaId, Integer tipoDocumentoId) throws ServiceException;

    RespuestaGenerica descargar(Integer documentoId) throws ServiceException;

    RespuestaGenerica remplazar(DocumentosEmpleadoDto documentosEmpleadoDto) throws ServiceException;

    RespuestaGenerica obtenerTipoDocumentos() throws ServiceException;

    RespuestaGenerica eliminar(Integer documentoId) throws ServiceException;

}
