package mx.com.ga.cosmonaut.common.dto;

import lombok.Data;

import java.net.URL;

@Data
public class RespuestaGoogleStorage {

    private String mensaje;
    private String objectId;
    private String bucket;
    private URL url;
    private boolean respuesta;
    private byte[] arreglo;

}
