package Locadora;

import java.time.LocalDate;

public abstract class Item {

    protected boolean disponibilidade;
    protected double preco;
    protected LocalDate localDate;



    public Item() {
        this.disponibilidade = true;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public double getPrecoDiario() {
        return preco;
    }


}
