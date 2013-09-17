package com.xdnote.ab.skin;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

import com.xdnote.ab.util.StringUtil;
/**
 * @author xdnote.com
 * <p>设置样式</p>
 * */
public class CheckBox extends JCheckBox {
    /**
     * <p>设置样式</p>
     * */
    private void initSkin(){
        this.setFont(StringUtil.font);
    }
    public CheckBox() {
        super();
        this.initSkin();
    }

    public CheckBox(Action arg0) {
        super(arg0);
        this.initSkin();
    }

    public CheckBox(Icon arg0, boolean arg1) {
        super(arg0, arg1);
        this.initSkin();
    }

    public CheckBox(Icon arg0) {
        super(arg0);
        this.initSkin();
    }

    public CheckBox(String arg0, boolean arg1) {
        super(arg0, arg1);
        this.initSkin();
    }

    public CheckBox(String arg0, Icon arg1, boolean arg2) {
        super(arg0, arg1, arg2);
        this.initSkin();
    }

    public CheckBox(String arg0, Icon arg1) {
        super(arg0, arg1);
        this.initSkin();
    }

    public CheckBox(String arg0) {
        super(arg0);
        this.initSkin();
    }

}
