package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import backend.CampoMinado;
import javax.swing.ImageIcon;

public class Jogo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Timer timer;
	private int minutos;
	private int segundos;
	private int x;
	private int y;
	private int size;
	private boolean colocaBandeira;
	private JLabel lblTempo;
	private JPanel panel;
	private JPanel panelCampo;
	private CampoMinado campoMinado;
	private JButton[][] buttons;
	private JLabel[][] labels;
	private boolean[][] bandeiras;
	private ImageIcon bandeiraIcon;
	private ImageIcon bombaIcon;
	private ImageIcon rostoDerrotaIcon;
	private ImageIcon rostoNormalIcon;
	private ImageIcon rostoVitoriaIcon;
	private JButton btnReiniciar;
	private JButton btnBandeira;
	private Dimension dimension;

	/**
	 * Launch the application.
	 */
	
	private void reiniciar() {
		minutos = 0;
		segundos = 0;
		lblTempo.setText("00:00");
		campoMinado.reiniciar();
		bandeiras = new boolean[x][y];
		btnReiniciar.setIcon(rostoNormalIcon);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				labels[i][j].setVisible(false);
				buttons[i][j].setIcon(null);
			}
		}
		updateButtons();
		timer.start();
	}
	
	private void updateLabels() {
		int[][] campo = campoMinado.getCampo();
		Font tahoma = new Font("Tahoma", Font.PLAIN, 15);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				JLabel label = labels[i][j];
				int celula = campo[i][j];
				if (celula != -1) {
					label.setIcon(null);
					label.setText("" + celula);
					label.setFont(tahoma);
				} else {
					label.setIcon(bombaIcon);
					label.setText("");
				}
			}
		}
	}
	
	private void updateButtons() {
		boolean[][] casaAberta = campoMinado.getCasaAberta();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (casaAberta[i][j]) {
					buttons[i][j].setVisible(false);
					labels[i][j].setVisible(true);
				} else {
					buttons[i][j].setVisible(true);
				}
			}
		}
	}
	
	private int resultado() {
		if(campoMinado.isDerrota()) {
			 btnReiniciar.setIcon(rostoDerrotaIcon);
			 campoMinado.clear();
			 return 0;
		 } else if (campoMinado.isVitoria()) {
			 btnReiniciar.setIcon(rostoVitoriaIcon);
			 return 1;
		 }
		return 2;
	}
	
	private void mostraResultado(int resultado) {
		if (resultado == 0) {
			 JOptionPane.showMessageDialog(contentPane, "Derrota", "FIM", JOptionPane.INFORMATION_MESSAGE);
		 } else if (resultado == 1) {
			 JOptionPane.showMessageDialog(contentPane, "Vitória", "FIM", JOptionPane.INFORMATION_MESSAGE);
		 }
	}
	
	private void addButtonsLabels() {
		Cursor hand_cursor = new Cursor(Cursor.HAND_CURSOR);
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				JButton button = new JButton();
				buttons[i][j] = button;
				button.setPreferredSize(dimension);
				button.setCursor(hand_cursor);
				button.setBorder(bevelBorder);
				int posI = i;
				int posJ = j;
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean bandeira = bandeiras[posI][posJ];
						if (colocaBandeira) {
							bandeiras[posI][posJ] = !bandeira;
							if (!bandeira) {
								button.setIcon(bandeiraIcon);
							} else {
								button.setIcon(null);
							}
						} else if (!bandeira) {
							if (campoMinado.jogar(posI, posJ)) {		
								if (campoMinado.getJogadas() == 1) {
									updateLabels();
								}
								int resultado = resultado();
								updateButtons();
								mostraResultado(resultado);
							}
						}
						
					}
				});
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.gridx = i;
				gbc_button.gridy = j;
				panelCampo.add(button, gbc_button);
				JLabel label = new JLabel("" + 0);
				labels[i][j] = label;
				label.setVisible(false);
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.gridx = i;
				gbc_label.gridy = j;
				panelCampo.add(label, gbc_label);
			}
		}
		pack();
		setBounds(getX(), getY(), getWidth() + 50, getHeight() + 25);
	}
	
	private String timeToText(int min, int sec) {
		String minStr;
		String secStr;
		if (min < 10) {
			minStr = "0" + min;
		} else {
			minStr = "" + min;
		}
		
		if (sec < 10) {
			secStr = "0" + sec;
		} else {
			secStr = "" + sec;
		}
			return minStr + ':' + secStr;
	}

	/**
	 * Create the frame.
	 */
	public Jogo(String titulo, int x, int y, double bombRate) {
		this.x = x;
		this.y = y;
		size = 25 + (100 / (x + y));

		campoMinado = new CampoMinado(x, y, bombRate);
		buttons = new JButton[x][y];
		labels = new JLabel[x][y];
		bandeiras = new boolean[x][y];
		dimension = new Dimension(size, size);
		bandeiraIcon = new ImageIcon(((new ImageIcon(Jogo.class.getResource("/imagens/bandeira.png"))).getImage()).getScaledInstance(size - 5, size - 5, 50));
		bombaIcon = new ImageIcon(((new ImageIcon(Jogo.class.getResource("/imagens/bomba.png"))).getImage()).getScaledInstance(size - 5, size - 5, 50));
		rostoDerrotaIcon = new ImageIcon(((new ImageIcon(Jogo.class.getResource("/imagens/rostoderrota.png"))).getImage()).getScaledInstance(size - 5, size - 5, 50));
		rostoNormalIcon = new ImageIcon(((new ImageIcon(Jogo.class.getResource("/imagens/rostonormal.png"))).getImage()).getScaledInstance(size - 5, size - 5, 50));
		rostoVitoriaIcon = new ImageIcon(((new ImageIcon(Jogo.class.getResource("/imagens/rostovitória.png"))).getImage()).getScaledInstance(size - 5, size - 5, 50));
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (campoMinado.isDerrota() || campoMinado.isVitoria()) {
					timer.stop();
					return;
				}
				segundos++;
				if (segundos == 60) {
					segundos = 0;
					minutos++;
				}
				if (minutos == 100) {
					minutos = 0;
				}
				
				lblTempo.setText(timeToText(minutos, segundos));
				
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.gameOn = false;
			}
		});
		
		timer.start();
		
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(600, 100);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{50, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{75, 75, 75, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblTempo = new JLabel("00:00");
		lblTempo.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblTempo.setForeground(Color.RED);
		lblTempo.setPreferredSize(new Dimension(64, 20));
		GridBagConstraints gbc_lblTempo = new GridBagConstraints();
		gbc_lblTempo.insets = new Insets(0, 0, 0, 5);
		gbc_lblTempo.gridx = 0;
		gbc_lblTempo.gridy = 0;
		panel.add(lblTempo, gbc_lblTempo);
		lblTempo.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		
		btnReiniciar = new JButton();
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			}
		});
		btnReiniciar.setIcon(rostoNormalIcon);
		btnReiniciar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnReiniciar.setPreferredSize(dimension);
		GridBagConstraints gbc_btnReiniciar = new GridBagConstraints();
		gbc_btnReiniciar.insets = new Insets(0, 0, 0, 5);
		gbc_btnReiniciar.gridx = 1;
		gbc_btnReiniciar.gridy = 0;
		panel.add(btnReiniciar, gbc_btnReiniciar);
		btnReiniciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnBandeira = new JButton();
		btnBandeira.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				colocaBandeira = !colocaBandeira;
				if (colocaBandeira) {
					btnBandeira.setIcon(bandeiraIcon);
				} else {
					btnBandeira.setIcon(bombaIcon);
				}
			}
		});
		btnBandeira.setIcon(bombaIcon);
		btnBandeira.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBandeira.setPreferredSize(dimension);
		GridBagConstraints gbc_btnBandeira = new GridBagConstraints();
		gbc_btnBandeira.gridx = 2;
		gbc_btnBandeira.gridy = 0;
		panel.add(btnBandeira, gbc_btnBandeira);
		btnBandeira.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panelCampo = new JPanel();
		GridBagLayout gbl_panelCampo = new GridBagLayout();
		int[] columns = new int[x];
		int[] rows = new int[y];
		for (int i = 0; i < x; i++) {
			columns[i] = size;
		}
		for (int i = 0; i < y; i++) {
			rows[i] = size;
		}
		gbl_panelCampo.columnWidths = columns;
		gbl_panelCampo.rowHeights = rows;
		panelCampo.setLayout(gbl_panelCampo);
		GridBagConstraints gbc_panelCampo = new GridBagConstraints();
		gbc_panelCampo.gridx = 0;
		gbc_panelCampo.gridy = 1;
		contentPane.add(panelCampo, gbc_panelCampo);
		addButtonsLabels();
	}
}