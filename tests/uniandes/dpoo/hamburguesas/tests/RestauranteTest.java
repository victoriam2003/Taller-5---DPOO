package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

class RestauranteTest {
	
	private Restaurante restaurantePrueba;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<ProductoMenu> menuBase;
    private ArrayList<Combo> menuCombos;
    
	@BeforeEach
    void setUp( ) throws Exception
    {
		restaurantePrueba = new Restaurante();
		ingredientes = new ArrayList<Ingrediente>();
		menuCombos = new ArrayList<Combo>();
		menuBase = new ArrayList<ProductoMenu>();
		ingredientes.add(new Ingrediente("lechuga",1000));
		ingredientes.add(new Ingrediente("tomate",1000));
		ingredientes.add(new Ingrediente("cebolla",1000));
		ingredientes.add(new Ingrediente("queso mozzarella",2500));
		ingredientes.add(new Ingrediente("huevo",2500));
		ingredientes.add(new Ingrediente("queso americano",2500));
		ingredientes.add(new Ingrediente("tocineta express",2500));
		ingredientes.add(new Ingrediente("papa callejera",2000));
		ingredientes.add(new Ingrediente("pepinillos", 2500));
		ingredientes.add(new Ingrediente("cebolla grille",2500));
		ingredientes.add(new Ingrediente("suero costeño",3000));
		ingredientes.add(new Ingrediente("frijol refrito",4500));
		ingredientes.add(new Ingrediente("queso fundido",4500));
		ingredientes.add(new Ingrediente("tocineta picada",6000));
		ingredientes.add(new Ingrediente("piña",2500));
		
		menuBase.add(new ProductoMenu("corral", 14000));
		menuBase.add(new ProductoMenu("corral queso", 16000));
		menuBase.add(new ProductoMenu("corral pollo", 15000));
		menuBase.add(new ProductoMenu("corralita", 13000));
		menuBase.add(new ProductoMenu("todoterreno", 25000));
		menuBase.add(new ProductoMenu("1/2 libra", 25000));
		menuBase.add(new ProductoMenu("especial", 24000));
		menuBase.add(new ProductoMenu("casera", 23000));
		menuBase.add(new ProductoMenu("mexicana", 22000));
		menuBase.add(new ProductoMenu("criolla", 22000));
		menuBase.add(new ProductoMenu("costeña", 20000));
		menuBase.add(new ProductoMenu("hawaiana", 20000));
		menuBase.add(new ProductoMenu("wrap de pollo", 15000));
		menuBase.add(new ProductoMenu("wrap de lomo", 22000));
		menuBase.add(new ProductoMenu("ensalada mexicana", 20900));
		menuBase.add(new ProductoMenu("papas medianas", 5500));
		menuBase.add(new ProductoMenu("papas grandes", 6900));
		menuBase.add(new ProductoMenu("papas en casco medianas", 5500));
		menuBase.add(new ProductoMenu("papas en casco grandes", 6900));
		menuBase.add(new ProductoMenu("agua cristal sin gas", 5000));
		menuBase.add(new ProductoMenu("agua cristal con gas", 5000));
		menuBase.add(new ProductoMenu("gaseosa", 5000));
		
		ArrayList<ProductoMenu> combo1 = new ArrayList<ProductoMenu>();
		ArrayList<ProductoMenu> combo2 = new ArrayList<ProductoMenu>();
		ArrayList<ProductoMenu> combo3 = new ArrayList<ProductoMenu>();
		ArrayList<ProductoMenu> combo4 = new ArrayList<ProductoMenu>();
		
		combo1.add(menuBase.get(0));
		combo1.add(menuBase.get(15));
		combo1.add(menuBase.get(21));
		
		combo2.add(menuBase.get(1));
		combo2.add(menuBase.get(15));
		combo2.add(menuBase.get(21));
		
		combo3.add(menuBase.get(4));
		combo3.add(menuBase.get(16));
		combo3.add(menuBase.get(21));
		
		combo4.add(menuBase.get(6));
		combo4.add(menuBase.get(15));
		combo4.add(menuBase.get(21));
		
		menuCombos.add(new Combo("combo corral", 0.1, combo1));
		menuCombos.add(new Combo("combo corral queso", 0.1, combo2));
		menuCombos.add(new Combo("combo todoterreno", 0.07, combo3));
		menuCombos.add(new Combo("combo especial", 0.095, combo4));
		
	}
	
    //Tests de iniciarPedido cuando no hay un  en curso
	@Test
	void testIniciarPedido() throws YaHayUnPedidoEnCursoException {

		restaurantePrueba.iniciarPedido("Juan", "Calle 123 #3-70");
		assertNotNull(restaurantePrueba.getPedidoEnCurso(), "El pedido no se creo correctamente");
	}
	
    //Tests de iniciarPedido cuando hay un pedido en curso
	@Test
	void testIniciarPedidoConPedidoEnCurso()throws YaHayUnPedidoEnCursoException {
		restaurantePrueba.iniciarPedido("Juan", "Calle 123 #3-70");
		
		boolean thrown = false;

		try {
			restaurantePrueba.iniciarPedido("Valeria", "Calle 109 #3-70");
		} catch (YaHayUnPedidoEnCursoException e) {
			thrown = true;
		}
		assertTrue(thrown);
}

	
// Tests de cerrarYGuardarPedido cuando hay un pedido en curso
@Test
void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
	restaurantePrueba.iniciarPedido("Juan", "Calle 123 #3-70");
	restaurantePrueba.cerrarYGuardarPedido();
	assertNull(restaurantePrueba.getPedidoEnCurso(), "El pedido no se cerro correctamente");
}

