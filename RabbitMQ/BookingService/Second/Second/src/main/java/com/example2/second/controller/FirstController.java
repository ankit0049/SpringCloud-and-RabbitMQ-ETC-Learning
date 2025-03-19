package com.example2.second.controller;

import com.example2.second.entity.Data;
import com.example2.second.service.ServiceData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/first")
public class FirstController {
    private final ServiceData setService;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public FirstController(ServiceData setService, RabbitTemplate rabbitTemplate) {
	   this.setService = setService;
	   this.rabbitTemplate = rabbitTemplate; // ✅ Inject RabbitTemplate
    }

    @PostMapping
    public Data setDetails(@RequestBody Data data) {
	   rabbitTemplate.convertAndSend("second.exchange", "second.routing.key", data);  // ✅ Fix: Use correct exchange name
	   return setService.saveDetails(data);
    }

    @GetMapping
    public List<Data> getDetails() {
	   return setService.findAllDetails();
    }
}
