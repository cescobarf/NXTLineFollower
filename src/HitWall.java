import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.util.Delay;

/**
 * Comportamiento chocar, es el tercer comportamiento con menos prioridad. Se ejecuta cuando el robot choca con
 * la pared
 *
 * @author Constanza Escobar
 */

public class HitWall implements Behavior {
    private TouchSensor touch;
    private boolean suppressed = false;

    /**
     * Crea el comportamiento
     *
     * @param port El puerto donde esta conectado el sensor de tacto
     */
    public HitWall(SensorPort port) {
        touch = new TouchSensor( port );
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
        Motor.A.forward();
        Motor.C.backward();

        Delay.msDelay(200);
        Motor.A.stop();
        Motor.C.stop();
    }
}
