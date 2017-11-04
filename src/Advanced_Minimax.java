/**
 * Created by naven on 2/14/2017.
 */
public class Advanced_Minimax {

    static Board TTT = new Board();
    static Minimax MM= new Minimax();
    public static int [][]Nine_Action_Make(int[][] state,int board_pos, int move, int player_num){


        state[board_pos][move]= player_num;

        return state;
    }


    public static int Partial_Util(int[][] state, int comp_num, int user_num){
        for (int i=0;i<state.length;i++) {
            int row1 = state[i][0] + state[i][1] + state[i][2];
            int row2 = state[i][6] + state[i][7] + state[i][8];
            int column1 = state[i][0] + state[i][3] + state[i][6];
            int column2 = state[i][2] + state[i][5] + state[i][8];
            int diagonal1 = state[i][0] + state[i][4] + state[i][8];
            int diagonal2 = state[i][0] + state[i][4] + state[i][8];

//            if ((row1 == user_num * 2 && row2 == user_num * 2) || (column1 == user_num * 2 && column2 == user_num * 2) || (row1 == user_num * 2 && column1 == user_num * 2)
//                    || (row1 == user_num * 2 && column2 == user_num * 2) || (row2 == user_num * 2 && column1 == user_num * 2) ||
//                    (row2 == user_num * 2 && column2 == user_num * 2) || (row1 == user_num * 2 && diagonal1 == user_num * 2)
//                    || (row1 == user_num * 2 && diagonal2 == user_num * 2) || (row2 == user_num * 2 && diagonal1 == user_num * 2) ||
//                    (row2 == user_num * 2 && diagonal2 == user_num * 2) || (column1 == user_num * 2 && diagonal1 == user_num * 2) ||
//                    (diagonal2 == user_num * 2 && column1 == user_num * 2) || (diagonal1 == user_num * 2 && column2 == user_num * 2) ||
//                    (diagonal2 == user_num * 2 && column2 == user_num * 2)) {
//
//                return -30;
//
//            }
//
//            if ((row1 == comp_num * 2 && row2 == comp_num * 2) || (column1 == comp_num * 2 && column2 == comp_num * 2) || (row1 == comp_num * 2 && column1 == comp_num * 2)
//                    || (row1 == comp_num * 2 && column2 == comp_num * 2) || (row2 == comp_num * 2 && column1 == comp_num * 2) ||
//                    (row2 == comp_num * 2 && column2 == comp_num * 2) || (row1 == comp_num * 2 && diagonal1 == comp_num * 2)
//                    || (row1 == comp_num * 2 && diagonal2 == comp_num * 2) || (row2 == comp_num * 2 && diagonal1 == comp_num * 2) ||
//                    (row2 == comp_num * 2 && diagonal2 == comp_num * 2) || (column1 == comp_num * 2 && diagonal1 == comp_num * 2) ||
//                    (diagonal2 == comp_num * 2 && column1 == comp_num * 2) || (diagonal1 == comp_num * 2 && column2 == comp_num * 2) ||
//                    (diagonal2 == comp_num * 2 && column2 == comp_num * 2)) {
//
//                return 30;
//
//            }
            int row_addition1 = state[i][0] + state[i][1] + state[i][2];
            int row_addition2 = state[i][3] + state[i][4] + state[i][5];
            int row_addition3 = state[i][6] + state[i][7] + state[i][8];
            int column_addition1 = state[i][0] + state[i][3] + state[i][6];
            int column_addition2 = state[i][1] + state[i][4] + state[i][7];
            int column_addition3 = state[i][2] + state[i][5] + state[i][8];
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


            int diagona1= state[i][0]+state[i][4]+state[i][8];
            int diagona2= state[i][2]+state[i][4]+state[i][6];

            if (3*comp_num==diagona1||3*comp_num==diagona2){
                return 40;
            }
            if (3*user_num==diagona1||3*user_num==diagona2){
                return -40;
            }
            
            if (row1 == user_num * 2 || row2 == user_num * 2 || column1 == user_num * 2 || column2 == user_num * 2 || diagonal1 == user_num * 2 || diagonal2 == user_num * 2) {
                return -10;
            }

            if (row1 == comp_num * 2 || row2 == comp_num * 2 || column1 == comp_num * 2 || column2 == comp_num * 2 || diagonal1 == comp_num * 2 || diagonal2 == comp_num * 2) {
                return 10;
            }

        }
        return 0;
    }



