package com.vrcc.api.domain.validation;

import static com.vrcc.utils.Characteristics.MAX_X;
import static com.vrcc.utils.Characteristics.MIN_X;
import static com.vrcc.utils.Regexes.NUMERIC;
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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Min(MIN_X)
@Max(MAX_X)
@Pattern(regexp = NUMERIC)
@NotBlank
public @interface X {

	String message() default "not a valid x";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
