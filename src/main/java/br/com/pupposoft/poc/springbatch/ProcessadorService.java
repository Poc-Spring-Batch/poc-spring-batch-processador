package br.com.pupposoft.poc.springbatch;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
public class ProcessadorService {
    public static void main(String[] args) {
    	Locale.setDefault(new Locale( "pt", "BR" ));
        SpringApplication.run(ProcessadorService.class, args);
    }
}
