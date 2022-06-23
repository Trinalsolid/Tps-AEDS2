#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <locale.h>
#include <time.h>

int MOV = 0;
int COMP = 0;

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
        printf("ERRO! O arquivo n�o foi aberto!\n");
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
        //strcpy(filme.palavrasChave[indice], "[]\n");
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


typedef struct{
    Filme *filmes;
    int n;
}Lista;

Lista makeLista(){
    Lista listas;
    listas.filmes = malloc (50 * sizeof(Filme));
    listas.n = 0;
}

Lista makeListaNzero(Lista *lista){
    lista->n = 0;
}


void inserirInicio(Lista *listas, Filme filme, int tamanho) {
    //validar insercao
    if(listas->n >= tamanho){
        printf("Erro ao inserir!");
    } 

    //levar elementos para o fim do array
    for(int i = listas->n; i > 0; i--){
        listas->filmes[i] = listas->filmes[i-1];
    }

    listas->filmes[0] = filme;
    listas->n++;
}

//INSERIR FIM
void inserirFim(Lista *listas, Filme filme, int tamanho){

    //validar insercao
    if(listas->n >= tamanho){
        printf("Erro ao inserir!");
    }

    listas->filmes[listas->n] = filme;
    listas->n++;
}

//INSERIR POSICAO
void inserir(Lista *listas, Filme filme, int tamanho, int pos) {

    //validar insercao
    if(listas->n >= tamanho || pos < 0 || pos > listas->n){
        printf("Erro ao inserir!");      
    }

    //levar elementos para o fim do array
    for(int i = listas->n; i > pos; i--){
        listas->filmes[i] = listas->filmes[i-1];
    }

    listas->filmes[pos] = filme;
    listas->n++;
}

//REMOVER INICIO
Filme removerInicio(Lista *listas) {

    //validar remocao
    if (listas->n == 0) {
        printf("Erro ao remover!");
    }
    Filme resp = listas->filmes[0];

    listas->n--;

    for(int i = 0; i < listas->n; i++){
        listas->filmes[i] = listas->filmes[i+1];
    }

    return resp;
}

//REMOVER FIM
Filme removerFim(Lista *listas){
    
    //validar remocao
    if (listas->n == 0) {
        printf("Erro ao remover!");
    }
    Filme resp = listas->filmes[listas->n];

    return listas->filmes[--listas->n ];
}
//REMOVER POSICAO
Filme remover(Lista *listas, int pos) {
    //validar remocao
    if (listas->n == 0 || pos < 0 || pos >= listas->n){
        printf("Erro ao remover!");
    }
    Filme resp = listas->filmes[pos];
    listas->n--;

    for(int i = pos; i < listas->n; i++){
        listas->filmes[i] = listas->filmes[i+1];
    }

    return resp;
}

//MOSTRAR
void mostrar (Lista *listas){
    for(int i = 0; i < listas->n; i++){
        //System.out.print(i + "--->");
        imprimir(listas->filmes[i]);
    }
}

void swap(Lista *lista, int menor, int aux){
    Filme tmp = lista->filmes[menor];
    lista->filmes[menor] = lista->filmes[aux];
    lista->filmes[aux] = tmp;
}

void Selection(Lista *array){
    for (int i = 0; i < (array->n - 1); i++) {
      int menor = i;
    if(strcmp(array->filmes[i].idiomaOriginal, array->filmes[i+1].idiomaOriginal) == 0){  
        for (int j = (i + 1); j < array->n && strcmp(array->filmes[menor].idiomaOriginal, array->filmes[j].idiomaOriginal) == 0; j++){
            if (strcmp(array->filmes[menor].nome, array->filmes[j].nome) > 0){
                menor = j;
            }
        }
        
    }  
      swap(array ,menor, i);
      MOV = MOV + 3;
   }
}

void insercaoPorCor(Lista *lista, int n, int cor, int h){
    for (int i = (h + cor); i < n; i+=h) {
        Filme tmp = lista->filmes[i];
        int j = i - h;
        COMP++;
        while ((j >= 0) && (strcmp(lista->filmes[j].idiomaOriginal, tmp.idiomaOriginal) > 0)) {
            lista->filmes[j + h] = lista->filmes[j];
            COMP = COMP + 1;
            j-=h;
        }
        lista->filmes[j + h] = tmp;
    }
}

void shellsort(Lista *lista) {
    int h = 1;
    do { h = (h * 3) + 1; } while (h < lista->n);

    do {
        h /= 3;
        for(int cor = 0; cor < h; cor++){
            insercaoPorCor(lista, lista->n, cor, h);
        }
    } while (h != 1);
    Selection(lista);
}


int main(){
    clock_t inicio, fim;
    Lista listas = makeLista();
    makeListaNzero(&listas);
    Filme filmes;

    int aux = 0;
    char endereco[100] = "", entrada[100] = "", nome[100] = "";
    do{
        memset(endereco, 0, 100);
        memset(entrada, 0, 100);
        strcpy(endereco, "/tmp/filmes/");
        fgets(entrada, 100, stdin);
        // C:\\Users\\WazX\\Desktop\\filmes\\ /tmp/filmes/

        aux = strlen(endereco);
        for (int i = 0; i < strlen(entrada) - 1; i++){
            endereco[aux + i] = entrada[i];
        }

        if (isFim(entrada) == 0){
            filmes = ler(endereco);
            inserirFim(&listas, filmes, 100);
        }

    } while (isFim(entrada) == 0);
    shellsort(&listas);
    mostrar(&listas);   

    return 0;
}