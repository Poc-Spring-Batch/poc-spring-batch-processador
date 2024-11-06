package br.com.pupposoft.poc.springbatch.processador.carga.job;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class Importacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String cliente;
    private LocalDate nascimento;
    private String evento;
    private LocalDate data;
    private String tipoIngresso;
    private Double valor;
    private LocalDateTime horaImportacao;
    private Double taxaAdm;

	
}
