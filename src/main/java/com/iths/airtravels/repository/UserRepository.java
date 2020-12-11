package com.iths.airtravels.repository;

import com.iths.airtravels.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User,Long> {
    User findByUsername (String username);
}
