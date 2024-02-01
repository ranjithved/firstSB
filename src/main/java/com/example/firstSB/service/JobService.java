package com.example.firstSB.service;

import com.example.firstSB.model.Jobs;
import com.example.firstSB.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    JobsRepository jRepo;

    public List<Jobs> getAllJobs() {
        return jRepo.findAll();
    }
}
