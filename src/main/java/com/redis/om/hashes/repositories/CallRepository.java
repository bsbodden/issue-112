package com.redis.om.hashes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.redis.om.hashes.domain.Call;

@Repository
public interface CallRepository extends CrudRepository<Call, String> {
  
  List<Call> findByControlPolicy(String policy);
  
  Iterable<Call> search(String policy);
  
  List<Call> findByQueryNoContainingIgnoreCase(String mobile);

  List<Call> findByStartUtcGreaterThanEqualAndStartUtcLessThanEqual(long startTime, long endTime);
  
  List<Call> findByStartUtcBetween(long startTime, long endTime);
  
  List<Call> findByStartUtcGreaterThanEqual( long startTime);
  
 // boolean existsByEmail(String email);
}
