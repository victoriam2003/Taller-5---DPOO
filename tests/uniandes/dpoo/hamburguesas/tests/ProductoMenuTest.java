package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	
	private ProductoMenu productoMenuPrueba;
	
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		productoMenuPrueba = new ProductoMenu( "Hamburguesa", 15 );
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    //Tests de getNombre

	@Test
	@DisplayName("Nombre de Producto Menu Creado Correctamente")
	void testGetNombreProductoMenuCreado() {
		assertEquals("Hamburguesa",productoMenuPrueba.getNombre(),"El nombre del producto no es el esperado");
	}
	
	//Tests de getPrecio 
	
	@Test
	@DisplayName("Precio de Producto Menu Creado Correctamente")
	void testGetPrecioProductoMenuCreado() {
		assertEquals(15,productoMenuPrueba.getPrecio(),"El precio del producto no es el esperado");
	}
	
	//Tests de generar texto de factura
	
	@Test
	@DisplayName("Generar Texto de Factura Producto Menu Correctamente")
	void testGenerarTextoFactura() {
		StringBuffer sb = new StringBuffer( );
        sb.append( "Hamburguesa\n" );
        sb.append( "            15\n" );
        String facturaHamburguesa = sb.toString();
        
        assertEquals(facturaHamburguesa, productoMenuPrueba.generarTextoFactura(),"La factura generada para el producto no es la esperada");
	}	
	
	
	//TODO: Hacer un test para revisar que los precios de los productos agregados sea mayor a cero podria ser un test adicional a crear

}
