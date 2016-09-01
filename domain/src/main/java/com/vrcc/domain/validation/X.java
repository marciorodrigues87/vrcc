package com.vrcc.domain.validation;

import static com.vrcc.utils.Characteristics.MAX_BEDS;
import static com.vrcc.utils.Characteristics.MIN_BEDS;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Min(MIN_BEDS)
@Max(MAX_BEDS)
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface X {

}
