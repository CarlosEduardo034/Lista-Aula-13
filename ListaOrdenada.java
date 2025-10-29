import java.util.Random;

class Nodo {
    int valor;
    Nodo anterior;
    Nodo proximo;

    public Nodo(int valor) {
        this.valor = valor;
        this.anterior = null;
        this.proximo = null;
    }
}

class ListaDuplamenteEncadeada {
    private Nodo inicio;
    private Nodo fim;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void inserirOrdenado(int valor) {
        Nodo novo = new Nodo(valor);

        if (inicio == null) { 
            inicio = novo;
            fim = novo;
        } else if (valor < inicio.valor) { 
            novo.proximo = inicio;
            inicio.anterior = novo;
            inicio = novo;
        } else if (valor >= fim.valor) { 
            fim.proximo = novo;
            novo.anterior = fim;
            fim = novo;
        } else {
            Nodo atual = inicio;
            while (atual != null && atual.valor <= valor) {
                atual = atual.proximo;
            }
            novo.proximo = atual;
            novo.anterior = atual.anterior;
            atual.anterior.proximo = novo;
            atual.anterior = novo;
        }
        tamanho++;
    }

    public void exibirCrescente() {
        Nodo atual = inicio;
        System.out.print("lista crescente: ");
        while (atual != null) {
            System.out.print(atual.valor);
            if (atual.proximo != null) System.out.print(", ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public void exibirDecrescente() {
        Nodo atual = fim;
        System.out.print("lista decrescente: ");
        while (atual != null) {
            System.out.print(atual.valor);
            if (atual.anterior != null) System.out.print(", ");
            atual = atual.anterior;
        }
        System.out.println();
    }

    private boolean ehPrimo(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public void removerPrimos() {
        Nodo atual = inicio;
        while (atual != null) {
            if (ehPrimo(Math.abs(atual.valor))) {
                Nodo remover = atual;
                if (remover.anterior != null)
                    remover.anterior.proximo = remover.proximo;
                else
                    inicio = remover.proximo;

                if (remover.proximo != null)
                    remover.proximo.anterior = remover.anterior;
                else
                    fim = remover.anterior;

                tamanho--;
            }
            atual = atual.proximo;
        }
    }
}

public class ListaOrdenada {
    public static void main(String[] args) {
        int[] vetor = new int[1000];
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        Random gerador = new Random();

        System.out.println("vetor gerado:");
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = gerador.nextInt(19999) - 9999; 
            System.out.print(vetor[i] + (i < vetor.length - 1 ? ", " : ""));
        }

        System.out.println("\n\ninserindo elementos na lista...");
        for (int i = 0; i < vetor.length; i++) {
            lista.inserirOrdenado(vetor[i]);
        }

        System.out.println("\nlista completa em ordem crescente:");
        lista.exibirCrescente();

        System.out.println("\nlista completa em ordem decrescente:");
        lista.exibirDecrescente();

        System.out.println("\nremovendo números primos da lista...");
        lista.removerPrimos();

        System.out.println("\nlista sem números primos (ordem crescente):");
        lista.exibirCrescente();

        System.out.println("\nlista sem números primos (ordem decrescente):");
        lista.exibirDecrescente();
    }
}
