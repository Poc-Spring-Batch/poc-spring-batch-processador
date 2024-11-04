package br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Estoque {
	private Long id;
	private Integer quantidade;
	private List<Carrinho> carrinhos;
}
