package com.example.springbatchdemo.csvtodb;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbatchdemo.entity.UserEntity;
import com.example.springbatchdemo.repo.UsersRepository;

@Component
public class DBWriter implements ItemWriter<UserEntity>{
	
	@Autowired
	private UsersRepository repo;

	@Override
	@Transactional
	public void write(List<? extends UserEntity> users) throws Exception {
		repo.saveAll(users);
	}
	
}