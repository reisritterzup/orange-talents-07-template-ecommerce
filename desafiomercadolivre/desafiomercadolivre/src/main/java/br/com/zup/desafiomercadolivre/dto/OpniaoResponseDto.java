package br.com.zup.desafiomercadolivre.dto;
import br.com.zup.desafiomercadolivre.model.Opniao;

public class OpniaoResponseDto {

    public OpniaoResponseDto() {
    }

    public OpniaoResponseDto(Opniao opniao) {
        this.nota = opniao.getNota();
        this.titulo = opniao.getTitulo();
        this.descricao = opniao.getDescricao();
        this.produto = opniao.getProduto().getNome();
        this.usuario = opniao.getUsuario().getEmail();
    }

    public OpniaoResponseDto(int nota, String titulo, String descricao, String produto, String usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    private int nota;
    private String titulo;
    private String descricao;
    private String produto;
    private String usuario;

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getProduto() {
        return produto;
    }

    public String getUsuario() {
        return usuario;
    }
}
