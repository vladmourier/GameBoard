package fr.univ_lyon1.polytech.apo.gameboard;

import fr.univ_lyon1.polytech.apo.gameboard.save.IncorrectInputException;

/**
 *
 * @author YOU
 */
public class Position {
    public int x;
    public int y;

    public Position (){
        this.x=0;
        this.y=0;
    }
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void parse(String seizure) throws IncorrectInputException//la chaine doit être de la forme "i,j"
    {
        if(seizure.isEmpty())
            throw new IncorrectInputException();
        String[] clean = seizure.split(",");
        boolean x_ok=false;
        
        int j = 0;
        if(clean[0].equals(""))//si la partie gauche de la chaine est vide, on considère que c'est 0
        {
            x=0;
        } else
        {
            for (int n = clean[0].length();  n > 0; n --)
            {
                x = x + (clean[0].charAt(j) - '0') * (int) Math.pow(10,(n-1));
                j++;
            }
            x_ok=true;
        }
        
        j = 0;
        if(clean.length==1 && x_ok==true)//idem si la partie droite est vide y=0
        {
            y=0;
        }else
        {
            for (int n = clean[1].length();  n > 0; n --)
            {
                y = y + (clean[1].charAt(j) - '0')* (int) Math.pow(10,(n-1));
                j++;
            }
        }
    }

    @Override
    public String toString() {
        return "TODO";
    }
    
    
}
