package com.happycode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happycode.mapper.BaseDictMapper;
import com.happycode.po.BaseDict;
import com.happycode.po.BaseDictExample;

@Service
public class BaseDictServiceImpl implements BaseDictService {
	
	@Autowired
	private BaseDictMapper baseDictMapper;
	
	public List<BaseDict> selectBaseDictByCode(String code){
		BaseDictExample example = new BaseDictExample();
		// 设置查询条件
		example.createCriteria().andDictTypeCodeEqualTo(code);
		return baseDictMapper.selectByExample(example);
	}
}
