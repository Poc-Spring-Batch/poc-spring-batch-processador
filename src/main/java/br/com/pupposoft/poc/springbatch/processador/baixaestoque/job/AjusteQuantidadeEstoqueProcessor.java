package br.com.pupposoft.poc.springbatch.processador.baixaestoque.job;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AjusteQuantidadeEstoqueProcessor implements ItemProcessor<List<Produto>, List<Estoque>> {

	private final AjusteQuantidadeEstoqueProcessor ajusteQuantidadeEstoqueProcessor;
	
	@Override
	public List<Estoque> process(List<Produto> produtos) {
		return ajusteQuantidadeEstoqueProcessor.process(produtos);
	}
}
