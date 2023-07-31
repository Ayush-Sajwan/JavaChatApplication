package com.chatApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class IdClient {
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);

        String id=JOptionPane.showInputDialog("Enter you Id for chat");
        String name=JOptionPane.showInputDialog("Enter your name for Chat");

        Socket s=new Socket("localhost",9906);

        BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        out.write(id);
        out.newLine();
        out.flush();
        out.write(name);
        out.newLine();
        out.flush();

        //Storing the id value in variable so that we can use it to access the database
        int numid=Integer.parseInt(id);

        //Creating an object of the myframe class which contains the gui of client
        MyFrame frame=new MyFrame();
        IdClientDatabase db=new IdClientDatabase();


        //fetching any old messages that a client might have
        //fetching messages on the based of the id
        //if any messages found then loading them on the page
        db.fetchMessageDB(numid,frame);

        Thread r=new Thread(new Runnable() {
            @Override
            public void run() {
                while (s.isConnected()) {
                    String msgRe;

                    String sender;
                    String msg;
                    try {
                        sender = in.readLine();
                         msg=in.readLine();
                         msgRe=sender+" :"+msg;
                    } catch (IOException e) {
                        System.out.println("Server is unavailable");
                        break;
                    }
                    frame.updateMessageBox("\n"+msgRe);

                    try {
                        //storing the message in the database
                        db.addMessageDB(numid,sender,msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        });
        r.start();

            frame.btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (s.isConnected()) {
                        String msgid = frame.area2.getText();
                        String msg = frame.area1.getText();
                        try {
                            frame.updateMessageBox("\nMe:"+msg);

                            try {
                                //Storing the message send by the client in database
                                db.addMessageDB(numid,"Me",msg);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }

                            out.write(msgid);
                            out.newLine();
                            out.flush();
                            out.write(msg);
                            out.newLine();
                            out.flush();
                        } catch (IOException ex) {
                            System.out.println("Exiting--");
                        }
                    }
                }
            });

    }
}
