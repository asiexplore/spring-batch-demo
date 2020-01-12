package com.example.springbatchdemo.dbtocsv;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.example.springbatchdemo.model.UserModel;

@Component
public class CSVWriter {
	@Bean
	 public FlatFileItemWriter<UserModel> writer(){
	  FlatFileItemWriter<UserModel> writer = new FlatFileItemWriter<UserModel>();
	  writer.setResource(new FileSystemResource("C:\\My\\workspace\\TestWS\\spring-batch-demo\\src\\main\\resources\\users.csv"));
	  writer.setLineAggregator(new DelimitedLineAggregator<UserModel>() {{
	   setDelimiter(",");
	   setFieldExtractor(new BeanWrapperFieldExtractor<UserModel>() {{
	    setNames(new String[] { "userId", "name" });
	   }});
	  }});
	  
	  return writer;
	 }	
}
