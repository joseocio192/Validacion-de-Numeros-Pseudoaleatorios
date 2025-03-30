package com.mycompany.app.Model;

public class RenglonDistancia {
    Integer n;
    float Ui;
    int c;
    int i;

    public RenglonDistancia(Integer n, float ui, int c, int i) {
        this.n = n;
        this.Ui = ui;
        this.c = c;
        this.i = i;
    }

    public RenglonDistancia() {
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public float getUi() {
        return Ui;
    }

    public void setUi(float ui) {
        Ui = ui;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    
}
