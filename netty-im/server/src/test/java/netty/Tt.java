package netty;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-10
 */
public class Tt {

    public static void main(String[] args) {
        Class<Ta> taClass = Ta.class;
        for (Type type : taClass.getGenericInterfaces()) {
            System.out.println("type:"+type.getTypeName());
            // 要求 type 是泛型参数
            if(type instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) type;
                // 要求是 Te类
                if(Objects.equals(parameterizedType.getRawType(),Tc.class)){

                    Type[] arguments = parameterizedType.getActualTypeArguments();

                }

            }
        }

        Class<? super Ta> superclass = taClass.getSuperclass();
        System.out.println("superclass"+superclass.getName());

        for (Class<?> anInterface : taClass.getInterfaces()) {
            System.out.println("anInterface:"+anInterface.getName());
        }

        Type genericSuperclass = taClass.getGenericSuperclass();
        System.out.println("genericSuperclass=="+genericSuperclass.getTypeName());

    }
}
