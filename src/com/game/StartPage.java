package com.game;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage implements ActionListener {

    static JFrame frame;
    static Container c;

    /* Variables for buttons */
    static JButton start;
    static JButton back;

    /* required Jlabel variables */
    static JLabel lbl;
    static JLabel user1Name;
    static JLabel user2Name;
    static JLabel game_lbl;

    static JComboBox matchType;    // combobox for game mode selection
    static JPanel panel;
    static JPanel symbol;
    static Border blackline;

    static JTextField user1tf;
    static JTextField user2tf;
    static String[] match={"One Game","3 Game series","5 Game series"};
    static boolean allChecked1=false;   // check length of user_name1
    static boolean allChecked2=false;   // check length of user_name2
    static String matchPattern;
    static ImageIcon ic1,ic2;

    StartPage(){
        ic1=new ImageIcon(getClass().getClassLoader().getResource("ic1.jpg"));      // X
        ic2=new ImageIcon(getClass().getClassLoader().getResource("ic2.jpg"));      // O
        blackline = BorderFactory.createLineBorder(Color.black,4);    // border

        /* Creating frame and frame title */
        frame=new JFrame("Tic Tac Toe");
        frame.setBounds(200,100,900,600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        c=frame.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.BLACK);

        lbl=new JLabel("",JLabel.CENTER);
        lbl.setBounds(0,0,900,600);
        lbl.setBackground(new Color(0,255,0,20));
        c.add(lbl);

        Font f=new Font("Arial",Font.BOLD,40);


        //titel Jpanel
        panel=new JPanel();
        panel.setBounds(0,0,900,50);
        panel.setBackground(new Color(0,51,51,80));
        panel.setLayout(null);
        panel.setBorder(blackline);
        lbl.add(panel);

        //add title on panel
        JLabel title=new JLabel("Tic-Tac-Toe",JLabel.CENTER);
        panel.add(title);
        title.setBounds(0,0,900,50);
        title.setFont(f);
        title.setForeground(new Color(76,0,153));


        start=new JButton("START");
        start.setForeground(Color.RED);
        start.addActionListener(this);
        lbl.add(start);

        back=new JButton("BACK");
        back.setForeground(Color.RED);
        back.addActionListener(this);
        lbl.add(back);

        showUserSymbols();

    }


    static void userNames(){
        user1Name=new JLabel("Enter Player1 Name : ");
        user2Name=new JLabel("Enter Player2 Name : ");
        user1Name.setBounds(15,100,220,40);
        user2Name.setBounds(15,170,220,40);
        user1Name.setForeground(Color.BLUE);
        user2Name.setForeground(Color.BLUE);
        user1Name.setFont(new Font("Times New Roman",Font.BOLD,22));
        user2Name.setFont(new Font("Times New Roman",Font.BOLD,22));
        lbl.add(user1Name);
        lbl.add(user2Name);

        user1tf=new JTextField();
        user1tf.setText("");

        user2tf=new JTextField();
        user2tf.setText("");
        user1tf.setFont(new Font("Times New Roman",Font.BOLD,20));
        user2tf.setFont(new Font("Times New Roman",Font.BOLD,20));
        user1tf.setBounds(250,100,200,40);
        user2tf.setBounds(250,170,200,40);
        lbl.add(user1tf);
        lbl.add(user2tf);
    }



    static void setGameType(){
        Font fs=new Font("Times New Roman",Font.BOLD,25);
        game_lbl=new JLabel(" Select Game Type");
        game_lbl.setFont(fs);
        game_lbl.setBounds(20,270,300,40);
        game_lbl.setForeground(new Color(70,0,150));
        lbl.add(game_lbl);


        matchType=new JComboBox(match);
        matchType.setBounds(250,270,200,40);
        matchType.setFont(new Font("Times New Roman",Font.BOLD,20));
        lbl.add(matchType);
    }

    // setting location of start and back button
    static void buttons(){
        start.setBounds(250,400,200,50);
        back.setBounds(30,400,200,50);
    }


    // function for showing username and symbol so that user will know their assigned symbol
    static void showUserSymbols(){
        symbol=new JPanel();
        symbol.setLayout(null);
        symbol.setBounds(570,100,300,400);
        symbol.setBackground(Color.cyan);
        lbl.add(symbol);


        if(UserOrComputer.user_clicked){
            JLabel n1=new JLabel("Player1 Symbol ");
            n1.setBounds(30,50,200,30);
            n1.setFont(new Font("Times New Roman",Font.BOLD,22));
            symbol.add(n1);

            JLabel n1symbol=new JLabel("",ic1,JLabel.CENTER);
            n1symbol.setBounds(100,70,120,120);
            symbol.add(n1symbol);

            JLabel n2=new JLabel("Player2 Symbol ");
            n2.setBounds(30,200,200,30);
            n2.setFont(new Font("Times New Roman",Font.BOLD,22));
            symbol.add(n2);

            JLabel n2symbol=new JLabel("",ic2,JLabel.CENTER);
            n2symbol.setBounds(100,240,120,120);
            symbol.add(n2symbol);
        }
        else{

            String name=UserName.user_name_tf.getText();

            JLabel playerName=new JLabel("Player1 Name : "+name);
            JLabel compName=new JLabel("Player2 Name : Computer AI");
            playerName.setFont(new Font("Times New Roman",Font.BOLD,30));
            compName.setFont(new Font("Times New Roman",Font.BOLD,30));
            playerName.setForeground(Color.BLUE);
            compName.setForeground(Color.BLUE);
            playerName.setBounds(30,70,500,40);
            compName.setBounds(30,150,400,40);
            lbl.add(playerName);
            lbl.add(compName);

            JLabel n1=new JLabel(name+"'s Symbol");
            n1.setBounds(30,50,200,30);
            n1.setFont(new Font("Times New Roman",Font.BOLD,22));
            symbol.add(n1);

            JLabel n1symbol=new JLabel("",ic1,JLabel.CENTER);
            n1symbol.setBounds(100,70,120,120);
            symbol.add(n1symbol);

            JLabel n2=new JLabel("Computer's Symbol ");
            n2.setBounds(30,200,200,30);
            n2.setFont(new Font("Times New Roman",Font.BOLD,22));
            symbol.add(n2);

            JLabel n2symbol=new JLabel("",ic2,JLabel.CENTER);
            n2symbol.setBounds(100,240,120,120);
            symbol.add(n2symbol);


        }

    }


    /* Defining abstarct methods of interface ActionListener */
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==start){
            FiveGame.gameNumber=1;
            ThreeGame.gameNumber=1;
            if(UserOrComputer.user_clicked){
                if(user1tf.getText().length()>=3){
                    allChecked1=true;
                }
                if(user2tf.getText().length()>=3){
                    allChecked2=true;
                }

                if(allChecked1 && allChecked2){
                    matchPattern=(String) matchType.getSelectedItem();
                    if(matchPattern.equals("One Game")){
                        frame.dispose();
                        for(int p1=0;p1<9;p1++){
                            OneGame.values_array[p1]=' ';
                            OneGame.values_array_copy[p1]=' ';
                        }
                        OneGame.turn=true;
                        new OneGame();

                    }
                    else if(matchPattern.equals("3 Game series")){
                        frame.dispose();
                        for(int p1=0;p1<9;p1++){
                            ThreeGame.values_array[p1]=' ';
                            ThreeGame.values_array_copy[p1]=' ';
                        }


                        new ThreeGame();
                    }
                    else if(matchPattern.equals("5 Game series")){
                        frame.dispose();
                        for(int p1=0;p1<9;p1++){
                            FiveGame.values_array[p1]=' ';
                            FiveGame.values_array_copy[p1]=' ';
                        }

                        new FiveGame();
                    }
                }
                else {

                    JOptionPane.showMessageDialog(null,"User name must be greater than 3","Error",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                matchPattern=(String) matchType.getSelectedItem();
                if(matchPattern.equals("One Game")){
                    for(int p=0;p<9;p++){
                        OneGame.values_array[p]=' ';
                        OneGame.values_array_copy[p]=' ';
                    }
                    OneGame.turn=true;
                    frame.dispose();
                    new OneGame();
                }
                else if(matchPattern.equals("3 Game series")){
                    frame.dispose();
                    ThreeGame.turn=true;
                    for(int p=0;p<9;p++){
                        ThreeGame.values_array[p]=' ';
                        ThreeGame.values_array_copy[p]=' ';
                    }

                    new ThreeGame();
                }
                else if(matchPattern.equals("5 Game series")){
                    frame.dispose();
                    FiveGame.turn=true;
                    for(int p=0;p<9;p++){
                        FiveGame.values_array[p]=' ';
                        FiveGame.values_array_copy[p]=' ';
                    }
                    new FiveGame();

                }
            }


        }


        if(e.getSource()==back){
            frame.dispose();
            new UserOrComputer();
        }
    }
}

