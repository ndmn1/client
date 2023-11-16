package com.example;

import java.util.*;
import java.io.*;
import java.net.Socket;

public class App {

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */

    public static void main(String[] args) throws IOException {

    Socket socket=null;
    Scanner scanner =new Scanner(System.in);
    //InputStreamReader inputStreamReader=null;
    OutputStreamWriter outputStreamWriter=null;
    //BufferedReader bufferedReader=null;
    BufferedWriter bufferedWriter=null;

        socket=new Socket("localhost", 2225);
        //inputStreamReader=new InputStreamReader(socket.getInputStream());
        outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());
        //bufferedReader=new BufferedReader(inputStreamReader);
        bufferedWriter=new BufferedWriter(outputStreamWriter);
        
        bufferedWriter.write("HELO");
        bufferedWriter.newLine();
        bufferedWriter.write("MAIL FROM: <mnhat@fit.hcmus.edu.vn>");
        bufferedWriter.newLine();
        
        ArrayList<String> a=new ArrayList<String>();
        String to=new String("To: ");
        String cc=new String("Cc: ");
        System.out.printf("To: ");
        Scanner sc=new Scanner(scanner.nextLine());
        
        while(sc.hasNext())
        {
            a.add(sc.next());
            to+=a.get(a.size()-1);
            if(sc.hasNext()) to+=", ";
        }
        if(sc!=null) sc.close();
        System.out.printf("Cc: ");
        sc=new Scanner(scanner.nextLine());
        while(sc.hasNext())
        {
            a.add(sc.next());
            cc+=a.get(a.size()-1);
            if(sc.hasNext()) cc+=", ";
        }
        if(sc!=null) sc.close();
        System.out.printf("Bcc: ");
        sc=new Scanner(scanner.nextLine());
        while(sc.hasNext())
        {
            a.add(sc.next());
        }   
        if(sc!=null) sc.close();
        for(int i=0;i<a.size();i++)
        {
            bufferedWriter.write("RCPT TO: <" +a.get(i) + ">");
            bufferedWriter.newLine();
        }

        bufferedWriter.flush();
        bufferedWriter.write("DATA");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.write("From: minh nhat <mnhat@fit.hcmus.edu.vn>");
        bufferedWriter.newLine();
        bufferedWriter.write(to);
        bufferedWriter.newLine();
        bufferedWriter.write(cc);
        bufferedWriter.newLine();
        System.out.printf("Type your subject: ");
        bufferedWriter.write("Subject: "+scanner.nextLine());
        bufferedWriter.newLine();
        bufferedWriter.flush();

        System.out.println("Type your contents (type \"0\" to exit):");
        while(true)
        {
            String msgToSend =scanner.nextLine();
            if(msgToSend.compareTo("0")==0)
            {
                bufferedWriter.newLine();
                bufferedWriter.write(".");
                bufferedWriter.newLine();
                bufferedWriter.write("QUIT");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                break;
            }
            else
            {
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
            }
        }
        if(scanner!=null)
        {
            scanner.close();
        }
        if(bufferedWriter!=null)
        {
            bufferedWriter.close();
        }
        if(socket!=null)
        {
            socket.close();
        }
}
}
