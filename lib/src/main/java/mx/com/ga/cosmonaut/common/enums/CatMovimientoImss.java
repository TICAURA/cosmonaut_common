package mx.com.ga.cosmonaut.common.enums;

public enum CatMovimientoImss {

    BAJA(1, "guardarBaja","02"),
    MODIFICAR(2, "modificarCompensacion","07"),
    GUARDAR(3, "guardar","08"),
    GUARDAR_REACTIVAR(4, "guardarReactivar","08");

    private final Integer id;
    private final String descripcion;
    private final String clave;

    CatMovimientoImss(Integer id, String descripcion, String clave) {
        this.id = id;
        this.descripcion = descripcion;
        this.clave = clave;
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getClave() {
        return clave;
    }
}
