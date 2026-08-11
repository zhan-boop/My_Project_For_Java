/*【题1】HashSet去重 — IP黑名单检查
初始化一个黑名单 HashSet：{"10.0.0.5","192.168.1.100","172.16.0.99"}
给你一个访问IP列表，打印每个IP是否被拦截（被拦截=在黑名单中）
提示：set.contains(ip)

【题2】ArrayList排序 — 服务器按名称排序
servers = ["db-03","web-01","cache-02","web-02","db-01"]
用 Collections.sort() 排序后输出

【题3】HashMap + ArrayList 分组 — 日志按级别归类
logs = ["ERROR: timeout","INFO: start","ERROR: disk","WARN: memory","INFO: login"]
要求：用 HashMap<String, List<String>> 按级别分组
输出：{ERROR=[timeout, disk], INFO=[start, login], WARN=[memory]}

        【题4】HashMap 遍历三种方式对比
sales = {"apple":30, "banana":50, "orange":20}
分别用 keySet、values、entrySet 三种方式遍历并注释哪种适合什么场景

【题5】List去重后转回List
ips 有重复项，用 HashSet 去重，然后转回 ArrayList 输出

【题6】综合 — 学生成绩管理系统
HashMap<String,Integer> 存学生成绩
功能：① 打印全部 ② 找最高分(max+entrySet) ③ 找不及格(<60)
④ 按分数降序排列(sorted+Comparator)
*/

/*【题1】HashSet去重 — IP黑名单检查
初始化一个黑名单 HashSet：{"10.0.0.5","192.168.1.100","172.16.0.99"}
给你一个访问IP列表，打印每个IP是否被拦截（被拦截=在黑名单中）
提示：set.contains(ip)
 */
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;
class PracTice{
    public static void main(String[] args){
        //设置黑名单
        HashSet<String>blackip=new HashSet<String>();
        blackip.add("10.0.0.5");
        blackip.add("192.168.1.100");
        blackip.add("172.16.0.99");
        String[] accessIPs = {
                "192.168.1.100",   // 在黑名单
                "192.168.1.50",    // 不在
                "10.0.0.5",        // 在黑名单
                "10.0.0.8",        // 不在
                "172.16.0.99",     // 在黑名单
        };
        for(String ip:accessIPs){
            if(blackip.contains(ip)){
                System.out.println("🚫 " + ip + " → 拦截");
            }
            else{
                System.out.println("✅ " + ip + " → 放行");
            }
        }
        /*
【题2】ArrayList排序 — 服务器按名称排序
        servers = ["db-03","web-01","cache-02","web-02","db-01"]
        用 Collections.sort() 排序后输出*/
        List<String>servers=Arrays.asList("db03","web-01","cache-02","web-02","db-01");
        for (String server:servers){
            System.out.println(server);
        }
        System.out.println("排序后:");
        Collections.sort(servers);
        for(String server:servers){
            System.out.println(server);
        }
        /*
        【题3】HashMap + ArrayList 分组 — 日志按级别归类
        logs = ["ERROR: timeout","INFO: start","ERROR: disk","WARN: memory","INFO: login"]
        要求：用 HashMap<String, List<String>> 按级别分组
        输出：{ERROR=[timeout, disk], INFO=[start, login], WARN=[memory]}
         */
        String[] logs={"ERROR: timeout","INFO: start","ERROR: disk","WARN: memory","INFO: login"};
        HashMap<String,List<String>>groupedLogs=new HashMap<>();
        for(String log:logs){
            String[] part=log.split(":");
            String level=part[0];//级别
            String message=part[1];//消息内容
            if(!groupedLogs.containsKey(level)){
                groupedLogs.put(level,new ArrayList<>());
            }
            //将消息添加到对应级别的列表中
            groupedLogs.get(level).add(message);
        }
        //输出结果
        System.out.println(groupedLogs);
        /* 【题4】HashMap 遍历三种方式对比
sales = {"apple":30, "banana":50, "orange":20}
分别用 keySet、values、entrySet 三种方式遍历并注释哪种适合什么场景
*/
        Map<String,Integer>sales=new HashMap<>();
        sales.put("apple",30);
        sales.put("banana",50);
        sales.put("orange",20);
        //方法一
        for(String name:sales.keySet()){
            System.out.println("key:"+name+"value:"+sales.get(name));
        }
        //方法二
        for(int a:sales.values()){
            System.out.println(a+',');
        }
        //方法三
        for(Map.Entry<String,Integer>entry:sales.entrySet()){
            System.out.println("key："+entry.getKey()+" value: "+entry.getValue());
        }

        /*【题5】List去重后转回List
        ips 有重复项，用 HashSet 去重，然后转回 ArrayList 输出
        */
        //方法一：直接使用hashset去重
       List<String>list= Arrays.asList("192.168.1.1","192.168.1.2","192.168.1.1","192.168.1.3","192.168.1.5");

       //使用list构造hashset去重
        HashSet<String>set=new HashSet<>(list);
        System.out.println(set);
        //方法二：使用hashset后转为list去重
        List<String>distinctList=new ArrayList<>(new HashSet<>(list));
        //输出
        System.out.println(distinctList);
        //如果要保持顺序，用LinkedHashSet
        List<String>orderedList=new ArrayList<>(new LinkedHashSet<>(list));
        System.out.println(orderedList);
        /*【题6】综合 — 学生成绩管理系统
HashMap<String,Integer> 存学生成绩
功能：① 打印全部 ② 找最高分(max+entrySet) ③ 找不及格(<60)
④ 按分数降序排列(sorted+Comparator)
*/
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("张三", 85);
        scores.put("李四", 92);
        scores.put("王五", 58);
        scores.put("赵六", 76);
        scores.put("孙七", 43);
        scores.put("周八", 67);
        scores.put("吴九", 90);
        scores.put("郑十", 55);

        System.out.println("========== 学生成绩管理系统 ==========\n");
        for(Map.Entry<String,Integer>entry: scores.entrySet()){
            System.out.println("name: "+entry.getKey()+" score: "+ entry.getValue());
        }
        int max=0;
        String name=null;
        for(String name_copy:scores.keySet()){
            if(scores.get(name_copy)>max){
                name=name_copy;
                max=scores.get(name_copy);
            }
        }
        System.out.println("max_scores_name: "+ name + ",score: "+max);
        Map<String,Integer>people=new HashMap<>();
        for(Map.Entry<String,Integer>entry:scores.entrySet()){
            if(entry.getValue()<60){
                people.put(entry.getKey(), entry.getValue());
            }
        }
        for(Map.Entry<String,Integer>entry:people.entrySet()){
            if(entry.getValue()<60){
                System.out.println("不及格人姓名："+entry.getKey()+" 其分数为："+entry.getValue());
            }
        }
        // ★ 步骤①：entrySet 转 List（HashMap 无序，必须转 List 才能排序）
        List<Map.Entry<String, Integer>>list_copy = new ArrayList<>(scores.entrySet());

        // ★ 步骤②：Collections.sort + 自定义比较器，按 value 降序
        Collections.sort(list_copy, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();  // o2-o1 = 降序（大→小）
            }
        });

// ★ 步骤③：遍历输出
        for (Map.Entry<String, Integer> entry : list_copy) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}