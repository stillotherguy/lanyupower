package cn.lanyu.converter;

import org.springframework.core.convert.converter.Converter;

import cn.lanyu.insurance.Insurance.Type;

public class StringType implements Converter<String,Type> {

	@Override
	public Type convert(String source) {
		return Type.valueOf(source);
	}

}
