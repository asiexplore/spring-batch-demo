package com.example.springbatchdemo.dbtocsv;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.springbatchdemo.entity.UserEntity;
import com.example.springbatchdemo.model.UserModel;

@Component
public class DBToCSVLoaderJob extends JobExecutionListenerSupport {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	RepositoryItemReader<UserEntity> dbReader;
	
	@Autowired
	ItemProcessor<UserEntity, UserModel> userEntityProcessor;
	
	@Autowired
	ItemWriter<UserModel> csvWriter;


	@Bean(name = "dbToCSVLoader")
	public Job dbToCSVLoader() {
		Step step = stepBuilderFactory.get("step1")
									.<UserEntity, UserModel> chunk(10)
								    .reader(dbReader)
								    .processor(userEntityProcessor)
								    .writer(csvWriter)
								    .build();
		
		Job job = jobBuilderFactory.get("db-to-csv-loader-job")
								.incrementer(new RunIdIncrementer())
								.listener(this)
								.start(step)
								.build();
		
		return job;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}
