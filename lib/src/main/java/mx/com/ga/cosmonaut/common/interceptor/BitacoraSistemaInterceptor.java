package mx.com.ga.cosmonaut.common.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.type.MutableArgumentValue;
import io.micronaut.http.HttpResponse;
import lombok.SneakyThrows;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.entity.BitacoraSistema;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmBitacoraCosmonaut;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.repository.BitacoraSistemaRepository;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Optional;

@Singleton
public class BitacoraSistemaInterceptor implements MethodInterceptor<Object,Object> {

    @Inject
    private BitacoraSistemaRepository bitacoraSistemaRepository;

    @SneakyThrows
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        try{
            HttpResponse<RespuestaGenerica> httRespuesta = (HttpResponse<RespuestaGenerica>) context.proceed();
            RespuestaGenerica respuesta = httRespuesta.getBody().get();
            if (respuesta.isResultado()){

                String datosFlujo = (String) context.getParameterValues()[0];
                String datosSesion = (String) context.getParameterValues()[1];
                Object request = context.getParameterValues()[2];

                ObjectMapper mapper = new ObjectMapper();
                String json = "";
                try {
                    json = mapper.writeValueAsString(request);
                    System.out.println("ResultingJSONstring = " + json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                int b = datosSesion.indexOf("&");
                int c = datosSesion.indexOf("=");
                int d = datosSesion.lastIndexOf("=");
                String centroClienteId = datosSesion.substring(c + 1, b);
                String usuarioId = datosSesion.substring(d + 1);

                String metodo = context.getExecutableMethod().getMethodName();
                BitacoraSistema bitacoraSistema = new BitacoraSistema();
                bitacoraSistema.setUsuarioId(new AdmUsuarios());
                bitacoraSistema.getUsuarioId().setUsuarioId(Integer.valueOf(usuarioId));
                bitacoraSistema.setAccion(metodo);
                bitacoraSistema.setModulo(datosFlujo);
                bitacoraSistema.setCentroClienteId(Integer.valueOf(centroClienteId));
                bitacoraSistema.setRequest(json);
                bitacoraSistemaRepository.save(bitacoraSistema);
            }
            return httRespuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" intercept " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
