package mx.com.ga.cosmonaut.common.dto.administracion.noticias;

import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.administracion.noticias.AdmNoticias;

import java.util.List;

@Data
public class AdmNoticiasResponse {

    List<AdmNoticias> noticiasCosmonaut;
    List<AdmNoticias> noticiasGeneral;
}
