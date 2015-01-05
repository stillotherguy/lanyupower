package cn.lanyu.converter;

import org.springframework.core.convert.converter.Converter;

import cn.lanyu.auth.Authority;

public class StringAuthority implements Converter<String,Authority> {

	@Override
	public Authority convert(String source) {
		return Authority.valueOf(source);
	}

}
