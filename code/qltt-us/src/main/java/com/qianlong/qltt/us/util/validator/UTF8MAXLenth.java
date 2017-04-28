package com.qianlong.qltt.us.util.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义验证规则注解类：
 * 		字符串utf-8编号的最大长度
 */
@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {UTF8MAXLenthValidator.class})
public @interface UTF8MAXLenth {

    String message() default "字符串长度最大为{max}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

    int max(); 
}
