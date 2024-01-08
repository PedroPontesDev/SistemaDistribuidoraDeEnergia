package com.webapp.light.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Endereco;
import com.webapp.light.repositories.ClienteRepository;

import jakarta.persistence.EntityManager;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
    
    	Cliente c1 = new Cliente();
        c1.setEmail("jhonndooe@gmail.com");
        c1.setEndereco(new Endereco(null, null, null, null, false, c1));
        c1.setPassword("12345454");
        c1.setUsername("doee211");

        // Use o EntityManager para persistir a entidade
        entityManager.persist(c1);

        // Flush para garantir que a persistência ocorra imediatamente
        entityManager.flush();

        // Agora você pode usar o repositório também, se necessário
        clienteRepository.save(c1);
    }
}
