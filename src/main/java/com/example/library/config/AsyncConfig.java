package com.example.library.config;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("async-thread-");
        executor.setCorePoolSize(1); //최초 스레드풀에 해당 개수만큼 스레드 생성, 살아있는 최소 스레드 수
        executor.setQueueCapacity(1);
        executor.setMaxPoolSize(1); //QueueCapacity큐마저도 잔뜩 찼다면 해당 개수만큼 스레드 추가생성
        executor.setWaitForTasksToCompleteOnShutdown(true); //프로세스 종료 시 큐 대기하던 작업 모두 완료될때까지 종료 보류
        executor.setAwaitTerminationSeconds(10); //추가 생성스레드 경우 해당 시간만큼 대기하다가 삭제된다. 디폴트는 60
        executor.setRejectedExecutionHandler(rejectedExecutionHandler());
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    private RejectedExecutionHandler rejectedExecutionHandler() {
        return (runnable, executor) -> {
            throw new AppException(ErrorCode.SEARCH_THROUGHPUT_EXCEEDED_EXCEPTION);
        };
    }

}
