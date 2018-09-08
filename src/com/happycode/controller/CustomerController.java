package com.happycode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happycode.po.BaseDict;
import com.happycode.po.Customer;
import com.happycode.po.QueryVo;
import com.happycode.service.BaseDictService;
import com.happycode.service.CustomerService;
import com.happycode.utils.Page;

@Controller
public class CustomerController {
	
	@Autowired
	private BaseDictService baseDictService;
	
	@Autowired
	private CustomerService customerService;
	
	/*
	 * resource.propertiesd的数据
	 */
	@Value("${fromType.code}")
	private String fromTypeCode;
	
	@Value("${industryType.code}")
	private String industryTypeCode;
	
	@Value("${levelType.code}")
	private String levelTypeCode;
	
	@RequestMapping(value = "/customer/list")
	public String list(Model model, QueryVo vo) {
		// 查询固定的选择数据
		List<BaseDict> fromType = baseDictService.selectBaseDictByCode(fromTypeCode);
		List<BaseDict> industryType = baseDictService.selectBaseDictByCode(industryTypeCode);
		List<BaseDict> levelType = baseDictService.selectBaseDictByCode(levelTypeCode);
		
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		
		System.out.println("Name:" + vo.getCustName());
		
		// 通过查询对象，查询数据
		Page<Customer> page = customerService.selectPageByQueryVo(vo);
		
		model.addAttribute("page", page);
		// 回显数据
		model.addAttribute("custName", vo.getCustName());
		model.addAttribute("custSource", vo.getCustSource());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		model.addAttribute("custLevel", vo.getCustLevel());
		
		return "customer";
	}
	
	
	// 去修改页面
	@RequestMapping("/customer/edit")
	public @ResponseBody
	Customer edit(Integer id) {
		return customerService.selectCustomerById(id);
	}
	
	// 保存修改
	@RequestMapping("/customer/update")
	public @ResponseBody
	String update(Customer customer) {
		customerService.updateCustomerById(customer);
		return "ok";
	}
	
	// 删除用户
	@RequestMapping("/customer/delete")
	public @ResponseBody
	String delete(Integer id) {
		customerService.deleteCustomerById(id);
		return "ok";
	}
}
