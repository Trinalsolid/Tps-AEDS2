import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

class Filme{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    private String nome;  
    private String tituloOriginal; 
    private Date datalancamento; 
    private int duracao; 
    private String genero; 
    private String idiomaOriginal; 
    private String situacao;
    private float orcamento; 
    private String[] palavrasChave; 

    Filme(){
        nome = "";
        tituloOriginal = "";
        datalancamento = null;
        duracao = 0;
        genero = "";
        idiomaOriginal = "";
        situacao = "";
        orcamento = 0;
        palavrasChave = null;
    }

    Filme(String nome, String tituloOriginal, Date datalancamento, int duracao, String genero, String idiomaOriginal, String situacao
        , float orcamento, String[] palavrasChave){
        this.nome = nome;
        this.tituloOriginal = tituloOriginal;
        this.datalancamento = datalancamento;
        this.duracao = duracao;
        this.genero = genero;
        this.idiomaOriginal = idiomaOriginal;
        this.situacao = situacao;
        this.orcamento = orcamento;
        this.palavrasChave = palavrasChave;
    }

    //------------------------------------------------------CLONE---------------------------------------------------------------

    public Filme clone(){
        Filme copia = new Filme(this.nome, this.tituloOriginal, this.datalancamento, this.duracao, this.genero, this.idiomaOriginal
        , this.situacao, this.orcamento, this.palavrasChave);
        
        return copia;
    }

    //--------------------------------------------------------GET--------------------------------------------------------------------
    public Date getDatalancamento() {
        return datalancamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getGenero() {
        return genero;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public String getNome() {
        return nome;
    }

    public float getOrcamento() {
        return orcamento;
    }

    public String[] getPalavrasChave() {
        return palavrasChave;
    }

    public String getSituacao() {
        return situacao;
    }
    

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    //------------------------------------------------------SET----------------------------------------------------------------------
    public void setDatalancamento(Date datalancamento) {
        this.datalancamento = datalancamento;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    public void setPalavrasChave(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    //---------------------------------------------------------------------------------------------------------------------------------
            /*nome tituloOriginal dataLancamento duracao genero idiomaOriginal situacao orcamento [palavrasChave] */
    public void imprimir(){
        MyIO.print(this.nome + " " + this.tituloOriginal + " " + sdf.format(this.datalancamento) + " " + this.duracao  + " " + this.genero  + " " +
        this.idiomaOriginal + " " + this.situacao + " " + this.orcamento + " " );
        mostrarPalavraschaves();
    }

    public void mostrarPalavraschaves(){
        if(this.palavrasChave[0].contains("[") == false){
            MyIO.print("[");
            for(int i = 0; i < this.palavrasChave.length; i++){
                if(i < this.palavrasChave.length - 1){
                    MyIO.print(this.palavrasChave[i] + "," + " ");
                }else{
                    MyIO.println(this.palavrasChave[i] + "]");
                }
            }
        }else{
            MyIO.println(this.palavrasChave[0]);
        }
        
    }


    //Remove as tags presentes na String
    public String semtags(String str){
        String resp = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '<'){
                while(str.charAt(i) != '>'){
                    i++;
                }
                
            }else{
                resp = resp + str.charAt(i);
            }
                
        }
        return resp;
    }

    //remove caracteres indesejados entre ',' e ';' e coloca um espaco
    public String RemoverEntreVirgulas(String str){
        String resp = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ','){
                while(str.charAt(i) != ';'){
                    i++;
                }
                resp = resp + " ";
            }else{
                resp = resp + str.charAt(i);
            }
        }  
        return resp;
        
    }

