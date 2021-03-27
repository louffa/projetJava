package app.service;

import app.domain.Categorie;
import app.domain.Produit;

public interface DisplayProduit {
    void afficherBienvenu();
    void afficherMenuPrincipal();
    void afficherMenuProduit();
    void afficherMenucategorie();
    void afficherListeProduits(Produit[] produits);

    Produit addProduit() throws Exception;
    void  afficherOptionInconnue();
    Produit updateProduit() throws Exception;
    int deleteProduit();

    void afficherListeCategories(Categorie[] categories);
    Categorie addCategorie();
    Categorie updateCategorie();
    int deleteCategorie();
}
