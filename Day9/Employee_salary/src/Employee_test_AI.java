/**
 * 题1：员工薪资系统 — 继承+多态+动态绑定+instanceof
 * ============================================================
 * ★ 四个考点：
 *   1. super() 调用父类构造器（必须放第一行）
 *   2. @Override 重写 getSalary()
 *   3. 多态数组 Employee[] + 动态绑定（同一调用，不同结果）
 *   4. instanceof + 向下转型（拿回子类特有方法）
 */

// ① 父类 — 定义"骨架"
class Employee {
    // ★ protected：子类可直接访问，不需要 getter
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // ★ 父类的 getSalary()：返回基础工资
    // 子类可以重写它，返回自己计算逻辑
    public double getSalary() {
        return baseSalary;
    }

    public String getName() {
        return name;
    }
}

// ② 子类 Manager — 工资 = 底薪 + 奖金
class Manager extends Employee {
    private double bonus;       // 子类新增字段

    // ★ super(name, baseSalary) 必须放构造器第一行
    // 如果父类没有无参构造器，必须显式调用
    public Manager(String name, double baseSalary) {
        super(name, baseSalary);
        this.bonus = 0;         // 奖金初始为0
    }

    // ★ 重写父类方法：工资 = 底薪 + 奖金
    // @Override 让编译器帮你检查是否真的重写了
    @Override
    public double getSalary() {
        return baseSalary + bonus;
    }

    // ★ 子类特有方法：父类引用拿不到，需要向下转型
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}

// ② 子类 Intern — 工资 = 时薪 × 小时数
class Intern extends Employee {
    private double hourlyRate;  // 时薪
    private int hours;          // 工作时长

    // ★ baseSalary 传 0：实习生没有固定底薪
    public Intern(String name, double hourlyRate, int hours) {
        super(name, 0);
        this.hourlyRate = hourlyRate;
        this.hours = hours;
    }

    // ★ 重写：工资 = 时薪 × 小时数
    @Override
    public double getSalary() {
        return hourlyRate * hours;
    }
}

// ③ main — 多态验证
public class Employee_test_AI {
    public static void main(String[] args) {

        // ★ 多态数组：声明类型是 Employee，实际类型各不相同
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("张三", 8000);              // 普通员工
        staff[1] = new Manager("李四", 10000);              // 经理
        staff[2] = new Intern("王五", 50, 160);             // 实习生

        // ★ 动态绑定验证：同一个 e.getSalary()，调的是各自重写后的版本
        System.out.println("===== 薪资列表 =====");
        for (Employee e : staff) {
            System.out.println(e.getName() + ": ¥" + e.getSalary());
            // 张三: 8000（Employee.getSalary）
            // 李四: 10000（Manager.getSalary，bonus=0）
            // 王五: 8000（Intern.getSalary，50×160）
        }

        // ★ instanceof + 向下转型：拿到子类特有方法 setBonus()
        System.out.println("\n===== 给经理发奖金 =====");
        for (Employee e : staff) {
            if (e instanceof Manager) {         // 判断"你到底是Manager吗？"
                Manager m = (Manager) e;        // ★ 向下转型：强制转换
                m.setBonus(5000);               // 调用子类特有方法
                System.out.println(m.getName() + " 发了5000奖金");
            }
        }

        // 再次验证：经理薪资变了
        System.out.println("\n===== 发奖金后 =====");
        for (Employee e : staff) {
            System.out.println(e.getName() + ": ¥" + e.getSalary());
            // 李四: ¥15000（10000 + 5000）
        }
    }
}
