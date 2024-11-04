package br.com.pupposoft.poc.springbatch.processador.baixaestoque.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.usecase.BaixarEstoqueUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BaixaEstoqueProcessadoWritter implements ItemWriter<List<Estoque>> {

	
	private final BaixarEstoqueUsecase baixarEstoqueUsecase;
	
	@Override
	public void write(Chunk<? extends List<Estoque>> chunks) throws Exception {
		try {
			List<Estoque> estoques = new ArrayList<Estoque>();
			chunks.forEach(estoques::addAll);
			
			baixarEstoqueUsecase.baixar(estoques);

		} catch (Exception e) {
			log.warn("Error to process writter: {}", e.getMessage());
			throw e;
		}
	}
	

}
