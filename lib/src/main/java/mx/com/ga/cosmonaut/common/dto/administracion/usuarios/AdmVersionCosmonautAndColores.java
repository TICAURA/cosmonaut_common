package mx.com.ga.cosmonaut.common.dto.administracion.usuarios;


import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;
import lombok.Data;
import mx.com.ga.cosmonaut.common.dto.administracion.preferencias.PreferenciasColores;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmVersionCosmonaut;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

@Data
public class AdmVersionCosmonautAndColores {

    private PreferenciasColores color;
    private Integer versionCosmonautXclienteId;
    private AdmVersionCosmonaut versionCosmonautId;
    private NclCentrocCliente centrocClienteId;
}
