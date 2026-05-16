package co.edu.unbosque.view;
import java.awt.FlowLayout;

import javax.swing.*;

public class ConfigPanel {
	
	private static JPanel confPanel = new JPanel(); //Panel y mucha cosa rara
	
	private JComboBox selectdiff = new JComboBox(); //Densidad de enemigos
	private String[] diff ={"Baja", "Media", "Alta", "CULOTE MASIVO"}; //Lista de dificultad / Densidad de enemgios
	
	private JSpinner spinFILAS = new JSpinner(); //Cokocar Filas
	private JSpinner spinCOLUMNAS = new JSpinner(); //Cokocar Columnas
	private JSpinner spinPUERTOS = new JSpinner(); //Cokocar Puertos
	
	private JCheckBox chkINVERSO = new JCheckBox(); //Check de Juego Inverso
	
	private JButton botOK = new JButton(); //Boton Final
	
	
	
	public ConfigPanel() {
		
		selectdiff = new JComboBox(diff);  //Creacion de Densidad de emeg
		
		spinFILAS = new JSpinner(new SpinnerNumberModel(10,5, 20, 1));   //PREDETERMINADO-MIN-MAX-INCREMENTO
		spinCOLUMNAS = new JSpinner(new SpinnerNumberModel(10,5, 20, 1)); //Spin de las Filas, Columnas y puertos...
		spinPUERTOS = new JSpinner(new SpinnerNumberModel(3,2, 5, 1));
		
		botOK.setText("LISTO"); //Boton
		
		chkINVERSO.setText("¿Orden Inverso?");
		
		confPanel.setLayout(new FlowLayout());
		
		confPanel.add(selectdiff); //Adicion todo al paneñ
		confPanel.add(spinFILAS);
		confPanel.add(spinCOLUMNAS);
		confPanel.add(spinPUERTOS);
		confPanel.add(chkINVERSO);
		confPanel.add(botOK);
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
	
	

}
