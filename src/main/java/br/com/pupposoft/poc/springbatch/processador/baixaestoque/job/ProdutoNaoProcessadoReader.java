package br.com.pupposoft.poc.springbatch.processador.baixaestoque.job;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.exception.AcessoProdutoServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProdutoNaoProcessadoReader implements ItemReader<List<Produto>> {

	@Value("${produto.base-url}")
	private String produtoBaseUrl;
	
	private final WebClient.Builder webClientBuilder;
	
	@Override
	public List<Produto> read() {
		try {

			final String url = produtoBaseUrl + "/poc/spring-batch/legado/v1/carrinho-compras/status/ABERTO/produtos";
			WebClient webClient = webClientBuilder.baseUrl(url).build();
			
			List<Produto> produtos = webClient
			.get()
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<List<Produto>>() {})
			.block();
			
			if(produtos.isEmpty()) {
				return null;
			}
			
			return produtos;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoProdutoServiceException();
		}
	}
	
}
