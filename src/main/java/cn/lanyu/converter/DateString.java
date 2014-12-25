package cn.lanyu.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import static com.google.common.base.Preconditions.checkNotNull;

public class DateString implements Converter<Date,String>{
	@Override
	public String convert(Date source) {
		checkNotNull(source);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(source);
	}

}
