package com.xdnote.ab;

import com.xdnote.ab.activity.PortalActivity;

/**
* @author xdnote.com
* <p>
* AB客户端入口，打包时使用client的main方法做为入口
* </p>
*/
public final class Client {

    /**
     * @deprecated
    * <p>私有构造器，防止工具类被构造</p>
    */
    private Client() { }

    /**
    * @param args 执行命令
    * <p>
    * 执行程序，启动一个window窗口
    * </p>
    */
    public static void main(final String[] args) {
        new PortalActivity();
    }

}
