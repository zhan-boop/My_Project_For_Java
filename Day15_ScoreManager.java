import java.util.*;
import java.io.*;

//学生类--封装
class Student{
    private String name;
    private String subject;
    private int score;
    public Student(String name,String subject,int score){
        this.name=name;
        this.subject=subject;
        this.score=score;
    }

    public String getName() {
        return name;
    }
    public String getSubject(){
        return subject;
    }
    public int getScore(){
        return score;
    }
    @Override
    public String toString(){return String.format("%-5s  %-7s  %3d分", name, subject, score);}
}
public class ScoreManager{
    //定义学生类，可能有多名学生，所以需要定义学生列表
    List<Student>students=new ArrayList<Student>();
    //由于后续需要做选择，必然会有一个输入（设计结构时应该想到，或者结合ai给出想法，就应该想象到有一个输入）
    private static Scanner scanner=new Scanner(System.in);


    public static void main(String[] agrs){new ScoreManager().run();}
    public void run(){
        while(true){
            System.out.println("\n===== 学生成绩管理系统 =====");
            System.out.println("1.添加  2.查看  3.排序  4.最高分");
            System.out.println("5.及格率 6.不及格 7.导出  8.科目  0.退出");
            System.out.print("选择: ");
            String choice=scanner.nextLine().trim();

            switch (choice){
                case "1":addStudent();break;
                case "2":showAll();break;
                case "3":sortByscore();break;
                case "4":findMax();break;
                case "5":passRate();break;
                case "6":showFail();break;
                case "7":exportFile();break;
                case "8":showSubject();break;
                case "0":System.out.println("再见");return;
            }
        }
    }
    //添加学生+异常处理
    private void addStudent(){
        try{
            System.out.println("姓名:");String name=scanner.nextLine();
            System.out.println("科目:");String subject=scanner.nextLine();
            System.out.println("成绩:");int score=Integer.parseInt(scanner.nextLine());
            if(score<0 || score>100){
                System.out.println("成绩必须在0-100之间!");
                return;
            }
            students.add(new Student(name,subject,score));
            System.out.println("✅ 添加成功");
        }catch(NumberFormatException e){
            System.out.println("❌ 成绩必须是数字!");
        }
    }
    //查看全部
    private void showAll(){
        //List<Student>students=new ArrayList<Student>();
        //方法1
        for(Student i : students){
            System.out.println(i.getName()+" "+i.getSubject()+" "+i.getScore());
        }
        //方法二：ai书写(建议)
        if(students.isEmpty()){System.out.println("暂无数据");return;}
        for(int i=0;i<students.size();i++){
            System.out.println((i+1)+"."+students.get(i));
        }
    }
    //collections.sort+comparator降序
    private void sortByscore(){
        if(students.isEmpty()){System.out.println("暂无数据");return;}
        List<Student>sorted=new ArrayList<Student>();
        //逻辑点
        Collections.sort(sorted,(a,b)->b.getScore()-a.getScore());
        System.out.println("===成绩排名(降序)===========");
        for(Student s:sorted){
            System.out.println(s.getName()+":"+s.getScore());
        }
    }
    //遍历找最大
    private void findMax(){
        if(students.isEmpty()){System.out.println("暂无数据");return;}
        Student max=students.get(0);
        for(Student s:students){
            if(s.getScore()>max.getScore()){
                max=s;
            }
        }
        System.out.println("🏆 最高分: " + max);
    }
    //统计及格率
    private void passRate(){
        if(students.isEmpty()){System.out.println("暂无数据");return;}
        int pass=0;
        for(Student s:students){
            if(s.getScore()>60){pass++;}
        }
        //存储及格率
        double rate=100.0*pass/students.size();
        System.out.printf("及格率:%.1f%% (%d/%d)%n",rate,pass,students.size());
    }

    //ArrayList+条件筛选
    private void showFail(){
        List<String>fails=new ArrayList<>();
        for(Student s:students){
            if(s.getScore()<60){fails.add(s.getName());}
        }
        if(fails.isEmpty()){System.out.println("全部及格");}
        else System.out.println("不及格："+fails);
    }

    //文件导出(今日新学)
    private void exportFile(){
        try(FileWriter fw=new FileWriter("D:/scores_export.txt")){
            for(Student s:students){
                fw.write(s.toString()+"\n");
            }
            System.out.println("✅ 已导出到 D:/scores_export.txt");
        } catch (IOException e) {
            System.out.println("❌ 导出失败: " + e.getMessage());
        }
    }

    //HashSet去重--统计科目
    private void showSubject(){
        Set<String>subjects=new HashSet<>();
        for(Student s:students){subjects.add(s.getSubject());}
        System.out.println("已有科目("+subjects.size()+"门)"+subjects);
    }

}