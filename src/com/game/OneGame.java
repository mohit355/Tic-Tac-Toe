package com.game;


import javax.swing.*;
import javax.swing.border.Border;   // for applying border
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OneGame implements ActionListener  {
    static boolean turn=true;   // true means user chance and false means computer chance
    static boolean winner=false;  // false means no one is winner
    static int noWinner=0;
    static JFrame frame;
    static Container c;
    static JPanel panel;
    static JPanel panel0;
    static JLabel lbl;
    static JPanel scoreBoard;
    static Border border;
    static JLabel winnerName;
    static JLabel matchNo;
    static JButton[] btn =new JButton[9];
    static JButton newGame ;
    static JButton quit;
    static JButton fullScoreBoard;
    static JLabel user1SymbolLable;
    static JLabel user2SymbolLabel;
    static JLabel user1Symbol;
    static JLabel user2Symbol;
    static List<Integer> blank_space=new ArrayList<Integer>();
    static List<Integer> cornersOpened=new ArrayList<Integer>();
    static List<Integer> edgeOpened=new ArrayList<Integer>();
    static int moves=0;
    static char[] values_array={' ',' ',' ',' ',' ',' ',' ',' ',' '};
    static char[] values_array_copy={' ',' ',' ',' ',' ',' ',' ',' ',' '};
    static String game1r1="";
    static String game1r2="";
    ImageIcon ic1,ic2,ic11,ic22;



    // Constructor
    OneGame() {

        border = BorderFactory.createLineBorder(Color.black,4);
        ic1=new ImageIcon(getClass().getClassLoader().getResource("ic1.jpg"));      // X
        ic2=new ImageIcon(getClass().getClassLoader().getResource("ic2.jpg"));      // O
        ic11=new ImageIcon(getClass().getClassLoader().getResource("ic11.jpg"));    //  dark X
        ic22=new ImageIcon(getClass().getClassLoader().getResource("ic22.jpg"));  // dark O

        Image i1=ic1.getImage();
        Image new_imgic1=i1.getScaledInstance(120,120,Image.SCALE_SMOOTH);
        ic1=new ImageIcon(new_imgic1);

        Image i2=ic2.getImage();
        Image new_imgic2=i2.getScaledInstance(120,120,Image.SCALE_SMOOTH);
        ic2=new ImageIcon(new_imgic2);

        Image i3=ic11.getImage();
        Image new_imgic11=i3.getScaledInstance(120,120,Image.SCALE_SMOOTH);
        ic11=new ImageIcon(new_imgic11);

        Image i4=ic22.getImage();
        Image new_imgic22=i4.getScaledInstance(120,120,Image.SCALE_SMOOTH);
        ic22=new ImageIcon(new_imgic22);


        frame=new JFrame("Tic-Tac-Toe (One Game)");
        frame.setVisible(true);
        frame.setBounds(200,100,900,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c=frame.getContentPane();
        c.setLayout(null);
        frame.setResizable(false);



        lbl=new JLabel("",JLabel.CENTER);
        lbl.setBounds(0,0,900,600);
        c.add(lbl);

        JPanel title=new JPanel();
        title.setBounds(0,0,900,50);
        title.setBackground(Color.BLUE);
        lbl.add(title);


        panel0=new JPanel();
        panel0.setBounds(0,50,400,550);
        panel0.setBackground(Color.BLACK);
        panel0.setLayout(null);
        lbl.add(panel0);

        matchNo=new JLabel("MATCH 1");
        matchNo.setBounds(140,20,200,30);
        matchNo.setFont(new Font("Arial",Font.BOLD,30));
        matchNo.setForeground(Color.RED);
        panel0.add(matchNo);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBounds(20,70,360,360);
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(3,3));
        panel0.add(panel);


        // Adding ActionListener to all buttons of grid
        for(int i=0;i<9;i++){
            btn[i]=new JButton();
            btn[i].addActionListener(this);
            panel.add(btn[i]);


        }



        // New Game BUTTON
        newGame=new JButton("NEW GAME");
        newGame.setBounds(70,450,245,50);
        newGame.setFont(new Font("Arial",Font.BOLD,15));
        newGame.addActionListener(this);
        panel0.add(newGame);

        quit=new JButton("Back");
        quit.setBounds(400,500,240,61);
        quit.addActionListener(this);
        lbl.add(quit);

        fullScoreBoard=new JButton("Exit");
        fullScoreBoard.setFont(new Font("Arial",Font.BOLD,15));
        fullScoreBoard.addActionListener(this);
        fullScoreBoard.setBounds(640,500,245,61);
        lbl.add(fullScoreBoard);


        // Score Board
        scoreBoard=new JPanel();
        scoreBoard.setBounds(400,50,500,450);
        scoreBoard.setBackground(Color.CYAN);
        scoreBoard.setLayout(null);
        lbl.add(scoreBoard);


        winnerName=new JLabel("",JLabel.CENTER);
        winnerName.setFont(new Font("Times New Roman",Font.BOLD,40));
        winnerName.setBounds(20,300,400,60);
        winnerName.setForeground(Color.RED);
        //winnerName.setBorder(border);
        scoreBoard.add(winnerName);


        if (UserOrComputer.user_clicked) {
            user1SymbolLable=new JLabel(StartPage.user1tf.getText(),JLabel.CENTER);
            user2SymbolLabel=new JLabel(StartPage.user2tf.getText(),JLabel.CENTER);
        }
        else {
            user1SymbolLable=new JLabel(UserName.user_name_tf.getText(),JLabel.CENTER);
            user2SymbolLabel=new JLabel("COMPUTER AI",JLabel.CENTER);
        }
        user1SymbolLable.setBounds(0,10,240,40);
        user1SymbolLable.setFont(new Font("Times New Roman",Font.BOLD,25));
        user2SymbolLabel.setBounds(260,10,240,40);
        user2SymbolLabel.setFont(new Font("Times New Roman",Font.BOLD,25));
        scoreBoard.add(user1SymbolLable);
        scoreBoard.add(user2SymbolLabel);

        JLabel vs=new JLabel("VS",JLabel.CENTER);
        vs.setBounds(225,100,60,40);
        vs.setFont(new Font("Times New Roman",Font.BOLD,30));
        scoreBoard.add(vs);

        user1Symbol=new JLabel("",ic11,JLabel.CENTER);
        user2Symbol=new JLabel("",ic2,JLabel.CENTER);
        user1Symbol.setBounds(0,60,240,120);
        user2Symbol.setBounds(260,60,240,120);
        scoreBoard.add(user1Symbol);
        scoreBoard.add(user2Symbol);



        c.validate();
        panel.validate();



    }

