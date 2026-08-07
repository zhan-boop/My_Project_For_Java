package test2;
/**
 * 题2：银行账户异常处理 — try-catch-finally + 自定义异常
 * ============================================================
 *
 * ★ 知识点1：自定义异常
 *   - class XxxException extends Exception
 *   - 构造器调用 super(message)，把错误消息传给父类
 *
 * ★ 知识点2：throw + throws
 *   - throw = 在方法里"抛出"异常对象
 *   - throws = 在方法签名上声明"我可能抛这个异常"
 *   - 谁调用这个方法，谁就必须 try-catch 处理
 *
 * ★ 知识点3：try-catch-finally
 *   - try：包住可能出错的代码
 *   - catch：出错时跳到这里，程序不崩溃
 *   - finally：不管是否异常，一定执行（关资源、记录日志）
 */
class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    // 存钱 — 不会出错，无需异常
    public void deposit(double amount) {
        balance += amount;
        System.out.println("  存入 ¥" + amount + "，余额: ¥" + balance);
    }

    /**
     * ★ 取钱 — 余额不足时抛出自定义异常
     * throws InsufficientException：告诉调用者"我可能抛这个"
     */
    public void withdraw(double amount) throws InsufficientException {
        if (amount > balance) {
            // ★ throw = 主动抛出异常，方法立即结束
            throw new InsufficientException("余额不足！需要 ¥" + amount + "，当前余额 ¥" + balance);
        }
        balance -= amount;
        System.out.println("  取出 ¥" + amount + "，余额: ¥" + balance);
    }

    public double getBalance() {
        return balance;
    }
}

// ② 自定义异常 — 继承 Exception，构造器传消息给父类
class InsufficientException extends Exception {
    public InsufficientException(String message) {
        super(message);     // 把错误消息传给 Exception
    }
}

public class BankAccount_test_AI {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        System.out.println("初始余额: ¥" + account.getBalance());

        // ===== 第一次取款：正常 =====
        System.out.println("\n--- 取款 ¥500 ---");
        try {
            account.withdraw(500);                    // 不会抛异常
        } catch (InsufficientException e) {
            System.out.println("  ❌ " + e.getMessage());  // 不执行
        } finally {
            // ★ finally：不管 try 是否异常，一定执行
            System.out.println("  [记录] 本次操作已保存");
        }

        // ===== 第二次取款：超额 → 触发异常 =====
        System.out.println("\n--- 取款 ¥2000 ---");
        try {
            account.withdraw(2000);                   // ★ 余额不足，抛异常
        } catch (InsufficientException e) {
            System.out.println("  ❌ " + e.getMessage());  // 捕获并打印错误消息
        } finally {
            System.out.println("  [记录] 本次操作已保存");  // ★ 照样执行
        }

        System.out.println("\n最终余额: ¥" + account.getBalance());
        System.out.println("程序正常结束");  // 异常被捕获，程序没崩溃
    }
}
