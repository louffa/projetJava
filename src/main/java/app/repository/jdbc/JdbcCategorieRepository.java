package app.repository.jdbc;

import app.domain.Categorie;
import app.domain.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCategorieRepository implements CategorieRepository{
    private DataSource dataSource;

    public JdbcCategorieRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int add(Categorie categorie) {
        int ok = 0;
        String query = "INSERT INTO categorie (libelle) VALUES(?)";
        try {

            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,categorie.getLibelle());

            ok = statement.executeUpdate();

            if (ok > 0) {
                System.out.println("catégorie ajoutée avec succés!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Categorie categorie) {
        String query = "UPDATE  categorie SET libelle=? where id=?";
        int ok = 0;
        try {

            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,categorie.getLibelle());

            statement.setInt(2, categorie.getId());
            ok = statement.executeUpdate();

            System.out.println(statement);
            ok = statement.executeUpdate();
            if (ok > 0) {
                System.out.println("catégorie modifiée avec succés !!!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM categorie where id=?";
        int ok =0;
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ok = statement.executeUpdate();
            if (ok > 0) {
                System.out.println("catégorie supprimée avec succés !!!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public Categorie[] getAll() {
        String query = "SELECT * FROM categorie";
        //mapper le résultat
        List<Categorie> categories = new ArrayList<Categorie>();

        try {
            Connection connection = dataSource.createConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query) ;

            while (rs.next()) {
                int id = rs.getInt("id");
                String libelle = rs.getString("libelle");

                //mapping retour db (relationnel) avec objet Offre
                Categorie categorie = new Categorie( id, libelle);

                categories.add(categorie);
            }
            return categories.toArray(new Categorie[0]);

        }
        catch (SQLException e) {
            return new Categorie[0];
        }
        catch (Exception ex){
            return new Categorie[0];
        }
    }
}
