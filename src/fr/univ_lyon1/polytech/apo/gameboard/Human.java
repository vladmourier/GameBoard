/**
 * @author p1206512 & p1206617
 * COMTE Adrien & MOURIER Vladimir
 */

package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.Scanner;

public class Human extends Player {
    
    public Human(int i, GameBoard gameboard)
    {
       super(i, gameboard);
    }
        
    /**
     *
     * @return
     */
    @Override
    public Turn play()
    {
        Turn tour;
         Position position = new Position(0,0);
        boolean valide=false;
        while (valide==false)
        {
        try{
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir une position de la forme X,Y :");
        String str = sc.nextLine();
        System.out.println("Vous avez saisi : " + str);
        position.parse(str);
        valide=true;
        }catch(IncorrectInputException i)
        {
            System.out.println("Chaine de caractères non valide, recommencez");
        }
        }
        tour = new Turn(position,this);
        return tour;
    }
    

}
