package com.foxconn.iot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tb_update_log")
@EntityListeners(AuditingEntityListener.class)
public class UpdateRecordEntity {
	
	@Id
	private long id;
	
	@Column(name = "sn", length = 45, nullable = false)
	private String sn;
	
	@Column(name = "app_id", length = 45)
	private String appId;
	
	@Column(name = "app_name", length = 90)
	private String applicationName;
	
	@Column(name = "ver_id")
	private long versionId;
	
	@Column(name = "app_ver", length = 45, nullable = false)
	private String version;
	
	@Column(name = "link", length = 45)
	private String link;
	
	@Column(name="create_on")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public long getVersionId() {
		return versionId;
	}

	public void setVersionId(long versionId) {
		this.versionId = versionId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
}
