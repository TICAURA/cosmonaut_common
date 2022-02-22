package mx.com.ga.cosmonaut.common.exception;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServiceException extends Exception {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceException.class);

	private static final long serialVersionUID = 2147982372536866955L;
	private final String codigoError;
	private final Throwable descripcionError;
	private final Map<String, String> parametros;

	public ServiceException(final String codigoError) {
		super(codigoError);
		this.codigoError = codigoError;
		this.descripcionError = new Throwable();
		this.parametros = new HashMap<>(0);
	}

	public ServiceException(final String codigoError, final Throwable descripcionError) {
		super(codigoError,descripcionError);
		this.codigoError = codigoError;
		this.descripcionError = descripcionError;
		this.parametros = new HashMap<>(0);
		LOG.error(codigoError,descripcionError);
	}

	public ServiceException(final String codigoError, final Throwable descripcionError, final Map<String, String> parametros) {
		super(codigoError);
		this.codigoError = codigoError;
		this.descripcionError = descripcionError;
		this.parametros = parametros;
	}

	public final String getCodigoError() {
		return codigoError;
	}

	public final Throwable getDescripcionError() {
		return descripcionError;
	}

	public final Map<String, String> getParametros() {
		return parametros;
	}

}
