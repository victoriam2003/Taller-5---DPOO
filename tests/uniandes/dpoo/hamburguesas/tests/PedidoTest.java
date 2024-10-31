package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	
	private Pedido pedidoPrueba;
	private ProductoMenu hamburguesa;
	private ProductoMenu papas;
	private ProductoMenu gaseosa;
	private ProductoAjustado hamburguesaPersonalizada;
	private Combo combo;
	private Ingrediente cebolla;
	private Ingrediente queso;
 
	@BeforeEach
    void setUp( ) throws Exception
    {
		pedidoPrueba = new Pedido("Juan", "Calle 123 #3-70");
		hamburguesa = new ProductoMenu( "Hamburguesa", 15000 );
		papas = new ProductoMenu( "Papas", 5000 );
		gaseosa = new ProductoMenu("Gaseosa", 3000);
		
    	hamburguesaPersonalizada = new ProductoAjustado( hamburguesa );
    	cebolla = new Ingrediente("Cebolla",1000);
    	queso = new Ingrediente("Queso",2000);
    	hamburguesaPersonalizada.addIngredienteEliminado( cebolla);
    	hamburguesaPersonalizada.addIngredienteAgregado( queso);
    	

		ArrayList<ProductoMenu> items = new ArrayList<ProductoMenu>();
		items.add(hamburguesa);
		items.add(papas);
		items.add(gaseosa);
		combo = new Combo("Combo especial", 0.07, items);
	} 
	
    

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    //Tests de getIdPedido
    @Test
        void testGetIdPedidoPedidoCreado() {
    	assertEquals(10, pedidoPrueba.getIdPedido(), "El id del pedido no es el esperado");
    }
    
    //Tests de getNombreCliente
    @Test
        void testGetNombreClientePedidoCreado() {
    	assertEquals("Juan", pedidoPrueba.getNombreCliente(), "El nombre del pedido no es el esperado");
    }
    
    //Tests de agregarProducto con productosMenu
    @Test
        void testAgregarProductoPedidoCreado() {
		pedidoPrueba.agregarProducto(hamburguesa);
		pedidoPrueba.agregarProducto(papas);
		assertEquals(2, pedidoPrueba.getProductos().size(), "El producto no fue agregado correctamente");
	}
    
    //Tests de agregarProducto con productos ajustados
    @Test
        void testAgregarProductoAjustadoPedidoCreado() {
		pedidoPrueba.agregarProducto(hamburguesaPersonalizada);
    	assertEquals(1, pedidoPrueba.getProductos().size(), "El producto no fue agregado correctamente");
    }
    	
  //Tests de agregarProducto con combos
	@Test
	void testAgregarComboPedidoCreado() {
		pedidoPrueba.agregarProducto(combo);
		assertEquals(1, pedidoPrueba.getProductos().size(), "El producto no fue agregado correctamente");
	}

	// Tests de getPrecioTotalPedido con productosMenu
	@Test
        void testGetPrecioTotalPedidoProductoMenuPedidoCreado() {
    	pedidoPrueba.agregarProducto(hamburguesa);
    	pedidoPrueba.agregarProducto(papas);
    	assertEquals(23800, pedidoPrueba.getPrecioTotalPedido(), "El precio total del pedido no es el esperado");
	}
	
	// Tests de getPrecioTotalPedido con productos ajustados
	@Test
	void testGetPrecioTotalPedidoProductoAjustadoPedidoCreado() {
		pedidoPrueba.agregarProducto(hamburguesaPersonalizada);
		assertEquals(20230, pedidoPrueba.getPrecioTotalPedido(), "El precio total del pedido no es el esperado");
	}
	
	// Tests de getPrecioTotalPedido con combos
	@Test
	void testGetPrecioTotalPedidoComboPedidoCreado() {
		pedidoPrueba.agregarProducto(combo);
		assertEquals(25454, pedidoPrueba.getPrecioTotalPedido(), "El precio total del pedido no es el esperado");
	}
	
	// Tests de getPrecioTotalPedido con combos y producto Menu
	@Test
	void testGetPrecioTotalPedidoComboProductoMenuPedidoCreado() {
		ProductoMenu helado = new ProductoMenu("Helado", 8000);
		pedidoPrueba.agregarProducto(combo);
		pedidoPrueba.agregarProducto(helado);
		assertEquals(34974, pedidoPrueba.getPrecioTotalPedido(), "El precio total del pedido no es el esperado");
	}
	
	// Tests de getPrecioTotalPedido con combos y producto ajustado
	@Test
	void testGetPrecioTotalPedidoComboProductoAjustadoPedidoCreado() {
		pedidoPrueba.agregarProducto(combo);
		pedidoPrueba.agregarProducto(hamburguesaPersonalizada);
		assertEquals(45684, pedidoPrueba.getPrecioTotalPedido(), "El precio total del pedido no es el esperado");
	}
	
	// Tests de getPrecioTotalPedido con combos, producto menu y producto ajustado
	@Test
	void testGetPrecioTotalPedidoComboProductoMenuProductoAjustadoPedidoCreado() {
		ProductoMenu helado = new ProductoMenu("Helado", 8000);
		pedidoPrueba.agregarProducto(combo);
		pedidoPrueba.agregarProducto(helado);
		pedidoPrueba.agregarProducto(hamburguesaPersonalizada);
		assertEquals(55204, pedidoPrueba.getPrecioTotalPedido(), "El precio total del pedido no es el esperado");
	}
	
	
	// Tests de generarTextoFactura
	@Test
	void testGenerarTextoFacturaPedidoCreado() {
		ProductoMenu helado = new ProductoMenu("Helado", 8000);
		pedidoPrueba.agregarProducto(combo);
		pedidoPrueba.agregarProducto(helado);
		pedidoPrueba.agregarProducto(hamburguesaPersonalizada);
		
        StringBuffer sb = new StringBuffer( );

        sb.append( "Cliente: " + "Juan" + "\n" );
        sb.append( "Dirección: " + "Calle 123 #3-70" + "\n" );
        sb.append( "----------------\n" );

        for( Producto item : pedidoPrueba.getProductos( ))
        {
            sb.append( item.generarTextoFactura( ) );
        }

        sb.append( "----------------\n" );
        sb.append( "Precio Neto:  " + 46390 + "\n" );
        sb.append( "IVA:          " + 8814 + "\n" );
        sb.append( "Precio Total: " + 55204 + "\n" );
        
        String facturaEsperada = sb.toString( );
        assertEquals(facturaEsperada, pedidoPrueba.generarTextoFactura());
	}

	// Tests de guardarfactura cuando el archivo existe
	@Test
	void testGuardarFacturaPedidoCreado() throws FileNotFoundException {
		ProductoMenu helado = new ProductoMenu("Helado", 8000);
		pedidoPrueba.agregarProducto(combo);
		pedidoPrueba.agregarProducto(helado);
		pedidoPrueba.agregarProducto(hamburguesaPersonalizada);
		File archivo = new File("./data/factura.txt");
		pedidoPrueba.guardarFactura(archivo);
		FileReader leer  = new FileReader(archivo);
	}
	
	// Tests de guardarfactura cuando el archivo no existe
	@Test
	void testGuardarFacturaPedidoCreadoArchivoNoExiste() throws FileNotFoundException {
		ProductoMenu helado = new ProductoMenu("Helado", 8);
		pedidoPrueba.agregarProducto(combo);
		pedidoPrueba.agregarProducto(helado);
		pedidoPrueba.agregarProducto(hamburguesaPersonalizada);
		File archivo = new File("./factura2.txt/");
		
		boolean thrown = false;
		try {
			pedidoPrueba.guardarFactura(archivo);
		} catch (FileNotFoundException e) {
			thrown=true;
		}
		assertTrue(thrown);
	}
	
	

}