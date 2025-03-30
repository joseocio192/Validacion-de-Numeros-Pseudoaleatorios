package com.mycompany.app.Model;

public class RenglonDistancia2 {
    Integer I;
    Float pi;
    Integer oi;
    Float ei;
    Float eimoi;
    Float eimoicei;
    
    public RenglonDistancia2() {
    }
    
    public RenglonDistancia2(Integer i, Float pi, Integer oi, Float ei, Float eimoi, Float eimoicei) {
        this.I = i;
        this.pi = pi;
        this.oi = oi;
        this.ei = ei;
        this.eimoi = eimoi;
        this.eimoicei = eimoicei;
    }

    
    
    @Override
    public String toString() {
        return "RenglonDistancia2 [I=" + I + ", pi=" + pi + ", oi=" + oi + ", ei=" + ei + ", eimoi=" + eimoi
                + ", eimoicei=" + eimoicei + "]";
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
    public Float getEimoi() {
        return eimoi;
    }
    public void setEimoi(Float eimoi) {
        this.eimoi = eimoi;
    }
    public Float getEimoicei() {
        return eimoicei;
    }
    public void setEimoicei(Float eimoicei) {
        this.eimoicei = eimoicei;
    }

    
}
