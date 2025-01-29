import java.util.Scanner;

class Item {
    String nome;
    int peso;

    Item(String nome, int peso) {
        this.nome = nome;
        this.peso = peso;
    }
}

class Mochila {

    private Item[] itens;
    private Scanner sc;
    private static final int PESO_MAXIMO = 15; // Limite máximo de peso da mochila

    public Mochila() {
        itens = new Item[5]; // Capacidade máxima de 5 itens
        itens[0] = new Item("laranja", 2);
        itens[1] = new Item("morango", 3);
        itens[2] = new Item("uva", 1);
        itens[3] = new Item("tangerina", 3);
        itens[4] = null; // Espaço vazio para o tomate

        sc = new Scanner(System.in);
        String menu;
        do {
            System.out.println("Menu: ");
            System.out.println("A - Inserir Tomate");
            System.out.println("B - Imprimir Mochila");
            System.out.println("C - Valor Item Mais Pesado");
            System.out.println("D - Ordenar Mochila");
            System.out.println("E - Excluir Item Mochila");
            System.out.println("S - Sair");
            String men = sc.next();
            menu = men.toUpperCase();

            switch (menu) {
                case "A":
                    inserirTomate();
                    break;
                case "B":
                    imprimirMochila();
                    break;
                case "C":
                    valorItemMaisPesado();
                    break;
                case "D":
                    ordenarMochila();
                    break;
                case "E":
                    excluirItemMochila();
                    break;
                case "S":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (!menu.equals("S"));
    }

    private void inserirTomate() {
        if (itens[4] != null) {
            System.out.println("\nJá existe um tomate na mochila.");
            return;
        }

        System.out.println("Você deseja adicionar tomate a sua mochila? (Digite com sim, ou nao)");
        String resposta = sc.next();
        if (resposta.equalsIgnoreCase("sim")) {
            System.out.println("\nDigite o peso do Tomate:");
            int pesoTomate = sc.nextInt();

            // Verifica se o peso total da mochila + o peso do tomate excede o limite
            if (calcularPesoTotal() + pesoTomate > PESO_MAXIMO) {
                System.out.println("\nNão é possível adicionar o tomate. Peso máximo da mochila excedido.");
                return;
            }

            if (pesoTomate <= 6) {
                itens[4] = new Item("tomate", pesoTomate);
                System.out.println("\nTomate adicionado à mochila.");
                imprimirMochila();
            } else {
                System.out.println("\nO peso do tomate excede o limite permitido.");
            }
        } else {
            System.out.println("\nVocê não adicionou o tomate a sua mochila.");
        }
    }

    private void imprimirMochila() {
        if (mochilaVazia()) {
            System.out.println("\nA mochila está vazia.");
            return;
        }

        System.out.println("\nMochila Atual: ");
        for (Item item : itens) {
            if (item != null) {
                System.out.println(item.nome + " - Peso: " + item.peso);
            }
        }
        System.out.println("Peso total da mochila: " + calcularPesoTotal());
    }

    private void valorItemMaisPesado() {
        Item itemMaisPesado = null;
        for (Item item : itens) {
            if (item != null && (itemMaisPesado == null || item.peso > itemMaisPesado.peso)) {
                itemMaisPesado = item;
            }
        }
        if (itemMaisPesado != null) {
            System.out.println("\nO item mais pesado é: " + itemMaisPesado.nome + " com peso " + itemMaisPesado.peso);
        } else {
            System.out.println("\nA mochila está vazia.");
        }
    }

    private void ordenarMochila() {
        for (int i = 0; i < itens.length - 1; i++) {
            for (int j = i + 1; j < itens.length; j++) {
                if (itens[i] != null && itens[j] != null && itens[i].peso < itens[j].peso) {
                    Item temp = itens[i];
                    itens[i] = itens[j];
                    itens[j] = temp;
                }
            }
        }
        System.out.println("Mochila ordenada por peso.");
        imprimirMochila();
    }

    private void excluirItemMochila() {
        if (mochilaVazia()) {
            System.out.println("\nA mochila está vazia. Nada para excluir.");
            return;
        }

        System.out.println("\nVocê deseja excluir um item da mochila?  (Digite com sim, ou nao)");
        String resposta = sc.next();
        if (resposta.equalsIgnoreCase("sim")) {
            System.out.println("\nDigite o nome do item que você deseja excluir: ");
            String itemExcluir = sc.next();
            boolean itemEncontrado = false;
            for (int i = 0; i < itens.length; i++) {
                if (itens[i] != null && itens[i].nome.equalsIgnoreCase(itemExcluir)) {
                    itens[i] = null;
                    itemEncontrado = true;
                    System.out.println("\nItem excluído com sucesso.");
                    imprimirMochila();
                    break;
                }
            }
            if (!itemEncontrado) {
                System.out.println("\nItem não encontrado na mochila.");
            }
        }
    }

    private boolean mochilaVazia() {
        for (Item item : itens) {
            if (item != null) {
                return false;
            }
        }
        return true;
    }

    private int calcularPesoTotal() {
        int pesoTotal = 0;
        for (Item item : itens) {
            if (item != null) {
                pesoTotal += item.peso;
            }
        }
        return pesoTotal;
    }

    public static void main(String[] args) {
        Mochila mochila = new Mochila();
        mochila.sc.close(); // Fechar o Scanner
    }
}