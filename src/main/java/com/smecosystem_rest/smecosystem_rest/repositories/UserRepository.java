package com.smecosystem_rest.smecosystem_rest.repositories;

import com.smecosystem_rest.smecosystem_rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{


}