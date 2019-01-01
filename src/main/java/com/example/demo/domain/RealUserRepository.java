package com.example.demo.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RealUserRepository extends JpaRepository<RealUser,Long> {

    RealUser findByNameAndAndPassword(String name,String password);

    RealUser findByName(String name);
}
