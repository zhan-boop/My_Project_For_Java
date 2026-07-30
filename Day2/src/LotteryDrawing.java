import java.util.*;  // 导入Java工具包，包含Scanner、Arrays等类

public class LotteryDrawing {
    public static void main(String[] args) {
        // ========== 1. 创建输入扫描器 ==========
        // 定义标准流对象，方便后续进行输入
        Scanner in = new Scanner(System.in);

        // ========== 2. 获取用户输入 ==========
        System.out.print("How many numbers do you need to draw? ");
        int k = in.nextInt();  // 读取需要抽取的数字个数

        System.out.print("What is the highest number you can draw? ");
        int n = in.nextInt();  // 读取数字的最大范围（从1到n）

        // ========== 3. 初始化号码池 ==========
        // 创建包含1到n所有数字的数组
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;  // 填充数字：numbers[0]=1, numbers[1]=2, ...
        }

        // ========== 4. 随机抽取k个不重复的数字 ==========
        // 创建结果数组，用于存储抽取的k个数字
        int[] result = new int[k];

        // 循环k次，每次抽取一个数字
        for (int i = 0; i < result.length; i++) {
            // 4.1 生成随机索引：范围在 0 到 n-1 之间
            // Math.random() 返回 [0.0, 1.0) 的随机小数
            // 乘以n后强制转换为int，得到 [0, n-1] 的随机整数
            int r = (int)(Math.random() * n);

            // 4.2 将随机位置上的数字放入结果数组
            result[i] = numbers[r];

            // 4.3 关键步骤：避免重复抽取
            // 将数组中最后一个未被使用的数字（numbers[n-1]）
            // 移动到被取走的位置（numbers[r]）
            numbers[r] = numbers[n - 1];

            // 4.4 缩小可选范围
            // n-- 表示下一次随机选择时，范围缩小1
            // 这样被移动到最后位置的那个数字就不会再被选中
            n--;
        }

        // ========== 5. 排序并输出结果 ==========
        // 对抽取的数字进行升序排序（Arrays类需要导入java.util.Arrays）
        Arrays.sort(result);

        // 输出提示信息
        System.out.println("Bet the following combination. It'll make you rich!");

        // 使用增强for循环遍历并打印每个数字
        for (int r : result) {
            System.out.println(r);  // 每个数字占一行
        }

        // ========== 6. 释放资源 ==========
        // 关闭Scanner，释放系统资源（好的编程习惯）
        in.close();
    }
}