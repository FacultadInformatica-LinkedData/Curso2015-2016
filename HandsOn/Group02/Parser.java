import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Parser {
	
	static int c = 0;
	
	public static String crearAnalisis(String titulo, String filename, String fecha, String descripcion){
		return 
		"<h3>"+titulo+"</h3>"+"\n"+
	    "<ul>"+"\n"+
	    "	<li><b>Nombre del fichero:</b>"+filename+"</li>"+"\n"+
	    "	<li><b>Fecha:</b>"+fecha+"</li>"+"\n"+
	    "	<li><b>Descripción:</b>"+ descripcion +"</li>"+"\n"+
	    "	<li><b>Origen de los datos:</b> Ayuntamiento de Madrid</li>"+"\n"+
	    "	<li><a href=\"http://datos.madrid.es/egob/catalogo/aviso-legal\">Licencia</a></li>"+"\n"+
	    "</ul>"+"\n";
	}
	public static String crearTabla(ArrayList<String> elementos){
		String ini = 	"<table style=\"width:100%; text-align:left;\">"+"\n"+
			    		"	<tr  bgcolor=\"#8ca6fd\"><th>Column</th><th>Type</th><th>Comments/Range</th><th>Problems</th></tr>"+"\n";
		for(String fila : elementos){
			ini += fila+"\n";
		}
		ini += "</table> \n";
		return ini;
	}
	
	public static String crearFila(String column, String type, String coment, String problems){
		String fila ="";
		if(c%2==0){
			fila = "<tr><td>"+column+"</td><td>"+type+"</td><td>"+coment+"</td><td>"+problems+"</td></tr>";
		}
		else{
			fila = "<tr bgcolor=\"#e5e5e5\"><td>"+column+"</td><td>"+type+"</td><td>"+coment+"</td><td>"+problems+"</td></tr>";
		}
		c++;
		return fila;
				
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el titulo del csv: \n");
		String titulo = s.nextLine();
		System.out.println("Introduce el nombre de fichero del csv: \n");
		String filename = s.nextLine();
		System.out.println("Introduce la fecha del csv: \n");
		String fecha = s.nextLine();
		System.out.println("Introduce una descripcion (1 linea) del csv: \n");
		String descripcion = s.nextLine();
		
		String analisis = crearAnalisis(titulo, filename, fecha, descripcion);
		
		boolean fin=false;
		ArrayList<String> elementos = new ArrayList<String>();
		while(!fin){
			
			System.out.println("Introduce el nombre de la columna: \n");
			String column = s.nextLine();
			System.out.println("Introduce el tipo: \n");
			String type = s.nextLine();
			System.out.println("Introduce el rango/comentarios: \n");
			String coment = s.nextLine();
			System.out.println("Introduce los problemas: \n");
			String problems = s.nextLine();
			
			elementos.add(crearFila(column, type, coment, problems));
			
			System.out.println("Continuar = 1\n");
			System.out.println("Finalizar = 0\n");
			System.out.println("¿Que quieres hacer? \n");
			if(s.nextInt()==0)fin=true;
			s.nextLine();
		}
		
		String tabla = crearTabla(elementos);
		
		String html = 	"<html>\n"+
						"	<body>\n"+analisis+tabla+
						"	</body>\n"+
						"</html>";
		
		File file = new File("analysis.html");
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(html);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fichero creado en: "+file.getAbsolutePath());
		
		
		

	}

}
