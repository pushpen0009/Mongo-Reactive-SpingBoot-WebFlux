package com.micro.secureapp;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.micro.secureapp.service.MongoWatcherService;

@SpringBootApplication
public class SpringMongoApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(getClass());
	boolean inturrupted=false;
	
	@Autowired
	MongoWatcherService watchService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting commandline..{}",Arrays.asList(args).toString());
		while(!inturrupted) {
			try {
				log.info("Watching for user collection!!!");
				watchService.watch();				
			} catch (Exception e) {
				log.error("Error while watching change stream",e);
			}
			log.debug("Wait for some time..");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				inturrupted=true;
			} //Wait for a while before trying watch again.
		}
	}
}
