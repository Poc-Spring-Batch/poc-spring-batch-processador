package br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
	private Long id;
	private Integer quantidade;
	private List<Carrinho> carrinhos;
}
