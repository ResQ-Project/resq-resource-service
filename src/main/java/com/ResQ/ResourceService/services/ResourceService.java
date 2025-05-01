package com.ResQ.ResourceService.services;

import com.ResQ.ResourceService.dtos.ResourceDto;
import com.ResQ.ResourceService.entities.Resource;
import com.ResQ.ResourceService.repo.ResourceRepo;
import com.ResQ.ResourceService.utils.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ResourceService {

    @Autowired
    private ResourceRepo resourceRepo;

    @Autowired
    private ModelMapper modelMapper;

    //view all the resources
    public List<ResourceDto> getAllResources(){
        List<Resource> existingResources = resourceRepo.findAll();
        return modelMapper.map(existingResources, new TypeToken<ArrayList<ResourceDto>>(){}.getType());
    }

    //get single resource by id
    public ResourceDto getSingleResourceById(Integer resource_id){
        try{
            if(resourceRepo.existsById(resource_id)){
                return modelMapper.map(resourceRepo.findById(resource_id), ResourceDto.class);
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    //get single resource by resource_name //search purpose
    public ResourceDto getSingleResourceByName(String category){
        Resource existingResource = resourceRepo.findByCategory(category);
        if(existingResource == null){
            return null;
        }else{
            return modelMapper.map(existingResource, ResourceDto.class);
        }
    }

    //save a resource in the database
    public String saveResource(ResourceDto resourceDto){
        try{
            if(resourceRepo.existsById(resourceDto.getResource_id())){
                return "Same_Resource_Already_Exist";
            }else{
                resourceRepo.save(modelMapper.map(resourceDto, Resource.class));
                return "SUCCESS";
            }
        }catch (Exception e){
            return "Error";
        }
    }

    //update a resource
    public String updateResource(int resource_id, ResourceDto resourceDto){
        try{
            if(resourceRepo.existsById(resource_id)){
                resourceRepo.save(modelMapper.map(resourceDto, Resource.class));
                return "SUCCESS";
            }else{
                return "Error";
            }
        } catch (Exception e) {
            return "Error";
        }

    }
}
