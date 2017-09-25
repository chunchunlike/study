package org.xi.quick.test.anontation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnnotationTest {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Integer> integerList = new ArrayList<>();
        Collections.addAll(integerList, 10, 20, 30);

        AnnotationUseCase useCase = new AnnotationUseCase();
        useCase.setName("xish");

        for (Method m : AnnotationUseCase.class.getDeclaredMethods()) {
            System.out.println(m.getName());
            if(m.getName().equals("setName")) {
                m.invoke(useCase,"xish2");
            }

            TestAnnotation testAnnotation = m.getAnnotation(TestAnnotation.class);
            if (testAnnotation != null)
                System.out.println(testAnnotation.id() + "," + testAnnotation.name());
        }
        Method m2 = AnnotationUseCase.class.getDeclaredMethod("getName");
        if(m2!=null) {
            System.out.println(m2.invoke(useCase));
        }

        for(Field f : AnnotationUseCase.class.getDeclaredFields()) {

            TestAnnotation testAnnotation = f.getAnnotation(TestAnnotation.class);
            if (testAnnotation != null) {
                System.out.println(testAnnotation.id() + "," + testAnnotation.name());
            }
        }
    }
}
