import java.time.*;
/*定义Employee类*/
public class Employee {
    /*定义三个私有变量，若想获取，得有三个get公有方法进行获取*/
    private String name;
    private double salary;
    private LocalDate hireDay;
    public Employee(String n,double s,int year,int month,int day){
        name=n;
        salary=s;
        hireDay=LocalDate.of(year,month,day);//构造年月日
    }
    /*按需设置方法，对salary进行计算*/
    public void raiseSalary(double byPercent){
        double raise=salary*byPercent/100;
        salary+=raise;
    }
    /*采用隐式参数返回*/
    public String getName(){return name;}
    public double getSalary(){return salary;}
    public LocalDate getHireDay(){return hireDay;}
}

public class EmployeeTest{
    public static void main(String[] args){
        /*定义Employee数组类型，new了三个实例对象*/
        Employee[] staff=new Employee[3];
        staff[0]=new Employee("Carl Cracker",75000,1987,12,15);
        staff[1]=new Employee("Harry Hacker",50000,1989,10,1);
        staff[2]=new Employee("Tony Tester",40000,1990,3,15);
        /*遍历数组，设置每个人的增长值为5%*/
        for(Employee e:staff){
            e.raiseSalary(5);
        }
        /*使用增强for循环，循环遍历staff数组*/
        for(Employee e:staff){
            System.out.println("name="+e.getName()+",salary="+e.getSalary()+",hireDay="+e.getHireDay());
        }

    }
}