    //Retorna o conteudo da String ate a abertura de um parenteses
    public String parentesesStop(String str){
        String resp = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                i = str.length();
            }else{
                resp = resp + str.charAt(i);
            }
        }
        return resp;
    }

    //Remove o conteudo da String que apartir do & ate chegar no ;
    public String ecomercialRemove(String str){
        String resp = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '&'){
                while(str.charAt(i) != ';')i++;
                i++;
            }else{
                resp = resp + str.charAt(i);
            }
        }
        return resp;
    }

    //Remove os espacos em branco entre as frases da String
    public String removeEspaco(String str){
        String resp = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != ' '){
                resp = resp + str.charAt(i);
            }
        }
        return resp;
    }

    //Troca os espacos em branco por virgulas
    public String trocaEspacoPorVirgula(String str){
        String resp = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                resp = resp + ',';
            }else{
                resp = resp + str.charAt(i);
            }
        }
        return resp;
    }



    //metodo que verifica se a String que contem tempo possui horas
    public boolean temHoras(String str){
        boolean resp = false;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == 'h'){
                resp = true;
                i = str.length();
            }
        }
        return resp;
    }

    //metodo que verifica se a String que contem tempo possui minutos
    public boolean temMinutos(String str){
        boolean resp = false;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == 'm'){
                resp = true;
                i = str.length();
            }
        }
        return resp;
    }
    //converte o tempo obtido em uma String(SEM ESPACO) para minutos e devolve esse resultado em um inteiro
    public int tempoEmMinutos(String str){
        //MyIO.println("STRING PRA TEMPO = " + str);
        int resp = 0;
        int m = 0; // minutos
        int h = 0; // horas
        int i = 0; //indice
        String horas = "";
        String minutos = "";
        if(temHoras(str) == false){
            
            while(str.charAt(i) != 'm'){
                minutos = minutos + str.charAt(i);
                i++;
            }
            
            resp = Integer.parseInt(minutos);
        
        }else{
            
            while(str.charAt(i) != 'h'){
                horas = horas + str.charAt(i);
                i++;
            }

            i++;
            if(temMinutos(str) == true){
                while(str.charAt(i) != 'm'){
                    minutos = minutos + str.charAt(i);
                    i++;
                }
            }else{
                minutos = "0";
            }
            

            //MyIO.println("CONVERTER M = " + minutos);
            //MyIO.println("CONVERTER H = " + horas);
            m = Integer.parseInt(minutos);
            h = Integer.parseInt(horas);
            resp = (h*60) + m;
        }
        
        return resp;
    }

    //verifica se a string possui titulo Original escrito;
    public boolean temTituloOriginal(String str){
        boolean resp = false;
        if(str.contains("tulo original")){
            resp = true;
        }

        return resp;
    }

    //remove Titulo original da String
    public String removeTituloOriginal(String str){
        String resp = "";
        if(temTituloOriginal(str) == true){
            for(int i = 17; i < str.length(); i++){
                resp = resp + str.charAt(i);
            }
        }else{
            resp = str;
        }
        return resp;
    }

    //verifica se a string possui Situacao escrito;
    public boolean temSituacao(String str){
        boolean resp = false;
        if(str.contains("Situa")){
            resp = true;
        }
        return resp;
    }

    //remove Situacao da String;
    public String removeSituacao(String str){
        String resp = "";
        if(temSituacao(str) == true){
            for(int i = 10; i < str.length(); i++){
                resp = resp + str.charAt(i);
            }
        }else{
            resp = str;
        }
        return resp;
    }
    
    //remove Idioma Original da String;
    public String removeIdiomaOriginal(String str){
        String resp = "";
        for(int i = 16; i < str.length(); i++){
            resp = resp + str.charAt(i);
        }

        return resp;
    }

    //remove Orcamento da String;
    public String removeOrcamento(String str){
        String resp = "";
        for(int i = 10; i < str.length(); i++){
            resp = resp + str.charAt(i);
        }

        return resp;
    }

    //remove $ da String;
    public String removeCifrao(String str){
        String resp = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != '$'){
                resp = resp + str.charAt(i);
            }
        }
        return resp;
    }

    //remove virgula da String;
    public String removeVirgula(String str){
        String resp = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != ','){
                resp = resp + str.charAt(i);
            }
        }
        return resp;
    }

    //converte a String para um valor float, caso o valor seja '-' o orcamento eh zero
    public float converterOrcamentoParaValor(String str){
        float resp;
        if(str.contains("-")){
            resp = 0;
        }else{
            resp = Float.parseFloat(str);
        }
        return resp;
    }

    public void ler(String entrada) throws Exception{
        //Abrir o Arquivo para leitura
        Arq.openRead("/tmp/filmes/"+entrada);
        //D:\\RPG\\Filmes\\ /tmp/filmes/ C:\\Users\\WazX\\Desktop\\Filmes\\
        String linha = " ";
        linha = Arq.readLine();
        
        //------------------------------------NOME-----------------------------------
        //ler ate <title> para obter o nome
        while(!linha.contains("<title>")){
            linha = Arq.readLine();
        }
        //remover () & e tags e espaco caso seja necessario e dar set no nome
        linha = ecomercialRemove(parentesesStop(semtags(linha))).trim();
        setNome(linha);

        //---------------------------------DATA-------------------------------------
        //ler ate <span class="release"> para obter data
        while(!linha.contains("<span class=\"release\">")){
            linha = Arq.readLine();
        }
        linha = parentesesStop(Arq.readLine().trim());
        setDatalancamento(sdf.parse(linha));

        //------------------------------GENERO--------------------------------------
        //ler ate <a href= para obter genero
        while(!linha.contains("<a href=")){
            linha = Arq.readLine();
        }
        linha = trocaEspacoPorVirgula(ecomercialRemove(RemoverEntreVirgulas(semtags(linha).trim())));
        setGenero(linha);

        //---------------------------DURACAO----------------------------------------
        //ler ate <span class="runtime"> para obter duracao
        int tempo = 0;
        while(!linha.contains("<span class=\"runtime\">")){
            linha = Arq.readLine();
        }
        String aux = linha;
        while(!aux.contains("</span>")){
            if(aux.contains("h") || aux.contains("m")){
                linha = aux.trim();
                aux = Arq.readLine();
            }else{
                aux = Arq.readLine();
            }
        }
        //transformar a String obtida de tempo em minutos(tipo int)
        tempo = tempoEmMinutos(removeEspaco(linha));
        setDuracao(tempo);

        //------------------------------TITULO ORIGINAL-------------------------------------------------------//
        //ler ate a primeira tag strong
        while(!linha.contains("<strong>")){
            linha = Arq.readLine();
        }
        
        //como existem html que nao possui titulo original, eh necessario verificar 
        //se nao tem estamos na linha de situacao e o titulo original eh o proprio nome
        if(linha.contains("original")){
            linha = semtags(linha).trim();
            aux = removeTituloOriginal(linha);
            setTituloOriginal(aux);
        }else{
            setTituloOriginal(getNome());
        }

        //-----------------------------------------SITUACAO--------------------------------------------------//
        //verifica se estamos na linha que contem titulo original ou na linha que ja esta em situacao
        //caso seja na linha do titulo original, caminhamos ate a linha que contem situacao
        if(linha.contains("original")){
            while(!linha.contains("Situa")){
                linha = Arq.readLine();
            }
            linha = semtags(linha).trim();
            linha = removeSituacao(linha).trim();
        //caso nao, ja estamos na linha certa, basta tratar
        }else{
            linha = semtags(linha).trim();
            linha = removeSituacao(linha).trim();
        }
        setSituacao(linha);

        //------------------------------------------IDIOMA ORIGINAL-------------------------------------------//
        //ir ate a linha que contem idioma original e tratar
        while(!linha.contains("Idioma original")){
            linha = Arq.readLine();
        }
        linha = semtags(linha).trim();
        linha = removeIdiomaOriginal(linha).trim();
        setIdiomaOriginal(linha);

        //------------------------------------------- ORCAMENTO -------------------------------------
        //a proxima linha eh orcamento
        float valor = 0;
        linha = Arq.readLine();
        linha = semtags(linha).trim();
        linha = removeVirgula(removeCifrao(removeOrcamento(linha))).trim();
        valor = converterOrcamentoParaValor(linha);
        setOrcamento(valor);
        
        //------------------------------------------PALAVRAS CHAVES----------------------------------

        //ler ate Palavras-chave e depois ate </ul> pegando apenas as linhas que contem <li>
        String[] temp = new String[100];
        int count = 0;
        //ir ate a linha Palavras-chave
        while(!linha.contains("Palavras-chave")){
            linha = Arq.readLine();
        }
        linha = Arq.readLine();
        linha = Arq.readLine();
        //ir pegar apenas as linhas que tem <li>
        if(linha.contains("Nenhuma palavra-chave foi adicionada")){
            temp[count] = "[]";
            setPalavrasChave(temp);
            
        }else{
            while(!linha.contains("</ul>")){
                if(linha.contains("<li>")){
                    temp[count] = semtags(linha).trim();
                    linha = Arq.readLine();
                    count++;
                }else{
                    linha = Arq.readLine();
                }
            }
            String[] pchave = new String[count];
            for(int i = 0; i < count; i++){
                pchave[i] = temp[i];
            }
            setPalavrasChave(pchave);
        }
        //MyIO.println("FILME SETADO = " + getNome());
        
        
        Arq.close();
    }

    
}


