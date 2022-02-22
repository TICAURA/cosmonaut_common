package mx.com.ga.cosmonaut.common.dto.consultas;


import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Introspected
public class ColaboradorPagoComplementarioConsulta {

    private Integer personaId;
    private String idEmpleado;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String rfc;
    private String pppMontoComplementario;
    private String clabe;
    private String curp;
    private String nss;
    private String instituto;
    private String correo;

    public String getInfo(Integer i) {
        switch (i) {
            case 0 : return idEmpleado != null ? getIdEmpleado() : "";
            case 1 : return nombre != null ? getNombre() : "";
            case 2 : return apellidoPat != null ? getApellidoPat() : "";
            case 3 : return apellidoMat != null ? getApellidoMat() : "";
            case 4 : return rfc != null ? getRfc() : "";
            case 5 : return pppMontoComplementario != null ? new BigDecimal(getPppMontoComplementario())
                    .setScale(2, RoundingMode.HALF_UP).toString(): "";
            case 6 : return clabe != null ? getClabe() : "";
            case 7 : return curp != null ? getCurp() : "";
            case 8 : return nss != null ? getNss() : "";
            case 9 : return instituto != null ? getInstituto() : "";
            case 10 : return correo != null ? getCorreo() : "";
            default: return "";
        }
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer idPersona) {
        this.personaId = idPersona;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPppMontoComplementario() {
        return pppMontoComplementario;
    }

    public void setPppMontoComplementario(String pppMontoComplementario) {
        this.pppMontoComplementario = pppMontoComplementario;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getInstituto() {
        return instituto;
    }

    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
