package com;

import com.utils.BaseHolder;
import com.view.MainWindow;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

/**
 * 程序运行入口
 *
 * @author zumin
 */
@SpringBootApplication
public class GraphicDesignApplication {

    /**
     * 创建MainWindowSwing窗口进行显示
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        //构建核心容器
        final SpringApplicationBuilder builder = new SpringApplicationBuilder(GraphicDesignApplication.class);
        ApplicationContext applicationContext = builder.headless(false).run(args);
        //将核心容器存到BaseHolder中
        applicationContext.getBean("baseHolder", BaseHolder.class).setApplicationContext(applicationContext);
        //显示swing窗口
        SwingUtilities.invokeLater(() -> applicationContext.getBean("viewWindow", MainWindow.class).setVisible(true));
    }

}
