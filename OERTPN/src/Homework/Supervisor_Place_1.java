package Homework;

import Homework.Interfaces.PlaceHandlerTemplate;
import Homework.Interfaces.PlaceTemplate;

import java.util.ArrayList;

public class Supervisor_Place_1 implements PlaceHandlerTemplate {
    ArrayList<PlaceTemplate> List_P_1;

    public Supervisor_Place_1() {
        this.List_P_1 = new ArrayList<PlaceTemplate>();
    }

    @Override
    public void AddPlace(PlaceTemplate place) {
        this.List_P_1.add(place);
    }

    @Override
    public PlaceTemplate GetPlaceByName(String Name) {
        for (PlaceTemplate p : this.List_P_1) {
            if(p.GetPlaceName().equals(Name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String PrintAllPlaces() {
        String output = "";
        for (PlaceTemplate p : this.List_P_1) {
            output = output.concat(p.Print() + " ");
        }

        return output;
    }

    public Boolean isListEmpty() {
        return this.List_P_1.isEmpty();
    }

    public ArrayList<PlaceTemplate> getList_P_1() {
        return this.List_P_1;
    }
}
