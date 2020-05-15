import java.awt.*;
import java.util.StringTokenizer;


public abstract class Poligono
{
    protected Ponto[] inicio;
    protected Ponto[] fim;
    protected int ponto;

    public Poligono(Ponto inicio[], Ponto fim[], int ponto) throws Exception

    {
        this( inicio[], fim[], ponto, Color.BLACK, new Color(253, 234, 234, 0));
    }

    public Poligono(Ponto inicio[], Ponto fim[], int ponto, Color corContorno) throws Exception

    {
        this(inicio[], fim[], ponto, Color.BLACK, new Color(0, 0, 0, 0));
    }

    public Poligono(Ponto inicio[], Ponto fim[], int ponto, Color corContorno, Color corPreenchimento) throws Exception
    {
        super(corContorno, corPreenchimento);

        this.fim = fim[];
        this.inicio = inicio[];
        this.ponto = ponto;
        this.corContorno = corContorno;
        this.corPreenchimento = corPreenchimento;
    }
}
