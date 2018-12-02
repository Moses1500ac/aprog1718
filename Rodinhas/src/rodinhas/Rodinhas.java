package rodinhas;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Scanner;


public class Rodinhas {

    private final static int MAX_PARTICIPANTES = 20;
    private final static int N_CAMPOS_INFO = 4;
    private final static int N_PROVAS = 5;
    private final static int MAX_LINHAS_PAGINA = 3;
    private final static String SEPARADOR_DADOS=";";
    private final static String SEPARADOR_DADOS_TEMPOS="/";
    private final static String SEPARADOR_DADOS_MS=":";
    private final static String SEPARADOR_DADOS_PROVAS=":";
    

    public static int menu(int operacao) {
        System.out.println("MENU \n");
        System.out.println("1- Ler um ficheiro de texto");
        System.out.println("2- Visualizar informação dos participantes inscritos");
        System.out.println("3- Alterar informação de um participante");
        System.out.println("4- Carregar informação sobre as provas");
        System.out.println("5- Carreagr tempos dos participantes");
        System.out.println("6- Calcular prémios atribuídos");
        System.out.println("7- Criar um backup de toda a informação");
        System.out.println("8- Remover participante");
        System.out.println("9- Inserir novo participante");
        System.out.println("10- Visualizar velocidades médias");
        System.out.println("11- Visualizar informações sobre as provas");
        System.out.println("12- Criar ficheiro com toda a informação");
        System.out.println("0- Sair \n");

        do {
            Scanner ler = new Scanner(System.in);
            System.out.print("Insira a sua opção: ");
            operacao = ler.nextInt();
        } while (operacao < 0 || operacao > 12);

        return operacao;
    }
    
