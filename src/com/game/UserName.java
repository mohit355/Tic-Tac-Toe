package com.game;

// this page open when user select computer as a opponent

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserName implements FocusListener, ActionListener {

    static JTextField user_name_tf;
    static JFrame frame;
    static JLabel user_name;
    static JButton next;
    static JButton back;
    static String user;


    UserName(){

        frame=new JFrame("Login Form");
        frame.setVisible(true);
        frame.setBounds(200,100,900,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Container c= frame.getContentPane();
        c.setFocusable(false);

        //BackGround Image
        ImageIcon img=new ImageIcon(getClass().getClassLoader().getResource("bg.jpg"));
        //code for resizing image
        Image i=img.getImage();
        Image new_img=i.getScaledInstance(900,600,Image.SCALE_SMOOTH);
        img=new ImageIcon(new_img);

        //adding BG img
        JLabel BG=new JLabel("",img,JLabel.CENTER);
        BG.setBounds(0,0,900,600);


        //  ***************  HEADER  *******************

        JPanel heading=new JPanel();
        heading.setBounds(0,0,900,60);
        heading.setBackground(new Color(0,0,0,80));  // 80 is the opticity
        BG.add(heading);

        JLabel head=new JLabel("Enter your name ");
        Font f=new Font("Times New Roman",Font.BOLD,40);
        head.setFont(f);
        head.setForeground(Color.GREEN);
        heading.add(head);

        // ***************** LOGIN PANEL *************************

        Font login_font=new Font("Arial",Font.BOLD,20);
        JPanel login=new JPanel();
        login.setBounds(250,170,400,350);
        login.setBackground(new Color(0,0,0,60));
        login.setLayout(null);
        BG.add(login);


        /* creating login Panel */

        user_name=new JLabel("Enter your Name");
        Font usenameFont=new Font("Times New Roman",Font.BOLD,20);
        user_name.setFont(usenameFont);
        user_name.setBounds(50,50,200,40);
        user_name.setForeground(Color.cyan);
        login.add(user_name);


        user_name_tf=new JTextField("Enter your name");
        user_name_tf.setBounds(50,100,300,50);
        user_name_tf.setForeground(Color.WHITE);
        user_name_tf.setFont(login_font);
        user_name_tf.setBackground(new Color(210,180,140));
        user_name_tf.addFocusListener(this);
        login.add(user_name_tf);



        back=new JButton("Back");
        back.setBounds(50,200,100,50);
        back.setBackground(new Color(160,182,45));
        back.addActionListener( this);
        login.add(back);

        next=new JButton("Next");
        next.setBounds(250,200,100,50);
        next.setBackground(new Color(160,182,45));
        next.addActionListener(this);
        login.add(next);


        c.add(BG);
        c.revalidate();
    }



    /* adding abstract method of FocusLintener and ActionListener */

    public void focusGained(FocusEvent e) {
        if(e.getSource()==user_name_tf) {
            String user=user_name_tf.getText();
            if (user.equals("Enter your name") || user.equals("Enter a valid name")) {

                user_name_tf.setText("");
            }
        }
    }



    public void focusLost(FocusEvent e) {
        if(e.getSource()==user_name_tf){
            String user=user_name_tf.getText();
            if(user.equals("")){
                user_name_tf.setText("Enter your name");
            }
        }



    }


    public void actionPerformed(ActionEvent e) {
        String userID ="";
        if (e.getSource() == back) {
            frame.dispose();
            new UserOrComputer();

        }
        if(e.getSource()==next){

            userID = user_name_tf.getText();

            if(userID.equals("Enter your name")){
                user_name_tf.setText("Enter your name");
            }
            else if(userID.length()==0){
                user_name_tf.setText("Enter a valid name");
            }
            else{
                frame.dispose();
                new StartPage();
                StartPage.setGameType();
                StartPage.buttons();

            }
        }
    }
}


