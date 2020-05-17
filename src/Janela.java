import say.swing.JFontChooser;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.awt.Font;

public class Janela extends JFrame
{
  protected JButton btnPonto = new JButton ("Ponto"),
          btnLinha = new JButton ("Linha"),
          btnCirculo = new JButton ("Circulo"),
          btnElipse = new JButton ("Elipse"),
          btnQuadrado = new JButton("Quadrado"),
          btnRetangulo = new JButton("Retangulo"),
          btnPoligono = new JButton("Polígono"),
          btnTexto = new JButton("Texto"),
          btnCorContorno = new JButton ("Contorno"),
          btnCorPreenchimento = new JButton ("Preenchimento"),
          btnAbrir = new JButton ("Abrir"),
          btnSalvar = new JButton ("Salvar"),
          btnApagar = new JButton ("Apagar"),
          btnSair = new JButton ("Sair");

  protected MeuJPanel pnlDesenho = new MeuJPanel ();

  protected JLabel statusBar1 = new JLabel ("Mensagem:");
  protected JLabel statusBar2 = new JLabel ("Coordenada:");

  protected boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaCentro, esperaRaio,
          esperaInicioElipse, esperaFimElipse, esperaInicioQuadrado, esperaFimQuadrado, esperaInicioRetangulo,
          esperaFimRetangulo, esperaInicioPoligono, esperaFimPoligono;

  protected Color corContorno = Color.BLACK, corPreenchimento = new Color(0, 0, 0, 0);

  protected Ponto p1;
  protected Ponto inicioElipse;
  protected Ponto fimElipse;
  protected Ponto inicioQuadrado;
  protected Ponto fimQuadrado;
  protected Ponto inicioRetangulo;
  protected Ponto fimRetangulo;
  protected Ponto inicioPoligono;
  protected Ponto fimPoligono;


  protected Vector<Figura> figuras = new Vector<>();

