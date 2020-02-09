package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import excepciones.CampoVacioException;
import excepciones.SeleccionYaExisteException;


public class DialogoAgregarSeleccion extends JDialog implements ActionListener{

	public final static String GUARDAR = "Guardar";
	
	private InterfazCopaMundialFutbol principal;
	private PanelImagenSeleccion panelImagenSeleccion;
	private boolean presionado = false;
	
	private JLabel labPais, labPuntos, labPromedioAltura, labPromedioEdad, labPromedioFIFA, labImagen;
	private JTextField txtPais, txtPuntos, txtPromedioAltura, txtPromedioEdad, txtPromedioFIFA, txtImagen;
	private JButton butGuardar;
	
	public DialogoAgregarSeleccion(InterfazCopaMundialFutbol ventana) {
		super(ventana, true);
		this.principal = ventana;
		setTitle("Selecciones Copa Mundial de la FIFA");
		setLayout(new GridLayout(1, 2));
		panelImagenSeleccion = new PanelImagenSeleccion(this);
		
		JPanel aux1 = new JPanel();
		aux1.add(panelImagenSeleccion);
		
		JPanel aux2 = new JPanel();
		aux2.setLayout(new GridLayout(7, 2));
		labPais = new JLabel("País");
		txtPais = new JTextField();
		labPuntos = new JLabel("Puntos");
		txtPuntos = new JTextField();
		labPromedioAltura = new JLabel("Promedio altura");
		txtPromedioAltura = new JTextField();
		txtPromedioAltura.setEditable(false);
		labPromedioEdad = new JLabel("Promedio edad");
		txtPromedioEdad = new JTextField();
		txtPromedioEdad.setEditable(false);
		labPromedioFIFA = new JLabel("Promedio FIFA");
		txtPromedioFIFA = new JTextField();
		txtPromedioFIFA.setEditable(false);
		labImagen = new JLabel("Imagen");
		txtImagen = new JTextField();
		txtImagen.setEditable(false);
		butGuardar = new JButton(GUARDAR);
		butGuardar.addActionListener(this);
		butGuardar.setActionCommand(GUARDAR);
		aux2.add(labPais); aux2.add(txtPais); aux2.add(labPuntos); aux2.add(txtPuntos); aux2.add(labPromedioAltura); aux2.add(txtPromedioAltura);
		aux2.add(labPromedioEdad); aux2.add(txtPromedioEdad); aux2.add(labPromedioFIFA); aux2.add(txtPromedioFIFA); aux2.add(labImagen); aux2.add(txtImagen);
		aux2.add(new JLabel()); aux2.add(butGuardar);
		
		add(aux1);
		add(aux2);
		pack();
	}
	
	public String darPais() throws CampoVacioException{
		String m = txtPais.getText();
		if(m.equals("")) {
			throw new CampoVacioException();
		}
		return m;
	}
	
	public int darPuntos(){
		return Integer.parseInt(txtPuntos.getText());
	}
	
	public String darImagen() throws CampoVacioException {
		String m = txtImagen.getText();
		if(m.equals("")) {
			throw new CampoVacioException();
		}
		return m;
	}
	
	public boolean darPresionado() {
		return presionado;
	}
	
	public String darRutaArchivo() {
		return panelImagenSeleccion.darRutaImagen();
	}
	
	public void refrescarImagen(String imagen) {
		txtImagen.setText(imagen);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(GUARDAR)) {
			try {
				principal.agregarSeleccion();
				presionado = true;
			}catch(SeleccionYaExisteException s) {
				JOptionPane.showMessageDialog(null, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}

