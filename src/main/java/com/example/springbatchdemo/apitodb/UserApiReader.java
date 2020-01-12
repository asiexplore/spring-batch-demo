package com.example.springbatchdemo.apitodb;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.springbatchdemo.model.UserModel;

public class UserApiReader implements ItemReader<UserModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiReader.class);

    private final String apiUrl;
    private final RestTemplate restTemplate;

    private int nextUserIndex;
    private List<UserModel> userList;

    UserApiReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextUserIndex = 0;
    }

    @Override
    public UserModel read() throws Exception {
        LOGGER.info("Reading the information of the next User");

        if (this.userList == null) {
            userList = readUsersFromApi();
        }

        UserModel nextUser = null;

        if (nextUserIndex < userList.size()) {
            nextUser = userList.get(nextUserIndex);
            nextUserIndex++;
        }

        LOGGER.info("Found user: {}", nextUser);

        return nextUser;
    }

    private List<UserModel> readUsersFromApi() {
        LOGGER.debug("Fetching user data from an external API by using the url: {}", apiUrl);

        ResponseEntity<UserModel[]> response = restTemplate.getForEntity(apiUrl, UserModel[].class);
        UserModel[] userList = response.getBody();
        LOGGER.debug("Found {} users", userList.length);

        return Arrays.asList(userList);
    }
}