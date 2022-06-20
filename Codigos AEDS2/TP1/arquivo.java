class arquivo{

    public static void main(String[] args) {
        try{
            int quantidade = MyIO.readInt();
    
            RandomAccessFile put = new RandomAccessFile("Arquivojava.txt", "rw");
    
            for(int i = 0 ; i < quantidade ; i++){
                put.seek( i * 8);
                put.writeDouble(MyIO.readDouble());;
            }
    
            put.close();
    
            RandomAccessFile arq = new RandomAccessFile("ArquivoJava.txt", "r");
            double print = arq.readDouble();
    
            for(int i = quantidade-1 ; i >= 0 ; i-- ){
                arq.seek(i * 8);
                print = arq.readDouble();
                if(print % 1 == 0){
                    MyIO.println((int) print);
                }else{
                    MyIO.println(print);
                }
            }
            arq.close();
        } catch (Exception erro) {
            MyIO.println("ERRO");
        }
    } 

}
