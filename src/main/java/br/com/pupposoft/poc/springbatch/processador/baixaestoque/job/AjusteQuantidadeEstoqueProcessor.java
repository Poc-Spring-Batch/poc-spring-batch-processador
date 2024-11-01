package br.com.pupposoft.poc.springbatch.processador.baixaestoque.job;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;

@Component
public class AjusteQuantidadeEstoqueProcessor implements ItemProcessor<List<Produto>, List<Estoque>> {

	@Override
	public List<Estoque> process(List<Produto> produtos) throws Exception {
		return produtos.stream().map(p -> new Estoque(p.getId(), p.getQuantidade())).toList();
	}

}
