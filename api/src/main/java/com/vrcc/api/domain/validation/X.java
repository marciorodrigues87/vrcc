package com.vrcc.api.domain.validation;

import static com.vrcc.utils.Characteristics.MAX_BEDS;
import static com.vrcc.utils.Characteristics.MIN_BEDS;
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

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Min(MIN_BEDS)
@Max(MAX_BEDS)
@Pattern(regexp = NUMERIC)
public @interface X {

	String message() default "not a valid x";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
