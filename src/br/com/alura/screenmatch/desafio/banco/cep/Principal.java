package br.com.alura.screenmatch.desafio.banco.cep;

import br.com.alura.screenmatch.desafio.banco.cep.classes.apiCEP.ConsumoAPIViaCep;
import br.com.alura.screenmatch.desafio.banco.cep.classes.files.FileGenerator;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        String busca = "";
        Scanner leitura = new Scanner(System.in);
        ConsumoAPIViaCep cepAPI = new ConsumoAPIViaCep();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um CEP: ");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            try {
                cepAPI.ConsultarAPIViaCep(busca);
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
                continue;
            }

        }

        if (!cepAPI.enderecoList.isEmpty()) {
            FileGenerator files = new FileGenerator(cepAPI.enderecoList);
            files.generateJSONFile();
        }

    }
}
