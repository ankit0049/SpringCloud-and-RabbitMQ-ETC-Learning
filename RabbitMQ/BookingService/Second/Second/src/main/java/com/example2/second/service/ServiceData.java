package com.example2.second.service;

import com.example2.second.entity.Data;
import com.example2.second.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceData {
    @Autowired
     DataRepository repository;

    public Data saveDetails(Data data){
	  return repository.save(data);
    }

    public List<Data> findAllDetails(){

	   return repository.findAll();
    }
}
