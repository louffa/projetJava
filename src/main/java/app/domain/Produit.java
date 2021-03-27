package app.domain;

public class Produit {
    private int id;
    private String designation;
    private  String quantite;
    private String prix;
    private Categorie categorie;

    public Produit(int id, String designation, String quantite, String prix, Categorie categorie) {
        this.id = id;
        this.designation = designation;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Produit() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


}
