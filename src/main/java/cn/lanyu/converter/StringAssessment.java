package cn.lanyu.converter;

import org.springframework.core.convert.converter.Converter;

import cn.lanyu.insurance.Insurance.Assessment;

public class StringAssessment implements Converter<String,Assessment> {

	@Override
	public Assessment convert(String source) {
		switch(source){
		case "0":
			return Assessment.VERY_SATISFIED;
		case "1":
			return Assessment.SATISFIED;
		case "2":
			return Assessment.ORDINARY;
		case "3":
			return Assessment.UNSATISFIED;
		case "4":
			return Assessment.COMPLAINT;
		default:
			return Assessment.VERY_SATISFIED;
		}
		//return Assessment.valueOf(source);
	}

}
