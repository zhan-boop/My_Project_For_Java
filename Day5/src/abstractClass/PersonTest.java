package abstractClass;
import java.time.*;

public class PersonTest {

    /**
     * ============================
     * 核心知识点1：抽象类
     * ============================
     * 为什么用抽象类？
     *   - Person 本身没有实际意义（不存在"一个人"这种通用实体）
     *   - 但 Employee、Student 都是 Person 的具体形式
     *   - 抽象类 = 定义"骨架"，子类填充"血肉"
     *
     * 关键规则：
     *   - 有抽象方法 → 类必须声明为 abstract
     *   - 抽象类不能 new（new Person() 编译报错）
     *   - 可以定义普通字段和方法（name、getName()）
     */
    static public abstract class Person{
        // ★ 抽象方法：只有声明，没有方法体
        // 作用：强制所有子类必须提供自己的实现
        // 相当于说"你必须告诉我怎么描述自己，但我不规定具体怎么描述"
        public abstract String getDescription();

        // ★ 私有字段 + 公有getter = 封装
        // name 对外不可见，只能通过 getName() 访问
        // 好处：以后想改存储方式（比如存到数据库），外部代码不用动
        private String name;

        // ★ 抽象类也可以有构造器
        // 虽然不能 new Person()，但子类通过 super(name) 调用
        public Person(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }

    /**
     * ============================
     * 核心知识点2：继承 + 方法重写
     * ============================
     * Employee 是 Person 的子类（is-a 关系：Employee IS A Person）
     *
     * 继承做了什么：
     *   - 自动获得父类的 name 字段和 getName() 方法
     *   - 必须实现父类的抽象方法 getDescription()
     *   - 可以添加自己的字段（salary、hireDay）
     */
    static public class Employee extends Person{
        private double salary;
        private LocalDate hireDay;

        // ★ super(name) — 调用父类构造器
        // 必须放在子类构造器第一行
        // 如果父类只有带参构造器，子类必须显式调用 super(参数)
        public Employee(String name, double salary, int year, int month, int day){
            super(name); // ← 把 name 传给父类去存储
            this.salary = salary;
            this.hireDay = LocalDate.of(year, month, day);
        }

        public double getSalary(){
            return salary;
        }

        public LocalDate getHireDay(){
            return hireDay;
        }

        // ★ @Override 注解（建议写但不强制）
        // 作用①：编译器帮你检查是否真的重写了父类方法
        // 作用②：读代码的人一眼看出这是重写
        public String getDescription(){
            // String.format 类似 printf，但不打印而是返回字符串
            // %.2f = 保留两位小数
            return String.format("an employee with a salary of $%.2f", salary);
        }
    }

    /**
     * ============================
     * 核心知识点3：多个子类 → 多态的基础
     * ============================
     * Student 和 Employee 都继承 Person
     * 都实现了 getDescription()，但返回的内容完全不同
     *
     * 这就是多态的核心：
     *   同一个方法名 getDescription()，不同对象执行不同逻辑
     */
    static public class student extends Person{
        private String major;

        public student(String name, String major){
            super(name);
            this.major = major;
        }

        public String getMajor(){
            return major;
        }

        public String getDescription(){
            return String.format("a student majoring in " + major);
        }
    }

    /**
     * ============================
     * 核心知识点4：多态 + 动态绑定
     * ============================
     * 这是 Java 最重要的特性之一
     */
    public static void main(String[] args){
        // ★ 关键：声明类型 vs 实际类型
        // 声明类型是 Person（左边），实际类型是 Employee/student（右边）
        // 变量 people 只知道"这是 Person 数组"
        // 但运行时 JVM 知道每个元素"到底是什么"
        Person[] people = new Person[2];

        // ★ 向上转型（upcasting）：自动完成，无需强转
        // Employee → Person：合法，因为 Employee IS A Person
        people[0] = new Employee("Harry Hacker", 50000, 1989, 10, 1);

        // Student → Person：同样合法
        people[1] = new student("Maria Morris", "computer science");

        // ★ 动态绑定（dynamic binding）：
        // 编译器只看声明类型 Person，所以只能调用 Person 中定义的方法
        // JVM 在运行时根据实际类型，动态选择正确的 getDescription() 版本
        for(Person p : people){
            // p.getName()    → 父类方法，不变（Employee 和 student 都没重写）
            // p.getDescription() → 动态绑定！运行时决定调哪个版本
            //   如果 p 实际是 Employee → 调 Employee.getDescription()
            //   如果 p 实际是 student  → 调 student.getDescription()
            System.out.println(p.getName() + ", " + p.getDescription());
        }

        // ★ 如果想知道 p 的原始类型，用 instanceof
        // if (p instanceof Employee) {
        //     Employee e = (Employee) p; // 向下转型（需要强转）
        //     System.out.println(e.getSalary());
        // }
    }
}
