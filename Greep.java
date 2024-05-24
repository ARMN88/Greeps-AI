import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.util.List;

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author (your name here)
 * @version 0.1
 */
public class Greep extends Creature
{
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
    
    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
    }

    
    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }
    

    /**
     * Do what a greep's gotta do.
     */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act().
        
        Matrix input = new Matrix(new double[][]{
            {
                Boolean.compare(carryingTomato(), false),
                Boolean.compare(atShip(), false),
                Boolean.compare(atWorldEdge(), false),
                Boolean.compare(atWater(), false),
                Boolean.compare(getFlag(1), false),
                Boolean.compare(getFlag(2), false)
            }
        });
        Matrix output = Earth.brains[Earth.brain].apply(input);
        
        if(output.getNum(0, 0) > 0) {
            dropTomato();
        }
        if(output.getNum(0, 1) > 0) {
            turnHome();
        }
        turn((int)(output.getNum(0, 2) * 180 + 180));
        if(output.getNum(0, 3) > 0) {
            setFlag(1, true);
        } else {
            setFlag(1, false);
        }
        if(output.getNum(0, 4) > 0) {
            setFlag(2, true);
        } else {
            setFlag(2, false);
        }
        
        move();
        checkFood();
        
        /*if (carryingTomato()) {
            if(atShip()) {
                dropTomato();
                setMemory(0);
            }
            else {
                int rand = (int)(Math.random() * 1000) + 1;
                if(rand == 1) {
                    setFlag(2, true);
                }
                turnHome();
                if(atWorldEdge() || atWater()) {
                    if(getFlag(1)) {
                        turn(((int)(Math.random() * 90) + 90));
                    } else {
                        turn(((int)(Math.random() * -90) - 90));
                    }
                    
                }
                move();
                if(getFlag(2)) {
                    setFlag(1, !getFlag(1));
                    setFlag(2, false);
                }
            }
        }
        else {
            if (atWorldEdge() || atWater()) {
                 turn((int)(Math.random() * 90) + 90);
                 //turn(90);
            }
            move();
            checkFood();
        }*/
    }

    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if(tomatoes != null) {
            turnTowards(tomatoes.getX(), tomatoes.getY());
            loadTomato();
        }
    }


    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Generation " + (Earth.brain + 1);  // write your name here!
    }
    public int getDistance(Ship ship) {
        int distanceX = ship.getX() - getX();
        int distanceY = ship.getY() - getY();
        return (int) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }


    /**
     * This method specifies the image we want displayed at any time. (No need 
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if(carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }
}