/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.Scanner;

/**
 *
 * @author p1206617
 */
public class Human extends Player {
    
    public Human(int i)
    {
       super(i);
    }
        
    @Override
    public Turn play()
    {
        Turn tour;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir une position de la forme X,Y :");
        String str = sc.nextLine();
        System.out.println("Vous avez saisi : " + str);
        Position position;
        position = new Position(0,0);
        position.parse(str);
        
        tour = new Turn(position,this);
        return tour;
    }
    
    @Override
    public Turn random_play(boolean b)
    {
        return play();
    }
}
