package com.zcp.swing;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/8 11:20
 * @description：
 * @version:
 */
public class FeedProcessDialog extends JDialog {
    JLabel jLabel;
    JButton okButton;
    JTextArea jTextArea;
    JScrollPane jScrollPane;
    JTable jTable;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public FeedProcessDialog() {
        super();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public FeedProcessDialog(String title) {
        super((Dialog) null, title);
        init();
    }

    public void init() {

        this.setBounds(132, 132, 1000, 200);
        Container dialogContainer = this.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        jScrollPane = new JScrollPane();
        this.add(jScrollPane, BorderLayout.CENTER);
        jTextArea = new JTextArea();
        jScrollPane.add(jTextArea);
        jScrollPane.setViewportView(jTextArea);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void addLine(String s) {
        this.jTextArea.append(sdf.format(new Date()) + ":" + s + "\r\n");
        //显示最底层一行
        jTextArea.setCaretPosition(jTextArea.getText().length());
    }


}
