package Test_Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public  class ParseAnnotation {
	public static void Parse() throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		// TODO Auto-generated method stub
		try {
			Class<?> class1=Class.forName("Shooting1.Player1");
			Annotation ca=class1.getAnnotation(FunctionAnnotation.class);
			String classauthor=((FunctionAnnotation)ca).author();
			System.out.println("classauthor:" + classauthor);
			
	        Class<?> class2=Class.forName("Shooting1.Game_MainPanel_Shooting1");
	        Field[] fields=class2.getDeclaredFields();
	        for(Field f:fields) {//处理每一个方法
				System.out.println(f.getName());
	        }
	        
			Method m=class1.getDeclaredMethod("fire");
			Annotation ma=m.getAnnotation(FunctionAnnotation.class);
			String methodsender=((FunctionAnnotation)ma).sender();
			String methodreceiver=((FunctionAnnotation)ma).receiver();
			String methodfunction=((FunctionAnnotation)ma).function();
			System.out.println("sender:"+methodsender);
			System.out.println("receiver:"+methodreceiver);
			System.out.println("function"+methodfunction);
			System.out.println();
			
			
			//m.invoke(Player1);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
