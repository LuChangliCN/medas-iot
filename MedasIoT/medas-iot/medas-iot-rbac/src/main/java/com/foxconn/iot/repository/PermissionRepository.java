package com.foxconn.iot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foxconn.iot.entity.MenuEntity;
import com.foxconn.iot.entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {

	PermissionEntity findById(int id);
	
	@Modifying
	@Query(value = "update PermissionEntity a set a.menus=:menus where a.id=:id")
	void updateMenusById(@Param("menus") List<MenuEntity> menues, @Param("id") int id);
	
	@Modifying
	@Query(value = "update PermissionEntity a set a.status=:status where a.id=:id")
	void updateStatusById(@Param("status") int status, @Param("id") int id);
	
	@Query(value = "select a.menus from PermissionEntity a where a.id=:id")
	List<MenuEntity> queryMenusById(@Param("id") int id);
	
	List<PermissionEntity> findByIdIn(List<Integer> ids);
}
