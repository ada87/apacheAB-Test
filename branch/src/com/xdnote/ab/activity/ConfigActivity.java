package com.xdnote.ab.activity;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.xdnote.ab.WindowFrame;
import com.xdnote.ab.skin.Label;
import com.xdnote.ab.skin.Text;
import com.xdnote.ab.util.ConfigManager;
import com.xdnote.ab.util.M;

public class ConfigActivity extends WindowFrame {


    private Label lb_apachePath;
    private Label lb_logPath;
    
    private Text txt_apachePath;
    private Text txt_logPath;
    
    private JButton btn_submit;

    private void setPanel(){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
//     gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady
        GridBagConstraints ol_apachePath=new GridBagConstraints(1,1,1,1,0.2,1,GridBagConstraints.LINE_END,GridBagConstraints.NONE, new Insets(0,0,0,0),0,0);
        GridBagConstraints ot_apachePath=new GridBagConstraints(2,1,1,1,0.5,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL, new Insets(0,10,0,0),0,0);
        GridBagConstraints ol_logPath=new GridBagConstraints(1,2,1,1,0.2,1,GridBagConstraints.LINE_END,GridBagConstraints.NONE, new Insets(0,0,0,0),0,0);
        GridBagConstraints ot_logPath=new GridBagConstraints(2,2,1,1,0.5,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL, new Insets(0,10,0,0),0,0);
        GridBagConstraints ob_submit=new GridBagConstraints(2,3,1,1,0,1,GridBagConstraints.CENTER,GridBagConstraints.NONE, new Insets(0,0,20,200),0,0);
        
        lb_apachePath=new Label("apache路径");
        txt_apachePath=new Text(ConfigManager.getValue(M.T_APACHE_PATH),100);
        lb_logPath=new Label("存储目录");
        txt_logPath=new Text(ConfigManager.getValue(M.T_LOG_PATH),100);
        btn_submit=new JButton();
        btn_submit.setText("submit");
        btn_submit.addActionListener(this);

        GridBagConstraints oo_line1=new GridBagConstraints(3,1,1,1,0.1,20,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0);
        GridBagConstraints oo_line2=new GridBagConstraints(3,2,1,1,0.1,20,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0);
        
        panel.add(new JLabel(),oo_line1);
        panel.add(new JLabel(),oo_line2);
        
        panel.add(lb_apachePath,ol_apachePath);
        panel.add(txt_apachePath,ot_apachePath);
        panel.add(lb_logPath,ol_logPath);
        panel.add(txt_logPath,ot_logPath);
        panel.add(btn_submit,ob_submit);
        this.add(panel);
    }

    public ConfigActivity() {
        super();
        this.setTitle("软件设置");
        this.setPanel();
        this.setVisible(true);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String apachePath = txt_apachePath.getText();
        String logPath = txt_logPath.getText();
        ConfigManager.setValue(M.T_APACHE_PATH,apachePath);
        ConfigManager.setValue(M.T_LOG_PATH,logPath);
        JOptionPane.showMessageDialog(null, "保存成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
}
