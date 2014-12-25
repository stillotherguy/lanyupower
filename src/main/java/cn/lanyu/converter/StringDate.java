package cn.lanyu.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringDate implements Converter<String,Date>{
	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.hasText(source)) {
			try {
				return sdf.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
				return new Date();
			}
		}
		return new Date();
		/*if(source.matches("^\\d{}-\\d{}-\\d{}$")) {
			sdf = new SimpleDateFormat("");
		}else {
			sdf = new SimpleDateFormat("");
		}*/
	}
	
	
}
