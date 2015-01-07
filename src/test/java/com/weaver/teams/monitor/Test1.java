/**
 * 
 */
package com.weaver.teams.monitor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.List;

import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import cn.lanyu.insurance.Insurance;
import cn.lanyu.insurance.Insurance.Assessment;
import cn.lanyu.util.BeanUtils;

import com.google.common.collect.Lists;

class Test1 {
	public static void main(String[] args) throws Exception {
		StandardPasswordEncoder en = new StandardPasswordEncoder();
				System.out.println(en.encode("123456"));
	}
	
	@Test
	public void test2(){
		
	}
}