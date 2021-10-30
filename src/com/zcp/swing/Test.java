package com.zcp.swing;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/8 11:18
 * @description：
 * @version:
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        FeedProcessDialog feedProcessDialog = new FeedProcessDialog("lalal");
        feedProcessDialog.setVisible(true);
        for (int i = 0; i < 20; i++) {
            feedProcessDialog.addLine(i+"");
            Thread.sleep(500);
        }

    }


}
