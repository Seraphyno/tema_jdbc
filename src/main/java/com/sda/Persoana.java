package com.sda;

public class Persoana {

    private String nume;
    private int varsta;
    private String cnp;

    public Persoana(String nume, int varsta, String cnp) {
        this.nume = nume;
        this.varsta = varsta;
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getCnp() {
        return cnp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Persoana{");
        sb.append("nume='").append(nume).append('\'');
        sb.append(", varsta=").append(varsta);
        sb.append(", cnp='").append(cnp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
