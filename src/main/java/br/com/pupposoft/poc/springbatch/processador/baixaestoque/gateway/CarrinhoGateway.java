package br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway;

import java.util.List;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Carrinho;

public interface CarrinhoGateway {

	void concluir(List<Carrinho> carrinhos);

}
