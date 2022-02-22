package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.RefreshTokenEntity;
import mx.com.ga.cosmonaut.common.exception.ServiceException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface FirestoreService {

    void inicializar() throws ServiceException, IOException;

    void guardar(RefreshTokenEntity tokenEntity) throws InterruptedException, ExecutionException;

    RefreshTokenEntity obtener(String username);

    RefreshTokenEntity buscar(String refresh_token);

    void update(String username, String refresh_token) throws InterruptedException, ExecutionException;

    void borrar(String username) throws InterruptedException, ExecutionException;

}
