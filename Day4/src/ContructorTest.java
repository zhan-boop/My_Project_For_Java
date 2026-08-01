import java.util.Random;  // 显式导入，避免var推断歧义

public class ConstructorTest {

    /**
     * Employee类演示：构造器重载、初始化块、静态变量
     *
     */
    static class Employee {
        // ========== 静态成员（类级别） ==========
        private static int nextId;          // 下一个可用ID，所有实例共享

        // 静态初始化块：类加载时执行一次（类似Spring的@PostConstruct）
        static {
            var generator = new Random();
            nextId = generator.nextInt(10000);  // 初始ID：0-9999随机
        }

        // ========== 实例成员（对象级别） ==========
        private int id;                      // 唯一标识，类似数据库主键
        private String name = "";            // 显式初始化，避免null
        private double salary;

        // 实例初始化块：每次new对象前执行（类似AOP前置增强）
        {
            id = nextId;    // 分配当前ID
            nextId++;       // ID自增，保证唯一性
        }

        // ========== 构造器（三种重载） ==========
        // 构造器1：完整参数
        public Employee(String n, double s) {
            name = n;
            salary = s;
        }

        // 构造器2：仅薪资，名字自动生成（链式调用构造器1）
        public Employee(double s) {
            this("Employee #" + nextId, s);  // this()必须是第一行
        }

        // 构造器3：无参构造器（提供默认值）
        public Employee() {
            // 空构造器，name保持""，salary保持0.0
        }

        // ========== Getter方法 ==========
        public String getName() { return name; }
        public double getSalary() { return salary; }
        public int getId() { return id; }
    }

    // ========== 主方法（测试入口） ==========
    public static void main(String[] args) {
        // 创建员工数组
        var staff = new Employee[3];

        // 使用不同构造器创建对象
        staff[0] = new Employee("Harry", 40000);   // 构造器1：指定姓名+薪资
        staff[1] = new Employee(60000);             // 构造器2：自动生成姓名
        staff[2] = new Employee();                  // 构造器3：全默认

        // 增强for循环遍历输出
        for (Employee e : staff) {
            System.out.println("name=" + e.getName()
                    + ", id=" + e.getId()
                    + ", salary=" + e.getSalary());
        }
    }
}