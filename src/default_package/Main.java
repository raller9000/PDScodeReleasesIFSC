package default_package;

import javax.swing.SwingUtilities;
import controller.*;
import dao.*;
import model.*;
import view.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaLogin tela = new TelaLogin();
                tela.setVisible(true);
            }
        });
    }
}
