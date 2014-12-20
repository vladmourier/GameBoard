package fr.univ_lyon1.polytech.apo.gameboard;

/**
 *
 * @author YOU
 */
public abstract class Player {
    public final int number;
    

    public Player(int number) {
        this.number = number;
    }
    
    public abstract Turn play();
    public abstract Turn random_play(boolean b);
}
