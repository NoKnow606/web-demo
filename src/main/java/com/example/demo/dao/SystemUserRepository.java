package com.example.demo.dao;

import com.example.demo.domain.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SystemUserRepository extends JpaRepository<SystemUser,Long> {

     SystemUser findByUsername(String username);

     SystemUser findById(long id);

}
