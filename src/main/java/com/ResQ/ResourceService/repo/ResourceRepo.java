package com.ResQ.ResourceService.repo;

import com.ResQ.ResourceService.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepo extends JpaRepository<Resource, Integer> {
}
