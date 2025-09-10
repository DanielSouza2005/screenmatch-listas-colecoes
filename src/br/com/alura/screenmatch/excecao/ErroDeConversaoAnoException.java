package br.com.alura.screenmatch.excecao;

public class ErroDeConversaoAnoException extends RuntimeException {
    private String mensagem;

    public ErroDeConversaoAnoException(String s) {
        this.mensagem = s;
    }

    @Override
    public String getMessage(){
        return this.mensagem;
    }
}
