package com.github.LEA;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class FileChooser {

    private JFileChooser fileChooser;
    private JButton btnOpen;
    private String currentDirectory = "C:/";

    public String chooseFile(){
        String absPath=""; // stores path of the file

        // instantiates the components
        btnOpen = new JButton(); // button that will serve as file opener/getter
        fileChooser = new JFileChooser(); // the file chooser

        // this is the current/default directory you'll be in when you open fileChooser
        fileChooser.setCurrentDirectory(new java.io.File(currentDirectory));
        fileChooser.setDialogTitle("Add File Attachment"); // sets frame title

        if(fileChooser.showOpenDialog(btnOpen) == JFileChooser.APPROVE_OPTION){
            absPath = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            absPath = null;
        }

        return absPath;
    }

}
