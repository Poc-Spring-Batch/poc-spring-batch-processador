package br.com.pupposoft.poc.springbatch.processador.usecase;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.springbatch.processador.gateway.EstoqueGateway;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ObterEstoqueCarrinhoUseCase {
	
	private EstoqueGateway estoqueGateway;
	
	public Long obterPorProdutoId(Long produtoId) {
		return estoqueGateway.obterPorProdutoId(produtoId);
	}

}
