package agenda;

public class Telefone {

    private String ddd;
    private Long numero;

    public Telefone() {
    }

    public Telefone(String ddd, Long numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "(" + this.ddd + ") " + this.numero;
    }

}
