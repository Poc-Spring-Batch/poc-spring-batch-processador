package br.com.pupposoft.poc.springbatch.processador.baixaestoque.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;

@Service
public class ProcessarEstoqueUseCase {

	public List<Estoque> processar(List<Produto> produtos){
		return produtos.stream().map(p -> new Estoque(p.getId(), p.getQuantidade())).toList();
	}
	
}
