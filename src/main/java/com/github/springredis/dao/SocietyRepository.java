package com.github.springredis.dao;

import com.github.springredis.model.Society;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */
@Repository
public interface SocietyRepository extends CrudRepository<Society, String> {
    List<Society> findByIdAndName(String id, String name);
}
