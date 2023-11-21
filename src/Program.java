package src;

import src.controller.AppController;
import src.view.AppView;

public class Program {

    public static void main(String[] args) {
        AppController.createDefaultUser();
        AppController.displayGUI(AppView.getInstance());
    }

}