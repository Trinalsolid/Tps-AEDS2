import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

class Filmes {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String nome;
    private String titulooriginal;
    private Date datalancamento;
    private int duracao;
    private String genero;
    private String idioma;
    private String situacao;
    private float orcamento;
    private String palavrachave[];
    
    //Construtores

    Filmes(){
        nome = "";
        titulooriginal = "";
        datalancamento = new Date();
        duracao = 0;
        genero = "";
        idioma = "";
        situacao = "";
        orcamento = 0;
        palavrachave = new String[100];
    }

    Filmes(String nome,String titulooriginal , Date datalancamento,int duracao,String genero,String idioma,String situacao,float orcamento, String[] palavrachave){
        this.nome = nome;
        this.titulooriginal = titulooriginal;
        this.datalancamento = datalancamento;
        this.duracao = duracao;
        this.genero = genero;
        this.idioma = idioma;
        this.situacao = situacao;
        this.orcamento = orcamento;
        this.palavrachave = palavrachave;
    }

    //metodos de GET //

    public String getNome() {
        return nome;
    }
    public String getTitulooriginal() {
        return titulooriginal;
    }
    public Date getDatalancamento() {
        return datalancamento;
    }
    public int getDuracao() {
        return duracao;
    }
    public String getGenero() {
        return genero;
    }
    public String getIdioma() {
        return idioma;
    }
    public String getSituacao() {
        return situacao;
    }
    public float getOrcamento() {
        return orcamento;
    }
    public String[] getPalavrachave() {
        return palavrachave;
    }

    //Metodos de SET //

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTitulooriginal(String titulooriginal) {
        this.titulooriginal = titulooriginal;
    }
    public void setDatalancamento(Date datalancamento) {
        this.datalancamento = datalancamento;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }
    public void setPalavrachave(String[] palavrachave) {
        this.palavrachave = palavrachave;
    }

    //Metodo de Remover Tags e remover () //

    public String removeTags(String entrada){
        String saida = "";
	    for(int i = 0; i < entrada.length(); i++) {
		    if(entrada.charAt(i) == '<') {
			    while(entrada.charAt(i) != '>') i++;
		    } else {
			    saida = saida + entrada.charAt(i);
		    }
	    }
	    return saida;
    }

    public String BuscaParenteses(String original){
        String limpa = "";
        
        for(int i=0; original.charAt(i)!='('; i++){
            limpa += original.charAt(i);
        }

        return limpa;
    }

    //Metodo de leitura//

