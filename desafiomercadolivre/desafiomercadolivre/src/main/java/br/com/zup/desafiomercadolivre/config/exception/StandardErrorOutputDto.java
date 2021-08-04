package br.com.zup.desafiomercadolivre.config.exception;

import org.springframework.http.HttpStatus;

public class StandardErrorOutputDto {
    private String message;
    private HttpStatus status;

    public StandardErrorOutputDto(RuntimeException ex, HttpStatus status) {
        this.message = ex.getMessage();
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status.value();
    }
}