package com.iths.airtravels.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.iths.airtravels.entity.AuthGroup;

import java.util.List;

@Repository
public interface AuthGroupRepository extends CrudRepository<AuthGroup, Long> {
    List<AuthGroup> findByUsername(String username);
}