class No {
	public Filme elemento; // Conteudo do no.
	public No esq, dir; // Filhos da esq e dir.
	public int nivel; // Numero de niveis abaixo do no

	/**
	 * Construtor da classe
	 * @param elemento Conteudo do no.
	 */
	public No(Filme elemento) {
		this(elemento, null, null, 1);
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq      No da esquerda.
	 * @param dir      No da direita.
	 */
	public No(Filme elemento, No esq, No dir, int nivel) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
		this.nivel = nivel;
	}

	/**
	 * C?lculo do n?mero de n?veis a partir de um v?rtice
	 */
	public void setNivel() {
		this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
	}

	/**
	 * Retorna o n?mero de n?veis a partir de um v?rtice
	 * @param no n? que se deseja o n?vel.
	 */
	public static int getNivel(No no) {
		return (no == null) ? 0 : no.nivel;
	}
}

class AVL {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public AVL() {
		raiz = null;
	}

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir, <code>false</code> em caso
	 *         contrario.
	 */
	public boolean pesquisar(String x) {
        MyIO.print("raiz ");
		return pesquisar(x, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir, <code>false</code> em caso
	 *         contrario.
	 */
	private boolean pesquisar(String x, No i) {
		boolean resp;
		if (i == null) {
			resp = false;
		} else if (x.compareTo(i.elemento.getTituloOriginal()) == 0) {
			resp = true;
		} else if (x.compareTo(i.elemento.getTituloOriginal()) < 0) {
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
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento + "(fator " + (No.getNivel(i.dir) - No.getNivel(i.esq)) + ") "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
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
	public void inserir(Filme x) throws Exception {
		raiz = inserir(x, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	private No inserir(Filme x, No i) throws Exception {
		if (i == null) {
			i = new No(x);
		} else if (x.getTituloOriginal().compareTo(i.elemento.getTituloOriginal()) < 0) {
			i.esq = inserir(x, i.esq);
		} else if (x.getTituloOriginal().compareTo(i.elemento.getTituloOriginal()) > 0) {
			i.dir = inserir(x, i.dir);
		} else {
			throw new Exception("Erro ao inserir!");
		}
		return balancear(i);
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
			throw new Exception("Erro ao remover!");
		} else if (x.compareTo(i.elemento.getTituloOriginal()) < 0) {
			i.esq = remover(x, i.esq);
		} else if (x.compareTo(i.elemento.getTituloOriginal()) > 0) {
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
		return balancear(i);
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

	private No balancear(No no) throws Exception {
		if (no != null) {
			int fator = No.getNivel(no.dir) - No.getNivel(no.esq);
			// Se balanceada
			if (Math.abs(fator) <= 1) {
				no.setNivel();
			// Se desbalanceada para a direita
			} else if (fator == 2) {
				int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);
				// Se o filho a direita tambem estiver desbalanceado
				if (fatorFilhoDir == -1) {
					no.dir = rotacionarDir(no.dir);
				}
				no = rotacionarEsq(no);
			// Se desbalanceada para a esquerda
			} else if (fator == -2) {
				int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);
				// Se o filho a esquerda tambem estiver desbalanceado
				if (fatorFilhoEsq == 1) {
					no.esq = rotacionarEsq(no.esq);
				}
				no = rotacionarDir(no);
			} else {
				throw new Exception(
					"Erro no No(" + no.elemento + ") com fator de balanceamento (" + fator + ") invalido!");
			}
		}
		return no;
	}

	private No rotacionarDir(No no) {
		//System.out.println("Rotacionar DIR(" + no.elemento + ")");
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;

		noEsq.dir = no;
		no.esq = noEsqDir;
		no.setNivel(); // Atualizar o nivel do no
		noEsq.setNivel(); // Atualizar o nivel do noEsq

		return noEsq;
	}

	private No rotacionarEsq(No no) {
		//System.out.println("Rotacionar ESQ(" + no.elemento + ")");
		No noDir = no.dir;
		No noDirEsq = noDir.esq;

		noDir.esq = no;
		no.dir = noDirEsq;

		no.setNivel(); // Atualizar o nivel do no
		noDir.setNivel(); // Atualizar o nivel do noDir
		return noDir;
	}
}

public class questao3 {

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }
  
     public static AVL comando(String a , AVL d) throws Exception{
        AVL resp = d;
        String[] split = new String[2];
        Filme aux2 = new Filme();
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
        AVL arvoreAVL = new AVL();
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
           Filme aux = new Filme();
           try {
              aux.ler(entrada[i]);
              arvoreAVL.inserir(aux);
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
           arvoreAVL = comando(entrada[numentrada] , arvoreAVL);
        }
        // le a terceira parte e faz as pesquisas ate o FIM
        String entrada2 = "";
        boolean testemp = false;
        do{
              entrada2 = entrada1.nextLine();
              MyIO.println(entrada2);
              if(isFim(entrada2) == false){
                  testemp = arvoreAVL.pesquisar(entrada2);
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
