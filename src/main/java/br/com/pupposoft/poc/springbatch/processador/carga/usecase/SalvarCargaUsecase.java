package br.com.pupposoft.poc.springbatch.processador.carga.usecase;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.springbatch.processador.carga.domain.Carga;
import br.com.pupposoft.poc.springbatch.processador.carga.gateway.FileGateway;
import br.com.pupposoft.poc.springbatch.processador.carga.gateway.JobGateway;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalvarCargaUsecase {

	private final FileGateway fileGateway;
	private final JobGateway jobGateway;

	public void salvar(Carga carga) {
		fileGateway.salvar(carga);
		jobGateway.execute(carga);
	}
}
