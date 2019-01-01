package com.example.demo.dao;

import com.example.demo.domain.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GroupInfoRepository extends JpaRepository<GroupInfo,Long> {

    GroupInfo findGroupInfoByThemeId(Long themeId);

    List<GroupInfo> findGroupInfosByThemeIdAndCountBefore(long themeId, int count);

    List<GroupInfo> findGroupInfosByThemeId(long themeId);
}
