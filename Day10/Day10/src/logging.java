package logging;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;

/**
 * Java日志框架演示 — 日志记录 + Swing GUI 可视化
 * ============================================================
 *
 * ★ 知识点1：Logger 日志系统
 *   - Logger.getLogger("名字") 创建/获取日志记录器
 *   - setLevel(Level.ALL) 设置记录级别
 *   - FileHandler 把日志写到文件，WindowHandler 显示在窗口
 *
 * ★ 知识点2：Handler（处理器）
 *   - 日志不直接写到目的地，而是通过 Handler 分发
 *   - FileHandler → 写文件；StreamHandler → 写到流（这里重定向到文本框）
 *
 * ★ 知识点3：Lambda + 匿名内部类
 *   - EventQueue.invokeLater(() -> {...})：Swing 必须在事件调度线程运行
 *   - new ActionListener() { ... }：匿名内部类实现接口
 */
public class logging {
    public static void main(String[] args) {

        // ★ 检查是否已配置日志（避免重复配置）
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null) {

            try {
                // ① 获取名为 "com.horstmann.corejava" 的 Logger
                Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL);

                // ② 创建 FileHandler：日志写入文件
                //    %h = 用户主目录，0 = 不限制文件大小，10 = 保留10个轮转文件
                final int LOG_ROTATION_COUNT = 10;
                var handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("com.horstmann.corejava").addHandler(handler);

            } catch (IOException e) {
                Logger.getLogger("com.horstmann.corejava")
                        .log(Level.SEVERE, "Can't create log file handler", e);
            }

            // ★ Swing GUI 必须在事件调度线程中创建
            EventQueue.invokeLater(() -> {
                // ③ 创建窗口日志处理器（日志显示在独立窗口中）
                var windowHandler = new WindowHandler();
                windowHandler.setLevel(Level.ALL);
                Logger.getLogger("com.horstmann.corejava").addHandler(windowHandler);

                // ④ 创建主窗口
                var frame = new ImageViewerFrame();
                frame.setTitle("LoggingImageViewer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Logger.getLogger("com.horstmann.corejava").fine("Show frame");
                frame.setVisible(true);
            });
        }
    }
}

/**
 * 图片查看窗口 — 文件菜单 + 图片显示
 * 每步操作都记录日志
 */
class ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    private static Logger logger = Logger.getLogger("com.horstmann.corejava");

    public ImageViewerFrame() {
        // ★ 记录方法进入日志（entering/exiting 是规范用法）
        logger.entering("ImageViewerFrame", "<init>");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // 菜单栏
        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // File 菜单
        var menu = new JMenu("File");
        menuBar.add(menu);

        // Open 菜单项：打开图片
        var openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        // Exit 菜单项：退出
        var exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(event -> {
            logger.fine("Exiting.");
            System.exit(0);
        });

        // 图片显示区域
        label = new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame", "<init>");
    }

    /**
     * ★ 内部类：文件打开监听器
     * 点击 Open → 弹出文件选择器 → 选 .gif 图片 → 显示
     */
    private class FileOpenListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", event);

            var chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            // ★ 匿名内部类：文件过滤器，只显示 .gif 文件和目录
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }
                public String getDescription() {
                    return "GIF Images";
                }
            });

            // 显示打开对话框
            int r = chooser.showOpenDialog(ImageViewerFrame.this);
            if (r == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            } else {
                logger.fine("File open dialog canceled.");
            }
            logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
        }
    }
}

/**
 * ★ 自定义日志处理器 — 把日志输出到独立窗口中
 * 继承 StreamHandler，重定向输出流到 JTextArea
 */
class WindowHandler extends StreamHandler {
    private JFrame frame;

    public WindowHandler() {
        frame = new JFrame();
        var output = new JTextArea();
        output.setEditable(false);          // 日志只读
        frame.setSize(200, 200);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);

        // ★ 重定向输出流：write() 的内容显示在 JTextArea 中
        setOutputStream(new OutputStream() {
            public void write(int b) {}     // 单字节不处理

            public void write(byte[] b, int off, int len) {
                output.append(new String(b, off, len));  // 追加到文本框
            }
        });
    }

    // ★ 重写 publish：窗口不可见时跳过；否则刷新显示
    public void publish(LogRecord record) {
        if (!frame.isVisible()) return;
        super.publish(record);
        flush();  // 立即刷新，保证日志实时显示
    }
}
