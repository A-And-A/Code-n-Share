package fulltexteditor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author archit08jain
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class MyServer {
    
    ArrayList al=new ArrayList();
    ArrayList users=new ArrayList();
    ServerSocket ss;
    Socket s;
    
    public final static int PORT=10;
    public final static String UPDATE_USERS="updateuserslist:";
    public final static String LOGOUT_MESSAGE="@@logoutme@@:";
    
    public MyServer()
    {
        try{
	ss=new ServerSocket(PORT);
	System.out.println("Server Started "+ss);
	while(true)
	{
	s=ss.accept();
	Runnable r=new MyThread(s,al,users);
	Thread t=new Thread(r);
	t.start();
//	System.out.println("Total alive clients : "+ss.);
	}
        }catch(Exception e){System.err.println("Server constructor"+e);}
    }
    
    public ServerSocket getSocket()
    {
        return ss;
    }
    
}
