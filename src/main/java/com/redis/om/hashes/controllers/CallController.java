package com.redis.om.hashes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.om.hashes.domain.Call;
import com.redis.om.hashes.repositories.CallRepository;

@RestController
@RequestMapping("/api/calls")
public class CallController {

	@Autowired
	private CallRepository callRepository;

	@PostMapping("/")
	public Call save(@RequestBody Call call) {
		return callRepository.save(call);
	}

	@GetMapping("/q")
	public List<Call> findByStartUTCGreaterThanEqualAndStartUtcLessThanEqual(@RequestParam long startTime,
			@RequestParam long endTime) {
		return callRepository.findByStartUtcGreaterThanEqualAndStartUtcLessThanEqual(startTime, endTime);
	}

	@GetMapping("/start/{startTime}")
	public List<Call> findByStartUtcGreaterThanEqual(@PathVariable("startTime") long startTime) {
		return callRepository.findByStartUtcGreaterThanEqual(startTime);
	}
	
	
	@GetMapping("/start")
	public List<Call> findByStartUtcBetween(@RequestParam("startTime") long startTime,@RequestParam("endTime") long endTime) {
		return callRepository.findByStartUtcBetween(startTime, endTime);
	}
	
	  @GetMapping("/policy") 
	 public List<Call> findByControlPolicy(@RequestParam("policy") String policy) 
	  { return callRepository.findByControlPolicy(policy); 
	  }
	  
	  @GetMapping("/controlPolicy") 
		 public Iterable<Call> search(@RequestParam("policy") String policy) 
		  { return callRepository.search(policy); 
		  }
	  
	  @GetMapping("/mobile") 
		 public List<Call> findByQueryNoContainingIgnoreCase(@RequestParam("mobile") String mobile) 
		  { return callRepository.findByQueryNoContainingIgnoreCase(mobile); 
		  }
	  
	 /* 
	  @GetMapping("/exists") boolean isEmailTaken(@RequestParam("email") String
	  email) { return callRepository.existsByEmail(email); }
	  */
	 

}
