import java.awt.*;
import java.util.StringTokenizer;

public class Elipse extends Figura
{
  protected Ponto inicio; //inicio = ponto do canto superior esquerdo
  protected Ponto fim; // fim = ponto do canto inferior direito
  protected int largura;
  protected int altura;

  public Elipse(Ponto inicio, Ponto fim, int altura, int largura) throws Exception
  {
    this (inicio, fim, altura, largura, Color.BLACK,  new Color(0, 0, 0, 0));
  }

  public Elipse(Ponto inicio, Ponto fim, int altura, int largura, Color corContorno) throws Exception
  {
    this (inicio, fim, altura, largura, Color.BLACK, new Color( 0,0,0,0));
  }

  public Elipse (Ponto inicio , Ponto fim, int altura, int largura, Color corContorno, Color corPreenchimento) throws Exception
  {
    super(corContorno, corPreenchimento);

    this.fim = fim;
    this.inicio = inicio;
    this.altura = altura;
    this.largura = largura;
    this.corContorno = corContorno;
    this.corPreenchimento = corPreenchimento;
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
    Color corPreenchimento = new Color (
            Integer.parseInt(quebrador.nextToken()),  // R
            Integer.parseInt(quebrador.nextToken()),  // G
            Integer.parseInt(quebrador.nextToken())   // B
    );

    try
    {
      this.inicio = new Ponto (iniciox, inicioy);
      this.fim = new Ponto (fimx, fimy);
      this.corContorno = corContorno;
      this.corPreenchimento = corPreenchimento;
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }

  }

  public void setInicio(int x, int y)
  {
    try
    {
      this.inicio = new Ponto (x,y,this.getCorContorno());
    }
    catch(Exception ex)
    {
      System.out.println(ex.getMessage());
    }
  }
  public void setFim(int x, int y)
  {
    try
    {
      this.fim = new Ponto (x,y,this.getCorContorno());
    }
    catch(Exception ex)
    {
      System.out.println(ex.getMessage());
    }
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
    g.setColor(this.corPreenchimento);
        this.largura = Math.abs(this.inicio.getX() - this.fim.getX());
        this.altura = Math.abs(this.inicio.getY() - this.fim.getY());
    g.fillOval(inicio.getX(), largura, inicio.getY() , altura);
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
            this.getCorContorno().getBlue() +
            ":" +
            this.getCorPreenchimento().getRed() +
            ":" +
            this.getCorPreenchimento().getGreen() +
            ":" +
            this.getCorPreenchimento().getBlue();
  }
}

