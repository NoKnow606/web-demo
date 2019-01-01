package com.example.demo.dao;

import com.example.demo.domain.LogOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public interface LogOrderRepository extends JpaRepository<LogOrder, Long> {

    LogOrder findLogOrderByParentIdAndAndType(long parentId, String type);


    List<LogOrder> findLogOrdersByParentIdAndAndType(long parentId, String type);

    List<LogOrder> findLogOrdersByUserIdAndTypeInAndStatus(long userId,List<String> types,String status);


}
