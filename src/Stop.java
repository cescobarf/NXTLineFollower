import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Comportamiento detenerse, es el comportamiento con más alta prioridad. Detiene al robot
 *
 * @author Constanza Escobar
 */
public class Stop implements Behavior {
    private TouchSensor touch;
    private boolean suppressed = false;

    /**
     * Crea el comportamiento
     *
     * @param port El puerto donde esta conectado el sensor de tacto
     */
    public Stop(SensorPort port){
        touch = new TouchSensor( port );
    }

    /**
     * Toma el control cuando el sensor es presionado
     *
     * @return true si el sensor esta presionado
     */
    public boolean takeControl() {
        return touch.isPressed();
    }

    public void suppress() {
        suppressed = true;
    }

    /**
     * Acción del comportamiento
     * Detiene al robot
     */
    public void action() {
        suppressed = false;
        System.exit(0);
    }
}
