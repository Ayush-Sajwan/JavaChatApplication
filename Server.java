package com.chatApplication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ClientInfo{
    String name;
    int id;
    BufferedReader in;
    BufferedWriter out;
    Socket conn;
}



public class Server {
    public static void main(String[] args)throws Exception {

        ServerSocket server=new ServerSocket(9906);
        //Object for client connection
        Socket client;
        //Object for storing all the clients detail
        ClientInfo cc;

        while(true){
            //accepting connection from client
            client=server.accept();

            //Creating the object of client type
            cc=new ClientInfo();

            //storing all the details of client
            cc.in=new BufferedReader(new InputStreamReader(client.getInputStream()));
            cc.out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            cc.conn=client;
            cc.id=Integer.parseInt(cc.in.readLine());
            cc.name=cc.in.readLine();
            System.out.println("Client with id "+cc.id+" Name "+cc.name+" connected");

            Thread t=new Thread(new ClientHandlers(cc));
            t.start();
        }


    }
}
