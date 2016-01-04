package sportsMadrid;


	import java.awt.EventQueue;

	import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

	import java.awt.Color;

	import javax.swing.JTextField;

	import java.awt.Font;

	import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;

	import net.datastructures.ArrayStack;
import net.datastructures.HeapPriorityQueue;
import net.datastructures.PriorityQueue;
import net.datastructures.Stack;

	import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Collections;

	import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;

import org.apache.jena.query.ResultSet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;
/**
	 * @author �lvaro Casela Ordo�ez
	 * @author Francisco Jim�nez Palacio
	 *
	 */

	public class Ventana2  extends JFrame {

		private JPanel contentPane;
		private final JPanel ResultContainer = new JPanel();
		
		private final String tildea = "\u00e1";
		private final String tildee = "\u00e9";
		private final String tildei = "\u00ed";
		private final String tildeo = "\u00f3";
		private final String tildeu = "\u00fa";
		private final String ene = "\u00f1";
		/**
		 * Launch the application.
		 */

		/*public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Ventana2 frame = new Ventana2(new String[] {"Chamartin","holahola","23456"});
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}*/
		/**
		 * Create the frame.
		 */
		public Ventana2(String [] info) {
			//setIconImage(new ImageIcon(getClass().getResource("../images/logo2.png")).getImage());;
			setTitle("SportsMadrid");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(350, 300, 1300, 700);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JPanel LogoContainer = new JPanel();
			LogoContainer.setBounds(500, 10, 380, 100);
			LogoContainer.setBorder(null);
			LogoContainer.setBackground(Color.WHITE);
			contentPane.add(LogoContainer);
			LogoContainer.setLayout(null);

			JLabel metroLogo = new JLabel("");
			metroLogo.setBounds(0, 0, 100, 75);
			LogoContainer.add(metroLogo);
			metroLogo.setIcon(new ImageIcon(getClass().getResource("../images/ist2_3517764-style-runner_opt.jpg")));
			
			JTextPane txtpnMetroBucarest = new JTextPane();
			txtpnMetroBucarest.setForeground(Color.BLACK);
			txtpnMetroBucarest.setFont(new Font("Arial Black", Font.BOLD, 30));
			txtpnMetroBucarest.setText(info[0]);
			txtpnMetroBucarest.setEditable(false);
			txtpnMetroBucarest.setBounds(110, 20, 250, 75);
			txtpnMetroBucarest.setBackground(Color.WHITE);
			LogoContainer.add(txtpnMetroBucarest);
			
			JPanel MapaContainer = new JPanel();
			contentPane.add(MapaContainer);
			MapaContainer.setBounds(650, 140, 640, 500);
			MapaContainer.setBorder(null);
			MapaContainer.setBackground(Color.BLACK);			
			ResultContainer.setLayout(null);
			
			JLabel ImagenLabel= new JLabel("");
			if (info[0].equals("Arganzuela")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Arganzuela.PNG")));
			}
			if (info[0].equals("Chamberí")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Chamberí.PNG")));
			}
			if (info[0].equals("Moncloa-Aravaca")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Moncloa-Aravaca.PNG")));
			}
			if (info[0].equals("Salamanca")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Salamanca.PNG")));
			}
			if (info[0].equals("Usera")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Usera.PNG")));
			}
			if (info[0].equals("Retiro")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Retiro.PNG")));
			}	
			if (info[0].equals("Carabanchel")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Carabanchel.PNG")));
			}
			if (info[0].equals("Centro")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Centro.PNG")));
			}
			if (info[0].equals("Latina")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Latina.PNG")));
			}
			if (info[0].equals("San Blas-Canillejas")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_San_Blas.PNG")));
			}
			if (info[0].equals("Barajas")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Barajas.PNG")));
			}
			if (info[0].equals("Villaverde")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Villaverde.PNG")));
			}
			if (info[0].equals("Chamartín")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Chamartin.PNG")));
			}
			if (info[0].equals("Ciudad Lineal")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Ciudad_Lineas.PNG")));
			}
			if (info[0].equals("Hortaleza")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Hortaleza.PNG")));
			}
			if (info[0].equals("Moratalaz")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Moratalaz.PNG")));
			}
			if (info[0].equals("Puente de Vallecas")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Puente_de_Vallecas.PNG")));
			}
			if (info[0].equals("Tetuán")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Tetuan.PNG")));
			}
			if (info[0].equals("Vicálvaro")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Vicalvaro.PNG")));
			}
			if (info[0].equals("Fuencarral-El Pardo")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Fuencarral-El Pardo.PNG")));
			}
			if (info[0].equals("Villa de Vallecas")) {
				ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/300px-Maps_-_ES_-_Madrid_-_Villa_de_Vallecas.PNG")));
			}
			
			MapaContainer.add(ImagenLabel);
			MapaContainer.setBackground(Color.WHITE);
			contentPane.add(ResultContainer);
			ResultContainer.setLayout(null);
			
			JEditorPane ResultadoLabel = new JEditorPane();
			ResultContainer.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			ResultContainer.setBackground(new Color(229,229,229));
			ResultContainer.setBounds(60, 140, 580, 450);
			contentPane.add(ResultContainer);
			ResultContainer.setLayout(null);

			final JEditorPane ResultadoPanel = new JEditorPane();
			ResultadoPanel.setForeground(new Color(0, 0, 102));
			ResultadoPanel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			ResultadoPanel.setBackground(new Color(229,229,229));
			ResultadoPanel.setEditable(false);
			ResultadoPanel.setBounds(830, 100, 300, 585);
			ResultContainer.add(ResultadoPanel);

			JTextPane txtpnElResultadoDe_1 = new JTextPane();
			txtpnElResultadoDe_1.setBounds(10, 10, 550, 25);
			txtpnElResultadoDe_1.setBackground(new Color(229,229,229));
			txtpnElResultadoDe_1.setEditable(false);
			txtpnElResultadoDe_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			txtpnElResultadoDe_1.setText("Datos del distrito:");
			ResultContainer.add(txtpnElResultadoDe_1);
			
			String r2 = "Resumen: "+ info[1] +"\n\nCodigo Postal: "+info[2] 
					+ "\n\nLatitud: " + info[4] + "\n\nLongitud: " + info[3];
			JTextPane res = new JTextPane();
			res.setBounds(10, 40, 530, 400);
			res.setBackground(new Color(229,229,229));
			res.setEditable(false);
			res.setFont(new Font("Lucida Grande", Font.BOLD, 12));
			res.setText(r2);
			ResultContainer.add(res);
			
			JButton Cerrar = new JButton("Cerrar");
			Cerrar.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			Cerrar.setBounds(65, 610, 117, 29);
			contentPane.add(Cerrar);
			
			Cerrar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					SportsInterface s = new SportsInterface();
					s.setVisible(true);
					dispose();
				}
			});

		}
	}
