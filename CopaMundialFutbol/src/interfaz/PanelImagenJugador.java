package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelImagenJugador extends JPanel implements ActionListener{
	
	public final static String CARGAR = "";
	
	private JButton cargar;
	private ImageIcon icon;
	private JFileChooser file;
	private File archivo;
	private String nombreArchivo;
	private String rutaArchivo;
	private String rutaImagen;
	
	private DialogoAgregarJugador principal;
	
	public PanelImagenJugador(DialogoAgregarJugador principal) {
		this.principal = principal;
		TitledBorder border = BorderFactory.createTitledBorder("Imágen del Jugador");
		border.setTitleColor(Color.BLUE);
		setBorder(border);
		icon = new ImageIcon("img/anonimo.jpg");
		cargar = new JButton();
		cargar.setIcon(icon);
		cargar.addActionListener(this);
		cargar.setActionCommand(CARGAR);
		add(cargar);
	}

	public void cargarImagen() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("imagenes", "jpg", "png");
		file = new JFileChooser();
		file.setFileFilter(filtro);
		int a  = file.showOpenDialog(this);
		char back = 92;
		if(a == JFileChooser.APPROVE_OPTION) {
			archivo = file.getSelectedFile();
			modificarNombreArchivo(archivo.getName());
			modificarRutaArchivo(archivo.getParent());
			modificarRutaImagen(darRutaArchivo()+back+darNombreArchivo());
			ImageIcon imagen = new ImageIcon(rutaImagen);
			int w = imagen.getIconWidth();
			int h = imagen.getIconHeight();
			cargar.setSize(w, h);
			cargar.setIcon(imagen);
			
		}
	}
	
	public String darNombreArchivo() {
		return nombreArchivo;
	}
	
	public void modificarNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public String darRutaImagen() {
		return rutaImagen;
	}
	
	public void modificarRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	
	public String darRutaArchivo() {
		return rutaArchivo;
	}
	
	public void modificarRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(CARGAR)) {
			cargarImagen();
			principal.refrescarImagen(darRutaImagen());
		}
	}

}