package Homework;

import java.util.Scanner;

public class Supervisor extends Thread {
    boolean stop    = false;
    PlaceHandler PH = new PlaceHandler();

    public Controller    c;

    Supervisor_Transition1 ts_1;
    Supervisor_Transition2 ts_2;
    Supervisor_Transition3 ts_3;

    Scanner scanner = new Scanner(System.in);

    public void run() {

        PH.AddPlace(new Supervisor_Place_Normal(0, "ps_2"));
        PH.AddPlace(new Supervisor_Place_Normal(0, "ps_3"));
        PH.AddPlace(new Supervisor_Place_Normal(null, "ps_o1"));
        PH.AddPlace(new Supervisor_Place_Normal(null, "ps_i2"));
        PH.AddPlace(new Supervisor_Place_Normal(null, "ps_o2"));

        ts_1 = new Supervisor_Transition1("ts_1", PH, 0);
        ts_2 = new Supervisor_Transition2("ts_2", PH, 0);
        ts_3 = new Supervisor_Transition3("ts_3", PH, 0);

        ts_2.ControllerPH = c.PH;

        System.out.println("Supervisor: Input r, e, id values:");
        int r = Integer.parseInt(scanner.next());
        int e = Integer.parseInt(scanner.next());
        int id = Integer.parseInt(scanner.next());

        Supervisor_PlaceModel input = new Supervisor_PlaceModel(r, e, id);
        PH.GetPlaceByName("ps_i1").Set(input);

        while (!stop) {
            ts_1.TransitionGuardsMappings();
            ts_2.TransitionGuardsMappings();
            ts_3.TransitionGuardsMappings();

            // For slower printing on the console
            try {
                Thread.sleep(1000);
            } catch (InterruptedException err) {
                // TODO Auto-generated catch block
                err.printStackTrace();
            }
        }

    }

    public void StopThread() {
        this.stop = true;
    }
}
