package com.example.demo.dao;

import com.example.demo.domain.GroupOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GroupOrderRepository extends JpaRepository<GroupOrder, Long> {

    List<GroupOrder> findGroupOrdersByThemeIdAndStatus(long themeId, String status);
    List<GroupOrder> findGroupOrdersByThemeIdAndStatusAndGroupId(long themeId, String status,long groupId);
    GroupOrder findGroupOrderByUserIdAndStatusAndThemeId(long userId, String status, long themId );
}
