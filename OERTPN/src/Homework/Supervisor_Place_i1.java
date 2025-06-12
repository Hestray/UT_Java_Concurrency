package Homework;

import Homework.Interfaces.PlaceTemplate;

public class Supervisor_Place_i1 implements PlaceTemplate {
    Supervisor_PlaceModel   p_i1;
    String                  Name;

    public Supervisor_Place_i1(String name, Object obj) {
        this.Init(name, obj);
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
        // will return r
        return this.p_i1.r;
    }

    public Object GetE() {
        return this.p_i1.e;
    }

    public Object GetL() {
        return this.p_i1.l;
    }

    @Override
    public Boolean IsNull() {
        return this.Get() == null;
    }

    @Override
    public void Set(Object r) {
        this.p_i1.r = (Integer) r;
    }

    public void SetE(Object e) {
        this.p_i1.e = (Integer) e;
    }

    public void SetL(Object l) {
        this.p_i1.l = (Integer) l;
    }

    @Override
    public String Print() {
        return this.p_i1.Print();
    }

    @Override
    public void Init(String name, Object value) {
        this.SetPlaceName(name);
        this.Set(value);
    }
}
