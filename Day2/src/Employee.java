public class Employee {
    //私有字段(封装)
    private String name;
    private double salary;

    //构造器
    public Employee(String name,double salary){
        this.name=name;
        this.salary=salary;
    }
    //方法：涨薪
    public void raiseSalary(double percent){
        salary=salary*(1+percent/100);
    }
    //方法：打印信息
    public void printInfo(){
        System.out.println("姓名"+name+",薪资："+salary);
    }
}