    public static int[] MaxValue(int[][] state, int BoardNum, int comp_num, int user_num,int depth){
        //      System.out.println("entered Max");
//        int [] State1= new int [state.length];
//        for(int i = 0; i< state.length; i++){
//            State1[i]= state[i];
//
//        }
        // PrintArray(State1);
        depth++;
        int[] Moves = new int[3];
        int compare= -10000;
        if (MM.NineTerminal(state)){
            int [] Utili= {0,0,MM.utility(state[BoardNum],comp_num,user_num)};
            //   System.out.println("terminal in max");

            return Utili;
        }
        if (depth>=9){
            int[] Utili= {0,0,Partial_Util(state,comp_num,user_num)};
            return Utili;
        }
//        System.out.println("Board that enters possible states "+ BoardNum);
//        MM.PrintArray(state[BoardNum]);
        int [] Possible_States= TTT.Action(state[BoardNum]);
        // loop that goes through all the possible actions


        for (int i = 0; i< Possible_States.length;i++){
            // temporary state after putting in one of the possible moves
//          System.out.println("total possible outcomes in max "+Possible_States.length);
            //         System.out.println(compare);

            int [][]Temp_State= Nine_Action_Make(state,BoardNum, Possible_States[i],comp_num);
            // System.out.println("goes to min");

            int[] Util= MinValue(Temp_State,Possible_States[i],comp_num,user_num,depth);

            //  System.out.println("back from min");
            //confusion with recursion here
            if (Util[2]>compare){
                compare=Util[1];
                Moves[0]=BoardNum;
                Moves[1]= Possible_States[i] ;

                Moves[2]= compare;
                //   System.out.println("In max util "+compare);
            }
            //Temp_State=TTT.Intial_State();
            //unmakes the action
            Nine_Action_Make(Temp_State,BoardNum,Possible_States[i],0);
        }

        // System.out.println("Out "+Moves[0]+" , "+Moves[1]);
        //System.out.println("in Max");
        return Moves;
    }
    public static int[] MinValue(int[][] state,int BoardNum, int comp_num, int user_num,int depth){
        //System.out.println("entered MIN");
//        int [] State1= new int [state.length];
//        for(int i = 0; i< state.length; i++){
//            State1[i]= state[i];
//        }
        //
        depth++;
        int[] Moves = new int[3];
        int compare= 10000;
        if (MM.NineTerminal(state)){
            int [] Utili= {0,0,MM.utility(state[BoardNum],comp_num,user_num)};
            //  System.out.println("terminal in min");
            return Utili;
        }
        if (depth>=9){
            int[] Utili= {0,0,Partial_Util(state,comp_num,user_num)};
            return Utili;
        }
        int [] Possible_States= TTT.Action(state[BoardNum]);
        // loop that goes through all the possible actions

        for (int i = 0; i< Possible_States.length;i++){
            // temporary state after putting in one of the possible moves
            //  System.out.println("total possible outcomes in min "+Possible_States.length);
            //System.out.println(compare);
            int [][]Temp_State= Nine_Action_Make(state,BoardNum, Possible_States[i],user_num);
            // System.out.println("goes to max");
            int[] Util= MaxValue(Temp_State,BoardNum,comp_num,user_num,depth);
            //  System.out.println("back from max");
            //confusion with recursion here

            if (Util[2]<compare){
                compare=Util[1];
                //  System.out.println("prob"+Possible_States[i][0]+" , " +Possible_States[i][1]);
                Moves[0]= BoardNum;
                Moves[1]= Possible_States[i] ;
                Moves[2]= compare;
                // System.out.println("in min util "+ compare);
            }
            //unmakes the action
            Nine_Action_Make(Temp_State,BoardNum,Possible_States[i],0);

        }

        // System.out.println("in Min");

        return Moves;
    }
}
