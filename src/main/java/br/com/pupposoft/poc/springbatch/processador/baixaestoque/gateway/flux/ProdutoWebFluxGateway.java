package br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway.flux;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.StatusPedido;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.exception.AcessoProdutoServiceException;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProdutoWebFluxGateway implements ProdutoGateway {

	@Value("${produto.base-url}")
	private String produtoBaseUrl;
	
	private final WebClient.Builder webClientBuilder;
	
	@Override
	public List<Produto> obterPorStatusPedido(StatusPedido aberto) {
		try {

			final String url = produtoBaseUrl + "/poc/spring-batch/legado/v1/carrinho-compras/status/ABERTO/produtos";
			WebClient webClient = webClientBuilder.baseUrl(url).build();
			
			return webClient
			.get()
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<List<Produto>>() {})
			.block();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoProdutoServiceException();
		}
	}

}
