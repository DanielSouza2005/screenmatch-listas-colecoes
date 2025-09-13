package br.com.alura.screenmatch.desafio.banco.cep.classes.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileGenerator {
    public List list = new ArrayList();

    public FileGenerator(List list) {
        this.list = list;
    }

    public void generateJSONFile() throws IOException {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            FileWriter arquivo = new FileWriter("file.json");
            arquivo.write(gson.toJson(this.list));
            arquivo.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gravar o arquivo", e);
        }

    }

}
