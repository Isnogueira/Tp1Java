package br.edu.infnet.tp1java;

import java.util.Scanner;

public class SistemaAcademico {

    private static String[] nomes;
    private static float[] avs1;
    private static float[] avs2;

    private static int index;

    private static final int QTD = 100;

   
    public static void main(String[] args) {
        
        nomes = new String[QTD];
        avs1 = new float[QTD];
        avs2 = new float[QTD];

        String opcao = null;

        do {
            System.out.println("==================");
            System.out.println("Sistema Escolar");
            System.out.println("==================");
            System.out.println("Escolha uma opção:");
            System.out.println("[1] Registrar aluno");
            System.out.println("[2] Consultar aluno");
            System.out.println("[3] Notas da turma");
            System.out.println("[4] Sair");
            
            opcao = ler().next();
            
            switch (opcao) {
                case "1" -> {
                    if (index < QTD){ 
                        System.out.println("Informe o nome do aluno: ");
                        nomes[index] = ler().nextLine();
                        System.out.println("Informe a nota da AV1: ");
                        avs1[index] = ler().nextFloat();
                        System.out.println("Informe a nota da AV2: ");
                        avs2[index] = ler().nextFloat();

                        System.out.println("Aluno cadastrado com sucesso!");

                        index++;
                    } else{
                        System.out.println("Amazenamento cheio.");
                    }
                }

                case "2" -> {
                    System.out.println("Informe a posiçâo do aluno: ");
                    int idx = ler().nextInt();
                    
                    if (idx >= 0 && idx < index){
                        imprimir(idx);
                    } else {
                        System.out.println("O aluno da posição " + idx + " não existe.");
                    }
                }
                case "3" -> {
                     System.out.println("===========================");
                    System.out.println("Alunos contidos no sistema");
                    System.out.println("===========================");
                    for(int i = 0; i < index; i++){
                        imprimir(i);
                    }
                }
                case "4" -> {
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("4"));

        exibirRelatorio();
        
        ler().close();
    }
    
    private static Scanner ler(){
        Scanner in = new Scanner(System.in);
        return in;
    }

    private static float calcularMediaAluno(int idx){
        return (avs1[idx] + avs2[idx])/ 2;
    }
    
    private static String ObterSituacaoAluno(float media){
        
        if (media < 4){
            return "reprovado";
        }else if(media >= 4 && media < 7){
            return "Em prova final";
        }else{
            return "Aprovado";
        } 
    } 
    
    private static void imprimir(int idx) {
        
        float media = calcularMediaAluno(idx);
        
        System.out.printf("N° %d\nNome: %s\nAV1: %.2f\nAV2: %.2f\nMédia: %.2f\nSituação: %s\n",
                idx + 1,
                nomes[idx],
                avs1[idx],
                avs2[idx],
                media,
                ObterSituacaoAluno(media));
        System.out.println("----------------------------------");
    }
    
    private static float calcularMediaTurma(){
        float soma = 0;
    
        for (int i = 0; i < index; i++) {
            soma += calcularMediaAluno(i);
        }
        return soma / index;
    }
    private static void exibirRelatorio(){
        int qtd = index;
        
        System.out.println("=========");
        System.out.println("Relatório");
        System.out.println("=========");
        System.out.println("Alunos na turma: " + qtd);
        System.out.printf("Média da turma: %.2f", calcularMediaTurma());
        
    }    
}