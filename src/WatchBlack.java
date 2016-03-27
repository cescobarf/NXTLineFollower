import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.*;

/**
 * Class description
 *
 * @author Constanza Escobar
 */

public class WatchBlack implements Behavior {
    private DifferentialPilot pilot;
    private boolean suppressed = false;

    public WatchBlack (DifferentialPilot dfpilot){
        pilot = dfpilot;
    }

    public boolean takeControl() {
        return true;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        suppressed = false;
        pilot.arcForward(-10);

        while (!suppressed){
            Thread.yield();
        }

        pilot.stop();
    }
}
