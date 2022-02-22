package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.dto.RespuestaGoogleStorage;
import mx.com.ga.cosmonaut.common.exception.ServiceException;

import java.io.IOException;

public interface GoogleStorageService {

    void inicializar() throws ServiceException, IOException;

    RespuestaGoogleStorage subirArchivo(byte[] archivo, String ruta) throws ServiceException;

    RespuestaGoogleStorage actualizarArchivo(byte[] archivo, String ruta) throws ServiceException;

    RespuestaGoogleStorage obtenerArchivo(String objectId) throws ServiceException;

}
