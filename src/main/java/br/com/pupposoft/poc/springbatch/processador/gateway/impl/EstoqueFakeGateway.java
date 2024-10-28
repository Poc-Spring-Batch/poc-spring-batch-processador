package br.com.pupposoft.poc.springbatch.processador.gateway.impl;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.processador.gateway.EstoqueGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EstoqueFakeGateway implements EstoqueGateway {
	
	@Override
	public Long obterPorProdutoId(Long produtoId) {
		
		long estoque = ThreadLocalRandom.current().nextLong(0,10);
		
		log.warn("Fake estoque={}", estoque);
		
		return estoque;
	}
}
