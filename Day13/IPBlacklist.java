/**
 * 题1：HashSet去重 — IP黑名单检查
 * ★ 知识点：HashSet.contains() 是 O(1) 查找，比 ArrayList.contains() 的 O(n) 快得多
 */
import java.util.*;

public class IPBlacklist {
    public static void main(String[] args) {
        // ① 初始化黑名单（只存IP，不需要value → 用Set不用Map）
        Set<String> blacklist = new HashSet<>();
        blacklist.add("10.0.0.5");
        blacklist.add("192.168.1.100");
        blacklist.add("172.16.0.99");

        // ② 访问IP列表
        String[] accessIPs = {
            "192.168.1.100",   // 在黑名单
            "192.168.1.50",    // 不在
            "10.0.0.5",        // 在黑名单
            "10.0.0.8",        // 不在
            "172.16.0.99",     // 在黑名单
        };

        // ③ 逐个检查
        for (String ip : accessIPs) {
            if (blacklist.contains(ip)) {       // ★ O(1) 哈希查找
                System.out.println("🚫 " + ip + " → 拦截");
            } else {
                System.out.println("✅ " + ip + " → 放行");
            }
        }
    }
}
