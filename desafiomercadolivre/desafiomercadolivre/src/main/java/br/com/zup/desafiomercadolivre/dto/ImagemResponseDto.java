package br.com.zup.desafiomercadolivre.dto;

public class ImagemResponseDto {

    public ImagemResponseDto() {
    }

    public ImagemResponseDto(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    private String caminhoImagem;

    public String getCaminhoImagem() {
        return caminhoImagem;
    }
}
