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

    

    // COMPUTER LOGIC FUNCTION

    int compLogic(){
        int moves=0;          // variable which store the computer move
        blank_space.clear();
        cornersOpened.clear();
        edgeOpened.clear();
        for(int i=0;i<9;i++){
            if(btn[i].getIcon()==null){
                blank_space.add(i);

            }
        }

        // Check for Win First    // computer symbol is O
        char[] symbols={'O','X'};
        for(char let:symbols) {
            for (int i : blank_space) {
                copyArray();
                values_array_copy[i] = let;
                if (checkWinner(values_array_copy, let)) {
                    btn[i].setIcon(ic2);
                    values_array[i] = 'O';
                    if(isWinner(values_array,'O')){
                        winnerName.setText("COMPUTER AI WON");
                        user2wins+=1;
                        user2Symbol.setEnabled(true);
                        user2SymbolLabel.setEnabled(true);
                        user1Symbol.setEnabled(false);
                        user1SymbolLable.setEnabled(false);
                        user2Symbol.setIcon(ic22);
                    }
                    turn=true;
                    return 1;
                }

            }
        }

        if(btn[4].getIcon()==ic1){
            // checking corner is opened or not
            for(int j:blank_space){
                if(j==0 || j==2 || j==6 || j==8){
                    cornersOpened.add(j);
                }
            }

            if(cornersOpened.size()>0){
                moves=selectRandom(cornersOpened);
                btn[moves].setIcon(ic2);
                values_array[moves]='O';
                turn=true;
                return 1;
            }


            // checking edge is opened or not
            for(int j:blank_space){
                if(j==1 || j==3 || j==5 || j==7){
                    edgeOpened.add(j);
                }
            }

            if(edgeOpened.size()>0) {
                moves = selectRandom(edgeOpened);
                btn[moves].setIcon(ic2);
                values_array[moves] = 'O';
                turn = true;
                return 1;
            }

        }
        else {
            for(int k:blank_space){
                if(k==4){
                    moves=4;
                    btn[4].setIcon(ic2);
                    values_array[4]='O';
                    turn=true;
                    return 1;
                }
            }

            for(int j:blank_space){
                if(j==0 || j==2 || j==6 || j==8){
                    cornersOpened.add(j);
                }
            }

            if(cornersOpened.size()>0){
                moves=selectRandom(cornersOpened);
                btn[moves].setIcon(ic2);
                values_array[moves]='O';
                turn=true;
                return 1;
            }
            // checking edge is opened or not
            for(int j:blank_space){
                if(j==1 || j==3 || j==5 || j==7){
                    edgeOpened.add(j);
                }
            }

            if(edgeOpened.size()>0) {
                moves = selectRandom(edgeOpened);
                btn[moves].setIcon(ic2);
                values_array[moves] = 'O';
                turn = true;
                return 1;
            }
        }

        return 0;
    }


    int selectRandom(List<Integer> values){
        Random rand=new Random();
        int rand_val=rand.nextInt(values.size());
        return values.get(rand_val);
    }

    void copyArray(){
        for(int i=0;i<9;i++){
            values_array_copy[i]=values_array[i];
        }
    }


    //FOR SHOWING SCOREBOARD

    void showScoreBoard(){

        scoreBoard.removeAll();
        scoreBoard.revalidate();
        scoreBoard.repaint();
        String name;
        if(UserOrComputer.user_clicked){
            name=StartPage.user1tf.getText()+"       vs       "+StartPage.user2tf.getText();
        }
        else {

            name=UserName.user_name_tf.getText()+"       vs       "+"COMPUTER AI";
        }

        playernames=new JLabel(name,JLabel.CENTER);
        playernames.setFont(new Font("Times New Roman",Font.BOLD,20));
        playernames.setBounds(150,10,300,40);
        scoreBoard.add(playernames);

        JButton match1btn=new JButton("MATCH 1");
        match1btn.setBounds(10,60,130,40);
        scoreBoard.add(match1btn);

        JButton match2btn=new JButton("MATCH 2");
        match2btn.setBounds(10,120,130,40);
        scoreBoard.add(match2btn);

        JButton match3btn=new JButton("MATCH 3");
        match3btn.setBounds(10,180,130,40);
        scoreBoard.add(match3btn);


        if(user1wins>user2wins){
            if(UserOrComputer.user_clicked){
                finalwinner=new JLabel("Final Winner : "+StartPage.user1tf.getText());
            }
            else {
                finalwinner=new JLabel("Final Winner : "+UserName.user_name_tf.getText());
            }
        }
        else if (user1wins<user2wins){
            if(UserOrComputer.user_clicked){
                finalwinner=new JLabel("Final Winner : "+StartPage.user2tf.getText());
            }
            else{
                finalwinner=new JLabel("Final Winner : computer");
            }
        }
        else {
            finalwinner=new JLabel("Final Winner : Series Tie");
        }
        finalwinner.setBounds(100,400,500,40);
        finalwinner.setFont(new Font("Times New Roman",Font.BOLD,30));
        finalwinner.setBackground(Color.RED);
        scoreBoard.add(finalwinner);


//LABEL for shown result
        game1Result1=new JLabel(game1r1,JLabel.CENTER);
        game1Result1.setBounds(170,60,100,40);
        game1Result1.setFont(new Font("Times New Roman",Font.BOLD,20));
        scoreBoard.add(game1Result1);

        game2Result1=new JLabel(game2r1,JLabel.CENTER);
        game2Result1.setBounds(170,120,100,40);
        game2Result1.setFont(new Font("Times New Roman",Font.BOLD,20));
        scoreBoard.add(game2Result1);

        game3Result1=new JLabel(game3r1,JLabel.CENTER);
        game3Result1.setBounds(170,180,100,40);
        game3Result1.setFont(new Font("Times New Roman",Font.BOLD,20));
        scoreBoard.add(game3Result1);

        game1Result2=new JLabel(game1r2,JLabel.CENTER);
        game1Result2.setBounds(350,60,100,40);
        game1Result2.setFont(new Font("Times New Roman",Font.BOLD,20));
        scoreBoard.add(game1Result2);

        game2Result2=new JLabel(game2r2,JLabel.CENTER);
        game2Result2.setBounds(350,120,100,40);
        game2Result2.setFont(new Font("Times New Roman",Font.BOLD,20));
        scoreBoard.add(game2Result2);

        game3Result2=new JLabel(game3r2,JLabel.CENTER);
        game3Result2.setBounds(350,180,100,40);
        game3Result2.setFont(new Font("Times New Roman",Font.BOLD,20));
        scoreBoard.add(game3Result2);

    }



    void updateScoreboardUser1(int gameNumber){
        String username1="null";
        String username2="null";
        if(UserOrComputer.user_clicked){
            username1=StartPage.user1tf.getText().toUpperCase()+" WON";
            username2=StartPage.user2tf.getText().toUpperCase()+" WON";
        }
        else{
            username1=UserName.user_name_tf.getText().toUpperCase()+" WON";
            username2="COMPUTER AI WON";
        }


//GAME1 result
        if(gameNumber==1 && winnerName.getText().equalsIgnoreCase(username1) ){
            game1r1="WON";
            game1r2="LOST";
        }
        else if(gameNumber==1 && winnerName.getText().equalsIgnoreCase(username2) ){
            game1r1="LOST";
            game1r2="WON";
        }
        else if(gameNumber==1 && winnerName.getText().equalsIgnoreCase("MATCH TIE")){
            game1r1="TIE";
            game1r2="TIE";
        }

//GAME2 result
        if(gameNumber==2 && winnerName.getText().equalsIgnoreCase(username1) ){
            game2r1="WON";
            game2r2="LOST";
        }
        else if(gameNumber==2 && winnerName.getText().equalsIgnoreCase(username2) ){
            game2r1="LOST";
            game2r2="WON";
        }
        else if(gameNumber==2 && winnerName.getText().equalsIgnoreCase("MATCH TIE")){
            game2r1="TIE";
            game2r2="TIE";
        }


        //GAME3 result
        if(gameNumber==3 && winnerName.getText().equalsIgnoreCase(username1) ){
            game3r1="WON";
            game3r2="LOST";
        }
        else if(gameNumber==3 && winnerName.getText().equalsIgnoreCase(username2) ){
            game3r1="LOST";
            game3r2="WON";
        }
        else if(gameNumber==3 && winnerName.getText().equalsIgnoreCase("MATCH TIE")){
            game3r1="TIE";
            game3r2="TIE";
        }

    }







    //CHECKING WINNER


    boolean isWinner(char[] val ,int let){

        //row1
        if (val[0]==let && val[1]==let && val[2]==let){
            lockBoard();
            btn[0].setEnabled(true);
            btn[1].setEnabled(true);
            btn[2].setEnabled(true);
            if(let=='X'){
                btn[0].setIcon(ic11);
                btn[1].setIcon(ic11);
                btn[2].setIcon(ic11);
            }
            else{
                btn[0].setIcon(ic22);
                btn[1].setIcon(ic22);
                btn[2].setIcon(ic22);
            }
            return true;
        }
        //ROW 2
        if(val[3]==let && val[4]==let && val[5]==let){
            lockBoard();
            btn[3].setEnabled(true);
            btn[4].setEnabled(true);
            btn[5].setEnabled(true);
            if(let=='X'){
                btn[3].setIcon(ic11);
                btn[4].setIcon(ic11);
                btn[5].setIcon(ic11);
            }
            else{
                btn[3].setIcon(ic22);
                btn[4].setIcon(ic22);
                btn[5].setIcon(ic22);
            }
            return  true;
        }
//ROW 3
        if (val[6]==let && val[7]==let && val[8]==let){
            lockBoard();
            btn[6].setEnabled(true);
            btn[7].setEnabled(true);
            btn[8].setEnabled(true);
            if(let=='X'){
                btn[6].setIcon(ic11);
                btn[7].setIcon(ic11);
                btn[8].setIcon(ic11);
            }
            else{
                btn[6].setIcon(ic22);
                btn[7].setIcon(ic22);
                btn[8].setIcon(ic22);
            }
            return true;
        }

        //COLUMN 1
        if(val[0]==let && val[3]==let && val[6]==let){
            lockBoard();
            btn[0].setEnabled(true);
            btn[3].setEnabled(true);
            btn[6].setEnabled(true);
            if(let=='X'){
                btn[0].setIcon(ic11);
                btn[3].setIcon(ic11);
                btn[6].setIcon(ic11);
            }
            else{
                btn[0].setIcon(ic22);
                btn[3].setIcon(ic22);
                btn[6].setIcon(ic22);
            }

            return  true;
        }

//COLUMN 2
        if (val[1]==let && val[4]==let && val[7]==let){
            lockBoard();
            btn[1].setEnabled(true);
            btn[4].setEnabled(true);
            btn[7].setEnabled(true);
            if(let=='X'){
                btn[1].setIcon(ic11);
                btn[4].setIcon(ic11);
                btn[7].setIcon(ic11);
            }
            else{
                btn[1].setIcon(ic22);
                btn[4].setIcon(ic22);
                btn[7].setIcon(ic22);
            }

            return true;
        }
        //COLUMN 3
        if(val[2]==let && val[5]==let && val[8]==let){
            lockBoard();
            btn[2].setEnabled(true);
            btn[5].setEnabled(true);
            btn[8].setEnabled(true);
            if(let=='X'){
                btn[2].setIcon(ic11);
                btn[5].setIcon(ic11);
                btn[8].setIcon(ic11);
            }
            else{
                btn[2].setIcon(ic22);
                btn[5].setIcon(ic22);
                btn[8].setIcon(ic22);
            }

            return  true;
        }
// DIAGONAL top left to bottom right
        if (val[0]==let && val[4]==let && val[8]==let){
            lockBoard();
            btn[0].setEnabled(true);
            btn[4].setEnabled(true);
            btn[8].setEnabled(true);
            if(let=='X'){
                btn[0].setIcon(ic11);
                btn[4].setIcon(ic11);
                btn[8].setIcon(ic11);
            }
            else{
                btn[0].setIcon(ic22);
                btn[4].setIcon(ic22);
                btn[8].setIcon(ic22);
            }

            return true;
        }

        //DIAGONAL top right to bottom left
        if(val[2]==let && val[4]==let && val[6]==let){
            lockBoard();
            btn[2].setEnabled(true);
            btn[4].setEnabled(true);
            btn[6].setEnabled(true);
            if(let=='X'){                           //ic1=X
                btn[2].setIcon(ic11);
                btn[4].setIcon(ic11);
                btn[6].setIcon(ic11);
            }
            else{
                btn[2].setIcon(ic22);
                btn[4].setIcon(ic22);
                btn[6].setIcon(ic22);
            }

            return  true;
        }
        return false;
    }






    boolean checkWinner(char[] val ,int let){

        //row1
        if (val[0]==let && val[1]==let && val[2]==let){
            return true;
        }
        //ROW 2
        if(val[3]==let && val[4]==let && val[5]==let){
            return  true;
        }
//ROW 3
        if (val[6]==let && val[7]==let && val[8]==let){
            return true;
        }

        //COLUMN 1
        if(val[0]==let && val[3]==let && val[6]==let){
            return  true;
        }

//COLUMN 2
        if (val[1]==let && val[4]==let && val[7]==let){

            return true;
        }
        //COLUMN 3
        if(val[2]==let && val[5]==let && val[8]==let){
            return  true;
        }
// DIAGONAL top left to bottom right
        if (val[0]==let && val[4]==let && val[8]==let){
            return true;
        }

        //DIAGONAL top right to bottom left
        if(val[2]==let && val[4]==let && val[6]==let){
            return  true;
        }
        return false;
    }
}

