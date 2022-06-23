#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <locale.h>
#include <time.h>

int numin = 0;
int n = 0;

typedef struct {
    char data[10];
} Date;


typedef struct {
    int qntpalavraschave;
    char nome[200];
    char tituloOriginal[200];
    Date datalancamento;
    int duracao;
    char genero[500];
    char idiomaOriginal[200];
    char situacao[200];
    float orcamento;
    char palavrasChave[100][1000];

} Filme;

//Remover todos os espacos em branco da string
char *removeEspacoEmBranco(char string[]){
    char *resp = malloc(500);
    int aux = 0;
    for(int i = 0; i < strlen(string); i++){
        if(string[i] != ' '){
            resp[aux++] = string[i];    
        }
    }

    return resp;
}
//Remove os espacos em branco no inicio da string
char *trimInicio(char string[]){
    char *resp = malloc(500);
    int aux = 0;
    for(int i = 0; i < strlen(string); i++){
        if(i == 0){
            while(string[i] == ' '){
                i++;
            }
            i--;
        }else{
            resp[aux++] = string[i];
        }
    } 
    return resp;
}

//Remove tudo que esta entre tags
char *semTags(char string[]){
    char * resp = malloc(500);
    int aux = 0;
    for(int i = 0; i < strlen(string); i++){
        if(string[i] == '<'){
            while(string[i] != '>'){
                i++;
            }
        }else{
            resp[aux++] = string[i];
        }
    }
    return resp;
}

//Retorna a string ate encontrar a abertura de parenteses
char *parentesesStop(char string[]){
    char *resp = malloc(500);
    int aux = 0;
    for(int i = 0; i < strlen(string); i++){
        if(string[i] == '('){
            i = strlen(string);
        }else{
            resp[aux++] = string[i];
        }
    }
    return resp;
}

//Remove o conteudo da String que apartir do & ate chegar no ;
char *ecomercialRemove(char string[]){
    char * resp = malloc(500);
    int aux = 0;
    for(int i = 0; i < strlen(string); i++){
        if(string[i] == '&'){
            while(string[i] != ';'){
                i++;
            }
            //pular o espaco em branco dps do ;
            i++;
        }else{
            resp[aux++] = string[i];
        }
    }
    return resp;
}

//Troca os espacos em branco por virgulas
char *trocaEspacoPorVirgula(char string[]){
    char * resp = malloc(500);
    for(int i = 0; i < strlen(string); i++){
        if(string[i] != ' '){
            resp[i] = string[i];
        }else{
            resp[i] = ',';
        }
    }
    return resp;
}

//remove os chars presentes entre , e ; colocando um espaco
char *RemoverEntreVirgulas(char string[]){
    char * resp = malloc(500);
    int aux = 0;
    for(int i = 0; i < strlen(string); i++){
        if(string[i] == ','){
            resp[aux++] = ' ';
            while(string[i] != ';'){
                i++;
            }
        }else{
            resp[aux++] = string[i];
        }
    }
    return resp;       
}

//remover ultimo char da string
char *removeLastChar(char string[]){
    char *resp = malloc(500);
    for(int i = 0; i < strlen(string)-1; i++){
        resp[i] = string[i];
    }
    return resp;
}

//verifica se a String tem horas
int temHoras(char string[]){
    int resp = 0;
    for(int i = 0; i < strlen(string); i++){
        if(string[i] == 'h'){
            resp = 1;
            i = strlen(string);
        }
    }
    return resp;
}

//converte o tempo obtido em uma String(SEM ESPACO) para minutos e devolve esse resultado em um inteiro
int tempoEmMinutos(char string[]){
    int resp = 0;
    int m = 0; // minutos
    int h = 0; // horas
    int i = 0; // indice
    int j = 0; //indice 2
    char *horas = malloc(200);
    char *minutos = malloc(200);
    if(temHoras(string) == 0){
        while(string[i] != 'm'){
            minutos[i] = string[i];
            i++;
        }

        resp = atoi(minutos);

    }else{
        while(string[i] != 'h'){
            horas[i] = string[i];
            i++;
        }
        i++;

        h = atoi(horas);

        while(string[i] != 'm'){
            minutos[j] = string[i];
            i++;
            j++;
        }

        m = atoi(minutos);

        resp = (h*60) + m;
    }
}

