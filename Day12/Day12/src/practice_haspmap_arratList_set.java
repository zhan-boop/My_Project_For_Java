import java.util.*;

public class practice_haspmap_arratList_set {
    public static void main(String[] args){
        // 统计 ERROR/WARN/INFO 各出现几次，用 getOrDefault
        String[] logs = {"ERROR:timeout", "INFO:start", "ERROR:disk", "WARN:mem", "ERROR:null"};
        Map<String, Integer> count = new HashMap<>();
        // 你的代码
        for(String log:logs){
            String name=log.split(":")[0];
            int current_figure=count.getOrDefault(name,0)+1;
            count.put(name,current_figure);
        }
        System.out.println(count); // {ERROR=3, INFO=1, WARN=1}
        // 给 List<String> names = ["张三","李四","张三","王五"]，去重输出
        List<String> names = Arrays.asList("张三","李四","张三","王五");
        Set<String> mergedSet = new HashSet<>(names);
        System.out.println(mergedSet);
        // Map<String,Integer> score = {"张三":85, "李四":92, "王五":78}，按分数从高到低打印
        Map<String,Integer>score=new HashMap<>();
        score.put("张三",85);
        score.put("李四",92);
        score.put("王五",78);
        List<Map.Entry<String,Integer>>list=new ArrayList<>(score.entrySet());
        Collections.sort(list,(o1,o2)->o2.getValue()-o1.getValue());
        for(Map.Entry<String,Integer>entry:list){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
