package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PrincipalListas {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("O poderoso chefão");
        Serie lost = new Serie("Lost");
        Filme outroFilme = new Filme("Avatar");

        meuFilme.avalia(8);
        outroFilme.avalia(4);

        meuFilme.setDuracaoEmMinutos(180);
        outroFilme.setDuracaoEmMinutos(200);
        lost.setDuracaoEmMinutos(30);

        var listaAssistidos = new ArrayList<Titulo>();
        listaAssistidos.add(meuFilme);
        listaAssistidos.add(lost);
        listaAssistidos.add(outroFilme);

//        listaAssistidos.forEach(System.out::println);
        for (Titulo item : listaAssistidos){
            System.out.println(item.getNome());

            if (item instanceof Filme filme && filme.getClassificacao() > 2) {
                System.out.println(filme.getClassificacao());
            }
        }

        ArrayList<String> listaNomes = new ArrayList<>();
        listaNomes.add("Teste");
        listaNomes.add("Daniel");

        System.out.println(listaNomes);
        Collections.sort(listaNomes);
        System.out.println(listaNomes);

        System.out.println(listaAssistidos);
        Collections.sort(listaAssistidos);
        System.out.println(listaAssistidos);

        listaAssistidos.sort(Comparator.comparing(Titulo::getDuracaoEmMinutos));
        System.out.println(listaAssistidos);
    }
}
