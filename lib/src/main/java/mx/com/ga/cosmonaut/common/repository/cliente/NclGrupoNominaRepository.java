package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.cliente.NclGrupoNomina;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPolitica;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclGrupoNominaRepository extends CrudRepository<NclGrupoNomina, Integer> {

    @Override
    @Join(value = "centrocClienteId", type = Join.Type.FETCH, alias = "clienteId")
    @Join(value = "periodicidadPagoId", type = Join.Type.FETCH, alias = "periodicidadId")
    @Join(value = "periodoAguinaldoId", type = Join.Type.FETCH, alias = "periodoId")
    @Join(value = "monedaId", type = Join.Type.FETCH, alias = "monedaId")
    @Join(value = "cuentaBancoId", type = Join.Type.FETCH, alias = "cuentaId")
    List<NclGrupoNomina> findAll();

    @Join(value = "centrocClienteId", type = Join.Type.FETCH, alias = "clienteId")
    @Join(value = "periodicidadPagoId", type = Join.Type.FETCH, alias = "periodicidadId")
    @Join(value = "periodoAguinaldoId", type = Join.Type.FETCH, alias = "periodoId")
    @Join(value = "monedaId", type = Join.Type.FETCH, alias = "monedaId")
    @Join(value = "cuentaBancoId", type = Join.Type.FETCH, alias = "cuentaId")
    List<NclGrupoNomina> findByEsActivoOrderByNombre(boolean esActivo);

    @Override
    @Join(value = "centrocClienteId", type = Join.Type.FETCH, alias = "clienteId")
    @Join(value = "periodicidadPagoId", type = Join.Type.FETCH, alias = "periodicidadId")
    @Join(value = "periodoAguinaldoId", type = Join.Type.FETCH, alias = "periodoId")
    @Join(value = "monedaId", type = Join.Type.FETCH, alias = "monedaId")
    @Join(value = "cuentaBancoId", type = Join.Type.FETCH, alias = "cuentaId")
    @Join(value = "basePeriodoId", type = Join.Type.FETCH, alias = "basePeriodoId")
    Optional<NclGrupoNomina> findById(Integer grupoNominaId);

    Optional<NclGrupoNomina> findByGrupoNominaId(Integer grupoNominaId);

    List<NclGrupoNomina> findByCentrocClienteIdCentrocClienteId(Integer centrocClienteId);

    void update(@Id Integer id, boolean esActivo);

    List<NclGrupoNomina> findByEsActivoAndCentrocClienteIdCentrocClienteIdOrderByGrupoNominaId(
            boolean activo, Integer centroCclienteId);

    List<NclGrupoNomina> findByEsActivoAndCentrocClienteIdCentrocClienteIdAndPagoComplementarioOrderByGrupoNominaId(
            boolean activo, Integer centroCclienteId, boolean pagoComplementario);


    boolean existsByGrupoNominaIdAndCentrocClienteIdCentrocClienteId(Integer grupoNominaId, Integer centrocClienteId);

    @Query(value = "SELECT *  FROM public.ncl_grupo_nomina t  where t.centroc_cliente_id = :idCliente and  upper(t.nombre) = :nombre", nativeQuery = true)
    List<NclGrupoNomina> existsByCentrocClienteIdCentrocClienteIdAndNominaAndEsActivo(Integer idCliente, String nombre);

    @Query(value = "SELECT *  FROM public.ncl_grupo_nomina t  where t.centroc_cliente_id = :idCliente and  t.grupo_nomina_id = :idNomina", nativeQuery = true)
    List<NclGrupoNomina> duplicadoCentroIDNominaID(Integer idCliente, Integer idNomina);
}