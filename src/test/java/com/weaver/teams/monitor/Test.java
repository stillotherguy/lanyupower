/**
 * 
 */
package com.weaver.teams.monitor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.List;

import com.google.common.collect.Lists;

class Test {
	public static void main(String[] args) throws Exception {
		/*
		 * Socket s = null;
		 * try {
		 * s = new Socket();
		 * s.bind(new InetSocketAddress("localhost", 80));
		 * s.connect();
		 * } catch (Exception e) {
		 * e.printStackTrace();
		 * throw new CheckHostException("无法连接主机", e);
		 * }
		 */
		System.out.println(InetAddress.getByAddress(InetAddress.getByName("www.baidu.com").getAddress()).isReachable(
				5000));
		/*
		 * StringBuilder sb = new StringBuilder();
		 * for (int i = 0; i < ip.length; i++) {
		 * sb.append(UnsignedBytes.toString(ip[i]));
		 * if (i != (ip.length - 1)) {
		 * sb.append(".");
		 * }
		 * }
		 * System.out.println(sb.toString());
		 */
		List<Class> list = Lists.newArrayList();
		ParameterizedType parameterizedType =   
			    (ParameterizedType) list.getClass().getGenericInterfaces()[0];  
			Type genericType = parameterizedType.getActualTypeArguments()[0];  
			System.out.println(genericType);

	}
}