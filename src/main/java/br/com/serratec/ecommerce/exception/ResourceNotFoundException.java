package br.com.serratec.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException (String mensagem) {
		super(mensagem);
	}
}
