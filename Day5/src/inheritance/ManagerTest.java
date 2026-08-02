package inheritance;
import java.time.*;

public class ManagerTest {

    // 父类：Employee（员工）
    // 关键点：private 属性可以被子类继承（内存中存在），但不能直接访问
    static public class Employee {
        private String name;
        private double salary;
        private LocalDate hireDay;

        // 构造器：有参构造，没有无参构造
        // 注意：子类必须显式调用 super(name, salary, year, month, day)，否则编译报错
        public Employee(String name, double salary, int year, int month, int day) {
            this.name = name;
            this.salary = salary;
            this.hireDay = LocalDate.of(year, month, day);
        }

        public String getName() { return name; }
        public double getSalary() { return salary; }
        public LocalDate getHireDay() { return hireDay; }

        // 普通方法：加薪，由子类继承
        public void raiseSalary(double byPercent) {
            double raise = salary * byPercent / 100;
            salary += raise;
        }
    }

    // 子类：Manager（经理）继承 Employee
    // 关键点：extends 表示继承关系，Java 单继承
    static public class Manager extends Employee {
        private double bonus;  // 子类新增属性

        // 构造器：super() 必须在第一行
        // 重点：父类没有无参构造，这里必须显式调用有参构造
        public Manager(String name, double salary, int year, int month, int day) {
            super(name, salary, year, month, day);  // 初始化父类部分
            bonus = 0;
        }

        // 方法重写（Override）：覆盖父类的 getSalary()
        // 重点：@Override 注解可加可不加，但建议加上，让编译器帮你检查是否正确重写
        public double getSalary() {
            double baseSalary = super.getSalary();  // super 调用父类被隐藏的方法
            return baseSalary + bonus;              // 子类特有逻辑：工资 + 奖金
        }

        public void setBonus(double b) {
            bonus = b;
        }
    }

    public static void main(String[] args) {
        // 创建子类对象，调用子类构造器，内部会先执行 super() 初始化父类
        Manager boss = new Manager("Carl Craker", 80000, 1987, 12, 15);
        boss.setBonus(5000);

        // 多态：向上转型（Employee 引用指向 Manager 对象）
        // 重点：编译看左边（Employee 类型），运行看右边（Manager 对象）
        Employee[] staff = new Employee[3];
        staff[0] = boss;                          // 向上转型：Manager → Employee（自动）
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);

        // 动态绑定：循环调用 e.getSalary()
        // 重点：e 实际指向谁，就调用谁的 getSalary() 方法
        // boss 调用的是重写后的 Manager.getSalary()（含奖金）
        // 其他 Employee 调用的是父类原始的 getSalary()
        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
        }
    }
}