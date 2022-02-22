package mx.com.ga.cosmonaut.common.service.impl;

import mx.com.ga.cosmonaut.common.dto.IdseModel;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.consultas.IdseConsulta;
import mx.com.ga.cosmonaut.common.enums.CatMotivoBajaImms;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.repository.colaborador.NcoKardexColaboradorRepository;
import mx.com.ga.cosmonaut.common.repository.nativo.EmpleadosReporteRepository;
import mx.com.ga.cosmonaut.common.service.IdseService;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Utilidades;
import mx.com.ga.cosmonaut.common.util.UtilidadesReportes;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class IdseServiceImpl implements IdseService {

    @Inject
    private EmpleadosReporteRepository empleadosReporteRepository;

    @Inject
    private NcoKardexColaboradorRepository ncoKardexColaboradorRepository;

    private RespuestaGenerica respuestaGenerica = new RespuestaGenerica();

    @Override
    public RespuestaGenerica idseConsulta(IdseModel idseModel, Boolean esPadre) throws ServiceException {
        try {
            /** se genera consulta sobre empresa en kardex.  */
            List<IdseConsulta> listaIdseAltasReingresos =
                    consultaIdseConsulta(idseModel.getIdEmpresa(), esPadre);

            if (Boolean.TRUE.equals(UtilidadesReportes.validaContenidoLista(idseModel.getIdKardex()))) {
                listaIdseAltasReingresos = listaIdseAltasReingresos
                        .stream()
                        .filter(c -> idseModel.getIdKardex().contains(c.getKardexId()))
                        .collect(Collectors.toList());
                idseModel.getIdKardex()
                        .stream()
                        .forEach(c -> ncoKardexColaboradorRepository.updateByKardexColaboradorId(c, true));
            }

            ByteArrayOutputStream salida = escribirArchivoIdseConsulta(listaIdseAltasReingresos);
            respuestaGenerica.setDatos(Base64.getEncoder().encodeToString(salida.toByteArray()));
            respuestaGenerica.setResultado(Constantes.RESULTADO_EXITO);
            respuestaGenerica.setMensaje(Constantes.EXITO);

            return respuestaGenerica;
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" idseAltasReingresos " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    private List<IdseConsulta> consultaIdseConsulta(Integer idEmpresa, Boolean esPadre)
            throws ServiceException {
        try {
            if (Boolean.TRUE.equals(esPadre)) {
                return empleadosReporteRepository.idseConsultaCentroCostoPadre(idEmpresa);
            } else {
                return empleadosReporteRepository.idseConsulta(idEmpresa);
            }
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaIdseConsulta " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    @Override
    public ByteArrayOutputStream escribirArchivoIdseConsulta(List<IdseConsulta> listaIdseAltasReingresos) throws ServiceException {

        ByteArrayOutputStream salida = new ByteArrayOutputStream();

        try (OutputStreamWriter escribir = new OutputStreamWriter(salida)) {

            listaIdseAltasReingresos.stream().forEach( a -> {
                if(!Constantes.ES_UNO.equals(a.getImssId())) {
                    a.setMotivoBaja("0");
                }
            });

            listaIdseAltasReingresos.stream()
                    .forEach( c -> {
                        try {
                            /**c.setTipoSalario(tipoSalarioCalculo(c.getTipoSalario()));*/
                            escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getRegistroPatronal(),11,1,0));
                            escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getNss(),11,1,0));
                            escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getPrimerApellido().replace("ñ","#").replace("Ñ","#"),27,1,0));
                            escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getSegundoApellido().replace("ñ","#").replace("Ñ","#"),27,1,0));
                            escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getNombres().replace("ñ","#").replace("Ñ","#"),27,1,0));
                            /** Baja. */
                            if (Constantes.ES_UNO.equals(c.getImssId())) {
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),15,1,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFechaMovimiento(),8,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),5,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getTipoMovimiento(),2,0,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getGuia(),5,1,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getClaveTrabajador(),10,0,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(obtenerMotivoBaja(Integer.valueOf(c.getMotivoBaja())),1,1,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),18,1,0));
                            } /** Modificacion. */
                            else if (Constantes.ES_DOS.equals(c.getImssId())) {
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(Utilidades.redondeaDoubleStrin(Double.valueOf(c.getSbc())).replace(".",""),6,0,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),6,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),1,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(obtenerTipoCompensacion(Integer.valueOf(c.getTipoSalario())),1,1,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getJornadaReducida(),1,1,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFechaMovimiento(),8,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),5,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getTipoMovimiento(),2,0,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),5,1,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getClaveTrabajador(),10,0,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),1,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getCurp().replace("ñ","#").replace("Ñ","#"),18,0,1));

                            } /** Alta Reingreso*/
                            else if (Constantes.ES_TRES.equals(c.getImssId())) {
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(Utilidades.redondeaDoubleStrin(Double.valueOf(c.getSbc())).replace(".",""),6,0,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),6,0,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getTipoTrabajador(),1,0,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(obtenerTipoCompensacion(Integer.valueOf(c.getTipoSalario())),1,0,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getJornadaReducida(),1,1,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFechaMovimiento(),8,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getMedicinaFamiliar(),3,1,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),2,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getTipoMovimiento(),2,0,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getGuia(),5,0,1));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getClaveTrabajador(),10,0,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getFillerBlanco(),1,1,0));
                                escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getCurp().replace("ñ","#").replace("Ñ","#"),18,1,0));
                            }

                            escribir.write(UtilidadesReportes.validaCadenaComplementaria(c.getIdentificadorFormato(),1,1,0));
                            escribir.write("\r\n");
                        } catch (IOException | ServiceException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO + " escribirArchivoIdseConsulta " + Constantes.ERROR_EXCEPCION, ex);
        }
        return salida;
    }

    private String tipoSalarioCalculo(String tipoSalario) {
        if (tipoSalario != null) {
            return String.valueOf(Integer.parseInt(tipoSalario) - Constantes.ES_UNO);
        }
        return " ";
    }

    private String obtenerMotivoBaja(Integer motivoBajaId){
        String motivoBaja;
        switch (motivoBajaId) {
            case 1:
                motivoBaja = CatMotivoBajaImms.ABANDONO_EMPLEO.getClave();
                break;
            case 2:
                motivoBaja = CatMotivoBajaImms.AUSENTISMO.getClave();
                break;
            case 3:
                motivoBaja = CatMotivoBajaImms.CLAUSURA.getClave();
                break;
            case 4:
                motivoBaja = CatMotivoBajaImms.DEFUCION.getClave();
                break;
            case 5:
                motivoBaja = CatMotivoBajaImms.JUBILACION.getClave();
                break;
            case 7:
                motivoBaja = CatMotivoBajaImms.PENSION.getClave();
                break;
            case 8:
                motivoBaja = CatMotivoBajaImms.RESCISION_CONTRATO.getClave();
                break;
            case 9:
                motivoBaja = CatMotivoBajaImms.SEPARACION_VOLUNTARIA.getClave();
                break;
            case 10:
                motivoBaja = CatMotivoBajaImms.TERMINO_CONTRATO.getClave();
                break;
            default:
                motivoBaja = CatMotivoBajaImms.OTRA.getClave();
                break;
        }

        return motivoBaja;
    }

    private String obtenerTipoCompensacion(Integer tipoCompensacionId){
        String tipoCompensacion;
        switch (tipoCompensacionId) {
            case 1:
                tipoCompensacion = "0";
                break;
            case 2:
                tipoCompensacion = "2";
                break;
            default:
                tipoCompensacion = "1";
                break;
        }

        return tipoCompensacion;
    }

}
