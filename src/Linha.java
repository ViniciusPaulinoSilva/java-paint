import java.awt.*;
import java.util.*;
 
public class Linha extends Figura
{
    protected Ponto p1, p2;
    
    public Linha (int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK);
    }
	
    public Linha (int x1, int y1, int x2, int y2, Color cor)
    {
        super(cor);
		
		try
		{
			this.p1 = new Ponto (x1,y1,cor);
			this.p2 = new Ponto (x2,y2,cor);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
    }

    public Linha (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
		
		try
		{
			this.p1 = new Ponto (x1,y1,cor);
			this.p2 = new Ponto (x2,y2,cor);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
        this.cor = cor;
    }

    public void setP1 (int x, int y)
    {
		try
		{
			this.p1 = new Ponto (x,y,this.getCor());
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
    }

    public void setP2 (int x, int y)
    {
		try
		{
			this.p2 = new Ponto (x,y,this.getCor());
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
    }

    public Ponto getP1 ()
    {
        return this.p1;
    }

    public Ponto getP2 ()
    {
        return this.p2;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
        g.drawLine(this.p1.getX(), this.p1.getY(),   // ponto inicial
                   this.p2.getX(), this.p2.getY());  // ponto final
    }

    public String toString()
    {
        return "r:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
}
