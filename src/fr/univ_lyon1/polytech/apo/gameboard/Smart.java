/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ_lyon1.polytech.apo.gameboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Vladimir
 */
public class Smart extends Stupid {
    

    public Smart(int i, GameBoard b)
    {
        super(i, b);
    }
    
    public Turn play()
    {
        if(board.get_history().isEmpty())
        { return new Turn (new Position(1,1), this);}
        int[] v = new int [8];         //tableau qui stock l'avantage de chaque cas de victoire
        for(int i = 0; i < 3; i ++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(board.get_board(i, j) == this.number)        //la case est jouée par smart, elle vaut 1 point de priorité
                {
                    v[i]++;
                    v[j+3]++;
                    if((i+j)%2 == 0)                //la  case est sur la diagonale
                    {
                        if((i == 0) && (j == 0))
                        {
                            v[7]++;
                        }
                        else if((i == 0) && (j == 2))
                        {
                            v[6]++;
                        }
                        else if((i == 1) && (j == 1))
                        {
                            v[6]++;
                            v[7]++;
                        }
                        else if((i == 2) && (j == 0))
                        {
                            v[6]++;
                        }
                        else if((i == 2) && (j == 2))
                        {
                            v[7]++;
                        }
                    }
                }
                if((board.get_board(i, j) != this.number) && (board.get_board(i, j) != 0))      //la carte est jouée par l'adversaire, elle vaut -1 point de priorité
                {
                    v[i]--;
                    v[j+3]--;
                    if((i+j)%2 == 0)                //la  case est sur la diagonale
                    {
                        if((i == 0) && (j == 0))
                        {
                            v[7]--;
                        }
                        else if((i == 0) && (j == 2))
                        {
                            v[6]--;
                        }
                        else if((i == 1) && (j == 1))
                        {
                            v[6]--;
                            v[7]--;
                        }
                        else if((i == 2) && (j == 0))
                        {
                            v[6]--;
                        }
                        else if((i == 2) && (j == 2))
                        {
                            v[7]--;
                        }
                    }
                }
            }
        }
        // on a ici pour chaque cas de vistoire le nombre de pions adverses et smart
            List<Position> pose_gagnante = new ArrayList<>();
            pose_gagnante=((TicTacToe)board).next_win();
            if(!pose_gagnante.isEmpty())
            {
                Random r = new Random();
                
                int x=pose_gagnante.get(r.nextInt(pose_gagnante.size())).x;
                int y=pose_gagnante.get(r.nextInt(pose_gagnante.size())).y;
                while(board.get_board(x, y)!=0)
                {
                    x=pose_gagnante.get(r.nextInt(pose_gagnante.size())).x;
                    y=pose_gagnante.get(r.nextInt(pose_gagnante.size())).y;
                }if(board.get_board(x, y)==0){
                Position p = new Position(x,y);
                Turn t=new Turn(p, this);
                return t;
                }
            }        
        for(int i = 0; i < 8; i++)
        {

            
            if (v[i] == 2)             //smart peut gagner ce tour ci !!
            {
                if(i < 3)
                {
                    for(int j = 0; j < 3; j ++)
                    {
                        if(board.get_board(i, j) == 0)
                        {
                            Position p = new Position(i, j);
                            Turn t = new Turn(p, this);
                            return t;
                        }
                    }
                }
                else if(i < 6)
                {
                   for(int j = 0; j < 3; j ++)
                    {
                        if(board.get_board(j, i-3) == 0)
                        {
                            Position p = new Position(j, i-3);
                            Turn t = new Turn(p, this);
                            return t;
                        }
                    } 
                }
                else if (i == 6)
                {
                    for(int j = 0; j < 3; j ++)
                    {
                        if(board.get_board(j, 2-j) == 0)
                        {
                            Position p = new Position(j, 2-j);
                            Turn t = new Turn(p, this);
                            return t;
                        }
                    }
                }
                else
                {
                    for(int j = 0; j < 3; j ++)
                    {
                        if(board.get_board(j, j) == 0)
                        {
                            Position p = new Position(j, j);
                            Turn t = new Turn(p, this);
                            return t;
                        }
                    }
                }
            }
            else if (v[i] == -2)             //smart peut éviter de perdre ce coup ci !!
            {
            pose_gagnante=((TicTacToe)board).next_win();
            if(!pose_gagnante.isEmpty())
            {
                Random r = new Random();
                
                int x=pose_gagnante.get(r.nextInt(pose_gagnante.size())).x;
                int y=pose_gagnante.get(r.nextInt(pose_gagnante.size())).y;
                while(board.get_board(x, y)!=0)
                {
                    x=pose_gagnante.get(r.nextInt(pose_gagnante.size())).x;
                    y=pose_gagnante.get(r.nextInt(pose_gagnante.size())).y;
                }if(board.get_board(x, y)==0){
                Position p = new Position(x,y);
                Turn t=new Turn(p, this);
                return t;
            }
            }
                if(i < 3)
                {
                    for(int j = 0; j < 3; j ++)
                    {
                        if(board.get_board(i, j) == 0)
                        {
                            Position p = new Position(i, j);
                            Turn t = new Turn(p, this);
                            return t;
                        }
                    }
                }
                else if(i < 6)
                {
                   for(int j = 0; j < 3; j ++)
                    {
                        if(board.get_board(j, i-3) == 0)
                        {
                            Position p = new Position(j, i-3);
                            Turn t = new Turn(p, this);
                            return t;
                        }
                    } 
                }
                else if (i == 6)
                {
                    for(int j = 0; j < 3; j ++)
                    {
                        if(board.get_board(j, 2-j) == 0)
                        {
                            Position p = new Position(j, 2-j);
                            Turn t = new Turn(p, this);
                            return t;
                        }
                    }
                }
                else
                {
                    for(int j = 0; j < 3; j ++)
                    {
                        if(board.get_board(j, j) == 0)
                        {
                            Position p = new Position(j, j);
                            Turn t = new Turn(p, this);
                            return t;
                        }
                    }
                }
            }
        }
        // on a pas mis de condition de ou pour que le joueur smart préfère gagner plutôt que d'éviter de perdre
        return super.play();       //dans le cas plus que probable où le joueur smart ne soit ni sur le point de gagner ou de perdre, il joue Stupid
       // return null;
    }
}
