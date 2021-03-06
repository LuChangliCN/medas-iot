package com.foxconn.iot.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class DeviceVersionDto {
	
	@JsonFormat(shape = Shape.STRING)
	private long id;
	
	@NotBlank(message = "版本号不能为空")
	private String version;
	
	@NotBlank(message = "硬件版本号不能为空")
	private String hardVersion;
	
	private String imageUrl;
	
	private String details;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createOn;
	
	@NotNull(message = "设备类型不能为空")
	@JsonFormat(shape = Shape.STRING)
	private long deviceTypeId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHardVersion() {
		return hardVersion;
	}

	public void setHardVersion(String hardVersion) {
		this.hardVersion = hardVersion;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public long getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
}
