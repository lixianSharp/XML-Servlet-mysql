﻿package cn.itcast.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.entity.Admin;

public class WebUtils {

	
	@Deprecated
	public static <T> T copyToBean_old(HttpServletRequest request, Class<T> clazz) {
		try {
			// 创建对象
			T t = clazz.newInstance();
			
			// 获取所有的表单元素的名称
			Enumeration<String> enums = request.getParameterNames();
			// 遍历
			while (enums.hasMoreElements()) {
				// 获取表单元素的名称:<input type="password" name="pwd"/>
				String name = enums.nextElement();  // pwd
				// 获取名称对应的值
				String value = request.getParameter(name);
				// 把指定属性名称对应的值进行拷贝
				BeanUtils.copyProperty(t, name, value);
			}
			
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 处理请求数据的封装
	 */
	public static <T> T copyToBean(HttpServletRequest request, Class<T> clazz) {
		try {
			// （注册日期类型转换器）
			// 创建对象
			T t = clazz.newInstance();
			BeanUtils.populate(t, request.getParameterMap());
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
