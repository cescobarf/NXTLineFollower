import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * Clase principal del seguidor de linea, ejecuta al administrador de comportamientos
 *
 * @author Constanza Escobar
 */

public class LineFollower {

    public static void main(String [] args) {
        new LineFollower().go();
    }
    
    public void go() {
        LightSensor light = new LightSensor(SensorPort.S2);
        light.setFloodlight(true);

        //Calibración de colores blanco y negro
        LCD.drawString("Black",0,0);
        Button.ENTER.waitForPressAndRelease();
        int black = light.getNormalizedLightValue();
        LCD.drawString("White",0,1);
        Button.ENTER.waitForPressAndRelease();
        int white = light.getNormalizedLightValue();
        int threshold = (black + white)/2;

        //Comportamientos e inicialización
        DifferentialPilot pilot = new DifferentialPilot(3, 12, Motor.A, Motor.C);
        pilot.setTravelSpeed(300);
        pilot.setRotateSpeed(300);
        Behavior b1 = new WatchBlack(pilot);
        Behavior b2 = new WatchWhite(light,pilot,threshold);
        Behavior b3 = new HitWall(SensorPort.S1, pilot);
        Behavior b4 = new Stop(SensorPort.S3);
        Behavior [] bArray = {b1, b2, b3, b4};
        Arbitrator arby = new Arbitrator(bArray);

        LCD.drawString("Press for start",0,4);
        Button.ENTER.waitForPressAndRelease();
        LCD.clear();
        arby.start();
    }
}
