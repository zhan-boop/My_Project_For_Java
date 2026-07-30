public class EmployeeTest {
    public static void main(String[] args){
        Employee emp1=new Employee("张三",8000);//借用构造器，对类中私有变量进行初始化，理解构造器作用，构造器是Java中用于创建和初始化对象的特殊方法
        Employee emp2=new Employee("李四",10000);
        emp1.printInfo();//调用类的方法，进行输出
        emp2.printInfo();

        emp1.raiseSalary(10);
        System.out.println("涨薪后：");
        emp1.printInfo();
    }
}
