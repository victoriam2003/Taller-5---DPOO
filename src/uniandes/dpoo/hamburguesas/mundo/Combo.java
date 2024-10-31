package uniandes.dpoo.hamburguesas.mundo;

import java.util.ArrayList;

/**
 * La clase utilizada para organizar la información de un combo
 */
public class Combo implements Producto
{
    /**
     * Los productos que hacen parte del combo
     */
    private ArrayList<ProductoMenu> itemsCombo;

    /**
     * El descuento que incluye el combo. Es un número entre 0 y 1, donde 0 corresponde a que no hay descuento y 1 corresponde al 100% de descuento
     */
    private double descuento;

    /**
     * El nombre del combo
     */
    private String nombreCombo;

    /**
     * Construye un nuevo combo
     * @param nombre El nombre del combo
     * @param descuento El descuento sobre el valor normal de los productos en el combo
     * @param items Los productos que hacen parte del combo
     */
    public Combo( String nombre, double descuento, ArrayList<ProductoMenu> items )
    {
        this.itemsCombo = new ArrayList<>( items );
        this.nombreCombo = nombre;
        this.descuento = descuento;
    }

    @Override
    public String getNombre( )
    {
        return this.nombreCombo;
    }

    /**
     * Retorna el precio del combo.
     * 
     * El precio está basado en aplicarle el descuento del combo al valor de cada uno de los productos.
     */
    @Override
    public int getPrecio( )
    {
    	/*
    	 * ERROR: No se le estaba aplicando el descuento al valor de cada uno de los productos
    	 * CORRECCI�N: se llama getPrecio y se le aplica directamente el descuento para restarselo al valor original
    	 */
        int precio = 0;
        for( Producto i : itemsCombo )
        {
        	double precioOriginal = i.getPrecio( );
        	double descuentoProducto = precioOriginal * descuento;
        	double precioFinal = precioOriginal - descuentoProducto;
        	precio += precioFinal;
        }

        return precio;
    }

    /**
     * Genera el texto que debe aparecer en la factura.
     * 
     * El texto incluye el nombre del combo, su costo y el valor del descuento
     */
    @Override
    public String generarTextoFactura( )
    {
        StringBuffer sb = new StringBuffer( );
        sb.append( "Combo " + nombreCombo + "\n" );
        sb.append( " Descuento: " + descuento + "\n" );
        sb.append( "            " + getPrecio( ) + "\n" );

        return sb.toString( );
    }

}
