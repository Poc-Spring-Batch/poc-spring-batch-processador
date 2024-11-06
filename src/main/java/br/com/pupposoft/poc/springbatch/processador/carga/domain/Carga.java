package br.com.pupposoft.poc.springbatch.processador.carga.domain;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Carga {
	private String nome;
	private String diretorio;

	private byte[] binario;
	private LocalDateTime dataHora;

	public Carga(String nome, String diretorio, byte[] binario) {
		this.nome = nome;
		this.diretorio = diretorio;
		this.binario = binario;
		this.dataHora = LocalDateTime.now();
	}
}
