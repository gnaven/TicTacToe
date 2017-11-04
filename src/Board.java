/**
 * Created by naven on 2/3/2017.
 */
public class Board {

    public Board(){

    }

    //Makes a new empty matrix to represent new board
    public int[] Intial_State(){
        int [] new_board = new int[9];
        for (int i = 0; i<9; i++){

                new_board[i]=0;

        }
        return new_board;
    }

    public boolean Terminal_State(int[]state){

            int row_addition1= state[0]+state[1]+state[2];
            int row_addition2= state[3]+state[4]+state[5];
            int row_addition3= state[6]+state[7]+state[8];
            //clumn matches
            int column_addition1= state[0]+state[3]+state[6];
            int column_addition2= state[1]+state[4]+state[7];
            int column_addition3= state[2]+state[5]+state[8];

           if (row_addition1==3|| row_addition1 ==12||column_addition1==3||column_addition1==12 ||
        row_addition2==3|| row_addition2 ==12 || row_addition3==3|| row_addition3 ==12
                   ||column_addition2==3||column_addition2==12||column_addition3==3||column_addition3==12){
               return true;
           }


        int diagonal1= state[0]+state[4]+state[8];
        int diagonal2= state[2]+state[4]+state[6];

        if (diagonal1==3||diagonal1==12||diagonal2==3||diagonal2==12){
            return true;
        }

        for (int i = 0; i<9; i++){
            if (state[i]==0){
                return false;
            }
        }

       return true;
    }
    // takes in a state and returns the positions of the possible actions for that state

    public int [] Action( int [] state){
        int count=0;
        for (int i=0;i<state.length;i++){

                if (state[i]==0){

                    count++;

            }
        }
        // possible error maybe if count stays 0
        int [] Possible_Actions= new int [count];
        // to keep track of the number on the Possible_Actions array
        int tracker=0;
        for (int i=0;i<state.length;i++) {

                if (state[i]==0){

                    // inserts positions of the empty box on the TTT board
                    Possible_Actions[tracker]=i;

                    tracker++;

                }
        }
        return Possible_Actions;
    }



}
