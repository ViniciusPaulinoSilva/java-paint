import java.awt.*;
import java.util.*;
 
public class Circulo extends Figura
{
    protected Ponto centro;
    protected int raio;
    
    public Circulo (int x, int y, int raio) throws Exception
    {
		this (x, y, raio, Color.BLACK);
    }
	
    public Circulo (int x, int y, int raio, Color cor) throws Exception
    {
        super(cor);

        if (raio <= 0)
        {
            throw new Exception("Raio deve ser maior que zero");
        }

        this.centro = new Ponto (x,y,cor);        
        this.raio = raio;
    }

    public Circulo (String s) throws Exception  // "C:x:y:raio:R:G:B"
    {
        StringTokenizer quebrador = new StringTokenizer(s, ":");
        
        String tipo = quebrador.nextToken();
        
        if (tipo != "C" || tipo != "c")
        {
            throw new Exception("String de construção de figura inválida");
        }

        int   x  = Integer.parseInt(quebrador.nextToken());
        int   y  = Integer.parseInt(quebrador.nextToken());

        int   raio  = Integer.parseInt(quebrador.nextToken());

        if (raio <= 0)
        {
            throw new Exception("A medida do raio deve ser positiva");
        }

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.centro  = new Ponto (x,y,cor);
        this.raio = raio;
        this.cor = cor;
    }

    public void setCentro (int x, int y)
    {
		try
		{
			this.centro = new Ponto (x,y,this.getCor());
		}
        catch(Exception ex)
        {
			System.out.println(ex);
		}
    }

    public Ponto getCentro()
    {
        return this.centro;
    }
    
    public void setRaio(int raio) throws Exception
    {
        
        if (raio <= 0)
        {
            throw new Exception("A medida do raio deve ser maior que zero");
        }

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
