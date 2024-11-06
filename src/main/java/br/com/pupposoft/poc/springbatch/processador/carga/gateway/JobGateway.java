package br.com.pupposoft.poc.springbatch.processador.carga.gateway;

import br.com.pupposoft.poc.springbatch.processador.carga.domain.Carga;

public interface JobGateway {

	public void execute(Carga carga);

}
