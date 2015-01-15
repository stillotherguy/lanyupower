package cn.lanyu.converter;

import org.springframework.core.convert.converter.Converter;

import cn.lanyu.insurance.Insurance.Type;

public class StringType implements Converter<String,Type> {

	@Override
	public Type convert(String source) {
		/*switch(source){
		case "0":
			return Type.LEAK;
		case "1":
			return Type.HEATMETER;
		case "2":
			return Type.COLD;
		case "3":
			return Type.OTHER;
		default:
			return Type.OTHER;
		}*/
		return Type.valueOf(source);
	}

}
