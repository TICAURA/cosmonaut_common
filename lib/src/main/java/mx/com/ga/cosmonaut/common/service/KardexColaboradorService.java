package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoKardexColaborador;
import mx.com.ga.cosmonaut.common.exception.ServiceException;

public interface KardexColaboradorService {

    RespuestaGenerica guardar(NcoKardexColaborador kardexColaborador) throws ServiceException;

    RespuestaGenerica listar(NcoKardexColaborador kardexColaborador) throws ServiceException;

    RespuestaGenerica listarTipoMovimiento(NcoKardexColaborador kardexColaborador) throws ServiceException;

}
