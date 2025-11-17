package MainApp;

import Login.LoginInicio;

public class MainApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginInicio().setVisible(true);
        });
    }
}
