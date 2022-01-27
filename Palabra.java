package gami5;

public class Palabra { 

    private char[] letras;
    private int longitud;
    private static final int MAX = 24; // la palabra más larga del diccionario
    // es "electroencefalografista", que tiene 23 letras

    public Palabra() {
        letras = new char[MAX];
        longitud = 0;
    }

    public Palabra(String frase) { // constructor
        letras = frase.toCharArray();
        longitud = letras.length;
    }

    @Override
    public String toString() {
        String s = "";
        for (int idx = 0; idx < longitud; idx++) {
            s = s + letras[idx];
        }
        return s;
    }

    public void AñadirCarácter(char c) {
        letras[longitud] = c;
        longitud++;
    }

    private static int SaltarBlancos(char[] oracion, int idx) {
        while (oracion[idx] == ' ') {
            idx++; // salta blancos
        }
        return idx;
    }

    public boolean PalabraVacía() {
        return (longitud == 0);
    }
    
    public int GetLongitud(){
        return longitud;
    }
    
    public static Palabra[] organizarEnPalabras(char[] oracion) {
        Palabra conjuntoPal[] = new Palabra[oracion.length / 2 + 10];
        // como máximo el conjunto de palabras tendrá en cuanto a palabras 
        // la mitad de letras más 10 palabras para oraciones pequeñas o imprevistos
        int pos = 0; // creo el índice del conjunto de palabras (pos) refiriéndose
        // a la posición de la palabra en el conjunto y es declarado fuera para
        // luego eliminar las palabras vacías
        for (int idx = 0; idx < oracion.length; pos++) { // creo el índice del array oración (idx)
            idx = SaltarBlancos(oracion, idx); // salta blancos de la oración

            conjuntoPal[pos] = new Palabra(); // creo una palabra del conjunto
            while (oracion[idx] != ' ' && oracion[idx] != ',' && oracion[idx] != '.') {
                // mientras no acabe la palabra
                conjuntoPal[pos].AñadirCarácter(oracion[idx++]);
                // añade la siguiente letra
                if (idx >= oracion.length) {
                    // y si ha llegado al final de la oración
                    oracion[--idx] = '.';
                    // mengua el índice para que no compare algo con nada el while
                    // y hace que valga una condición de finalización del recorrido
                }
            }
            idx++; // pasa a la siguiente letra que según el código de arriba
            // será un espacio, una coma o un punto, por lo que se salta
            // es esencial porque si es el final de la palabra y se ha restado 1
            // se sume de nuevo para que la condición de finalización del for lo pare
        }
        Palabra conjuntoPalFinal[] = new Palabra[pos];
        // creo un conjunto al que le quitaré las palabras vacías
        // pos es la cantidad de palabras no vacías que tiene conjuntoPal
        for (int idx = 0; idx < pos; idx++) { // mientras queden palabras en el conjunto
            conjuntoPalFinal[idx] = conjuntoPal[idx]; // copia las palabras
        }
        return conjuntoPalFinal;
    }
    
    public static boolean sonIguales(Palabra primera, Palabra segunda) {
        // método que devuelve true si dos palabras son exactamente iguales
        if (primera.longitud != segunda.longitud) {
            return false;
        } // si no son del mismo tamaño no son iguales
        for (int idx = 0; idx < primera.longitud; idx++) {
            if (primera.letras[idx] != segunda.letras[idx]) {
                return false; // si una letra no es igual, no son iguales
            }
        }
        return true;
    }
    
    public static void Similares(Palabra[] conjuntoPal, int numPal) {
    // programa que busca palabras similares a la dada (numPal), siendo numPal el
    // número de la palabra intuitivamente, por lo tanto si es la primera palabra numPal = 1
        numPal--; // como en los arrays la primera palabra es la 0, le resto 1
        for (int idx = 0; idx < conjuntoPal.length; idx++) { // recorro el conjunto
            if (Palabra.similarPorDerecha(conjuntoPal[numPal], conjuntoPal[idx])) {
                // si son iguales por la derecha imprimo
                System.out.println(conjuntoPal[idx]);
                System.out.println("");
            } else if (Palabra.similarPorIzquierda(conjuntoPal[numPal], conjuntoPal[idx])) {
                // si son iguales por la izquierda imprimo
                System.out.println(conjuntoPal[idx]);
                System.out.println("");
            }
        }
    }

