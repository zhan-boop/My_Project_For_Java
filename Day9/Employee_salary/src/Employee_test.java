class Employee{
    protected String name;
    protected double baseSalary;
    public Employee(String name,double baseSalary){
        this.name=name;
        this.baseSalary=baseSalary;
    }
    public double getSalary(){return baseSalary;}
    public String getName(){return name;}
}
class Manager extends Employee{
    private double bonus;
    public Manager(String name,double baseSalary){
        super(name,baseSalary);
        bonus=0;
    }
    public void setBonus(double b){bonus=b;}
    public double getSalary(){return baseSalary+bonus;}

}
class Intern extends Employee{
    private double hourlyRate;
    private int hours;
    public Intern(String name,double hourlyRate,int hours){
        super(name,0);
        this.hourlyRate=hourlyRate;
        this.hours=hours;
    }
    public double getSalary(){return hourlyRate*hours;}
}
public class Employee_test{
    public static void main(String[] args){
        Employee[] staff=new Employee[3];
        staff[0]=new Employee("张三",6000);
        staff[1]=new Manager("李四",10000);
        staff[2]=new Intern("王五",50,160);

        for(Employee e:staff){
            System.out.println("姓名："+e.getName()+"薪资为:"+e.getSalary());
        }

        for(Employee e:staff){
            if(e instanceof Manager){
                Manager m=(Manager)e;
                m.setBonus(300);
                System.out.println(e.getName()+"发了300元奖金");
            }
        }
        System.out.println("\n===== 发奖金后 =====");
        for(Employee e:staff){
            System.out.println(e.getName()+": ¥" + e.getSalary());
        }
    }
}