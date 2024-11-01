package br.com.pupposoft.poc.springbatch.processador.baixaestoque.job;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.exception.AcessoProdutoServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BaixaEstoqueProcessadoWritter implements ItemWriter<List<Estoque>> {

	@Value("${estoque.base-url}")
	private String estoqueBaseUrl;
	
	private final WebClient.Builder webClientBuilder;
	
	@Override
	public void write(Chunk<? extends List<Estoque>> chunks) throws Exception {
		try {
			
			chunks.forEach(c -> {
				c.forEach(e -> {
					final String url = estoqueBaseUrl + "/poc/spring-batch/estoque/v1/produtos/"+ e.getId() +"/decrementar-estoque";
					WebClient webClient = webClientBuilder.baseUrl(url).build();
					webClient
					.patch()
					.bodyValue(new RequestBodyJson(e))
					.retrieve()
					.bodyToMono(String.class)
					.block();
				});
			});

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoProdutoServiceException();
		}
	}
	
	@Getter
	@AllArgsConstructor
	private class RequestBodyJson {
		private Integer quantidadeDecrementar;
		
		public RequestBodyJson(Estoque estoque) {
			quantidadeDecrementar = estoque.getQuantidade();
		}
		
	}
}
