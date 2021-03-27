package app.controller;

import app.repository.jdbc.*;
import app.service.DisplayProduit;

import app.service.console.ConsoleDisplayProduit;

import app.service.console.ScannerMenuProduit;

public class ProduitController {

    private final DisplayProduit displayProduit;
    private final ScannerMenuProduit scannerMenuProduit;
    public ProduitController() {
        displayProduit = new ConsoleDisplayProduit();
        DataSource dataSource = new MysqlDataSource();
        ProduitRepository produitRepository = new JdbcProduitRepository(dataSource) ;
        CategorieRepository categorieRepository = new JdbcCategorieRepository(dataSource);
        scannerMenuProduit = new ScannerMenuProduit(produitRepository,categorieRepository,displayProduit);

    }

    public void process() throws Exception {

            displayProduit.afficherBienvenu();
            displayProduit.afficherMenuPrincipal();
            scannerMenuProduit.afficherMenu();

    }
}
