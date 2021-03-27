package app.repository.jdbc;

import app.domain.Categorie;


public interface CategorieRepository {
    int add(Categorie categorie);
    int update(Categorie categorie);
    int delete(int id);

    Categorie[] getAll();
}
