package com.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.entity.PhysicalDetail;

@Mapper
public interface PhysicalMapper {

	public void insert(PhysicalDetail physical);

	public List<PhysicalDetail> selectByUserId(int userId, long offset, int pageSize);

	public int countByUserId(int userId);

	public PhysicalDetail selectByPk(LocalDateTime createdAt, int userId);

	public void updateByPk(PhysicalDetail physical);

	public void deleteByPk(LocalDateTime createdAt, int userId);

}
