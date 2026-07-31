/**
 * 静态字段演示类
 * 演示静态变量 nextId 如何为所有 Employee1 对象自动生成唯一 ID
 */
public class StaticTest {

    /**
     * 程序入口 main 方法
     * 这是 JVM 启动时第一个执行的方法
     */
    public static void main(String[] args) {

        // 使用 var 关键字创建长度为 3 的 Employee1 数组
        // var 是 Java 10+ 的特性，编译器会自动推断类型为 Employee1[]
        var staff = new Employee1[3];

        // 创建第一个员工对象，传入姓名 "Tom" 和薪资 40000
        // 调用 Employee1 的有参构造器，初始化对象
        staff[0] = new Employee1("Tom", 40000);

        // 创建第二个员工对象，传入姓名 "Dick" 和薪资 60000
        staff[1] = new Employee1("Dick", 60000);

        // 创建第三个员工对象，传入姓名 "Harry" 和薪资 65000
        staff[2] = new Employee1("Harry", 65000);

        // 增强 for 循环（for-each），遍历 staff 数组中的每个 Employee1 对象
        // 每次循环把当前元素赋值给变量 e，类型是 Employee1
        for (Employee1 e : staff) {

            // 调用当前员工对象的 setId() 方法
            // 这个方法会从静态变量 nextId 中获取当前 ID，然后 nextId 自增 1
            e.setId();

            // 打印员工信息：姓名、ID、薪资
            // 注意：这里 e.getSalary() 应该加括号，原代码漏写了
            System.out.println("name=" + e.getName() +
                    ", id=" + e.getId() +
                    ", salary=" + e.getSalary());
        }

        // 调用静态方法 getNextId()，获取下一个可用的 ID
        // 注意：静态方法通过类名调用，不需要创建对象
        int n = Employee1.getNextId();

        // 打印下一个可用的 ID 值
        System.out.println("Next available id=" + n);
    }
}

/**
 * Employee1 类
 * 员工类，包含姓名、薪资、ID 属性
 * 演示静态字段 nextId 的用法
 */
class Employee1 {

    /**
     * 静态字段（类变量）
     * 被 static 修饰，属于类本身，不属于任何对象
     * 在内存中只有一份，所有 Employee1 对象共享
     * 初始值为 1，类加载时初始化
     * 作用：作为 ID 生成器的计数器，保证每个员工的 ID 唯一且连续
     */
    private static int nextId = 1;

    /**
     * 实例字段（对象变量）
     * 没有被 static 修饰，属于每个对象自己
     * 每个 Employee1 对象都有自己的 name、salary、id
     * 互不影响，各自独立
     */
    private String name;      // 员工姓名
    private double salary;    // 员工薪资
    private int id;           // 员工 ID（由 nextId 分配）

    /**
     * 构造器（构造方法）
     * 名称与类名完全相同，没有返回值类型（连 void 都不写）
     * 在 new 对象时自动调用，用于初始化对象的实例字段
     *
     * @param n 员工姓名
     * @param s 员工薪资
     */
    public Employee1(String n, double s) {
        name = n;          // 将参数 n 赋值给实例字段 name
        salary = s;        // 将参数 s 赋值给实例字段 salary
        id = 0;            // 初始 ID 设为 0，稍后由 setId() 分配真实 ID
    }

    /**
     * 实例方法：获取员工姓名
     * 非静态方法，必须通过对象调用（如 e.getName()）
     *
     * @return 员工姓名
     */
    public String getName() {
        return name;       // 返回当前对象的 name 字段
    }

    /**
     * 实例方法：获取员工薪资
     *
     * @return 员工薪资
     */
    public double getSalary() {
        return salary;     // 返回当前对象的 salary 字段
    }

    /**
     * 实例方法：获取员工 ID
     *
     * @return 员工 ID
     */
    public int getId() {
        return id;         // 返回当前对象的 id 字段
    }

    /**
     * 实例方法：给当前员工设置 ID
     * 从静态变量 nextId 中取当前值作为 ID
     * 然后 nextId 自增 1，为下一个员工做准备
     *
     * 关键点：实例方法可以访问静态字段（nextId）
     * 因为静态字段属于类，所有实例都能看到
     */
    public void setId() {
        id = nextId;       // 将当前 nextId 的值赋给当前对象的 id
        nextId++;          // nextId 自增，变为下一个可用 ID
    }

    /**
     * 静态方法：获取下一个可用的 ID
     * 被 static 修饰，属于类，直接通过类名调用
     *
     * 注意：静态方法不能访问实例字段（name、salary、id）
     * 因为还没有对象，所以不能使用 this
     *
     * @return 下一个可用的 ID 值
     */
    public static int getNextId() {
        return nextId;     // 返回静态字段 nextId 的当前值
    }

    /**
     * 额外写的一个 main 方法（仅供测试）
     * 注意：这个 main 方法不是程序入口，程序入口是 StaticTest 的 main
     * 但是每个类都可以有自己的 main 方法，方便单独测试
     * 如果要执行这个 main，需要单独运行 Employee1 类
     */
    public static void main(String[] args) {
        // 创建 Employee1 对象用于测试
        Employee1 e = new Employee1("Harry", 50000);
        // 打印员工姓名
        System.out.println(e.getName());
    }
}