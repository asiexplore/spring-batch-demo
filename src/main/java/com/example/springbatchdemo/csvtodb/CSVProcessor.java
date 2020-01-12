package com.example.springbatchdemo.csvtodb;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.springbatchdemo.entity.UserEntity;
import com.example.springbatchdemo.model.UserModel;


@Component
public class CSVProcessor implements ItemProcessor<UserModel, UserEntity> {

	@Override
	public UserEntity process(UserModel user) throws Exception {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setName(user.getName().toUpperCase());
		
		return userEntity;
	}

}