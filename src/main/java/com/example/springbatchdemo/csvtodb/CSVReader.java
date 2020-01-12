package com.example.springbatchdemo.csvtodb;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.example.springbatchdemo.model.UserModel;


public class CSVReader extends FlatFileItemReader<UserModel> {
	
	public CSVReader(Resource resource) {
		
		super();
		
		setResource(resource);
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "userid", "name", "dept", "amount" });
		lineTokenizer.setDelimiter(",");
	    lineTokenizer.setStrict(false);
	    
	    BeanWrapperFieldSetMapper<UserModel> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(UserModel.class);

		DefaultLineMapper<UserModel> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		setLineMapper(defaultLineMapper);
	}
}