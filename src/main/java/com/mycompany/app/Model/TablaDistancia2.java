package com.mycompany.app.Model;

public class TablaDistancia2 {
    Integer I;
    Float pi;
    Integer oi;
    Float ei;

    public TablaDistancia2() {}

    public TablaDistancia2(Integer i, Float pi, Integer oi, Float ei) {
        I = i;
        this.pi = pi;
        this.oi = oi;
        this.ei = ei;
    }
    public Integer getI() {
        return I;
    }
    public void setI(Integer i) {
        I = i;
    }
    public Float getPi() {
        return pi;
    }
    public void setPi(Float pi) {
        this.pi = pi;
    }
    public Integer getOi() {
        return oi;
    }
    public void setOi(Integer oi) {
        this.oi = oi;
    }
    public Float getEi() {
        return ei;
    }
    public void setEi(Float ei) {
        this.ei = ei;
    }
}
