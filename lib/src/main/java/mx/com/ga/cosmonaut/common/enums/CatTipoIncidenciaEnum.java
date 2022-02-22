package mx.com.ga.cosmonaut.common.enums;

public enum CatTipoIncidenciaEnum {

    VACACIONES(1, "Vacaciones"),
    DIAS_ECONOMICOS(2, "Días económicos"),
    INCAPACIDADES(3, "Incapacidad"),
    FALTAS(5, "Falta"),
    CUMPLEANIOS(6, "Cumpleaños"),
    ANIVERSARIO(7, "Aniversario"),
    RETARDO(8, "Retardo"),
    PRIMA_DOMINICAL(9, "Prima dominical"),
    DIA_DESCANSO_LABORADO(11, "Días de descanso laborados"),
    HORAS_EXTRAS_DOBLES(13, "Horas extras dobles"),
    HORAS_EXTRAS_TRIPLES(14, "Horas extras triples"),
    DIAS_FESTIVOS_LABORADOS(16, "Días festivos laborados");

    private final Integer id;
    private final String descripcion;

    CatTipoIncidenciaEnum(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
