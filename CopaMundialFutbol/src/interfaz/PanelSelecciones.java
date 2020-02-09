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

import mundo.Seleccion;

public class PanelSelecciones extends JPanel implements ActionListener, ListSelectionListener{

	public final static String AGREGAR = "Agregar";
	public final static String ELIMINAR = "Eliminar";
	public final static String BUSCAR = "Buscar";
	public final static String MODIFICAR = "Modificar";
	public final static String MOSTRAR_ARBOL = "Mostrar Árbol";
	
	private JButton butAgregar, butEliminar, butBuscar, butModificar, butMostrarArbol;
	private JList listaSelecciones;
	private JScrollPane scroll;
	private InterfazCopaMundialFutbol principal;
	
	public PanelSelecciones(InterfazCopaMundialFutbol principal) {
		this.principal = principal;
		Font negrita = new Font("Bahnschrift", Font.BOLD, 16);
		TitledBorder border = BorderFactory.createTitledBorder("Selecciones");
		border.setTitleColor(Color.BLUE);
		border.setTitleFont(negrita);
		setBorder(border);
		setLayout(new BorderLayout());
		
		listaSelecciones = new JList();
		scroll = new JScrollPane(listaSelecciones);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listaSelecciones.addListSelectionListener(this);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(2, 3));
		
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
		
		butModificar = new JButton(MODIFICAR);
		butModificar.addActionListener(this);
		butModificar.setActionCommand(MODIFICAR);
		
		butMostrarArbol = new JButton(MOSTRAR_ARBOL);
		butMostrarArbol.addActionListener(this);
		butMostrarArbol.setActionCommand(MOSTRAR_ARBOL);
		
		aux.add(butAgregar);
		aux.add(butEliminar);
		aux.add(butBuscar);
		aux.add(butModificar);
		aux.add(butMostrarArbol);
		
		add(aux, BorderLayout.SOUTH);
		add(scroll,BorderLayout.CENTER);
	}
	
	public void refrescarLista(ArrayList nuevaLista){
		listaSelecciones.setListData(nuevaLista.toArray());
		listaSelecciones.setSelectedIndex(0);
	}

	public Seleccion darSeleccion() {
		Seleccion s = (Seleccion)listaSelecciones.getSelectedValue();
		return s;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(AGREGAR)) {
			principal.mostrarDialogoAgregarSeleccion();
		}else if(comando.equals(ELIMINAR)) {
//			principal.eliminarSeleccion();
		}else if(comando.equals(BUSCAR)) {
			principal.buscarSeleccion();
		}else if(comando.equals(MODIFICAR)) {
			int i = listaSelecciones.getSelectedIndex();
//			principal.modificarSeleccion(i);
		}else if(comando.equals(MOSTRAR_ARBOL)) {
			principal.mostrarDialogoArbol();
		}
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent l) {
		if(listaSelecciones.getSelectedValue() !=null) {
			principal.mostrarJugadores();
		}
		
	}
}
