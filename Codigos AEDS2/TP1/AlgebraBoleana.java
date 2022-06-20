class AlgebraBoleana {
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean VerificadorExpressao(String Quantidade , String valor1 , String valor2 ,String valor3) {
        boolean Resposta = false;

        if(Quantidade.charAt(0) == '2'){
            if(valor1.equals(valor2) && valor1.equals(0)){
                Resposta = true;
            }else if(valor1.equals(valor2) && valor1.equals(1)){
                Resposta = false;
            }else{
                Resposta = true;
            }
        }
        
        return Resposta;
    }

    public static void main(String[] args) {

        String[] entrada = new String[1000];

        do {
            entrada[0] = MyIO.readLine();
            VerificadorExpressao(entrada[0], entrada[1], entrada[2], entrada[3]);
            if(VerificadorExpressao(entrada[0], entrada[1], entrada[2], entrada[3])){
                System.out.println("0");
            }else{
                System.out.println("1");
            }
        } while (isFim(entrada[0]) == false);
           //Desconsiderar ultima linha contendo a palavra FIM
    
    }
}
