package quoteutils.jdbc;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import quoteutils.Lang;

public class LangConverter implements ConversionService{

	@Override
	public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
		//Can convert any value only to Lang
		if(targetType == Lang.class) return true;
		return false;
	}

	@Override
	public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
		//Can convert any value only to Lang
		if(targetType.getType() == Lang.class) return true;
		return false;
	}

	@Override
	public <T> T convert(Object source, Class<T> targetType) {
				
		if(targetType == Lang.class){
			return (T) Lang.fromValue((String) source);
		}
		throw new IllegalArgumentException("Convert from "  + source.getClass() + " to " + targetType + " failed!");
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		
		if(targetType.getType() == Lang.class){
					
			return Lang.fromValue((String) source);
		}
		throw new IllegalArgumentException("Convert from "  + source.getClass() + " to " + targetType + " failed!");
			
	}

}
