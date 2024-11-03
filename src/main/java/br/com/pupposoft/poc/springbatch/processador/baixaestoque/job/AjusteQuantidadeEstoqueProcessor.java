package br.com.pupposoft.poc.springbatch.processador.baixaestoque.job;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.usecase.ProcessarEstoqueUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AjusteQuantidadeEstoqueProcessor implements ItemProcessor<List<Produto>, List<Estoque>> {

	private final ProcessarEstoqueUseCase processarEstoqueUseCase;
	
	@Override
	public List<Estoque> process(List<Produto> produtos) {
		return processarEstoqueUseCase.processar(produtos);
	}
}
