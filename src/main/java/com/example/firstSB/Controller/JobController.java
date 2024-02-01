package com.example.firstSB.Controller;

import com.example.firstSB.model.Jobs;
import com.example.firstSB.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
 public class JobController {

    @Autowired
    JobService jService;
    @GetMapping ("/all")
    public List<Jobs> getAllJobs() {
        return jService.getAllJobs();
    }
}
