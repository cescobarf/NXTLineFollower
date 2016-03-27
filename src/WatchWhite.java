import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.*;

/**
 * Class description
 *
 * @author Constanza Escobar
 */

public class WatchWhite implements Behavior {
    private LightSensor light;
    private int white;
    private DifferentialPilot pilot;
    private boolean suppressed = false;

    public WatchWhite (LightSensor sensor, DifferentialPilot dfpilot, int color){
        light = sensor;
        white = color;
        pilot = dfpilot;
    }

    public boolean takeControl() {
        return light.getNormalizedLightValue() >= white;
    }

    public void suppress() {
        suppressed = true;
    }

    public void action() {
        suppressed = false;
        pilot.arcForward(10);

        while (light.getNormalizedLightValue() >= white && !suppressed){
            Thread.yield();
        }

        pilot.stop();
    }
}