package mx.com.ga.cosmonaut.common.service.impl;

import io.micronaut.context.annotation.Value;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.administracion.usuarios.AdminUsuarioCS;
import mx.com.ga.cosmonaut.common.dto.mail.MailObject;
import mx.com.ga.cosmonaut.common.dto.mail.MailResult;
import mx.com.ga.cosmonaut.common.dto.mail.SendGridMailConfig;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmRoles;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarioXcliente;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.repository.administracion.usuarios.AdmUsuarioXclienteRepository;
import mx.com.ga.cosmonaut.common.repository.administracion.usuarios.AdmUsuariosRepository;
import mx.com.ga.cosmonaut.common.repository.cliente.NclCentrocClienteRepository;
import mx.com.ga.cosmonaut.common.service.AdmUsuariosService;
import mx.com.ga.cosmonaut.common.service.MailService;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.MailTemplateUtil;
import mx.com.ga.cosmonaut.common.util.PwdUtil;
import mx.com.ga.cosmonaut.common.util.Utilidades;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;


@Singleton
public class AdmUsuariosServiceImp implements AdmUsuariosService {
    @Inject
    private AdmUsuariosRepository admUsuariosRepository;
    @Inject
    private NclCentrocClienteRepository nclCentrocClienteRepository;
    @Inject
    private AdmUsuarioXclienteRepository admUsuarioXclienteRepository;

    @Inject
    private MailService mailService;


    private String sender = "soporte@cosmonaut.tech";

    private String apikey = "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";

    @Inject
    private MailTemplateUtil mailTemplateUtil;


    @Override
    public boolean existeEmpleadoCorreo(String correo) {
         return admUsuariosRepository.findByEmail(correo).isPresent();
    }

    @Override
    public RespuestaGenerica agregarEmpleado(AdminUsuarioCS usuario) {
        RespuestaGenerica respuesta = new RespuestaGenerica();
        if (existeEmpleadoCorreo(usuario.getEmail())) {
            respuesta.setResultado(Constantes.RESULTADO_ERROR);
            respuesta.setMensaje(Constantes.CORREO_REPETIDO);
            return respuesta;
        }

        String pwd = PwdUtil.generatePwd(8);

        AdmUsuarios empleado = new AdmUsuarios();
        empleado.setNombre(usuario.getNombre());
        empleado.setApellidoPat(usuario.getApellidoPat());
        empleado.setApellidoMat(usuario.getApellidoMat());
        empleado.setEmail(usuario.getEmail());
        AdmRoles rol = new AdmRoles();
        rol.setRolId(2);
        empleado.setPassword(BCrypt.hashpw(pwd, BCrypt.gensalt()));
        empleado.setEsActivo(Constantes.ESTATUS_ACTIVO);
        empleado.setPasswordProvisional(Constantes.ESTATUS_ACTIVO);
        empleado.setFechaUltimoPassword(Utilidades.obtenerFechaSystema());
        empleado.setRolId(rol);


        empleado = admUsuariosRepository.save(empleado);

        Optional<NclCentrocCliente> centrocCliente =
                nclCentrocClienteRepository.findById(usuario.getClienteId());

       if(centrocCliente.isPresent()){
           AdmUsuarioXcliente admUsuarioXcliente = new AdmUsuarioXcliente();
           admUsuarioXcliente.setUsuarioId(empleado);
           admUsuarioXcliente.setCentrocClienteId(centrocCliente.get());
           admUsuarioXcliente.setEsActivo(Constantes.ESTATUS_ACTIVO);

           admUsuarioXclienteRepository.save(admUsuarioXcliente);
       }

        mailService.setConfig(new SendGridMailConfig(apikey, sender));
        MailResult mailResult = mailService.enviarCorreo(generarPwdMail(empleado, pwd));
        System.out.println("MailResult: " +mailResult);

        respuesta.setDatos(empleado);
        respuesta.setResultado(Constantes.RESULTADO_EXITO);
        respuesta.setMensaje(Constantes.EXITO);


        return respuesta;
    }

    @Override
    public List<AdmUsuarios> agregarListaEmpleados(List<AdminUsuarioCS> usuarios) {
        List<AdmUsuarios> lista = new ArrayList<AdmUsuarios>();
        for(AdminUsuarioCS item : usuarios){
            RespuestaGenerica respuestaInsert = agregarEmpleado(item);
            if(respuestaInsert.isResultado()){
                AdmUsuarios usuario = (AdmUsuarios) respuestaInsert.getDatos();
                lista.add(usuario);
            }
        }
        return lista;
    }

    private MailObject generarPwdMail(AdmUsuarios admUsuarios, String pwd) {
        MailObject mailObject = new MailObject();
        mailObject.setTo(new String[]{admUsuarios.getEmail()});
        mailObject.setSubject(Constantes.MAIL_PWD_SUBJECT);

        Map<String, Object> valores = new HashMap<>();
        valores.put("nombre", admUsuarios.getNombre()+" "+admUsuarios.getApellidoPat());
        valores.put("pwd", pwd);
        mailObject.setHtmlContent(mailTemplateUtil.renderHtml(Constantes.MAIL_PWD_TEMPLATE, valores));

        return mailObject;
    }

    @Override
    public RespuestaGenerica eliminar(AdmUsuarios usuarios) {
        admUsuarioXclienteRepository.deleteByUsuarioId(usuarios);
        admUsuariosRepository.delete(usuarios);
        return new RespuestaGenerica(null,Constantes.RESULTADO_EXITO,Constantes.EXITO);
    }

}
