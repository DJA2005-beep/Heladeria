package Utilidades;

import javax.swing.JOptionPane;

public class Validaciones {
    public static int veriEnteroPositivo(String input, String campo) {
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, campo + " no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        try {
            int num = Integer.parseInt(input.trim());
            if (num <= 0) {
                JOptionPane.showMessageDialog(null, campo + " debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
            return num;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, campo + " debe contener solo números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static double veriDoublePositivo(String input, String campo) {
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, campo + " no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1.0;
        }
        try {
            double num = Double.parseDouble(input.trim());
            if (num <= 0) {
                JOptionPane.showMessageDialog(null, campo + " debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return -1.0;
            }
            return num;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, campo + " debe contener un número válido (puede tener decimales).", "Error", JOptionPane.ERROR_MESSAGE);
            return -1.0;
        }
    }
    
}
