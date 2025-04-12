package com.example.authbivservice.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Loggable annotation is used to mark classes or methods that should be logged.
 * When applied to a class, all methods within the class will be logged.
 * When applied to a method, only that specific method will be logged.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Loggable {

}
