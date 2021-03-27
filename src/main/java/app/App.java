package app;


import app.controller.ProduitController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        ProduitController produitController = new ProduitController();
        produitController.process();

    }
}
