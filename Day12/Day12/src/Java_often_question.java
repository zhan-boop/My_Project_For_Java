import java.util.*;
import java.util.stream.Collectors;

public class Java_often_question {
    // 场景：日志分析，找出访问量最高的IP
    // 给一个 List<String> 的IP列表，找出出现次数最多的IP
    public static void main(String[] args) {
        List<String> ips = Arrays.asList(
                "192.168.1.1", "192.168.1.2", "192.168.1.1",
                "192.168.1.3", "192.168.1.1", "192.168.1.2"
        );

// 要求：
// 1. 用 HashMap 统计每个IP出现次数（getOrDefault）
        Map<String, Integer> map = new HashMap<>();
        for (String ip : ips) {
            map.put(ip, map.getOrDefault(ip, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue() + "次");
        }
// 2. 找出次数最多的IP（遍历比较，或用 Collections.max）
        String maxip = null;
        int maxcount = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxcount) {
                maxcount = entry.getValue();
                maxip = entry.getKey();
            }
        }
// 3. 输出："192.168.1.1 出现 3 次"
        System.out.println(maxip + "出现了" + maxcount + "次");
        System.out.println("====================================================");
        // 场景：两个系统导出的用户ID列表合并，需要去重
        List<Integer> ids1 = Arrays.asList(101, 102, 103, 104);
        List<Integer> ids2 = Arrays.asList(102, 104, 105, 106);

// 要求：
// 1. 用 HashSet 合并去重
        Set<Integer> mergedSet = new HashSet<>(ids1);
        mergedSet.addAll(ids2);
// 2. 输出：[101, 102, 103, 104, 105, 106]
        //先转换为List，再进行输出
        List<Integer> result = new ArrayList<>(mergedSet);
        //排序使输出更整齐
        Collections.sort(result);
        //输出结果
        System.out.println(result);
// 3. 追问：为什么用 HashSet 而不是 ArrayList.contains()？
//    （HashSet.contains 是 O(1)，ArrayList.contains 是 O(n)）
        System.out.println("=====================================================");
// 场景：商品销量排行，按销量从高到低输出
        Map<String, Integer> sales = new HashMap<>();
        sales.put("Java书", 300);
        sales.put("Python书", 500);
        sales.put("Docker书", 200);
        sales.put("K8s书", 400);

        // ===== 第一步：entrySet 转 List（才能排序,hashMap无序）=====
        List<Map.Entry<String,Integer>>list=new ArrayList<>(sales.entrySet());
        //======第二部排序(降序)===================================
        Collections.sort(list,(o1,o2)->o2.getValue()-o1.getValue());
        //======第三步：遍历输出===================================
        for(Map.Entry<String,Integer>entry:list){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        class Server{
            private String name;
            private String ip;
            public Server(String name,String ip){
                this.name=name;
                this.ip=ip;
            }
            public String getName(){return name;}
            public String GetIp(){return ip;}
            @Override
            public String toString(){return "Server{name='" +name +"',ip='"+ip+"'}";}
        }
        List<Server>servers=Arrays.asList(
                new Server("web-01","192.168.1.10"),
                new Server("web-02","192.168.1.11"),
                new Server("db-01","192.168.1.20"),
                new Server("cache-01","192.168.1.30")
        );
        System.out.println("===========原始列表==========");
        servers.forEach(System.out::println);
        Map<String,Server>serverMap=servers.stream().collect(Collectors.toMap(
                Server::getName,//key映射：主机名
                server->server,//value映射：Server对象本身
                (existing,replacement)->existing
        ));
        System.out.println("\n===Map转换完成===");
        System.out.println("Map 大小"+ serverMap.size());
        //3.转换后server.get("web-01")直接o(1)寻找
        Server web01=serverMap.get("web-01");
        System.out.println("\n===o(1)寻找测试===");
        System.out.println("查找web-01："+web01);

        Server db01=serverMap.get("db-01");
        System.out.println("查找db-01:"+db01);

        Server notExit=serverMap.get("not-exist");
        System.out.println("查找不存在的key:"+notExit);

        //4.再把Map的values()转回List
        List<Server>serverList=new ArrayList<>(serverMap.values());
        System.out.println("\n=== 转回 List ===");
        System.out.println("List 大小: " + serverList.size());
        serverList.forEach(System.out::println);

        // 5. 验证转换前后是否一致（不考虑顺序）
        System.out.println("\n=== 验证 ===");
        System.out.println("原始 List 大小: " + servers.size());
        System.out.println("转换后 List 大小: " + serverList.size());
        System.out.println("包含所有元素: " + serverList.containsAll(servers));
        // 6. 性能对比演示
        System.out.println("\n=== 性能对比 ===");
        System.out.println("Map.get() 时间复杂度: O(1) - 常数时间");
        System.out.println("List.contains() 时间复杂度: O(n) - 线性时间");
        System.out.println("当有 10000 台服务器时：");
        System.out.println("  - Map.get() 只需 1 次哈希计算");
        System.out.println("  - List 查找需要平均 5000 次比较");
    }
}
