package com.example.springbatchdemo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class JobInvokerController {
 
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    @Qualifier("csvToDBLoader")
    Job csvToDBLoader;
    
    @Autowired
    @Qualifier("dbToCSVLoader")
    Job dbToCSVLoader;
    
    @Autowired
    @Qualifier("apiToDBLoader")
    Job apiToDBLoader;
    
    @RequestMapping("/load-csv-to-db")
    public String loadCsvToDb() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder()
            								.addLong("time",System.currentTimeMillis())
            								.toJobParameters();
            jobLauncher.run(csvToDBLoader, jobParameters);
            
        return "Batch job has been invoked";
    }
    
    @RequestMapping("/load-db-to-csv")
    public String loadDbToCsv() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder()
            								.addLong("time",System.currentTimeMillis())
            								.toJobParameters();
            jobLauncher.run(dbToCSVLoader, jobParameters);
            
        return "Batch job has been invoked";
    }

    
    @RequestMapping("/load-api-to-db")
    public String loadApiToDb() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder()
            								.addLong("time",System.currentTimeMillis())
            								.toJobParameters();
            jobLauncher.run(apiToDBLoader, jobParameters);
            
        return "Batch job has been invoked";
    }
}