import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.*;

/**
 * Comportamiento chocar, es el tercer comportamiento con menos prioridad. Se ejecuta cuando el robot choca con
 * la pared
 *
 * @author Constanza Escobar
 */

public class HitWall implements Behavior {
    private TouchSensor touch;
    private DifferentialPilot pilot;
    private boolean suppressed = false;

    /**
     * Crea el comportamiento
     *
     * @param port El puerto donde esta conectado el sensor de tacto
     * @param dfpilot DifferentialPilot que controla al robot
     */
    public HitWall(SensorPort port, DifferentialPilot dfpilot) {
        touch = new TouchSensor( port );
        pilot = dfpilot;
    }

    /**
     * Toma el control cuando choca con la pared
     *
     * @return true si el sensor de tacto esta apretado
     */
    public boolean takeControl() {
        return touch.isPressed();
    }

    public void suppress() {
        suppressed = true;
    }

    /**
     * Acción del comportamiento
     * El robot gira en su propio eje, 180 grados
     */
    public void action() {
        suppressed = false;
        pilot.travel(-5);
        pilot.rotate(90, true);

        while( pilot.isMoving() && !suppressed )
            Thread.yield();

        pilot.stop();
    }
}