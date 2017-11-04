/**
 * Created by naven on 2/4/2017.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Minimax {

    static Board TTT = new Board();{
    }
    static Advanced_Minimax AM= new Advanced_Minimax();

    // calculates the utility of the terminal state with 1 or max,-1 for min, and 0 for tie
    public static int utility(int[] terminal_state, int comp_num, int user_num){

        int row_addition1 = terminal_state[0] + terminal_state[1] + terminal_state[2];
        int row_addition2 = terminal_state[3] + terminal_state[4] + terminal_state[5];
        int row_addition3 = terminal_state[6] + terminal_state[7] + terminal_state[8];
        int column_addition1 = terminal_state[0] + terminal_state[3] + terminal_state[6];
        int column_addition2 = terminal_state[1] + terminal_state[4] + terminal_state[7];
        int column_addition3 = terminal_state[2] + terminal_state[5] + terminal_state[8];
            if  (3*comp_num==row_addition1||3*comp_num==column_addition1||
                    3*comp_num==row_addition2||3*comp_num==column_addition2||
                    3*comp_num==row_addition3||3*comp_num==column_addition3){
                return 40;
            }
            if  (3*user_num==row_addition1||3*user_num==column_addition1||
                    3*user_num==row_addition2||3*user_num==column_addition2||
                    3*user_num==row_addition3||3*user_num==column_addition3){
                return -40;
            }


        int diagonal1= terminal_state[0]+terminal_state[4]+terminal_state[8];
        int diagonal2= terminal_state[2]+terminal_state[4]+terminal_state[6];

        if (3*comp_num==diagonal1||3*comp_num==diagonal2){
            return 40;
        }
        if (3*user_num==diagonal1||3*user_num==diagonal2){
            return -40;
        }

       return 0;
    }
    // inserts the computers move in the board
    public static void PrintArray(int[] state){
        for (int i=0;i<state.length;i++){
            System.out.print(state[i]+" ");
            if (i==2||i==5||i==8) {
                System.out.println();
            }
        }
    }



    public static int []Action_Make(int[] state, int move, int player_num){


        state[move]= player_num;

        return state;
    }



    public static int[] MaxValue(int[] state, int comp_num, int user_num){
      //      System.out.println("entered Max");
        int [] State1= new int [state.length];
        for(int i = 0; i< state.length; i++){
            State1[i]= state[i];

        }
       // PrintArray(State1);
         int[] Moves = new int[2];
        int compare= -10000;
        if (TTT.Terminal_State(State1)){
            int [] Utili= {0,utility(State1,comp_num,user_num)};
        //   System.out.println("terminal in max");

            return Utili;
        }
        int [] Possible_States= TTT.Action(State1);
        // loop that goes through all the possible actions


        for (int i = 0; i< Possible_States.length;i++){
            // temporary state after putting in one of the possible moves
//          System.out.println("total possible outcomes in max "+Possible_States.length);
   //         System.out.println(compare);

            int []Temp_State= Action_Make(State1,Possible_States[i],comp_num);
           // System.out.println("goes to min");
            int[] Util= MinValue(Temp_State,comp_num,user_num);
          //  System.out.println("back from min");
            //confusion with recursion here
            if (Util[1]>compare){
                compare=Util[1];
                Moves[0]= Possible_States[i] ;
                Moves[1]= compare;
             //   System.out.println("In max util "+compare);
            }
            //Temp_State=TTT.Intial_State();
            //unmakes the action
           Action_Make(Temp_State,Possible_States[i],0);
        }

       // System.out.println("Out "+Moves[0]+" , "+Moves[1]);
        //System.out.println("in Max");
        return Moves;
    }
    public static int[] MinValue(int[] state, int comp_num, int user_num){
        //System.out.println("entered MIN");
        int [] State1= new int [state.length];
        for(int i = 0; i< state.length; i++){
            State1[i]= state[i];
        }
       // PrintArray(State1);
        int[] Moves = new int[2];
        int compare= 10000;
        if (TTT.Terminal_State(State1)){
            int [] Utili= {0,utility(State1,comp_num,user_num)};
          //  System.out.println("terminal in min");
            return Utili;
        }
        int [] Possible_States= TTT.Action(State1);
        // loop that goes through all the possible actions

        for (int i = 0; i< Possible_States.length;i++){
            // temporary state after putting in one of the possible moves
          //  System.out.println("total possible outcomes in min "+Possible_States.length);
            //System.out.println(compare);
            int [] Temp_State= Action_Make(State1,Possible_States[i],user_num);
           // System.out.println("goes to max");
            int[] Util= MaxValue(Temp_State,comp_num,user_num);
          //  System.out.println("back from max");
            //confusion with recursion here

            if (Util[1]<compare){
                compare=Util[1];
              //  System.out.println("prob"+Possible_States[i][0]+" , " +Possible_States[i][1]);
                Moves[0]= Possible_States[i] ;
                Moves[1]= compare;
               // System.out.println("in min util "+ compare);
            }
            //unmakes the action
           Action_Make(Temp_State,Possible_States[i],0);

        }

       // System.out.println("in Min");

        return Moves;
    }

    //Intializer for 9X9 board

        public static int [][] AdvancedBoard() {
        int[][] nineboard = new int[9][9];
        for (int i=0;i<nineboard.length;i++){
            for (int j=0;j<nineboard[i].length;j++){
                nineboard[i][j]=0;
            }
        }
        return nineboard;
        }


        //9 board terminal state check
        public static boolean NineTerminal(int[][]state){

            for (int i=0; i<state.length;i++){
                boolean x= TTT.Terminal_State(state[i]);
                if (x){
                    return x;
                }
            }

        return false;
        }

        //nine board utility

    //method that controls the 9x9 board

        public static void AdvancedGamed(){
        int[][]nineboard = AdvancedBoard();

            Scanner scan = new Scanner (System.in);
            System.out.println("Do you want to play X or O");

            String choice = scan.nextLine();
            boolean user= false;
            int user_num=4;
            int comp_num=1;
            if (choice.equalsIgnoreCase("X")){
                user= true;
                user_num=1;
                comp_num=4;
            }
            int l=0;
            int [] user_moves=new int[2];
            Random ran = new Random();
            int movefollow=ran.nextInt(8);

            do {

                if (user){
                    if (l==0){
                        System.out.println("Enter the board number in which you want to make your move");
                        movefollow= scan.nextInt();
                    }
                    l++;
                    System.out.println("You will be making a move on board "+movefollow);
                    for (int i=0;i<nineboard.length;i++) {
                        System.out.println(i);
                        PrintArray(nineboard[i]);
                    }
                    System.out.println("Enter your move");
                   // int move1= scan.nextInt();
                    int move2= scan.nextInt();
                    // add restriction of movement according to the position
                   // movefollow=move1;
                     //nineboard[movefollow]= Action_Make(nineboard[move1],move2,user_num);
                    nineboard = AM.Nine_Action_Make(nineboard, movefollow, move2, user_num);
                    user=false;
                    movefollow=move2;
                }else {
                    int [] nineMove= AM.MaxValue(nineboard,movefollow,comp_num,user_num,0);
                            //MaxValue(nineboard[movefollow],comp_num,user_num);
                   nineboard=AM.Nine_Action_Make(nineboard,movefollow,nineMove[1],comp_num);
                    System.out.println("Computer Movement: "+ movefollow+" "+nineMove[1]);
                    user =true;
                    movefollow=nineMove[1];
                    l++;
                }



            }while(!NineTerminal(nineboard));



        }
    // making the method through

    //Game control/Start method
    public static void CurrentGame(){
        int []board= TTT.Intial_State();

        Scanner scan = new Scanner (System.in);
        System.out.println("Do you want to play X or O");
//        int []board1= {1,4,1,4,4,1,4,1,0};
//      System.out.println(TTT.Terminal_State(board1));

        String choice = scan.nextLine();
        boolean user= false;
        int user_num=4;
        int comp_num=1;
        if (choice.equalsIgnoreCase("X")){
            user= true;
            user_num=1;
            comp_num=4;
        }

      do {

            if (user==true){
                System.out.println("Enter your move");
                int move1=scan.nextInt();


                board= Action_Make(board,move1,user_num);
                user= false;
            }else{
                // get move from the maxValue function and then put that in the currfent board function
                long start = System.currentTimeMillis();
                int []Moves;
                        Moves= MaxValue(board,comp_num,user_num);
                long elapsedTime= System.currentTimeMillis()-start;
                System.out.println(elapsedTime/1000F);
                board=Action_Make(board,Moves[0],comp_num);
                System.out.println("Computer Movement: "+ Moves[0]);
                user =true;

            }

            PrintArray(board);

      }while (!TTT.Terminal_State(board));


      if (utility(board,comp_num,user_num)>=10){
          System.out.println("Computer wins");
      }else if (utility(board,comp_num,user_num)==0){
          System.out.println("It's a tie");
      }else{
          System.out.println("You win, WTF");
        }

    }

public static void main(String[]args){
System.out.println("If you want to play basic TTT press 1 and if you want to play advanced press 2");
Scanner scan=new Scanner (System.in);
    int pref= scan.nextInt();
    if (pref==1) {
        CurrentGame();
    }else {
        AdvancedGamed();
    }
}

}
