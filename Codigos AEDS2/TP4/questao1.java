
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Thiago de Campos Ribeiro Nolasco
 */
class Film {
    // Attributes
    private String name;
    private String ogTitle;
    private Date releaseDate;
    private Integer duration;
    private String genre;
    private String ogLanguage;
    private String situation;
    private Float budget;
    private String[] arrKeyWds;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Constructors
    public Film() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * @param name
     * @param ogTitle
     * @param releaseDate
     * @param duration
     * @param genre
     * @param ogLanguage
     * @param situation
     * @param budget
     */
    public Film(String name, String ogTitle, Date releaseDate, Integer duration, String genre, String ogLanguage, String situation, Float budget) {
        this.name = name;
        this.ogTitle = ogTitle;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.genre = genre;
        this.ogLanguage = ogLanguage;
        this.situation = situation;
        this.budget = budget;
        this.arrKeyWds = null;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOgTitle() {
        return ogTitle;
    }

    public void setOgTitle(String ogTitle) {
        this.ogTitle = ogTitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOgLanguage() {
        return ogLanguage;
    }

    public void setOgLanguage(String ogLanguage) {
        this.ogLanguage = ogLanguage;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public String[] getArrKeyWds() {
        return arrKeyWds;
    }

    public void setArrKeyWds(String[] arrKeyWds) {
        this.arrKeyWds = arrKeyWds;
    }

    public Film clone(){
        Film cloned = new Film();

        cloned.name = this.name;
        cloned.ogTitle = this.ogTitle;
        cloned.releaseDate = this.releaseDate;
        cloned.duration = this.duration;
        cloned.genre = this.genre;
        cloned.ogLanguage = this.ogLanguage;
        cloned.situation = this.situation;
        cloned.budget = this.budget;
        cloned.arrKeyWds = this.arrKeyWds;

        return cloned;
    }


    /**
     * @param fileName
     */
    public void ler(String fileName){
        // Getting the right path for each read file
        String path = "/tmp/filmes/" + fileName;
        //D:\\RPG\\Filmes\\ /tmp/filmes/ C:\\Users\\WazX\\Desktop\\Filmes\\

        // Method that will split chunks of the read HTML and will assign the value to each Film's attribute
        splittingString(path);
    }

    private void splittingString(String path){
      // Data declaration
      String line = "";

      try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))) {

          // Film name
          while(!reader.readLine().contains("title ott"));
          while(!reader.readLine().contains("h2"));
          this.name = removeTags(reader.readLine().trim());

          // Film release date
          while(!reader.readLine().contains("\"release\""));
          this.releaseDate = sdf.parse(removeTags(reader.readLine().trim()));

          // Film genre
          while(!reader.readLine().contains("genres"));
              // In this case, will use "line" because the last readLine will have the content that we want
          while(!(line = reader.readLine()).contains("<a href"));
          this.genre = removeTags(line).trim();

          // Film duration
          while(!reader.readLine().contains("runtime"));
          reader.readLine(); // Needed because an empty line was found
          this.duration = hoursToMinutes(reader.readLine().trim());

          // Film original title (if there is) & situation
          this.ogTitle = this.name;
          while( !(line = reader.readLine()).contains("Situação</bdi>") ) {
              if(line.contains("Título original")){
                  this.ogTitle = removeTags(line.replace("Título original", " ")).trim();
              }
          }
          this.situation = removeTags(line.replace("Situação", " ")).trim();

          // Film original language
          while( !(line = reader.readLine()).contains("Idioma original</bdi>") );
          this.ogLanguage = removeTags(line.replace("Idioma original", " ")).trim();

          // Film budget
          while( !(line = reader.readLine()).contains("Orçamento</bdi>") );
          String aux = removeTags(line.replace("Orçamento", " ")).trim();
          this.budget = (aux.equals("-")) ? 0.0F : convertBudget(aux);

          // Film key-words
          line = "";
          while( !reader.readLine().contains("Palavras-chave</bdi>") );
          while( !(line += reader.readLine().trim() + " ").contains("</ul>") );
          if(!line.contains("Nenhuma palavra-chave foi adicionada")){
              arrKeyWds = removeTags(line).trim().split("  ");
          }


      } catch (FileNotFoundException e){
          System.out.println("File not found");
      } catch (IOException e){
          System.out.println("File cannot be read");
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

    /**
     * Receives a line that contains an HTML content and removes its tags
     * @param line
     * @return
     */
    private String removeTags(String line){
        // Data declaration
        String resp = "";
        int i = 0;

        /*
           The main idea here is to check if the char is equals to '<', if it's, it means that an HTML tag has opened
           So, CAN'T read anything until the tag is closed, '>' is found.

           It's also checking if any HTML special character (&....;) or if any "()" is found
           IF found, don't read anything until it has ended.
         */
        while (i < line.length()) {
            if (line.charAt(i) == '<') {
                i++;
                while (line.charAt(i) != '>') i++;
            }else {
                resp += line.charAt(i);
            }
            i++;
        }
        // Returning cleaned line
        return resp.replace("&nbsp;", "");
    }

    /**
     * Receives a String that contains hours, and convert it to minutes (Integer)
     * @param value
     * @return
     */
    private int hoursToMinutes(String value){
        // Data declaration
        int result = 0, minutes = 0;

        String[] splitValue = value.split(" ");
        if(splitValue.length > 1) {
            int hour = Integer.parseInt(removeLetters(splitValue[0]));
            minutes = Integer.parseInt(removeLetters(splitValue[1]));
            result = (60 * hour) + minutes;
        } else {
            if(splitValue[0].contains("h")){
                minutes = Integer.parseInt(removeLetters(splitValue[0]))*60;
            } else {
                minutes = Integer.parseInt(removeLetters(splitValue[0]));
            }
            result = minutes;
        }
        return result;
    }

    /**
     * Receives a String that contains hours, and leave only the numbers (ex: 1h 49m = 1 49)
     * @param value
     * @return
     */
    private String removeLetters(String value){
        // Data declaration
        String result = "";

        for(int i = 0; i < value.length(); i++){
            // If char is a number, a blank space, or a '.' (Used on convertBudget), will be stored into "result"
            if( (value.charAt(i) >= 48 && value.charAt(i) <= 57) || value.charAt(i) == ' ' || value.charAt(i) == '.')
                result += value.charAt(i);
        }
        return result;
    }

    /**
     * Receives a String that contains a FLOAT number, and converts it to a FLOAT number
     * (PS: It's necessary to remove few characters because String has ',' on it)
     * @param value
     * @return
     */
    private Float convertBudget(String value){
        return Float.parseFloat(removeLetters(value));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(name);
        sb.append(" ").append(ogTitle);
        sb.append(" ").append(sdf.format(getReleaseDate()));
        sb.append(" ").append(duration);
        sb.append(" ").append(genre);
        sb.append(" ").append(ogLanguage);
        sb.append(" ").append(situation);
        sb.append(" ").append(budget);
        sb.append(" ").append(arrKeyWds == null ? "[]" : Arrays.asList(arrKeyWds).toString());
        return sb.toString();
    }

    public void imprimir(){
        System.out.println(this.toString());
    }
}


class No {
   public Film elemento; // Conteudo do no.
   public No esq, dir;  // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     */
   public No(Film elemento) {
        this(elemento, null, null);
   }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
   public No(Film elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
   }
}

class ArvoreBinaria{
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public ArvoreBinaria() {
		raiz = null;
	}

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String x) {
        MyIO.print("=>raiz ");
		return pesquisar(x, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(String x, No i) {
      boolean resp;
		if (i == null) {
         resp = false;
      } else if (x.compareTo(i.elemento.getOgTitle()) == 0) {
         resp = true;


      } else if (x.compareTo(i.elemento.getOgTitle()) < 0) {
        MyIO.print("esq "); 
        resp = pesquisar(x, i.esq);
         

      } else {
        MyIO.print("dir "); 
        resp = pesquisar(x, i.dir);
         
      }
      return resp;
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharCentral() {
		System.out.print("In..: ");
		caminharCentral(raiz);
		System.out.println("\t");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.println(i.elemento.getOgTitle() + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPre() {
		System.out.print("Pre.: ");
		caminharPre(raiz);
		System.out.println("\t");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPos() {
		System.out.print("Post: ");
		caminharPos(raiz);
		System.out.println("\t");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + " "); // Conteudo do no.
		}
	}


	/**
	 * Metodo publico iterativo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Film x) throws Exception {
		raiz = inserir(x, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	private No inserir(Film x, No i) throws Exception {
		if (i == null) {
         i = new No(x);

      } else if (x.getOgTitle().compareTo(i.elemento.getOgTitle()) < 0) {
         i.esq = inserir(x, i.esq);

      } else if (x.getOgTitle().compareTo(i.elemento.getOgTitle()) > 0) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }
		return i;
	}

	/**
	 * Metodo publico para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserirPai(Film x) throws Exception {
      if(raiz == null){
         raiz = new No(x);
      } else if(x.getOgTitle().compareTo(raiz.elemento.getOgTitle()) < 0){
		   inserirPai(x, raiz.esq, raiz);
      } else if(x.getOgTitle().compareTo(raiz.elemento.getOgTitle()) > 0){
		   inserirPai(x, raiz.dir, raiz);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}

	/**
	 * Metodo privado recursivo para inserirPai elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @param pai No superior ao em analise.
	 * @throws Exception Se o elemento existir.
	 */
	private void inserirPai(Film x, No i, No pai) throws Exception {
		if (i == null) {
         if(x.getOgTitle().compareTo(pai.elemento.getOgTitle()) < 0){
            pai.esq = new No(x);
         } else {
            pai.dir = new No(x);
         }
      } else if (x.getOgTitle().compareTo(i.elemento.getOgTitle()) < 0) {
         inserirPai(x, i.esq, i);
      } else if (x.getOgTitle().compareTo(i.elemento.getOgTitle()) > 0) {
         inserirPai(x, i.dir, i);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}


	/**
	 * Metodo publico iterativo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @throws Exception Se nao encontrar elemento.
	 */
	public void remover(String x) throws Exception {
		raiz = remover(x, raiz);
	}

	/**
	 * Metodo privado recursivo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private No remover(String x, No i) throws Exception {

		if (i == null) {
         //throw new Exception("Erro ao remover!");

      } else if (x.compareTo(i.elemento.getOgTitle()) < 0) {
         i.esq = remover(x, i.esq);

      } else if (x.compareTo(i.elemento.getOgTitle()) > 0) {
         i.dir = remover(x, i.dir);

      // Sem no a direita.
      } else if (i.dir == null) {
         i = i.esq;

      // Sem no a esquerda.
      } else if (i.esq == null) {
         i = i.dir;

      // No a esquerda e no a direita.
      } else {
         i.esq = maiorEsq(i, i.esq);
		}

		return i;
	}

	/**
	 * Metodo para trocar o elemento "removido" pelo maior da esquerda.
	 * @param i No que teve o elemento removido.
	 * @param j No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
	private No maiorEsq(No i, No j) {

      // Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

      // Existe no a direita.
		} else {
         // Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}

	/**
	 * Metodo que retorna o maior elemento da árvore
	 * @return int maior elemento da árvore
	 */
   /*public int getMaior(){
      int resp = -1;

      if(raiz != null){
         No i;
         for(i = raiz; i.dir != null; i = i.dir);
         resp = i.elemento;
      }

      return resp;
   }*/


	/**
	 * Metodo que retorna o menor elemento da árvore
	 * @return int menor elemento da árvore
	 */
   /*public int getMenor(){
      int resp = -1;

      if(raiz != null){
         No i;
         for(i = raiz; i.esq != null; i = i.esq);
         resp = i.elemento;
      }

      return resp;
   }*/


	/**
	 * Metodo que retorna a altura da árvore
	 * @return int altura da árvore
	 */
   public int getAltura(){
      return getAltura(raiz, 0);
   }


	/**
	 * Metodo que retorna a altura da árvore
	 * @return int altura da árvore
	 */
   public int getAltura(No i, int altura){
      if(i == null){
         altura--;
      } else {
         int alturaEsq = getAltura(i.esq, altura + 1);
         int alturaDir = getAltura(i.dir, altura + 1);
         altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
      }
      return altura;
   }


	/**
	 * Metodo publico iterativo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @throws Exception Se nao encontrar elemento.
	 */
	public void remover2(Film x) throws Exception {
      if (raiz == null) {
         throw new Exception("Erro ao remover2!");
      } else if(x.getOgTitle().compareTo(raiz.elemento.getOgTitle()) < 0){
         remover2(x, raiz.esq, raiz);
      } else if (x.getOgTitle().compareTo(raiz.elemento.getOgTitle()) > 0){
         remover2(x, raiz.dir, raiz);
      } else if (raiz.dir == null) {
         raiz = raiz.esq;
      } else if (raiz.esq == null) {
         raiz = raiz.dir;
      } else {
         raiz.esq = maiorEsq(raiz, raiz.esq);
      }
   }

	/**
	 * Metodo privado recursivo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @param i No em analise.
	 * @param pai do No em analise.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private void remover2(Film x, No i, No pai) throws Exception {
		if (i == null) {
         throw new Exception("Erro ao remover2!");
      } else if (x.getOgTitle().compareTo(raiz.elemento.getOgTitle()) < 0) {
         remover2(x, i.esq, i);
      } else if (x.getOgTitle().compareTo(raiz.elemento.getOgTitle()) > 0) {
         remover2(x, i.dir, i);
      } else if (i.dir == null) {
         pai = i.esq;
      } else if (i.esq == null) {
         pai = i.dir;
      } else {
         i.esq = maiorEsq(i, i.esq);
		}
	}

   public Film getRaiz() throws Exception {
      return raiz.elemento;
   }

   public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
      return igual(a1.raiz, a2.raiz);
   }

   private static boolean igual (No i1, No i2){
      boolean resp;
      if(i1 != null && i2 != null){
         resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
      } else if(i1 == null && i2 == null){
         resp = true;
      } else {
         resp = false; 
      }
      return resp;
   }
   
}

public class questao1{
   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static ArvoreBinaria comando(String a , ArvoreBinaria d) throws Exception{
      ArvoreBinaria resp = d;
      String[] split = new String[2];
      Film aux2 = new Film();
      split = a.split(" " , 2);
      String operacao = split[0];
      String filme = split[1];
      

      switch(operacao){
			case "I":
            aux2.ler(filme);
				resp.inserir(aux2);
				break;
			case "R":
                //MyIO.println(filme);
				resp.remover(filme);
				break;
		}
        return resp;
	}

   public static void main(String[] args) throws Exception{
      ArvoreBinaria arvoreB = new ArvoreBinaria();
      String[] entrada = new String[1000];
      int numentrada = 0;
      String aux2 = "";
      String leitura ,parte1 , parte2;

      Scanner entrada1 = new Scanner(System.in);

      do {
        entrada[numentrada] = entrada1.nextLine();
         //MyIO.println(entrada[numentrada]);
         aux2 = entrada[numentrada];
         numentrada++;
      } while (isFim(aux2) == false);
      numentrada--;
      //MyIO.println("END");
      for(int i=0;i<numentrada;i++){
         Film aux = new Film();
         try {
            aux.ler(entrada[i]);
            arvoreB.inserir(aux);
         } catch (Exception e) {
         }
      }
      numentrada++;

      //le a segunda parte da entrada

      entrada[numentrada] = entrada1.nextLine();
      //MyIO.println(entrada[numentrada]);
      int tmp = numentrada;
      int movimentacoes = Integer.parseInt(entrada[numentrada]);
      for(int i = 0; i < movimentacoes; i++){
         entrada[numentrada] = entrada1.nextLine();
         //MyIO.println(entrada[numentrada]);
         arvoreB = comando(entrada[numentrada] , arvoreB);
      }
      // le a terceira parte e faz as pesquisas ate o FIM
      String entrada2 = "";
      boolean testemp = false;
      do{
            entrada2 = entrada1.nextLine();
            MyIO.println(entrada2);
            if(isFim(entrada2) == false){
                testemp = arvoreB.pesquisar(entrada2);
                if(testemp == false){
                    MyIO.println("NAO");
                }else{
                    MyIO.println("SIM"); 
                }
            }
      }while(isFim(entrada2) == false);
        //arvoreB.caminharCentral();      
      
      entrada1.close();

   }

}
