import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * Class description
 *
 * @author Constanza Escobar
 */

public class LineFollower {

    public static void main(String [] args) {
        LightSensor light = new LightSensor(SensorPort.S2);
        light.setFloodlight(true);

        Button.ENTER.waitForPressAndRelease();
        int black = light.getNormalizedLightValue();
        Button.ENTER.waitForPressAndRelease();
        int white = light.getNormalizedLightValue();
        int threshold = (black + white)/2;

        DifferentialPilot pilot = new DifferentialPilot(3, 12, Motor.A, Motor.C);
        pilot.setTravelSpeed(50);
        pilot.setRotateSpeed(300);
        Behavior b1 = new WatchBlack(pilot);
        Behavior b2 = new WatchWhite(light,pilot,threshold);
        Behavior b3 = new HitWall(SensorPort.S1, pilot);
        Behavior b4 = new Stop(SensorPort.S3);
        Behavior [] bArray = {b1, b2, b3, b4};
        Arbitrator arby = new Arbitrator(bArray);

        Button.ENTER.waitForPressAndRelease();
        arby.start();
    }
}
