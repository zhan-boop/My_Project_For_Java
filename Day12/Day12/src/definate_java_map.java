public class definate_java_map {
    public static void main(String[] args){
        //// 场景：面试官问"HashMap 有几种遍历方式？哪种最快？"
        //3种类
        //1 先获取key，然后使用get函数获取值
        //2 直接根据键值对，弄成集合的形式进行输出，最方便
        //3. 直接获取值
        //// 方式1：keySet + get（效率最低，每次get都要算hash）
        //for (String key : map.keySet()) { map.get(key); }
        //
        //// 方式2：entrySet（★推荐，直接拿键值对）
        //for (Map.Entry<String, Integer> e : map.entrySet()) {
        //    e.getKey(); e.getValue();
        //}
        //
        //// 方式3：values（只要值）
        //for (Integer v : map.values()) { }
        //
        //// 面试答案：需要键和值时用 entrySet（只遍历一次）；
        //// keySet+get 遍历两次（keySet遍历 + get计算hash），慢。
    }
}