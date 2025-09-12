package br.com.alura.screenmatch;

import br.com.alura.screenmatch.excecao.ErroDeConversaoAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> tituloList = new ArrayList<>();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um Filme: ");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=15fd6310";

            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = httpClient
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.body());

                TituloOMDB tituloOMBD = gson.fromJson(response.body(), TituloOMDB.class);
                System.out.println(tituloOMBD);

                Titulo titulo = new Titulo(tituloOMBD);
                System.out.println(titulo + " " + titulo.getAnoDeLancamento() + " " + titulo.getDuracaoEmMinutos());

                tituloList.add(titulo);
            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Algum erro de argumento na busca, verifique o endereço");
            } catch (ErroDeConversaoAnoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(tituloList);
        FileWriter arquivo = new FileWriter("filmes.json");
        arquivo.write(gson.toJson(tituloList));
        arquivo.close();

    }
}
