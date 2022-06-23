#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <locale.h>
#include <time.h>

#define TAM 100

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

struct Lista
{
    struct Filme filmes[TAM];
    int n;
};

void initiLista(struct Lista *lista)
{
    lista->n = 0;
}

void inserirFim(struct Lista *lista, struct Series s)
{
    if (lista->n >= TAM)
    {
        printf("\nERRO");
    }

    lista->series[lista->n] = clone(s);
    lista->n++;
}

void inserirInicio(struct Lista *lista, struct filme s)
{
    if (lista->n >= TAM)
    {
        printf("\nERRO");
    }

    for (int i = lista->n; i > 0; i--)
    {
        lista->series[i] = lista->series[i - 1];
    }

    lista->series[0] = clone(s);
    lista->n++;
}

void inserir(struct Lista *lista, struct Series s, int pos)
{
    if (lista->n >= MAX_TAM || pos < 0 || pos > lista->n)
    {
        printf("\nERRO");
    }

    for (int i = lista->n; i > pos; i--)
    {
        lista->series[i] = lista->series[i - 1];
    }

    lista->series[pos] = clone(s);
    lista->n++;
}

struct Series remover(struct Lista *lista, int pos)
{
    if (lista->n == 0 || pos < 0 || pos > lista->n)
    {
        printf("\nERRO");
    }

    struct Series serie = lista->series[pos];
    lista->n--;

    for (int i = pos; i < lista->n; i++)
    {
        lista->series[i] = lista->series[i + 1];
    }

    return serie;
}

struct Series removerInicio(struct Lista *lista)
{
    if (lista->n == 0)
    {
        printf("\nERRO");
    }

    struct Series primeira = clone(lista->series[0]);
    lista->n--;
    for (int i = 0; i < lista->n; i++)
    {
        lista->series[i] = lista->series[i + 1];
    }

    return primeira;
}

struct Series removerFim(struct Lista *lista)
{
    if (lista->n == 0)
    {
        printf("\nERRO");
    }

    lista->n--;
    return clone(lista->series[lista->n + 1]);
}

void imprimir(struct Series *s)
{
    setlocale(LC_ALL, "Portuguese");

    printf("%s %s %s %s %s %s %s %i %i\n", s->nome, s->formato, s->duracao, s->pais, s->idioma, s->emissora, s->transmissao, s->temporadas, s->episodios);
}

void imprimirLista(struct Lista lista)
{
    for (int i = 0; i < lista.n; i++)
    {
        imprimir(&lista.series[i]);
    }
}

int correcaoNum(char linha[])
{
    int j = 0, n = 0;
    char num[strlen(linha)];

    linha[strlen(linha)] = '\0'; // -> para o verde

    for (int i = 0; i < strlen(linha); i++)
    {
        if (linha[i] >= 48 && linha[i] <= 57)
        {
            num[j++] = linha[i];
        }
        else
        {
            i = strlen(linha);
        }
    }

    n = atoi(num);
    return n;
}

char *trim(char *str)
{
    char *end;

    while (isspace((unsigned char)*str))
        str++;

    if (*str == 0)
        return str;

    end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end))
        end--;

    end[1] = '\0';

    return str;
}

void correcao(char linha[])
{
    char correcao[strlen(linha)];

    int i = 0, j = 0;
    while (i < strlen(linha))
    {
        if (linha[i] == '<')
        {
            i++;
            while (linha[i] != '>')
            {
                i++;
            }
        }
        else if (linha[i] == '&')
        {
            i++;
            while (linha[i] != ';')
            {
                i++;
            }
        }
        else if (linha[i] != '\n')
        {
            correcao[j] = linha[i];
            j++;
        }
        i++;
    }
    correcao[j] = '\0'; // -> para o verde
    // correcao[j - 1] = '\0';

    strcpy(linha, correcao);
}

void correcaoTitle(char linha[])
{
    char correcao[strlen(linha)];
    int j = 0;

    for (int i = 0; i < strlen(linha); i++)
    {
        if (linha[i] == '(')
        {
            i++;
            while (linha[i] != ')')
            {
                i++;
            }
        }
        else if (linha[i + 4] == 'W' && linha[i + 5] == 'i' && linha[i + 6] == 'k')
        {
            i = strlen(linha);
        }
        else
        {
            correcao[j++] = linha[i];
        }
    }
    correcao[j] = '\0';

    strcpy(linha, correcao);
}