//verifica se a string possui titulo Original escrito;
int temTituloOriginal(char string[]){
    int resp = 0;
    if(strstr(string ,"tulo original") != NULL){
        resp = 1;
    }

    return resp;
}

//remove Titulo original da String
char *removeTituloOriginal(char string[]){
        char *resp = malloc(300);
        int j = 0;
        if(temTituloOriginal(string) == 1){
            for(int i = 17; i < strlen(string); i++){
                resp[j++] =  string[i];
            }
        }else{
            strcpy(resp, string);
    }
       return resp;
}

//verifica se a string possui Situacao escrito;
int temSituacao(char string[]){
    int resp = 0;
    if(strstr(string, "Situa") != NULL){
        resp = 1;
    }
    return resp;
}            

//remove Situacao da String;
char *removeSituacao(char string[]){
    char *resp = malloc(200);
    int j = 0;
    if(temSituacao(string) == 1){
        for(int i = 10; i < strlen(string); i++){
            resp[j++] = string[i];
        }
    }else{
        strcpy(resp, string);
    }
    return resp;
}


//remove Idioma Original da String;
char *removeIdiomaOriginal(char string[]){
    char *resp = malloc(200);
    int j =0;
    for(int i = 16; i < strlen(string); i++){
            resp[j++] = string[i];
    }

    return resp;
}

//remove Orcamento da String;
char *removeOrcamento(char string[]){
    char *resp = malloc(200);
    int j =0;
    for(int i = 10; i < strlen(string); i++){
            resp[j++] = string[i];
    }

    return resp;
}

//remove $ da String;
char *removeCifrao(char string[]){
    char *resp = malloc(200);
    int j = 0;
    for(int i = 0; i < strlen(string); i++){
        if(string[i] != '$'){
            resp[j++] = string[i];
        }
    }
    return resp;
}

//remove virgula da String;
char *removeVirgula(char string[]){
    char *resp = malloc(200);
    int j = 0;
    for(int i = 0; i < strlen(string); i++){
        if(string[i] != ','){
            resp[j++] = string[i];
        }
    }
    return resp;
}

//converte a String para um valor float, caso o valor seja '-' o orcamento eh zero
float converterOrcamentoParaValor(char string[]){
    float resp;
    if(strstr(string, "-") != NULL){
        resp = 0;
    }else{
        resp = atof(string);
    }
    return resp;
}

void mostrarOrcamento(Filme filmes){
    if(filmes.orcamento == 0){
        printf("%.0f ", filmes.orcamento);
    }else{
        printf("%.2e ", filmes.orcamento);
    }
}

void mostrarPalavrasChave(Filme filmes){
    int indice = filmes.qntpalavraschave;
    if(strstr(filmes.palavrasChave[indice], "[]\n") == NULL){
        printf("[");
        for(int i = 0; i < indice - 1; i++){
            printf("%s, ", filmes.palavrasChave[i]);
        }
        printf("%s]\n", filmes.palavrasChave[--indice]);
    }else{
        printf("%s", filmes.palavrasChave[indice]);
    }
    
}
//Mostrar os atributos da struct filmes
void imprimir(Filme filme){
    printf("%s %s %s %d ", filme.nome,filme.tituloOriginal, filme.datalancamento.data, filme.duracao);
    printf("%s %s %s " , filme.genero, filme.idiomaOriginal, filme.situacao);
    mostrarOrcamento(filme);
    mostrarPalavrasChave(filme);
}

char *getSituacao(Filme x){
    char *resp = malloc (200);
    strcpy(resp, x.situacao);
    return resp;
}




