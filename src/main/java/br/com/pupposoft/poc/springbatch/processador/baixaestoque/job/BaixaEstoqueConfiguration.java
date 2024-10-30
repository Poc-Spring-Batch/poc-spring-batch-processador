package br.com.pupposoft.poc.springbatch.processador.baixaestoque.job;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Estoque;
import br.com.pupposoft.poc.springbatch.processador.baixaestoque.domain.Produto;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BaixaEstoqueConfiguration {

    private final PlatformTransactionManager transactionManager;
    
	@Bean
    public Step baixaEstoqueStep(
    		@Qualifier("produtoNaoProcessadoReader") ItemReader<List<Produto>> reader, 
    		@Qualifier("baixaEstoqueProcessadoWritter") ItemWriter<Estoque> writer, 
    		JobRepository jobRepository) {
        
    	return new StepBuilder("baixa-estoque-step", jobRepository)
                .<List<Produto>, Estoque>chunk(10, transactionManager)
                .reader(reader)
                //.processor(processor())
                .writer(writer)
                .build();
    }
	
    @Bean
    public Job baixaEstoquejob(Step baixaEstoqueStep, JobRepository jobRepository) {
        return new JobBuilder("baixa-stock-job", jobRepository)
                .start(baixaEstoqueStep)
                //.next(moverArquivosStep(jobRepository))
                .incrementer(new RunIdIncrementer())
                .build();
    }
	
}