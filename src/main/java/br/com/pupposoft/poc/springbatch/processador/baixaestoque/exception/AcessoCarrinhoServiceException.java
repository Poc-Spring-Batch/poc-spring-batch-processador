package br.com.pupposoft.poc.springbatch.processador.baixaestoque.exception;

import br.com.pupposoft.poc.springbatch.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class AcessoCarrinhoServiceException extends SystemBaseException {
	private static final long serialVersionUID = 4306545258260552901L;
	
	private final String code = "erroAcessoCarrinhoService";//NOSONAR
	private final String message = "Erro ao acessar o serviço de carrinhos";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
