package br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway.flux;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Carrinho;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.exception.AcessoCarrinhoServiceException;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.gateway.CarrinhoGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarrinhoWebFluxGateway implements CarrinhoGateway {

	@Value("${carrinho.base-url}")
	private String carrinhoBaseUrl;
	
	private final WebClient.Builder webClientBuilder;
	
	@Override
	public void concluir(List<Carrinho> carrinhos) {
		try {

			carrinhos.forEach(c -> {
				final String url = carrinhoBaseUrl + "/poc/spring-batch/legado/v1/carrinhos/" + c.getId();
				WebClient webClient = webClientBuilder.baseUrl(url).build();
				
				webClient
				.patch()
				.bodyValue(new RequestBodyJson("FECHADO"))
				.retrieve()
				.bodyToMono(String.class)
				.block();
				
			});
			

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoCarrinhoServiceException();
		}
		
	}
	
	@Getter
	@AllArgsConstructor
	private class RequestBodyJson {
		private String status;
	}

}
