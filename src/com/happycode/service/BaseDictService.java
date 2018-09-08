package com.happycode.service;

import java.util.List;

import com.happycode.po.BaseDict;

public interface BaseDictService {
	
	// 根据代码查数据
	public List<BaseDict> selectBaseDictByCode(String code);
}
