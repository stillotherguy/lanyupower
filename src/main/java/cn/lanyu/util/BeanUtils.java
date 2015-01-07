package cn.lanyu.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;

public class BeanUtils {
	/**
	 * 属性复制(同种类型）,由于spring和commons的beanutils会复制null值到target对象，所以此处进行了重写
	 * 
	 * @param original
	 * @param dest
	 * @return
	 */
	public static <K> void copyProperties(K original, K dest) {
		Assert.notNull(original, "original must not be null");
		Assert.notNull(dest, "dest must not be null");
		BeanWrapper wrapper = new BeanWrapperImpl(original);
		for (PropertyDescriptor prop : wrapper.getPropertyDescriptors()) {
			if ("class".equals(prop.getName())) {
				continue;
			}
			Method setter = prop.getWriteMethod();
			Method getter = prop.getReadMethod();
			try {
				if (!Modifier.isPublic(getter.getDeclaringClass().getModifiers())) {
					getter.setAccessible(true);
				}
				Object value = getter.invoke(original);
				// 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
				if (value != null) {
					if (!Modifier.isPublic(setter.getDeclaringClass().getModifiers())) {
						setter.setAccessible(true);
					}
					setter.invoke(dest, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static <K> Map toMap(K k) {
		Assert.notNull(k, "param must not be null");
		if (k == null) {
			// throw new IllegalArgumentException("参数不合法");
			return Maps.newHashMap();
		}
		BeanWrapper wrapper = new BeanWrapperImpl(k);
		Map map = null;
		for (PropertyDescriptor d : wrapper.getPropertyDescriptors()) {
			Object b = d.getValue(d.getName());
			if (b != null) {
				map.put(d.getName(), b);
			}
		}
		return map;
	}
}
