package com.happycode.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happycode.mapper.CustomerMapper;
import com.happycode.po.Customer;
import com.happycode.po.CustomerExample;
import com.happycode.po.CustomerExample.Criteria;
import com.happycode.po.QueryVo;
import com.happycode.utils.Page;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	// mapperm, 尝试用Resource注入
	@Resource(name = "customerMapper")
	private CustomerMapper customerMapper;
	
	
	/*
	 * 根据 QueryVo查询Customer
	 */
	@Override
	public Page<Customer> selectPageByQueryVo(QueryVo vo) {
		Page<Customer> page = new Page<Customer>();
		
		// 设置默认页面大小，显示5条记录
		page.setSize(5);
		vo.setSize(5);
		
		CustomerExample example = new CustomerExample();
		
		if (vo != null) {
			
			// 判断当前页
			if (null == vo.getPage()) {
				vo.setPage(1);
			}
			page.setPage(vo.getPage());
			
			// 分页查询设置
			example.setPageSize(vo.getSize());
			example.setStartRow((vo.getPage() -1)*vo.getSize());
			
			Criteria criteria = example.createCriteria();
			
			if (vo.getCustName() != null && !"".equals(vo.getCustName().trim())) {
				criteria.andCustNameLike("%"+vo.getCustName().trim()+"%");
			}
			
			if (vo.getCustSource() != null && !"".equals(vo.getCustSource())) {
				criteria.andCustSourceEqualTo(vo.getCustSource());
			}
			
			if (vo.getCustIndustry() != null && !"".equals(vo.getCustIndustry())) {
				criteria.andCustIndustryEqualTo(vo.getCustIndustry());
			}
			
			if (vo.getCustLevel() != null && !"".equals(vo.getCustLevel())) {
				criteria.andCustLevelEqualTo(vo.getCustLevel());
			}
			
		}
		
		// 查询满足条件的数据
		page.setRows(customerMapper.selectByExample(example));
		// 查询总记录数
		page.setTotal((int)customerMapper.countByExample(example));
		
		return page;
	}


	@Override
	public Customer selectCustomerById(Integer id) {
		return customerMapper.selectByPrimaryKey(id.longValue());
	}
	
	@Override
	public void updateCustomerById(Customer customer) {
		customerMapper.updateByPrimaryKey(customer);
	}
	
	@Override
	public void deleteCustomerById(Integer id) {
		customerMapper.deleteByPrimaryKey(id.longValue());
	}
}
