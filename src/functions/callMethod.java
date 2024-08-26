/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package functions;

import java.lang.reflect.Method;

/**
 *
 * @author Hidayah Arif
 */
public class callMethod {

    public static void callMethodUsingReflection(Object obj, String methodName) {
        try {
            // Get the class of the object
            Class<?> clazz = obj.getClass();

            // Get the method by name (assuming it has no parameters)
            Method method = clazz.getMethod(methodName);

            // Invoke the method on the given object
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
