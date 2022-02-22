package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoHistoricoCompensacion;
import mx.com.ga.cosmonaut.common.exception.ServiceException;

public interface HistoricoCompensacionService {

    RespuestaGenerica guardar(NcoHistoricoCompensacion ncoHistoricoCompensacion) throws ServiceException;

}
