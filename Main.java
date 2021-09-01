package Locadora;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    // funcoes principais
    private static void alugar(Pessoa pessoa) {
        if (!verificacaoCadastroAluguel(pessoa.getCpf(), TipoVerficacao.CADASTRO)) {
            System.out.println("Você não está cadastrado, deseja se cadastrar?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            int opcao = leitor.nextInt();
            if (opcao == 1) {
                cadastrar(pessoa);
                imprimirMenuItens();
                opcao = leitor.nextInt();
                Item itemEscolhido = escolherItem(opcao);
                pessoa.setTemAlugel(true);
                Aluguel aluguel =  new Aluguel(pessoa, itemEscolhido);
                listaAlugueis.add(aluguel);
                System.out.println("Item alugado com sucesso\n");
                return;

            } else if (opcao == 2) {
                System.exit(0);
            } else {
                System.out.println("Opcao invalida");
                alugar(pessoa);
            }
        }
        if (verificacaoCadastroAluguel(pessoa.getCpf(), TipoVerficacao.ALUGUEL)) {
            System.out.println("Voce ja possui um aluguel");
            System.out.println("Deseja devovler ?");
        }else{
            imprimirMenuItens();
            int opcao =  leitor.nextInt();
            Item itemEscolhido = escolherItem(opcao);
            pessoa.setTemAlugel(true);
            Aluguel aluguel =  new Aluguel(pessoa, itemEscolhido);
            listaAlugueis.add(aluguel);
        }

    }

    private  static void devolver(Pessoa pessoa){

        for (Pessoa listaPessoa : listaPessoas) {
            if (pessoa.getCpf().equals(listaPessoa.getCpf())){
                pessoa = listaPessoa;
            }
        }

        if (pessoa.getTemAlugel()) {
            String cpf = pessoa.getCpf();
            Aluguel aluguel = null;

            for (Aluguel listaAluguel : listaAlugueis) {
                if (cpf.equals(listaAluguel.getPessoa().getCpf())) {
                    aluguel = listaAluguel;
                    double valorPendente = ChronoUnit.DAYS.between(aluguel.getLocalDate(), LocalDate.now()) * listaAluguel.getItem().getPrecoDiario();
                    aluguel.getPessoa().setTemAlugel(false);
                    aluguel.getItem().setDisponibilidade(true);
                    aluguel.pessoa = null;
                    aluguel.item = null;
                    listaAlugueis.remove(aluguel);
                    System.out.println("O valor a ser pago pelo item alugado é de : " + valorPendente);
                    break;
                }
            }
        }else
            System.out.println("Você não possui um item alugado");
    }

    private static void cadastrar(Pessoa pessoa) {
        if (listaPessoas.isEmpty()) {
            pessoa.setEstaCadastrado(true);
            listaPessoas.add(pessoa);
            return;
        }

        for (Pessoa listaPessoa : listaPessoas) {
            if (listaPessoa.getCpf().equals(pessoa.getCpf())) {
                System.out.println("Usuário ja cadastrado");
                return;
            } else {
                listaPessoas.add(pessoa);
                pessoa.setEstaCadastrado(true);
                return;
            }
        }

    }

    public static Item escolherItem(int opcao) {
        Item item = tipoInstancia(opcao);
        boolean disponibilidade  = false;

        for (Item listaIten : listaItens) {
            if (item instanceof Datashow && listaIten instanceof Datashow && listaIten.getDisponibilidade()) {
                disponibilidade = listaIten.getDisponibilidade();
                listaIten.setDisponibilidade(false);
                item = listaIten;
                break;
            } else if (item instanceof Desktop && listaIten instanceof Desktop && listaIten.getDisponibilidade()) {
                disponibilidade = listaIten.getDisponibilidade();
                listaIten.setDisponibilidade(false);
                item = listaIten;
                break;
            } else if (item instanceof Laptop && listaIten instanceof Laptop && listaIten.getDisponibilidade()) {
                disponibilidade = listaIten.getDisponibilidade();
                listaIten.setDisponibilidade(false);
                item = listaIten;
                break;
            } else if (item instanceof TelefoneCelular && listaIten instanceof TelefoneCelular && listaIten.getDisponibilidade()) {
                disponibilidade = listaIten.getDisponibilidade();
                listaIten.setDisponibilidade(false);
                item = listaIten;
                break;
            }
        }
        if (!disponibilidade) {
            System.out.println("Nao há itens disponiveis");
            return escolherItem(opcao);
        }
        return item;


    }
    // fim das funcoes principais

    //funçoes de verificacao
    public static boolean verificacaoCadastroAluguel(String cpf, TipoVerficacao tipoVerficacao) {
        boolean booleano = false;
        for (Pessoa listaPessoa : listaPessoas) {
            if (cpf.equals((listaPessoa.getCpf()))) {
                if (tipoVerficacao == TipoVerficacao.CADASTRO) {
                    booleano = listaPessoa.getEstaCadastrado();
                    break;
                } else if (tipoVerficacao == TipoVerficacao.ALUGUEL) {
                    booleano = listaPessoa.getTemAlugel();
                    break;
                }
            }
        }
        return booleano;
    }

    private static Item tipoInstancia(int opcao) {
        Item item = null;
        if (opcao == 1) {
            item = new Datashow();
        } else if (opcao == 2) {
            item = new Desktop();
        } else if (opcao == 3) {
            item = new Laptop();
        } else if (opcao == 4) {
            item = new TelefoneCelular();
        } else if (opcao == 0) {
            System.exit(0);
        } else {
            System.out.println("Opcao invalida");
            tipoInstancia(opcao);
        }
        return item;
    }
    // fim das funcoes de veificao

    //funcao de controle de fluxo
    private static void tratarRespostaMenu(int opcao) {
        if (opcao == 1) {
            System.out.println("digite seu nome");
            String nome = leitor.next();
            System.out.println("digite seu cpf");
            String cpf = leitor.next();
            Pessoa pessoa = new Pessoa(nome, cpf);
            alugar(pessoa);
        } else if (opcao == 2) {
            System.out.println("digite seu nome");
            String nome = leitor.next();
            System.out.println("digite seu cpf");
            String cpf = leitor.next();
            Pessoa pessoa = new Pessoa(nome, cpf);
          devolver(pessoa);
        } else if (opcao == 0) {
            System.out.println("fim");
        } else {
            System.out.println("opcao invalida");
        }
    }

    //Funçoes de inicacao de ambiente
    private static void iniciarItens() {
        iniciarQuantidades(new Datashow());
        iniciarQuantidades(new Desktop());
        iniciarQuantidades(new Laptop());
        iniciarQuantidades(new TelefoneCelular());
    }

    private static <T> void iniciarQuantidades(T item) {
        if (item instanceof Datashow) {
            for (int i = 0; i < quantidadeItens; i++)
                listaItens.add(new Datashow());
        } else if (item instanceof Desktop) {
            for (int i = 0; i < quantidadeItens; i++)
                listaItens.add(new Desktop());
        } else if (item instanceof Laptop) {
            for (int i = 0; i < quantidadeItens; i++)
                listaItens.add(new Laptop());
        } else {
            for (int i = 0; i < quantidadeItens; i++)
                listaItens.add(new TelefoneCelular());
        }

    }

    private static void imprimirMenuItens() {
        System.out.print("Escolha o item a ser alugado\n" +
                "1 - Datashow\n" +
                "2 - Desktop\n" +
                "3 - laptop\n" +
                "4 - telefone celular\n" +
                "0 - Finalizar\n");

    }

    private static void imprimirMenuPrincipal() {
        System.out.println("ESCOLHA UMA DAS OPÇÕES");
        System.out.println("1 - Alugar");
        System.out.println("2 - Devolver");
        System.out.println("0 - Para sair");
    }
    // fim das funcoes de iniciacao

    //modificador de quantidade de itens
    public void setQuantidadeItens(int quantidadeItens) {
        Main.quantidadeItens = quantidadeItens;
    }

    //variaveis e estruturas necessarias para o programa
    private static final Scanner leitor = new Scanner(System.in);
    private static int quantidadeItens = 5;
    private static final ArrayList<Item> listaItens = new ArrayList<>();
    private static final ArrayList<Pessoa> listaPessoas = new ArrayList<>();
    private static final ArrayList<Aluguel> listaAlugueis = new ArrayList<>();


    public static void main(String[] args) {
         int opcao;
         iniciarItens();
         do {
             imprimirMenuPrincipal();
             opcao = leitor.nextInt();
             tratarRespostaMenu(opcao);
         } while (opcao != 0);
    }
}