package fr.univ_lyon1.polytech.apo.gameboard;

/**
 *
 * @author YOU
 */
public abstract class Player {
    public final int number;
    public int nb_coups;
    

    public Player(int number) {
        this.number = number;
    }
    
    public Player(int number, int nbcoups)
    {
        this.number=number;
        this.nb_coups=nbcoups;
    }
    
    public abstract Turn play();
    public abstract Turn random_play(boolean b);

    public abstract Turn stupid_play(Turn _history);
}
