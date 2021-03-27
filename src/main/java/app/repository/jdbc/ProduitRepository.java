package app.repository.jdbc;

import app.domain.Categorie;
import app.domain.Produit;

public interface ProduitRepository {
    int add(Produit produit) throws Exception;
    int update(Produit produit);
    int delete(int id);

    public Categorie getcatById (int id) throws Exception;
    Produit[] getAll();
}
