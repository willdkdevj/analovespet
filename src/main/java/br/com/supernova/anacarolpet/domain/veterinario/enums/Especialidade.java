package br.com.supernova.anacarolpet.domain.veterinario.enums;

public enum Especialidade {
    MANIFERO("Manífero"),
    REPTIL("Réptil"),
    INVERTEBRADO("Invertebrado");

    final String value;

    Especialidade(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
