package com.redis.om.hashes.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import com.redis.om.spring.annotations.Bloom;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@RedisHash
public class Call {
  @Id 
  private String id;
  
  @Indexed @NonNull
  private long insertUTC;
  
  @NonNull
  private String sessionId;
  
  @Indexed  @NonNull
  private long startUtc;
  
  @Searchable @NonNull
  private long queryNo;
  
  @Indexed @NonNull
  private String controlPolicy;
  
  @NonNull
  private String callStatus;
  
  @NonNull
  private long purgeUTC;
  
  // audit fields
  
  @CreatedDate
  private Date createdDate;
}