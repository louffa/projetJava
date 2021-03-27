package app.service.console;

import app.domain.Categorie;
import app.domain.Produit;
import app.repository.jdbc.CategorieRepository;
import app.repository.jdbc.ProduitRepository;
import app.service.DisplayProduit;
import app.service.MenuProduit;

import java.util.Scanner;

public class ScannerMenuProduit implements MenuProduit {
    private final Scanner scanner;
    private ProduitRepository produitRepository;
    private CategorieRepository categorieRepository;
    private DisplayProduit displayProduit;

    public ScannerMenuProduit(ProduitRepository produitRepository,CategorieRepository categorieRepository, DisplayProduit displayProduit) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.displayProduit = displayProduit;
        this.scanner = new Scanner(System.in);
    }

    private int lireChoix() {
        return scanner.nextInt();
    }

    private void afficherMenu( int choix) throws Exception {

        Produit[] produits = produitRepository.getAll();
        Categorie[] categories = categorieRepository.getAll();
        switch (choix){

            case 1:
                    displayProduit.afficherMenucategorie();

                    int ch =scanner.nextInt();
                    switch (ch){
                    case 1:

                        displayProduit.afficherListeCategories(categories);
                        System.out.println("Press Any Key To Continue...");
                        new Scanner(System.in).nextLine();
                        displayProduit.afficherMenuPrincipal();
                        afficherMenu();

                        break;
                    case 2:
                        categorieRepository.add(displayProduit.addCategorie());
                        System.out.println("Press Any Key To Continue...");
                        new Scanner(System.in).nextLine();
                        displayProduit.afficherMenuPrincipal();
                        afficherMenu();
                        break;
                    case 3:
                        displayProduit.afficherListeCategories(categories);
                        categorieRepository.update(displayProduit.updateCategorie()) ;
                        System.out.println("Press Any Key To Continue...");
                        new Scanner(System.in).nextLine();
                        displayProduit.afficherMenuPrincipal();
                        afficherMenu();
                        break;
                    case 4:
                        displayProduit.afficherListeCategories(categories);
                        categorieRepository.delete(displayProduit.deleteCategorie()) ;
                        System.out.println("Press Any Key To Continue...");
                        new Scanner(System.in).nextLine();
                        displayProduit.afficherMenuPrincipal();
                        afficherMenu();
                        break;
                    default:
                        displayProduit.afficherOptionInconnue();
                }

            break;



            case 2:
                    displayProduit.afficherMenuProduit();

                    int chx =scanner.nextInt();
                    switch (chx){
                        case 1:

                            displayProduit.afficherListeProduits(produits);
                            System.out.println("Press Any Key To Continue...");
                            new Scanner(System.in).nextLine();
                            displayProduit.afficherMenuPrincipal();
                            afficherMenu();

                        break;
                        case 2:
                            produitRepository.add(displayProduit.addProduit());
                            System.out.println("Press Any Key To Continue...");
                            new Scanner(System.in).nextLine();
                            displayProduit.afficherMenuPrincipal();
                            afficherMenu();
                        break;
                        case 3:
                            displayProduit.afficherListeProduits(produits);
                            produitRepository.update(displayProduit.updateProduit()) ;
                            System.out.println("Press Any Key To Continue...");
                            new Scanner(System.in).nextLine();
                            displayProduit.afficherMenuPrincipal();
                            afficherMenu();
                        break;
                        case 4:
                            displayProduit.afficherListeProduits(produits);
                            produitRepository.delete(displayProduit.deleteProduit()) ;
                            System.out.println("Press Any Key To Continue...");
                            new Scanner(System.in).nextLine();
                            displayProduit.afficherMenuPrincipal();
                            afficherMenu();
                        break;
                        default:
                        displayProduit.afficherOptionInconnue();
                    }

            break;


            default:
                displayProduit.afficherOptionInconnue();
        }

    }

    @Override
    public void afficherMenu() throws Exception {
        int choix = lireChoix();

        afficherMenu(choix );

    }
}
