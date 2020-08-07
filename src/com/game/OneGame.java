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


    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==quit){
            frame.dispose();
            if(!UserOrComputer.user_clicked) {

                new StartPage();
                StartPage.setGameType();
                StartPage.buttons();
                turn=true;
                noWinner=0;
                edgeOpened.clear();
                cornersOpened.clear();
                blank_space.clear();
                for(int p=0;p<9;p++){
                    values_array[p]=' ';
                    values_array_copy[p]=' ';
                }
                game1r1="";
                game1r2="";


            }
            else{
                new StartPage();
                StartPage.setGameType();
                StartPage.buttons();
                StartPage.userNames();
                turn=true;
                noWinner=0;
                for(int p1=0;p1<9;p1++){
                    values_array[p1]=' ';

                }
                game1r1="";
                game1r2="";

            }
        }

        if(e.getSource()==fullScoreBoard){
            frame.dispose();
            System.exit(0);
        }

        if(e.getSource()==newGame){
            turn=true;
            for(int i=0;i<9;i++){
                values_array[i]=' ';
                values_array_copy[i]=' ';
            }
            noWinner=0;
            unlockedBoard();
            winnerName.setText("");
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
                            values_array[i] = 'X';
                            btn[i].setIcon(ic1);     // ic1 foe use and ic2 for computer
                            turn = false;

                            winner = isWinner(values_array, 'X');
                            if (winner) {
                                if (UserOrComputer.user_clicked) {
                                    winnerName.setText(StartPage.user1tf.getText()+" WON");
                                    game1r1="WON";
                                    game1r2="LOST";

                                    user2Symbol.setEnabled(false);
                                    user2SymbolLabel.setEnabled(false);
                                } else {
                                    winnerName.setText(UserName.user_name_tf.getText()+" WON");
                                    game1r1="WON";
                                    game1r2="LOST";

                                }
                                noWinner = 0;
                                break;
                            } else {
                                noWinner = noWinner + 1;
                                if (noWinner == 9) {
                                    winnerName.setText("MATCH TIE");
                                    game1r1="TIE";
                                    game1r2="TIE";

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
                            int d= 0;
                            d = compLogic();

                            if(d==0){
                                winnerName.setText("MATCH TIE");
                                game1r1="TIE";
                                game1r2="TIE";

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
                                values_array[i]='O';
                                btn[i].setIcon(ic2);     // ic1 foe use and ic2 for computer
                                turn=true;
                                winner =isWinner(values_array,'O');
                                if(winner){
                                    winnerName.setText(StartPage.user2tf.getText()+" WON");
                                    game1r1="LOST";
                                    game1r2="WON";

                                    noWinner=0;
                                    user1Symbol.setEnabled(false);
                                    user1SymbolLable.setEnabled(false);
                                    break;
                                }
                                else{
                                    noWinner=noWinner+1;
                                    if(noWinner==9){
                                        winnerName.setText("MATCH TIE");
                                        game1r1="TIE";
                                        game1r2="TIE";

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

    // Function for Computer Moves

    int compLogic() {

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
                        game1r1="LOST";
                        game1r2="WON";

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
        // checking for center opened
        for(int k:blank_space){
            if(k==5){
                moves=5;
                btn[5].setIcon(ic2);
                values_array[5]='O';
                turn=true;
                return 1;
            }
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

