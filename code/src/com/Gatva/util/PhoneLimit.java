package com.Gatva.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PhoneLimit {

    String[] phoneStart() default {"135","136","137","138"};
    int phoneNumbers() default 11;
}
