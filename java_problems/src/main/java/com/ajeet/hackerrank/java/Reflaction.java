/*
 * (c) Copyright 2020 Brite:Bill Ltd.
 *
 * 7 Grand Canal Street Lower, Dublin 2, Ireland
 * info@britebill.com
 * +353 1 661 9426
 */
package com.ajeet.hackerrank.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author <a href="mailto:AjeetKumar.Singh1@britebill.com">Ajeet</a>
 */
public class Reflaction {
    public static void main(String[] args) throws Exception {
        Class student = Class.forName("com.ajeet.hackerrank.java.Student2");
        Method[] methods = student.getDeclaredMethods();

        ArrayList<String> methodList = new ArrayList<>();
        for (Method m : methods) {
            methodList.add(m.getName());
        }
        Collections.sort(methodList);
        for (String name : methodList) {
            System.out.println(name);
        }



        Inner inner = Inner.class.newInstance();
        Class<Inner.Private> privateClass = Inner.Private.class;
        Constructor<?> constructor = privateClass.getDeclaredConstructors()[0];
        constructor.setAccessible(true);

        Object instance = constructor.newInstance(inner);

        Method power = privateClass.getDeclaredMethod("powerof2", int.class);
        power.setAccessible(true);
        Object ans = power.invoke(instance, 18);

        System.out.println(ans);
    }

    static class Inner{
        private class Private{
            private String powerof2(int num){
                return ((num&num-1)==0)?"power of 2":"not a power of 2";
            }
        }
    }
}

class Student2 {
    private String name;
    private String id;
    private String email;

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void anothermethod() {
    }
}
