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

//Celula

typedef struct Celula {
	Filme elemento;      // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* NewCelula(Filme elemento) {
   Celula* novacelula = (Celula*) malloc(sizeof(Celula));
   novacelula->elemento = elemento;
   novacelula->prox = NULL;
   return novacelula;
}

Celula* topo; // Sem celula cabeca.

//inicio da pilha flexivel

void InicioPilha () {
   topo = NULL;
}

/**
 * Insere elemento na pilha (politica FILO).
 * @param x Filme elemento a inserir.
 */
void inserir(Filme x) {
   Celula* aux = NewCelula(x);
   aux->prox = topo;
   topo = aux;
   aux = NULL;
}

/**
 * Remove elemento da pilha (politica FILO).
 * @return Elemento removido.
 */
Filme remover() {
   if (topo == NULL) {
      printf("Erro ao remover!\n");
   }

   Filme resp = topo->elemento;
   printf("(R) ");
   printf("%s\n", resp.nome);
   Celula* tmp = topo;
   topo = topo->prox;
   tmp->prox = NULL;
   tmp = NULL;
   free(tmp);
   return resp;
}

void mostrar(Celula *i){
    if(i != NULL){
        i = i->prox;
        mostrar(i);
    }
    if(i != NULL){
        printf("[%d] ", numin++);
        imprimir(i->elemento);
    }

}

int main(){
    InicioPilha();
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
        //adicionar ao endereco a entrada logo apos /tmp/filmes/
        for (int i = 0; i < strlen(entrada) - 1; i++){
            endereco[aux + i] = entrada[i];
        }

        if (isFim(entrada) == 0){
            filmes = ler(endereco);
            inserir(filmes);
        }

    } while (isFim(entrada) == 0);
 
    // segunda entrada

    int n = 0;
    char entrada2[200];
    char aux2[200];
    char temp[200];
    int pos = 0;
    int countador = 0;
    int auxiliar = 0;
    Filme removido;
    scanf("%d", &n);
    for(int i = 0; i < n; i++){
        memset(entrada2, 0, 200);
        memset(aux2, 0, 200);
        memset(temp, 0, 200);
        memset(endereco, 0, 100);
        strcpy(endereco, "/tmp/filmes/");
        //C:\Users\WazX\Desktop\filmes /tmp/filmes/
        strcpy(entrada2, "");
        aux = strlen(endereco);
        
        if(i == 0){
            fgets(entrada2, 200, stdin);
            memset(entrada2, 0, 200);
        }
        fgets(entrada2, 200, stdin);
        countador = 0;
        auxiliar = 0;


        //insercao de elementos
        if(entrada2[0] == 'I'){
            for(int j = 2; j < strlen(entrada2)-1; j++){
                aux2[countador++] = entrada2[j];
            }
            //passa o caminho do elemento
            for (int j = 0; j < strlen(aux2); j++){
                endereco[aux + j] = aux2[j];
            }
            filmes = ler(endereco);
            inserir(filmes);
        //inicio do processo de remover
        }else{
            remover();
        }
    }
    Celula *i = topo;
    mostrar(i);
    printf("[%d] ", numin++);
    imprimir(i->elemento);
    return 0;
}


