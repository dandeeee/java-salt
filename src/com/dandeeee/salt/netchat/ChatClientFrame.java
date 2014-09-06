package com.dandeeee.salt.netchat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class ChatClientFrame extends JFrame{
    private JFrame jfrmChatClientFrame;
    private JPanel jpnlLoginPane;
    private JPanel jpnlChatPane;
    private JPanel jpnlMessagePane;
    private JTextArea jtxtActiveTextArea;
    private final JTextArea jtxtChatTextArea;
    private final JTextArea jtxtMessageTextArea;
    private final JScrollPane jscrChatScrollPane;

    ClientConnection clientConnection;

    void shutdownConnection(){
        //System.out.println(clientConnection.clientSocket.isConnected());
        if(clientConnection!=null){
            try {
                clientConnection.closeConnection();
            } catch (Exception e) {
                System.out.println("ChatClientFrame.shutdownConnection: ERROR " + e.getMessage());
            }
            System.out.println("ChatClientFrame.shutdownConnection: Client closed the connection.");


        }
    }

    ChatClientFrame(){

        /////////////////////////////////////////////////////////////////////
        // Making the frame
        jfrmChatClientFrame = new JFrame();
        jfrmChatClientFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("ChatClientFrame: CLOSING");
                shutdownConnection();
                System.exit(0);
            }
        });

        jfrmChatClientFrame.setLayout(new FlowLayout());
        jfrmChatClientFrame.setBounds(100, 100, 400, 670);
        jfrmChatClientFrame.setResizable(false);


        /////////////////////////////////////////////////////////////////////
        // Login Pane
        jpnlLoginPane = new JPanel();
        jpnlLoginPane.setLayout(new BorderLayout());
        jpnlLoginPane.setBackground(Color.yellow);
        jpnlLoginPane.setPreferredSize(new Dimension(380, 100));
        // Label with active users
        jtxtActiveTextArea = new JTextArea();
        jtxtActiveTextArea.setBackground(Color.red);
        jtxtActiveTextArea.setLineWrap(true);
        jtxtActiveTextArea.setWrapStyleWord(true);
        // Adding all these to the Chat Pane
        jpnlLoginPane.add(jtxtActiveTextArea);


        /////////////////////////////////////////////////////////////////////
        // Chat Pane
        jpnlChatPane = new JPanel();
        jpnlChatPane.setLayout(new BorderLayout());
        jpnlChatPane.setBackground(Color.red);
        // Text area as output area
        jtxtChatTextArea = new JTextArea();
        jtxtChatTextArea.setEditable(false);
        jtxtChatTextArea.setLineWrap(true);
        jtxtChatTextArea.setWrapStyleWord(true);
        // Put it into a scrollable area
        jscrChatScrollPane = new JScrollPane(jtxtChatTextArea);
        jscrChatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscrChatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jscrChatScrollPane.setPreferredSize(new Dimension(380, 350));
        jscrChatScrollPane.setAutoscrolls(true);
        // Adding all these to the Chat Pane
        jpnlChatPane.add(jscrChatScrollPane);


        /////////////////////////////////////////////////////////////////////
        // Message Pane
        jpnlMessagePane = new JPanel();
        jpnlMessagePane.setLayout(new BorderLayout());
        jpnlMessagePane.setBackground(Color.green);
        // Text area as input area
        jtxtMessageTextArea = new JTextArea();
        jtxtMessageTextArea.setEditable(true);
        jtxtMessageTextArea.setLineWrap(true);
        jtxtMessageTextArea.setWrapStyleWord(true);
        // Put it into a scrollable area
        JScrollPane jscrMessageScrollPane = new JScrollPane(jtxtMessageTextArea);
        jscrMessageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscrMessageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jscrMessageScrollPane.setPreferredSize(new Dimension(380, 150));
        // Button SEND
        JButton jbtnMessageSend = new JButton("Send");
        jbtnMessageSend.setPreferredSize(new Dimension(180, 20));
        jbtnMessageSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String msg = jtxtMessageTextArea.getText();
                jtxtMessageTextArea.setText("");
                try{
                    clientConnection.sendMessage(msg);
                }catch(Exception e){
                    System.out.println("ChatClientFrame.SEND-button: ERROR " + e.getMessage());
                }
            }
        });


        // Adding all these to the Message Pane
        jpnlMessagePane.add(jscrMessageScrollPane,BorderLayout.PAGE_START);
        jpnlMessagePane.add(jbtnMessageSend);

        /////////////////////////////////////////////////////////////////////
        // Pupulating the components into the frame
        jfrmChatClientFrame.add(jpnlLoginPane);
        jfrmChatClientFrame.add(jpnlChatPane);
        jfrmChatClientFrame.add(jpnlMessagePane);
        jfrmChatClientFrame.setVisible(true);


        // Starting the networking
        User user = new User("Guest");
        clientConnection = new ClientConnection(user, 55555, this);

    }

    void updateUI(String msg){
        jtxtChatTextArea.append(msg+"\n");
    }

}