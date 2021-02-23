package com.jmpaniego;

import com.jmpaniego.entities.Address;
import com.jmpaniego.entities.Client;
import com.jmpaniego.repositories.AddressRepository;
import com.jmpaniego.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsSpringApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(ClientRepository clientRepository, AddressRepository addressRepository){
		return args -> {
			Address address1 = new Address();
			address1.setCalle("6");
			address1.setNumero(24);

			Client client1 = new Client();
			client1.setFirstName("Juan");
			client1.setLastName("Pan");
			client1.setBalance(100.0);
			client1.setAddress(address1);

			Address address2 = new Address();
			address2.setCalle("6");
			address2.setNumero(24);
			Client client2 = new Client();
			client2.setFirstName("Lorena");
			client2.setLastName("Pri");
			client2.setBalance(200.0);
			client2.setAddress(address2);

			clientRepository.save(client1);
			clientRepository.save(client2);

		};
	}
}
