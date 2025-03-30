package com.mycompany.app.Model;

public class RenglonJiCuadrado {
    Double i;
    Integer o;
    Double e;
    Double ome;
    Double eso;

    public RenglonJiCuadrado() {
    }

    public RenglonJiCuadrado(Double i, Integer o, Double e, Double ome, Double eso) {
        this.i = i;
        this.o = o;
        this.e = e;
        this.ome = ome;
        this.eso = eso;
    }

    public Double getI() {
        return i;
    }
    public void setI(double i) {
        this.i = i;
    }
    public Integer getO() {
        return o;
    }
    public void setO(Integer o) {
        this.o = o;
    }
    public Double getE() {
        return e;
    }
    public void setE(Double e) {
        this.e = e;
    }
    public Double getOme() {
        return ome;
    }
    public void setOme(Double ome) {
        this.ome = ome;
    }
    public Double getEso() {
        return eso;
    }
    public void setEso(Double eso) {
        this.eso = eso;
    }
}
