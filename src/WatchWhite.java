import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Comportamiento ver blanco, es el segundo comportamiento con menos prioridad. Debe ejecutarse cuando el robot no vea
 * la línea
 *
 * @author Constanza Escobar
 */

public class WatchWhite implements Behavior {
    private LightSensor light;
    private int white;
    private boolean suppressed = false;

    /**
     * Crea el comportamiento
     *
     * @param sensor El sensor de luz que se inició en Main
     * @param color Umbral de diferencia entre blanco y negro
     */
    public WatchWhite (LightSensor sensor, int color){
        light = sensor;
        white = color;
        Motor.C.setSpeed(50);
    }

    /**
     * Tomar el control si el robot ve blanco
     *
     * @return true si el robot ve blanco
     */
    public boolean takeControl() {
        return light.getNormalizedLightValue() >= white;
    }

    public void suppress() {
        suppressed = true;
    }

    /**
     * La acción del comportamiento
     * El robot gira a la izquierda en un arco
     */
    public void action() {
        suppressed = false;
        Motor.C.forward();

        while (light.getNormalizedLightValue() >= white && !suppressed){
            Thread.yield();
        }

        Motor.C.stop();
    }
}
