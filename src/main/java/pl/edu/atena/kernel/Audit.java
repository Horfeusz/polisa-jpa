package pl.edu.atena.kernel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.interceptor.Interceptors;

@Interceptors(CzasTrwaniaMetodyLogger.class)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR })
public @interface Audit {

}
