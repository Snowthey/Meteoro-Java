import javax.swing.JFrame;

public class Principal extends JFrame {

    public static void main(String[] args){
        Jogo game = new Jogo();
        JFrame janelaprincipal = new JFrame("Explosão");

        janelaprincipal.add(game);
        janelaprincipal.pack();
        janelaprincipal.setLocationRelativeTo(null);
        janelaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaprincipal.setResizable(false);
        janelaprincipal.setVisible(true);

        new Thread(game).start();
    }
}