    public void ler(String nomeArq) throws Exception{
        //D:\\RPG\\Filmes\\ --------- /tmp/filmes/
        FileReader leitura = new FileReader("D:\\RPG\\Filmes\\"+nomeArq);
        BufferedReader br = new BufferedReader(leitura);
        String linha = br.readLine();
        
        // atributo nome
        while(!linha.contains("h2 class")) linha = br.readLine();
        linha = br.readLine();
        this.nome = removeTags(linha).trim();

        // atributo dataDeLancamento
        linha = br.readLine();
        while(!linha.contains("span class=\"release\"")) linha = br.readLine();
        linha = br.readLine().trim();
        this.datalancamento = sdf.parse(BuscaParenteses(linha).trim());
        
        //atributo genero
        linha = br.readLine();
        while(!linha.contains("span class=\"genres\"")) linha = br.readLine();
        br.readLine();
        linha = br.readLine().trim().replace("&nbsp;", "");
        this.genero = removeTags(linha);
        
        // atributo duracao
        linha = br.readLine();
        while(!linha.contains("span class=\"runtime\"")) linha = br.readLine();
        br.readLine();
        linha = br.readLine().trim();
        String tempHours = Character.toString(linha.charAt(0));
        int hours = Integer.parseInt(tempHours) * 60;
        String minutes = removeTags(linha).substring(linha.indexOf("h") + 1).replace("m", "").replace(" ", "");
        int total = hours + Integer.parseInt(minutes);
        this.duracao = total;
        
        // atributo tituloOriginal
        linha = br.readLine();
        while(!linha.contains("<strong>")){
            linha = br.readLine().trim();
        }
        if(!linha.contains("Título original")){
            this.titulooriginal = getNome().trim();
        }else{
            this.titulooriginal = removeTags(linha).replace("Título original ", "").trim();
        }

        // atributo situacao
        linha = br.readLine();
        while(!linha.contains("Situação")) linha = br.readLine().trim();
        this.situacao = removeTags(linha).replace("Situação ", "").trim();

        // atributo idiomaOriginal
        linha = br.readLine();
        while(!linha.contains("Idioma original")) linha = br.readLine().trim();
        this.idioma = removeTags(linha).replace("Idioma original ", "").trim();

        // atributo orcamento

        linha = br.readLine();
        while(!linha.contains("Orçamento")) linha = br.readLine().trim();
        this.orcamento = Float.parseFloat(removeTags(linha).replace("Orçamento ", "").replace(" $", "").replace(",", "").trim());

        
        // atributo palavrasChave 
        linha = br.readLine();
        while(!linha.contains("Palavras-chave")){
            linha=br.readLine();
        }
        String palavrasTemp[]= new String[100];
        int contador=0;
        while(!linha.contains("</ul>")){
            linha=br.readLine();
            if(linha.contains("<li>")){
                //setar a palavra chave nesta linha em array temp
                palavrasTemp[contador] = removeTags(linha).trim();
                contador++;
            }
        }
        palavrachave = new String[contador];
        for(int i=0;i<contador;i++){
            palavrachave[i] = palavrasTemp[i];
        }


        /*linha = br.readLine();
        while(!linha.contains("Palavras-chave")) linha = br.readLine();
        linha = br.readLine();
        int contador = 0;
        while(!linha.contains("<ul>")){
            linha = br.readLine();
            if(linha.contains("<li>")){
                this.palavrachave[contador++] = Arrays.toString(palavrachave).trim();
            }
        }*/

        /*String[] vetor = new String[50];
        int contadorVetor = 0;
        while(!linha.contains("Palavras-chave")){
            linha = br.readLine();
        }
        for(int j=0 ; j<4;j++){
            linha = br.readLine();
        }
        while(linha.contains("<ul>")){
            linha = removeTags(linha).trim();
            vetor[contadorVetor] = linha;
            contadorVetor++;
            br.readLine();
            linha = br.readLine();
        }
        setPalavrachave(vetor);*/

        br.close();
    }

    //Metodo de Clone//

    public Filmes clone(){
        Filmes clone = new Filmes();
        clone.setNome(nome);
        clone.setTitulooriginal(titulooriginal);
        clone.setDatalancamento(datalancamento);
        clone.setDuracao(duracao);
        clone.setGenero(genero);
        clone.setIdioma(idioma);
        clone.setSituacao(situacao);
        clone.setOrcamento(orcamento);
        clone.setPalavrachave(palavrachave);
        return clone;
    }

    //Metodo de Impressao//

    public String imprimir(){
        return(getNome()+" "+getTitulooriginal()+" "+sdf.format(getDatalancamento())+" "+getDuracao()+" "+getGenero()+" "+getIdioma()+" "+getSituacao()+" "+getOrcamento()+" "+Arrays.toString(getPalavrachave()));
    }

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    //Metodo do main//

    public static void main(String[] args) throws Exception {

        String[] entrada = new String[1000];
        int numEntrada = 0;
        Scanner entrada1 = new Scanner(System.in);

        do {
            entrada[numEntrada] = entrada1.nextLine();
         } while (isFim(entrada[numEntrada++]) == false);
         numEntrada--;
         for(int i = 0 ; i < numEntrada ; i++){
             Filmes x = new Filmes();
             try {
                 x.ler(entrada[i]);
             } catch (Exception e){
             }
             MyIO.println(x.imprimir());
         }
    
    }

}