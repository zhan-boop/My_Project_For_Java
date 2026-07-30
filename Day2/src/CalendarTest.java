    import java.time.*;


    public class CalendarTest {
        public static void main(String[] args){
            /*LocalDate 是 Java 8 引入的日期时间 API（java.time 包）中的一个不可变类，用于表示不带时间的日期（年-月-日）*/
            LocalDate date=LocalDate.now(); //定义LocalDate对象,获取当前时间，主要了解LocalDate类作用及其方法
            int month=date.getMonthValue();//利用date对象调用类中的getMonthValue方法获取当前月份值
            int today=date.getDayOfMonth();//利用date对象调用getDayOfMonth函数获取日期

            date=date.minusDays(today-1);//=today-(today-1）利用date对象使用munusDays函数减去today-1天，即到当月的第一天(前提：today必须是一个日期的号数)
           /*DayOfWeek 是 Java 8 引入的 java.time 包中的一个枚举类，用于表示一周中的七天（星期一至星期日）*/
            DayOfWeek weekday=date.getDayOfWeek();//获取当月第一天是周几，LocalDate类提供DayOfWeek的方法，并返回一个DayOfWeek对象，LocalDate与DayOfWeek呈现依赖关系
            int value=weekday.getValue();//由于是DayOfWeek类的对象，可以调用DayOfWeek方法，返回数值1-7
            // 打印日历标题（从周一开始）
            System.out.println("  Mon Tue Wed Thu Fri Sat Sun");
            System.out.println("-----------------------------");
            // 改进1：打印正确的缩进（每个位置占4个字符）
            for(int i=1;i<value;i++){
                System.out.print("    ");
            }
            //借用循环输出当月所有日期
            while(date.getMonthValue()==month){
                if(date.getDayOfMonth()==today){
                    System.out.printf(" %2d*", date.getDayOfMonth());  // 标记今天，直接引用日期改进
                }
                else{
                    System.out.printf("  %2d", date.getDayOfMonth()); //输出当前日期
                }
                date=date.plusDays(1);//plusDays() 是 LocalDate 类的一个实例方法，用于在日期上增加指定的天数，返回一个新的 LocalDate 对象。
               if(date.getDayOfWeek().getValue()==1){System.out.println();}//周日换行=也可以说"是周一就换行"
            }
             System.out.println();
        }
    }
