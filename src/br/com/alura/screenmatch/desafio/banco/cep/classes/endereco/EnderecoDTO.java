package br.com.alura.screenmatch.desafio.banco.cep.classes.endereco;

public record EnderecoDTO(String cep,
                          String logradouro,
                          String complemento,
                          String bairro,
                          String uf,
                          String estado,
                          String regiao,
                          String ibge) {
}
