package com.game;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserOrComputer implements ActionListener {

    static JFrame frame;        // main frame
    static Container c;     // container which contain all the components
    static JPanel panel;
    static JLabel opponent;
    static JButton vsComputer;
    static JButton vsUser2;
    static boolean user_clicked=false;    // user_clicked==true means play with frd else play with computer
    static JButton exit;


    UserOrComputer(){
        frame=new JFrame("Choose Opponent");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200,100,900,600);

        c=frame.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.BLACK);

        panel=new JPanel();
        panel.setBounds(200,80,500,400);
        panel.setBackground(new Color(200,10,120,70));
        panel.setLayout(null);
        c.add(panel);

        opponent=new JLabel("   Select Your Opponent ",JLabel.CENTER);
        opponent.setBounds(30,0,400,50);
        opponent.setForeground(Color.ORANGE);
        panel.add(opponent);
        opponent.setFont(new Font("Arial",Font.BOLD,30));

        vsUser2=new JButton("VS FRIEND");
        vsUser2.setBounds(150,100,200,50);
        vsUser2.setFont(new Font("Times New Roman",Font.BOLD,20));
        panel.add(vsUser2);
        vsUser2.addActionListener(this);  // called when clicked

        vsComputer=new JButton("VS COMPUTER");
        vsComputer.setBounds(150,200,200,50);
        vsComputer.setFont(new Font("Times New Roman",Font.BOLD,20));
        panel.add(vsComputer);
        vsComputer.addActionListener(this);  // called when clicked

        exit=new JButton("EXIT");
        exit.setBounds(200,350,100,40);
        exit.setFont(new Font("Times New Roman",Font.BOLD,15));
        panel.add(exit);
        exit.addActionListener(this);

    }

    // ActionListener abstract methods

    public void actionPerformed(ActionEvent e) {

        // checking mouse click

        if(e.getSource()==exit){
            System.exit(0);
        }

        if(e.getSource()==vsComputer){
            frame.dispose();
            user_clicked=false;      // User vs computer
            new UserName();
        }


        /* functionality when vsuser2 button clicked */
        if(e.getSource()==vsUser2){
            frame.dispose();
            user_clicked=true;         // User1 vs user2


            // creating StartPage object and calling some required function
            StartPage p= new StartPage();
            p.userNames();
            p.buttons();
            p.setGameType();

        }
    }
}

