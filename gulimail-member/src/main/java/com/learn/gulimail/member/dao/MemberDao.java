package com.learn.gulimail.member.dao;

import com.learn.gulimail.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-07-27 04:31:05
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
