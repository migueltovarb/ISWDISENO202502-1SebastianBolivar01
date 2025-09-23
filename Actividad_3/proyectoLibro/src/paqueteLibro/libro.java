package paqueteLibro;

public class libro {
    private String name;
    private Autor autor;
    private double price;
    private int qty = 0;

    public libro(String name, Autor autor, double price) {
        this.name = name;
        this.autor = autor;
        this.price = price;
    }

    public libro(String name, Autor autor, double price, int qty) {
        this.name = name;
        this.autor = autor;
        this.price = price;
        this.qty = qty;
    }

    public String getName() { return name; }
    public Autor getAuthor() { return autor; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    @Override
    public String toString() {
        return "Book[name=" + name + "," + autor.toString() +
               ",price=" + price + ",qty=" + qty + "]";
    }
}