void ler(char arq[], struct Series *s)
{
    char linha[6000], conteudo[6000];
    char nomeArq[] = {"/tmp/series/"};

    strcat(nomeArq, arq);

    FILE *fp;
    fp = fopen(nomeArq, "r");

    if (!fp)
    {
        printf("\nOcorreu um erro na abertura do arquivo.");
    }

    int pad = 0;
    char padrao[9][50];
    strcpy(padrao[0], "<title>");
    strcpy(padrao[1], "Formato");                        // proxima linha
    strcpy(padrao[2], "Duração");                        // proxima linha
    strcpy(padrao[3], "País de origem");                 // proxima linha
    strcpy(padrao[4], "Idioma original");                // proxima linha
    strcpy(padrao[5], "Emissora de televisão original"); // proxima linha
    strcpy(padrao[6], "Transmissão original");           // proxima linha
    strcpy(padrao[7], "N.º de temporadas");              // proxima linha
    strcpy(padrao[8], "N.º de episódios");               // proxima linha

    fgets(linha, 6000, fp);

    while (s->episodios == 0)
    {
        if (strstr(linha, padrao[pad]) != NULL)
        {

            if (pad == 0)
            {
                correcao(linha);
                correcaoTitle(linha);
                strcpy(linha, trim(linha));
                strcpy(s->nome, linha);
            }
            else
            {
                fgets(linha, 6000, fp);
                correcao(linha);
                strcpy(linha, trim(linha));

                switch (pad)
                {
                case 1:
                    strcpy(s->formato, linha);
                    break;
                case 2:
                    strcpy(s->duracao, linha);
                    break;
                case 3:
                    strcpy(s->pais, linha);
                    break;
                case 4:
                    strcpy(s->idioma, linha);
                    break;
                case 5:
                    strcpy(s->emissora, linha);
                    break;
                case 6:
                    strcpy(s->transmissao, linha);
                    break;
                case 7:
                    s->temporadas = correcaoNum(linha);
                    break;
                case 8:
                    s->episodios = correcaoNum(linha);
                    break;
                default:
                    break;
                }
            }
            pad++;
        }
        fgets(linha, 6000, fp);
    }

    fclose(fp);
}

int isFim(char string[])
{
    return strcmp(string, "FIM\0");
}

struct Series getMaior(struct Lista *lista)
{
    struct Series maior = lista->series[0];

    for (int i = 0; i < lista->n; i++)
    {
        if (maior.temporadas < lista->series[i].temporadas)
        {
            maior = lista->series[i];
        }
    }
    return maior;
}

void ordenarCountingsort(struct Lista *lista)
{
    //Array para contar o numero de ocorrencias de cada elemento
    int tamCount = getMaior(lista).temporadas + 1;
    int count[tamCount];
    struct Series ordenado[lista->n];

    //Inicializar cada posicao do array de contagem
    for (int i = 0; i < tamCount; count[i] = 0, i++);

    //Agora, o count[i] contem o numero de elemento iguais a i
    for (int i = 0; i < lista->n; count[lista->series[i].temporadas]++, i++);

    //Agora, o count[i] contem o numero de elemento menores ou iguais a i
    for (int i = 1; i < tamCount; count[i] += count[i - 1], i++);

    //Ordenando
    for (int i = lista->n - 1; i >= 0; count[lista->series[i].temporadas]--, i--)
    {
        ordenado[count[lista->series[i].temporadas] - 1] = lista->series[i];
    }

    for(int i = 1; i < lista->n; i++){
        struct Series tmp = ordenado[i];
        int j = i - 1;

        while((j >= 0) && ordenado[j].temporadas == tmp.temporadas && strcmp(ordenado[j].nome, tmp.nome) > 0){
            comp+=2;
            ordenado[j+1] = ordenado[j];
            j--;
        }
        ordenado[j+1] = tmp;
    }

    //Copiando para o array original
    for (int i = 0; i < lista->n; lista->series[i] = ordenado[i], i++);
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