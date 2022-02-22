package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.dto.IdseModel;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.consultas.IdseConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface IdseService {

    RespuestaGenerica idseConsulta(IdseModel idseModel, Boolean esPadre) throws ServiceException;

    ByteArrayOutputStream escribirArchivoIdseConsulta(List<IdseConsulta> listaIdseAltasReingresos) throws ServiceException;
}
