package com.foxconn.iot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_dev_group_relation", uniqueConstraints = {
		@UniqueConstraint(name = "uq_dev_group_relation", columnNames = { "ancestor", "descendant" }) })
public class DeviceGroupRelationEntity {

	@Id
	private long id;

	@Column(name = "ancestor")
	private long ancestor;

	@Column(name = "descendant")
	private long descendant;

	@Column(name = "depth")
	private int depth;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAncestor() {
		return ancestor;
	}

	public void setAncestor(long ancestor) {
		this.ancestor = ancestor;
	}

	public long getDescendant() {
		return descendant;
	}

	public void setDescendant(long descendant) {
		this.descendant = descendant;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
