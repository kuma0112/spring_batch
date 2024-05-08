package com.study.spring_batch.batch;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BatchController {
    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    @GetMapping("/api/job/launch")
    public void launchJob() throws Exception {
        Job job = jobRegistry.getJob("exampleJob");
        jobLauncher.run(job, new JobParameters());
    }
}