  public Janela()
  {
    super("Editor Gráfico");
    try
    {
      Image btnPontoImg = ImageIO.read(this.getClass().getResource("resources/ponto.jpg"));
      btnPonto.setIcon(new ImageIcon(btnPontoImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
        null,
        "Arquivo ponto.jpg não foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
      btnLinha.setIcon(new ImageIcon(btnLinhaImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
        null,
        "Arquivo linha.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
      btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
        null,
        "Arquivo circulo.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
      btnElipse.setIcon(new ImageIcon(btnElipseImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
        null,
        "Arquivo elipse.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnQuadradoImg = ImageIO.read(getClass().getResource("resources/quadrado.jpg"));
      btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
              null,
              "Arquivo quadrado.jpg n�o foi encontrado",
              "Arquivo de imagem ausente",
              JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/retangulo.jpg"));
      btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
              null,
              "Arquivo retangulo.jpg n�o foi encontrado",
              "Arquivo de imagem ausente",
              JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnPoligonoImg = ImageIO.read(getClass().getResource("resources/poligono.jpg"));
      btnPoligono.setIcon(new ImageIcon(btnPoligonoImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
              null,
              "Arquivo poligono.jpg n�o foi encontrado",
              "Arquivo de imagem ausente",
              JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnTextoImg = ImageIO.read(getClass().getResource("resources/texto.jpg"));
      btnTexto.setIcon(new ImageIcon(btnTextoImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
              null,
              "Arquivo texto.jpg n�o foi encontrado",
              "Arquivo de imagem ausente",
              JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnCorImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
      btnCorContorno.setIcon(new ImageIcon(btnCorImg));
      btnCorPreenchimento.setIcon(new ImageIcon(btnCorImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
        null,
        "Arquivo cores.jpg não foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
      btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
        null,
        "Arquivo abrir.jpg n�o foi encontrado",
        "Arquivo de imagem ausente",
        JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
      btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
              null,
              "Arquivo salvar.jpg n�o foi encontrado",
              "Arquivo de imagem ausente",
              JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnApagarImg = ImageIO.read(getClass().getResource("resources/apagar.jpg"));
      btnApagar.setIcon(new ImageIcon(btnApagarImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
              null,
              "Arquivo apagar.jpg n�o foi encontrado",
              "Arquivo de imagem ausente",
              JOptionPane.WARNING_MESSAGE
      );
    }

    try
    {
      Image btnSairImg = ImageIO.read(getClass().getResource("resources/sair.jpg"));
      btnSair.setIcon(new ImageIcon(btnSairImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
              null,
              "Arquivo sair.jpg n�o foi encontrado",
              "Arquivo de imagem ausente",
              JOptionPane.WARNING_MESSAGE
      );
    }

    btnPonto.addActionListener(new DesenhoDePonto());
    btnLinha.addActionListener(new DesenhoDeReta());
    btnCirculo.addActionListener(new DesenhoDeCirculo());
    btnCorContorno.addActionListener(new EscolheCorContorno());
    btnCorPreenchimento.addActionListener(new EscolheCorPreenchimento());
    btnElipse.addActionListener(new DesenhoDeElipse());
    btnQuadrado.addActionListener(new DesenhoDeQuadrado());
    btnRetangulo.addActionListener(new DesenhoDeRetangulo());
    btnPoligono.addActionListener(new DesenhoDePoligono());
    btnTexto.addActionListener(new EscolheFonte());

    JPanel pnlBotoes = new JPanel();
    JPanel pnlBotoes2 = new JPanel();
    JPanel botoes = new JPanel();
    FlowLayout flwBotoes1 = new FlowLayout();
    FlowLayout flwBotoes2 = new FlowLayout();
    GridLayout grdBotoes = new GridLayout(2,1);
    pnlBotoes.setLayout(flwBotoes1);
    pnlBotoes2.setLayout(flwBotoes2);

    pnlBotoes.add(btnAbrir);
    pnlBotoes.add(btnSalvar);
    pnlBotoes.add(btnPonto);
    pnlBotoes.add(btnLinha);
    pnlBotoes.add(btnCirculo);
    pnlBotoes.add(btnElipse);
    pnlBotoes.add(btnQuadrado);
    pnlBotoes.add(btnRetangulo);
    pnlBotoes2.add(btnPoligono);
    pnlBotoes2.add(btnTexto);
    pnlBotoes2.add(btnCorContorno);
    pnlBotoes2.add(btnCorPreenchimento);
    pnlBotoes2.add(btnApagar);
    pnlBotoes2.add(btnSair);
    botoes.setLayout(grdBotoes);
    botoes.add(pnlBotoes);
    botoes.add(pnlBotoes2);

    JPanel pnlStatus = new JPanel();
    GridLayout grdStatus = new GridLayout(1,2);
    pnlStatus.setLayout(grdStatus);

    pnlStatus.add(statusBar1);
    pnlStatus.add(statusBar2);

    Container cntForm = this.getContentPane();
    cntForm.setLayout(new BorderLayout());
    cntForm.add(botoes, BorderLayout.NORTH);
    cntForm.add(pnlDesenho, BorderLayout.CENTER);
    cntForm.add(pnlStatus, BorderLayout.SOUTH);

    this.addWindowListener(new FechamentoDeJanela());

    this.setSize(900,600);
    this.setVisible(true);
  }

  protected class MeuJPanel extends JPanel implements MouseListener, MouseMotionListener
  {
    public MeuJPanel()
    {
      super();

      this.addMouseListener(this);
      this.addMouseMotionListener(this);
    }

    public void paint(Graphics g)
    {
      for (Figura figura : figuras) figura.torneSeVisivel(g);
    }

    public void mousePressed(MouseEvent e)
    {
      if (esperaPonto)
      {
        try
        {
          figuras.add (new Ponto (e.getX(), e.getY(), corContorno));
        }
        catch (Exception ex)
        {
          System.out.println(ex.getMessage());
        }
        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
        esperaPonto = true;
      }
      else if (esperaInicioReta)
      {
        try {
          p1 = new Ponto(e.getX(), e.getY(), corContorno);
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }

        esperaInicioReta = false;
        esperaFimReta = true;
        statusBar1.setText("Mensagem: clique o ponto final da reta");
      }
      else if (esperaFimReta)
      {
        esperaFimReta = false;
        esperaInicioReta = true;
        figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corContorno));
        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
        statusBar1.setText("Mensagem:");
      }
      else if (esperaCentro)
      {
        try {
          p1 = new Ponto (e.getX(), e.getY(), corContorno);
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        esperaCentro = false;
        esperaRaio = true;
        statusBar1.setText("Mensagem: clique a extremidade do circulo");
      }
      else if (esperaRaio)
      {
        esperaRaio = false;
        int altura = e.getY() - p1.getY();
        int largura = e.getX() - p1.getX();
        int raio = (int)Math.round(Math.sqrt((altura * altura) + (largura * largura)));
        try {
          figuras.add(new Circulo(p1.getX(), p1.getY(), raio, corContorno, corPreenchimento));
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
        statusBar1.setText("Mensagem: ");
        esperaCentro = true;
      }
      else if(esperaInicioElipse)
      {
        try {
          inicioElipse = new Ponto(e.getX(), e.getY(), corContorno);
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        esperaInicioElipse = false;
        esperaFimElipse = true;
        statusBar1.setText("Mensagem: Clique no ponto inferior direito da elipse");
      }
      else if(esperaFimElipse)
      {
        try {
          fimElipse = new Ponto (e.getX(), e.getY(), corContorno);
          esperaFimElipse = false;
          figuras.add(new Elipse(inicioElipse, fimElipse, corContorno, corPreenchimento));
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
        statusBar1.setText("Mensagem: ");
        esperaInicioElipse = true;
      }

      else if(esperaInicioQuadrado)
      {
        try
        {
          inicioQuadrado = new Ponto (e.getX(), e.getY(), corContorno);
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        esperaInicioQuadrado = false;
        esperaFimQuadrado = true;
        statusBar1.setText("Mensagem: Clique no ponto inferior direito do Quadrado ");
      }

      else if(esperaFimQuadrado)
      {
        try {
          fimQuadrado = new Ponto (e.getX(), e.getY(), corContorno);
          esperaFimQuadrado = false;
          int lado = Math.abs(inicioQuadrado.getX() - fimQuadrado.getX());
          figuras.add(new Quadrado(inicioQuadrado, fimQuadrado, lado, corContorno, corPreenchimento));
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
        statusBar1.setText("Mensagem: ");
        esperaInicioQuadrado = true;
      }

      else if(esperaInicioRetangulo)
      {
        try
        {
          inicioRetangulo = new Ponto (e.getX(), e.getY(), corContorno);
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        esperaInicioRetangulo = false;
        esperaFimRetangulo = true;
        statusBar1.setText("Mensagem: Clique no ponto inferior direito do retangulo ");
      }

      else if(esperaFimRetangulo)
      {
        try {
          fimRetangulo = new Ponto (e.getX(), e.getY(), corContorno);
          esperaFimRetangulo = false;
          int largura = Math.abs(inicioRetangulo.getX() - fimRetangulo.getX());
          int altura = Math.abs(inicioRetangulo.getY() - fimRetangulo.getY());
          figuras.add(new Retangulo(inicioRetangulo, fimRetangulo, largura, altura, corContorno, corPreenchimento));
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
        statusBar1.setText("Mensagem: ");
        esperaInicioRetangulo = true;
      }
    }

    public void mouseReleased (MouseEvent e)
    {}

    public void mouseClicked (MouseEvent e)
    {}

    public void mouseEntered (MouseEvent e)
    {}

    public void mouseExited (MouseEvent e)
    {}

    public void mouseDragged(MouseEvent e)
    {}

    public void mouseMoved(MouseEvent e)
    {
      statusBar2.setText("Coordenada: " + e.getX()+ "," + e.getY());
    }
  }

  protected class DesenhoDePonto implements ActionListener
  {
    public void actionPerformed (ActionEvent e)
    {
      esperaPonto = true;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;

      statusBar1.setText("Mensagem: clique o local do ponto desejado");
    }
  }

  protected class DesenhoDeReta implements ActionListener
  {
    public void actionPerformed (ActionEvent e)
    {
      esperaPonto = false;
      esperaInicioReta = true;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;

      statusBar1.setText("Mensagem: clique o ponto inicial da reta");
    }
  }

  protected class DesenhoDeCirculo implements ActionListener
  {
    public void actionPerformed (ActionEvent e)
    {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = true;
      esperaRaio = false;

      statusBar1.setText("Mensagem: clique o ponto central do círculo");
    }
  }

  protected class DesenhoDeElipse implements ActionListener
  {
    public void actionPerformed (ActionEvent e)
    {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = true;
      esperaFimElipse = false;

      statusBar1.setText("Mensagem: clique no canto superior esquerdo da elipse");
    }
  }

  protected class DesenhoDeQuadrado implements ActionListener
  {
    public void actionPerformed (ActionEvent e)
    {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = false;
      esperaFimElipse = false;
      esperaInicioQuadrado = true;
      esperaFimQuadrado = false;

      statusBar1.setText("Mensagem: clique no canto superior esquerdo do quadrado");
    }
  }

  protected class DesenhoDeRetangulo implements ActionListener
  {
    public void actionPerformed (ActionEvent e)
    {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = false;
      esperaFimElipse = false;
      esperaInicioQuadrado = false;
      esperaFimQuadrado = false;
      esperaInicioRetangulo = true;
      esperaFimRetangulo = false;

      statusBar1.setText("Mensagem: clique no canto superior esquerdo do retangulo");
    }
  }

  protected class DesenhoDePoligono implements ActionListener
  {
    public void actionPerformed (ActionEvent e)
    {
      esperaPonto = false;
      esperaInicioReta = false;
      esperaFimReta = false;
      esperaCentro = false;
      esperaRaio = false;
      esperaInicioElipse = false;
      esperaFimElipse = false;
      esperaInicioQuadrado = false;
      esperaFimQuadrado = false;
      esperaInicioRetangulo = false;
      esperaFimRetangulo = false;
      esperaInicioPoligono = true;
      esperaFimPoligono = false;

      statusBar1.setText("Mensagem: clique no canto superior esquerdo do retangulo");
    }
  }

  protected class EscolheCorContorno implements ActionListener
  {
    public void actionPerformed (ActionEvent e)
    {
      Color novaCorContorno = JColorChooser.showDialog(
        null,
        "Selecione a cor para o contorno",
        Color.BLACK
      );

      if (novaCorContorno != null)
      {
        corContorno = novaCorContorno;
      }
    }
  }

  protected class EscolheFonte implements ActionListener
  {
    public void actionPerformed(ActionEvent e) {
      JFontChooser fontChooser = new JFontChooser();
      int result;
      result = fontChooser.showDialog(null);
      if (result == JFontChooser.OK_OPTION) {
        Font font = fontChooser.getSelectedFont();
        System.out.println("Selecione Fonte : " + font);
      }
    }
  }
  protected class EscolheCorPreenchimento implements  ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      Color novaCorPreenchimento = JColorChooser.showDialog(
              null,
              "Selecione a cor para o contorno",
              Color.BLACK
      );

      if (novaCorPreenchimento != null)
      {
        corPreenchimento = novaCorPreenchimento;
      }
    }
  }

  protected class FechamentoDeJanela extends WindowAdapter
  {
    public void windowClosing (WindowEvent e)
    {
      System.exit(0);
    }
  }
}
