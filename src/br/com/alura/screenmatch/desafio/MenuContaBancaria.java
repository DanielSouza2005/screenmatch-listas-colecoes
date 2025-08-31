package br.com.alura.screenmatch.desafio;

import br.com.alura.screenmatch.desafio.Classes.CartaoCredito;
import br.com.alura.screenmatch.desafio.Classes.Compra;

import java.util.*;

public class MenuContaBancaria {

    private int opcaoMenu;
    private CartaoCredito cartao;

    public int getOpcaoMenu() {
        return opcaoMenu;
    }

    public void setOpcaoMenu(int opcaoMenu) {
        this.opcaoMenu = opcaoMenu;
    }

    public void efetuarCompra() {
        Scanner scanner = new Scanner(System.in);
        String descricaoCompra = "";
        double valorCompra = 0;

        System.out.println("Digite a descrição da Compra: ");
        descricaoCompra = scanner.nextLine();

        System.out.println("Digite o valor da Compra: ");
        valorCompra = scanner.nextDouble();
        scanner.nextLine();

        if (cartao.lancaCompra(new Compra(descricaoCompra, valorCompra))) {
            System.out.println("Compra Realizada com Sucesso!");
            System.out.println("Saldo atual: " + cartao.getLimite());
            System.out.println("Digite 0 para sair ou 1 para continuar");

            this.setOpcaoMenu(scanner.nextInt());

            if (this.getOpcaoMenu() == 0) {
                finalizar();
            }
        } else {
            System.out.println("Saldo Insuficiente :(");
            finalizar();
            this.setOpcaoMenu(0);
        }
    }

    public void finalizar() {
        System.out.println("*****************************************");
        System.out.println("Histórico de Compras");

        cartao.listarCompras();

        System.out.println("*****************************************");
        System.out.println("Limite do Cartão: " + cartao.getLimite());
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o Limite do Cartão:");
        this.cartao = new CartaoCredito(scanner.nextDouble());
        scanner.nextLine();

        do {
            efetuarCompra();
        } while (this.getOpcaoMenu() == 1);
    }

    public MenuContaBancaria() {
        this.setOpcaoMenu(1);
        System.out.println("Bem vindo ao Banco!");
    }

    public static void main(String[] args) {
        MenuContaBancaria banco = new MenuContaBancaria();
        banco.iniciar();
    }
}
