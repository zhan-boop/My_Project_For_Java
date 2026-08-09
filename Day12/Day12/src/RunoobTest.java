import java.util.*;
public class RunoobTest{
    public static void main(String[] args){
        //创建HashMap对象Sites（键值对时需要使用）
        HashMap<Integer,String>Sites=new HashMap<Integer, String>();
        //添加键值对
        Sites.put(1,"Google");
        Sites.put(2,"firefox");
        Sites.put(3,"Taobao");
        Sites.put(4,"zhihu");
        Sites.put(5,"Google");
        System.out.println(Sites);
        //输出key与value值
        for(Integer i:Sites.keySet()){
            System.out.println("key: "+i+" value: "+Sites.get(i));
        }
        System.out.println("==================");
        //统计结果，存入新的键值对中，并进行输出
        Map<String,Integer>map=new HashMap<String, Integer>();
        for(String value:Sites.values())
        {
            map.put(value, map.getOrDefault(value,0)+1);
        }
        for(Map.Entry<String,Integer>entry:map.entrySet()){
            System.out.println("key= "+entry.getKey()+" value= "+entry.getValue());
        }
        System.out.println("==================");
        //遍历键值对(推荐)
        for(Map.Entry<Integer,String>entry:Sites.entrySet()){
            System.out.println("key="+entry.getKey()+"value="+entry.getValue());
        }
        //直接返回所有value值
        for(String value:Sites.values()){
            System.out.println(value+",");
        }

        HashMap<String,String>Sites_back=new HashMap<String,String>();
        Sites_back.put("one","Google");
        Sites_back.put("two","Runoob");
        Sites_back.put("three","Taobao");
        Sites_back.put("four","Zhihu");

        System.out.println(Sites_back);
        //删除
        Sites_back.remove("four");
        System.out.println(Sites_back);
        //计算大小
        System.out.println(Sites_back.size());

        //使用get函数由key获取value
        System.out.println(Sites_back.get("one"));
    }
}

