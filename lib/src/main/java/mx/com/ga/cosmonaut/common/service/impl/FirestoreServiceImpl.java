package mx.com.ga.cosmonaut.common.service.impl;

import com.google.api.core.ApiFuture;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import mx.com.ga.cosmonaut.common.dto.FirestoreConfiguracionDto;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.RefreshTokenEntity;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.service.FirestoreService;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Singleton
public class FirestoreServiceImpl implements FirestoreService {

    private Firestore firestore = null;

    private FirestoreConfiguracionDto firestoreConfiguracionDto;

    @Override
    @PostConstruct
    public void inicializar() throws ServiceException {
        Credentials credenciales;
        try {
            firestoreConfiguracionDto = new FirestoreConfiguracionDto();
            firestoreConfiguracionDto.setIdProyecto(Constantes.GOOGLE_STORAGE_ID_PROYECTO);
            firestoreConfiguracionDto.setNameCollection(Constantes.GOOGLE_FIRESTORE_COLLECTION);
            firestoreConfiguracionDto.setRutaJson(Constantes.GOOGLE_STORAGE_JSON);
            credenciales = GoogleCredentials.fromStream(this.getClass().getResourceAsStream(firestoreConfiguracionDto.getRutaJson()));
            firestore = FirestoreOptions.newBuilder().setCredentials(credenciales)
                    .setProjectId(firestoreConfiguracionDto.getIdProyecto()).build().getService();
        } catch (IOException e) {
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Override
    public void guardar(RefreshTokenEntity tokenEntity) throws InterruptedException, ExecutionException {
        DocumentReference docRef = firestore.collection(firestoreConfiguracionDto.getNameCollection())
                .document(tokenEntity.getUsername());

        ApiFuture<WriteResult> result = docRef.create(tokenEntity);
        result.get();
    }

    @Override
    public RefreshTokenEntity obtener(String username) {
        try {
            DocumentReference docRef = firestore.collection(firestoreConfiguracionDto.getNameCollection())
                    .document(username);

            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot doc = future.get();
            if (doc.exists()) {
                return doc.toObject(RefreshTokenEntity.class);
            } else {
                return null;
            }
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    @Override
    public RefreshTokenEntity buscar(String refresh_token) {
        try {
            CollectionReference colRef = firestore.collection(firestoreConfiguracionDto.getNameCollection());

            Query query = colRef.whereEqualTo("refresh_token", refresh_token);

            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot result = future.get();

            if (!result.isEmpty() && !result.getDocuments().isEmpty()) {
                DocumentSnapshot doc = future.get().getDocuments().get(0);
                return doc.toObject(RefreshTokenEntity.class);
            } else {
                return null;
            }
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    @Override
    public void update(String username, String refresh_token) throws InterruptedException, ExecutionException {
        DocumentReference docRef = firestore.collection(firestoreConfiguracionDto.getNameCollection())
                .document(username);

        ApiFuture<WriteResult> result = docRef.update("refresh_token", refresh_token);
        result.get();
    }

    @Override
    public void borrar(String username) throws InterruptedException, ExecutionException {
        DocumentReference docRef = firestore.collection(firestoreConfiguracionDto.getNameCollection())
                .document(username);

        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }

}
