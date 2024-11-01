package br.com.pupposoft.poc.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProcessadorController {

	
    private final JobLauncher jobLauncher;

    private final Job job;

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
