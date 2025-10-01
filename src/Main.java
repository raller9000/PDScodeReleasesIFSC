import javax.swing.SwingUtilities;
import telasFinal.TelaLogin;

package src;

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
