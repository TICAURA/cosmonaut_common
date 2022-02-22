package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.NclCentrocClienteDto;
import mx.com.ga.cosmonaut.common.dto.SqlDto;
import mx.com.ga.cosmonaut.common.dto.consultas.CentroCostosClienteConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Sql;
import mx.com.ga.cosmonaut.common.util.Utilidades;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class CentroCostoCienteRepository {

    private final JdbcOperations jdbcOperations;

    protected CentroCostoCienteRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<CentroCostosClienteConsulta> consultaDimanica(NclCentrocClienteDto centrocCliente) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamico(centrocCliente);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, CentroCostosClienteConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDimanica " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<CentroCostosClienteConsulta> consultaDimanicaPaginado(NclCentrocClienteDto centrocCliente,Integer numeroRegistros, Integer pagina)
            throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamico(centrocCliente);
            StringBuilder query = new StringBuilder(100);
            query.append(sqlDto.getQuery()).append(" LIMIT ? ").append(" OFFSET ? ");
            sqlDto.setQuery(query.toString());
            sqlDto.getListaDatos().add(numeroRegistros);
            sqlDto.getListaDatos().add(pagina);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, CentroCostosClienteConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDimanicaPaginado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private SqlDto generaQueryDinamico(NclCentrocClienteDto centrocCliente){
        SqlDto sqlDto = new SqlDto();
        sqlDto.setListaDatos(new ArrayList<>());
        StringBuilder query = new StringBuilder(100);
        query.append("SELECT "
                + "ncc.centroc_cliente_id, "
                + "ncc.curp, "
                + "ncc.rfc, "
                + "ncc.razon_social, "
                + "ncc.fecha_constitucion, "
                + "ncc.es_activo, "
                + "ncc.nombre, "
                + "ncc.fecha_alta, "
                + "ncc.base_periodo_id, "
                + "ncc.cc_url_logo AS url_logo, "
                + "ncc.cc_url_logo AS url, "
                + "ncc.multiempresa AS multiempresa, "
                + "ncc.pago_complementario AS pagoComplementario "
                + "FROM ncl_centroc_cliente ncc "
                + "WHERE centro_costos_centroc_cliente_id IS NULL AND ");
        if (centrocCliente.getCentrocClienteId() != null){
            query.append(" ncc.centroc_cliente_id::text ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + centrocCliente.getCentrocClienteId().toString() + "%");
        }
        if (centrocCliente.getRfc() != null && !centrocCliente.getRfc().isEmpty()){
            query.append(" ncc.rfc ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + centrocCliente.getRfc() + "%");
        }
        if (centrocCliente.getNombre() != null && !centrocCliente.getNombre().isEmpty()){
            query.append(" ncc.nombre ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + centrocCliente.getNombre() + "%");
        }
        if (centrocCliente.getRazonSocial() != null && !centrocCliente.getRazonSocial().isEmpty()){
            query.append(" ncc.razon_social ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + centrocCliente.getRazonSocial() + "%");
        }
        if (centrocCliente.getFechaAlta() != null ){
            query.append(" ncc.fecha_alta BETWEEN '" + Utilidades.fechaTexto(centrocCliente.getFechaAlta()) +
                    " 00:00'" + " AND '" + Utilidades.fechaTexto(centrocCliente.getFechaAlta()) +" 23:59' ");
            query.append(Constantes.SQL_AND);
        }

        if (centrocCliente.getEsActivo() != null){
            query.append(" ncc.es_activo = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(centrocCliente.getEsActivo());
        }

        if (centrocCliente.getMultiempresa() != null){
            query.append(" ncc.multiempresa = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(centrocCliente.getMultiempresa());
        }

        sqlDto.setQuery(Sql.eliminarUltimoAnd(query.toString()));
        sqlDto.setQuery(sqlDto.getQuery() + " ORDER BY ncc.fecha_alta DESC ");
        return sqlDto;
    }


}
