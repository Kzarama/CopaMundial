package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mundo.Jugador;

public class PanelJugadores extends JPanel implements ActionListener, ListSelectionListener{
	
	public final static String AGREGAR = "Agregar";
	public final static String ELIMINAR = "Eliminar";
	public final static String BUSCAR = "Buscar";
	
	private JButton butAgregar, butEliminar, butBuscar;
	private JList listaJugadores;
	private JScrollPane scroll;
	private InterfazCopaMundialFutbol principal;
	
	public PanelJugadores(InterfazCopaMundialFutbol principal) {
		this.principal = principal;
		Font negrita = new Font("Bahnschrift", Font.BOLD, 16);
		TitledBorder border = BorderFactory.createTitledBorder("Jugadores");
		border.setTitleColor(Color.BLUE);
		border.setTitleFont(negrita);
		setBorder(border);
		setLayout(new BorderLayout());
		
		listaJugadores = new JList();
		scroll = new JScrollPane(listaJugadores);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listaJugadores.addListSelectionListener(this);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(1, 3));
		
		butAgregar = new JButton(AGREGAR);
		butAgregar.setForeground(Color.RED);
		butAgregar.setBackground(Color.BLACK);
		butAgregar.addActionListener(this);
		butAgregar.setActionCommand(AGREGAR);
		
		butEliminar = new JButton(ELIMINAR);
		butEliminar.addActionListener(this);
		butEliminar.setActionCommand(ELIMINAR);
		
		butBuscar = new JButton(BUSCAR);
		butBuscar.addActionListener(this);
		butBuscar.setActionCommand(BUSCAR);
		
		aux.add(butAgregar);
		aux.add(butEliminar);
		aux.add(butBuscar);
		
		add(aux, BorderLayout.SOUTH);
		add(scroll,BorderLayout.CENTER);
	}
	
	public void refrescarLista(ArrayList nuevaLista){
		listaJugadores.setListData(nuevaLista.toArray());
		listaJugadores.setSelectedIndex(0);
	}
	
	public JList darListaJugadores() {
		return listaJugadores;
	}
	
	public int darPosicion() {
		return listaJugadores.getSelectedIndex();
	}
	
	public Jugador darJugador() {
		return (Jugador)listaJugadores.getSelectedValue();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(AGREGAR)) {
			principal.mostrarDialogoAgregarJugador();
		}else if(comando.equals(ELIMINAR)) {
			principal.eliminarJugador();
		}else if(comando.equals(BUSCAR)) {
			principal.buscarJugador();
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent l) {
		if(listaJugadores.getSelectedValue()!=null) {
			Jugador j = (Jugador)listaJugadores.getSelectedValue();
			principal.actualizarDatos(j);
		}
		
	}
}
