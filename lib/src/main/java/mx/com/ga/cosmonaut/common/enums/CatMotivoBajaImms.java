package mx.com.ga.cosmonaut.common.enums;

public enum CatMotivoBajaImms {

    TERMINO_CONTRATO(1, "1","Término del contrato"),
    SEPARACION_VOLUNTARIA(2, "2","Separación voluntaria"),
    ABANDONO_EMPLEO(3, "3","Abandono de empleo"),
    DEFUCION(4, "4","Defunción"),
    CLAUSURA(5, "5","Clausura"),
    OTRA(6, "6","Otra"),
    AUSENTISMO(7, "7","Ausentismo"),
    RESCISION_CONTRATO(8, "8","Rescisión de contrato"),
    JUBILACION(9, "9","Jubilación"),
    PENSION(10, "A","Pensión");

    private final Integer id;
    private final String clave;
    private final String descripcion;

    CatMotivoBajaImms(Integer id, String clave, String descripcion) {
        this.id = id;
        this.clave = clave;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
