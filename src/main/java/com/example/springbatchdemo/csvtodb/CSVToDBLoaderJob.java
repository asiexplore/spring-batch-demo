package com.example.springbatchdemo.csvtodb;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.springbatchdemo.entity.UserEntity;
import com.example.springbatchdemo.model.UserModel;

@Component
public class CSVToDBLoaderJob extends JobExecutionListenerSupport {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Value("${input.file}")
	Resource resource;

	@Autowired
	CSVProcessor csvProcessor;

	@Autowired
	DBWriter dbWriter;

	@Bean(name = "csvToDBLoader")
	public Job csvToDBLoader() {

		Step step = stepBuilderFactory.get("step-1")
									.<UserModel, UserEntity>chunk(1)
									.reader(new CSVReader(resource))
									.processor(csvProcessor)
									.writer(dbWriter)
									.build();

		Job job = jobBuilderFactory.get("csv-to-db-loader-job")
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
