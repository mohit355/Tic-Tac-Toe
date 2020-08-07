package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main implements ActionListener  {

    static JFrame frame;
    static JButton next;
    static JButton about;

    Main(){

        frame=new JFrame("Tic-Tac-Toe");
        frame.setVisible(true);
        frame.setBounds(200,100,900,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c= frame.getContentPane();
        c.setBackground(Color.BLACK);
        frame.setResizable(false);

        //BackGround Image
        ImageIcon img=new ImageIcon(getClass().getClassLoader().getResource("t3.jpg"));

        //code for resizing image
        Image i=img.getImage();
        Image new_img=i.getScaledInstance(900,450,Image.SCALE_SMOOTH);
        img=new ImageIcon(new_img);

        //adding BG img
        JLabel BG=new JLabel("",img,JLabel.CENTER);
        BG.setBounds(0,0,900,450);




        // ***********  HEADER  **********************************

        JPanel heading=new JPanel();
        heading.setBounds(0,0,900,60);
        heading.setBackground(Color.WHITE);  // 80 is the opticity
        heading.setLayout(null);
        BG.add(heading);

        JLabel head=new JLabel("WELCOME TO",JLabel.CENTER);
        Font f=new Font("Arial",Font.BOLD,40);
        head.setFont(f);
        head.setBounds(0,0,800,60);
        head.setForeground(Color.GREEN);
        head.setBackground(Color.WHITE);
        heading.add(head);

        about =new JButton("About");
        Font aboutFont=new Font("Times New Roman",Font.BOLD,20);
        about.setFont(aboutFont);
        about.setBounds(801,0,98,60);
        about.setForeground(Color.YELLOW);
        about.setBackground(new Color(210,180,140));
        about.addActionListener(this);
        heading.add(about);










        //  ***************  FOOTER  *******************

        JPanel footer=new JPanel();
        footer.setBounds(0,505,900,60);
        footer.setBackground(Color.RED);
        footer.setLayout(null);
        BG.add(footer);

        next=new JButton("NEXT");
        Font footerFont=new Font("Times New Roman",Font.BOLD,40);
        next.setFont(footerFont);
        next.setBounds(0,0,900,60);
        next.setForeground(Color.RED);
        next.addActionListener(this);
        footer.add(next);

        c.add(BG);
        c.revalidate();


    }


    public static void main(String[] args) {

        new Main();
    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==about){
            JOptionPane.showMessageDialog(null,"This Game is developed by Mohit","About",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource()==next){
            frame.dispose();
            new UserOrComputer();
        }
    }
}



