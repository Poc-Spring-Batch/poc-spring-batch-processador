package br.com.pupposoft.poc.springbatch.processador.carga.gateway;

import br.com.pupposoft.poc.springbatch.processador.carga.domain.Carga;

public interface FileGateway {
	
	public void salvar(Carga carga);

}
