
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

//hash direto com reserva 

class HashDiretoReserva {

    private Filme[] filme;
    int m1, m2, m, reserva;
    private int n;
    
    public HashDiretoReserva(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
         filme = new Filme[m];
         for (int i = 0; i < m1; i++) {
            filme[i] = new Filme();
        }
        reserva = 0;
        n = 0;
    }
    
    public HashDiretoReserva() {
        this(21, 9);
    }

    public int h(int elemento) {
        return elemento % m;
     }
    
    public int hash(String elemento) {
        int num = 0;
        for (int i = 0; i < elemento.length(); i++) {
            num += elemento.charAt(i);
        }
        return num % this.m1;
    }
    
    /**
     * Retorna o tamanho total da lista
     * @return
     */
    public int getTamanho() {
        return filme.length;
    }
    
    public boolean inserir(Filme elemento) throws Exception {
        if (n >= this.m) {
            throw new Exception("ERRO");
        }
        boolean resp = false;
        if (elemento != null) {
            int pos = hash(elemento.getTituloOriginal());
            if (filme[pos] == null) {
                filme[pos] = elemento;
                resp = true;
                n++;
            } else if (reserva < m2) {
                filme[m1 + reserva] = elemento;
                reserva++;
                resp = true;
                n++;
            }
        }
        return resp;
    }

    Filme remover(Filme elemento) throws Exception {
        if (n == 0) {
            throw new Exception("ERRO");
        }
        Filme filmes = filme[0];
        return filmes;
    }

    public boolean pesquisar(String linha) {
        boolean resp = false;
        int pos = hash(linha);
    
        if(filme[pos].getTituloOriginal().equals(linha)) {
            resp = true;
        } else if (filme[pos] != null) {
            for (int i = 0; i < reserva; i++) {
                if (filme[m1 + i].getTituloOriginal().equals(linha)) {
                    resp = true;
                    i = reserva;
                }
            }
        }
        return resp;
    }
    }


public class questao5{
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception{
        HashDiretoReserva hashreservadireta = new HashDiretoReserva();
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
                hashreservadireta.inserir(aux);
            } catch (Exception e) {
            }
        }
        numentrada++;

        // le a segunda parte at? o fim
        String entrada2 = "";
        boolean testemp = false;
        do{
            entrada2 = entrada1.nextLine();
            if(isFim(entrada2) == false){
                MyIO.println("=> "+entrada2);
                testemp = hashreservadireta.pesquisar(entrada2);
                if(testemp == false){
                    System.out.println("NAO");
                }else{
                    System.out.println("SIM"); 
                }
             }
        }while(isFim(entrada2) == false);
            //arvoreB.caminharCentral();      
        
        entrada1.close();

    }

}
