package com.twu.biblioteca;

abstract class library {

    private boolean checkedOut = false;

    public void checkOut(){
        checkedOut = true;
    }

    public void checkIn(){
        checkedOut = false;
    }

    public boolean isCheckedOut(){
        return checkedOut;
    }

}
