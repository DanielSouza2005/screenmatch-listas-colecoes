package br.com.alura.screenmatch.desafio.Classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CartaoCredito {
    private double limite;
    private List<Compra> historicoCompra;

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public boolean lancaCompra(Compra compra) {
        if (compra.getValor() <= this.getLimite()) {
            this.setLimite(this.getLimite() - compra.getValor());
            this.historicoCompra.add(new Compra(compra.getDescricao(), compra.getValor()));

            return true;
        }

        return false;
    }

    public void listarCompras() {
        this.historicoCompra.sort(Comparator.comparing(Compra::getValor));
        this.historicoCompra.forEach(System.out::println);
    }

    public CartaoCredito(double limite) {
        this.setLimite(limite);
        this.historicoCompra = new ArrayList<>();
    }
}
