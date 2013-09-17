package com.xdnote.ab.activity;

import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.xdnote.ab.WindowFrame;
import com.xdnote.ab.skin.Button;

public class PortalActivity extends WindowFrame{

    /**
     * 入口链接 - 进入测试页面
     * */
    private Button btn_start = null;
    /**
     *入口链接 -  进入历史记录
     * */
    private Button btn_history = null;
    /**
     *入口链接 -  进入选项设置
     * */
    private Button btn_option = null;
    /**
     * 入口链接 - 退出软件
     * */
    private Button btn_exit = null;
    /**
     * <p>
     * 初始化面板
     * </p>
     * */
    private void setPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        btn_start = new Button();
        btn_start.setText("开始测试");
        btn_start.addActionListener(this);
        btn_history = new Button();
        btn_history.setText("记录查看");
        btn_history.addActionListener(this);
        btn_option = new Button();
        btn_option.setText("系统选项");
        btn_option.addActionListener(this);
        btn_exit = new Button();
        btn_exit.setText("退出软件");
        btn_exit.addActionListener(this);
        panel.add(Box.createHorizontalStrut(100));
        panel.add(btn_start);
        panel.add(Box.createHorizontalStrut(0));
        panel.add(btn_history);
        panel.add(Box.createHorizontalStrut(0));
        panel.add(btn_option);
        panel.add(Box.createHorizontalStrut(0));
        panel.add(btn_exit);
        panel.add(Box.createHorizontalStrut(0));
        panel.setSize(60, 200);
        this.add(panel);
    }

    /**
     * <p>
     * 无参构造方法，入口页面，生成入口页面的主模块
     * </p>
     * */
    public PortalActivity() {
        super();
        this.setTitle("软件主页面");
        this.setPanel();
        this.setVisible(true);
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        if (e.getSource() == btn_start) {
            new AbActivity();
        }
        if (e.getSource() == btn_history) {
            new HistoryActivity();
        }
        if (e.getSource() == btn_option) {
            new ConfigActivity();
        }
        if (e.getSource() == btn_exit) {
            System.exit(0);
        }
    }

}
