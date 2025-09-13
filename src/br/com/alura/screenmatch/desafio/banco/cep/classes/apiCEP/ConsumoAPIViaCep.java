package br.com.alura.screenmatch.desafio.banco.cep.classes.apiCEP;

import br.com.alura.screenmatch.desafio.banco.cep.classes.endereco.Endereco;
import br.com.alura.screenmatch.desafio.banco.cep.classes.endereco.EnderecoDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConsumoAPIViaCep {
    private String endereco;
    private final String linkAPI = "https://viacep.com.br/ws/";
    public List<Endereco> enderecoList = new ArrayList<>();

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    private String getFullAPIEndereco() {
        return this.linkAPI + this.getEndereco() + "/json/";
    }

    public Endereco ConsultarAPIViaCep(String endereco) throws IOException, InterruptedException {
        this.setEndereco(endereco.replaceAll("\\D+", ""));

        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(getFullAPIEndereco()))
                    .build();

            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            EnderecoDTO enderecoCEPDTO = gson.fromJson(response.body(), EnderecoDTO.class);

            Endereco enderecoCEP = new Endereco(enderecoCEPDTO.cep(),
                    enderecoCEPDTO.logradouro(),
                    enderecoCEPDTO.complemento(),
                    enderecoCEPDTO.bairro(),
                    enderecoCEPDTO.uf(),
                    enderecoCEPDTO.estado(),
                    enderecoCEPDTO.regiao(),
                    enderecoCEPDTO.ibge());

            enderecoList.add(enderecoCEP);

            return enderecoCEP;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar API ViaCep", e);
        }
    }
}
