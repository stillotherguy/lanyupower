package cn.lanyu.converter;

import org.springframework.core.convert.converter.Converter;

import cn.lanyu.insurance.Insurance.Assessment;

public class StringAssessment implements Converter<String,Assessment> {

	@Override
	public Assessment convert(String source) {
		return Assessment.valueOf(source);
	}

}
