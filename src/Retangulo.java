import java.awt.*;
import java.rmi.server.ExportException;
import java.util.StringTokenizer;

public class Retangulo extends Figura {
    protected Ponto inicio; //inicio do retangulo, extremidade superior
    protected Ponto fim; //ponto base do retangulo, extremidade inferior
    protected int largura;
    protected int altura;

    public Retangulo(Ponto inicio, Ponto fim, int largura, int altura) throws Exception {
        this(inicio, fim, largura, altura, Color.BLACK, new Color(0, 0, 0, 0));
    }

    public Retangulo(Ponto inicio, Ponto fim, int largura, int altura, Color corContorno) throws Exception {
        this(inicio, fim, largura, altura, Color.BLACK, new Color(0, 0, 0, 0));
    }

    public Retangulo(Ponto inicio, Ponto fim, int largura, int altura, Color corContorno, Color corPreenchimento) throws Exception {
        super(corContorno, corPreenchimento);

        this.fim = fim;
        this.inicio = inicio;
        this.largura = largura;
        this.altura = altura;
        this.corContorno = corContorno;
        this.corPreenchimento = corPreenchimento;
    }

    public Retangulo(String s) throws Exception //"R:inicio.X:inicio.Y:fim.X:fim.Y:R:G:B"
    {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        String tipo = quebrador.nextToken();

        if (tipo != "R" || tipo != "r")
        {
            throw new Exception("String de construção de figura inválida");
        }

        quebrador.nextToken();

        int iniciox = Integer.parseInt(quebrador.nextToken());
        int inicioy = Integer.parseInt(quebrador.nextToken());
        int fimx = Integer.parseInt(quebrador.nextToken());
        int fimy = Integer.parseInt(quebrador.nextToken());

        Color corContorno = new Color(
                Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())   // B
        );
        Color corPreenchimento = new Color(
                Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())   // B
        );

        try {
            this.inicio = new Ponto(iniciox, inicioy);
            this.fim = new Ponto(fimx, fimy);
            this.corContorno = corContorno;
            this.corPreenchimento = corPreenchimento;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setInicio(int x, int y) {
        try {
            this.inicio = new Ponto(x, y, this.getCorContorno());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setFim(int x, int y) {
        try {
            this.fim = new Ponto(x, y, this.getCorContorno());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Ponto getInicio() {
        return this.inicio;
    }

    public Ponto getFim() {
        return this.fim;
    }

    public void torneSeVisivel(Graphics g) {
        Ponto inicio = this.inicio;
        Ponto fim = this.fim;
        g.setColor(this.corPreenchimento);
        this.largura = Math.abs(inicio.getX() - fim.getX());
        this.altura = Math.abs(inicio.getY() - fim.getY());
        g.fillRect(inicio.getX(), inicio.getY(), largura, altura);
        g.setColor(this.corContorno);
        g.drawRect(inicio.getX(), inicio.getY(), largura, altura);

    }

    public String toString() {
        return "r:" +
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

