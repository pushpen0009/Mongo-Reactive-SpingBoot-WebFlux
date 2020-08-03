package com.micro.secureapp;

import java.util.UUID;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.micro.secureapp.model.User;
import com.micro.secureapp.repo.UserRepo;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoApplication.class, args);
	}

	@Bean  
	ApplicationRunner init(UserRepo repository) {  
		Object[][] data = {  
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"},
				{"sea", "Andrew1"},  
				{"creek", "Andrew2"},  
				{"loaner", "Andrew3"}
		};  
		return args -> {  
			repository  
			.deleteAll()  
			.thenMany(  
					Flux  
					.just(data)  
					.map(array -> {  
						return new User(UUID.randomUUID(), array[0]+" "+array[1]);  
					})  
					.flatMap(repository::save)  
					)  
			.thenMany(repository.findAll())  
			.subscribe(kayak -> System.out.println("Saved " + kayak.toString()));
		};  
	}

}
