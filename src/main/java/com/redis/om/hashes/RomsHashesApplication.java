package com.redis.om.hashes;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.redis.om.hashes.domain.Role;
import com.redis.om.hashes.domain.User;
import com.redis.om.hashes.domain.Call;
import com.redis.om.hashes.repositories.RoleRepository;
import com.redis.om.hashes.repositories.UserRepository;
import com.redis.om.hashes.repositories.CallRepository;
import com.redis.om.spring.annotations.EnableRedisEnhancedRepositories;

@SpringBootApplication
@EnableRedisEnhancedRepositories(basePackages = "com.redis.om.hashes.*")
public class RomsHashesApplication {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private CallRepository callRepo;

	@Bean
	CommandLineRunner loadTestData() {
		return args -> {

			Role bass = Role.of("BASS");
			Role vocals = Role.of("VOCALS");
			Role guitar = Role.of("GUITAR");
			Role drums = Role.of("DRUMS");

			roleRepo.saveAll(List.of(bass, vocals, guitar, drums));

			User john = User.of("Zack", "de la Rocha", "zack@ratm.com", bass, 1234);
			User tim = User.of("Tim", "Commerford", "tim@ratm.com", vocals,234);
			User tom = User.of("Tom", "Morello", "tom@ratm.com", guitar, 1234);
			User brad = User.of("Brad", "Wilk", "brad@ratm.com", drums, 567);

			userRepo.saveAll(List.of(john, tim, tom, brad));
			userRepo.save(tom); // save again to trigger @LastModifiedDate

			
			  List<String> list = new ArrayList<String>(); list.add("underUsed");
			  list.add("overUsed"); list.add("Barred from calling");
			  list.add("Cannot call till recharged"); list.add("No Data activated");
			  
			  
			  List<String> policy = new ArrayList<String>(); policy.add("123");
			  policy.add("342"); policy.add("223"); policy.add("987"); policy.add("4533");
			  policy.add("9872"); policy.add("2567"); policy.add("1987");
			  policy.add("145331");
			  
			  for(int i=0; i<=3000; i++) { long unixTime = System.currentTimeMillis() /
			  1000L; Random randomizer1 = new Random(); String randomStatus =
			  list.get(randomizer1.nextInt(list.size())); // Random randomizer2 = new
			  String randomPolicy = policy.get(randomizer1.nextInt(policy.size())); 
			  Call johnCall = Call.of(unixTime, "aa-123", unixTime+5000, 9879876543L, randomPolicy,
			  randomStatus, unixTime+10000); callRepo.save(johnCall); }
			 
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RomsHashesApplication.class, args);
	}

}
