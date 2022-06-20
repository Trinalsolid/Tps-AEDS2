import java.util.Random;

class AlteracaoAleatoria0 {
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String Aleatorio(String s , Random gerador){
        String alterada = "";
        char inicio = ((char)('a'+(Math.abs(gerador.nextInt())%26)));
        char ultima = ((char)('a'+(Math.abs(gerador.nextInt())%26)));

        alterada = s.replace(inicio , ultima);
        return alterada;
    }

    public static void main(String[] args) {
        String[] entrada = new String[1000];

        Random gerador = new Random();
        gerador.setSeed(4);

        String alterada = "";
        int tamanho = 0;

        do {
            entrada[tamanho] = MyIO.readLine();
            //System.out.println("" + Aleatorio(entrada[tamanho] , gerador));
        } while (isFim(entrada[tamanho++]) == false);
            tamanho--;
           //Desconsiderar ultima linha contendo a palavra FIM

        for(int i=0 ; i < tamanho ; i++){
            alterada = Aleatorio(entrada[i], gerador);
            MyIO.println(alterada);
        }

    }
}