package test_AI_2;
import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.ArrayList;
import java.util.*;
public class CollectionDemo_AI {
    public static void main(String[] args){
        System.out.println("======ArrayList:服务器列表==========");

        //创建+增加
        ArrayList<String> servers=new ArrayList<>();

        servers.add("web-01");
        servers.add("web-02");
        servers.add("web-03");
        servers.add("cache-01");

        //删
        servers.remove(3);
        //改
        servers.set(1,"web-02-update");
        //查询
        System.out.println("第一台:"+servers.get(0));
        System.out.println("======================");
        //遍历
        for(String s:servers){
            System.out.println("-"+s);
        }
        System.out.println("======================");
        if(servers.contains("web-03")){
            System.out.println("web-03在线");
        }
        System.out.println("======================");
        System.out.println("共"+servers.size()+"台");
        // ============================================================
        // ② ArrayList<String[]> — 命令行历史
        // ============================================================
        System.out.println("\n======ArrayList:命令历史====");
        ArrayList<String[]>history=new ArrayList<>();
        history.add(new String[]{"ls","-al","/var/log"});
        history.add(new String[]{"grep","ERROR","app.log"});
        history.add(new String[]{"tar","-zcf","backup.tar.gz","/data"});

        for(int i=0;i<history.size();i++){
            String[] cmd=history.get(i);
            System.out.print("["+i+"]");
            for (String part:cmd){
                System.out.print(part+" ");
            }
            System.out.println();
        }
        // ============================================================
        // ③ HashMap — 服务器状态监控
        // ============================================================
        System.out.println("\n===== HashMap：服务器状态 =====");
        Map<String,Boolean> status=new HashMap<>();
        status.put("web-01",true);
        status.put("web-02",false);
        status.put("web-03",true);
        status.put("web-04",true);
        //查单台
        System.out.println("web-02状态 "+(status.get("web-02")?"在线":"离线"));

        //安全取值：不存在的key取默认值
        boolean backupStatus=status.getOrDefault("backup-01",false);
        System.out.println("backup-01 状态："+backupStatus+"（默认）");

        //遍历所有
        System.out.println("\n全部状态：");
        for(Map.Entry<String,Boolean>entry:status.entrySet()){
            String icon=entry.getValue()? "🟢" : "🔴";
            System.out.println(" "+icon+" "+entry.getKey());
        }
        // ============================================================
        // ④ HashMap + ArrayList — 按状态分组
        // ============================================================
        System.out.println("\n===== HashMap分组：在线/离线 =====");

        ArrayList<String> onlineList=new ArrayList<>();
        ArrayList<String> offlineList=new ArrayList<>();

        for(Map.Entry<String,Boolean>entry:status.entrySet()){
            if(entry.getValue()){
                onlineList.add(entry.getKey());
            }else{
                offlineList.add(entry.getKey());
            }
        }
        System.out.println("在线："+onlineList);
        System.out.println("离线："+offlineList);

        // ============================================================
        // ⑤ HashSet — 去重（日志中去重IP）
        // ============================================================
        System.out.println("\n===== HashSet：日志IP去重 =====");
        String[] logs={
                "192.168.1.10 登录成功",
                "192.168.1.20 登录成功",
                "192.168.1.10 查询数据",    // 重复IP
                "192.168.1.30 登录成功",
                "192.168.1.20 修改配置",    // 重复IP
        };
        HashSet<String>uniqueIPs=new HashSet<>();
        for(String log:logs){
            String ip=log.split(" ")[0];
            uniqueIPs.add(ip);
        }
        System.out.println("独立ip数："+uniqueIPs.size());
        System.out.println("IP列表："+uniqueIPs);

        // ============================================================
        // ⑥ HashSet — 白名单检查
        // ============================================================
        System.out.println("\n===== HashSet：白名单检查 =====");

        HashSet<String> whitelist=new HashSet<>();
        whitelist.add("admin");
        whitelist.add("root");
        whitelist.add("deployer");

        String[] users={"admin","guest","deployer","hacker"};
        for (String user:users){
            if(whitelist.contains(user)){
                System.out.println("  ✅ " + user + " 有权限");
            }
            else{
                System.out.println("  ❌ " + user + " 拒绝访问");
            }
            // ============================================================
            // ⑦ LinkedList — 操作日志（最新在前）
            // ============================================================
            System.out.println("\n===== LinkedList：操作日志 =====");
            LinkedList<String> opLog=new LinkedList<>();
            opLog.addFirst("10:05 重启web-02");
            opLog.addFirst("10:03 部署新版本");
            opLog.addFirst("10:01 数据库备份完成");
            opLog.addFirst("09:58 收到告警");

            System.out.println("最近3条操作：");
            for(int i=0;i<3&&i<opLog.size();i++){
                System.out.println(" "+opLog.get(i));
            }
            System.out.println("\n最早操作 "+opLog.getLast());
        }
    }
}
