package fulltexteditor;

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;



public class ClientThread implements Runnable
{
DataInputStream dis;
MyClient client;
MainWindow MW;

ClientThread(DataInputStream dis,MyClient client,MainWindow MW)
{
    this.dis=dis;
    this.client=client;
    this.MW = MW;
}
////////////////////////
public void run()
{
    String s2="";
    do
    {
	try{
                s2=dis.readUTF();
      
                if(s2.startsWith(MyServer.UPDATE_USERS))
                        updateUsersList(s2);
                else if(s2.equals(MyServer.LOGOUT_MESSAGE))
                        break;
                else if(s2.startsWith(MyServer.TO_CHAT))
                {
                   JOptionPane.showConfirmDialog(MW, "Accepting A code");
                    MW.toChatWindow(s2.substring(10));
                }
                else
                    client.txtBroadcast.append("\n"+s2);

                int lineOffset=client.txtBroadcast.getLineStartOffset(client.txtBroadcast.getLineCount()-1);
                client.txtBroadcast.setCaretPosition(lineOffset);
	    }
	catch(Exception e){client.txtBroadcast.append("\nClientThread run : "+e);}
   }
    while(true);
}

public void updateUsersList(String ul)
{
Vector ulist=new Vector();

ul=ul.replace("[","");
ul=ul.replace("]","");
ul=ul.replace(MyServer.UPDATE_USERS,"");
StringTokenizer st=new StringTokenizer(ul,",");

while(st.hasMoreTokens())
{
String temp=st.nextToken();
ulist.add(temp);
}
client.usersList.setListData(ulist);
}
/////////////////////////
}
/*********************************/