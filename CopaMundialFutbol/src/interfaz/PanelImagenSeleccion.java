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

public class PanelImagenSeleccion extends JPanel implements ActionListener{
	
	public final static String CARGAR = "";
	
	private JButton cargar;
	private ImageIcon icon;
	private JFileChooser file;
	private File archivo;
	private String nombreArchivo;
	private String rutaImagen;
	private DialogoAgregarSeleccion principal;
	
	public PanelImagenSeleccion(DialogoAgregarSeleccion principal) {
		this.principal = principal;
		TitledBorder border = BorderFactory.createTitledBorder("Imágen de la Selección");
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
			String rutaArchivo = archivo.getParent();
			rutaImagen = rutaArchivo+back+darNombreArchivo();
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(CARGAR)) {
			cargarImagen();
			principal.refrescarImagen(darRutaImagen());
		}
	}

}

