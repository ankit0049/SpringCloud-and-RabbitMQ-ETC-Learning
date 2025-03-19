package com.example2.second.service;

import com.example2.second.dto.UserDTO;
import com.example2.second.entity.Data;
import com.example2.second.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class ServiceData {
    @Autowired
     DataRepository repository;


    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public UserDTO getUser(Long userId) {
	   return restTemplate.getForObject("http://USERSERVICE/api/users/" + userId, UserDTO.class);
    }



    public Data saveDetails(Data data){
	  return repository.save(data);
    }

    public List<Data> findAllDetails(){

	   return repository.findAll();
    }
}
