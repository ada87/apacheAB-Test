package com.xdnote.ab.skin;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import com.xdnote.ab.util.StringUtil;

/**
 * @author xdnote.com
 * */
public class Button extends JButton {
    /**
     * 初始化皮肤（设置字体）
     * */
    private void initSkin() {
        this.setFont(StringUtil.font);
    };
    /**
     * 不带参数的构造器，
     * 参数与JButton一致
     * */
    public Button() {
        super();
        this.initSkin();
    }

    /**
     * @param action 绑定事件
     * 参数与JButton一致
     * */
    public Button(final Action action) {
        super(action);
        this.initSkin();
    }

    /**
     * @param icon 设置ICON
     * 参数与JButton一致
     * */
    public Button(final Icon icon) {
        super(icon);
        this.initSkin();
    }

    /**
     * @param text 按钮上的文字
     * @param icon 按钮Icon
     * 参数与JButton一致
     * */
    public Button(final String text, final Icon icon) {
        super(text, icon);
        this.initSkin();
    }

    /**
     * @param text 按钮上的文字
     * 参数与JButton一致
     * */
    public Button(final String text) {
        super(text);
        this.initSkin();
    }

}
