package com.foxconn.iot.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

public class PermissionDto {
	
	public interface PermissionCreate {}
	
	public interface PermissionSave extends PermissionCreate {}
	
	@JsonFormat(shape = Shape.STRING)
	@JsonView(PermissionSave.class)
	private long id;
	
	@JsonView(PermissionCreate.class)
	@NotBlank(message = "權限名稱不能為空")
	private String name;
	
	@JsonView(PermissionCreate.class)
	@NotBlank(message="權限標題不能為空")
	private String title;
	
	@JsonView(PermissionCreate.class)
	private String details;
	
	@JsonView(PermissionSave.class)
	private int status;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createOn;
	
	@JsonView(PermissionCreate.class)
	@JsonInclude(value = Include.NON_NULL)
	private List<String[]> menuIds;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<ResourceDto> menuList;
	
	@JsonView(PermissionCreate.class)
	@JsonInclude(value = Include.NON_NULL)
	private List<String[]> buttonIds;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<ResourceDto> buttonList;

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

	public List<String[]> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<String[]> menuIds) {
		this.menuIds = menuIds;
	}

	public List<ResourceDto> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<ResourceDto> menuList) {
		this.menuList = menuList;
	}

	public List<String[]> getButtonIds() {
		return buttonIds;
	}

	public void setButtonIds(List<String[]> buttonIds) {
		this.buttonIds = buttonIds;
	}

	public List<ResourceDto> getButtonList() {
		return buttonList;
	}

	public void setButtonList(List<ResourceDto> buttonList) {
		this.buttonList = buttonList;
	}
	
}