//---------------------------------------------LEITURA-----------------------------------------------------------------
//Fazer a leitura do arquivo dando set nos atributos dos filmes
Filme ler(char endereco[]){
    Filme filme;
    char *tmp = malloc(1500);
    char * aux = malloc(500);

    strcpy(filme.nome, "");
    strcpy(filme.tituloOriginal, "");
    filme.duracao = 0;
    strcpy(filme.genero, "");
    strcpy(filme.idiomaOriginal, "");
    strcpy(filme.datalancamento.data, "");
    //printf("%s\n", endereco);
    //Abrir o arquivo e verificar se foi aberto corretamente
    FILE *fp = fopen(endereco, "r");
    if (fp == NULL){
        printf("ERRO! O arquivo n?o foi aberto!\n");
    }
    memset(tmp, 0, 1500);
    
    //------------------------------------NOME-----------------------------------
    //ler ate <title> para obter o nome
    while(strstr(fgets(tmp, 1500, fp), "<title>") == NULL);
    aux = removeLastChar(ecomercialRemove(parentesesStop(semTags(trimInicio(tmp)))));
    strcpy(filme.nome, aux);

    //---------------------------------DATA-------------------------------------
    //ler ate <span class="release"> para obter data
    while(strstr(fgets(tmp, 1500, fp), "<span class=\"release\">") == NULL);
    fgets(tmp, 1500, fp);
    aux = parentesesStop(removeEspacoEmBranco(tmp));
    strcpy(filme.datalancamento.data, aux);

    //------------------------------GENERO--------------------------------------
    //ler ate <a href= para obter genero
    while(strstr(fgets(tmp, 1500, fp), "<a href=") == NULL);
    aux = removeLastChar(trocaEspacoPorVirgula(RemoverEntreVirgulas(semTags(trimInicio(tmp)))));
    strcpy(filme.genero, aux);
    
    //---------------------------DURACAO----------------------------------------
    //ler ate <span class="runtime"> para obter duracao
    while(strstr(fgets(tmp, 1500, fp), "<span class=\"runtime\">") == NULL);
    while(strstr(tmp, "</span>") == NULL){
        if(strstr(tmp, "h") != NULL || strstr(tmp, "m") != NULL){
            strcpy(aux, removeLastChar(trimInicio(tmp)));
            fgets(tmp, 1500, fp);
        }else{
            fgets(tmp, 1500, fp);
        }
    }
    int tempo = 0;
    tempo = tempoEmMinutos(aux);
    filme.duracao = tempo;
    
    //------------------------------TITULO ORIGINAL-------------------------------------------------------//
    //ler ate a primeira tag strong
    while(strstr(fgets(tmp, 1500, fp), "<strong>") == NULL);

    //como existem html que nao possui titulo original, eh necessario verificar 
    //se nao tem estamos na linha de situacao e o titulo original eh o proprio nome
    if(strstr(tmp, "original") != NULL){
        strcpy(aux, removeLastChar(removeTituloOriginal(semTags(trimInicio(tmp)))));
        strcpy(filme.tituloOriginal, aux);
    }else{
        strcpy(filme.tituloOriginal, filme.nome);
    }

    //-----------------------------------------SITUACAO--------------------------------------------------//
    //verifica se estamos na linha que contem titulo original ou na linha que ja esta em situacao
    //caso seja na linha do titulo original, caminhamos ate a linha que contem situacao
    if(strstr(tmp, "original") != NULL){
        while(strstr(fgets(tmp, 1500, fp), "Situa") == NULL);
        strcpy(aux, trimInicio(removeLastChar(removeSituacao(semTags(trimInicio(tmp))))));
        strcpy(filme.situacao, aux);
    }else{
        strcpy(aux, trimInicio(removeLastChar(removeSituacao(semTags(trimInicio(tmp))))));
        strcpy(filme.situacao, aux);
    }

    //------------------------------------------IDIOMA ORIGINAL-------------------------------------------//
    //ir ate a linha que contem idioma original e tratar
    while(strstr(fgets(tmp, 1500, fp), "Idioma original") == NULL);
    strcpy(aux, removeLastChar(removeIdiomaOriginal(semTags(trimInicio(tmp)))));
    strcpy(filme.idiomaOriginal, aux);

    //------------------------------------------- ORCAMENTO -------------------------------------
    //a proxima linha eh orcamento
    float valor = 0;
    fgets(tmp, 1500, fp);
    strcpy(aux, removeVirgula(removeCifrao(removeOrcamento(semTags(trimInicio(tmp))))));
    valor = converterOrcamentoParaValor(aux);
    filme.orcamento = valor;
    
    //------------------------------------------PALAVRAS CHAVES----------------------------------
    //ler ate Palavras-chave e depois ate </ul> pegando apenas as linhas que contem <li>
    char vetstring[100][1000];
    int indice = 0;
    while(strstr(fgets(tmp, 1500, fp), "Palavras-chave") == NULL);
    fgets(tmp, 1500, fp);
    fgets(tmp, 1500, fp);
    if(strstr(tmp, "Nenhuma palavra-chave foi adicionada") == NULL){
        while(strstr(fgets(tmp, 1500, fp), "</ul>") == NULL){
            if(strstr(tmp, "<li>") != NULL){
                strcpy(aux, removeLastChar(semTags(trimInicio(tmp))));
                strcpy(vetstring[indice++], aux);
            }
        }
        filme.qntpalavraschave = indice;
        for(int i = 0; i < indice; i++){
            strcpy(filme.palavrasChave[i], vetstring[i]);
        }
    }else{
    }
    
    free(tmp);
    free(aux);

    fclose(fp);
    return filme;
}

