package com.example.library.config;

import com.example.library.global.utils.DateUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Date;


@RequiredArgsConstructor
@EnableScheduling
@Configuration
@Slf4j
public class BathScheduler {


    private final JobLauncher jobLauncher;
    private final JobConfig jobConfig;
    private final PlatformTransactionManager manager;
    private final JobRepository jobRepository;

    @Scheduled(cron = "0 * * * * *" , zone = "Asia/Seoul") //1분마다
    public void doBatch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info("★★★Batch스케줄러 시작★★★");

        JobParameters jobParameters= new JobParametersBuilder()
                .addString("nowDt", DateUtil.getDate())
                .addLong("time",new Date().getTime())//여러번 돌수 있게 세팅
                .toJobParameters();
        jobLauncher.run(jobConfig.overDueRegJob(manager,jobRepository),jobParameters);
    }
}
