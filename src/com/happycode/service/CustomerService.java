package com.happycode.service;

import com.happycode.po.Customer;
import com.happycode.po.QueryVo;
import com.happycode.utils.Page;

public interface CustomerService {
	
	// 根据QueryVo查询数据结果
	public Page<Customer> selectPageByQueryVo(QueryVo vo);
	
	// 根据Id查数据
	public Customer selectCustomerById(Integer id);

	// 根据pojo主键修改数据
	public void updateCustomerById(Customer customer);

	// 删除用户
	public void deleteCustomerById(Integer id);
}
