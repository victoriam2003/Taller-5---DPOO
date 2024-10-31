package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	
	private ProductoAjustado productoAjustadoPrueba;
	private Ingrediente ingredienteQuesoPrueba;
	private Ingrediente ingredienteCebollaPrueba ;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		ProductoMenu productoMenuPrueba = new ProductoMenu( "Hamburguesa", 15 );
		productoAjustadoPrueba = new ProductoAjustado(productoMenuPrueba);
		ingredienteQuesoPrueba = new Ingrediente("Queso",2);
  		ingredienteCebollaPrueba = new Ingrediente("Cebolla",1);
  		
		
		
  		productoAjustadoPrueba.addIngredienteAgregado(ingredienteQuesoPrueba);
  		productoAjustadoPrueba.addIngredienteEliminado(ingredienteCebollaPrueba);
    }
 
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    //Tests de getNombre

  	@Test
  	@DisplayName("Nombre de Producto Ajustado Creado Correctamente")
  	void testGetNombreProductoAjustadoCreado() {
  		assertEquals("Hamburguesa",productoAjustadoPrueba.getNombre(),"El nombre del producto no es el esperado");
  	}
  	
  	//Tests de getPrecio
  	
  	@Test
  	@DisplayName("Precio de Producto Ajustado Creado Correctamente")
  	void testGetPrecioProductoAjustadoCreado() {
  		assertEquals(17,productoAjustadoPrueba.getPrecio(),"El precio del producto no es el esperado");
  	}
  	
  	//Test de Ingredientes de Producto Ajustado
  	
  	@Test
  	@DisplayName("Agregar un solo ingrediente a la lista de ingredientes agregados de un Producto Ajustado Correctamente")
  	void testAgregarUnSoloIngredienteAgregados() {
  		
  		assertEquals(1,productoAjustadoPrueba.getIngredientesAgregados().size(),"El numero de ingredientes agregados no es el esperado");
  		assertSame(ingredienteQuesoPrueba, productoAjustadoPrueba.getIngredientesAgregados().get(0), "El ingrediente agregado no es el esperado");
  	}
  	
  	@Test
  	@DisplayName("Agregar un solo ingrediente a la lista de ingredientes eliminados de un Producto Ajustado Correctamente")
  	void testAgregarUnSoloIngredienteEliminados() {
  		
  		assertEquals(1,productoAjustadoPrueba.getIngredientesEliminados().size(),"El numero de ingredientes eliminados no es el esperado");
  		assertSame(ingredienteCebollaPrueba, productoAjustadoPrueba.getIngredientesEliminados().get(0), "El ingrediente eliminado no es el esperado");
  	}
  	
  	

  	@Test
  	@DisplayName("Agregar un ingrediente duplicado a la lista de ingredientes agregados de un Producto Ajustado Correctamente")
  	void testAggregarIngredienteDuplicadoAgregados() {

  		productoAjustadoPrueba.addIngredienteAgregado(ingredienteQuesoPrueba);
  		
  		assertEquals(2,productoAjustadoPrueba.getIngredientesAgregados().size(),"El numero de ingredientes agregados no es el esperado");
  		assertSame(ingredienteQuesoPrueba, productoAjustadoPrueba.getIngredientesAgregados().get(0), "El ingrediente agregado no es el esperado");
  		assertSame(ingredienteQuesoPrueba, productoAjustadoPrueba.getIngredientesAgregados().get(1), "El ingrediente agregado no es el esperado");
  	}
  	
  	@Test
  	@DisplayName("Agregar dos ingredientes diferentes a la lista de ingredientes agregados de un Producto Ajustado Correctamente")
  	void testAggregarIngredientesDiferentesAgregados() {

  		Ingrediente ingredienteTocinetaPrueba = new Ingrediente("Tocineta", 3);
  		productoAjustadoPrueba.addIngredienteAgregado(ingredienteTocinetaPrueba);
  		
  		assertEquals(2,productoAjustadoPrueba.getIngredientesAgregados().size(),"El numero de ingredientes agregados no es el esperado");
  		assertSame(ingredienteQuesoPrueba, productoAjustadoPrueba.getIngredientesAgregados().get(0), "El ingrediente agregado no es el esperado");
  		assertSame(ingredienteTocinetaPrueba, productoAjustadoPrueba.getIngredientesAgregados().get(1), "El ingrediente agregado no es el esperado");
  	}
  	
  	@Test
  	@DisplayName("Agregar dos ingredientes diferentes a la lista de ingredientes eliminados de un Producto Ajustado Correctamente")
  	void testAggregarIngredientesDiferentesEliminados() {

  		Ingrediente ingredienteTomatePrueba = new Ingrediente("Tomate", 1);
  		productoAjustadoPrueba.addIngredienteEliminado(ingredienteTomatePrueba);
  		
  		assertEquals(2,productoAjustadoPrueba.getIngredientesEliminados().size(),"El numero de ingredientes agregados no es el esperado");
  		assertSame(ingredienteCebollaPrueba, productoAjustadoPrueba.getIngredientesEliminados().get(0), "El ingrediente agregado no es el esperado");
  		assertSame(ingredienteTomatePrueba, productoAjustadoPrueba.getIngredientesEliminados().get(1), "El ingrediente agregado no es el esperado");
  	}
  	
  	
  	//Tests de generar texto de factura
	
  	@Test
  	@DisplayName("Generar Texto de Factura Producto Ajustado Correctamente")
  	void testGenerarTextoFactura() {
  		StringBuffer sb = new StringBuffer( );
        sb.append( productoAjustadoPrueba.getNombre() );
        for( Ingrediente ing : productoAjustadoPrueba.getIngredientesAgregados() )
        {
            sb.append( "    +" + ing.getNombre( ) );
            sb.append( "                " + ing.getCostoAdicional( ) );
        }
        for( Ingrediente ing : productoAjustadoPrueba.getIngredientesEliminados() )
        {
            sb.append( "    -" + ing.getNombre( ) );
        }

        sb.append( "            " + productoAjustadoPrueba.getPrecio() + "\n" );

        String facturaEsperada = sb.toString( );
        
        assertEquals(facturaEsperada,productoAjustadoPrueba.generarTextoFactura());
  	}

}
