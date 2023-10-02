package gui;

import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import backend.Snake;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

public class Jogo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows;
	private int columns;
	private int snakeRow;
	private int snakeColumn;
	private Snake snake;
	private Color backgroundColor;
	private Color snakeColor;
	private Color pointColor;
	private JPanel[][] panels;
	private Timer timer;
	private char tecla;

	
	public void tecla() {
		switch (tecla) {
		case 'w':
			snake.jogar(Snake.UP);
			break;
		case 'a':
			snake.jogar(Snake.LEFT);
			break;
		case 's':
			snake.jogar(Snake.DOWN);
			break;
		case 'd':
			snake.jogar(Snake.RIGHT);
			break;
		case 'r':
			if (snake.getMoves() > 0) {
				snake.reiniciar(snakeRow, snakeColumn);
				timer.start();
			}
			break;
		}
	}
	
	private void setColor(JPanel panel, int value) {
		if (value == Snake.EMPTY_SPACE) {
			panel.setBackground(backgroundColor);
		} else if (value == Snake.POINT_SPACE) {
			panel.setBackground(pointColor);
		} else {
			panel.setBackground(snakeColor);
		}
	}
	
	private void mapa() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				JPanel panel = new JPanel();
				panels[i][j] = panel;
				panel.setPreferredSize(new Dimension(25, 25));
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = j;
				gbc_panel.gridy = i;
				add(panel, gbc_panel);
			}
		}
	}
	
	public void update() {
		int[][] mapa = snake.getMapa();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				setColor(panels[i][j], mapa[i][j]);
			}
		}
	}

	/**
	 * Create the panel.
	 */
	public Jogo(int rows, int columns, int snakeRow, int snakeColumn) {
		backgroundColor = Color.BLACK;
		snakeColor = Color.GREEN;
		pointColor = Color.RED;
		panels = new JPanel[rows][columns];
		this.rows = rows;
		this.columns = columns;
		this.snakeRow = snakeRow;
		this.snakeColumn = snakeColumn;
		setBackground(backgroundColor);
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		snake = new Snake(rows, columns, snakeRow, snakeColumn);
		mapa();
		timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				tecla();
			}
		});
		timer.start();
		
		InputMap inputMap = getInputMap(JComponent.WHEN_FOCUSED);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0),'w');
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0),'a');
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0),'s');
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0),'d');
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0),'r');
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),10);
		
		ActionMap actionMap = getActionMap();
		setInputMap(JComponent.WHEN_FOCUSED, inputMap);
		actionMap.put('w', new AbstractAction(){
		    private static final long serialVersionUID = 1L;
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	if (!timer.isRunning()) return;
		        tecla = 'w';
		    }
		});
		actionMap.put('a', new AbstractAction(){
		    private static final long serialVersionUID = 1L;
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	if (!timer.isRunning()) return;
		        tecla = 'a';
		    }
		});
		actionMap.put('s', new AbstractAction(){
		    private static final long serialVersionUID = 1L;
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	if (!timer.isRunning()) return;
		        tecla = 's';
		    }
		});
		
		actionMap.put('d', new AbstractAction(){
		    private static final long serialVersionUID = 1L;
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	if (!timer.isRunning()) return;
		        tecla = 'd';
		    }
		});
		
		actionMap.put('r', new AbstractAction(){
		    private static final long serialVersionUID = 1L;
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	if (!timer.isRunning()) return;
		        tecla = 'r';
		    }
		});
		
		actionMap.put(10, new AbstractAction(){
		    private static final long serialVersionUID = 1L;
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	if(timer.isRunning() && snake.getMoves() > 0 && !snake.isGameOver()) {
		    		timer.stop();
		    	} else {
		    		timer.start();
		    	}
		    }
		});
	}
}
