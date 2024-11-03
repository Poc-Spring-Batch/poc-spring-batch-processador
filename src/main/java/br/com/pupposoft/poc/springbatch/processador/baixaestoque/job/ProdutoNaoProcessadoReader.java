package br.com.pupposoft.poc.springbatch.processador.baixaestoque.job;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.usecase.ObterProdutosUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProdutoNaoProcessadoReader implements ItemReader<List<Produto>> {

	private final ObterProdutosUsecase obterProdutosUsecase;
	
	@Override
	public List<Produto> read() {
		try {
			log.info("Start job");

			List<Produto> produtos = obterProdutosUsecase.obter();
			
			if(produtos.isEmpty()) {
				log.info("End job");
				return null;
			}
			
			log.info("reader processed");
			return produtos;

		} catch (Exception e) {
			log.warn("Error to process reader: {}", e.getMessage());
			throw e;
		}
	}
	
}