int isFim(char string[]){
    if (string[0] == 'F' && string[1] == 'I' && string [2] == 'M'){
        return 1;
    } else {
        return 0;
    }
}


#define bool   short
#define true   1
#define false  0

//celula
typedef struct CelulaDupla {
	Filme filmes;        // filmes inserido na celula.
	struct CelulaDupla* prox; // Aponta a celula prox.
    struct CelulaDupla* ant;  // Aponta a celula anterior.
} CelulaDupla;


CelulaDupla* NewCelulaDupla(Filme filmes) {
   CelulaDupla* novacelula = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   novacelula->filmes = filmes;
   novacelula->ant = novacelula->prox = NULL;
   return novacelula;
}

// criacao da lista

CelulaDupla* primeiro;
CelulaDupla* ultimo;


/**
 * Cria uma lista dupla sem filmes (somente no cabeca).
 */
void InicioLista(Filme x) {
   primeiro = NewCelulaDupla(x);
   ultimo = primeiro;
}

/**
 * Insere um filmes na primeira posicao da lista.
 * @param x int filmes a ser inserido.
 */
void inserirInicio(Filme x) {
    CelulaDupla* aux = NewCelulaDupla(x);
    aux->ant = primeiro;
    aux->prox = primeiro->prox;
    primeiro->prox = aux;
    if (primeiro == ultimo) {                    
        ultimo = aux;
    } else {
        aux->prox->ant = aux;
    }
    aux = NULL;
    n++;
}


/**
 * Insere um filmes na ultima posicao da lista.
 * @param x Filme filmes a ser inserido.
 */
void inserirFim(Filme x) {
    ultimo->prox = NewCelulaDupla(x);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
    n++;
}


/**
 * Remove um filmes da primeira posicao da lista.
 * @return resp Filme filmes a ser removido.
 */
Filme removerInicio() {
    if (primeiro == ultimo) {
        printf("Erro ao remover");
    }

    CelulaDupla* tmp = primeiro;
    primeiro = primeiro->prox;
    Filme resp = primeiro->filmes;
    tmp->prox = primeiro->ant = NULL;
    free(tmp);
    tmp = NULL;
    n--;
    return resp;
}

/**
 * Remove um filmes da ultima posicao da lista.
 * @return resp Filme filmes a ser removido.
 */
Filme removerFim() {
    if (primeiro == ultimo) {
        printf("Erro ao remover!");
    } 
    Filme resp = ultimo->filmes;
    ultimo = ultimo->ant;
    ultimo->prox->ant = NULL;
    free(ultimo->prox);
    ultimo->prox = NULL;
    n--;
    return resp;
}

/**
 *  Calcula e retorna o tamanho, em numero de filmes, da lista.
 *  @return resp int tamanho
 */
int tamanholista() {
    int tamanho = 0; 
    CelulaDupla* i;
    for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
    return tamanho;
}

/**
 * Insere um filmes em uma posicao especifica considerando que o 
 * primeiro filmes valido esta na posicao 0.
 * @param x Filme filmes a ser inserido.
 * @param pos int posicao da insercao.
 * @throws Exception Se <code>posicao</code> invalida.
 */
