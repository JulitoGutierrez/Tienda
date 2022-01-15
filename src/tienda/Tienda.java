/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import MenuTienda.TiendaMenu;



public class Tienda {

    public static void main(String[] args) {
//        //Scanner sc = new Scanner(System.in).useDelimiter("\n");
//        FabricanteServicio f = new FabricanteServicio();
//        Integer id = 10;
//        String nombre = "julio";
//        ProductoServicio p = new ProductoServicio();
//
//        try {
//
//            p.listaProdPorNombre();
//            System.out.println("-----------------------");
//            p.listaProdPorNombrePrecio();
//            System.out.println("-----------------------");
//            f.nuevoFabric(id, nombre);
//            System.out.println("-----------------------");
//            f.mostrarListaFabricante();
//        } catch (Exception e) {
//            System.out.println("ERROR:" + e.getMessage());
//        }
      TiendaMenu menu = new TiendaMenu();

        menu.menuPrincipal();
                
                
    }

}
