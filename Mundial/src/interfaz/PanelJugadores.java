package interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Jugador;

public class PanelJugadores extends JPanel implements ActionListener, ListSelectionListener {
	
	public final static String AGREGAR = "AGREGAR", ELIMINAR = "ELIMINAR", BUSCAR = "BUSCAR";
	
	private JList lista;
	private JScrollPane scroll;
	private JButton btAgregar, btEliminar, btBuscar;
	private JPanel panel;
	private InterfazPrincipal i;
	private JLabel jugadores;
	
	public PanelJugadores(InterfazPrincipal i) {
		this.i = i;
		
		setLayout(new BorderLayout());
		
		lista = new JList();
		scroll = new JScrollPane(lista);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		lista.addListSelectionListener(this);
		
		btAgregar = new JButton("AGREGAR");
		btAgregar.setActionCommand(AGREGAR);
		btAgregar.addActionListener(this);
		btEliminar = new JButton("ELIMINAR");
		btEliminar.setActionCommand(ELIMINAR);
		btEliminar.addActionListener(this);
		btBuscar = new JButton("BUSCAR");
		btBuscar.setActionCommand(BUSCAR);
		btBuscar.addActionListener(this);
		
		Font fselecciones = new Font("Arial", Font.BOLD, 15);
		jugadores = new JLabel("                                       JUGADORES");
		jugadores.setFont(fselecciones);
		
		panel = new JPanel(new GridLayout(1, 3));
		panel.add(btAgregar);
		panel.add(btEliminar);
		panel.add(btBuscar);
		
		add(jugadores, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
	}
	
	public void refrescarLista(ArrayList n) {
		lista.setListData(n.toArray());
		lista.setSelectedIndex(0);
	}
	
	public String darJugador() {
		return (String) lista.getSelectedValue();
	}
	
	public void setSelectIndex(int numm, int num) {
		lista.setSelectedIndex((lista.getSelectedIndex() * numm ) + num);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando == AGREGAR) {
			i.mostrarAJugador(true);
			i.refrescarListaJugadores();
			i.guardarDatos();
		} else if(comando == ELIMINAR) {
			i.eliminarJugador();
			i.refrescarListaJugadores();
			i.refrescarInformacion();
			i.guardarDatos();
		} else if(comando == BUSCAR) {
			i.buscarJugador();
			i.refrescarListaJugadores();
			i.guardarDatos();
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(lista.getSelectedValue() != null) {
			i.refrescarInformacion();
		}
	}
	
}
