package gui2;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;

public class MenuPrincipal extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton btnIniciar;
	protected JButton btnSair;

	/**
	 * Create the panel.
	 */
	public MenuPrincipal() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setPreferredSize(new Dimension(75, 23));
		btnIniciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnIniciar = new GridBagConstraints();
		gbc_btnIniciar.anchor = GridBagConstraints.SOUTH;
		gbc_btnIniciar.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciar.gridx = 0;
		gbc_btnIniciar.gridy = 1;

		JLabel lblTitulo = new JLabel("Campo minado");
		lblTitulo.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		add(lblTitulo, gbc_lblTitulo);
		add(btnIniciar, gbc_btnIniciar);
		
		btnSair = new JButton("Sair");
		btnSair.setPreferredSize(new Dimension(75, 23));
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnSair = new GridBagConstraints();
		gbc_btnSair.anchor = GridBagConstraints.NORTH;
		gbc_btnSair.gridx = 0;
		gbc_btnSair.gridy = 2;
		add(btnSair, gbc_btnSair);
	}

}
