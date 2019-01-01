package com.example.demo.dao;

import com.example.demo.domain.Grouping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GroupingRepository extends JpaRepository<Grouping,Long> {

    Grouping findById(long id);

    Grouping findByIdAndStatus(long id, String status);

}
