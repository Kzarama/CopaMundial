package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import excepciones.SeleccionYaExisteException;

public class PanelASeleccion extends JDialog implements ActionListener {
	
	public final static String IMAGEN = "IMAGEN", GUARDAR = "GUARDAR", ACEPTAR = "ACEPTAR";
	
	private JPanel panel1, panel2;
	private JLabel labPais, labPuntos, labAlturaPro, labEdadPro, labFIFAPro, labImagen;
	private JTextField txtPais, txtPuntos, txtAlturaPro, txtEdadPro, txtFIFAPro, txtImagen;
	private JButton btImagen, bt, btGuardar;
	private InterfazPrincipal i;
	private ImageIcon img;
	private JFileChooser fc;
	private File file;
	private boolean bool;
	
	public PanelASeleccion(InterfazPrincipal i, boolean bool) {
		super(i, true);
		this.i = i;
		this.bool = bool;
		
		setTitle("SELECCIONES COPA MUNDIAL DE LA FIFA");
		setSize(500, 300);
		
		panel1 = new JPanel(new GridLayout(1, 1));
		TitledBorder border = BorderFactory.createTitledBorder("PAIS DE LA SELECCION");
		border.setTitleColor(Color.BLUE);
		panel1.setBorder(border);
		
		panel2 = new JPanel(new GridLayout(7, 1));
		
		labPais = new JLabel("PAIS");
		labPuntos = new JLabel("PUNTOS");
		labAlturaPro = new JLabel("PROMEDIO ALTURA");
		labEdadPro = new JLabel("PROMEDIO EDAD");
		labFIFAPro = new JLabel("PROMEDIO FIFA");
		labImagen = new JLabel("IMAGEN");
		
		txtPais = new JTextField();
		txtPuntos = new JTextField();
		txtAlturaPro = new JTextField();
		txtAlturaPro.setEditable(false);
		txtEdadPro = new JTextField();
		txtEdadPro.setEditable(false);
		txtFIFAPro = new JTextField();
		txtFIFAPro.setEditable(false);
		txtImagen = new JTextField();
		txtImagen.setEditable(false);
		
		btImagen = new JButton();
		btImagen.setActionCommand(IMAGEN);
		btImagen.addActionListener(this);
		
		btGuardar = new JButton("GUARDAR");
		btGuardar.setActionCommand(GUARDAR);
		btGuardar.addActionListener(this);
		bt = new JButton(ACEPTAR);
		if(bool) {
			bt.setVisible(false);
			bt.setEnabled(false);
			img = new ImageIcon("img/ok.jpg");
		} else {
			btGuardar.setVisible(false);
			btGuardar.setEnabled(false);
			bt.setActionCommand(ACEPTAR);
			bt.addActionListener(this);
			txtPais.setText(i.getSeleccion().getPais());
			txtPuntos.setText(i.getSeleccion().getPuntos() + "");
			txtAlturaPro.setText(i.calcularPromedioAltura(i.getNombreSeleccion()) + "");
			txtEdadPro.setText(i.calcularPromedioEdad(i.getNombreSeleccion()) + "");
			txtFIFAPro.setText(i.calcularPromedioFIFA(i.getNombreSeleccion()) + "");
			txtImagen.setText(i.getSeleccion().getImagen());
			img = new ImageIcon(i.getSeleccion().getImagen());
		}
		btImagen.setIcon(img);
		setLayout(new GridLayout(1, 2));
		panel1.add(btImagen);
		panel2.add(labPais);
		panel2.add(txtPais);
		panel2.add(labPuntos);
		panel2.add(txtPuntos);
		panel2.add(labAlturaPro);
		panel2.add(txtAlturaPro);
		panel2.add(labEdadPro);
		panel2.add(txtEdadPro);
		panel2.add(labFIFAPro);
		panel2.add(txtFIFAPro);
		panel2.add(labImagen);
		panel2.add(txtImagen);
		panel2.add(bt);
		panel2.add(btGuardar);
		
		add(panel1);
		add(panel2);
	}
	
	public void cargarImagen() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("jpg", "png", "jpeg");
		fc = new JFileChooser();
		fc.setFileFilter(filtro);
		int a = fc.showOpenDialog(this);
		char back = 92;
		if(a == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		}
		String imag = file.getParent() + back + file.getName();
		setImagen(imag);
		img = new ImageIcon(getImagen());
		int w = img.getIconWidth();
		int h = img.getIconHeight();
		btImagen.setSize(w, h);
		btImagen.setIcon(img);
	}
	
	public String getPais() {
		return txtPais.getText();
	}
	
	public void setPais(String pais) {
		txtPais.setText(pais);
	}
	
	public int getPuntos() {
		return Integer.parseInt(txtPuntos.getText());
	}
	
	public void setPuntos(String puntos) {
		txtPuntos.setText(puntos);
	}
	
	public String getImagen() {
		return txtImagen.getText();
	}
	
	public void setImagen(String img) {
		txtImagen.setText(img);
	}
	
	public double getAlturaPro() {
		return Double.parseDouble(txtAlturaPro.getText());
	}
	
	public void setAlturaPro(String alturaPro) {
		txtAlturaPro.setText(alturaPro);
	}
	
	public double getEdadPro() {
		return Double.parseDouble(txtEdadPro.getText());
	}
	
	public void setEdadPro(String edadPro) {
		txtEdadPro.setText(edadPro);
	}
	
	public int getPuntosFIFA() {
		return Integer.parseInt(txtFIFAPro.getText());
	}
	
	public void setPuntosFIFA(String puntosFIFA) {
		txtFIFAPro.setText(puntosFIFA);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando == GUARDAR) {
			try {
				i.agregarSeleccion();
			} catch (SeleccionYaExisteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if (comando == ACEPTAR) {
			dispose();
		} else {
			cargarImagen();
		}
	}

}