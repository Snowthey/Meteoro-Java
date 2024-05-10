import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InterrupcaoMouse implements MouseListener{

    @Override
    public void mousePressed(MouseEvent e){
        try{
            Jogo.Mutex.acquire();
        } catch (InterruptedException e1){
            e1.printStackTrace();
        }

        Jogo.MouseClicado = true;
        Jogo.mouse_x = e.getX();
        Jogo.mouse_y = e.getY();

        Jogo.Mutex.release();
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }
}
