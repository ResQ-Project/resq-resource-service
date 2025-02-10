package com.ResQ.ResourceService.repo;

import com.ResQ.ResourceService.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResourceRepo extends JpaRepository<Resource, Integer> {

    //find resource by resource name
    @Query("SELECT r FROM Resource r WHERE r.category = :category")
    Resource findByCategory(@Param("category") String category);
}
