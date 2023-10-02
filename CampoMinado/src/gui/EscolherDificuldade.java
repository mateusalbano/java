package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Dimension;

public class EscolherDificuldade extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton btnFacil;
	protected JButton btnMedio;
	protected JButton btnDificil;
	protected JButton btnRetornar;
	protected JButton btnPersonalizado;

	/**
	 * Create the panel.
	 */
	public EscolherDificuldade() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnFacil = new JButton("Fácil");
		btnFacil.setPreferredSize(new Dimension(75, 23));
		btnFacil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnFacil = new GridBagConstraints();
		gbc_btnFacil.anchor = GridBagConstraints.SOUTH;
		gbc_btnFacil.insets = new Insets(0, 0, 5, 0);
		gbc_btnFacil.gridx = 0;
		gbc_btnFacil.gridy = 0;
		panel.add(btnFacil, gbc_btnFacil);
		
		btnMedio = new JButton("Médio");
		btnMedio.setPreferredSize(new Dimension(75, 23));
		btnMedio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnMedio = new GridBagConstraints();
		gbc_btnMedio.insets = new Insets(0, 0, 5, 0);
		gbc_btnMedio.gridx = 0;
		gbc_btnMedio.gridy = 1;
		panel.add(btnMedio, gbc_btnMedio);
		
		btnDificil = new JButton("Difícil");
		btnDificil.setPreferredSize(new Dimension(75, 23));
		btnDificil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnDificil = new GridBagConstraints();
		gbc_btnDificil.anchor = GridBagConstraints.NORTH;
		gbc_btnDificil.gridx = 0;
		gbc_btnDificil.gridy = 2;
		panel.add(btnDificil, gbc_btnDificil);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		btnRetornar = new JButton("<<< Retornar");
		btnRetornar.setPreferredSize(new Dimension(125, 23));
		btnRetornar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnRetornar = new GridBagConstraints();
		gbc_btnRetornar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRetornar.anchor = GridBagConstraints.WEST;
		gbc_btnRetornar.gridx = 0;
		gbc_btnRetornar.gridy = 0;
		panel_1.add(btnRetornar, gbc_btnRetornar);
		
		btnPersonalizado = new JButton("Personalizado");
		btnPersonalizado.setPreferredSize(new Dimension(125, 23));
		btnPersonalizado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnPersonalizado = new GridBagConstraints();
		gbc_btnPersonalizado.gridx = 1;
		gbc_btnPersonalizado.gridy = 0;
		panel_1.add(btnPersonalizado, gbc_btnPersonalizado);

	}

}
