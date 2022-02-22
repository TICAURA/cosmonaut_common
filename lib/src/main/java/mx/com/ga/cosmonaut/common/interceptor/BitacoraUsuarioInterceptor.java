package mx.com.ga.cosmonaut.common.interceptor;

import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.http.HttpResponse;
import lombok.SneakyThrows;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmBitacoraCosmonaut;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.service.AdmBitacoraCosmonautService;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BitacoraUsuarioInterceptor implements MethodInterceptor<Object,Object> {

    @Inject
    private AdmBitacoraCosmonautService admBitacoraCosmonautService;

    @SneakyThrows
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        try{
            HttpResponse<RespuestaGenerica> httRespuesta = (HttpResponse<RespuestaGenerica>) context.proceed();
            RespuestaGenerica respuesta = httRespuesta.getBody().get();
            if (respuesta.isResultado()){

                String datosFlujo = (String) context.getParameterValues()[0];
                String datosSesion = (String) context.getParameterValues()[1];

                int b = datosSesion.indexOf("&");
                int c = datosSesion.indexOf("=");
                int d = datosSesion.lastIndexOf("=");
                String centroClienteId = datosSesion.substring(c + 1, b);
                String usuarioId = datosSesion.substring(d + 1);

                String metodo = context.getExecutableMethod().getMethodName();
                AdmBitacoraCosmonaut bitacoraCosmonaut = new AdmBitacoraCosmonaut();
                bitacoraCosmonaut.setUsuarioId(new AdmUsuarios());
                bitacoraCosmonaut.getUsuarioId().setUsuarioId(Integer.valueOf(usuarioId));
                bitacoraCosmonaut.setAccion(metodo);
                bitacoraCosmonaut.setModulo(datosFlujo);
                bitacoraCosmonaut.setCentroClienteId(Integer.valueOf(centroClienteId));
                admBitacoraCosmonautService.guardar(bitacoraCosmonaut);
            }
            return httRespuesta;
        }catch (ServiceException e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" intercept " + Constantes.ERROR_EXCEPCION, e);
        }
    }
}
