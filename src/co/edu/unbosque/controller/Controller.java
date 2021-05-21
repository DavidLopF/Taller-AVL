package co.edu.unbosque.controller;

import co.edu.unbosque.model.ArbolAVL;
import co.edu.unbosque.view.View;

import java.util.ArrayList;

public class Controller {

    private ArbolAVL arbolAVL;
    private View view;

    public Controller() {
        arbolAVL = new ArbolAVL();
        view = new View();
        view.mostrarDato("..:: INICIO DE PROGRAMA ::..");
        view.mostrarDato("...:: BIENVENIDO SEÑOR USUARIO :....");

        funcionar();
    }

    private void funcionar() {
        ArrayList list = new ArrayList();
        view.mostrarDato("elementos en el arbol : " + arbolAVL.recorrerInOrder(arbolAVL.getRaiz(), list));
        String menu =
                "\n1. INSERTAR." +
                        "\n2. ELIMINAR." +
                        "\n3. BUSCAR ELEMENTO." +
                        "\n4. RECORRIDO IN ORDER." +
                        "\n5. RECORRIDO PRE ORDER" +
                        "\n6. RECORRIDO POST ORDER";
        view.mostrarDato(menu);
        int opcion = view.capturarInt("\nESCRIBA ACCION A REALIZAR: ");

        if (opcion <= 6) {
            switch (opcion) {
                case 1:
                    int dato = view.capturarInt("INGRESE DATO A AGREGRAR AL ARBOL: ");
                    arbolAVL.insertar(dato);
                    view.mostrarDato("\nNODO INGRESADO CON EXTIO...");
                    funcionar();
                    break;

                case 2:

                    funcionar();
                    break;

                case 3:


                    funcionar();
                    break;

                case 4:
                    view.mostrarDato("\n");
                    ArrayList listIN = new ArrayList();
                    view.mostrarDato("RESULTAOD DE RECORRIDO IN ORDER:\n" + arbolAVL.recorrerInOrder(arbolAVL.getRaiz(), listIN));
                    funcionar();
                    break;

                case 5:
                    view.mostrarDato("\n");
                    ArrayList listPre = new ArrayList();
                    view.mostrarDato("RESULTAOD DE RECORRIDO PRE ORDER:\n" + arbolAVL.recorerPreOrden(arbolAVL.getRaiz(), listPre));
                    funcionar();
                    break;

                case 6:
                    view.mostrarDato("\n");
                    ArrayList listPost = new ArrayList();
                    view.mostrarDato("RESULTAOD DE RECORRIDO POST ORDER:\n" + arbolAVL.recorridoPostOrden(arbolAVL.getRaiz(), listPost));
                    funcionar();
                    break;

            }
        } else {
            view.mostrarDato("Opcion invalida :(");
            funcionar();
        }
    }
}
