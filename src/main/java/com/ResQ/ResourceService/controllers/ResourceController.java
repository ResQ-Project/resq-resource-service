package com.ResQ.ResourceService.controllers;

import com.ResQ.ResourceService.dtos.ResourceDto;
import com.ResQ.ResourceService.dtos.ResponseDto;
import com.ResQ.ResourceService.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResponseDto responseDto;

    //save a resource in the database
    @PostMapping("/saveResource")
    public ResponseDto saveResource(@RequestBody ResourceDto resourceDto){
        String res = resourceService.saveResource(resourceDto);
        if(res.equals("00")){
            responseDto.setStatus_code("201");
            responseDto.setMessage("Resource saved successfully");
            responseDto.setData(resourceDto);
        }else if(res.equals("02")){
            responseDto.setStatus_code("400");
            responseDto.setMessage("Resource Already Exists with that National ID");
            responseDto.setData(resourceDto);
        } else{
            responseDto.setStatus_code("400");
            responseDto.setMessage("Error");
            responseDto.setData(null);
        }

        return responseDto;
    }
}
