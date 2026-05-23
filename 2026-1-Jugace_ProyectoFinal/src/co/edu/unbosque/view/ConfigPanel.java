package co.edu.unbosque.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.*;

public class ConfigPanel {
	
	private static JPanel confPanel = new JPanel(); //Panel y mucha cosa rara
	
	private JComboBox selectdiff = new JComboBox(); //Densidad de enemigos
	private String[] diff ={"FACIL", "NORMAL", "MODERADO", "DIFICIL", "EXPERTO"}; //Lista de dificultad / Densidad de enemgios
	
	private JSpinner spinFILAS = new JSpinner(); //Cokocar Filas
	private JSpinner spinCOLUMNAS = new JSpinner(); //Cokocar Columnas
	private JSpinner spinPUERTOS = new JSpinner(); //Cokocar Puertos
	
	private JCheckBox chkINVERSO = new JCheckBox(); //Check de Juego Inverso
	
	private JButton botOK = new JButton(); //Boton Final
	private JButton botRTN = new JButton();
	
	private JLabel lblDiff = new JLabel();
	private JLabel lblFila = new JLabel();
	private JLabel lblCol = new JLabel();
	private JLabel lblPrt = new JLabel();
	
	public ConfigPanel() {
		
		selectdiff = new JComboBox(diff);  //Creacion de Densidad de emeg
		selectdiff.setFont(PonerFont.cargar(Font.BOLD, 20));
		
		spinFILAS = new JSpinner(new SpinnerNumberModel(5,5, 20, 1));   //PREDETERMINADO-MIN-MAX-INCREMENTO
		spinFILAS.setFont(PonerFont.cargar(Font.BOLD, 20));
		
		spinCOLUMNAS = new JSpinner(new SpinnerNumberModel(10,5, 20, 1)); //Spin de las Filas, Columnas y puertos...
		spinCOLUMNAS.setFont(PonerFont.cargar(Font.BOLD, 20));
		
		spinPUERTOS = new JSpinner(new SpinnerNumberModel(3,2, 5, 1));
		spinPUERTOS.setFont(PonerFont.cargar(Font.BOLD, 20));
		
		ImageIcon imgB = new ImageIcon("src/co/edu/unbosque/images/botonok.png"); // Cambia por el nombre real de tu archivo
	    botOK.setIcon(imgB);  
		botOK.setText("");
		botOK.setOpaque(false);
		botOK.setBorderPainted(false);
		botOK.setContentAreaFilled(false);
		
		ImageIcon imgV = new ImageIcon("src/co/edu/unbosque/images/btn_volver.png"); // Cambia por el nombre real de tu archivo
	    botRTN.setIcon(imgV);  
	    botRTN.setText("");
	    botRTN.setOpaque(false);
	    botRTN.setBorderPainted(false);
		botRTN.setContentAreaFilled(false);
		
		chkINVERSO.setText("¿Orden Inverso?");
		chkINVERSO.setFont(PonerFont.cargar(Font.PLAIN, 20));
		chkINVERSO.setForeground(Color.WHITE);
		chkINVERSO.setContentAreaFilled(false);
		
		lblDiff.setText("DIFICULTAD");
		lblDiff.setForeground(new Color(255,0,70));
		lblDiff.setFont(PonerFont.cargar(Font.BOLD, 18));
	
		lblFila.setText("FILAS");
		lblFila.setForeground(Color.CYAN);
		lblFila.setFont(PonerFont.cargar(Font.BOLD, 18));
		
		lblCol.setText("COLUMNAS");
		lblCol.setForeground(Color.CYAN);
		lblCol.setFont(PonerFont.cargar(Font.BOLD, 18));
		
		lblPrt.setText("PUERTOS");
		lblPrt.setForeground(Color.BLUE);
		lblPrt.setFont(PonerFont.cargar(Font.BOLD, 18));
		
		confPanel.setLayout(new GridBagLayout());
		confPanel.setBackground(new Color(15, 0, 50));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;          
		gbc.insets = new Insets(5, 5, 5, 5); 
		gbc.fill = GridBagConstraints.HORIZONTAL;

		ImageIcon icon = new ImageIcon("src/co/edu/unbosque/images/logo_con.png");
		JLabel lbl_logo = new JLabel(icon);

		confPanel.removeAll();
		
		
		confPanel.add(lbl_logo, gbc);
		confPanel.add(lblDiff, gbc); 
		confPanel.add(selectdiff, gbc); 
		
		confPanel.add(lblFila, gbc); 
		confPanel.add(spinFILAS, gbc);
		confPanel.add(lblCol, gbc); 
		confPanel.add(spinCOLUMNAS, gbc);
		confPanel.add(lblPrt, gbc); 
		confPanel.add(spinPUERTOS, gbc);
		confPanel.add(chkINVERSO, gbc);
		confPanel.add(botOK, gbc);
		confPanel.add(botRTN, gbc);
	}
	
	public static JPanel getPanel() {
		return confPanel;
	}

	public static JPanel getConfPanel() {
		return confPanel;
	}

	public static void setConfPanel(JPanel confPanel) {
		ConfigPanel.confPanel = confPanel;
	}

	public JComboBox getSelectdiff() {
		return selectdiff;
	}

	public void setSelectdiff(JComboBox selectdiff) {
		this.selectdiff = selectdiff;
	}

	public String[] getDiff() {
		return diff;
	}

	public void setDiff(String[] diff) {
		this.diff = diff;
	}

	public JSpinner getSpinFILAS() {
		return spinFILAS;
	}

	public void setSpinFILAS(JSpinner spinFILAS) {
		this.spinFILAS = spinFILAS;
	}

	public JSpinner getSpinCOLUMNAS() {
		return spinCOLUMNAS;
	}

	public void setSpinCOLUMNAS(JSpinner spinCOLUMNAS) {
		this.spinCOLUMNAS = spinCOLUMNAS;
	}

	public JSpinner getSpinPUERTOS() {
		return spinPUERTOS;
	}

	public void setSpinPUERTOS(JSpinner spinPUERTOS) {
		this.spinPUERTOS = spinPUERTOS;
	}

	public JButton getBotOK() {
		return botOK;
	}

	public void setBotOK(JButton botOK) {
		this.botOK = botOK;
	}

	public JCheckBox getChkINVERSO() {
		return chkINVERSO;
	}

	public void setChkINVERSO(JCheckBox chkINVERSO) {
		this.chkINVERSO = chkINVERSO;
	}

	public JButton getBotRTN() {
		return botRTN;
	}

	public void setBotRTN(JButton botRTN) {
		this.botRTN = botRTN;
	}
	
	
	
	

}
