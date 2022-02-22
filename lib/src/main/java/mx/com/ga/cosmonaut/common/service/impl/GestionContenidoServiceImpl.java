package mx.com.ga.cosmonaut.common.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Value;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.cms.Datos;
import mx.com.ga.cosmonaut.common.dto.cms.Entradas;
import mx.com.ga.cosmonaut.common.dto.cms.RespuestaEstatus;
import mx.com.ga.cosmonaut.common.dto.cms.RespuestaGestionContenido;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.service.GestionContenidoService;
import mx.com.ga.cosmonaut.common.util.Cliente;
import mx.com.ga.cosmonaut.common.util.Constantes;
import okhttp3.*;
import org.json.JSONObject;

import javax.inject.Singleton;
import java.util.Objects;

@Singleton
public class GestionContenidoServiceImpl implements GestionContenidoService {

    private static final String CODIGO = "WSE000";

    @Value("${servicio.cms.host}")
    private String host;

    @Value("${servicio.cms.catalogo.multimedios.obtener.path}")
    private String contextoObtenerMultimedios;

    @Value("${servicio.cms.catalogo.tipo-documentos.obtener.path}")
    private String contextoObtenerTipoDocumento;

    @Value("${servicio.cms.catalogo.tipo-documentos.guardar.path}")
    private String contextoGuardarTipoDocumento;

    @Value("${servicio.cms.catalogo.tipo-documentos.eliminar.path}")
    private String contextoEliminarTipoDocumento;

    @Value("${servicio.cms.expediente.guardar.path}")
    private String contextoGuardarExpediente;

    @Value("${servicio.cms.expediente.obtener.path}")
    private String contextoObtenerExpediente;

    @Value("${servicio.cms.documentos.subir.path}")
    private String contextoGuardarDocumento;

    @Value("${servicio.cms.documentos.versionar.path}")
    private String contextoVersionarDocumento;

    @Value("${servicio.cms.documentos.remplazar.path}")
    private String contextoRemplazarDocumento;

    @Value("${servicio.cms.documentos.descargar.path}")
    private String contextoDescargarDocumento;

    @Value("${servicio.cms.documentos.eliminar.path}")
    private String contextoEliminarDocumento;

    @Override
    public RespuestaGenerica obtenerCatalogoMultimedios() throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoObtenerMultimedios,"","GET"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " obtenerCatalogoMultimedios " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica obtenerCatalogoTipoDocumento() throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoObtenerTipoDocumento,"","GET"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " obtenerCatalogoTipoDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica guardarCatalogoTipoDocumento(Entradas entradas) throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoGuardarTipoDocumento,entradas,"POST"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " guardarCatalogoTipoDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica eliminarCatalogoTipoDocumento(Entradas entradas) throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoEliminarTipoDocumento,entradas,"POST"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " eliminarCatalogoTipoDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica guardarExpediente(Datos datos) throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoGuardarExpediente,datos,"POST"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " guardarExpediente " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica obtenerExpediente(String clave) throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoObtenerExpediente + clave,"","GET"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " obtenerExpediente " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica guardarDocumento(Entradas entrada) throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoGuardarDocumento,entrada,"POST"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " guardarDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica versionarDocumento(Entradas entrada) throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoVersionarDocumento,entrada,"POST"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " versionarDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica remplazarDocumento(Entradas entrada) throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoRemplazarDocumento,entrada,"POST"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " remplazarDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica obtenerDocumento(Integer documentoId) throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoDescargarDocumento + documentoId,"","GET"));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " obtenerDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica eliminarDocumento(Integer documentoId) throws ServiceException {
        try{
            return this.generaRespuesta(
                    clienteGestorContenido(host + contextoEliminarDocumento + documentoId,"",""));
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " eliminarDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private RespuestaGenerica generaRespuesta(RespuestaGestionContenido respuestaGestionContenido) throws ServiceException {
        try{
            if (respuestaGestionContenido.getResponseStatus().getCode().equals(CODIGO)){
                return new RespuestaGenerica(respuestaGestionContenido.getData(), Constantes.RESULTADO_EXITO,Constantes.EXITO);
            }else {
                return new RespuestaGenerica(respuestaGestionContenido.getData(), Constantes.RESULTADO_ERROR, respuestaGestionContenido.getResponseStatus().getDesc());
            }
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " generaRespuesta " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private RespuestaGestionContenido clienteGestorContenido(String url, Object objetoPeticion, String tipo)
            throws ServiceException {
        try{
            final MediaType media = MediaType.get("application/json; charset=utf-8");
            OkHttpClient cliente = Cliente.obtenOkHttpCliente();
            cliente.sslSocketFactory();
            JSONObject json = new JSONObject(objetoPeticion);
            RequestBody peticion = RequestBody.create(json.toString(),media);

            Request request;
            switch (tipo){
                case "GET":
                    request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                    break;
                case "POST":
                    request = new Request.Builder()
                            .url(url)
                            .post(peticion)
                            .build();
                    break;
                default:
                    request = new Request.Builder()
                            .url(url)
                            .delete(peticion)
                            .build();
                    break;
            }

            Call llamada = cliente.newCall(request);
            Response respuesta = llamada.execute();
            if (respuesta.code() == 200 || respuesta.code() == 500){
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(Objects.requireNonNull(respuesta.body()).string(), RespuestaGestionContenido.class);
            }
            RespuestaGestionContenido respuestaGestionContenido = new RespuestaGestionContenido();
            respuestaGestionContenido.setResponseStatus(new RespuestaEstatus());
            respuestaGestionContenido.getResponseStatus().setCode("ERROR");
            return respuestaGestionContenido;

        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " clienteGestorContenido " + Constantes.ERROR_EXCEPCION, e);
        }
    }
}
