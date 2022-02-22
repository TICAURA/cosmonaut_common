package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.SqlDto;
import mx.com.ga.cosmonaut.common.dto.consultas.BitacoraCosmonautConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Sql;
import mx.com.ga.cosmonaut.common.util.Utilidades;
import mx.com.ga.cosmonaut.common.util.Validar;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class BitacoraCosmonautRepository {

    private final JdbcOperations jdbcOperations;

    protected BitacoraCosmonautRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT = "SELECT bitacora.bitacora_cosmonaut_id, " +
            "       bitacora.centro_cliente_id, " +
            "       usuarios.nombre, " +
            "       usuarios.apellido_pat AS apellido_paterno, " +
            "       usuarios.apellido_mat AS apellido_materno, " +
            "       rol.nombre_rol AS rol, " +
            "       bitacora.modulo, " +
            "       bitacora.accion AS movimiento, " +
            "       bitacora.fecha_movimiento ";

    private static final String FROM = "FROM adm_bitacora_cosmonaut bitacora " +
            "INNER JOIN adm_usuarios usuarios on bitacora.usuario_id = usuarios.usuario_id " +
            "INNER JOIN adm_roles rol on usuarios.rol_id = rol.rol_id ";

    @Transactional
    public List<BitacoraCosmonautConsulta> consultaDimanica(BitacoraCosmonautConsulta bitacora) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamico(bitacora);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, BitacoraCosmonautConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDimanica " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private SqlDto generaQueryDinamico(BitacoraCosmonautConsulta bitacora){
        SqlDto sqlDto = new SqlDto();
        sqlDto.setListaDatos(new ArrayList<>());
        StringBuilder query = new StringBuilder(100);
        query.append(SELECT).append(FROM).append(" WHERE bitacora.centro_cliente_id = ? ");
        sqlDto.getListaDatos().add(bitacora.getCentroClienteId());
        query.append(Constantes.SQL_AND);

        if (Validar.validaTexto(bitacora.getNombre())){
            query.append(" usuarios.nombre ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(bitacora.getNombre() + "%");
        }

        if (Validar.validaTexto(bitacora.getApellidoPaterno())){
            query.append(" usuarios.apellido_pat ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(bitacora.getApellidoPaterno() + "%");
        }

        if (Validar.validaTexto(bitacora.getApellidoMaterno())){
            query.append(" usuarios.apellido_mat ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(bitacora.getApellidoMaterno() + "%");
        }

        if (bitacora.getFechaMovimiento() != null){
            query.append(" bitacora.fecha_movimiento::text ILIKE  ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(Utilidades.fechaTexto(bitacora.getFechaMovimiento()) + "%");
        }

        sqlDto.setQuery(Sql.eliminarUltimoAnd(query.toString()));
        return sqlDto;
    }

}
