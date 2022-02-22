package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.NcoPersonaDto;
import mx.com.ga.cosmonaut.common.dto.SqlDto;
import mx.com.ga.cosmonaut.common.dto.consultas.PersonaConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Sql;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class PersonaRepository {

    private final JdbcOperations jdbcOperations;

    protected PersonaRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT = "SELECT np.ci_puesto AS contacto_inicial_puesto, np.persona_id, np.nombre, np.apellido_pat AS apellido_paterno, np.apellido_mat AS apellido_materno, np.ci_email_personal AS contacto_inicial_email_personal, np.ci_telefono AS contacto_inicial_telefono, np.ci_extension AS contacto_inicial_extension, np.curp, np.email_corp AS email_corporativo, np.rfc, ncc.centroc_cliente_id, ncc.razon_social, np.es_activo AS activo, np.fecha_alta, np.iba_nacionalidad_id AS nacionalidad, np.celular AS celular, null AS nacionalidad_id, np.poder_notarial AS poder_notarial, null AS facultad_poder_id, np.facultad_poder_id AS facultad_poder ";

    private static final String SELECT_REPRESENTANTE = "SELECT np.ci_puesto AS contacto_inicial_puesto, np.persona_id, np.nombre, np.apellido_pat AS apellido_paterno, np.apellido_mat AS apellido_materno, np.ci_email_personal AS contacto_inicial_email_personal, np.ci_telefono AS contacto_inicial_telefono, np.ci_extension AS contacto_inicial_extension, np.curp, np.email_corp AS email_corporativo, np.rfc, ncc.centroc_cliente_id, ncc.razon_social, np.es_activo AS activo, np.fecha_alta, np.iba_nacionalidad_id AS nacionalidad, np.celular AS celular, null AS nacionalidad_id, np.poder_notarial AS poder_notarial, null AS facultad_poder_id, np.facultad_poder_id AS facultad_poder ";

    @Transactional
    public List<PersonaConsulta> consultaDimanicaPersona(NcoPersonaDto personaDto) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamicoPersona(personaDto);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, PersonaConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDimanicaPersona " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private SqlDto generaQueryDinamicoPersona(NcoPersonaDto ncoPersonaDto){
        SqlDto sqlDto = new SqlDto();
        sqlDto.setListaDatos(new ArrayList<>());
        StringBuilder query = new StringBuilder(100);
        if (ncoPersonaDto.getTipoPersonaId() != null && ncoPersonaDto.getTipoPersonaId().getTipoPersonaId() == 1 ){
            query.append(SELECT_REPRESENTANTE).append("FROM nco_persona np ")
                    .append("INNER JOIN ncl_centroc_cliente ncc on np.representante_legal_centroc_cliente_id = ncc.centroc_cliente_id ")
                    .append("INNER JOIN cat_nacionalidad cn on np.iba_nacionalidad_id = cn.nacionalidad_id ")
                    .append("WHERE ");
        }else{
            query.append(SELECT).append("FROM nco_persona np ")
                    .append("INNER JOIN ncl_centroc_cliente ncc on np.representante_legal_centroc_cliente_id = ncc.centroc_cliente_id ")
                    .append("WHERE ");
        }

        if (ncoPersonaDto.getPersonaId() != null){
            query.append(" np.persona_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(ncoPersonaDto.getPersonaId());
        }
        if (validaString(ncoPersonaDto.getNombre())){
            query.append(" np.nombre ILIKE ? " );
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + ncoPersonaDto.getNombre() + "%");
        }
        if (validaString(ncoPersonaDto.getApellidoPaterno())){
            query.append(" np.apellido_pat ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + ncoPersonaDto.getApellidoPaterno() + "%");
        }
        if (ncoPersonaDto.getCentrocClienteId() != null &&
                ncoPersonaDto.getCentrocClienteId().getCentrocClienteId() != null){
            query.append(" np.representante_legal_centroc_cliente_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(ncoPersonaDto.getCentrocClienteId().getCentrocClienteId());
        }
        if (validaString(ncoPersonaDto.getContactoInicialEmailPersonal())){
            query.append(" np.ci_email_personal ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + ncoPersonaDto.getContactoInicialEmailPersonal() + "%");
        }
        if (validaString(ncoPersonaDto.getEmailCorporativo())){
            query.append(" np.email_corp ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + ncoPersonaDto.getEmailCorporativo() + "%");
        }
        if (ncoPersonaDto.getTipoPersonaId() != null &&
                ncoPersonaDto.getTipoPersonaId().getTipoPersonaId() != null){
            query.append(" np.tipo_persona_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(ncoPersonaDto.getTipoPersonaId().getTipoPersonaId());
        }
        if (validaString(ncoPersonaDto.getApellidoMaterno())){
            query.append(" np.apellido_mat ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + ncoPersonaDto.getApellidoMaterno() + "%");
        }
        if (ncoPersonaDto.getFechaAlta() != null){
            query.append(" np.fecha_alta = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(ncoPersonaDto.getFechaAlta());
        }
        if (ncoPersonaDto.getEsActivo() != null){
            query.append(" np.es_activo = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(ncoPersonaDto.getEsActivo());
        }
        sqlDto.setQuery(Sql.eliminarUltimoAnd(query.toString()));
        sqlDto.setQuery(sqlDto.getQuery() + " ORDER BY np.fecha_alta DESC ");
        return sqlDto;
    }

    private boolean validaString (String texto){
        return texto != null && !texto.isEmpty();
    }
}
