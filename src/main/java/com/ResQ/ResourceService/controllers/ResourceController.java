package com.ResQ.ResourceService.controllers;

import com.ResQ.ResourceService.dtos.ResourceDto;
import com.ResQ.ResourceService.dtos.ResponseDto;
import com.ResQ.ResourceService.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResponseDto responseDto;

    //get all the resources
    @GetMapping("/getAllResources")
    public ResponseDto getAllResources(){
        List<ResourceDto> existingResources = resourceService.getAllResources();

        if(existingResources.size() > 0){
            responseDto.setStatus_code("200");
            responseDto.setMessage("Resources found successfully");
            responseDto.setData(existingResources);
        }else{
            responseDto.setStatus_code("404");
            responseDto.setMessage("No Resources found");
            responseDto.setData(null);
        }
        return responseDto;
    }

    //get a single resource by Id
    @GetMapping("/getSingleResource/{resource_id}")
    public ResponseDto getSingleResourceById(@PathVariable Integer resource_id){
        ResourceDto resource = resourceService.getSingleResourceById(resource_id);
        if(resource != null){
            responseDto.setStatus_code("200");
            responseDto.setMessage("Resource found successfully");
            responseDto.setData(resource);
        }else{
            responseDto.setStatus_code("404");
            responseDto.setMessage("No resources found");
            responseDto.setData(null);
        }
        return responseDto;
    }

    //get resource by resourceName
    @GetMapping("/getResourceByName/{resourceName}")
    public ResponseDto getResourceByName(@PathVariable String resourceName){
        ResourceDto resource = resourceService.getSingleResourceByName(resourceName);
        if(resource != null){
            responseDto.setStatus_code("200");
            responseDto.setMessage("Resource found successfully");
            responseDto.setData(resource);
        }else{
            responseDto.setStatus_code("404");
            responseDto.setMessage("No resources found");
            responseDto.setData(null);
        }
        return responseDto;

    }


    //save a resource in the database
    @PostMapping("/saveResource")
    public ResponseDto saveResource(@RequestBody ResourceDto resourceDto){
        String res = resourceService.saveResource(resourceDto);
        if(res.equals("SUCCESS")){
            responseDto.setStatus_code("201");
            responseDto.setMessage("Resource saved successfully");
            responseDto.setData(resourceDto);
        }else if(res.equals("Same_Resource_Already_Exist")){
            responseDto.setStatus_code("400");
            responseDto.setMessage("Same Resource Already Available");
            responseDto.setData(null);
        } else{
            responseDto.setStatus_code("400");
            responseDto.setMessage("Error");
            responseDto.setData(null);
        }

        return responseDto;
    }

    //update a resource
    @PutMapping("/updateResource/{resource_id}")
    public ResponseDto updateResource(@PathVariable Integer resource_id, @RequestBody ResourceDto resourceDto){
        String res = resourceService.updateResource(resource_id, resourceDto);
        if(res.equals("SUCCESS")){
            responseDto.setStatus_code("201");
            responseDto.setMessage("Resource updated successfully");
            responseDto.setData(resourceDto);
        }else{
            responseDto.setStatus_code("400");
            responseDto.setMessage("Error");
            responseDto.setData(null);
        }

        return responseDto;
    }
}
