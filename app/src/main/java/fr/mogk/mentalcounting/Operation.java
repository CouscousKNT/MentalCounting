package fr.mogk.mentalcounting;

import java.util.Random;

public class Operation {
    private double partieGauche;
    private char operation;
    private double partieDroite;
    private double reponse;

    public Operation(Rank rank){
        int typeOperation=0;
        int maxAddition=0;
        int minAddition=0;
        int maxMultiplication=0;
        int minMultiplication=0;
        if (rank == Rank.BRONZE) {
            typeOperation= (int) (Math.random() * ( 2 - 0 ));
            maxAddition = 20;
            minAddition = 1;

        }
        if (rank == Rank.SILVER) {
            typeOperation= (int) (Math.random() * ( 4 - 0 ));
            maxAddition = 50;
            minAddition = 1;
            maxMultiplication=10;
            minMultiplication=1;
        }
        if (rank == Rank.GOLD) {
            typeOperation= (int) (Math.random() * ( 4 - 0 ));
            maxAddition = 100;
            minAddition = -100;
            maxMultiplication=20;
            minMultiplication=1;
            //calcul complexe
        }
        switch (typeOperation){
            case (0):
                operation='+';
                partieGauche=minAddition+ (Math.random() * ( maxAddition - minAddition ));
                partieDroite=minAddition+ (Math.random() * ( maxAddition - minAddition ));
                break;
            case (1):
                operation='-';
                partieGauche=minAddition+ (Math.random() * ( maxAddition - minAddition ));
                partieDroite=minAddition+ (Math.random() * ( maxAddition - minAddition ));
                break;
            case (2):
                operation='*';
                partieGauche=minMultiplication+ (Math.random() * ( maxMultiplication - minMultiplication ));
                partieDroite=minMultiplication+ (Math.random() * ( maxMultiplication - minMultiplication ));
                break;
            case (3):
                operation='/';
                partieGauche=minMultiplication+ (Math.random() * ( maxMultiplication - minMultiplication ));
                partieDroite=minMultiplication+ (Math.random() * ( maxMultiplication - minMultiplication ));
                if(partieDroite>partieGauche){
                    double tmp = partieDroite;
                    partieDroite=partieGauche;
                    partieGauche=tmp;
                }
                break;
            default:
                operation='?';
        }

        if(rank==Rank.BRONZE||rank==Rank.SILVER||rank==Rank.GOLD){
            partieDroite=(int) partieDroite;
            partieGauche=(int) partieGauche;
        }

    }

    @Override
    public String toString() {
        String retour="";
        if (partieGauche - Math.floor(partieGauche) > 0 && partieDroite - Math.floor(partieDroite) > 0) {
            retour = partieGauche + " " + operation + " " + partieDroite + " = ?";
        } else {
            if (partieDroite - Math.floor(partieDroite) > 0) {
                retour = (int) partieGauche + " " + operation + " " + partieDroite + " = ?";
            } else {
                if (partieGauche - Math.floor(partieGauche) > 0) {
                    retour = (int) partieGauche + " " + operation + " " + partieDroite + " = ?";
                } else {
                    retour = (int) partieGauche + " " + operation + " " + (int) partieDroite + " = ?";
                }
            }

        }

        return retour;
    }

    public double getReponse() {
        return reponse;
    }
}
