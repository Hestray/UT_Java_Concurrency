package Homework;

import Homework.Interfaces.PlaceTemplate;

public class Supervisor_Place_Normal implements PlaceTemplate {
    Integer x;
    String  Name;

    public Supervisor_Place_Normal(Integer x, String Name) {
        this.Init(Name, x);
    }

    @Override
    public String GetPlaceName() {
        return this.Name;
    }

    @Override
    public void SetPlaceName(String name) {
        this.Name = name;
    }

    @Override
    public Object Get() {
        return this.x;
    }

    @Override
    public Boolean IsNull() {
        return this.Get() == null;
    }

    @Override
    public void Set(Object value) {

    }

    @Override
    public String Print() {
        return "[" + this.Name + "=" + this.x + "]";
    }

    @Override
    public void Init(String name, Object value) {
        this.SetPlaceName(name);
        this.Set(value);
    }
}
