package com.xdnote.ab.skin;


import javax.swing.Icon;
import javax.swing.JLabel;

import com.xdnote.ab.util.StringUtil;
/**
 * @author xdnote.com
 * <p>设置样式</p>
 * */
public class Label extends JLabel {

    private void initSkin(){
        this.setFont(StringUtil.font);
    }
    public Label() {
        super();
        this.initSkin();
    }

    public Label(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        this.initSkin();
    }

    public Label(Icon image) {
        super(image);
        this.initSkin();
    }

    public Label(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        this.initSkin();
    }

    public Label(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        this.initSkin();
    }

    public Label(String text) {
        super(text);
        this.setLabel(text);
        this.initSkin();
    }

    private String label = "";
    public void setLabel(String text) {
        this.label=text;
    }
    public void changeValue(String text) {
        this.setText(this.label + text);
    }


}
