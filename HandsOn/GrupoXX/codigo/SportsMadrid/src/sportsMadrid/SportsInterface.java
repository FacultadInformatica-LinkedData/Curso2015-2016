package sportsMadrid;


	import java.awt.EventQueue;

	import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

	import java.awt.Color;

	import javax.swing.JTextField;

	import java.awt.Font;

	import javax.swing.JComboBox;
import javax.net.ssl.HttpsURLConnection;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

	import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;

import org.apache.jena.query.ResultSet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

	public class SportsInterface  extends JFrame {

		private JPanel contentPane;
		private final JPanel ResultContainer = new JPanel();
		private final JPanel ResultContainer2 = new JPanel();
		
		private final String tildea = "\u00e1";
		private final String tildee = "\u00e9";
		private final String tildei = "\u00ed";
		private final String tildeo = "\u00f3";
		private final String tildeu = "\u00fa";
		private final String ene = "\u00f1";
		
		/**
		 * Launch the application.
		 */
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SportsInterface frame = new SportsInterface();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the frame.
		 */
		public SportsInterface() {
			setIconImage(new ImageIcon(getClass().getResource("../images/madrid.jpg")).getImage());;
			setTitle("SportsMadrid");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(80, 0, 1300, 750);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel LogoContainer = new JPanel();
			LogoContainer.setBounds(300, 0, 600, 100);
			LogoContainer.setBorder(null);
			LogoContainer.setBackground(Color.WHITE);
			contentPane.add(LogoContainer);
			LogoContainer.setLayout(null);

			JLabel metroLogo = new JLabel("");
			metroLogo.setBounds(53, 17, 100, 75);
			LogoContainer.add(metroLogo);
			metroLogo.setIcon(new ImageIcon(getClass().getResource("../images/ist2_3517764-style-runner_opt.jpg")));
			
			JTextPane txtpnMetroBucarest = new JTextPane();
			txtpnMetroBucarest.setForeground(Color.BLACK);
			txtpnMetroBucarest.setFont(new Font("Arial Black", Font.BOLD, 30));
			txtpnMetroBucarest.setText("SPORTS MADRID");
			txtpnMetroBucarest.setEditable(false);
			txtpnMetroBucarest.setBounds(185, 17, 350, 43);
			txtpnMetroBucarest.setBackground(Color.WHITE);
			
			LogoContainer.add(txtpnMetroBucarest);

			JPanel CabeceraContainer = new JPanel();
			CabeceraContainer.setBounds(150, 100, 800, 150);
			contentPane.add(CabeceraContainer);
			CabeceraContainer.setBorder(null);
			CabeceraContainer.setBackground(Color.WHITE);
			CabeceraContainer.setLayout(null);			

			final JComboBox OrigenBox = new JComboBox();
			OrigenBox.setModel(new DefaultComboBoxModel(new String[] {"--","Agrupaci"+tildeo+"n Deportiva Grupo de Monta"+ene+"a Jara",
					"Agrupaci"+tildeo+"n Deportiva La Fuenfr"+tildei+"a",
					"Agrupaci"+tildeo+"n Deportiva Rutas",
					"Barclaycard Center (antiguo Palacio de Deportes de la Comunidad de Madrid)",
					"Campo de Golf de la Real Federaci"+tildeo+"n Espa"+ene+"ola",
					"Campo de Golf Olivar de la Hinojosa",
					"Campo de Golf. Barber"+tildeo+"n & Collar C.D.S.C.E.A.",
					"Campo de Golf. Centro Deportivo Militar la Dehesa",
					"Campo de Golf. Green Canal",
					"Campo de Golf. Real Club Puerta de Hierro",
					"Centro Comercial y Palacio de Hielo Dreams",
					"Centro Cultural Deportivo Recreativo Renfe Madrid",
					"Centro de alto rendimiento de Madrid",
					"Centro de Nataci"+tildeo+"n Mundial 86 (M - 86)",
					"Centro de tecnificaci"+tildeo+"n de nataci"+tildeo+"n M-86",
					"Centro de tecnificaci"+tildeo+"n de tenis",
					"Centro de tecnificaci"+tildeo+"n de tiro con arco",
					"Centro Deportivo Municipal Alberto Garc"+tildei+"a (Pozo T"+tildeo+"o Raimundo)",
					"Centro Deportivo Municipal Alfredo Goyeneche",
					"Centro Deportivo Municipal Aluche",
					"Centro Deportivo Municipal Antiguo Can"+tildeo+"dromo",
					"Centro Deportivo Municipal Antonio D"+tildei+"az Miguel",
					"Centro Deportivo Municipal Arganzuela",
					"Centro Deportivo Municipal Barajas",
					"Centro Deportivo Municipal Casa de Campo",
					"Centro Deportivo Municipal Centro Integrado Arganzuela",
					"Centro Deportivo Municipal Cerro Almodovar",
					"Centro Deportivo Municipal Chamart"+tildei+"n",
					"Centro Deportivo Municipal Circuito BMX",
					"Centro Deportivo Municipal Ciudad de Los Poetas",
					"Centro Deportivo Municipal Concepci"+tildeo+"n",
					"Centro Deportivo Municipal Daoiz y Velarde 1",
					"Centro Deportivo Municipal Daoiz y Velarde 2",
					"Centro Deportivo Municipal El Espinillo",
					"Centro Deportivo Municipal El Olivillo",
					"Centro Deportivo Municipal Entrev"+tildei+"as",
					"Centro Deportivo Municipal Escuelas de San Ant"+tildeo+"n",
					"Centro Deportivo Municipal Estanque del Retiro",
					"Centro Deportivo Municipal Fabi"+tildea+"n Roncero",
					"Centro Deportivo Municipal F"+tildee+"lix Rubio",
					"Centro Deportivo Municipal Fernando Mart"+tildei+"n",
					"Centro Deportivo Municipal Francisco Fern"+tildea+"ndez Ochoa",
					"Centro Deportivo Municipal Francos Rodr"+tildei+"guez",
					"Centro Deportivo Municipal Fuente del Berro",
					"Centro Deportivo Municipal Gallur",
					"Centro Deportivo Municipal Gimnasio Moscard"+tildeo+"",
					"Centro Deportivo Municipal Hortaleza",
					"Centro Deportivo Municipal Jos"+tildee+" Mar"+tildei+"a Cagigal",
					"Centro Deportivo Municipal La Almudena",
					"Centro Deportivo Municipal La Bombilla",
					"Centro Deportivo Municipal La Chopera",
					"Centro Deportivo Municipal La Elipa",
					"Centro Deportivo Municipal La Mas"+tildeo+"",
					"Centro Deportivo Municipal La Mina",
					"Centro Deportivo Municipal La Vaguada",
					"Centro Deportivo Municipal Lago Casa de Campo",
					"Centro Deportivo Municipal Las Cruces",
					"Centro Deportivo Municipal Los Prunos",
					"Centro Deportivo Municipal Luis Aragon"+tildee+"s",
					"Centro Deportivo Municipal Marqu"+tildee+"s de Samaranch",
					"Centro Deportivo Municipal Miguel Guill�n Prim",
					"Centro Deportivo Municipal Moratalaz",
					"Centro Deportivo Municipal Moscard"+tildeo+"",
					"Centro Deportivo Municipal Orcasitas",
					"Centro Deportivo Municipal Orcasur",
					"Centro Deportivo Municipal Palomeras",
					"Centro Deportivo Municipal Pe"+ene+"uelas",
					"Centro Deportivo Municipal Pepu Hern"+tildea+"ndez",
					"Centro Deportivo Municipal Plata y Casta"+ene+"ar",
					"Centro Deportivo Municipal Playa Victoria",
					"Centro Deportivo Municipal Pradillo",
					"Centro Deportivo Municipal Pueblo Nuevo",
					"Centro Deportivo Municipal Puente de Vallecas",
					"Centro Deportivo Municipal Ra"+tildeu+"l Gonz"+tildea+"lez",
					"Centro Deportivo Municipal San Blas",
					"Centro Deportivo Municipal San Crist"+tildeo+"bal",
					"Centro Deportivo Municipal San Ferm"+tildei+"n",
					"Centro Deportivo Municipal San Juan Bautista",
					"Centro Deportivo Municipal Santa Ana",
					"Centro Deportivo Municipal Tenis Casa de Campo",
					"Centro Deportivo Municipal Tri"+tildea+"ngulo de Oro",
					"Centro Deportivo Municipal Valdebernardo",
					"Centro Deportivo Municipal Vallecas",
					"Centro Deportivo Municipal Vallehermoso",
					"Centro Deportivo Municipal Vic"+tildea+"lvaro",
					"Centro Deportivo Municipal Vicente del Bosque",
					"Ciudad de la Raqueta",
					"Club Alpino Espa"+ene+"ol",
					"Club Alpino Madrileo-Monta"+ene+"eros Madrile"+ene+"os",
					"Club de Buceo ABYSS Sub",
					"Club de Buceo Airsub",
					"Club de Buceo Aquanauta",
					"Club de Buceo Arcasub",
					"Club de Buceo Arsub CDE",
					"Club de Buceo ASSE",
					"Club de Buceo Atl"+tildea+"ntida Madrid",
					"Club de Buceo Calamar",
					"Club de Buceo Casco Antiguo",
					"Club de Buceo CIAS Madrid (Centro de Investigaciones y Actividades Subacu"+tildea+"ticas)",
					"Club de Buceo Cormor"+tildeo+"n",
					"Club de Buceo Deep Diving",
					"Club de Buceo Grupo Especial Subacu"+tildea+"tico (GESUB)",
					"Club de Buceo Narval",
					"Club de Buceo Nect"+tildeo+"n",
					"Club de Buceo Planeta Azul",
					"Club de Buceo Scuba Plus",
					"Club de Buceo Universidad Madrid (UAM)",
					"Club de Campo Villa de Madrid",
					"Club de Esgrima Barajas",
					"Club de Esgrima de Madrid",
					"Club de Monta"+ene+"ismo A.C.U.D.E. Repsol",
					"Club de Monta"+ene+"ismo Akuyak",
					"Club de Monta"+ene+"ismo Arawak",
					"Club de Monta"+ene+"ismo Atletismo Caprus",
					"Club de Monta"+ene+"ismo Atletismo Gran Grupo Moratalaz",
					"Club de Monta"+ene+"ismo Azimut",
					"Club de Monta"+ene+"ismo Bukaneros Solidarios",
					"Club de Monta"+ene+"ismo Bulderking",
					"Club de Monta"+ene+"ismo C.L.H.",
					"Club de Monta"+ene+"ismo Cabeza de Ajo",
					"Club de Monta"+ene+"ismo Chogori",
					"Club de Monta"+ene+"ismo Cristal de Roca",
					"Club de Monta"+ene+"ismo Culmen",
					"Club de Monta"+ene+"ismo Cumbres",
					"Club de Monta"+ene+"ismo de Agr"+tildeo+"nomos",
					"Club de Monta"+ene+"ismo El Acebo - La Kasa",
					"Club de Monta"+ene+"ismo EMT",
					"Club de Monta"+ene+"ismo Euskal Etxea",
					"Club de Monta"+ene+"ismo Geogr"+tildea+"phica",
					"Club de Monta"+ene+"ismo Grumbre (Banco de Espa"+ene+"a)",
					"Club de Monta"+ene+"ismo Hermandades del Trabajo",
					"Club de Monta"+ene+"ismo Iberia",
					"Club de Monta"+ene+"ismo Ingenieros Industriales",
					"Club de Monta"+ene+"ismo Libertad y Ocio",
					"Club de Monta"+ene+"ismo Majalasna",
					"Club de Monta"+ene+"ismo Monta"+ene+"eros Sordos de Madrid",
					"Club de Monta"+ene+"ismo Sanfer",
					"Club de Monta"+ene+"ismo Senderismo IPA Madrid",
					"Club de Monta"+ene+"ismo Telef"+tildeo+"nica",
					"Club de Monta"+ene+"ismo Tierra Tr"+tildea+"game",
					"Club de Monta"+ene+"ismo Ultra Run",
					"Club de Monta"+ene+"ismo Urban Monkey",
					"Club de Monta"+ene+"ismo Venture",
					"Club de Monta"+ene+"ismo Vertical UAH",
					"Club de Monta"+ene+"ismo y Librer"+tildei+"a Tierra de Fuego",
					"Club de Monta"+ene+"ismo Yurok",
					"Club de Nataci"+tildeo+"n Halegatos",
					"Club de Nataci"+tildeo+"n Jim"+tildee+"nez",
					"Club de Nataci"+tildeo+"n La Latina",
					"Club de Nataci"+tildeo+"n Marlins Moratalaz",
					"Club de Nataci"+tildeo+"n Sur",
					"Club de Nataci"+tildeo+"n Valle del Kas",
					"Club de Nataci"+tildeo+"n Vand '03",
					"Club de Pesca Submarina Barracuda",
					"Club de Remo Lago",
					"Club de Remo Madrid Velocidad",
					"Club de Remo Retiro 66",
					"Club de Remo Versalles",
					"Club de Tenis Lopez - Maeso",
					"Club de Tenis y de Nataci"+tildeo+"n Chamart"+tildei+"n",
					"Club de tenis y padel Fuencarral",
					"Club Deportivo Brezo Osuna",
					"Club Deportivo Canal",
					"Club Deportivo Elemental de Monta"+ene+"a Arganzuela",
					"Club Deportivo Elemental Dreampeaks",
					"Club Deportivo Elemental Los Pajaros Carpinteros",
					"Club Esgrima Cardenal Cisneros",
					"Club Esgrima Centro Cultural Ej"+tildee+"rcitos de Madrid",
					"Club Esgrima Distrito Carabanchel",
					"Club Esgrima Liceo Franc"+tildee+"s",
					"Club Esgrima Ramiro de Maeztu",
					"Club Esgrima Sant"+tildei+"simo Sacramento",
					"Club Estudiantes de Baloncesto",
					"Club H"+tildei+"pico Alpago",
					"Club H"+tildei+"pico Ca"+ene+"o Quebrado",
					"Club H"+tildei+"pico La Alameda del Pardo",
					"Club Militar de Monta"+ene+"a",
					"Club Mirasierra",
					"Club Ol"+tildei+"mpico Esgrima",
					"Club Real Sociedad Espa"+ene+"ola de Alpinismo Pe"+ene+"alara",
					"Complejo Deportivo Hockey Somontes de la Comunidad de Madrid",
					"Complejo Deportivo Madrid Caja M"+tildea+"gica",
					"Complejo Deportivo Somontes",
					"Escuela de Esgrima Ateneo",
					"Escuela de Parapente de Madrid al Cielo",
					"Escuela Madrile"+ene+"a de Alta Monta"+ene+"a EMAM",
					"Estadio de F"+tildeu+"tbol Cotorruelo",
					"Estadio de Vallecas",
					"Estadio Santiago Bernab"+tildee+"u",
					"Estadio Vicente Calder"+tildeo+"n",
					"Fitness Place Marbella",
					"Grupo de Monta"+ene+"a CSIC",
					"Hip"+tildeo+"dromo de la Zarzuela",
					"Instalaci"+tildeo+"n Deportiva Canal de Isabel II",
					"Instalaci"+tildeo+"n Deportiva Municipal Campos de F"+tildeu+"tbol, Pistas de Skate y Patinaje Puente de Praga",
					"Instalaci"+tildeo+"n Deportiva Municipal Canal de Remo",
					"Instalaci"+tildeo+"n Deportiva Municipal Embajadores M-30",
					"Instalaci"+tildeo+"n Deportiva Municipal Nave de Terneras",
					"Instalaci"+tildeo+"n Deportiva Municipal Parque Calle Vado",
					"Instalaci"+tildeo+"n Deportiva Municipal Parque Lineal del Manzanares",
					"Instalaci"+tildeo+"n Deportiva Municipal Pistas Padel Marqu"+tildee+"s de Monistrol",
					"Instalaci"+tildeo+"n Deportiva Municipal Puente de Praga",
					"Instalaci"+tildeo+"n Deportiva Municipal Roc"+tildeo+"dromo 'Roc 30'- Escuela de Escalada",
					"Instalaci"+tildeo+"n Deportiva Municipal San Pol",
					"Instalaci"+tildeo+"n Deportiva San Vicente de Paul",
					"Parque Deportivo Puerta de Hierro",
					"Piscina Baby Gym",
					"Piscina Bah"+tildei+"a - Madrid",
					"Piscina Real Canoe Madrid",
					"Piscina SAGE 2000",
					"Pista de F"+tildeu+"tbol 7 (SIETE). Distrito de Chamart"+tildei+"n",
					"Polideportivo Antonio Magari"+ene+"os",
					"Real Aero Club de Espa"+ene+"a (Cuatro Vientos)",
					"Real Canoe Nataci"+tildeo+"n Club",
					"Roc"+tildeo+"dromo y Club de Monta"+ene+"ismo Espacio Acci"+tildeo+"n",
					"Sala de Armas de Madrid"
}));
			OrigenBox.setForeground(Color.BLACK);
			OrigenBox.setBounds(0, 33, 500, 27);
			CabeceraContainer.add(OrigenBox);
			
			final JComboBox Eventos = new JComboBox();
			Eventos.setBounds(0, 100, 500, 27);
			Eventos.setForeground(new Color(0, 0, 0));
			Eventos.setModel(new DefaultComboBoxModel(new String[] {"--"}));
			CabeceraContainer.add(Eventos);

			JButton CalcularButton = new JButton("Buscar");
			CalcularButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			CalcularButton.setBounds(550, 32, 117, 29);
			CabeceraContainer.add(CalcularButton);
			
			JButton DatosButton = new JButton("Datos");
			DatosButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			DatosButton.setBounds(550, 100, 117, 29);
			CabeceraContainer.add(DatosButton);

			JTextPane txtpnOrigen = new JTextPane();
			txtpnOrigen.setEditable(false);
			txtpnOrigen.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			txtpnOrigen.setText("Instalaciones Deportivas:");
			txtpnOrigen.setBounds(0, 6, 300, 27);
			txtpnOrigen.setBackground(Color.WHITE);
			CabeceraContainer.add(txtpnOrigen);
			
			JTextPane txtpnEvento = new JTextPane();
			txtpnEvento.setEditable(false);
			txtpnEvento.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			txtpnEvento.setText("Eventos Deportivos:");
			txtpnEvento.setBounds(0, 74, 300, 27);
			txtpnEvento.setBackground(Color.WHITE);
			CabeceraContainer.add(txtpnEvento);
			

			JPanel MapaContainer = new JPanel();
			contentPane.add(MapaContainer);
			MapaContainer.setBounds(800, 250, 440, 403);
			MapaContainer.setBorder(null);
			MapaContainer.setBackground(Color.BLACK);			
			ResultContainer.setLayout(null);
			
			JLabel ImagenLabel= new JLabel("");
			ImagenLabel.setIcon(new ImageIcon(getClass().getResource("../images/certifimadrid-distritos.jpg")));
			MapaContainer.add(ImagenLabel);
			MapaContainer.setBackground(Color.WHITE);
			contentPane.add(ResultContainer);
			ResultContainer.setLayout(null);

			JEditorPane ResultadoLabel = new JEditorPane();
			ResultContainer.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			ResultContainer.setBackground(new Color(229,229,229));
			ResultContainer.setBounds(150, 250, 500, 350);
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
			txtpnElResultadoDe_1.setBounds(10, 10, 400, 25);
			txtpnElResultadoDe_1.setBackground(new Color(229,229,229));
			txtpnElResultadoDe_1.setEditable(false);
			txtpnElResultadoDe_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			txtpnElResultadoDe_1.setText("Datos del evento:");
			ResultContainer.add(txtpnElResultadoDe_1);
			
			JEditorPane ResultadoLabel2 = new JEditorPane();
			ResultContainer2.setBackground(Color.white);
			ResultContainer2.setBounds(150, 600, 500, 200);
			contentPane.add(ResultContainer2);
			ResultContainer2.setLayout(null);
			
			/*JEditorPane ResultadoLabel2 = new JEditorPane();
			ResultContainer2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			ResultContainer2.setBackground(new Color(229,229,229));
			ResultContainer2.setBounds(150, 475, 500, 200);
			contentPane.add(ResultContainer2);
			ResultContainer2.setLayout(null);
			
			final JEditorPane ResultadoPanel2 = new JEditorPane();
			ResultadoPanel2.setForeground(new Color(0, 0, 102));
			ResultadoPanel2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			ResultadoPanel2.setBackground(new Color(229,229,229));
			ResultadoPanel2.setEditable(false);
			ResultadoPanel2.setBounds(830, 100, 300, 585);
			ResultContainer2.add(ResultadoPanel2);
			
			JTextPane txtpnElResultadoDe_2 = new JTextPane();
			txtpnElResultadoDe_2.setBounds(10, 10, 400, 25);
			txtpnElResultadoDe_2.setBackground(new Color(229,229,229));
			txtpnElResultadoDe_2.setEditable(false);
			txtpnElResultadoDe_2.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			txtpnElResultadoDe_2.setText("Datos del distrito:");
			ResultContainer2.add(txtpnElResultadoDe_2);*/
			
			JButton MasInfo = new JButton("MasInfo");
			MasInfo.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			MasInfo.setBounds(10, 20, 117, 29);
			ResultContainer2.add(MasInfo);
			ResultContainer2.setLayout(null);

			CalcularButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){

					String centro = (String)OrigenBox.getSelectedItem();
					
					Consultas c = new Consultas();
					ResultSet results = c.consultaID(centro);
					
					String [] r = new String [10];
					
					int a = 0;
				
					while (results.hasNext())
					{
						QuerySolution binding = results.nextSolution();
						Resource subj = (Resource) binding.get("x");
						Literal titulo = binding.getLiteral("y");
						Literal z = binding.getLiteral("z");
						r[a] = titulo.toString();
						a += 1;
					}
					if (r[0] == null) {
						JOptionPane.showMessageDialog(null, "No hay eventos disponibles");
					}
					
					Eventos.setModel(new DefaultComboBoxModel(r));
					CabeceraContainer.add(Eventos);
				}
			});
			
			DatosButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){

					String evento = (String)Eventos.getSelectedItem();
					String centro = (String)OrigenBox.getSelectedItem();

					Consultas c = new Consultas();
					ResultSet results = c.consultaFecha(evento);
					String r = "";
					String distrito = null;
					while (results.hasNext())
					{
						QuerySolution binding = results.nextSolution();
						Resource subj = (Resource) binding.get("x");
						Literal fecha = binding.getLiteral("y");
						Literal z = binding.getLiteral("z");
						Literal tr = binding.getLiteral("tr");
						Resource dis = (Resource) binding.get("dis");
						r = "Titulo: "+ evento + "\nCentro: "+ centro + "\nFecha: "+fecha + "\n" 
								+ "Hora: "+ z + "\nDistrito: ";
						distrito = dis.getURI();
					}
					String [] a = c.consultaDistrito(distrito);
					r += a[0];
					
					results = c.consultaTransporte(centro);
					while (results.hasNext())
					{
						QuerySolution binding = results.nextSolution();
						Resource subj = (Resource) binding.get("x");
						Literal tr = binding.getLiteral("tr");
						int bus = tr.toString().indexOf("Bus");
						int renfe = tr.toString().indexOf("Renfe");
						int m = tr.toString().indexOf("Metro");
						String metro = "-";
						if (m != -1 && bus != -1) metro = tr.toString().substring(m+6,bus);
						else if(m != -1 && bus == -1 && renfe != -1) metro = tr.toString().substring(m+6,renfe);
						else if(m != -1 && bus == -1 && renfe != -1) metro = tr.toString().substring(m+6);
						String bu = "-";
						if (bus != -1 && renfe != -1) bu = tr.toString().substring(bus+4,renfe);
						else if (bus != -1) bu = tr.toString().substring(bus+4);
						String ren = "-";
						if (renfe != -1) ren = tr.toString().substring(renfe+6);
						r += "\nTransporte: " + "\n   Metro:" + metro + "\n   Bus:" + bu + "\n   Renfe: " + ren;
						
						
					}
					results = c.consultaCalle(centro);
					while (results.hasNext())
					{
						QuerySolution binding = results.nextSolution();
						Resource subj = (Resource) binding.get("x");
						Literal calle = binding.getLiteral("calle");
						r += "\nCalle: " + calle;
					}
					results = c.consultaTlf(centro);
					while (results.hasNext())
					{
						QuerySolution binding = results.nextSolution();
						Resource subj = (Resource) binding.get("x");
						Literal tlf = binding.getLiteral("tlf");
						r +="\nTelefono: "+ tlf;
					}
					results = c.consultaEmail(centro);
					while (results.hasNext())
					{
						QuerySolution binding = results.nextSolution();
						Resource subj = (Resource) binding.get("x");
						Literal email = binding.getLiteral("email");
						r += "\nEmail: " + email;
					}
					
					
					JTextPane res = new JTextPane();
					res.setBounds(10, 40, 480, 290);
					res.setBackground(new Color(229,229,229));
					res.setEditable(false);
					res.setFont(new Font("Lucida Grande", Font.BOLD, 15));
					res.setText(r);
					ResultContainer.add(res);
					
					/*String r2 = "Resumen: "+ a[1] +"\nCodigo Postal: "+a[2];
					JTextPane res2 = new JTextPane();
					res2.setBounds(10, 40, 480, 140);
					res2.setBackground(new Color(229,229,229));
					res2.setEditable(false);
					res2.setFont(new Font("Lucida Grande", Font.BOLD, 12));
					res2.setText(r2);
					ResultContainer2.add(res2);
					
					Ventana2 frame = new Ventana2(a[0]);
					frame.setVisible(true);*/
				
					
				}
			});
			
			MasInfo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){

					String evento = (String)Eventos.getSelectedItem();
					Consultas c = new Consultas();
					ResultSet results = c.consultaFecha(evento);
					String r = "";
					String distrito = null;
					while (results.hasNext())
					{
						QuerySolution binding = results.nextSolution();
						Resource subj = (Resource) binding.get("x");
						Resource dis = (Resource) binding.get("dis");
						distrito = dis.getURI();
					}
					String [] a = c.consultaDistrito(distrito);
					String [] b = c.consultalatylong(distrito);
					a[3] = b[0];
					a[4] = b[1];
					Ventana2 v = new Ventana2(a);
					v.setVisible(true);
					dispose();
				}
				
			});
		}
	}
	
	
	


