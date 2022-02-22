package mx.com.ga.cosmonaut.common.dto.administracion.usuarios;

import lombok.Data;
import mx.com.ga.cosmonaut.common.dto.administracion.permisos.SubmoduloXPermiso;
import mx.com.ga.cosmonaut.common.dto.administracion.preferencias.PreferenciasColores;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.util.List;
import java.util.Set;

@Data
public class ObtenerUsernameResponse {

    private AdmUsuarios usuario;
    private Set<SubmoduloXPermiso> submodulosXpermisos;
    private List<NcoPersona> personas;
    private List<NclCentrocCliente> clientes;
    private List<PreferenciasColores> coloresDefecto;

}
