package interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Seleccion;

public class PanelSelecciones extends JPanel implements ActionListener, ListSelectionListener {
	
	public final static String AGREGAR = "AGREGAR", ELIMINAR = "ELIMINAR", BUSCAR = "BUSCAR", MODIFICAR = "MODIFICAR";
	
	private JList lista;
	private JScrollPane scroll;
	private JButton btAgregar, btEliminar, btBuscar, btModificar;
	private JPanel panel;
	private InterfazPrincipal i;
	private JLabel selecciones;
	
	public PanelSelecciones(InterfazPrincipal i) {
		
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
		btModificar = new JButton("MODIFICAR");
		btModificar.setActionCommand(MODIFICAR);
		btModificar.addActionListener(this);
		
		Font fselecciones = new Font("Arial", Font.BOLD, 15);
		selecciones = new JLabel("                                       SELECCIONES");
		selecciones.setFont(fselecciones);
		
		panel = new JPanel(new GridLayout(1, 3));
		panel.add(btAgregar);
		panel.add(btEliminar);
		panel.add(btBuscar);
		panel.add(btModificar);
		
		
		add(selecciones, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
	}
	
	public void refrescarLista(ArrayList n) {
		lista.setListData(n.toArray());
		lista.setSelectedIndex(0);
	}
	
	public int darPosicion() {
		return lista.getSelectedIndex();
	}
	
	public String getSeleccion() {
		return (String) lista.getSelectedValue();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando == AGREGAR) {
			i.mostrarASeleccion(true);
			i.refrescarListaSelecciones();
			i.guardarDatos();
		} else if(comando == ELIMINAR) {
			i.eliminarSeleccion();
			i.refrescarListaSelecciones();
			i.refrescarListaJugadores();
			i.guardarDatos();
		} else if(comando == BUSCAR) {
			i.buscarSeleccion();
			i.refrescarListaSelecciones();
			i.guardarDatos();
		} else {
			i.modificarSeleccion();
			i.refrescarListaSelecciones();
			i.guardarDatos();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(lista.getSelectedValue() != null) {
			i.refrescarListaJugadores();
			i.refrescarInformacion();
		}
	}
}