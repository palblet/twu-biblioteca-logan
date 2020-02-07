package com.twu.biblioteca;

class libraryObject{

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
