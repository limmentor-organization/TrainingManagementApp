package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.PhysicalDetailDto;
import com.entity.PhysicalDetail;
import com.mapper.PhysicalMapper;
import com.security.UserDetailsImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PhysicalService {

	private PhysicalMapper physicalMapper;

	@Transactional
	public void create(PhysicalDetailDto physicalDto) {
		PhysicalDetail weight = this.physicalMapper.selectByPk(physicalDto.getCreatedAt(), physicalDto.getUserId());
		if (Objects.nonNull(weight)) {
			throw new DuplicateKeyException("data is unique key");
		}
		PhysicalDetail insertedWeight = new PhysicalDetail();
		BeanUtils.copyProperties(physicalDto, insertedWeight);
		this.physicalMapper.insert(insertedWeight);
	}

	public Page<PhysicalDetail> getPhysicalDetails(Pageable pageable, @AuthenticationPrincipal UserDetailsImpl user) {
		int count = this.physicalMapper.countByUserId(user.getId());
		List<PhysicalDetail> list = this.physicalMapper.selectByUserId(user.getId(), pageable.getOffset(),
				pageable.getPageSize());
		return new PageImpl<PhysicalDetail>(list, pageable, count);
	}

	public PhysicalDetail getPhysicalDetail(LocalDateTime createdAt, int userId) {
		return this.physicalMapper.selectByPk(createdAt, userId);
	}

	@Transactional
	public void edit(PhysicalDetailDto physicalDto) {
		PhysicalDetail weight = this.physicalMapper.selectByPk(physicalDto.getCreatedAt(), physicalDto.getUserId());
		if (Objects.isNull(weight)) {
			throw new EmptyResultDataAccessException("udpated entity is not found", 1);
		}
		BeanUtils.copyProperties(physicalDto, weight);
		this.physicalMapper.updateByPk(weight);
	}

	@Transactional
	public void delete(LocalDateTime createdAt, int userId) {
		PhysicalDetail weight = this.physicalMapper.selectByPk(createdAt, userId);
		if (Objects.isNull(weight)) {
			throw new EmptyResultDataAccessException("udpated entity is not found", 1);
		}
		this.physicalMapper.deleteByPk(createdAt, userId);
	}

}
