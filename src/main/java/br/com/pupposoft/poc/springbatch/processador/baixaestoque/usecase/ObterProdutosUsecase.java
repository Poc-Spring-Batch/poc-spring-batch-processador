package br.com.pupposoft.poc.springbatch.processador.baixaestoque.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.StatusPedido;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterProdutosUsecase {

	private final ProdutoGateway produtoGateway;
	
	public List<Produto> obter(){
		return produtoGateway.obterPorStatusPedido(StatusPedido.ABERTO);
	}
}
