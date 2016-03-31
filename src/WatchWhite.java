import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.util.Delay;

/**
 * Comportamiento ver blanco, es el segundo comportamiento con menos prioridad. Debe ejecutarse cuando el robot no vea
 * la línea
 *
 * @author Constanza Escobar
 */

public class WatchWhite implements Behavior {
    private LightSensor light;
    private int white;
    private NXTRegulatedMotor m2 = Motor.B;
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
        m2.flt();
        m2.setAcceleration(5000);
        m2.setSpeed(500);
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
        LCD.clear();
        LCD.drawString("Ver blanco",0,0);

        m2.forward();

        while (light.getNormalizedLightValue() >= white && !suppressed){
            Thread.yield();
        }

        m2.stop();
    }
}
