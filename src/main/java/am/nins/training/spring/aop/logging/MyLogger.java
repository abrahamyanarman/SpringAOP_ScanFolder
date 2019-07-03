package am.nins.training.spring.aop.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.Map;

@Component
@Aspect
public class MyLogger {

	@Pointcut("execution(* *(..))&& within(am.nins.training.spring.aop.objects.*)")
	private void allMethods(){

	}

	@Around("@annotation(am.nins.training.spring.aop.anotations.ShowTime)&&allMethods()")
	public Object watchTime(ProceedingJoinPoint joinpoint) {
		long start = System.currentTimeMillis();
		System.out.println("method begin: " + joinpoint.getSignature().toShortString());
		Object output = null;

		int i=0;
		for (Object o:joinpoint.getArgs()){
			System.out.println("param"+(++i)+": "+o);
		}

		try {
			output = joinpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms");
		return output;
	}

	@AfterReturning(pointcut = "@annotation(am.nins.training.spring.aop.anotations.ShowResult)&&allMethods()",returning = "obj")
	public void print(Object obj){
		System.out.println("start printing!");
		if (obj instanceof Set){
			Set set = (Set)obj;
			for (Object object:set) {
				System.out.println(object);
			}
		}else if (obj instanceof Map){
			Map map = (Map)obj;
			for (Object o:map.keySet()) {
				System.out.println("\""+o+"\":\""+map.get(o)+"\"");

			}
			System.out.println("End printing");
		}
	}

}
