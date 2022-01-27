package gami5;

public class Gami5 {

    public static void main(String[] args) {
        Gami5 g = new Gami5();
        g.Inicio();
    }
    LT lector = new LT();

    public void Inicio() {
        System.out.println("Escribe una oración y se te mostrarán las palabras parecidas a la primera con un carácter de diferencia como máximo");
        System.out.println("");
        System.out.print("Texto introducido por teclado: ");

        Palabra [] conjuntoPal = Palabra.organizarEnPalabras(lector.leerLinea().toCharArray());
        
        System.out.println("");
        System.out.println("Palabras mostradas:");
        System.out.println("");
        
        Palabra.Similares(conjuntoPal, 1);
    }
}