    //1__________________________________________________________________________________________________________________________
    public static int lerInfoFicheroParticipantes(String nomeFich, String[][] participantes, int n) throws FileNotFoundException 
    {
        Scanner lerFich = new Scanner(new File(nomeFich+".txt"));
        while(lerFich.hasNext() && n<MAX_PARTICIPANTES) 
        {
            String linha=lerFich.nextLine();
            if (linha.trim().length()>0)
            {
                n=guardarDadosParticipantes(linha,participantes,n);
                
            }
        
        
        }
        lerFich.close();
        return n;
    }
    public static int guardarDadosParticipantes(String linha, String[][] participantes, int n)
    {
        String[] aux=linha.split(SEPARADOR_DADOS);
        if (aux.length==N_CAMPOS_INFO)
        {
            String aux_num=aux[0].trim();
        
        int posicao=pesquisarElementos(aux_num,n,participantes);
        if (posicao==-1)
        {
                participantes[n][0]=aux_num;
                participantes[n][1]=aux[1].trim();
                participantes[n][2]=aux[2].trim();
                participantes[n][3]=aux[3].trim();
               
                n++;
        }
        }    
        
        
        
        
        return n;
    
    }
     public static int pesquisarElementos(String aux_num, int n, String[][] participantes)
    {
        for (int i = 0; i < n; i++)
        {
            if(participantes[i][0].equals(aux_num))
            {
                return i;       
            
            }
            
        }
        return -1;
    }
    //2___________________________________________________________________________________________________________________________
     public static void  listar(String[][] participantes, int n)
    {
        int contPag=0;
        
        for (int i = 0; i < n; i++)
        {
            if (i%MAX_LINHAS_PAGINA==0)
            {
                if (contPag>0) 
                {
                    pausa();
                }
                contPag++;
                System.out.println("\nPAGINA"+contPag);
                cabecalho();
                
            }
            
        
        
                
        
            for (int j = 0; j < N_CAMPOS_INFO; j++) 
            {
                if (j==1) {
                     System.out.printf("%30s",participantes[i][j]);
                }
                else
                {
                     System.out.printf("%20s",participantes[i][j]);
                }
               
                
            }
            System.out.println("");
            
        }
        pausa();
    
    
    }
     public static void cabecalho()
     {
        System.out.printf("%50s%n","PARTICIPANTES");
        System.out.printf("%75s%n","==========================================================================================");
     }
     public static void pausa()
     {
         System.out.println("\n\nENTER PARA A PROXIMA PAGINA\n");
         Scanner enter=new Scanner(System.in);
         enter.nextLine();
     }
    //3__________________________________________________________________________________________________________________________
    public static void alterarInfo(String nSocio,String[][] participantes,int n)
    {
        int op;
        for (int i = 0; i < n; i++) 
        {
            if (participantes[i][0].equals(nSocio)) 
            {
                
                Scanner lerOp=new Scanner(System.in);
                System.out.println("1-alterar nome\n2-alterar marca\n3-alterar data\n0-sair");
                op=lerOp.nextInt();
                do{
                    
                    
                    switch(op){
                        case 1:
                        System.out.println("insira o novo nome");
                        Scanner lernome=new Scanner(System.in);
                        String novoNome=lernome.nextLine();
                        participantes[i][1]=novoNome;
                    
                            break;
                        case 2:
                        System.out.println("Insira nova marca");
                        Scanner lermarca=new Scanner(System.in);
                        String novaMarca=lermarca.nextLine();
                        participantes[i][2]=novaMarca;
                            break;
                        case 3:
                        System.out.println("Insira nova data");
                        Scanner leData=new Scanner(System.in);
                        String novaData=leData.nextLine();
                        participantes[i][3]=novaData;

                        break;
                        
                        case 0:
                            break;
                    
                    } 
                    System.out.println("1-alterar nome\n2-alterar marca\n3-alterar data\n0-sair");
                    op=lerOp.nextInt();
                
                
                }while(op!=0);
            }
            
        }
        
    
    
    }
    //4___________________________________________________________________________________________________________________________
    public static int lerInfoFicheiroProvas( String[]provas, int n_provas) throws FileNotFoundException
    {
        String linha="";
        Scanner lerFich=new Scanner(new File("provas.txt"));
        while(lerFich.hasNext()&&n_provas<N_PROVAS)
        {
            linha=linha+lerFich.nextLine();
            if (linha.trim().length()>0)
            {
                n_provas=guardarDadosProvas(linha,provas,n_provas);
                
            }
        }
        lerFich.close();
        
        return n_provas;
    }
    public static int guardarDadosProvas(String linha, String[] provas, int n_provas)
    {
        
        String[] aux=linha.split(SEPARADOR_DADOS);
        
        if (aux.length!=0)
        {
            
             while(n_provas<aux.length){
             provas[n_provas]=aux[n_provas].trim().replaceAll("Km", "");
             n_provas++;
            
            }
        }
        
        return n_provas;
    }
    //5____________________________________________________________________________________________________________________________
    public static void lerInfoTempos (String fichTempos, int[][] tempos, String[] provas,String[][] participantes,int n) throws FileNotFoundException
    {
        
        Scanner lerFichTempos= new Scanner(new File(fichTempos+".txt"));
        while(lerFichTempos.hasNext())
        {
            String linha=lerFichTempos.nextLine();
            if (linha.trim().length()>0)
            {
               guardarDadosTempos(linha,tempos,provas,fichTempos,participantes,n);
                
            }
        
        }
        
    
    }
    public static int procuraProva(String fichTempos, String[]provas)
    {
        for (int i = 0; i < N_PROVAS; i++) 
        {
            String[] auxProva=provas[i].split(":");
            
            if (auxProva[0].equals(fichTempos))
            {
                return i;
                
            }
            
        }
    
        return -1;
    }
    public static int procuraSocio(String[][] participantes,int n,String aux)
    {
        for (int i = 0; i < n; i++)
        {
            if (participantes[i][0].equals(aux))
            {
                return i;
                
            }
        }
        return -1;
    }
    public static void guardarDadosTempos(String linha, int[][] tempos, String[] provas, String fichTempos,String[][] participantes,int n)
    {
        String[] aux=linha.split(SEPARADOR_DADOS_TEMPOS);
        int j=procuraProva(fichTempos,provas);
       
        int i=procuraSocio(participantes,n,aux[0]);
        
        if (j!=-1 && i!=-1)
        {
            String[] tempo=aux[1].split(SEPARADOR_DADOS_MS);
            int tempof=(Integer.parseInt(tempo[0])*60)+Integer.parseInt(tempo[1]);
            tempos[i][j]=tempof;
        }
        
    }
   //6________________________________________________________________________________________________________________________________
    public static void  calculaPremios(String[] provas, int n, int[][] tempos, String[][] participantes, double[][] premios)
    {
        
        for (int i = 0; i < premios[0].length; i++)
        {
            String auxkm=provas[i];
            String[] aux=auxkm.trim().split(":");
            
            
            for (int j = 0; j < n; j++)
            {
                double idade=idadeAutomovel(participantes,j);
                premios[j][i]=((idade/20)*(Integer.parseInt(aux[1].trim()))*2);
                
            }
            
        }
        for (int i = 0; i < N_PROVAS; i++)
        {
            bonusMelhorTempo(tempos,premios,n,i);
            
        }
        
    
    
    }
    public static int idadeAutomovel(String[][] participantes,int j)
    {
        int idade=0;
        String[] aux=participantes[j][3].split("/");
        Calendar hoje=Calendar.getInstance();
        int diaH=hoje.get(Calendar.DAY_OF_MONTH);
        int mesH=hoje.get(Calendar.MONTH);
        int anoH=hoje.get(Calendar.YEAR);
        int diaAuto=Integer.parseInt(aux[0]);
        int mesAuto=Integer.parseInt(aux[1]);
        int anoAuto=Integer.parseInt(aux[2]);
        if (mesH>mesAuto || mesH==mesAuto && diaH>=diaAuto)
        {
            idade=anoH-anoAuto;
            return idade;
        }
        return idade-1;
    }
    public static void bonusMelhorTempo(int[][]tempos, double[][] premios, int n,int prova)
    {
        
        int menor=tempos[0][prova];
        
        for (int i = 0; i < n; i++)
        {
            if (tempos[i][prova]<menor && tempos[i][prova]!=0)
            {
                menor=tempos[i][prova];
                
            }
            
        }
        for (int i = 0; i < n; i++)
        {
            if (tempos[i][prova]==menor)
            {
                premios[i][prova]=premios[i][prova]+500;
                
            }
            
        }
        
            
    }
    //7________________________________________________________________________________________________________________________________
    public static void backup(int[][]tempos, double[][] premios, String[][] participantes, int n,int n_provas,String[]provas) throws IOException
    {
        Formatter backup=new Formatter(new File("backup.txt"));
        
        
        for (int i = 0; i < n; i++) 
        {
            backup.format("\n", null);
            for (int j = 0; j < N_CAMPOS_INFO; j++) 
            {
                backup.format("%s;", participantes[i][j]);
               
                
            }
            for (int j = 0; j < N_PROVAS; j++) 
            {
                backup.format("%s",tempos[i][j]);
                backup.format(";", null);
                
            }
        }
        backup.format("\n", null);
        for (int i = 0; i < N_PROVAS; i++) 
        {
            backup.format("%s", provas[i]);
            backup.format("\n", null);
        }
        backup.close();
                
    }
    //8________________________________________________________________________________________________________________________________
    public static int removerParticipante(int n, String[][]participantes, int n_provas,int[][]tempos,double[][]premios)
    {
        String nSocio;
        System.out.println("Insira o numero de socio do participante a remover");
        listar(participantes,n);
        Scanner numero=new Scanner(System.in);
        nSocio=numero.nextLine();
        
        int i=procuraSocio(participantes,n,nSocio);
        if (i!=-1) {
            
        
        eliminar(n,participantes,i,n_provas,tempos,premios);
        
        n--;
        }
        else
        {
            System.out.println("o participante não existe");
        }
        
    
        return n;
    }
    public static void eliminar(int n,String[][]participantes,int i,int n_provas,int[][] tempos,double[][] premios)
    {
        for (int j = i; j < n; j++) 
        {
            participantes[j]=participantes[j+1];
            tempos[j]=tempos[j+1];
            premios[j]=premios[j+1];
            
        }
        for (int j = 0; j < N_CAMPOS_INFO; j++) 
        {
            participantes[n][j]=" ";
            tempos[n][j]=0;
            premios[n][j]=0;
        }
        
    }
    //9_________________________________________________________________________________________________________________________________
    public static int adicionarParticipante(int n, String[][]participantes)
    {
        Scanner ler=new Scanner(System.in);
        if (n<MAX_PARTICIPANTES)
        {    
            for (int i = 0; i < N_CAMPOS_INFO; i++) 
            {
                switch(i){
                    case 0:
                        System.out.println("Insira o numero de sócio");
                        String nSocio=ler.nextLine();
                        participantes[n][i]=nSocio;
                        break;
                    case 1:
                        System.out.println("Insira o nome do participante");
                        String nome=ler.nextLine();
                        participantes[n][i]=nome;
                        break;
                    case 2:
                        System.out.println("Insira a marca do veículo");
                        String marca=ler.nextLine();
                        participantes[n][i]=marca;
                        break;
                    case 3:
                        System.out.println("Insira a data de registo do veìculo");
                        String data=ler.nextLine();
                        participantes[n][i]=data;
                        break;
                }
                
                
            }
            n++;
            
            
        }
        else
            System.out.println("já estão inscritos os máximos de participantes");
        
        return n;
    }
    //10__________________________________________________________________________________________________________________________________
    public static void velocidadeMedia(String provas[],int[][] tempos, double[][]velocidade_media,int n, String[][] participantes)
    {
        System.out.println("Insira o numero de socio que pretende ver a velocidade media");
        Scanner ler=new Scanner(System.in);
        String nSocio=ler.nextLine();
        int i=procuraSocio(participantes,n,nSocio);
        int vMax=0;
        int vMin=0;
        
        if (i!=-1)
        {
            int km[]=new int[N_PROVAS];
            
            System.out.println("Velocidade media do sócio "+nSocio+" nas provas realizadas");
            for (int j = 0; j < N_PROVAS; j++) 
            {
                String aux[]=provas[j].trim().split(SEPARADOR_DADOS_PROVAS);
                km[j]=Integer.parseInt(aux[1].trim())*1000;
                
            }
            for (int j = 0; j < n; j++) 
            {
                for (int k = 0; k < N_PROVAS; k++)
                {
                    double auxDistancia=km[k];
                    double tempo=tempos[j][k];
                    velocidade_media[j][k]=auxDistancia/tempo*3600/1000;
                    
                }
                
                
            }
            int vel_print;
            int vel_printm;
            int vel_printM;
            for (int j = 0; j < N_PROVAS; j++)
            {
                vMin=mediaBaixa(n,velocidade_media,j);
                vMax=mediaAlta(n,velocidade_media,j);
                vel_print=(int)(velocidade_media[i][j]);
                vel_printm=(int)(velocidade_media[vMin][j]);
                vel_printM=(int)(velocidade_media[vMax][j]);
                System.out.println("\n");
                System.out.println(provas[j]+"km:");
                System.out.println(participantes[i][1]+":"+vel_print+"Km/h");
                System.out.println("participante mais rapido:"+vel_printM+"Km/h");
                System.out.println("participante mais lento:"+vel_printm+"Km/h");
                               
                
            }
            
            
            
        }
        else
        {
            System.out.println("prova ainda n realizada");
        }
    }
    public static int mediaAlta(int n, double[][] velocidade_media,int j)
    {
        int posAlto=diferenteZero(n,velocidade_media,j);
        if (posAlto>-1)
        {
            double alto=velocidade_media[posAlto][j];
            for (int i = 0; i < n; i++)
            {
                if (velocidade_media[i][j]>alto)
                {
                    alto=velocidade_media[i][j];
                    posAlto=i;
                    
                    
                }
                
                
            }
            return posAlto;
            
        }
        return -1;
    }
    public static int mediaBaixa(int n, double[][] velocidade_media,int j)
    {
        int posBaixo=diferenteZero(n,velocidade_media,j);
        if (posBaixo>-1)
        {
            double baixo=velocidade_media[posBaixo][j];
            for (int i = 0; i < n; i++)
            {
                if (velocidade_media[i][j]<baixo)
                {
                    baixo=velocidade_media[i][j];
                    posBaixo=i;
                    
                    
                }
                
                
            }
            return posBaixo;
            
        }
        return -1;
    }
    public static int diferenteZero(int n, double[][] velocidade_media,int j)
    {
        for (int i = 0; i < n; i++) 
        {
            if (velocidade_media[i][j]!=0)
            {
                return i;
                
            }
            
        }
    
    
        return -1;
    }
    //11___________________________________________________________________________________________________________________________________
    public static double listarVelocidades(int n,int b,double[][]velocidade_media,double[] velocidades_provas)
    {
        double vel_provas=0;
        for (int i = 0; i < n; i++)
        {
            vel_provas=vel_provas+velocidade_media[i][b];
            velocidades_provas[b]=vel_provas;
            
        }
        return velocidades_provas[b];
    }
    public static int maisRapido(int n,double[][]velocidade_media,double[]vel_media)
    {
        for (int i = 0; i < n; i++) 
        {
            double vel_part_total=0;
            for (int j = 0; j < N_PROVAS; j++) 
            {
                vel_part_total=vel_part_total+velocidade_media[i][j];
                
                
            }
            vel_media[i]=vel_part_total/N_PROVAS;
        }
        double alto=vel_media[0];
        int pos=0;
        for (int i = 0; i < n; i++)
        {
            if (vel_media[i]>alto)
            {
                alto=vel_media[i];
                pos=i;
                
            }
            
        }
        return pos;
    }
    public static double totalPremios (int n,double[][]premios,int b)
    {
        double total_premios=0;
        for (int i = 0; i < n; i++)
        {
            total_premios=total_premios+premios[i][b];
            
        }
        return total_premios;
    }
    //12________________________________________________________________________________________________________________________________
    public static void ficheiroGrandePremio(int n,String[][] participantes,double[][] premios,int n_provas) throws FileNotFoundException
    {
       double[] tot_premios=new double [MAX_PARTICIPANTES];
       int[] novaPos=new int[MAX_PARTICIPANTES];
       double totalPremios=0;
       try(Formatter output=new Formatter(new File("GrandePremio.txt")))
       {
           output.format("%35s%n","LISTAGEM DE PRÉMIOS");
           output.format("%10s%20s%20s%20s%n","Num","Nome","Idade Automovel","Total de Prémios");
           for (int i = 0; i < n; i++)
           {
               tot_premios[i]=totalPremiosParticipante(n_provas,premios,i);
               
           }
           novaPos=ordenar(tot_premios,n,novaPos);
          
          String data=dataHoje();
           for (int i = 0; i < n; i++)
           {
               int indice=novaPos[i];
               String nomeFormatado=formataNome(participantes[indice][1]);
               int idade=idadeAutomovel(participantes,i);
               double premio=totalPremiosParticipante(n_provas,premios,indice);
               
               output.format("\n%10s",participantes[indice][0]);
               output.format("%20s",nomeFormatado);
               output.format("%20s",idade);
               output.format("%20s",premio+"€");
               totalPremios=totalPremios+premio;
               
               
           }
           output.format("\n");
           output.format("%80s","TOTAL   "+totalPremios+"€");
           output.format("\n\n%10s",data);
           output.close();
       }
       
    }  
     public static int[] ordenar(double [] tot_premios,int n,int[] novaPos)
    {
        
        int auxPos;
        
        for (int i = 0; i < MAX_PARTICIPANTES; i++)
        {
            novaPos[i]=i;
            
        }
        
        for (int i = 0; i <n ; i++) 
        {
            for (int j = i+1; j < n; j++) 
            {
                if(tot_premios[i]<tot_premios[j])
                {
                  
                  
                  auxPos=novaPos[i];
                  novaPos[i]=novaPos[j];
                  novaPos[j]=auxPos;
                }
            }
        }
        return novaPos;
    }
    public static double totalPremiosParticipante (int n_provas,double[][]premios,int b)
    {
        double total_premios=0;
        for (int i = 0; i < n_provas; i++)
        {
            total_premios=total_premios+premios[b][i];
            
        }
        return total_premios;
    }
    public static String dataHoje()
    {
        Calendar hoje=Calendar.getInstance();
        int diaH=hoje.get(Calendar.DAY_OF_MONTH);
        int mesH=hoje.get(Calendar.MONTH)+1;
        int anoH=hoje.get(Calendar.YEAR);
        String data=Integer.toString(diaH)+"/"+Integer.toString(mesH)+"/"+Integer.toString(anoH);
        return data;
    }
    public static String formataNome(String nome)
    {
        String nomeFormatado = null;
        String[] aux=nome.split(" ");
        int n=aux.length;
        nomeFormatado=aux[n-1]+" "+aux[0].charAt(0)+".";
        
        return nomeFormatado;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String[][] participantes = new String[MAX_PARTICIPANTES][N_CAMPOS_INFO];
        String[] provas=new String[N_PROVAS];
        int[][] tempos = new int[MAX_PARTICIPANTES][N_PROVAS];
        double[][] premios=new double[MAX_PARTICIPANTES][N_PROVAS];
        double[][] velocidade_media=new double[MAX_PARTICIPANTES][N_PROVAS];
        double[] velocidades_provas=new double[N_PROVAS];
        double[]vel_media=new double[MAX_PARTICIPANTES];
        
        
        int operacao = 0;
        int n=0;
        int n_provas=0;
        int cont_provas=0;
        
        do {
            operacao = menu(operacao);
            switch (operacao) {
                case 1:
                    Scanner lerNome=new Scanner(System.in);
                    System.out.println("Insira o nome do ficheiro");
                    String nomeFich=lerNome.nextLine();
                    n=lerInfoFicheroParticipantes(nomeFich,participantes,n);
                    break;
                case 2:
                    listar(participantes,n);
                    break;
                case 3:
                    Scanner lerSocio=new Scanner (System.in);
                    System.out.println("Insira o numeor de sócio de quem deseja alterar");
                    String nSocio=lerSocio.next();
                    alterarInfo(nSocio,participantes,n);
                    break;
                case 4:
                    n_provas=lerInfoFicheiroProvas(provas, n_provas);
                    break;
                case 5:
                    Scanner lerFichTempos=new Scanner(System.in);
                    System.out.println("Insira o nome do ficheiro dos tempos da prova desejada");
                    String fichTempos=lerFichTempos.nextLine();
                    lerInfoTempos(fichTempos, tempos,provas, participantes, n);
                    break;
                case 6:
                    calculaPremios(provas,n,tempos,participantes,premios);
                   
                    break;
                case 7:
                    backup(tempos,premios,participantes,n,n_provas,provas);
                    break;
                case 8:
                    n=removerParticipante(n,participantes,n_provas,tempos,premios);
                    break;
                case 9:
                    n=adicionarParticipante(n, participantes);
                    listar(participantes,n);
                    break;
                case 10:
                    velocidadeMedia(provas,tempos,velocidade_media,n,participantes);
                    break;
                case 11:
                    for (int b = 0; b < N_PROVAS; b++) 
                    {
                        velocidades_provas[b]=listarVelocidades(n,b,velocidade_media,velocidades_provas);
                        System.out.println(provas[b]);
                        System.out.println("MEdia da prova:" + (int)(velocidades_provas[b]/n)+"Km/h");
                        int indice=mediaAlta(n,velocidade_media,b);
                        System.out.println("Media mais elevada:"+(int)(velocidade_media[indice][b])+"Km/h");
                        double tot_premios=totalPremios(n,premios,b);
                        System.out.println("Total de premios:"+(int)tot_premios+"€\n");
                    }
                    break;
                case 12:
                     ficheiroGrandePremio(n,participantes,premios,n_provas);
                    break;
                
                    
                    

            }
        } while (operacao != 0);

    }

   

}