void inserir(Filme x, int pos) {

    int tam = tamanholista();
    if(pos < 0 || pos > tam){
        printf("Erro ao remover (posicao %d/%d invalida!", pos, tam);
    } else if (pos == 0){
        inserirInicio(x);
    } else if (pos == tam){
        inserirFim(x);
    } else {
        // Caminhar ate a posicao anterior a insercao
        CelulaDupla* i = primeiro;
        int j;
        for(j = 0; j < pos; j++, i = i->prox);

        CelulaDupla* tmp = NewCelulaDupla(x);
        tmp->ant = i;
        tmp->prox = i->prox;
        tmp->ant->prox = tmp->prox->ant = tmp;
        tmp = i = NULL;
        n++;
    }
}

/**
 * Remove um filmes de uma posicao especifica da lista
 * considerando que o primeiro filmes valido esta na posicao 0.
 * @param posicao Meio da remocao.
 * @return resp Filme filmes a ser removido.
 * @throws Exception Se <code>posicao</code> invalida.
 */
Filme remover(int pos) {
    Filme resp;
    int tam = tamanholista();

    if (primeiro == ultimo){
        printf("Erro ao remover vazio");
    } else if(pos < 0 || pos >= tam){
        printf("Erro ao remover (posicao %d/%d invalida!", pos, tam);
    } else if (pos == 0){
        resp = removerInicio();
    } else if (pos == tam - 1){
        resp = removerFim();
    } else {
        // Caminhar ate a posicao anterior a insercao
        CelulaDupla* i = primeiro->prox;
        int j;
        for(j = 0; j < pos; j++, i = i->prox);

        i->ant->prox = i->prox;
        i->prox->ant = i->ant;
        resp = i->filmes;
        i->prox = i->ant = NULL;
        free(i);
        n--;
        i = NULL;
    }

    return resp;
}

void mostrar() {
   CelulaDupla* i;
   for (i = primeiro->prox; i != NULL; i = i->prox) {
      imprimir(i->filmes);
   }

}

/**
 * Mostra os filmess da lista de forma invertida 
 * e separados por espacos.
 */
void mostrarInverso() {
    printf("[ ");
    CelulaDupla* i;
    for (i = ultimo; i != primeiro; i = i->ant){
        imprimir(i->filmes);
    }
    printf("] \n");
}


CelulaDupla* getFilmePosicao(int pos){
    CelulaDupla *resp;
    CelulaDupla *aux = primeiro;
    for(int i = 0; i < pos && aux->prox != NULL; aux = aux->prox);
    resp = aux;
    return resp;
}

void swap(int menor, int i) {
    Filme aux;
    CelulaDupla *j = getFilmePosicao(menor);
    CelulaDupla *x = getFilmePosicao(i);

}

void quicksort(int esq, int dir){
    int i = esq;
    int j = dir;
    CelulaDupla *pivo = primeiro;
    for(int x = 0; j < (dir+esq)/2 && pivo->prox != NULL; pivo = pivo->prox);

    while (i <= j) {

        for(CelulaDupla *tmp = getFilmePosicao(i); 
            strcmp(getSituacao(tmp->filmes),getSituacao(pivo->filmes)) < 0 && i < dir;
            tmp = tmp->prox, i = i+1);
        for(CelulaDupla *tmp = getFilmePosicao(j); 
            strcmp(getSituacao(tmp->filmes),getSituacao(pivo->filmes)) > 0 && j > esq;
            tmp = tmp->ant, j = j-1);
        if (i <= j) {
            i++;
            j--;
        }
    }
    if (esq < j)  quicksort(esq, j+1);
    if (i < dir)  quicksort(i, dir);

}

int main(){
    char padrao[100] = "/tmp/filmes/Batman.html";
    Filme iniciar = ler(padrao);
    InicioLista(iniciar);
    Filme filmes;
    int aux = 0;
    char endereco[100] = "", entrada[100] = "", nome[100] = "";
    do{
        memset(endereco, 0, 100);
        memset(entrada, 0, 100);
        strcpy(endereco, "/tmp/filmes/");
        //C:\Users\WazX\Desktop\filmes /tmp/filmes/
        fgets(entrada, 100, stdin);

        aux = strlen(endereco);
        for (int i = 0; i < strlen(entrada) - 1; i++){
            endereco[aux + i] = entrada[i];
        }
        if (isFim(entrada) == 0){
            filmes = ler(endereco);
            inserirFim(filmes);
        }
    } while (isFim(entrada) == 0);

    quicksort(0, n-1);  
    mostrar();

    return 0;
}


