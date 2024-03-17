package gui2;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Insets;
import java.awt.Cursor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import java.awt.Dimension;

public class Personalizado extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton btnRetornar;
	protected JButton btnJogar;
	private JSpinner spinnerLinhas;
	private JSpinner spinnerColunas;
	private JSlider slider;
	
	protected int getLinhas() {
		return (int) spinnerLinhas.getValue();
	}
	
	protected int getColunas() {
		return (int) spinnerColunas.getValue();
	}
	
	protected int getBombRate() {
		return slider.getValue();
	}

	/**
	 * Create the panel.
	 */
	public Personalizado() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblLinhas = new JLabel("  Linhas:");
		GridBagConstraints gbc_lblLinhas = new GridBagConstraints();
		gbc_lblLinhas.anchor = GridBagConstraints.EAST;
		gbc_lblLinhas.insets = new Insets(0, 0, 0, 5);
		gbc_lblLinhas.gridx = 0;
		gbc_lblLinhas.gridy = 0;
		panel.add(lblLinhas, gbc_lblLinhas);
		
		spinnerLinhas = new JSpinner();
		spinnerLinhas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spinnerLinhas.setModel(new SpinnerNumberModel(7, 5, 30, 1));
		GridBagConstraints gbc_spinnerLinhas = new GridBagConstraints();
		gbc_spinnerLinhas.insets = new Insets(0, 0, 0, 5);
		gbc_spinnerLinhas.gridx = 1;
		gbc_spinnerLinhas.gridy = 0;
		panel.add(spinnerLinhas, gbc_spinnerLinhas);
		
		JLabel lblColunas = new JLabel("  Colunas:");
		GridBagConstraints gbc_lblColunas = new GridBagConstraints();
		gbc_lblColunas.anchor = GridBagConstraints.EAST;
		gbc_lblColunas.insets = new Insets(0, 0, 0, 5);
		gbc_lblColunas.gridx = 2;
		gbc_lblColunas.gridy = 0;
		panel.add(lblColunas, gbc_lblColunas);
		
		spinnerColunas = new JSpinner();
		spinnerColunas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spinnerColunas.setModel(new SpinnerNumberModel(6, 5, 30, 1));
		GridBagConstraints gbc_spinnerColunas = new GridBagConstraints();
		gbc_spinnerColunas.gridx = 3;
		gbc_spinnerColunas.gridy = 0;
		panel.add(spinnerColunas, gbc_spinnerColunas);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblChanceBomba = new JLabel("  Chance de bomba:(%)");
		GridBagConstraints gbc_lblChanceBomba = new GridBagConstraints();
		gbc_lblChanceBomba.insets = new Insets(0, 0, 0, 5);
		gbc_lblChanceBomba.gridx = 0;
		gbc_lblChanceBomba.gridy = 0;
		panel_1.add(lblChanceBomba, gbc_lblChanceBomba);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				slider.setToolTipText("" + slider.getValue());
				
			}
		});
		slider.setMinimum(5);
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(15);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 0;
		panel_1.add(slider, gbc_slider);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		btnRetornar = new JButton("<<< Retornar");
		btnRetornar.setPreferredSize(new Dimension(125, 23));
		btnRetornar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnRetornar = new GridBagConstraints();
		gbc_btnRetornar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRetornar.gridx = 0;
		gbc_btnRetornar.gridy = 0;
		panel_3.add(btnRetornar, gbc_btnRetornar);
		
		btnJogar = new JButton("Jogar");
		btnJogar.setPreferredSize(new Dimension(125, 23));
		GridBagConstraints gbc_btnJogar = new GridBagConstraints();
		gbc_btnJogar.anchor = GridBagConstraints.EAST;
		gbc_btnJogar.gridx = 1;
		gbc_btnJogar.gridy = 0;
		panel_3.add(btnJogar, gbc_btnJogar);
		btnJogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

	}

}
