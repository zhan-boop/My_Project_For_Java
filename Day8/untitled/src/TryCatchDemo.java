// 场景：读取服务器配置文件，文件可能不存在、格式可能错误
import java.io.*;
import java.util.*;

public class TryCatchDemo {
    public static void main(String[] args) {

        // ========== 情况1：文件不存在 → catch 兜底 ==========
        try {
            FileReader fr = new FileReader("D:/server.conf");  // 这行可能崩
            System.out.println("✅ 配置文件已找到");
        } catch (FileNotFoundException e) {
            // ★ 文件不存在时执行这里，程序不会崩
            System.out.println("⚠ 配置文件不存在，使用默认值");
            System.out.println("   默认: server.port=8080");
        }

        // ========== 情况2：多个 catch，不同异常不同处理 ==========
        String config = "server.port=abcd";  // 模拟配置值（应该写数字）
        String value = config.split("=")[1];  // "abcd"

        try {
            int port = Integer.parseInt(value);  // ★ "abcd" 转数字 → 抛异常
            System.out.println("端口: " + port);
        } catch (NumberFormatException e) {       // ★ 捕获"格式不对"
            System.out.println("❌ 端口格式错误: " + value);
            System.out.println("   使用默认端口 8080");
        } catch (Exception e) {                   // ★ 兜底：捕其他一切异常
            System.out.println("❌ 未知错误: " + e.getMessage());
        }

        // ========== 情况3：finally — 不管成不成功都执行 ==========
        try {
            System.out.println("\n连接数据库...");
            System.out.println("执行查询...");
             int x = 1 / 0;  // 取消注释会走到 catch，但 finally 照样运行
        } catch (Exception e) {
            System.out.println("❌ 数据库出错");
        } finally {
            // ★ 无论是否异常，连接都必须关闭，否则资源泄露
            System.out.println("🔒 关闭数据库连接（finally 一定执行）");
        }

        System.out.println("\n程序正常结束");  // 走到这证明异常没让程序崩
    }
}