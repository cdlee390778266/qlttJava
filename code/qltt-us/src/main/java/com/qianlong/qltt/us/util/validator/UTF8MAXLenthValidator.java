package com.qianlong.qltt.us.util.validator;

import java.io.UnsupportedEncodingException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UTF8MAXLenthValidator implements ConstraintValidator<UTF8MAXLenth,String>{

    private int max ;

    @Override
    public void initialize(UTF8MAXLenth annotation) {
        max = annotation.max();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext context){
    	if(str == null){
    		return true;
    	}
        try {
			byte[] bytes = str.getBytes("utf-8");
			return bytes.length <=  max;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return false;
    }
}