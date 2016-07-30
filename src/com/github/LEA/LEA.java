package com.github.LEA;

public class LEA {

    public static void main(String[] args){
        LoginView myLoginView = new LoginView();
        MainView myMainView = new MainView();
        Model myModel = new Model();
        Controller myController = new Controller(myLoginView, myMainView, myModel);

        myMainView.initMainComponents();
        myLoginView.initLoginComponents();
        myLoginView.setVisible(true);
    }
}