package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AuthenticationAspect {
	
	@Before("execution(* *.createProf(..))")
	public void beforeProfCreate(){
		System.out.println("Before createProf()");
	}
}