    public static boolean similarPorDerecha(Palabra original, Palabra similar) {
        // método que devuelve true si dos palabras son similares por un carácter
        // por la derecha, solo si la original es menor en cuanto a longitud
        if (original.longitud + 1 == similar.longitud) {
            // si son similares la original tendrá una letra más
            Palabra aux = new Palabra();// creo una palabra auxiliar
            for (int idx = 0; idx < similar.longitud; idx++) {
                aux.AñadirCarácter(similar.letras[idx]); // le copio el contenido
            }
            aux.longitud--; // le resto 1 a la longitud
            return Palabra.sonIguales(original, aux); // devuelve si son iguales 
            // después de haberle quitado la letra que les hacía similares
        }
        return false;
    }

    public static boolean similarPorIzquierda(Palabra original, Palabra similar) {
        // método que devuelve true si dos palabras son similares por un carácter
        // por la izquierda, solo si la original es menor en cuanto a longitud
        if (original.longitud + 1 == similar.longitud) {
            // si son similares la original tendrá una letra más
            Palabra aux = new Palabra(); // creo una palabra auxiliar
            for (int idx = 0; idx < similar.longitud; idx++) {
                aux.AñadirCarácter(similar.letras[idx+1]); // le copio el
                // contenido una posición más adelante para saltar la primera letra
            }
            aux.longitud--; // le resto 1 a la longitud
            return Palabra.sonIguales(original, aux); // devuelve si son iguales 
            // después de haberle quitado la letra que les hacía similares
        }
        return false;
    }
    
    /* CÓDIGO ANTIGUO Y PEOR
    public void SimilaresANTIGUO(Palabra Oración) { // este método busca palabras de Oración parecidas a la instancia por la que pasa
        Palabra Similar = new Palabra(); // creación de la palabra similar
        for (int posOración = 0; posOración < Oración.longitud; posOración++) { // recorro la oración en busca de la similar
            boolean SimilarEncontrada = false;
            for (int posPalabra = 0;
                    !SimilarEncontrada && (posOración <= Oración.longitud - longitud) && (letras[posPalabra] == Oración.letras[posOración + posPalabra]);
                    posPalabra++) {  // mientras no se haya encontrado, pueda caber palabra similar en la oración y las letras sean iguales avanza posición
                Similar.letras[posPalabra] = Oración.letras[posOración + posPalabra]; // asigno la letra a similar
                SimilarEncontrada = (Palabra.this.SimilarDer(Similar, Oración, posOración, posPalabra)
                        || Palabra.this.SimilarIzq(Similar, Oración, posOración, posPalabra)); // si se ha encontrado por algún lado el booleano es true
                // separo estos 2 métodos del método principal para que sea más legible
                if (SimilarEncontrada) {
                    posPalabra = 0; // si se encuentra similar mengua la posición de la palabra para que no dé error si compara nada con una letra
                }
            }
        }
    }

    private boolean SimilarIzqANTIGUO(Palabra Similar, Palabra Oración, int posOración, int posPalabra) { // le doy atributos en vez de hacerlos globales porque son muy concretos
        if (longitud - 1 == posPalabra && posOración != 0) { // compruebo que sea el final de la palabra y que no es el principio de la oración
            if (Oración.letras[posOración - 1] != ' ') { // si hay una letra antes de la palabra 
                for (int mov = longitud; mov > 0;) {
                    Similar.letras[mov] = Similar.letras[--mov]; // muevo la similar una posición a la derecha para dejar la posición 0 libre
                }
                Similar.letras[0] = Oración.letras[posOración - 1];
                Similar.longitud = longitud + 1;
                System.out.println(Similar);
                System.out.println("");
                return true;
            } else {
                return false; // no ha sido encontrada
            }
        } else {
            return false;
        }
    }

    private boolean SimilarDerANTIGUO(Palabra Similar, Palabra Oración, int posOración, int posPalabra) {
        if (longitud - 1 == posPalabra && Oración.longitud - 1 != posOración + posPalabra) { // compruebo que sea el final de la palabra y que no es el final de la oración
            if (Oración.letras[posOración + posPalabra + 1] != ' ' && Oración.letras[posOración + posPalabra + 1] != '.' && Oración.letras[posOración + posPalabra + 1] != ',') {
                // se ha visto si hay una letra después de la palabra (sin tener en cuenta los dos puntos y demás signos de puntuación)
                Similar.letras[posPalabra + 1] = Oración.letras[posOración + posPalabra + 1];
                Similar.longitud = longitud + 1;
                System.out.println(Similar);
                System.out.println("");
                return true;
            } else {
                return false; // no ha sido encontrada
            }
        } else {
            return false;
        }
    }
    */
}
