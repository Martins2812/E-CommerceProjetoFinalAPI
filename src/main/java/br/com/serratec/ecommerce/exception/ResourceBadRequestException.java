package br.com.serratec.ecommerce.exception;

public class ResourceBadRequestException extends RuntimeException {

	public ResourceBadRequestException (String mensagem) {
		super(mensagem);
	}
}