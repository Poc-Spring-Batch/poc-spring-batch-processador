package br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway;

import java.util.List;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;

public interface EstoqueGateway {

	void baixa(List<Estoque> estoques);

}
