package com.foxconn.iot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foxconn.iot.entity.CompanyRelationEntity;

public interface CompanyRelationRepository extends JpaRepository<CompanyRelationEntity, Integer> {

	@Query(value = "select a.ancestor from CompanyRelationEntity a where a.descendant=:descendant order by a.depth desc")
	List<Long> queryAncestorByDescendant(@Param("descendant") long descendant);

	@Query(value = "select a.descendant from CompanyRelationEntity a where a.ancestor=:ancestor order by a.depth desc")
	List<Long> queryDescendantByAncestor(@Param("ancestor") long ancestor);

	@Modifying
	@Query(value = "delete from CompanyRelationEntity a where a.ancestor=:ancestor")
	void deleteByAncestor(@Param("ancestor") long ancestor);

	@Modifying
	@Query(value = "delete from CompanyRelationEntity a where a.descendant=:descendant")
	void deleteByDescendant(@Param("descendant") long descendant);
	
	@Query(value = "select a from CompanyRelationEntity a order by a.descendant, a.depth desc")
	List<CompanyRelationEntity> findAll();
}
