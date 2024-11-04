package br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway.flux;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.exception.AcessoEstoqueServiceException;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway.EstoqueGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EstoqueWebFluxGateway implements EstoqueGateway {
	
	@Value("${estoque.base-url}")
	private String estoqueBaseUrl;
	
	private final WebClient.Builder webClientBuilder;
	
	@Override
	public void baixa(List<Estoque> estoques) {
		try {
			
			
			estoques.forEach(e -> {
					final String url = estoqueBaseUrl + "/poc/spring-batch/estoque/v1/produtos/"+ e.getId() +"/decrementar-estoque";
					WebClient webClient = webClientBuilder.baseUrl(url).build();
					webClient
					.patch()
					.bodyValue(new RequestBodyJson(e))
					.retrieve()
					.bodyToMono(String.class)
					.block();
				});


		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoEstoqueServiceException();
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
