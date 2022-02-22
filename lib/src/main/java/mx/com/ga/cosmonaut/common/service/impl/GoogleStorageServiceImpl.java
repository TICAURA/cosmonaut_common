package mx.com.ga.cosmonaut.common.service.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.StorageOptions;
import mx.com.ga.cosmonaut.common.dto.GoogleStorageConfiguracionDto;
import mx.com.ga.cosmonaut.common.dto.RespuestaGoogleStorage;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.service.GoogleStorageService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import mx.com.ga.cosmonaut.common.util.Constantes;

@Singleton
public class GoogleStorageServiceImpl implements GoogleStorageService {

    private Storage storage = null;

    private GoogleStorageConfiguracionDto googleStorageConfiguracionDto;

    @Override
    @PostConstruct
    public void inicializar() throws ServiceException {
        Credentials credenciales;
        try {
            googleStorageConfiguracionDto = new GoogleStorageConfiguracionDto();
            googleStorageConfiguracionDto.setIdProyecto(Constantes.GOOGLE_STORAGE_ID_PROYECTO);
            googleStorageConfiguracionDto.setNombreBucket(Constantes.GOOGLE_STORAGE_BUCKET);
            googleStorageConfiguracionDto.setRutaJson(Constantes.GOOGLE_STORAGE_JSON);
            credenciales = GoogleCredentials.fromStream(this.getClass().getResourceAsStream(googleStorageConfiguracionDto.getRutaJson()));
            storage = StorageOptions.newBuilder().setCredentials(credenciales).setProjectId(googleStorageConfiguracionDto.getIdProyecto())
                    .build().getService();
        } catch (IOException e) {
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Override
    public RespuestaGoogleStorage subirArchivo(byte[] archivo, String ruta) throws ServiceException {
        try {
            Bucket bucket = storage.get(googleStorageConfiguracionDto.getNombreBucket());
            RespuestaGoogleStorage respuestaGoogleStorage = new RespuestaGoogleStorage();
            Blob resultado = bucket.create(ruta, archivo);
            respuestaGoogleStorage.setRespuesta(Constantes.RESULTADO_EXITO);
            respuestaGoogleStorage.setMensaje(Constantes.EXITO);
            respuestaGoogleStorage.setObjectId(resultado.getGeneratedId());
            respuestaGoogleStorage.setArreglo(resultado.getContent());
            URL url = resultado.signUrl(Constantes.DURACION_URL, TimeUnit.MINUTES);
            respuestaGoogleStorage.setUrl(url);
            respuestaGoogleStorage.setBucket(resultado.getBucket());
            return respuestaGoogleStorage;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Override
    public RespuestaGoogleStorage obtenerArchivo(String objectId) throws ServiceException {
        try{
            Blob blob = storage.get(googleStorageConfiguracionDto.getNombreBucket(), objectId);
            URL url = blob.signUrl(Constantes.DURACION_URL, TimeUnit.MINUTES);
            RespuestaGoogleStorage respuestaGoogleStorage = new RespuestaGoogleStorage();
            if (blob.exists()) {
                byte[] archivo = leerBlob(blob);
                respuestaGoogleStorage.setArreglo(archivo);
                respuestaGoogleStorage.setUrl(url);
                respuestaGoogleStorage.setObjectId(blob.getGeneratedId());
                respuestaGoogleStorage.setRespuesta(Constantes.RESULTADO_EXITO);
                respuestaGoogleStorage.setMensaje(Constantes.EXITO);
            } else {
                respuestaGoogleStorage.setArreglo(null);
                respuestaGoogleStorage.setRespuesta(Constantes.RESULTADO_ERROR);
                respuestaGoogleStorage.setMensaje(Constantes.ERROR);
            }
            return respuestaGoogleStorage;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Override
    public RespuestaGoogleStorage actualizarArchivo(byte[] archivo, String objectId) throws ServiceException {
        try {
            Bucket bucket = storage.get(googleStorageConfiguracionDto.getNombreBucket());
            RespuestaGoogleStorage respuestaGoogleStorage = new RespuestaGoogleStorage();
            Blob resultado;
            if (archivo != null) {
                resultado = bucket.create(objectId,archivo);
            } else {
                throw new ServiceException(Constantes.ERROR);
            }
            respuestaGoogleStorage.setRespuesta(Constantes.RESULTADO_EXITO);
            respuestaGoogleStorage.setMensaje(Constantes.EXITO);
            respuestaGoogleStorage.setArreglo(resultado.getContent());
            respuestaGoogleStorage.setObjectId(resultado.getGeneratedId());
            return respuestaGoogleStorage;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR);
        }
    }

    private byte[] leerBlob(Blob blob) throws ServiceException {
        try (ReadChannel reader = blob.reader()) {
            ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try (output; WritableByteChannel outChannel = Channels.newChannel(output)) {
                while (reader.read(bytes) > 0) {
                    bytes.flip();
                    outChannel.write(bytes);
                    bytes.clear();
                }
            } catch (IOException e) {
                throw new ServiceException(Constantes.ERROR);
            }
            return output.toByteArray();
        }
    }
}
