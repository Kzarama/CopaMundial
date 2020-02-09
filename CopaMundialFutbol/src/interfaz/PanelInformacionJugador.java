package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelInformacionJugador extends JPanel implements ActionListener{

	public final static String ANTERIOR = "Anterior";
	public final static String SIGUIENTE = "Siguiente";
	public final static String PRIMERO = "Primero";
	
	private JLabel labFoto, labImagen, labNombre, labPosicion, labPuntajeFIFA, labFechaNac, labAltura;
	private JTextField txtImagen, txtNombre, txtPosicion, txtPuntajeFIFA, txtFechaNac, txtAltura;
	private JButton butAnterior, butSiguiente, butPrimero;
	private Icon icono;
	private InterfazCopaMundialFutbol principal;
	
	public PanelInformacionJugador(InterfazCopaMundialFutbol principal) {
		this.principal = principal;

		Font negrita = new Font("Broadway", Font.BOLD, 20);
		setLayout(new BorderLayout());
		
		JPanel auxImagen = new JPanel();
		auxImagen.setLayout(new GridLayout(1, 2));

		labFoto = new JLabel();
		
		auxImagen.add(new JLabel());
		auxImagen.add(labFoto);
		
		
		JPanel auxInfor = new JPanel();
		auxInfor.setLayout(new GridLayout(6, 2));
		labImagen = new JLabel("Imagen"); 
		txtImagen = new JTextField();
		txtImagen.setEditable(false);
		txtImagen.setBackground(Color.WHITE);
		labNombre = new JLabel("Nombre"); 
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBackground(Color.WHITE);
		labPosicion = new JLabel("Posicion"); 
		txtPosicion = new JTextField();
		txtPosicion.setEditable(false);
		txtPosicion.setBackground(Color.WHITE);
		labPuntajeFIFA = new JLabel("Puntaje FIFA"); 
		txtPuntajeFIFA = new JTextField();
		txtPuntajeFIFA.setEditable(false);
		txtPuntajeFIFA.setBackground(Color.WHITE);
		labFechaNac = new JLabel("Fecha Nacimiento"); 
		txtFechaNac = new JTextField();
		txtFechaNac.setEditable(false);
		txtFechaNac.setBackground(Color.WHITE);
		labAltura = new JLabel("Altura (cm)"); 
		txtAltura = new JTextField();
		txtAltura.setEditable(false);
		txtAltura.setBackground(Color.WHITE);
		txtAltura.setPreferredSize(new Dimension(30,30));
		
		labImagen.setHorizontalAlignment(JTextField.CENTER);
		labNombre.setHorizontalAlignment(JTextField.CENTER);
		labPosicion.setHorizontalAlignment(JTextField.CENTER);
		labPuntajeFIFA.setHorizontalAlignment(JTextField.CENTER);
		labFechaNac.setHorizontalAlignment(JTextField.CENTER);
		labAltura.setHorizontalAlignment(JTextField.CENTER);
		
		auxInfor.add(labImagen); 
		auxInfor.add(txtImagen); 
		auxInfor.add(labNombre); 
		auxInfor.add(txtNombre); 
		auxInfor.add(labPosicion); 
		auxInfor.add(txtPosicion);
		auxInfor.add(labPuntajeFIFA); 
		auxInfor.add(txtPuntajeFIFA); 
		auxInfor.add(labFechaNac); 
		auxInfor.add(txtFechaNac); 
		auxInfor.add(labAltura); 
		auxInfor.add(txtAltura);

		JPanel auxBotones = new JPanel();
		auxBotones.setLayout(new GridLayout(1, 3));
		butAnterior = new JButton(ANTERIOR);
		butAnterior.addActionListener(this);
		butAnterior.setActionCommand(ANTERIOR);
		butSiguiente = new JButton(SIGUIENTE);
		butSiguiente.addActionListener(this);
		butSiguiente.setActionCommand(SIGUIENTE);
		butPrimero = new JButton(PRIMERO);
		butPrimero.addActionListener(this);
		butPrimero.setActionCommand(PRIMERO);
		auxBotones.add(butAnterior);
		auxBotones.add(butPrimero);
		auxBotones.add(butSiguiente);
		
		TitledBorder border = BorderFactory.createTitledBorder("Información Jugador");
		border.setTitleColor(Color.RED);
		border.setTitleFont(negrita);
		setBorder(border);

		add(auxImagen, BorderLayout.NORTH);
		add(auxInfor, BorderLayout.CENTER);
		add(auxBotones, BorderLayout.SOUTH);	
	}
	
	public void cambiarImagen(String imagen) {
		icono = new ImageIcon(imagen);
		labFoto.setIcon(icono);
	}
	
	public void refrescarImagen(String imagen) {
		txtImagen.setText(imagen);
	}
	
	public void refrescarNombre(String nombre) {
		txtNombre.setText(nombre);
	}
	
	public void refrescarPosicion(String posicion) {
		txtPosicion.setText(posicion);
	}
	
	public void refrescarPuntajeFIFA(int puntaje) {
		txtPuntajeFIFA.setText(puntaje+"");
	}
	
	public void refrescarFechaNacimiento(String fechaNac) {
		txtFechaNac.setText(fechaNac);
	}
	
	public void refrescarAltura(double altura) {
		txtAltura.setText(altura+"");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(ANTERIOR)) {
			principal.mostrarAnterior();
		}else if(comando.equals(SIGUIENTE)) {
			principal.mostrarSiguiente();
		}else if(comando.equals(PRIMERO)) {
			principal.mostrarPrimero();
		}
	}
}
