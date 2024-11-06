package br.com.pupposoft.poc.springbatch.processador.carga.gateway.filesystem;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.processador.carga.domain.Carga;
import br.com.pupposoft.poc.springbatch.processador.carga.exception.SalvarArquivoException;
import br.com.pupposoft.poc.springbatch.processador.carga.gateway.FileGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileSystemaGateway implements FileGateway {

	@Override
	public void salvar(Carga carga) {
		try {
			
			Path caminhoArquivo = Paths.get(carga.getDiretorio(), carga.getNome());
			
			Files.createDirectories(caminhoArquivo.getParent());

			Files.write(caminhoArquivo, carga.getBinario());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SalvarArquivoException();
		}
	}
}
