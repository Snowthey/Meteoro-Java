import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class Retangulo extends Rectangle{

    public Color cor;
    public int velocidade = 0;
    public int rotation = 0;

    public Retangulo(int x, int y, int largura, int altura){
        super(x, y, largura, altura);
        int R = new Random().nextInt(255);
        int G = new Random().nextInt(255);
        int B = new Random().nextInt(255);
        cor = new Color(R, G, B);
        velocidade = new Random().nextInt(8) + 4;

    }

    public void update(){
        x += velocidade;
        rotation++;

        if(rotation >= 360){
            rotation = 0;
        }
    }


}
