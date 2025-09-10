package br.com.alura.screenmatch;

import br.com.alura.screenmatch.excecao.ErroDeConversaoAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite um Filme: ");

        String busca = leitura.nextLine();
        String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=15fd6310";

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            TituloOMDB tituloOMBD = gson.fromJson(response.body(), TituloOMDB.class);
            System.out.println(tituloOMBD);

            Titulo titulo = new Titulo(tituloOMBD);
            System.out.println(titulo + "" + titulo.getAnoDeLancamento() + "" + titulo.getDuracaoEmMinutos());
        } catch (NumberFormatException | ErroDeConversaoAnoException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
