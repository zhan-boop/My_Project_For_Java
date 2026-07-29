import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args){
        //打印字符串
        System.out.println("Hello DevOps, Day 1 !");
        //变量理解
        int a=2;       //int类型
        long b=7888888L;//长整型变量
        double c=67.5;  //double类型,浮点数类型分为double与float，float类型后面需要加f/F
        char d='f';     //char类型理解，不能加int类型，可能会有损失,配置环境变量可能会用到
        char e='\\';//可参与加减法，但是需要转变e=(char)(d+a)
        final int f=90;//定义常量，则无法随意进行更改
        long sum=a+b;   //类型转变
        double sum1=a+c;    //double类型转换
        int g=8;            //理解pow时设计的参数
        int h=2;            //同理理解pow设计的参数
        double i=Math.pow(g,h);//数学函数中幂函数使用方法,pow内部值为double类型，返回值自然也是double类型，int/long会报错
        //类型转变
        long j=(long)(a+b);
        boolean k=true;//这里注意&与||运算符均为短路方式求值，&为false时，不需要继续就是false，不会计算后面的式子，||为true同理
        //字符串(子串以及拼接)
        String greeting="hello";//java中字符串不可变，无法随意进行更改
        String greeting_sub=greeting.substring(0,3);//提取字符串，下标从0开始，左闭右开原则，方便计算长度
        greeting=greeting_sub+"lo world";
        String s="hello world";
        //完全相等，区分大小写
        k=false;
        if(s.equals(greeting)){System.out.println("s=greeting，真实等式为："+s+"="+greeting);}else System.out.println(k);
        //不区分大小写
        s="HELLO worLD";
        if(s.equalsIgnoreCase(greeting)){System.out.println("不区分大小写，s=greeting:"+s+"="+greeting);}else{System.out.println(k);}
        //输出一个格式化的字符串(使用String.format函数进行输出)
        String name="zhangjianxiong";
        int age=27;
        String message=String.format("hello,%s,Next year,you will be %d 岁",name,age+1);
        System.out.println(message);
        //文件数据进行写入
        String filename="D:\\Java_project\\Day1\\zhangjianxiong.txt";
        try {
            PrintWriter out=new PrintWriter(filename,StandardCharsets.UTF_8);
            out.println("hello world");
            out.println("hello zhangjianxiong");
            out.close();                //写入时需要关闭句柄
            System.out.println("文件写入成功");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        //读出文件里的数据
        try {
            Scanner in=new Scanner(Path.of(filename), StandardCharsets.UTF_8);
            while(in.hasNextLine()){    //循环读出数据
                String line=in.nextLine();
                System.out.println(line);
            }
            in.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("a+c="+sum1);
        System.out.println("path:c:"+e+"Users");
        System.out.println("f为常量，无法随意更改，f值为:"+f);
        System.out.println("i为8的平方，即:"+i);
        System.out.println("j的最终值为:"+j);
        System.out.println("k类型为boolean类型，其值为："+k);
        System.out.println("greeting_sub值为:"+greeting_sub);
        System.out.println("greeting最终值为:"+greeting);
    }
}
