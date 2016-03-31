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
    private NXTRegulatedMotor m1 = Motor.C;

    /**
     * Crea el comportamiento
     */
    public WatchBlack (){
        m1.flt();
        m1.setAcceleration(5000);
        m1.setSpeed(500);
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

        LCD.clear();
        LCD.drawString("Ver negro",0,0);

        m1.forward();

        while (!suppressed){
            Thread.yield();
        }

        m1.stop();
    }
}
