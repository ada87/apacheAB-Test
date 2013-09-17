package com.xdnote.ab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * @author xdnote.com
 * <pre>
 * Window窗口父类,使用SWING,所有窗口类继承这个类，不用直接继承JFRAME
 * </pre>
 * */
public class WindowFrame extends JFrame  implements ActionListener {

    /**
     * <p>默认的窗体宽度</p>
     * */
    private int width = 400;
    /**
     * <p>默认的窗体高度</p>
     * */
    private int height = 200;
    /**
     * <p>屏幕总高度</p>
     */
    public int maxheight = this.getToolkit().getScreenSize().height;
    /**
     * <p>屏幕总宽度</p>
     * */
    public int maxwidth = this.getToolkit().getScreenSize().width;

    /**
     * <p>
     * 无参数的构造方法，new操作 出一个新窗口
     * </p>
     * */
    public WindowFrame() {
        this.setBounds((maxwidth - width) / 2, (maxheight - height) / 2,
                width, height);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

     /**
     * <p>
     * 居中显示
     * </p>
     * */
    public final void resetBounds() {
        int w = this.getWidth();
        int h = this.getHeight();
        this.setBounds((maxwidth - w) / 2, (maxheight - h) / 2,  w, h);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
    }
}
