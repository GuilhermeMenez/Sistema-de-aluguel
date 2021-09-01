package Locadora;

import java.time.LocalDate;

public class Aluguel {
    Pessoa pessoa;
    Item item;
    protected LocalDate localDate;

    public Aluguel(Pessoa pessoa,Item item) {
        this.pessoa = pessoa;
        this.item = item;
        this.localDate = LocalDate.now().minusDays(1);

    }

    public Aluguel(){}

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Item getItem() {
        return item;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
