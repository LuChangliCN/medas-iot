package com.foxconn.iot.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tb_role", uniqueConstraints = { @UniqueConstraint(name = "uq_role_name", columnNames = "name"), @UniqueConstraint(name = "up_role_title", columnNames = "title") })
@EntityListeners(AuditingEntityListener.class)
public class RoleEntity {

	@Id
	@Column(name = "id")
	private long id;

	/**
	 * 角色名称
	 */
	@Column(name = "name", length = 90, nullable = false)
	private String name;
	
	/**
	 * 角色標題
	 */
	@Column(name="title", length=45, nullable = false)
	private String title;

	/**
	 * 详情
	 */
	@Column(name = "details", length = 255)
	private String details;

	@Column(name = "status")
	private int status;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_on", updatable = false)
	private Date createOn;

	@ManyToMany(targetEntity = PermissionEntity.class, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_role_permission", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "permission_id", referencedColumnName = "id") })
	private List<PermissionEntity> permissions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public List<PermissionEntity> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionEntity> permissions) {
		this.permissions = permissions;
	}

}
