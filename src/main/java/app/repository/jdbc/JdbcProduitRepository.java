package app.repository.jdbc;

import app.domain.Categorie;
import app.domain.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProduitRepository implements ProduitRepository{
    private DataSource dataSource;

    public JdbcProduitRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int add(Produit produit) throws Exception {
        int ok = 0;
        //int idcat= getcatById(produit.getCategorie().getId());
        String query = "INSERT INTO produit (designation,quantite,prix,id_categorie) VALUES(?,?,?,?)";
        try {

            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,produit.getDesignation());
            statement.setString(2, produit.getQuantite());
            statement.setString(3, produit.getPrix());
            statement.setInt(4, produit.getCategorie().getId());
            ok = statement.executeUpdate();

            if (ok > 0) {
                System.out.println("produit créé avec succés!");
            }
            return ok;
        }
        catch (Exception ex){
            //ex.printStackTrace();
            System.out.println("Erreur lors de l\'insertion ! Cette catégorie n\'existe pas ");
        }
        return 0;
    }

    @Override
    public int update(Produit produit) {
        String query = "UPDATE  produit SET designation=?, quantite=?, prix=?, id_categorie=?  where id=?";
        int ok = 0;
        try {

            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,produit.getDesignation());
            statement.setString(2, produit.getQuantite());
            statement.setString(3, produit.getPrix());
            statement.setInt(4, produit.getCategorie().getId());
            statement.setInt(5, produit.getId());
            ok = statement.executeUpdate();

            System.out.println(statement);
            ok = statement.executeUpdate();
            if (ok > 0) {
                System.out.println("produit modifié avec succés !!!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Erreur lors de la modification !!!");
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM produit where id=?";
        int ok =0;
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ok = statement.executeUpdate();
            if (ok > 0) {
                System.out.println("produit supprimé avec succés !!!");
            }
            return ok;
        }
        catch (Exception ex){
            //ex.printStackTrace();
            System.out.println("Erreur lors de la suppression ");
        }
        return 0;
    }

    @Override
    public Categorie getcatById(int id) throws Exception {
        String query = "SELECT id, libelle FROM categorie where id=?";

        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                int retrievedId = rs.getInt("id");
                String retrievedlibelle = rs.getString("libelle");
                Categorie categorie = new Categorie(retrievedId, retrievedlibelle);
                return categorie;
            }

        }
        catch (Exception ex){
            //ex.printStackTrace();
            System.out.println(" Cette catégorie n\'existe pas ");
        }
        return null;

    }


    @Override
    public Produit[] getAll() {

        String query = "SELECT * FROM produit p,categorie c where p.id_categorie = c.id";
        //mapper le résultat
        List<Produit> produits = new ArrayList<Produit>();

        try {
            Connection connection = dataSource.createConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query) ;

            while (rs.next()) {
                int id = rs.getInt("id");
                String designation = rs.getString("designation");
                String quantite = rs.getString("quantite");
                String prix = rs.getString("prix");
                int IdC = rs.getInt("id_categorie");
                String libelle = rs.getString("libelle");
                Categorie categorie = new Categorie(IdC,libelle);
                //mapping retour db (relationnel) avec objet Offre
                Produit produit = new Produit( id, designation,quantite,prix,categorie);

                produits.add(produit);
            }
            return produits.toArray(new Produit[0]);

        }
        catch (SQLException e) {
            return new Produit[0];
        }
        catch (Exception ex){
            return new Produit[0];
        }
    }
}
