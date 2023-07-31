package com.chatApplication;

import java.io.IOException;
import java.util.ArrayList;

class ClientHandlers implements Runnable{

    //list for storing the info of all the clients
    static ArrayList<ClientInfo> list =new ArrayList<>();

    //Client object
    ClientInfo client;

    //adding the client to list
    ClientHandlers(ClientInfo client){
        this.client=client;
        list.add(this.client);
    }

    @Override
    public void run() {

        int msgid;
        String msg;

        while(this.client.conn.isConnected()){

            try {
                msgid=Integer.parseInt(this.client.in.readLine());
                msg=this.client.in.readLine();

                deliver(msgid,this.client.name,msg);


            } catch (IOException e) {

                list.remove(this.client);
                break;
            }
        }


    }

    public void deliver(int msgid,String sender,String msg){

        for(ClientInfo client:list){

            if(client.id==msgid){
                try {
                    client.out.write(sender);
                    client.out.newLine();
                    client.out.flush();
                    client.out.write(msg);
                    client.out.newLine();
                    client.out.flush();
                }catch (IOException e){
                    System.out.println(e);
                }
                break;
            }

        }

    }


}
