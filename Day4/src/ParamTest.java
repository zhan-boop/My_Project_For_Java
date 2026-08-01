class ParamTest{
    public static void tripleValue(double percent){
        percent=3*percent;
        System.out.println("End of method:percent="+percent);
    }
     /*定义Employee类进行测试，ps:static在Java里就一个作用：把"属于对象"变成"属于类"，
     加static的本质：切断内部类与外部类对象之间的引用链
     加static = 自由（但失去访问外部实例的能力）；不加static = 绑定（但可以访问外部实例）。
    */
    static class Employee{
        private String name;
        private double salary;

        public Employee(String a,double b){
            name=a;
            salary=b;
        }
        public void raiseSalary(double byPercent){
            double raise=salary*byPercent/100;
            salary+=raise;
         }
        public double getSalary(){
            return salary;
        }

        public String getName(){
            return name;
        }
    }

    public static void tripleSalary(Employee a){
        a.raiseSalary(10000);
        System.out.println("End of method:salary="+a.getSalary());
    }

    public static void swap(Employee a,Employee b){
        Employee tmp=a;
        a=b;
        b=tmp;
        System.out.println("End of method:a="+a.getName());
        System.out.println("End of method:b="+b.getName());
    }
    public static void main(String[] args){
        /*测试tripleValue方法*/
        System.out.println("Test tripleValue:");
        double percent=10;
        System.out.println("Before :percent="+percent);
        tripleValue(percent);
        System.out.println("After :percent="+percent);

        /*测试能够改变对象状态的方法*/
        System.out.println("\nTest tripleSalary:");
        Employee harry=new Employee("Harry",50000);
        System.out.println("Before: salary="+harry.getSalary());
        tripleSalary(harry);
        System.out.println("After: salary="+harry.getSalary());

        /*方法不能改变对象值*/
        System.out.print("\nTesting swap:");
        Employee a=new Employee("Alice",70000);
        Employee b=new Employee("Bob",60000);
        System.out.println("Before:a="+a.getName());
        System.out.println("Before:b="+b.getName());
        swap(a,b);
        System.out.println("After:a="+a.getName());
        System.out.println("After:b="+b.getName());
    }
}