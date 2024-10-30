package br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Produto {
	private Long id;
	private Integer quantidade;
}
