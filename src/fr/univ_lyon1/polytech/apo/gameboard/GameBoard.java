package fr.univ_lyon1.polytech.apo.gameboard;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YOU
 */
public abstract class GameBoard implements Serializable {
    public final int width;
    public final int length;
    private int[][] board;
    private List<Turn> history;

    public GameBoard(int width, int length) {
        this.width = width;
        this.length = length;
        board = new int[width][length];
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<length;j++)
            {
                board[i][j]=0;
            }
        }
        history = new ArrayList<Turn>() {};
    }

    public GameBoard(int width, int length, List<Turn> history) {
        this.width = width;
        this.length = length;
        int error = (new int[1])[1]; //TODO: things with history
    }

    public int[][] get_board() {
        return board;
    }
    
    public void set_board(int i, int j, int player) {
        board[i][j] = player;
    }
   public boolean to_history (Turn turn)
   {
       return history.add(turn);
   }
   public List<Turn> get_history ()
   {
       return history;
   }
   public Turn get_history (int i)
   {
       return history.get(i);
   }
   
   public void display_history ()
   {
        for (Turn history1 : history) {
            System.out.println(history1.toString());
        }
   }
   
 
    public abstract void play(Turn turn);
    public abstract void play_loop(Player a, Player b);
    public abstract void cancel();   
    public abstract Turn lastTurn();
   /* {
        return null;
    }
    */
    public abstract Player win();
    /*{
        return null;
    }*/

    @Override
    public abstract String toString();
    /*{
        return "TODO";
    }*/
    
    public void display_gameboard(String s)
    {
        System.out.println(s);
    }
    /*
   */ 
    
    public int get_board(int i, int j)
    {
        return board[i][j];
    }
    
      public Player get_player (int i, int j)
    {
        int nb_coups_joues=get_history().size();
        for(int cpt = 1; cpt<=nb_coups_joues;cpt++)
        {
        if(get_history().get(nb_coups_joues-cpt).position.equals(new Position(i,j)));
        {
            return get_history().get(nb_coups_joues-cpt).player;
        }       

    }
        return null;
    }
    
    public Position next_position (Position pos, int direction)
            /*      1 2 3
                    4 5 6
                    7 8 9
            
                    La direction 5 est la direction défault et renvoie faux
            */
    {
        if(((direction == 1 || direction == 2 || direction == 3) && (pos.x + 1 >= length)) ||       //On vérifie qu'on ne déborde pas en hauteur en haut
                ((direction == 3 || direction == 6 || direction == 9) && (pos.y + 1 >= width)) ||   //On vérifie qu'on ne déborde pas en largeur à droite
                ((direction == 7 || direction == 8 || direction == 9) && (pos.x - 1 < 0)) ||        //On vérifie qu'on ne déborde pas en hauteur en bas
                ((direction == 1 || direction == 4 || direction == 7) && (pos.y - 1 < 0)))          //On vérifie qu'on ne déborde pas en largeur à gauche
        {
            return null;
        }
            
        switch (direction)
        {
            case 1:
                pos.x--;
                pos.y++;
                return pos;
                
            case 2:
                pos.y++;
                return pos;
                
            case 3:
                pos.x++;
                pos.y++;
                return pos;
                
            case 4:
                pos.x--;
                return pos;
                
            case 6 : 
                pos.x++;
                return pos;
                
            case 7 :
                pos.x--;
                pos.y--;
                return pos;
                
            case 8 :
                pos.y--;
                return pos;
                
            case 9 :
                pos.x++;
                pos.y--;
                return pos;
                
            default :
                return null;
        }
    }
}
