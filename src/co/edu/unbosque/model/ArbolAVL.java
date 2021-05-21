package co.edu.unbosque.model;

import java.util.ArrayList;

public class ArbolAVL {

    private Nodo raiz;

    public ArbolAVL(Nodo raiz) {
        this.raiz = null;
    }

    public Boolean arbolVacio() {
        return raiz == null;
    }

    public Nodo insertarBalanceado(Nodo nuevo, Nodo subArbol) {
        Nodo nuevoPadre = subArbol;

        if (nuevo.getDato() < subArbol.getDato()) {
            if (subArbol.getHijoIzquierdo() == null) {
                subArbol.setHijoIzquierdo(nuevo);
            } else {
                subArbol.setHijoIzquierdo(insertarBalanceado(nuevo, subArbol.getHijoIzquierdo()));

                if ((obtenerFE(subArbol.getHijoIzquierdo()) - obtenerFE(subArbol.getHijoDerecho())) == 2) {

                    if (nuevo.getDato() < subArbol.getHijoIzquierdo().getDato()) {
                        nuevoPadre = rotacionIzquierda(subArbol);
                    } else {
                        nuevoPadre = rotacionDobleIzquierda(subArbol);
                    }
                }
            }
        } else if (nuevo.getDato() > subArbol.getDato()) {
            if (subArbol.getHijoDerecho() == null) {
                subArbol.setHijoDerecho(nuevo);
            } else {
                subArbol.setHijoDerecho(insertarBalanceado(nuevo, subArbol.getHijoDerecho()));
                if ((obtenerFE(subArbol.getHijoDerecho()) - obtenerFE(subArbol.getHijoIzquierdo()) == 2)) {
                    if (nuevo.getDato() > subArbol.getHijoDerecho().getDato()) {
                        nuevoPadre = rotacionDerecha(subArbol);
                    } else {
                        nuevoPadre = rotacionDobleDerecha(subArbol);
                    }
                }
            }
        } else {
            System.out.println("Nodo duplicado");
        }
        if (subArbol.getHijoIzquierdo() == null
                && subArbol.getHijoDerecho() != null) { //actualizar fe

            subArbol.setFe(subArbol.getHijoDerecho().getFe() + 1);

        } else if (subArbol.getHijoDerecho() == null
                && subArbol.getHijoIzquierdo() != null) {
            subArbol.setFe(subArbol.getHijoIzquierdo().getFe() + 1);
        } else {
            subArbol.setFe(Math.max(obtenerFE(subArbol.getHijoIzquierdo()), obtenerFE(subArbol.getHijoDerecho())) + 1);
        }
        return nuevoPadre;
    }

    public void insertar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertarBalanceado(nuevo, raiz);
        }

    }

    public ArrayList recorridoPostOrden(Nodo nodo, ArrayList a) {
        if (nodo != null) {
            recorridoPostOrden(nodo.getHijoIzquierdo(), a);
            recorridoPostOrden(nodo.getHijoDerecho(), a);
            a.add(nodo.getDato());

        }
        return a;
    }

    public ArrayList recorrerInOrder(Nodo nodo, ArrayList a) {
        if (nodo != null) {
            recorrerInOrder(nodo.getHijoIzquierdo(), a);
            a.add(nodo.getDato());
            recorrerInOrder(nodo.getHijoDerecho(), a);
        }
        return a;
    }

    public ArrayList recorerPreOrden(Nodo nodo, ArrayList a) {
        if (nodo != null) {
            a.add(nodo.getDato());
            recorerPreOrden(nodo.getHijoIzquierdo(), a);
            recorerPreOrden(nodo.getHijoDerecho(), a);

        }
        return a;
    }



    public Nodo buscar(int a, Nodo raiz) {
        if (arbolVacio()) {
            return null;
        } else if (raiz.getDato() == a) { // si lo encuentra lo retorna
            return raiz;
        } else if (raiz.getDato() < a) { // se buscar recursivamente por la derecha
            return buscar(a, raiz.getHijoDerecho());
        } else {
            return buscar(a, raiz.getHijoIzquierdo());
        }
    }

    public int obtenerFE(Nodo aux) { //obtener el factor de equiibrio
        if (aux == null) {
            return -1;
        } else {
            return aux.getFe();
        }
    }

    public Nodo rotacionIzquierda(Nodo c) {
        Nodo aux = c.getHijoIzquierdo();
        c.setHijoIzquierdo(aux.getHijoDerecho());
        aux.setHijoDerecho(c);
        c.setFe(Math.max(obtenerFE(c.getHijoIzquierdo()), obtenerFE(c.getHijoDerecho())) + 1);
        aux.setFe(Math.max(obtenerFE(aux.getHijoIzquierdo()), obtenerFE(aux.getHijoDerecho())) + 1);
        return aux;
    }

    public Nodo rotacionDerecha(Nodo c) {
        Nodo aux = c.getHijoDerecho();
        c.setHijoDerecho(aux.getHijoIzquierdo());
        aux.setHijoIzquierdo(c);
        c.setFe(Math.max(obtenerFE(c.getHijoIzquierdo()), obtenerFE(c.getHijoDerecho())) + 1);
        aux.setFe(Math.max(obtenerFE(aux.getHijoIzquierdo()), obtenerFE(aux.getHijoDerecho())) + 1);
        return aux;
    }

    public Nodo rotacionDobleIzquierda(Nodo c) {
        Nodo temp;
        c.setHijoIzquierdo(rotacionDerecha(c.getHijoIzquierdo()));
        temp = rotacionIzquierda(c);
        return temp;
    }

    public Nodo rotacionDobleDerecha(Nodo c) {
        Nodo temp;
        c.setHijoDerecho(rotacionIzquierda(c.getHijoDerecho()));
        temp = rotacionDerecha(c);
        return temp;

    }
}
