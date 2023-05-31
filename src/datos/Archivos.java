/*    
 
 -----------MANIPULACIÓN DE ARCHIVOS----------------
 
		   Se puede guardar de manera permanente la información en el equipo
		   y para ello en vez de ocupar la memoria RAM, se deberá usar un medio
		   físico como un disco duro, una unidad USB  y para realizar esta operación 
		   se tienen que manejar streams o flujos.
		   
		   Se pueden abordar los streams como FLUJOS DE BYTES o FLUJOS DE CARACTERES:
		   
		   -Si se requiere almacenar información que vamos a leer como un documento 
		   de texto se ocuparía el FLUJOS DE CARACTERES
		   					Reader (Secuencia de entrada)
		   					Writer (Secuencia de salida)
		   
		  -Si se requiere enviar información a través de la red a un sitio remoto, lo
		   mejor es mandarlo como FLUJO DE CARACTERES ya que no se requiere leer 
		   la información.
		   					InputStream (Secuencia de entrada)
		   					OutputStream (Secuencia de salida)
 
		 
		  Todos los métodos o clases pertenecientes al paquete java.io nos van a pedir 
		  siempre que capturemos las excepciones, lo anterior es porque puede ocurrir que 
		  la información que está almacenada en dispositivos físicos no esté disponible, por
		  ello java nos exige controlar las excepciones.

 -----------BUFFERS ----------- 
		  	
		  	Cuando el archivo tiene miles o millones de caracteres, las funciones anteriores no son eficientes 
		    porque cada que nosotros queremos escribir o leer  información en el archivo, lo que hacemos  es comunicarnos
		    con el archivo y leer o escribir carácter a carácter. En el caso de el archivo tuviera miles o millones 
		    de caracteres  esto sería una operación que se tendría que repetir miles o millones de veces lo que la 
		    haría ineficiente y lenta , estaría consumiendo muchos recursos.
		    
		    Para solucionar lo anterior se requiere de un Buffer,  éste es una especie de memoria interna que se 
		    coloca entre el programa de java y el archivo.
		    
		    					Programa Java ------  Buffer ------ Archivo
		    
		    El objetivo del buffer (memoria intermedia)  es que en vez de acceder al archivo se acceda al buffer.
		    	-Se gana rapidez, eficiencia 
		    	-Si existe una problema de comunicación con el archivo no se vería interrumpido el proceso 
		    	-Se usan los buffes para leer y escribir 
		    	
		    							BufferedReader  
		    							  	readLine()  lee línea a línea 
		    							BufferdWriter 
		    								writeLine()

 -----------STREAMS BYTE -----------
 
		 	Es una poderosa herramienta para poder enviar o recibir cualquier tipo de archivo desde un programa de java.
		 	En informática cualquier tipo de archivo es susceptible de convertirse en una sucesión de bytes:
		 	 
		 							FileInputStream
		 							FileOutPutStream
		 				
		     
*/

package datos;
import java.io.*;
import java.nio.charset.Charset;

public class Archivos {

	public void lee() {

		try {

			FileReader archivo =new FileReader("C:"+File.separator+"Users"+File.separator+"Angel"+
					File.separator+"eclipse-workspace"+File.separator+"CursoJava"+File.separator+"src"+
					File.separator+"archivo.txt",Charset.forName("UTF8"));

			int c=archivo.read();

			while(c!=-1)
			{
				char letra= (char)c;
				System.out.print(letra);				
				c= archivo.read();
			}

			archivo.close();

		} catch (IOException e) {
			System.out.println("No se ha encontrado el archivo");
		} 

	}	
	
	public void leeConMemoriaIntermedia() {

		try {

			FileReader archivo =new FileReader("C:"+File.separator+"Users"+File.separator+"Angel"+
					File.separator+"eclipse-workspace"+File.separator+"CursoJava"+File.separator+"src"+
					File.separator+"archivo.txt",Charset.forName("UTF8"));

			BufferedReader miMemoriaIntermedia= new BufferedReader(archivo);

			String linea="";

			while(linea!=null)
			{
				//Una linea es considerada hasta que encuentre un salto de linea \n o un retorno de carro \r 
				linea = miMemoriaIntermedia.readLine();

				if(linea!=null)
				{
					System.out.println(linea);
				}
			}

			archivo.close();

		} catch (IOException e) {
			System.out.println("No se ha encontrado el archivo");
		} 

	}	

	public int[] leeBytes()
	{
		int contadorBytes=0;
		int[] listaBytes=null;
		
		try {

			FileInputStream archivoBytes =new FileInputStream("C:"+File.separator+"Users"+File.separator+"Angel"+
					File.separator+"eclipse-workspace"+File.separator+"CursoJava"+File.separator+"src"+
					File.separator+"recuerdo.jpg");

			boolean finalArchivo=false;
			
			while(!finalArchivo) 
			{
				int byteEntrada=archivoBytes.read();
				
				if(byteEntrada==-1)
				{
					finalArchivo=true;
				}
				
				System.out.println(byteEntrada);
				contadorBytes++;
			}
			
			
			//-------------GUARDA----------------------------------------
			System.out.println(contadorBytes);
			
			archivoBytes =new FileInputStream("C:"+File.separator+"Users"+File.separator+"Angel"+
					File.separator+"eclipse-workspace"+File.separator+"CursoJava"+File.separator+"src"+
					File.separator+"recuerdo.jpg");
			listaBytes=new int[contadorBytes];
			contadorBytes=0;			
			finalArchivo=false;
			
			
			while(!finalArchivo) 
			{
				int byteEntrada=archivoBytes.read();
				
				if(byteEntrada!=-1)
				{
					listaBytes[contadorBytes]=byteEntrada;					
				}
				else
				{
					finalArchivo=true;
				}
				
				System.out.println("Indice: " + contadorBytes+ " Byte: "+listaBytes[contadorBytes]);
				contadorBytes++;
			}

			
			archivoBytes.close();

		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		} 
		
		
		return listaBytes;

	}



}
