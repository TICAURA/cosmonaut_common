package mx.com.ga.cosmonaut.common.util;

import mx.com.ga.cosmonaut.common.exception.ServiceException;
import okhttp3.OkHttpClient;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class Cliente {

    private Cliente() {
        super();
    }

    public static OkHttpClient obtenOkHttpCliente() throws ServiceException {
        try {
            TrustManager[] certificadosConfianza = obtenCertificadoConfianza();
            final SSLContext sslContexto = SSLContext.getInstance("TLSv1.2");
            sslContexto.init(null, certificadosConfianza, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFabrica= sslContexto.getSocketFactory();

            OkHttpClient.Builder constructor = new OkHttpClient.Builder();
            constructor.sslSocketFactory(sslSocketFabrica, (X509TrustManager) certificadosConfianza[0]);
            constructor.hostnameVerifier((requestedHost, remoteServerSession) ->
                    requestedHost.equalsIgnoreCase(remoteServerSession.getPeerHost())).build();

            return constructor.build();
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE
                    + Constantes.ERROR_METODO +" obtenOkHttpCliente " + Constantes.ERROR_EXCEPCION, e);

        }
    }

    private static TrustManager[] obtenCertificadoConfianza(){
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        // Clinete de confianza
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        // Clinete de confianza
                    }
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
    }

}
