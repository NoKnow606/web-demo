package com.example.demo.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

   User findByName(String name);

   User findByNameAndAge(String name, Integer age);

   @Query("from User u where u.name=:name")
    User findUser(@Param("name")String name);

   @Modifying
   @Query(value = "update user u set u.name=:name where u.id=:id",nativeQuery = true)
   void updateUserNameById(@Param("id")Long id,@Param("name")String name);

}
