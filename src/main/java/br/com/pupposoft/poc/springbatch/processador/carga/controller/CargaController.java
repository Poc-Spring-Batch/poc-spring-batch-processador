package br.com.pupposoft.poc.springbatch.processador.carga.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("carga")
public class CargaController {

    private final JobLauncher jobLauncher;

    private final Job job;

    @Autowired
    public CargaController(
    		JobLauncher jobLauncher, 
    		@Qualifier("job") Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}



	@GetMapping("/execute")
    public void importaArquivo() {
    	try {
    		JobParameters jobParameters = new JobParameters();
    		jobLauncher.run(job, jobParameters);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);//NOSONAR
		}
    	
    }
	
}
