package app.service.console;

import app.domain.Categorie;
import app.domain.Produit;
import app.repository.jdbc.DataSource;
import app.repository.jdbc.JdbcProduitRepository;
import app.repository.jdbc.MysqlDataSource;
import app.repository.jdbc.ProduitRepository;
import app.service.DisplayProduit;

import java.sql.ResultSet;
import java.util.Scanner;

public class ConsoleDisplayProduit implements DisplayProduit {
    DataSource dataSource = new MysqlDataSource();
    private ProduitRepository produitRepository=new JdbcProduitRepository(dataSource) ;;

    @Override
    public void afficherBienvenu() {
        System.out.println("Bienvenue sur La plateForme ");
    }

    @Override
    public void afficherMenuPrincipal() {

    System.out.println("-------------------------------------------");
    System.out.println("\t\t1\t Catégorie");
    System.out.println("\t\t2\t Produit");
    System.out.println("-------------------------------------------");
    }

    @Override
    public void afficherMenuProduit() {
        System.out.println("****************************************");
        System.out.println("1 lister des produits");
        System.out.println("2 ajouter un produit");
        System.out.println("3 modifier un produit");
        System.out.println("4 supprimer un produit");
        System.out.println("****************************************");
    }

    @Override
    public void afficherMenucategorie() {
        System.out.println("****************************************");
        System.out.println("1 lister des categories");
        System.out.println("2 ajouter une categorie");
        System.out.println("3 modifier une categorie");
        System.out.println("4 supprimer une catégorie");
        System.out.println("****************************************");

    }

    @Override
    public void afficherListeProduits(Produit[] produits) {

        System.out.println("Les produits disponibles sont:");
        for (int i = 0; i < produits.length; i++) {
            Produit produit = produits[i];
            System.out.println(String.format("> id : %s, designation :  %s, quantité : %s, prix unitaire : %s, categorie : %s", produit.getId(), produit.getDesignation(),produit.getQuantite(),produit.getPrix(),produit.getCategorie().getLibelle()));
        }
    }

    @Override
    public Produit addProduit() throws Exception {
        Produit produit = new Produit();
        Categorie categorie = new Categorie();
        Scanner sc = new Scanner(System.in);
        System.out.println("entrer le nom du produit");
        produit.setDesignation(sc.nextLine());
        System.out.println("entrer la quantité");
        produit.setQuantite(sc.nextLine());
        System.out.println("entrer le prix unitaire");
        produit.setPrix(sc.nextLine());
        System.out.println("entrer l'id de la categorie");
        int id =sc.nextInt();
        Categorie cat = produitRepository.getcatById(id);
        produit.setCategorie(cat);

        return produit;
    }

    @Override
    public void afficherOptionInconnue() {
        System.out.println("Menu Introuvable");
    }

    @Override
    public Produit updateProduit() throws Exception {
        Produit produit = new Produit();


        Scanner sc = new Scanner(System.in);

        System.out.println("entrer le nom du produit à modifier");
        produit.setDesignation(sc.nextLine());
        System.out.println("entrer la quantité");
        produit.setQuantite(sc.nextLine());
        System.out.println("entrer le prix unitaire");
        produit.setPrix(sc.nextLine());
        System.out.println("entrer l'id du produit à modifier");
        produit.setId(sc.nextInt());
        //System.out.println("entrer l'id de la categorie");
        //produit.setCategorie(cat);
        System.out.println("entrer l'id de la categorie");
        int id =sc.nextInt();
        Categorie cat = produitRepository.getcatById(id);
        produit.setCategorie(cat);
        return  produit;
    }

    @Override
    public int deleteProduit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("saisir l'id du produit à supprimer");
        return  scanner.nextInt();

    }


    //CATEGORIE


    @Override
    public void afficherListeCategories(Categorie[] categories) {
        System.out.println("Les categories disponibles sont:");
        for (int i = 0; i < categories.length; i++) {
            Categorie categorie = categories[i];
            System.out.println(String.format("> id : %s, libelle :  %s", categorie.getId(), categorie.getLibelle()));
        }
    }

    @Override
    public Categorie addCategorie() {
        Categorie categorie = new Categorie();
        Scanner sc = new Scanner(System.in);
        System.out.println("entrer le libelle de la catégorie");
        categorie.setLibelle(sc.nextLine());

        return categorie;
    }

    @Override
    public Categorie updateCategorie() {

        Categorie categorie = new Categorie();

        Scanner sc = new Scanner(System.in);

        System.out.println("entrer le libelle de la categorie à modifier");
        categorie.setLibelle(sc.nextLine());
        System.out.println("entrer l'id de la catégorie à modifier");
        categorie.setId(sc.nextInt());
        return categorie;
    }

    @Override
    public int deleteCategorie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("saisir l'id de la categorie à supprimer");
        return  scanner.nextInt();
    }

}
