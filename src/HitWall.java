import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.*;

/**
 * Class description
 *
 * @author Constanza Escobar
 */

public class HitWall implements Behavior {
    private TouchSensor touch;
    private DifferentialPilot pilot;
    private boolean suppressed = false;

    public HitWall(SensorPort port, DifferentialPilot dfpilot) {
        touch = new TouchSensor( port );
        pilot = dfpilot;
    }

    public boolean takeControl() {
        return touch.isPressed();
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        suppressed = false;
        pilot.rotate(90, true);

        while( pilot.isMoving() && !suppressed )
            Thread.yield();

        pilot.stop();
    }
}