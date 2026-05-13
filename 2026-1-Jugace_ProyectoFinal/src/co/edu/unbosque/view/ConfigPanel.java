package co.edu.unbosque.view;
import javax.swing.*;

public class ConfigPanel {
	
	private static JPanel confPanel = new JPanel();
	private JComboBox selectdiff = new JComboBox();
	private String[] diff ={"Baja", "Media", "Alta"};
	private JSpinner spinFILAS = new JSpinner();
	private JSpinner spinCOLUMNAS = new JSpinner();
	private JSpinner spinPUERTOS = new JSpinner();
	
	private JButton botOK = new JButton();
	
	private JCheckBox chkINVERSO = new JCheckBox();
	
	public ConfigPanel() {
		
		selectdiff = new JComboBox(diff);
		spinFILAS = new JSpinner(new SpinnerNumberModel(10,5, 20, 1));
		spinCOLUMNAS = new JSpinner(new SpinnerNumberModel(10,5, 20, 1));
		spinPUERTOS = new JSpinner(new SpinnerNumberModel(3,2, 5, 1));
		
		botOK.setText("LISTO");
		
		chkINVERSO.setText("¿Orden Inverso?");
		
		confPanel.setLayout(new BoxLayout(confPanel, BoxLayout.Y_AXIS));
		
		confPanel.add(selectdiff);
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
