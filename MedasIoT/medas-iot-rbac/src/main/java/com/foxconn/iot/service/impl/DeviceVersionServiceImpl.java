package com.foxconn.iot.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.foxconn.iot.dto.DeviceVersionDto;
import com.foxconn.iot.entity.DeviceTypeEntity;
import com.foxconn.iot.entity.DeviceVersionEntity;
import com.foxconn.iot.entity.DeviceVersionVo;
import com.foxconn.iot.exception.BizException;
import com.foxconn.iot.repository.DeviceRepository;
import com.foxconn.iot.repository.DeviceTypeRepository;
import com.foxconn.iot.repository.DeviceVersionRepository;
import com.foxconn.iot.service.DeviceVersionService;
import com.foxconn.iot.support.Snowflaker;
import com.mysql.cj.util.StringUtils;

@Service
public class DeviceVersionServiceImpl implements DeviceVersionService {

	@Autowired
	private DeviceVersionRepository deviceVersionRepository;
	@Autowired
	private DeviceTypeRepository deviceTypeRepository;
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Value("${iot.support.file-server}")
	private String fileServer;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void create(DeviceVersionDto version) {
		
		/** 移动图片 */
		String url = String.format("%s/move", fileServer);
		MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
		requestEntity.add("type", "/img/device/");
		requestEntity.add("file", version.getImageUrl());
		JSONObject json = restTemplate.postForEntity(url, requestEntity, JSONObject.class).getBody();
		if (json == null || !json.containsKey("code") || json.getInteger("code") != 1) {
			throw new BizException("Move device image failed");
		}
		version.setImageUrl(json.getString("filePath"));
		
		DeviceVersionEntity entity = new DeviceVersionEntity();
		BeanUtils.copyProperties(version, entity);
		entity.setId(Snowflaker.getId());
		DeviceTypeEntity deviceType = deviceTypeRepository.findById(version.getDeviceTypeId());
		if (deviceType == null) {
			throw new BizException("Invalid device type");
		}
		entity.setDeviceType(deviceType);
		deviceVersionRepository.save(entity);
	}

	@Override
	public void save(DeviceVersionDto version) {
		DeviceVersionEntity entity = deviceVersionRepository.findById(version.getId());
		if (entity == null) {
			throw new BizException("Invalid device version");
		}
		if (!version.getImageUrl().equalsIgnoreCase(entity.getImageUrl())) {
			/** 刪除旧图片 */
			String del = String.format("%s/delete?file=%s", fileServer, entity.getImageUrl());
			restTemplate.delete(del);
			
			/** 移动图片 */
			String url = String.format("%s/move", fileServer);
			MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
			requestEntity.add("type", "/img/device/");
			requestEntity.add("file", version.getImageUrl());
			JSONObject json = restTemplate.postForEntity(url, requestEntity, JSONObject.class).getBody();
			if (json == null || !json.containsKey("code") || json.getInteger("code") != 1) {
				throw new BizException("Move device image failed");
			}
			entity.setImageUrl(json.getString("filePath"));
		}
		if (!StringUtils.isNullOrEmpty(version.getHardVersion())) {
			entity.setHardVersion(version.getHardVersion());
		}
		entity.setDetails(version.getDetails());
		deviceVersionRepository.save(entity);
	}

	@Override
	public List<DeviceVersionDto> queryByDeviceType(long type) {
		List<DeviceVersionVo> vos = deviceVersionRepository.queryByDeviceType(type);
		List<DeviceVersionDto> dtos = new ArrayList<>();
		for (DeviceVersionVo vo : vos) {
			DeviceVersionDto dto = new DeviceVersionDto();
			BeanUtils.copyProperties(vo, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		long count = deviceRepository.countByVersion(id);
		if (count > 0) {
			throw new BizException("Delete this version of the device before deleting");
		}
		deviceVersionRepository.deleteById(id);
	}

	@Override
	public DeviceVersionDto queryLatestVersion(long type) {
		List<DeviceVersionVo> vos = deviceVersionRepository.queryByDeviceType(type);
		if (vos.size() > 0) {
			DeviceVersionDto version = new DeviceVersionDto();
			BeanUtils.copyProperties(vos.get(0), version);
			return version;
		}
		return null;
	}

}
