import java.awt.*;

public class Poligono extends Figura {
  protected int[] pontosX;
  protected int[] pontosY;
  protected int nPontos;
  protected Color corContorno;
  protected Color corPreenchimento;

  public Poligono (int[] pontosX, int[] pontosY, int nPontos) {
    this(pontosX, pontosY, nPontos, Color.BLACK, new Color(0, 0, 0, 0));
  }

  public Poligono (int[] pontosX, int[] pontosY, int nPontos, Color corContorno) {
    this(pontosX, pontosY, nPontos, corContorno, new Color(0, 0, 0, 0));
  }

  public Poligono (int[] pontosX, int[] pontosY, int nPontos, Color corContorno, Color corPreenchimento) {
    super(corContorno, corPreenchimento);

    this.pontosX = pontosX;
    this.pontosY = pontosY;
    this.nPontos = nPontos;
    this.corContorno = corContorno;
    this.corPreenchimento = corPreenchimento;
  }

  public void torneSeVisivel(Graphics g) {
    g.setColor(corContorno);
    g.drawPolygon(pontosX, pontosY, nPontos);
    g.setColor(corPreenchimento);
    g.fillPolygon(pontosX, pontosY, nPontos);
  }

  public String toString() {
    return "";
  }
}
