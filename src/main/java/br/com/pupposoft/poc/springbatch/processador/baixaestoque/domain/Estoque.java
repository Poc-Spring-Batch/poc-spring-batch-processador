package br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Estoque {
	private Long id;
	private Integer quantidade;
	
	public Carrinho obterCarrinho() {
		
		//TODO: implementar
		return null;
	}

}
