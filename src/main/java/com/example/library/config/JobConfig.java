package com.example.library.config;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.rent.domain.Events;
import com.example.library.domain.rent.infrastructure.entity.RentHistoryEntity;
import com.example.library.domain.rent.infrastructure.entity.RentManagerEntity;
import com.example.library.global.eventListener.SendedMailEvent;
import com.example.library.global.mail.enums.MailType;
import com.example.library.global.mail.mailHistory.MailDto;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfig {
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job overDueRegJob(PlatformTransactionManager transactionManager, JobRepository jobRepository){
        return new JobBuilder("chunkJob",jobRepository)
                .start(overDueRegStep(transactionManager,jobRepository))
                .build()
                ;
    }

    public Step overDueRegStep(PlatformTransactionManager transactionManager,JobRepository jobRepository){
        return new StepBuilder("chunkStep",jobRepository)
                .<RentManagerEntity, RentManagerEntity>chunk(5,transactionManager)
                .reader(rentManagerReader(null))
                .processor(rentManagerProcessor())
                .writer(rentManagerWriter())
                .build()
                ;
    }

    @Bean
    @StepScope //parameter를 사용하는 곳에서 사용
    public JpaPagingItemReader<RentManagerEntity> rentManagerReader(@Value("#{jobParameters[nowDt]}") String nowDt){
        log.info("rentManagerReader 실행됨");
        Map<String,Object> parameterMap =new HashMap<String,Object>();
        parameterMap.put("nowDt",nowDt);

        JpaPagingItemReader<RentManagerEntity> reader = new JpaPagingItemReader<>();
        reader.setPageSize(5);
        reader.setName("reader");
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setParameterValues(parameterMap);
        reader.setQueryString(
                "select c from RentManagerEntity c where userNo in ( select distinct(userNo)from RentHistoryEntity where rentState=0 and haveToReturnDt< :nowDt) and overdueFlg=0");
        return reader;
    }

    @Bean
    public ItemProcessor<RentManagerEntity,RentManagerEntity> rentManagerProcessor(){
        log.info("rentManagerProcessor 실행됨");
        return item -> {
            log.info("process 처리!!");
            item.setOverdueFlg(true);
            Events.raise(new SendedMailEvent(new MailDto(item.getUserNo(), MailType.MAIL_OVERDUE_REQ)));
            return item;
        };

    }

    @Bean
    public JpaItemWriter<RentManagerEntity> rentManagerWriter(){
        log.info("rentManagerWriter 실행됨");
        JpaItemWriter<RentManagerEntity> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }


}
