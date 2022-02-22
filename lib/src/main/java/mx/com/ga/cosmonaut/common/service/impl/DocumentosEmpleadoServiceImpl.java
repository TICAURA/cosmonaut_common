package mx.com.ga.cosmonaut.common.service.impl;

import mx.com.ga.cosmonaut.common.dto.DocumentosEmpleadoDto;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.cms.*;
import mx.com.ga.cosmonaut.common.entity.DocumentosEmpleado;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.repository.DocumentosEmpleadoRepository;
import mx.com.ga.cosmonaut.common.repository.colaborador.NcoPersonaRepository;
import mx.com.ga.cosmonaut.common.service.DocumentosEmpleadoService;
import mx.com.ga.cosmonaut.common.service.GestionContenidoService;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.ObjetoMapper;
import mx.com.ga.cosmonaut.common.util.Utilidades;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DocumentosEmpleadoServiceImpl implements DocumentosEmpleadoService {

    @Inject
    private DocumentosEmpleadoRepository documentosEmpleadoRepository;

    @Inject
    private NcoPersonaRepository ncoPersonaRepository;

    @Inject
    private GestionContenidoService gestionContenidoService;

    @Override
    public RespuestaGenerica guardar(DocumentosEmpleadoDto documentosEmpleadoDto) throws ServiceException {
        try{
            RespuestaGenerica respuesta = validaCamposObligatorios(documentosEmpleadoDto);
            if (respuesta.isResultado()){
                documentosEmpleadoDto.setCmsExpedienteId(obtenExpediente(documentosEmpleadoDto));
                documentosEmpleadoDto.setCmsTipoMultimedia(obtenerMultimedia(documentosEmpleadoDto.getNombreArchivo()));
                documentosEmpleadoDto.setNombreArchivo(
                        obtenerNombreArchivo(
                        documentosEmpleadoDto.getTipoDocumentoId(),
                        documentosEmpleadoDto.getPersonaId(),
                        documentosEmpleadoDto.getNombreArchivo()));
                respuesta = guardarGestionDocumento(documentosEmpleadoDto);
                if (respuesta.isResultado()){
                    ArchivosRespuesta archivosRespuesta = ObjetoMapper.map(respuesta.getDatos(),ArchivosRespuesta.class);
                    DocumentosEmpleado documentosEmpleado = ObjetoMapper.map(documentosEmpleadoDto, DocumentosEmpleado.class);
                    documentosEmpleado.setCmsArchivoId(archivosRespuesta.getId());
                    documentosEmpleado.setFechaCarga(Utilidades.obtenerFechaSystema());
                    respuesta.setDatos(documentosEmpleadoRepository.save(documentosEmpleado));
                    respuesta.setResultado(Constantes.RESULTADO_EXITO);
                    respuesta.setMensaje(Constantes.EXITO);
                }
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" guardar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica descargar(Integer documentoId) throws ServiceException {
        try{
            return gestionContenidoService.obtenerDocumento(documentoId);
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" descargar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica remplazar(DocumentosEmpleadoDto documentosEmpleadoDto) throws ServiceException {
        try{
            RespuestaGenerica respuesta = new RespuestaGenerica(null, Constantes.RESULTADO_EXITO,Constantes.EXITO);
            if (documentosEmpleadoDto.getDocumentosEmpleadoId() != null){
                respuesta = remplazarGestionDocumento(documentosEmpleadoDto);
            }else {
                respuesta.setResultado(Constantes.RESULTADO_ERROR);
                respuesta.setMensaje(Constantes.ID_NULO);
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" remplazarDocumentos " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica lista(Integer centroClienteId, Integer personaId) throws ServiceException {
        try{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            List<DocumentosEmpleadoDto> listaDocumentosEmpleado = new ArrayList<>();

            List<DocumentosEmpleado> lista = documentosEmpleadoRepository.findByCentrocClienteIdAndPersonaId(
                    centroClienteId,personaId);

            List<TipoDocumento> listaTipoDocumento = obtenerCatalogoTipoDocumentoCMS();

            for (DocumentosEmpleado documentosEmpleado : lista) {
                TipoDocumento tipoDocumento = listaTipoDocumento.stream()
                        .filter(x -> documentosEmpleado.getTipoDocumentoId().equals(x.getId()))
                        .findAny()
                        .orElse(null);
                DocumentosEmpleadoDto documentosEmpleadoDto = ObjetoMapper.map(documentosEmpleado, DocumentosEmpleadoDto.class);
                documentosEmpleadoDto.setTipoDocumento(tipoDocumento);
                listaDocumentosEmpleado.add(documentosEmpleadoDto);
            }

            respuesta.setDatos(listaDocumentosEmpleado);
            respuesta.setResultado(Constantes.RESULTADO_EXITO);
            respuesta.setMensaje(Constantes.EXITO);
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" listaDocumentos " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica listaTipoDocumento(Integer centroClienteId, Integer personaId, Integer tipoDocumentoId)
            throws ServiceException {
        try{
            RespuestaGenerica respuesta = lista(centroClienteId, personaId);
            List<DocumentosEmpleadoDto> lista = (List<DocumentosEmpleadoDto>) respuesta.getDatos();
            List<DocumentosEmpleadoDto> listaDocumentos = lista.stream()
                    .filter(x -> tipoDocumentoId.equals(x.getTipoDocumentoId()))
                    .collect(Collectors.toList());

            return new RespuestaGenerica(listaDocumentos, Constantes.RESULTADO_EXITO, Constantes.EXITO);
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" listaDocumentos " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica obtenerTipoDocumentos() throws ServiceException {
        try{
            List<TipoDocumento> listaTipoDocumento = obtenerCatalogoTipoDocumentoCMS();
            List<TipoDocumento> lista = listaTipoDocumento.stream()
                    .filter(TipoDocumento::isActivo).collect(Collectors.toList());
            return new RespuestaGenerica(lista,Constantes.RESULTADO_EXITO,Constantes.EXITO);
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" obtenerTipoDocumentos " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Override
    public RespuestaGenerica eliminar(Integer documentoId) throws ServiceException {
        try{
            DocumentosEmpleado documentosEmpleado = documentosEmpleadoRepository.findById(documentoId).
                    orElseThrow(() -> new ServiceException("El documentoId no se encuentra dado de alta en el sistema"));
            RespuestaGenerica respuesta = gestionContenidoService.eliminarDocumento(documentosEmpleado.getCmsArchivoId());
            if (respuesta.isResultado()){
                documentosEmpleadoRepository.deleteById(documentoId);
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" eliminar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private List<TipoDocumento> obtenerCatalogoTipoDocumentoCMS() throws ServiceException {
        RespuestaGenerica respuesta = gestionContenidoService.obtenerCatalogoTipoDocumento();
        if (respuesta.isResultado()){
            List<TipoDocumento> listaTipoDocumento = (List<TipoDocumento>) respuesta.getDatos();
            return ObjetoMapper.mapAll(listaTipoDocumento, TipoDocumento.class);
        }
        return new ArrayList<>();
    }

    private RespuestaGenerica guardarGestionDocumento(DocumentosEmpleadoDto documentosEmpleadoDto) throws ServiceException {
        try{
            RespuestaGenerica respuesta =  new RespuestaGenerica(null, Constantes.RESULTADO_EXITO, Constantes.EXITO);
            if (documentosEmpleadoDto.getCmsExpedienteId() != null &&
                    !documentosEmpleadoDto.getCmsExpedienteId().isEmpty()){
                Entradas entradas = new Entradas();
                Archivos archivos = new Archivos();
                Documentos documentos = new Documentos();
                documentos.setContenido(Utilidades.encodeContent(documentosEmpleadoDto.getDocumento()));
                documentos.setNombre(documentosEmpleadoDto.getNombreArchivo());
                documentos.setTipoDocto(new TipoDocumento());
                documentos.getTipoDocto().setAcronimo(obtenTipoDocumento(documentosEmpleadoDto.getTipoDocumentoId()));
                documentos.setTipoMM(new TipoMultimedia());
                documentos.getTipoMM().setId(documentosEmpleadoDto.getCmsTipoMultimedia());
                archivos.setArchivo(documentos);
                archivos.setExpediente(new Expediente());
                archivos.getExpediente().setClave(documentosEmpleadoDto.getCmsExpedienteId());
                entradas.setEntrada(archivos);
                respuesta = gestionContenidoService.guardarDocumento(entradas);
            }else {
                respuesta.setResultado(Constantes.RESULTADO_ERROR);
                respuesta.setMensaje("No se logro crear el expediente.");
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" guardarGestionDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private RespuestaGenerica remplazarGestionDocumento(DocumentosEmpleadoDto documentosEmpleadoDto) throws ServiceException {
        try{
            RespuestaGenerica respuesta =  new RespuestaGenerica(null, Constantes.RESULTADO_EXITO, Constantes.EXITO);
            if (documentosEmpleadoDto.getDocumento() != null){
                DocumentosEmpleado documentosEmpleado = documentosEmpleadoRepository.findById(documentosEmpleadoDto.getDocumentosEmpleadoId())
                                .orElseThrow(() -> new ServiceException("El ID del documento no existe"));
                Entradas entradas = new Entradas();
                Archivos archivos = new Archivos();
                Documentos documentos = new Documentos();
                documentos.setNombre(documentosEmpleadoDto.getNombreArchivo());
                documentos.setContenido(Utilidades.encodeContent(documentosEmpleadoDto.getDocumento()));
                documentos.setTipoDocto(new TipoDocumento());
                documentos.getTipoDocto().setAcronimo(obtenTipoDocumento(documentosEmpleado.getTipoDocumentoId()));
                documentos.setTipoMM(new TipoMultimedia());
                documentosEmpleadoDto.setCmsTipoMultimedia(obtenerMultimedia(documentosEmpleadoDto.getNombreArchivo()));
                documentos.getTipoMM().setId(documentosEmpleadoDto.getCmsTipoMultimedia());
                archivos.setArchivo(documentos);
                archivos.setOriginal(new Origen());
                archivos.getOriginal().setId(documentosEmpleado.getCmsArchivoId());
                entradas.setEntrada(archivos);
                respuesta = gestionContenidoService.remplazarDocumento(entradas);
            }else {
                respuesta.setResultado(Constantes.RESULTADO_ERROR);
                respuesta.setMensaje("Se requiere un documento para remplazar.");
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" remplazarGestionDocumento " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private String obtenExpediente(DocumentosEmpleadoDto documentosEmpleadoDto) throws ServiceException {
        NcoPersona ncoPersona = ncoPersonaRepository.findByPersonaId(documentosEmpleadoDto.getPersonaId());
        String expedienteId = documentosEmpleadoDto.getCentrocClienteId()
                    + "-"
                    + documentosEmpleadoDto.getPersonaId()
                    + "-"
                    + ncoPersona.getRfc();
        RespuestaGenerica respuesta = gestionContenidoService.obtenerExpediente(expedienteId);

        if (!respuesta.isResultado()){
            Datos datos = new Datos();
            Expediente expediente = new Expediente();
            expediente.setClave(expedienteId);
            datos.setData(expediente);
            RespuestaGenerica respuestaGenerica = gestionContenidoService.guardarExpediente(datos);
            if (respuestaGenerica.isResultado()){
                Expediente respuestaExpediente = ObjetoMapper.map(respuestaGenerica.getDatos(),Expediente.class);
                expedienteId = respuestaExpediente.getClave();
            }
        }

        return expedienteId;
    }

    private Integer obtenerMultimedia(String nombreArchivo) throws ServiceException {
        RespuestaGenerica respuestaGenerica = gestionContenidoService.obtenerCatalogoMultimedios();
        if (respuestaGenerica.isResultado()){
            String extencion = "";
            int i = nombreArchivo.lastIndexOf('.');
            if (i > 0) {
                extencion = nombreArchivo.substring(i+1).toLowerCase();
            }

            List<TipoMultimedia> listaMultimedia = (List<TipoMultimedia>) respuestaGenerica.getDatos();
            listaMultimedia = ObjetoMapper.mapAll(listaMultimedia, TipoMultimedia.class);

            String finalExtencion = extencion;
            TipoMultimedia tipoMultimedia = listaMultimedia.stream()
                    .filter(x -> finalExtencion.equals(x.getExtension()))
                    .findAny()
                    .orElse(null);
            return tipoMultimedia != null ? tipoMultimedia.getId() : null;
        }
        return null;
    }

    private String obtenTipoDocumento(Integer id) throws ServiceException {
        List<TipoDocumento> listaTipoDocumento = obtenerCatalogoTipoDocumentoCMS();
        TipoDocumento tipoDocumento = listaTipoDocumento.stream()
                .filter(x -> id.equals(x.getId()))
                .findAny()
                .orElse(null);
        return tipoDocumento != null ? tipoDocumento.getAcronimo() : null;
    }

    private RespuestaGenerica validaCamposObligatorios(DocumentosEmpleadoDto documentosEmpleadoDto) throws ServiceException {
        try{
            RespuestaGenerica respuesta =  new RespuestaGenerica(null, Constantes.RESULTADO_EXITO, Constantes.EXITO);
            if(documentosEmpleadoDto.getCentrocClienteId() == null
                    || documentosEmpleadoDto.getDocumento() == null
                    || documentosEmpleadoDto.getPersonaId() == null
                    || documentosEmpleadoDto.getNombreArchivo() == null
                    || documentosEmpleadoDto.getTipoDocumentoId() == null
                    || documentosEmpleadoDto.getUsuarioId() == null){
                respuesta.setResultado(Constantes.RESULTADO_ERROR);
                respuesta.setMensaje(Constantes.CAMPOS_REQUERIDOS);
                return respuesta;
            }
            return respuesta;
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" validaCamposObligatorios " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private String obtenerNombreArchivo(Integer tipoDocumentoId, Integer personaId, String nombreArchivo) throws ServiceException {
        NcoPersona ncoPersona = ncoPersonaRepository.findByPersonaId(personaId);

        List<TipoDocumento> listaTipoDocumento = obtenerCatalogoTipoDocumentoCMS();

        TipoDocumento tipoDocumento = listaTipoDocumento.stream()
                .filter(x -> tipoDocumentoId.equals(x.getId()))
                .findAny()
                .orElse(new TipoDocumento());
        return ncoPersona.getRfc() + "_" + tipoDocumento.getAcronimo()+ "_" + nombreArchivo;
    }

}
