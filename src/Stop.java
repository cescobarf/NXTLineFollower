import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * Class description
 *
 * @author Constanza Escobar
 */
public class Stop implements Behavior {
    private TouchSensor touch;
    private boolean suppressed = false;

    public Stop(SensorPort port){
        touch = new TouchSensor( port );
    }

    public boolean takeControl() {
        return touch.isPressed();
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        suppressed = false;
        System.exit(0);
    }
}
