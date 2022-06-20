#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdio.h>

bool isfim(char *string){
    return ( string[0] == 'F' && string[1] == 'I' && string[2] == 'M');
}

bool Palindromo(char *string ){
    bool eh = true;
    int tamanho = strlen(string)-1;

    for (int i = 0; i < tamanho/2 ; i++)
    {
        if ( string[i] != string[tamanho-i-1] )
        {
            eh = false;
            i = tamanho;
        }
    }   
    return eh;
}

int main(){
    char Entrada[1000];
    bool palindromo;
    
    fgets( Entrada , 1000 , stdin);

    while( !isfim(Entrada) )
    {
        palindromo = Palindromo(Entrada);
        
        if (palindromo)
        {
            printf("SIM\n");
        }else{
            printf("NAO\n");
        }
       
        fgets(Entrada , 1000 , stdin );        
        
    }  
    
    return 0;
}