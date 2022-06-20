public class Is {
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    static boolean Vogal(String s){
        boolean Vogal = true;
        
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) != 'a' && s.charAt(i) != 'e' && s.charAt(i) != 'i' && s.charAt(i) != 'o' && s.charAt(i) != 'u'
                && s.charAt(i) != 'A' && s.charAt(i) != 'E' && s.charAt(i) != 'I' && s.charAt(i) != 'O' && s.charAt(i) != 'U'){
                Vogal = false;
                s.length();
            }
        }

        if(Vogal){
            MyIO.println("SIM NAO NAO NAO");
            return Vogal;
        }else{
            return VerificaInteiros(s);
        }
    }

    static boolean Consoante(String s){
        boolean Consoante = true;
        

        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u'
                || s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U'|| (!IsVetor(s.charAt(i))) ){
                Consoante = false;
                i = s.length();
            }
        }
        
        if(Consoante){
            MyIO.println("NAO SIM NAO NAO");
            return Consoante;
        }else{
            return Vogal(s);
        }
    }

    static boolean VerificaInteiros(String s){
        boolean inteiro = true;
        
        for(int i = 0 ; i< s.length() ; i++){
            if(s.charAt(i) < '0' || s.charAt(i) > 9){
                inteiro = false;
                i = s.length();
            }
        }

        if(inteiro){
            MyIO.println("NAO NAO SIM NAO");
            return inteiro;
        }else{
            return VerificaReais(s);
        }
    }

    static boolean VerificaReais(String s){
        boolean real = true;
        boolean teste = false;
        for(int i = 0 ; i < s.length() ; i++){

            if(s.charAt(i) == ',' || s.charAt(i) == '.'){
                real = true;
            }else if(s.charAt(i) < '0' || s.charAt(i) > '9'){
                real = false;
                i = s.length();
            }
        }

        if(real && teste){
            real = true;
            MyIO.println("NAO NAO NAO SIM");
        }else{
            real = false;
            MyIO.println("NAO NAO NAO NAO");
        }
        return real;
    }

    static boolean IsVetor(char s){
        boolean IsVetor = false;
       
        if(s >= 'a' && s <= 'z' || s >= 'A' && s <= 'Z'){
            IsVetor = true;
        }
        
        return IsVetor;
    }


    public static void main(String[] args) {
        String entrada = MyIO.readLine();
        boolean retorno = false;

        while(!isFim(entrada)){
            retorno = Consoante(entrada);
            entrada = MyIO.readLine();
        }
    
    }
    
}