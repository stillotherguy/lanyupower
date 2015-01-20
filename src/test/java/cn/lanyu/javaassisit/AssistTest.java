package cn.lanyu.javaassisit;


import java.util.List;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.SignatureAttribute;

import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.Assert;

//import cn.lanyu.JobStarter;

import com.google.common.collect.Lists;
//import com.weaver.teams.monitor.domain.statistic.Statistic;

public class AssistTest {
	//@Test
	public void testAssist() {
		final ClassPool pool = ClassPool.getDefault();
		ClassLoader l = pool.getClassLoader();
		ClassLoader l1 = Thread.currentThread().getContextClassLoader();
		CtClass cl = null;
		try {
			cl = pool.get("com.weaver.teams.monitor.javaassist.TestBean");
			Assert.notNull(cl);
			CtMethod m = cl.getDeclaredMethod("test");
			m.addLocalVariable("hehe", CtClass.intType);
			m.insertAfter("hehe = 1;System.out.println(hehe + $1);");
			Class<?> c = cl.toClass();
			Assert.notNull(c);
			Assert.notNull(c.newInstance());
			((TestBean)c.newInstance()).test("aini");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void testNewMethod() {
		final ClassPool pool = ClassPool.getDefault();
		
		
		try {
			pool.insertClassPath(new ClassClassPath(AbstractBean.class));
			CtClass parent = pool.get("com.weaver.teams.monitor.javaassist.AbstractBean");
			CtClass cl = pool.makeClass("com.weaver.teams.monitor.javaassist.GenerateJob",parent);
			Assert.notNull(cl);
			CtMethod newMethod = CtNewMethod
					.make(Modifier.PUBLIC, CtClass.voidType, "test", new CtClass[] {pool.get("java.lang.String")}, new CtClass[] {pool.get("java.lang.Exception")}, "System.out.println(111111111);", cl);
			String code = "System.out.println(111111111);";
			LocalVariableAttribute l = (LocalVariableAttribute)newMethod.getMethodInfo().getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
			System.out.println(l);
			LocalVariableTableParameterNameDiscoverer ls = new LocalVariableTableParameterNameDiscoverer();
			System.out.println(ls.getParameterNames(AbstractBean.class.getDeclaredMethods()[0]));
			
			
			cl.addMethod(newMethod);
			Class<?> c = cl.toClass(Thread.currentThread().getContextClassLoader());
			Assert.notNull(c);
			/*Assert.notNull(c.newInstance()); 
			((JobStarter)c.newInstance()).test();*/
			((AbstractBean)c.newInstance()).test("hehe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testLogin() {
		final ClassPool pool = ClassPool.getDefault();
		
		try {
			pool.insertClassPath(new ClassClassPath(AbstractBean.class));
			//pool.insertClassPath(new ClassClassPath(Statistic.class));
			pool.importPackage("java.util");
			pool.importPackage("com.google.common.collect");
			pool.importPackage("com.fasterxml.jackson.databind");
			pool.importPackage("com.weaver.teams.util");
			CtClass parent = pool.get("com.weaver.teams.monitor.javaassist.AbstractBean");
			CtClass cl = pool.makeClass("com.weaver.teams.monitor.javaassist.GenerateJob",parent);
			Assert.notNull(cl);
			CtMethod newMethod = CtNewMethod
					.make(Modifier.PUBLIC, CtClass.voidType, "test", new CtClass[] {pool.get("java.lang.String")}, new CtClass[] {pool.get("java.lang.Exception")}, "System.out.println(111111111);", cl);
			String code = "System.out.println(\"START EXECUTING LOGIN TEST\");" +
				"String host = \"http://test.teems.cn\";" +
				"String username = \"stillotherguy@qq.com\";" +
				"String password = \"123456\";" +
				"Map params = new HashMap();"+
				"params.put(\"username\", username);" +
				"params.put(\"password\", password);" +
				"Map map = null;" +
				"try {" +
					"String stUrl = HttpUtils.httpPost(\"https://castest.eteams.cn/v1/tickets\", params);" +
					"ObjectMapper o = new ObjectMapper();" +
					"map = o.readValue(stUrl, Map.class);" +
				"} catch (Exception e) {" +
					"System.out.println(\"fail\");" +
					"return;" +
				"}" +
				"String url = (String)map.get(\"url\");" +
				"if (StringUtils.isEmpty(url)) {" +
					"System.out.println(\"fail\");" +
					"return;" +
				"}" +
				"Map stParam = Maps.newHashMap();" +
				"stParam.put(\"service\", host + \"/eteamslogin\");" +
				"String stUrl = \"https://castest.eteams.cn/v1/tickets\" + url.substring(url.lastIndexOf(\"/\"));" +
				"String serviceTicket = HttpUtils.httpPost(stUrl, stParam);" +
				"if (StringUtils.isEmpty(serviceTicket)) {" +
					"System.out.println(\"fail\");" + 
					"return;" +
				"}" +
				"System.out.println(serviceTicket);";
			newMethod.insertAfter(code);
			
			cl.addMethod(newMethod);
			Class<?> c = cl.toClass(Thread.currentThread().getContextClassLoader());
			Assert.notNull(c);
			/*Assert.notNull(c.newInstance()); 
			((JobStarter)c.newInstance()).test();*/
			((AbstractBean)c.newInstance()).test("hehe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetParamName() throws NotFoundException {
		final ClassPool pool = ClassPool.getDefault();
		CtClass parent = pool.get("org.springframework.scheduling.quartz.QuartzJobBean");
		CtMethod cm = parent.getDeclaredMethod("execute");
		MethodInfo methodInfo = cm.getMethodInfo();
		LocalVariableAttribute l = (LocalVariableAttribute)methodInfo.getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
		List<String> names = Lists.newArrayList();  
		int pos = Modifier.isStatic(cm.getModifiers()) ? 1 : 0;  
		for (int i = 0; i < l.tableLength(); i++)  
		    names.add(l.variableName(0));
		    // paramNames即参数名  
		for (String name:names) {  
		    System.out.println("javaassist================================>" + name);  
		}  
	}
}
