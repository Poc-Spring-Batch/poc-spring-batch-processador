package br.com.pupposoft.poc.springbatch.processador.carga.controller;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.pupposoft.poc.springbatch.processador.carga.domain.Carga;
import br.com.pupposoft.poc.springbatch.processador.carga.usecase.SalvarCargaUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("carga")
@RequiredArgsConstructor
public class CargaController {

    private final SalvarCargaUsecase salvarCargaUsecase;

    @Value("${carga.input-path}")
    private String diretorio;

    @PostMapping("importar")
	public ResponseEntity<Void> handleFileUpload(@RequestParam("file") MultipartFile file) {

    	try {
    		Carga carga = new Carga("dados.csv" , diretorio, file.getBytes());
			salvarCargaUsecase.salvar(carga);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}


//	@GetMapping("/execute")
//    public void importaArquivo() {
//    	try {
//    		JobParameters jobParameters = new JobParameters();
//    		jobLauncher.run(job, jobParameters);
//
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			throw new RuntimeException(e);//NOSONAR
//		}
//
//    }

}
