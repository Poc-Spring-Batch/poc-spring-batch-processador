package br.com.pupposoft.poc.springbatch.processador.carga.exception;

import br.com.pupposoft.poc.springbatch.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class SalvarArquivoException extends SystemBaseException {
	private static final long serialVersionUID = 4306545258260552901L;
	
	private final String code = "erroSalvarArquivo";//NOSONAR
	private final String message = "Erro ao salvar arquivo";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}