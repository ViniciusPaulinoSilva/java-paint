import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.awt.*;
import java.io.IOException;
import java.util.*;
 
public class Elipse extends Figura
{
    protected Ponto inicio; //inicio = ponto do canto superior esquerdo
    protected Ponto fim; // fim = ponto do canto inferior direito  
    protected int largura;
    protected int altura;
    
    public Elipse(Ponto inicio, int altura, int largura)
    {
        this (inicio, altura, largura, Color.BLACK);
    }
	
    public Elipse (Ponto inicio , int altura, int largura, Color cor)
    {
        super(cor);
        
        this.inicio = inicio;
        this.altura = altura;
        this.largura = largura;
    }

    public Elipse (String s)  // "E:Inicio.x:Inicio.y:Fim.x:Fim.Y::R:G:B"
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

		int	iniciox = Integer.parseInt(quebrador.nextToken());
        int inicioy = Integer.parseInt(quebrador.nextToken());
        int fimx = Integer.parseInt(quebrador.nextToken());
        int fimy = Integer.parseInt(quebrador.nextToken());
        
        Color cor = new Color (
            Integer.parseInt(quebrador.nextToken()),  // R
            Integer.parseInt(quebrador.nextToken()),  // G
            Integer.parseInt(quebrador.nextToken())   // B
        );
        try
        {
          this.inicio = new Ponto (iniciox, inicioy);
          this.fim = new Ponto (fimx, fimy);
          this.cor = cor;
        }
        catch (Exception e)
        {
          System.out.println(e.getMessage());
        }

    }
    
    public void setinicio(Ponto inicio)
    {
		this.inicio = inicio;
	}
	public void fim(Ponto fim)
    {
		this.fim = fim;
	}
    
    public Ponto getInicio()
    {
		return this.inicio;
	}
	
	public Ponto getFim()
    {
		return this.fim;
	}

    public void torneSeVisivel(Graphics g)
    {
        g.setColor(this.cor);
//        this.altura = Math.abs(this.inicio.getX() - this.fim.getX());
//        this.largura = Math.abs(this.inicio.getY() - this.fim.getY());
        g.drawOval(inicio.getX(), inicio.getY(), altura , largura);
    }

    public String toString()
    {
        return "e:" +
			   this.getInicio().getX() +
               ":" +
               this.getInicio().getY() + 
               ":" + 
               this.getFim().getX() +
               ":" + 
               this.getFim().getY() + 
               ":" + 
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
}

