package Homework;

import Homework.Interfaces.PlaceHandlerTemplate;
import Homework.Interfaces.PlaceTemplate;
import Homework.Interfaces.TransitionTemplate;

public class Supervisor_Transition3 implements TransitionTemplate {
    Integer              timeUnitControl = 500;
    Integer              eet;
    Integer              let;
    String               Name;
    PlaceHandlerTemplate PH;

    public Supervisor_Transition3(String name, PlaceHandlerTemplate PH, Integer delay) {
        this.Init(name, PH);
        this.SetDelay(delay);
    }

    public Supervisor_Transition3(String name, PlaceHandlerTemplate PH, Integer eet, Integer let) {
        this.Init(name, PH);
        this.SetDelayInRange(eet, let);
    }

    @Override
    public void TransitionDelay() {
        try {
            if (this.let == null) {
                Thread.sleep(this.eet * timeUnitControl);
            } else {
                Thread.sleep(Math.round(Math.random() * (this.let - this.eet) + this.eet) * timeUnitControl);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Boolean TransitionGuardsMappings() {
        TransitionDelay();
        String toPrint="--------------Supervisor--------------\n";

        if (!PH.GetPlaceByName("ps_2").IsNull()) {
            toPrint = toPrint.concat(Print() + "\n");
            toPrint = toPrint.concat("SupervisorPH\n" + PH.PrintAllPlaces() + "\n");

            PH.GetPlaceByName("ps_2").Set(
                    (Integer) PH.GetPlaceByName("ps_i2").Get()
            );
            PH.GetPlaceByName("ps_o2").Set(
                    (Integer) PH.GetPlaceByName("ps_i2").Get()
            );
            PH.GetPlaceByName("ps_o2").Set(null);

            toPrint = toPrint.concat(Print() + "\n");
            toPrint = toPrint.concat("--------------------------------------\n");

            System.out.println(toPrint);
            return true;
        }
        return false;
    }

    @Override
    public void Init(String name, PlaceHandlerTemplate PH) {
        this.PH = PH;
        this.Name = name;
    }

    @Override
    public void SetDelay(int value) {
        this.eet = value;
    }

    @Override
    public void SetDelayInRange(int eet, int let) {
        this.eet = eet;
        this.let = let;
    }

    @Override
    public String Print() {
        return this.Name + "\n" + this.PH.PrintAllPlaces();
    }
}
