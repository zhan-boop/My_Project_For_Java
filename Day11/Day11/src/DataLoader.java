
/**
 * 题3：异常处理器 + 日志模板 — try-catch-finally
 * ============================================================
 * ★ 知识点：自定义异常、try-catch-finally、Logger、finally必执行
 *
 * 要求：
 *   1. DataLoadException 自定义异常（继承Exception）
 *   2. loadData(String path) throws DataLoadException
 *      - 文件不存在 → throw DataLoadException
 *      - 正常 → 返回"加载成功: xxx条"
 *   3. main 里 try-catch-finally：
 *      - try 加载两个文件（一个存在、一个不存在）
 *      - catch 打印异常+记录logger.severe()
 *      - finally 打印"操作记录已保存"（不管成不成功都执行）
 */

import java.util.logging.*;
import java.io.*;

class DataLoadException extends Exception {
    // 构造器
    public DataLoadException(String msg){
        super(msg);
    }
}

public class DataLoader {
    private static Logger logger = Logger.getLogger("dataloader");

    // 写代码：loadData方法
    public static String loadData(String path) throws DataLoadException{
        File file=new File(path);
        if(!file.exists()){
            throw new DataLoadException("文件不存在 "+path);
        }
        else{
            int count=0;
            //文件读取并统计行数
            try(BufferedReader reader=new BufferedReader(new FileReader(file))){
                while(reader.readLine()!=null){
                    count++;
                }
                return "加载成功: " + count + "条记录";
            }catch (IOException e){
                throw new DataLoadException("读取文件失败:"+e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        // 配置Logger
        logger.setUseParentHandlers(false);
        ConsoleHandler handler=new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        // try-catch-finally
        try{
            String result=loadData("D:\\Devops\\DevOps_120天工程师计划.md");
            logger.info(result);
            System.out.println("✅"+result);
        }catch(DataLoadException e){
            logger.severe(e.getMessage());
            System.out.println("❌ "+e.getMessage());
        } finally{
            System.out.println("[记录] 本次操作已保存");
        }


        try{
            String result=loadData("D:/不存在的文件.txt");
            logger.info(result);
            System.out.println("✅"+result);
        }catch(DataLoadException e){
            logger.severe(e.getMessage());
            System.out.println("❌ "+e.getMessage());
        } finally{
            System.out.println("[记录] 本次操作已保存");
        }
        System.out.println("\n程序正常结束");
    }
}
