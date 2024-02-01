package com.example.firstSB.repository;

import com.example.firstSB.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends MongoRepository<Jobs, Integer> {


}
