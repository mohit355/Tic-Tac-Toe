package com.game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreeGame implements ActionListener {
    static boolean turn=true;   // true means user chance and false means computer chance
    static boolean winner=false;  // false means no one is winner
    static int noWinner=0;
    static JButton quit;
    static JButton scoresBoard;
    static JFrame frame;
    static Container c;
    static JPanel panel;
    static JPanel panel0;
    static JLabel lbl;
    static JPanel scoreBoard;
    static JLabel playernames;
    static JLabel user1SymbolLable;
    static JLabel user2SymbolLabel;
    static JLabel user1Symbol;
    static JLabel user2Symbol;
    static JLabel finalwinner;
    static JLabel winnerName;
    static JLabel matchNo;
    static JButton[] btn =new JButton[9];
    static JButton newGame ;
    static JButton fullScoreBoard;
    static int gameNumber=1;
    static List<Integer> blank_space=new ArrayList<Integer>();
    static List<Integer> cornersOpened=new ArrayList<Integer>();
    static List<Integer> edgeOpened=new ArrayList<Integer>();
    static int d;
    static char[] values_array={' ',' ',' ',' ',' ',' ',' ',' ',' '};
    static char[] values_array_copy={' ',' ',' ',' ',' ',' ',' ',' ',' '};
    static int user1wins=0;
    static int user2wins=0;

    //variables for updating scoreboard
    static JLabel game1Result1;
    static JLabel game2Result1;
    static JLabel game3Result1;
    static JLabel game1Result2;
    static JLabel game2Result2;
    static JLabel game3Result2;
    static String game1r1="";
    static String game2r1="";
    static String game3r1="";
    static String game1r2="";
    static String game2r2="";
    static String game3r2="";
    static boolean leavegame;

    ImageIcon ic1,ic2,ic11,ic22,ic111,ic222;


    ThreeGame() {

        blank_space.clear();
        cornersOpened.clear();
        edgeOpened.clear();

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



        frame=new JFrame("Tic-Tac-Toe (Three Game Series)");
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

        for(int i=0;i<9;i++){
            btn[i]=new JButton();
            btn[i].addActionListener(this);
            panel.add(btn[i]);


        }




        // New GAME BUTTON
        newGame=new JButton(" PLAY MATCH 2");
        newGame.setBounds(640,500,245,61);
        newGame.setFont(new Font("Arial",Font.BOLD,15));
        //newGame.setBackground(new Color(0,0,0,40));
        newGame.addActionListener(this);
        lbl.add(newGame);

        quit=new JButton("QUIT");
        quit.setBounds(400,500,240,61);
        quit.addActionListener(this);
        lbl.add(quit);

        scoresBoard=new JButton("Show Scores");
        scoresBoard.setFont(new Font("Arial",Font.BOLD,15));
        lbl.add(scoresBoard);
        scoresBoard.addActionListener(this);

        fullScoreBoard=new JButton("Play Again");
        fullScoreBoard.setFont(new Font("Arial",Font.BOLD,15));
        fullScoreBoard.addActionListener(this);
        fullScoreBoard.setBounds(640,500,245,61);


        // Score Board
        scoreBoard=new JPanel();

        scoreBoard.setBounds(400,50,500,450);
        scoreBoard.setBackground(Color.CYAN);
        scoreBoard.setLayout(null);
        lbl.add(scoreBoard);

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



        winnerName=new JLabel("",JLabel.CENTER);
        winnerName.setFont(new Font("Times New Roman",Font.BOLD,40));
        winnerName.setBounds(20,300,400,60);
        winnerName.setForeground(Color.RED);
        scoreBoard.add(winnerName);


        c.validate();
        panel.validate();



    }


    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==scoresBoard){

            if(UserOrComputer.user_clicked){                  // checking that someone leave the game in between
                if(noWinner>=0 && noWinner<9 && turn){
                    leavegame=true;
                    winnerName.setText(StartPage.user2tf.getText()+" WON");
                    user2wins+=1;

                }
                else if(noWinner>=0 && noWinner<9 && !turn){
                    leavegame=false;
                    winnerName.setText(StartPage.user1tf.getText()+" WON");
                    user1wins+=1;
                }

            }
            else {
                if(winnerName.getText().equals("")){
                    leavegame=true;
                    winnerName.setText("COMPUTER AI WON");
                    user2wins+=1;
                }
            }
            updateScoreboardUser1(gameNumber);
            lockBoard();
            showScoreBoard();


            matchNo.setText("");
            lbl.remove(scoresBoard);
            lbl.revalidate();
            lbl.repaint();
            lbl.add(fullScoreBoard);
        }

        if(e.getSource()==fullScoreBoard){
            for(int p1=0;p1<9;p1++){
                ThreeGame.values_array[p1]=' ';
                ThreeGame.values_array_copy[p1]=' ';
            }
            ThreeGame.turn=true;
            ThreeGame.noWinner=0;
            ThreeGame.gameNumber=1;
            ThreeGame.game1r1="";
            ThreeGame.game2r1="";
            ThreeGame.game3r1="";
            ThreeGame.game1r2="";
            ThreeGame.game2r2="";
            ThreeGame.game3r2="";
            frame.dispose();

            new ThreeGame();

        }


        if(e.getSource()==quit){
            frame.dispose();
            if(!UserOrComputer.user_clicked) {

                StartPage ps = new StartPage();

                ps.setGameType();
                ps.buttons();
                turn=true;
                gameNumber=1;
                noWinner=0;
                edgeOpened.clear();
                cornersOpened.clear();
                blank_space.clear();
                for(int p=0;p<9;p++){
                    values_array[p]=' ';
                    values_array_copy[p]=' ';
                }
                game1r1="";
                game2r1="";
                game3r1="";
                game1r2="";
                game2r2="";
                game3r2="";


            }
            else{
                StartPage p = new StartPage();
                p.setGameType();
                p.buttons();
                p.userNames();
                turn=true;
                gameNumber=1;
                noWinner=0;
                for(int p1=0;p1<9;p1++){
                    values_array[p1]=' ';
                }
                game1r1="";
                game2r1="";
                game3r1="";
                game1r2="";
                game2r2="";
                game3r2="";

            }


        }

        if(e.getSource()==newGame){
            if(UserOrComputer.user_clicked){
                if(noWinner>=0 && noWinner<9 && turn){
                    leavegame=true;
                    winnerName.setText(StartPage.user2tf.getText()+" WON");
                    user2wins+=1;
                }
                else if(noWinner>=0 && noWinner<9 && !turn){
                    leavegame=false;
                    winnerName.setText(StartPage.user1tf.getText()+" WON");
                    user1wins+=1;
                }

            }
            else {
                if(winnerName.getText().equals("")){
                    leavegame=true;
                    winnerName.setText("COMPUTER AI WON");
                    user2wins+=1;
                }
            }

            updateScoreboardUser1(gameNumber);
            turn=true;
            noWinner=0;
            gameNumber+=1;
            edgeOpened.clear();
            cornersOpened.clear();
            blank_space.clear();
            for(int p=0;p<9;p++){
                values_array[p]=' ';
                values_array_copy[p]=' ';
            }

            if(gameNumber<3){
                matchNo.setText("MATCH "+String.valueOf(gameNumber));
                newGame.setText("PLAY MATCH "+String.valueOf(gameNumber+1));
                unlockedBoard();
            }
            else if(gameNumber==3){
                unlockedBoard();
                matchNo.setText("MATCH "+String.valueOf(gameNumber));
                newGame.setEnabled(false);
                lbl.remove(newGame);
                lbl.revalidate();
                lbl.repaint();
                scoresBoard.setBounds(640,500,245,61);
            }
            winnerName.setText("");

            //whoseTurn.setText("USER1 TURN");
            user2Symbol.setEnabled(true);
            user1Symbol.setEnabled(true);
            user1SymbolLable.setEnabled(true);
            user2SymbolLabel.setEnabled(true);
            user2Symbol.setIcon(ic2);
            user1Symbol.setIcon(ic11);
        }
        else {
            if(turn){                           // CODE FOR USER    // first turn is of user

                for(int i=0;i<9;i++){
                    if(e.getSource()==btn[i]) {
                        if (btn[i].getIcon() == null) {
                            btn[i].setIcon(ic1);     // ic1 foe use and ic2 for computer
                            values_array[i]='X';
                            turn=false;
                            winner =isWinner(values_array,'X');
                            if(winner){
                                if(UserOrComputer.user_clicked){
                                    user2Symbol.setEnabled(false);
                                    user2SymbolLabel.setEnabled(false);
                                    winnerName.setText(StartPage.user1tf.getText().toUpperCase()+" WON");
                                    user1wins+=1;
                                }
                                else{
                                    winnerName.setText(UserName.user_name_tf.getText().toUpperCase()+" WON");
                                    user1wins+=1;
                                }
                                noWinner=0;
                                break;
                            }
                            else{
                                noWinner=noWinner+1;
                                if(noWinner==9){
                                    winnerName.setText("MATCH TIE");
                                    lockBoard();
                                    break;
                                }


                            }
                            user1Symbol.setIcon(ic1);
                            if (UserOrComputer.user_clicked) {
                                user2Symbol.setIcon(ic22);
                            }

                        }
                        if(!UserOrComputer.user_clicked && !turn){
                            d=2;
                            d=compLogic();
                            if(d==0){
                                winnerName.setText("MATCH TIE");
                                lockBoard();
                                user1Symbol.setEnabled(false);
                                user2Symbol.setEnabled(false);
                                user1SymbolLable.setEnabled(false);
                                user2SymbolLabel.setEnabled(false);
                            }
                            user1Symbol.setIcon(ic11);



                        }
                    }
                }
            }
            else if (!turn){                   // CODE FOR COMPUTER

                if(UserOrComputer.user_clicked){
                    for(int i=0;i<9;i++){
                        if(e.getSource()==btn[i]) {
                            if (btn[i].getIcon() == null) {
                                btn[i].setIcon(ic2);     // ic1 foe use and ic2 for computer
                                values_array[i]='O';
                                turn=true;
                                winner =isWinner(values_array,'O');
                                if(winner){
                                    winnerName.setText(StartPage.user2tf.getText().toUpperCase()+" WON");
                                    user2wins+=1;
                                    user1Symbol.setEnabled(false);
                                    user1SymbolLable.setEnabled(false);
                                    noWinner=0;
                                    break;
                                }
                                else{
                                    noWinner=noWinner+1;
                                    if(noWinner==9){
                                        winnerName.setText("MATCH TIE");
                                        lockBoard();
                                        break;
                                    }


                                }
                                user2Symbol.setIcon(ic2);
                                user1Symbol.setIcon(ic11);
                            }
                        }
                    }
                }



            }
        }



    }




    //For Disable all the box

    public void lockBoard(){

        for(int i=0;i<9;i++){
            panel.getComponent(i).setEnabled(false);
        }

    }

    // For Enable all the box

    public void unlockedBoard() {
        for (int i = 0; i < 9; i++) {
            panel.getComponent(i).setEnabled(true);
            btn[i].setIcon(null);

        }
        turn=true;
    }
