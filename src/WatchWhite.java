import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
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
    private DifferentialPilot pilot;
    private boolean suppressed = false;

    /**
     * Crea el comportamiento
     *
     * @param sensor El sensor de luz que se inició en Main
     * @param dfpilot DifferentialPilot que controla al robot
     * @param color Umbral de diferencia entre blanco y negro
     */
    public WatchWhite (LightSensor sensor, DifferentialPilot dfpilot, int color){
        light = sensor;
        white = color;
        pilot = dfpilot;
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
        Button.ENTER.waitForPressAndRelease();
        pilot.arcForward(10);

        while (light.getNormalizedLightValue() >= white && !suppressed){
            Thread.yield();
        }

        pilot.stop();
    }
}