// Tests de cerrarYGuardarPedido cuando no hay un pedido en curso
@Test
void testCerrarYGuardarPedidoSinPedidoEnCurso() throws NoHayPedidoEnCursoException, IOException{
	boolean thrown = false;

	try {
		restaurantePrueba.cerrarYGuardarPedido();
	} catch (NoHayPedidoEnCursoException e) {
		thrown = true;
	}

	assertTrue(thrown);
}


//Tests de cargarInformacionRestaurante sin excepciones 

@Test
void testCargarInformacionRestaurante() throws HamburguesaException, NumberFormatException, IOException {

    File archivoCombos = new File("./data/combos.txt");
    File archivoIngredientes = new File("./data/ingredientes.txt");
    File archivoMenu = new File("./data/menu.txt");
    Restaurante restaurante = new Restaurante();
    restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);

    List<Ingrediente> actualIngredientes = restaurante.getIngredientes();
    List<ProductoMenu> actualMenu = restaurante.getMenuBase();
    List<Combo> actualCombos = restaurante.getMenuCombos();

    // Debug statements
    System.out.println("Expected Ingredientes: " + ingredientes);
    System.out.println("Actual Ingredientes: " + actualIngredientes);
    System.out.println("Expected Menu: " + menuBase);
    System.out.println("Actual Menu: " + actualMenu);
    System.out.println("Expected Combos: " + menuCombos);
    System.out.println("Actual Combos: " + actualCombos);

    // Assertions
    assertAll("Cargar Informacion Restaurante",
        () -> assertSame(ingredientes, actualIngredientes, "Los ingredientes no se cargaron correctamente"),
        () -> assertSame(menuBase, actualMenu, "El menu base no se cargo correctamente"),
        () -> assertSame(menuCombos, actualCombos, "El menu de combos no se cargo correctamente")
    );
}



//Tests de cargarInformacionRestaurante con archivo de ingredientes no existente
@Test
void testCargarInformacionRestauranhteArchivoIngredientesNoExiste() throws HamburguesaException, NumberFormatException, IOException {
    File archivoCombos = new File("./data/combos.txt");
    File archivoIngredientes = new File("./data/ingredientes2.txt");
    File archivoMenu = new File("./data/menu.txt");
    boolean thrown = false;

    try {
        restaurantePrueba.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
    } catch (IOException e) {
        thrown = true;
    }
    assertTrue(thrown);
	
}

//Tests de cargarInformacionRestaurante con archivo de menu no existente
@Test
void testCargarInformacionRestauranhteArchivoMenuNoExiste()
		throws HamburguesaException, NumberFormatException, IOException {
	File archivoCombos = new File("./data/combos.txt");
	File archivoIngredientes = new File("./data/ingredientes.txt");
	File archivoMenu = new File("./data/menu2.txt");
	boolean thrown = false;

	try {
		restaurantePrueba.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	} catch (IOException e) {
		thrown = true;
	}
	assertTrue(thrown);

}

//Tests de cargarInformacionRestaurante con archivo de combos no existente
@Test
void testCargarInformacionRestauranhteArchivoCombosNoExiste()
		throws HamburguesaException, NumberFormatException, IOException {
	File archivoCombos = new File("./data/combos2.txt");
	File archivoIngredientes = new File("./data/ingredientes.txt");
	File archivoMenu = new File("./data/menu.txt");
	boolean thrown = false;

	try {
		restaurantePrueba.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	} catch (IOException e) {
		thrown = true;
	}
	assertTrue(thrown);
}

//Tests de cargarInformacionRestaurante con archivo de ingredientes repetidos
@Test
void testCargarInformacionRestauranhteIngredientesRepetidos()
		throws HamburguesaException, NumberFormatException, IOException {
	File archivoCombos = new File("./data/combos.txt");
	File archivoIngredientes = new File("./data/ingredientesRepetidos.txt");
	File archivoMenu = new File("./data/menu.txt");
	boolean thrown = false;

	try {
		restaurantePrueba.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	} catch (HamburguesaException e) {
		thrown = true;
	}
	assertTrue(thrown); 
}

//Tests de cargarInformacionRestaurante con archivo de productoMenu repetidos
@Test
void testCargarInformacionRestauranhteProductoMenuRepetidos()
		throws HamburguesaException, NumberFormatException, IOException {
	File archivoCombos = new File("./data/combos.txt");
	File archivoIngredientes = new File("./data/ingredientes.txt");
	File archivoMenu = new File("./data/menuRepetidos.txt");
	boolean thrown = false;

	try {
		restaurantePrueba.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	} catch (HamburguesaException e) {
		thrown = true;
	}
	assertTrue(thrown);
}

//Tests de cargarInformacionRestaurante con archivo de combos repetidos
@Test
void testCargarInformacionRestauranteCombosRepetidos()
        throws HamburguesaException, NumberFormatException, IOException {
    File archivoCombos = new File("./data/combosRepetidos.txt");
    File archivoIngredientes = new File("./data/ingredientes.txt");
    File archivoMenu = new File("./data/menu.txt");
    boolean thrown = false;

    try {
        restaurantePrueba.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
    } catch (HamburguesaException e) {
        thrown = true;
    }
    assertTrue(thrown);
}

//Tests de cargarInformacionRestaurante con archivo de combos con productos que no estan en el menu
@Test
void testCargarInformacionRestauranhteCombosConProductosNoEnMenu()
		throws HamburguesaException, NumberFormatException, IOException {
	File archivoCombos = new File("./data/combosNoExiste.txt");
	File archivoIngredientes = new File("./data/ingredientes.txt");
	File archivoMenu = new File("./data/menu.txt");
	boolean thrown = false;

	try {
		restaurantePrueba.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	} catch (ProductoFaltanteException e) {
		thrown = true;
	}
	assertTrue(thrown);
}
 
}
