package com.lisz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lisz.bean.User;

import java.util.List;

public interface UserDao extends BaseMapper<User> {
	public List<User> selectUserByCondition();
}
