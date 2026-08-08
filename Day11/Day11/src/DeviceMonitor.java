import java.util.logging.*;

abstract class Device {
protected String name;

protected static Logger logger=Logger.getLogger("monitor");
public Device(String name){this.name=name;}
public abstract void check();
}
class Server extends Device{
    private int cpuUsage;
    public Server(String name,int cpuUsage){
        super(name);
        this.cpuUsage=cpuUsage;
    }
    @Override
    public void check(){
        if(cpuUsage>90){
            logger.severe(name+"CPU= "+cpuUsage+"%🔴 严重告警");
        }
        else if (cpuUsage>70){
            logger.warning(name+"CPU= "+cpuUsage+"%🟡 需要关注");
        }
        else{
            logger.info(name+"CPU= "+cpuUsage+"% 🟢 正常");
        }
    }
}
class Switch extends Device{
    private int usedPorts;
    private int totalPorts;
    public Switch(String name,int usedPorts,int totalPorts){
        super(name);
        this.usedPorts=usedPorts;
        this.totalPorts=totalPorts;
    }
    @Override
    public void check(){
        double usage=(double)usedPorts/totalPorts;
        if(usage>0.8){
            logger.severe(name+"端口使用率"+(int)(usage*100)+"% 🔴 即将满载！");
        }
        else if(usage>0.5){
            logger.warning(name + " 端口使用率=" + (int)(usage*100) + "% 🟡 过半");
        }
        else{
            logger.info(name + " 端口使用率=" + (int)(usage*100) + "% 🟢 正常");
        }
    }
}
public class DeviceMonitor{
    public static void main(String[] args){
    Logger monitorLogger=Logger.getLogger("monitor");
    monitorLogger.setUseParentHandlers(false);
    monitorLogger.setLevel(Level.ALL);

    ConsoleHandler handler=new ConsoleHandler();
    handler.setLevel(Level.ALL);
    monitorLogger.addHandler(handler);

    System.out.println("=========设备健康检查============\n");

    Device[] devices={
            new Server("web-01",45),
            new Server("web-02",88),
            new Server("db-01",95),
            new Switch("core-sw-01",12,24),
            new Switch("core-sw-02",20,24),
    };
    for (Device dev:devices){
        dev.check();
    }
    }
}
