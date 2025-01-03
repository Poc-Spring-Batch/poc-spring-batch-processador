package br.com.pupposoft.poc.springbatch.processador.baixaestoque.job;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
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
    		@Qualifier("ajusteQuantidadeEstoqueProcessor")ItemProcessor<List<Produto>, List<Estoque>> processor,
    		@Qualifier("baixaEstoqueProcessadoWritter") ItemWriter<List<Estoque>> writer,
    		JobRepository jobRepository) {
        
    	return new StepBuilder("baixa-estoque-step", jobRepository)
                .<List<Produto>, List<Estoque>>chunk(1, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }
	
	@Bean
	public Step concluirPedidoStep(
			@Qualifier("concluirPedidoTasklet") Tasklet concluirPedidoTasklet, 
			JobRepository jobRepository) {
		
		return new StepBuilder("concluir-pedido-step", jobRepository)
				.tasklet(concluirPedidoTasklet, transactionManager)
				.build();
	}

	@Bean
	@DependsOn(value = {"baixaEstoqueStep", "concluirPedidoStep"})
	public Job baixaEstoquejob(
			@Qualifier("baixaEstoqueStep") Step baixaEstoqueStep,
			@Qualifier("concluirPedidoStep") Step concluirPedidoStep,
			JobRepository jobRepository) {
		return new JobBuilder("baixa-stock-job", jobRepository)
				.start(baixaEstoqueStep)
				.next(concluirPedidoStep)//É chamado após a conclusão do step anterior
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
