package br.com.blockade_letrinhas.lgc_game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.blockade_letrinhas.lgc_base.BackgroundStandart;
import br.com.blockade_letrinhas.lgc_base.Painter;
import br.com.blockade_letrinhas.lgc_base.Updater;

public class Game extends JFrame{
	
	private static final int UPS = 1000 / 20;
	private static final int FPS = 1000 / 20;

	private static final int JANELA_ALTURA = 450;

	private static final int JANELA_LARGURA = 450;

	private JPanel tela;

	private Graphics2D g2d;

	private BufferedImage buffer;

	private BackgroundStandart background;

	
	public Game(){
		
		buffer = new BufferedImage(JANELA_LARGURA, JANELA_ALTURA, BufferedImage.TYPE_INT_RGB);
		
		g2d = buffer.createGraphics();
		
		tela = new JPanel(){
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(buffer, 0, 0, null);
			}
			
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(JANELA_LARGURA, JANELA_ALTURA);
			}

			@Override
			public Dimension getMinimumSize() {
				return getPreferredSize();
			}
		};
		
		getContentPane().add( tela );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		
		setVisible(true);
		tela.repaint();
	}
	
	private void load(){
		
	}


	public void startGame(){
		long prxUpdate = 0;
		long paintInterval = 1000 / FPS; // intervalo necessario para ter o FPS desejado.
		long updateInterval = 1000 / UPS; //intervalo necessario para ter a quantidade desejada de Updates por segundo.

		background = new BackgroundOfGame(tela.getWidth(), tela.getHeight());
		background.load();

		Painter painter = new Painter(background, g2d, tela, JANELA_LARGURA, JANELA_ALTURA);
		Updater updater = new Updater(background);

		painter.runAtRate(FPS);
		updater.runAtRate(UPS);
		
	}
	
	
	public static void main(String[] args) {
		Game jogo = new Game();
		jogo.startGame();
	}
}
