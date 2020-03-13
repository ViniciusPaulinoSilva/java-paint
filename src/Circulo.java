import java.awt.*;
import java.util.*;
 
public class Circulo extends Figura
{
    protected Ponto centro;
    protected int raio;
    
    public Circulo (int x, int y, int raio)
    {
        this (x, y, raio, Color.BLACK);
    }
	
    public Circulo (int x, int y, int raio, Color cor)
    {
        super(cor);

        this.centro = new Ponto (x,y,cor);
        this.raio = raio;
    }

    public Circulo (String s)  // "C:x:y:raio:R:G:B"
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x  = Integer.parseInt(quebrador.nextToken());
        int   y  = Integer.parseInt(quebrador.nextToken());

        int   raio  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.centro  = new Ponto (x,y,cor);
        this.raio = raio;
        this.cor = cor;
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor());
    }

    public Ponto getCentro()
    {
        return this.centro;
    }
    
    public void setRaio(int raio)
    {
		this.raio = raio;
	}
    
    public int getRaio()
    {
		return this.raio;
	}

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
        int x = this.centro.getX() - this.raio;
        int y = this.centro.getY() - this.raio;
        g.drawOval(x, y, (2 * this.raio), (2 * this.raio));
    }

    public String toString()
    {
        return "c:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
}
