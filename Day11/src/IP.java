import java.util.*;

public class IP {
    public static void main(String[] args){
        List<String> ips = Arrays.asList(
                "192.168.1.1", "192.168.1.2", "192.168.1.1",
                "192.168.1.3", "192.168.1.1", "192.168.1.2"
        );
        Map<String,Integer>map=new HashMap<>();
        for(String s:ips){
            map.put(s, map.getOrDefault(s,0)+1);
        }
        for(Map.Entry<String,Integer>entry:map.entrySet()){
            System.out.println("key= "+entry.getKey()+" value= "+entry.getValue());
        }
        String maxkey=null;
        int maxcount=0;
        for(Map.Entry<String,Integer>entry:map.entrySet()){
            if(entry.getValue()>maxcount){
                maxkey=entry.getKey();
                maxcount=entry.getValue();
            }
        }
        System.out.println("最多的ip为："+maxkey+"其值为："+maxcount);
    }

}
