package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import excepciones.CampoVacioException;

public class DialogoAgregarJugador extends JDialog implements ActionListener{

	public final static String GUARDAR = "Guardar";
	
	private InterfazCopaMundialFutbol principal;
	private PanelImagenJugador panelImagenJugador;
	
	private JLabel labNombre, labPosicion, labPuntajeFIFA, labFechaNac, labAltura, labImagen;
	private JTextField txtNombre, txtPosicion, txtPuntajeFIFA, txtFechaNac, txtAltura, txtImagen;
	private JButton butGuardar;
	
	public DialogoAgregarJugador(InterfazCopaMundialFutbol ventana) {
		super(ventana, true);
		this.principal = ventana;
		setTitle("Jugadores Copa Mundial de la FIFA");
		setLayout(new GridLayout(1, 2));
		panelImagenJugador = new PanelImagenJugador(this);
		
		JPanel aux1 = new JPanel();
		aux1.add(panelImagenJugador);
		
		JPanel aux2 = new JPanel();
		aux2.setLayout(new GridLayout(7, 2));
		labNombre = new JLabel("Nombre");
		txtNombre = new JTextField();
		labPosicion = new JLabel("Posición");
		txtPosicion = new JTextField();
		labPuntajeFIFA = new JLabel("Puntaje FIFA");
		txtPuntajeFIFA = new JTextField();
		labFechaNac = new JLabel("Fecha Nacimiento (DD/MM/YYYY)");
		txtFechaNac = new JTextField();
		labAltura = new JLabel("Altura (cm)");
		txtAltura = new JTextField();
		labImagen = new JLabel("Imagen");
		txtImagen = new JTextField();
		txtImagen.setEditable(false);
		butGuardar = new JButton(GUARDAR);
		butGuardar.addActionListener(this);
		butGuardar.setActionCommand(GUARDAR);
		aux2.add(labNombre); aux2.add(txtNombre); aux2.add(labPosicion); aux2.add(txtPosicion); aux2.add(labPuntajeFIFA); aux2.add(txtPuntajeFIFA);
		aux2.add(labFechaNac); aux2.add(txtFechaNac); aux2.add(labAltura); aux2.add(txtAltura); aux2.add(labImagen); aux2.add(txtImagen);
		aux2.add(new JLabel()); aux2.add(butGuardar);
		
		add(aux1);
		add(aux2);
		pack();
	}
	
	public String darNombre() throws CampoVacioException{
		String m = txtNombre.getText();
		if(m.equals("")) {
			throw new CampoVacioException();
		}
		return m;
	}
	
	public String darPosicion() throws CampoVacioException{
		String m = txtPosicion.getText();
		if(m.equals("")) {
			throw new CampoVacioException();
		}
		return m;
	}
	
	public int darPuntajeFIFA() {
		return Integer.parseInt(txtPuntajeFIFA.getText());
	}
	
	public String darFechaNacimiento() throws CampoVacioException{
		String m = txtFechaNac.getText();
		if(m.equals("")) {
			throw new CampoVacioException();
		}
		return m;
	}
	
	public double darAltura() {
		return Double.parseDouble(txtAltura.getText());
	}
	
	public String darImagen() throws CampoVacioException {
		String m = txtImagen.getText();
		if(m.equals("")) {
			throw new CampoVacioException();
		}
		return m;
	}
	
	public void refrescarImagen(String imagen) {
		txtImagen.setText(imagen);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(GUARDAR)) {
			principal.agregarJugador();
		}
	}

}