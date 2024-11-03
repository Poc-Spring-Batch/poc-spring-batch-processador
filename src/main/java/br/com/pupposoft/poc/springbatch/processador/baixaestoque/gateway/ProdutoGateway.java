package br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway;

import java.util.List;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.StatusPedido;

public interface ProdutoGateway {

	List<Produto> obterPorStatusPedido(StatusPedido aberto);

}
