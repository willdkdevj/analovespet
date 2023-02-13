package br.com.supernova.anacarolpet.domain.veterinario.common;

public enum Especialidade {
    MANIFERO("Manífero"),
    REPTEL("Réptil"),
    INVERTEBRADO("Invertebrado");

    final String value;

    Especialidade(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
