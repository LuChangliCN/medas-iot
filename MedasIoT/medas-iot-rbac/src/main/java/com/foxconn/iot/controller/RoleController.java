package com.foxconn.iot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.foxconn.iot.dto.PermissionDto;
import com.foxconn.iot.dto.RoleDto;
import com.foxconn.iot.service.RoleService;
import com.foxconn.iot.support.CommonResponse;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping(value = "/")
	@CommonResponse
	public void create(@Valid @JsonView(RoleDto.RoleCreate.class) @RequestBody RoleDto role, BindingResult result) {
		roleService.create(role);
	}

	@GetMapping(value = "/{id:\\d+}")
	@CommonResponse
	public RoleDto query(@PathVariable(value = "id") long id) {
		return roleService.findById(id);
	}

	@PutMapping(value = "/")
	@CommonResponse
	public void update(@Valid @JsonView(RoleDto.RoleSave.class) @RequestBody RoleDto role, BindingResult result) {
		roleService.save(role);
	}

	@PutMapping(value = "/disable/{id:\\d+}/{status:^[01]$}")
	@CommonResponse
	public void disable(@PathVariable(value = "id") long id, @PathVariable(value = "status") int status) {
		roleService.updateStatusById(status, id);
	}

	@DeleteMapping(value = "/{id:\\d+}")
	@CommonResponse
	public void delete(@PathVariable(value = "id") long id) {
		roleService.deleteById(id);
	}

	@GetMapping(value = "/permissions/{id:\\d+}")
	@CommonResponse
	public List<PermissionDto> queryPermissions(@PathVariable(value = "id") long id) {
		return roleService.queryPermissionsById(id);
	}
}
