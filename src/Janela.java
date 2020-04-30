import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Janela extends JFrame
{
  protected JButton btnPonto = new JButton ("Ponto"),
          btnLinha = new JButton ("Linha"),
          btnCirculo = new JButton ("Circulo"),
          btnElipse = new JButton ("Elipse"),
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
          esperaInicioElipse, esperaFimElipse;
  protected Color corContorno = Color.BLACK, corPreenchimento = new Color(0, 0, 0, 0);;

  protected Ponto p1;
  protected Ponto inicioElipse;

  protected Vector<Figura> figuras = new Vector<Figura>();

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
      Image btnCorImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
      btnCorContorno.setIcon(new ImageIcon(btnCorImg));
      btnCorPreenchimento.setIcon(new ImageIcon(btnCorImg));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (
        null,
        "Arquivo cores.jpg n�o foi encontrado",
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

    JPanel pnlBotoes = new JPanel();
    FlowLayout flwBotoes = new FlowLayout();
    pnlBotoes.setLayout(flwBotoes);

    pnlBotoes.add(btnAbrir);
    pnlBotoes.add(btnSalvar);
    pnlBotoes.add(btnPonto);
    pnlBotoes.add(btnLinha);
    pnlBotoes.add(btnCirculo);
    pnlBotoes.add(btnElipse);
    pnlBotoes.add(btnCorContorno);
    pnlBotoes.add(btnCorPreenchimento);
    pnlBotoes.add(btnApagar);
    pnlBotoes.add(btnSair);

    JPanel pnlStatus = new JPanel();
    GridLayout grdStatus = new GridLayout(1,2);
    pnlStatus.setLayout(grdStatus);

    pnlStatus.add(statusBar1);
    pnlStatus.add(statusBar2);

    Container cntForm = this.getContentPane();
    cntForm.setLayout(new BorderLayout());
    cntForm.add(pnlBotoes, BorderLayout.NORTH);
    cntForm.add(pnlDesenho, BorderLayout.CENTER);
    cntForm.add(pnlStatus, BorderLayout.SOUTH);

    this.addWindowListener(new FechamentoDeJanela());

    this.setSize(1000,500);
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
        esperaPonto = false;
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
      }
      else if(esperaInicioElipse)
      {
        try {
          inicioElipse = new Ponto (e.getX(), e.getY(), corContorno);
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
          Ponto fim = new Ponto (e.getX(), e.getY(), corContorno);
          esperaFimElipse = false;
          int largura = Math.abs(inicioElipse.getX() - fim.getX());
          int altura = Math.abs(inicioElipse.getY() - fim.getY());
          figuras.add(new Elipse(inicioElipse,altura, largura));
        }
        catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
        statusBar1.setText("Mensagem: ");
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
