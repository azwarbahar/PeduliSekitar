package com.example.coronavirus.model;

public class RecVieDataNegaraModel {

    private String Confirm;
    private String Recover;
    private String Death;
    private String updateLast;

    public RecVieDataNegaraModel(){

    }

    public String getConfirm() {
        return Confirm;
    }

    public void setConfirm(String confirm) {
        Confirm = confirm;
    }

    public String getRecover() {
        return Recover;
    }

    public void setRecover(String recover) {
        Recover = recover;
    }

    public String getDeath() {
        return Death;
    }

    public void setDeath(String death) {
        Death = death;
    }

    public String getUpdateLast() {
        return updateLast;
    }

    public void setUpdateLast(String updateLast) {
        this.updateLast = updateLast;
    }

    public RecVieDataNegaraModel(String confirm, String recover, String death, String updateLast) {
        Confirm = confirm;
        Recover = recover;
        Death = death;
        this.updateLast = updateLast;
    }
}
