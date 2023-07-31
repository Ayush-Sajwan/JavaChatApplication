package com.chatApplication;

import javax.swing.*;
import java.awt.*;

class MyFrame{
    JFrame frame;JScrollPane scroll;JTextArea field;
    JPanel panel1;JPanel panel2;JLabel label1;
    JLabel label2;JTextArea area1;JTextArea area2;
    JScrollPane scroll2;JButton btn;

    MyFrame(){
        //frame for main screen
        frame=new JFrame("Client Application");
        frame.setSize(600,600);
        frame.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));

        //first text area for displaying messages
        field=new JTextArea();
        field.setBackground(Color.CYAN);
        field.setEditable(false);
        field.setLineWrap(true);
        field.setFont(new Font("Time_New_Roman",Font.BOLD,30));
        //JScrollPane for first scroll panel
        scroll = new JScrollPane(field);
        scroll.setPreferredSize(new Dimension(560,400));

        //panels for message and id block
        panel1=new JPanel();
        panel2=new JPanel();

        panel1.setBackground(Color.CYAN);
        panel2.setBackground(Color.CYAN);

        panel1.setPreferredSize(new Dimension(200,130));
        panel2.setPreferredSize(new Dimension(200,130));

        panel1.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));


        label1=new JLabel("Message");
        label2=new JLabel("Id");

        label1.setPreferredSize(new Dimension(180,50));
        label2.setPreferredSize(new Dimension(180,50));

        label1.setFont(new Font("Times_New_Roman",Font.BOLD,20));
        label2.setFont(new Font("Times_New_Roman",Font.BOLD,20));

        area1=new JTextArea();
        area2=new JTextArea();
        scroll2=new JScrollPane(area1);

        area1.setLineWrap(true);
        area2.setLineWrap(true);

        scroll2.setPreferredSize(new Dimension(180,50));
        area2.setPreferredSize(new Dimension(180,50));

        area1.setFont(new Font("Times_New_Roman",Font.BOLD,20));
        area2.setFont(new Font("Times_New_Roman",Font.BOLD,20));


        btn=new JButton("Send");
        btn.setBackground(Color.CYAN);
        btn.setFont(new Font("Times_New_Roman",Font.BOLD,20));
        btn.setPreferredSize(new Dimension(130,130));

        panel1.add(label1);
        panel2.add(label2);

        panel1.add(scroll2);
        panel2.add(area2);

        frame.add(scroll);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(btn);

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    void updateMessageBox(String msg){
        this.field.setText(this.field.getText()+msg);
    }
}
