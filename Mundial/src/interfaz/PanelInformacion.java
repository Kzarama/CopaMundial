package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import modelo.Jugador;

public class PanelInformacion extends JPanel implements ActionListener{
	
	public final static String ANTERIOR = "ANTERIOR", PRIMERO = "PRIMERO", SIGUIENTE = "SIGUIENTE";
	
	private JLabel labImagen, labNombre, labPosicion, labPuntaje, labFecha, LabAltura, imgn;
	private JTextField txtImagen, txtNombre, txtPosicion, txtPuntaje, txtFecha, txtAltura;
	private JButton btAnterior, btPrimero, btSiguiente;
	private JPanel principal, panel1, panel2, panel3, panelBotones;
	private InterfazPrincipal i;
	private ImageIcon img;
	
	public PanelInformacion(InterfazPrincipal i) {
		this.i = i;
		panel1 = new JPanel(new GridLayout(1, 3));
		panel2 = new JPanel(new GridLayout(6, 2));
		panel3 = new JPanel(new GridLayout(2, 1));
		panelBotones = new JPanel(new FlowLayout());
		principal = new JPanel(new BorderLayout());
		
		Font ft = new Font("Arial", Font.BOLD ,15);
		TitledBorder border = BorderFactory.createTitledBorder("INFORMACION JUGADOR");
		border.setTitleColor(Color.BLUE);
		border.setTitleFont(ft);
		panel3.setBorder(border);
		
		labImagen = new JLabel("IMAGEN");
		labNombre = new JLabel("NOMBRE");
		labPosicion = new JLabel("POSICION");
		labPuntaje = new JLabel("PUNTAJE FIFA");
		labFecha = new JLabel("FECHA NACIMIENTO");
		LabAltura = new JLabel("ALTURA");
		
		txtImagen = new JTextField();
		txtImagen.setEditable(false);
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtPosicion = new JTextField();
		txtPosicion.setEditable(false);
		txtPuntaje = new JTextField();
		txtPuntaje.setEditable(false);
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtAltura = new JTextField();
		txtAltura.setEditable(false);
		
		img = new ImageIcon("img/ok.jpg");
		
		imgn = new JLabel();
		imgn.setIcon(img);
		
		btAnterior = new JButton("ANTERIOR");
		btAnterior.setActionCommand(ANTERIOR);
		btAnterior.addActionListener(this);
		btPrimero = new JButton("PRIMERO");
		btPrimero.setActionCommand(PRIMERO);
		btPrimero.addActionListener(this);
		btSiguiente = new JButton("SIGUIENTE");
		btSiguiente.setActionCommand(SIGUIENTE);
		btSiguiente.addActionListener(this);
		
		panel1.add(imgn);
		panel2.add(labImagen);
		panel2.add(txtImagen);
		panel2.add(labNombre);
		panel2.add(txtNombre);
		panel2.add(labPosicion);
		panel2.add(txtPosicion);
		panel2.add(labPuntaje);
		panel2.add(txtPuntaje);
		panel2.add(labFecha);
		panel2.add(txtFecha);
		panel2.add(LabAltura);
		panel2.add(txtAltura);
		
		panel3.add(panel1);
		panel3.add(panel2);
		
		panelBotones.add(btAnterior);
		panelBotones.add(btPrimero);
		panelBotones.add(btSiguiente);
		
		principal.add(panel3, BorderLayout.CENTER);
		principal.add(panelBotones, BorderLayout.SOUTH);
		
		setLayout(new BorderLayout());
		add(principal, BorderLayout.CENTER);
		
	}
	
	public void cambiarDatos(Jugador j) {
		txtImagen.setText(j.getImagen());
		txtNombre.setText(j.getNombre());
		txtPosicion.setText(j.getPosicion());
		txtPuntaje.setText(j.getPuntajeFIFA()+"");
		txtFecha.setText(j.getFechaNacimiento());
		txtAltura.setText(j.getAltura()+"");
		img = new ImageIcon(j.getImagen());
		imgn.setIcon(img);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando == ANTERIOR) {
			i.getjugadorAnterior();
		} else if(comando == PRIMERO) {
			i.getPrimerJugador();
		} else if(comando == SIGUIENTE){
			i.getSiguienteJugador();
		}
	}	

}