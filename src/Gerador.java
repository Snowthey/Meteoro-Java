import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Gerador {

    public int Tempo_GeradorRetangulos = 0;
    public ArrayList<Retangulo> vetor_retangulos = new ArrayList<Retangulo>();
    public ArrayList<Particula> vetor_particulas = new ArrayList<Particula>();

    public void update() throws InterruptedException{

        Tempo_GeradorRetangulos++;

        if(Tempo_GeradorRetangulos % 30 == 0){
            Retangulo objRetangulo = new Retangulo(0, new Random().nextInt(480 - 40), 40, 40);
            vetor_retangulos.add(objRetangulo);
        }

        for(int i = 0; i < vetor_retangulos.size(); i++){
            Retangulo current = vetor_retangulos.get(i);
            vetor_retangulos.get(i).update();

            if(current.x > Jogo.Largura){
                vetor_retangulos.remove(current);
                Jogo.Contador_Tempo--;
            }

            Jogo.Mutex.acquire();

            if(Jogo.MouseClicado){
                if(Jogo.mouse_x >= current.x && Jogo.mouse_x < current.x + current.width){
                    if(Jogo.mouse_y >= current.y && Jogo.mouse_y < current.y + current.height){
                        vetor_retangulos.remove(current);
                        Jogo.pontuacao++;
                        Jogo.MouseClicado = false;

                        for(int n = 0; n < 50; n++){
                            Particula objParticula = new Particula(current.x, current.y, 8, 8, current.cor);
                            vetor_particulas.add(objParticula);
                        }
                    }
                }
            }

            Jogo.Mutex.release();
        }

        for(int i = 0; i < vetor_particulas.size(); i++){
            vetor_particulas.get(i).update();
            Particula objParticula = vetor_particulas.get(i);

            if(objParticula.Contador_TempoParticula >= 30){
                vetor_particulas.remove(objParticula);
            }
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < vetor_retangulos.size(); i++){
            Retangulo objRetangulo = vetor_retangulos.get(i);
            Graphics2D g2 = (Graphics2D) g;
            g2.rotate(Math.toRadians(objRetangulo.rotation), objRetangulo.x + objRetangulo.width / 2, objRetangulo.y + objRetangulo.height / 2);
            g2.setColor(objRetangulo.cor);
            g2.fillRect(objRetangulo.x, objRetangulo.y, objRetangulo.width, objRetangulo.height);
            g2.rotate(-Math.toRadians(objRetangulo.rotation), objRetangulo.x + objRetangulo.width / 2, objRetangulo.y + objRetangulo.height / 2);
        }

        for(int i = 0; i < vetor_particulas.size(); i++){
            vetor_particulas.get(i).render(g);
        }
    }
}
