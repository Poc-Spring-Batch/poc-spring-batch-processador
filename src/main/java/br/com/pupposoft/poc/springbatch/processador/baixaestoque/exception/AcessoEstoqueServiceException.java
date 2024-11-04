package br.com.pupposoft.poc.springbatch.processador.baixaestoque.exception;

import br.com.pupposoft.poc.springbatch.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class AcessoEstoqueServiceException extends SystemBaseException {
	private static final long serialVersionUID = -8768312524994793419L;
	
	private final String code = "erroAcessoEstoqueService";//NOSONAR
	private final String message = "Erro ao acessar o serviço de estoque";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
