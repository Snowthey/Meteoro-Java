import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;

public class Jogo extends Canvas implements Runnable{

    public static final int Largura = 640, Altura = 480;
    public static int Contador_Tempo = 100;
    public static int pontuacao = 0;
    public Gerador objetoGerador;
    public static int mouse_x, mouse_y;
    public static boolean MouseClicado = false;
    public boolean JogoAcabou = false;
    public static Semaphore Mutex;

    public Jogo(){
        Dimension dimension = new Dimension(Largura, Altura);
        this.setPreferredSize(dimension);
        this.addMouseListener(new InterrupcaoMouse());
        objetoGerador = new Gerador();
        Mutex = new Semaphore(1);
    }

    public void update() throws InterruptedException{
        if(JogoAcabou == false){
            objetoGerador.update();
            if(Contador_Tempo <= 0){
                JogoAcabou = true;
            }
        }
    }

    public void render(){

        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Largura, Altura);

        if(JogoAcabou == false){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 23));
            g.drawString("Pontos: " + pontuacao, 10, 40);
            g.setColor(Color.green);
            g.fillRect(Largura / 2 - 100 - 70, 20, Contador_Tempo * 3, 20);
            g.setColor(Color.white);
            g.drawRect(Largura / 2 - 100 - 70, 20, 300, 20);

            objetoGerador.render(g);
        } else {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", Largura / 2 - 100, Altura / 2);
        }

        bs.show();
    }

    @Override
    public void run(){
        while(true){
            try{
                update();
            } catch (InterruptedException e1){
                e1.printStackTrace();
            }

            render();

            try{
                Thread.sleep(16);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
