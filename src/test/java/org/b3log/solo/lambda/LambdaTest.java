package org.b3log.solo.lambda;


import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @Auther: fgm0129
 * @Date: 2024/2/18 11:29
 * @Description
 */
public class LambdaTest {

    public static void main(String[] args) throws Exception {

        BizService bizService = BizService.getInstance();

        DataCenter dataCenter = DataCenter.getInstance();
        dataCenter.handler(bizService::add);

        int result = dataCenter.execute(1, 2);
        System.out.println("result is "+result);
    }

}


interface Handler extends Serializable {
    int calc(Integer a,Integer b);
}


class BizService{

    public static BizService getInstance(){
        return new BizService();
    }
    public int add(Integer a, Integer b){
        return a+b;
    }

    public int sub(Integer a,Integer b){
        return a-b;
    }

    public int multi(Integer a,Integer b){
        return a*b;
    }

}

class  DataCenter {
    Handler handler;

    Method method;

    public static DataCenter getInstance(){
        return new DataCenter();
    }

    void handler(Handler handler){
        this.handler = handler;
        try{
            // 获取 Lambda 表达式的 writeReplace 方法
            Method writeReplace = handler.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);

            // 调用 writeReplace 方法，获取 SerializedLambda 对象
            SerializedLambda serializedLambda = (SerializedLambda) writeReplace.invoke(handler);

            // 输出 Lambda 表达式的相关信息
            System.out.println("Lambda 类名：" + serializedLambda.getImplClass());
            System.out.println("Lambda 方法名：" + serializedLambda.getImplMethodName());
            System.out.println("Lambda 描述符：" + serializedLambda.getImplMethodSignature());

            final String implClassName = serializedLambda.getImplClass().replaceAll("/", ".");
            final Class<?> implClass = Class.forName(implClassName);
            this.method = implClass.getDeclaredMethod(serializedLambda.getImplMethodName(),Integer.class,Integer.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    int execute(Integer a,Integer b){
        try {
          Object result=method.invoke(handler,a,b);
          return (int)result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }


}
