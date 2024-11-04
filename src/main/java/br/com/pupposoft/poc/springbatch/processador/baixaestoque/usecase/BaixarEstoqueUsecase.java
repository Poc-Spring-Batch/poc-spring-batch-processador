package br.com.pupposoft.poc.springbatch.processador.baixaestoque.usecase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Carrinho;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway.CarrinhoGateway;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway.EstoqueGateway;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaixarEstoqueUsecase {

	private final EstoqueGateway estoqueGateway;
	private final CarrinhoGateway carrinhoGateway;
	
	public void baixar(List<Estoque> estoques) {
		estoqueGateway.baixa(estoques);
		
		List<Carrinho> carrinhos = new ArrayList<>();
		
		estoques.forEach(e -> carrinhos.addAll(e.getCarrinhos()));
		
		carrinhoGateway.concluir(carrinhos);
	}
	
}
