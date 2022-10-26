package br.com.serratec.ecommerce.handler;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.com.serratec.ecommerce.exception.ResourceBadRequestException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.model.error.MensagemError;
import br.com.serratec.ecommerce.utils.ConversorDeData;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<MensagemError> handlerResourceNotFoundException(ResourceNotFoundException ex) {
		String dataHora = ConversorDeData.converterDateParaDataEHora(new Date());
		MensagemError erro = new MensagemError(dataHora, 404, "Not Found", ex.getMessage());

		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<MensagemError> handlerResourceNotFoundException(ResourceBadRequestException ex) {
		String dataHora = ConversorDeData.converterDateParaDataEHora(new Date());
		MensagemError erro = new MensagemError(dataHora, 400, "Bad Request", ex.getMessage());

		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MensagemError> handlerResourceNotFoundException(Exception ex) {
		String dataHora = ConversorDeData.converterDateParaDataEHora(new Date());
		MensagemError erro = new MensagemError(dataHora, 500, "Internal Server Error", ex.getMessage());

		return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
