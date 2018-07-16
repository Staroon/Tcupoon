package com.staroon.tcupoon.tools;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018-07-16
 * Time: 14:39:30
 * To change this template use File | Settings | File Templates.
 */
public class Commons {
    public String getTcupHome(){
        String tcupClassPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
        String tcupHome = new File(tcupClassPath + "/../conf").getParent();
        return tcupHome;
    }

    public static void main(String[] args) {
        System.out.println("home -> " + new Commons().getTcupHome());
    }
}
