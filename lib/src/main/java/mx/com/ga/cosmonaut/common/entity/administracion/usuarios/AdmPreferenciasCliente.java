package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

@Data
@MappedEntity(value = "adm_preferencias_empresa")
public class AdmPreferenciasCliente {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    private Long id;
    @MappedProperty(value = "color_fondo")
    private String colorFondo;
    @MappedProperty(value = "color_menu")
    private String colorMenu;
    @MappedProperty(value = "mostrar_logo_sistema")
    private boolean mostrarLogoSistema;
    @MappedProperty(value = "cliente_id")
    private Long clienteId;
}
