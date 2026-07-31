/*
* 1.依赖关系
* 定义：一个类的方法中使用了另一个类的对象（作为局部变量、方法参数或静态方法调用）。
*/
class Car{
    public void run(Gasoline gasoline){
        gasoline.burn();
        System.out.println("汽车跑起来了");
    }
}
class Gasoline{
    public void burn(){
        System.out.println("汽油燃烧");
    }
}

/*
* 2.关联关系
*一个类持有另一个类的引用（作为成员变量），两者之间存在长期的、固定的联系。
* */
class Student{
    private String name;
    private ClassRoom classRoom;//持有关联对象

    public Student(String name,ClassRoom classRoom){
        this.name=name;
        this.classRoom=classRoom;
    }
}
class ClassRoom{
    private String roomNumber;
    public ClassRoom(String roomNumber){
        this.roomNumber=roomNumber;
    }
}
/*
*
* 3.聚合关系
*整体包含部分，但部分可以脱离整体独立存在。用菱形空心表示
* */

class Computer{
    private Mouse mouse; //聚合

    public void setMouse(Mouse mouse){
        this.mouse=mouse;
    }
}
class Mouse{
    private String brand;

    public Mouse(String mouse) {
        this.brand=mouse;
    }
}
/*
* 4. 组合关系（Composition）—— 更强的 "has-a"（同生共死）
* 定义：整体包含部分，且部分不能脱离整体独立存在。用菱形实心表示。
*/
class Person{
    private Heart heart;    //组合
    public Person(){
        this.heart=new Heart(); //创建人时同时创建心脏
    }
    public void die(){
        heart.stop(); //人死了，心脏也停止
    }
}
class Heart{
    public void beat(){
        System.out.println("怦怦跳");
    }
    public void stop(){
        System.out.println("心脏停止跳动");
    }
}

/*
* 5.继承关系
*定义：子类继承父类的属性和方法，可以扩展或重写
* */
class Animal{
    protected String name;

    public Animal(String name){
        this.name=name;
    }
    public void eat() {
        System.out.println(name + "在吃东西");
    }
}
class Dog extends Animal{
    public Dog(String name){
        super(name);
    }

    @Override
    public void eat(){
        System.out.println(name+"在吃骨头");
    }
    public void bark(){
        System.out.println(name+"在汪汪叫");
    }
}

/*
* 6.实现关系---接口实现
*定义：类实现接口中定义的所有抽象方法
* */
interface Flyable{
    void fly();
}
interface Swimmable{
    void swim();
}

//类实现接口
class Duck implements Flyable,Swimmable{
    @Override
    public void fly(){
        System.out.println("鸭子飞一段时间");
    }
    public void swim(){
        System.out.println("鸭子在水里游");
    }
}
//由于java所有代码都是面向对象编程，所以main也需要一个类包含，使用构造函数main方法进行构造，里面执行相应代码
class Main{
    public static void main(String[] args){
        //依赖关系使用方法
        Car car=new Car();
        car.run(new Gasoline());//这里需要注意传递的是一个实例

        //关联关系使用方法
        ClassRoom room=new ClassRoom("301");
        Student stu=new Student("张三",room);

        //聚合关系
        Computer computer=new Computer();
        Mouse mouse=new Mouse("罗技");
        computer.setMouse(mouse);

        //聚合关系
        Dog dog=new Dog("旺财");
        dog.eat();
        dog.bark();

        //接口实现
        Duck duck=new Duck();
        duck.fly();
        duck.swim();
    }
}

