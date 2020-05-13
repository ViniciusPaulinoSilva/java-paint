import java.awt.*;
import java.util.StringTokenizer;

public class Elipse extends Figura
{
  protected Ponto inicio; //inicio = ponto do canto superior esquerdo
  protected Ponto fim; // fim = ponto do canto inferior direito
  protected int largura;
  protected int altura;

  public Elipse(Ponto inicio, Ponto fim, int altura, int largura, Color corContorno, Color corPreenchimento)
  {
    this (inicio, fim, altura, largura, Color.BLACK);
  }

  public Elipse (Ponto inicio , Ponto fim, int altura, int largura, Color cor)
  {
    super(cor);

    this.fim = fim;
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

    Color corContorno = new Color (
      Integer.parseInt(quebrador.nextToken()),  // R
      Integer.parseInt(quebrador.nextToken()),  // G
      Integer.parseInt(quebrador.nextToken())   // B
    );

    try
    {
      this.inicio = new Ponto (iniciox, inicioy);
      this.fim = new Ponto (fimx, fimy);
      this.corContorno = corContorno;
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }

  }

  public void setInicio(Ponto inicio)
  {
    this.inicio = inicio;
  }
  public void setFim(Ponto fim)
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
    Ponto inicio = this.inicio;
    Ponto fim = this.fim;
    g.setColor(this.corContorno);
        this.largura = Math.abs(this.inicio.getX() - this.fim.getX());
        this.altura = Math.abs(this.inicio.getY() - this.fim.getY());
    g.drawOval(inicio.getX(), largura, inicio.getY() , altura);
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
            this.getCorContorno().getRed() +
            ":" +
            this.getCorContorno().getGreen() +
            ":" +
            this.getCorContorno().getBlue();
  }
}

