package com.example.springbatchdemo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbatchdemo.model.UserModel;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

@RestController
public class UserController {

	@Value("${input.file}")
	Resource csvFile;

    @RequestMapping("/users")
    public List<UserModel> readUsersFromCsv() throws IOException {
		MappingIterator<UserModel> userIter = new CsvMapper()
													.readerWithTypedSchemaFor(UserModel.class)
													.readValues(csvFile.getInputStream());
		
		return userIter.readAll();
    }
	
}
