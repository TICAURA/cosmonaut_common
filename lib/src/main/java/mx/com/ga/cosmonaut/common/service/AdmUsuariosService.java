package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.administracion.usuarios.AdminUsuarioCS;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;

import java.util.List;

public interface AdmUsuariosService {

    boolean existeEmpleadoCorreo(String corre);

    RespuestaGenerica agregarEmpleado(AdminUsuarioCS usuario);

    List<AdmUsuarios>  agregarListaEmpleados(List<AdminUsuarioCS> usuario);

    RespuestaGenerica eliminar(AdmUsuarios usuarios);

}
