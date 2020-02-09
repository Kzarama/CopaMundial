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

import excepciones.JugadorYaExisteException;

public class PanelAJugador extends JDialog implements ActionListener {
	
	public final static String IMAGEN = "IMAGEN", GUARDAR = "GUARDAR", ACEPTAR = "ACEPTAR";
	
	private JPanel panel1, panel2;
	private JLabel labImagen, labNombre, labPosicion, labFIFAPtj, labFechaNac, labAltura;
	private JTextField txtImagen, txtNombre, txtPosicion, txtFIFAPtj, txtFechaNac, txtAltura;
	private JButton btImagen, bt, btGuardar;
	private InterfazPrincipal i;
	private ImageIcon img;
	private JFileChooser fc;
	private File file;
	private boolean bolo;
	
	public PanelAJugador(InterfazPrincipal i, boolean bolo) {
		super(i, true);
		this.i = i;
		this.bolo = bolo;
		
		setTitle("SELECCIONES COPA MUNDIAL DE LA FIFA");
		setSize(500, 300);
		
		panel1 = new JPanel(new GridLayout(1, 1));
		TitledBorder border = BorderFactory.createTitledBorder("JUGADOR DE LA SELECCION");
		border.setTitleColor(Color.BLUE);
		panel1.setBorder(border);
		
		panel2 = new JPanel(new GridLayout(7, 1));
		
		labImagen = new JLabel("IMAGEN");
		labNombre = new JLabel("NOMBRE");
		labPosicion = new JLabel("POSICION");
		labFIFAPtj = new JLabel("PUNTAJE FIFA");
		labFechaNac = new JLabel("FECHA NACIMIENTO");
		labAltura = new JLabel("ALTURA");
		
		txtImagen = new JTextField();
		txtImagen.setEditable(false);
		txtNombre = new JTextField();
		txtPosicion = new JTextField();
		txtFIFAPtj = new JTextField();
		txtFechaNac = new JTextField();
		txtAltura = new JTextField();
		
		btImagen = new JButton();
		btImagen.setActionCommand(IMAGEN);
		btImagen.addActionListener(this);
		btGuardar = new JButton("GUARDAR");
		btGuardar.setActionCommand(GUARDAR);
		btGuardar.addActionListener(this);
		bt = new JButton(ACEPTAR);
		if(bolo) {
			bt.setVisible(false);
			bt.setEnabled(false);
			img = new ImageIcon("img/ok.jpg");
		} else {
			btGuardar.setVisible(false);
			btGuardar.setEnabled(false);
			bt.setActionCommand(ACEPTAR);
			bt.addActionListener(this);
			txtImagen.setText(i.getJugador().getImagen());
			txtNombre.setText(i.getJugador().getNombre());
			txtPosicion.setText(i.getJugador().getPosicion());
			txtFIFAPtj.setText(i.getJugador().getPuntajeFIFA() + "");
			txtFechaNac.setText(i.getJugador().getFechaNacimiento());
			txtAltura.setText(i.getJugador().getAltura() + "");
			img = new ImageIcon(i.getJugador().getImagen());
		}
		btImagen.setIcon(img);
		setLayout(new GridLayout(1, 2));
		panel1.add(btImagen);
		panel2.add(labImagen);
		panel2.add(txtImagen);
		panel2.add(labNombre);
		panel2.add(txtNombre);
		panel2.add(labPosicion);
		panel2.add(txtPosicion);
		panel2.add(labFIFAPtj);
		panel2.add(txtFIFAPtj);
		panel2.add(labFechaNac);
		panel2.add(txtFechaNac);
		panel2.add(labAltura);
		panel2.add(txtAltura);
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
		setImagen(file.getParent() + back + file.getName());
		img = new ImageIcon(getImagen());
		int w = img.getIconWidth();
		int h = img.getIconHeight();
		btImagen.setSize(w, h);
		btImagen.setIcon(img);
	}
	
	public String getNombre() {
		return txtNombre.getText();
	}
	
	public void setNombre(String nom) {
		txtNombre.setText(nom);
	}
	
	public String getImagen() {
		return txtImagen.getText();
	}
	
	public void setImagen(String img) {
		txtImagen.setText(img);
	}
	
	public String getPosicion() {
		return txtPosicion.getText();
	}
	
	public void setPosicion(String pos) {
		txtPosicion.setText(pos);
	}
	
	public int getFIFAPtj() {
		return Integer.parseInt(txtFIFAPtj.getText());
	}
	
	public void setFIFAPtj(String fifa) {
		txtFIFAPtj.setText(fifa);
	}
	
	public String getFechaNac() {
		return txtFechaNac.getText();
	}
	
	public void setFechaNac(String fec) {
		txtFechaNac.setText(fec);
	}
	
	public double getAltura() {
		return Double.parseDouble(txtAltura.getText());
	}
	
	public void setAltura(String alt) {
		txtAltura.setText(alt);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando == GUARDAR) {
			try {
				i.agregarJugador();
			} catch (JugadorYaExisteException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if(comando == ACEPTAR) {
			dispose();
		} else {
			cargarImagen();
		}
	}

}