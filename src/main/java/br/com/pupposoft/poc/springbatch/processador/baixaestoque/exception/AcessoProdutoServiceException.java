package br.com.pupposoft.poc.springbatch.processador.baixaestoque.exception;

import br.com.pupposoft.poc.springbatch.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class AcessoProdutoServiceException extends SystemBaseException {
	private static final long serialVersionUID = 6924498945646227955L;
	
	private final String code = "erroAcessoProdutoService";//NOSONAR
	private final String message = "Erro ao acessar o serviço de produtos";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
