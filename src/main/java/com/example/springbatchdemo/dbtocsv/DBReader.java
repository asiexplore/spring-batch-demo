package com.example.springbatchdemo.dbtocsv;

import java.util.Collections;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.springbatchdemo.entity.UserEntity;
import com.example.springbatchdemo.repo.UsersRepository;

@Component
public class DBReader {

	@Autowired
	UsersRepository repository;
	
	@Bean
	public RepositoryItemReader<UserEntity> userEntityReader() {

		return new RepositoryItemReaderBuilder<UserEntity>()
					.name("customerItemReader")
					.methodName("findAll")
					.repository(repository)
					.sorts(Collections.singletonMap("userId", Sort.Direction.ASC))
					.build();
	}

}
