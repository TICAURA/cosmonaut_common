package mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion;

import io.micronaut.data.annotation.*;

import java.io.Serializable;

@Embeddable
public class CatMunicipioPK implements Serializable {

    @MappedProperty(value = "c_mnpio")
    private short cMnpio;
    @MappedProperty(value = "c_estado")
    private short cEstado;

}
