package uniandes.dpoo.hamburguesas.mundo;

import java.util.ArrayList;
import java.util.List;

/**
 * Un producto ajustado es un producto para el cual el cliente solicitó alguna modificación.
 */
public class ProductoAjustado implements Producto
{
    /**
     * El producto base que el cliente sobre el cual el cliente quiere hacer ajustes
     */
    private ProductoMenu productoBase;

    /**
     * La lista de ingrediente que el usuario quiere agregar. El mismo ingrediente puede aparecer varias veces.
     */
    private ArrayList<Ingrediente> agregados;

    /**
     * La lista de ingrediente que el usuario quiere eliminar.
     */
    private ArrayList<Ingrediente> eliminados;

    /**
     * Construye un nuevo producto ajustado a partir del producto base y sin modificaciones
     * @param productoBase El producto base que se va a ajustar
     */
    public ProductoAjustado( ProductoMenu productoBase )
    {
        this.productoBase = productoBase;
        agregados = new ArrayList<Ingrediente>( );
        eliminados = new ArrayList<Ingrediente>( );
    }

    @Override
    public String getNombre( )
    {
        return productoBase.getNombre( );
    }

    /**
     * Retorna el precio del producto ajustado, que debe ser igual al del producto base, sumándole el precio de los ingredientes adicionales.
     */
    @Override
    public int getPrecio( )
    {

    	/*
    	 * ERROR: se estaba retornando 0 por default y no se le estaba sumando el precio de los ingredientes adicionales
    	 * CORRECCI�N: se llama getPrecio para el precio base y despues se le suma el valor de los productos adicionales
    	 */
    	int precioTotal = productoBase.getPrecio();
    	for(Ingrediente ingrediente :agregados) {
    		precioTotal += ingrediente.getCostoAdicional(); 
    	}
    	
        return precioTotal;
    }
    
    public void addIngredienteAgregado(Ingrediente ingrediente) {
    	agregados.add(ingrediente);
    }

    public void addIngredienteEliminado(Ingrediente ingrediente) {
    	eliminados.add(ingrediente);
    }
    
    public List<Ingrediente> getIngredientesAgregados() {
    	return agregados;
    }
    
    public List<Ingrediente> getIngredientesEliminados(){
    	return eliminados;
    }
    
    
    /**
     * Genera el texto que debe aparecer en la factura.
     * 
     * El texto incluye el producto base, los ingredientes adicionales con su costo, los ingredientes eliminados, y el precio total
     */
    @Override
    public String generarTextoFactura( )
    {
        StringBuffer sb = new StringBuffer( );
        

    	/*
    	 * ERROR: Se estaba a�adiendo el objeto del producto pero no su nombre. Entonces la factura generada mostraba el ( uniandes.dpo..@###).
    	 * CORRECCI�N: Se saca el nombre del producto con el .getNombre()
    	 */
        sb.append( productoBase.getNombre() );
        for( Ingrediente ing : agregados )
        {
            sb.append( "    +" + ing.getNombre( ) );
            sb.append( "                " + ing.getCostoAdicional( ) );
        }
        for( Ingrediente ing : eliminados )
        {
            sb.append( "    -" + ing.getNombre( ) );
        }

        sb.append( "            " + getPrecio( ) + "\n" );

        return sb.toString( );
    }

}
