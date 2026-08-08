import java.util.*;

public class LogLevelCounter{
    public static void main(String[] args){
        String[] logs={
                "ERROR 数据库连接超时",
                "INFO  服务启动成功",
                "WARN  内存使用率85%",
                "ERROR 磁盘空间不足",
                "INFO  用户登录",
                "ERROR 空指针异常",
                "WARN  连接池接近上限",
        };
        Map<String,Integer> countMap=new HashMap<>();
        //遍历每条日志
        for(String log:logs){
            //level获取键值,log.split返回一个列表下标从0开始，，
            String level=log.split(" ")[0];
            //利用getOrDefault获取error等警告次数
            int current=countMap.getOrDefault(level,0);
            //存入字典，level为键，current为值
            countMap.put(level,current+1);
        }
        System.out.println("==========日志级别统计==============\n");
        //遍历字典(for循环内部值提取键值，不提取value值)
        for(String level:countMap.keySet()){
            int count=countMap.get(level);
            
        }
        //遍历方式二，直接用字典存储字典，进行输出
        for(Map.Entry<String,Integer>entry: countMap.entrySet()){
            System.out.println(entry.getKey()+" = "+entry.getValue());
        }
        //额外统计
        int total=0;
        for(int v : countMap.values()){
            total+=v;
        }
        System.out.println("\n总日志数: " + total);

        if(countMap.containsKey("ERROR")){
            System.out.println("存在Error日志，需要关注");
        }
    }

}