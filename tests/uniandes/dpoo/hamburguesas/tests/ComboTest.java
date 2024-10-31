package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	private Combo ComboPrueba;
	private ArrayList<ProductoMenu> items;
	private ProductoMenu hamburguesa;
	private ProductoMenu papas;
	private ProductoMenu gaseosa;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		hamburguesa = new ProductoMenu( "Hamburguesa", 15 );
		papas = new ProductoMenu( "Papas", 5 );
		gaseosa = new ProductoMenu( "Gaseosa", 3 );
		items = new ArrayList<ProductoMenu>();
		items.add(hamburguesa);
		items.add(papas);
		items.add(gaseosa);
		ComboPrueba = new Combo( "Combo especial", 0.07, items );

    
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    //Tests de getNombre
    @Test
	void testGetNombreComboCreado() {
		assertEquals("Combo especial", ComboPrueba.getNombre(), "El nombre del combo no es el esperado");
	}
    
    //Tests de getPrecio
    @Test
        void testGetPrecioComboCreado() {
    	 assertEquals(19, ComboPrueba.getPrecio(), "El precio del combo no es el esperado");
    }
    
    //Test de Generar texto factura
    @Test
	void testGenerarTextoFactura() {
    	StringBuffer sb = new StringBuffer( );
        sb.append( "Combo " + "Combo especial" + "\n" );
        sb.append( " Descuento: " + 0.07 + "\n" );
        sb.append( "            " + 19 + "\n" );

        String FacturaPrueba = sb.toString( );

		assertEquals(FacturaPrueba, ComboPrueba.generarTextoFactura(),
				"La factura generada para el combo no es la esperada");
	}
}
