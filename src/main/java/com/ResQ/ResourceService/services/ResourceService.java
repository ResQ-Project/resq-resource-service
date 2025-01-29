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

    //save a resource in the database
    public String saveResource(ResourceDto resourceDto){
        try{
            if(resourceRepo.existsById(resourceDto.getResource_id())){
                return VarList.RSP_DUPLICATE;
            }else{
                resourceRepo.save(modelMapper.map(resourceDto, Resource.class));
                return VarList.RSP_SUCCESS;
            }
        }catch (Exception e){
            return VarList.RSP_ERROR;
        }
    }
}
