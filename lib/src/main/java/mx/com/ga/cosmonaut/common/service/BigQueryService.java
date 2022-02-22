package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.exception.ServiceException;

import java.io.IOException;
import java.util.List;

public interface BigQueryService {

    void inicializar() throws ServiceException, IOException;

    void crearTablas();

    void insertarRegistros();

    List<String> consultaCommits();

}
