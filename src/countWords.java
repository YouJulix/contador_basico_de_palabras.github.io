//import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;;



public class countWords {
	public static void main(String args[]) {
//		contDoc("data.txt");		
		try{
			contDoc(args[0]);
		}catch(Exception e){
			System.out.println("No ingresaste un nombre de archivo");	
		}
	}
	
	
	public static void contDoc(String file){		
		HashMap<String,Integer> allWords = new HashMap<String,Integer>(); //HashMap con todas las palabras
		String word; //aux para keys del HashMap
		Integer count;  //aux para values del HashMap
		Integer countAllWords=0;
		Integer countAllAux=0;

		Scanner sc = null; //Scanner para leer de file
		
		
					//antes GUARDAR EN UNA LISTA LOS DELIMITADORES
		
		List <String> delimitador =new ArrayList<String>(); //lista que contendrá mis delimitadores
			
		Scanner sdelim=null;
		try {
			sdelim = new Scanner(new FileReader("delimitadores.txt"));
			while(sdelim.hasNext()){
				delimitador.add(sdelim.next());
			}
		} catch (FileNotFoundException e2) {
			System.out.println("Archivo \"delimitadores.txt\" no encontrado");
		}finally{
			sdelim.close();
		}			
	
				//antes GUARDAR EN HASHSET STOPWORDS		
		//HashSet
		HashSet<String> stopWords = new HashSet<String>();
		
		Scanner read = null;
		String connectors ="stopwords-spanish.txt";		
		try {
			read = new Scanner(new File(connectors));
			
			while(read.hasNextLine()){
				stopWords.add(read.nextLine());
			}
		} catch (FileNotFoundException e1) {
			System.out.println("Archivo \"stopwords-spanish.txt\" no encontrado");
		}finally{
			read.close();
		}
			
									//LEER DE file	
		
		try {
			sc = new Scanner(new File(file));
			
			String delimitadores="\\p{javaWhitespace}+"; //mi primer delimitador es el espacio
			for(String d : delimitador){
				delimitadores+="|"; //agrego los delimitadores extra aparte del espacio que estan en mi lista "delimitadores"
				delimitadores+=d;  				
			}
			
			sc.useDelimiter(delimitadores);
			
			while(sc.hasNext()){
				word = sc.next().toLowerCase();//.trim(); trim quita todos los espacios a String 
									//toLowerCase , convierte todas las mayusculas a minusculas
				//System.out.println(word);
				if(word.equals("")){
				}else{
					countAllWords++;
					if(stopWords.contains(word) == true){
						countAllAux++;

					} //else{
				//if(word.equals("") || (stopWords.contains(word)== true)){ //agrego que cheque si la word leida es una stopword, en ese caso no guardarla 
																			//el HashSet recibe objetos, por eso no hay problemas en la comparacion
									
				//}else{										
					if(allWords.get(word) == null){ //si no esta la palabra en el hashmap							
						allWords.put(word,1);
					}else{
						count = allWords.get(word);	//sino obtengo su num de repeticiones
						count++;
						allWords.put(word, count);
					}
					//}
				}
			}
			
			System.out.println("\n 1. Total de palabras: "+ countAllWords); //escribir en consola

			BufferedWriter bw = null;
			
			try{
				bw = new BufferedWriter(new FileWriter("reporte.palabras.txt")); 
				bw.write("\n 1. Total de palabras: "+countAllWords +"\n"); //On file  //"\n" ya que elmétodo "write()" no incluye el salto de línea de "...println();"
			}catch(Exception e){
				System.out.println("Algo ocurrió, no se ha podido escribir el archivo");
			}
			//aún no cerramos el archivo
			System.out.println(" 2. Total de palabras sin auxiliares: "+ (countAllWords - countAllAux) ); //Console
			
			
			try {
				bw.write(" 2. Total de palabras sin auxiliares: "+(countAllWords - countAllAux)+"\n"); //On file
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				bw.write(" 3. 10 palabras más comunes: \n"); //ON file
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			System.out.println(" 3. 10 palabras más comunes: "); //Console
			topWords(allWords,bw); //Mando a llamar a mi funcion para sacar el top 10
			
			
			
			// MANDAR A IMPRIMIR TODAS LAS PALABRAS ...
			try{
				bw.write("\n ---------------------------------------------------"
						+ "\n\tCONTEO DE TODAS LAS PALABRAS\n\n Palabra\t\t\t iteracion\n\n");
			}catch(IOException e){
				e.printStackTrace();
			}

			for(String key : allWords.keySet()){
				try{
					bw.write(" " + key+"\t\t\t\t "+allWords.get(key)  + "\n");
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo "+file+" no encontrado en el mismo directorio"); 
		}finally{
			try{
				sc.close();
			}catch(Exception e){
				System.out.println("¡Excepcion!");
			}
		}
	}
	
	public static void topWords(HashMap<String,Integer> allWords, BufferedWriter bw){ //mando el apuntador de bw para escribir aquí
		List<Element> listSorted =new ArrayList<Element>();		
		
		
		for(String key : allWords.keySet()){
			Integer c = allWords.get(key);
			Element e = new Element(key,c);
			
			int aux=0;
			
			if(listSorted.size() == 0){ //si no hay elementos en la lista 
				listSorted.add(e);
			}else{
				for(int i=0;i<listSorted.size();i++){ //buscar su lugar en la lista
					aux = (listSorted.get(i)).getCountWord();
					if(c > aux){ //si encuentra su pocisión, se agrega y termina el for
						listSorted.add(i,e); 
						aux=-1;
						i=listSorted.size(); 
					}
				}
				if(aux != -1){ //señal de que recorrio hasta el final y no hay num mas pequeño que este
					listSorted.add(e);
				}
				
			}
		}
		System.out.println("\n\t\t------- TOP 10 ------\n");
		
		try {
			bw.write("\n\t\t------- TOP 10 ------\n\n"+
					"\tPalabra\t\t\t\tInteracion\n\n"); //On file
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("\tPalabra\t\t\t\tInteracion\n");
		for(int i=0;(i<listSorted.size() && i<=9);i++){ //imprime las primeras 10 o hasta que la lista se vacie en caso de que el archivo tenga menos de 10 palabras
			System.out.println("\t" + (i+1) +")" +listSorted.get(i).getWord() + "\t\t\t\t " +listSorted.get(i).getCountWord());
			try {
				bw.write("\t" + (i+1) +")" +listSorted.get(i).getWord() + "\t\t\t\t " +listSorted.get(i).getCountWord()+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Para mejor visualizacion en consola imprimo un último salto de línea
		System.out.println("");
		
		try{
			bw.write("\n");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}		
	
}


class Element{
	String word;
	Integer countWord;
	
	Element(){		
	}
	
	Element(String word,Integer countWord){
		this.word =word;
		this.countWord = countWord;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getCountWord() {
		return countWord;
	}

	public void setCountWord(Integer countWord) {
		this.countWord = countWord;
	}
}
