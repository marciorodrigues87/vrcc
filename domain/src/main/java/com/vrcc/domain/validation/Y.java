package com.vrcc.domain.validation;

import static com.vrcc.utils.Characteristics.MAX_Y;
import static com.vrcc.utils.Characteristics.MIN_Y;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Min(MIN_Y)
@Max(MAX_Y)
public @interface Y {

	String message() default "not a valid y";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
