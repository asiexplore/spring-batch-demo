package com.example.springbatchdemo.dbtocsv;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.springbatchdemo.entity.UserEntity;
import com.example.springbatchdemo.model.UserModel;


@Component
public class UserEntityProcessor implements ItemProcessor<UserEntity, UserModel> {

	@Override
	public UserModel process(UserEntity userEntity) throws Exception {
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userEntity, userModel);
		userModel.setName(userEntity.getName().toLowerCase());
		
		return userModel;
	}

}