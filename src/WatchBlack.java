import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Comportamiento ver negro, debiera ser el comportamiento por default, es decir, el de menor prioridad.
 * Se debe ejecutar cuando el robot vea la linea
 *
 * @author Constanza Escobar
 */

public class WatchBlack implements Behavior {
    private boolean suppressed = false;

    /**
     * Crea el comportamiento
     */
    public WatchBlack (){
        Motor.A.setSpeed(50);
    }

    /**
     * Retorna true pues es el comportamiento por default
     *
     * @return true
     */
    public boolean takeControl() {
        return true;
    }

    public void suppress() {
        suppressed = true;
    }

    /**
     * La acción del comportamiento
     * EL robot gira a la derecha en un arco
     */
    public void action() {
        suppressed = false;
<<<<<<< HEAD
        LCD.clear();
        LCD.drawString("Ver negro",0,0);
        Button.ENTER.waitForPressAndRelease();
        pilot.arcForward(-10);
=======
        Motor.A.forward();
>>>>>>> 08275e409f528e49b8a8843f12a2b54405ffa028

        while (!suppressed){
            Thread.yield();
        }

         Motor.A.stop();
    }
}
