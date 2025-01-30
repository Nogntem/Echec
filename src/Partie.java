import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
    /*
    Une partie Joueur contre Joueur
     */
    public static void partie(String couleur1, String couleur2, String[][] plateau, int nbrIA) {
        Plateau.initialisation(plateau);

        String[][] plateauAvantChangement = Plateau.plateau();
        Scanner sc = new Scanner(System.in);
        boolean noirOuBlanc, fin = false, abandon = false, echec, robot, draw = false; // true = blanc, false = noir
        String[] pseudos = new String[2];
        String pseudo = "", choix;
        int nbrTours = 0, coordX, coordY, nbrCoups = 1, nbrSansPriseOuAutre = 1;
        char couleur;
        int emplacementPseudoIA = Joueur.ChoixPseudo(pseudos, nbrIA); //crée les pseudos et renvoie l'emplacement de l'IA si il y a IA vs Joueur
        ArrayList<String> coupsListe = new ArrayList<>();
        boolean[] onPasBouger = {true, true, true, true, true, true};// [TB GAUCHE][RB][TB DROITE][TN GAUCHE][RN][TN DROITE]
        String piece;

        System.out.print("            ╔════════════════════════════════╗\n");

        System.out.print("            "+pseudos[0] + " est les blancs.        \n            " + pseudos[1] + " est les noirs.        \n");
        System.out.print("            ╚════════════════════════════════╝\n");
        Plateau.afficherPlateau(plateau, couleur1, couleur2);

        while (!abandon && !fin && !draw) { //Boucle Principale
            if (nbrTours % 2 == 0) {

                pseudo = pseudos[0];
                noirOuBlanc = true;
                if (nbrIA != 0 && emplacementPseudoIA == 0) {
                    robot = true;
                } else {
                    robot = false;
                }
            } else {
                nbrCoups++;
                noirOuBlanc = false;
                pseudo = pseudos[1];
                if (nbrIA != 0 && emplacementPseudoIA == 1) {
                    robot = true;
                } else {
                    robot = false;
                }
            }
            if (nbrIA == 2) {
                robot = true;
            }
            System.out.println("♟ Coup(s) n: " + nbrCoups + " au tour de " + pseudo);
            do {
                do {
                    do {
                        do {
                            coordX = 0;
                            coordY = 7;
                            if (nbrIA == 0 || nbrIA == 1 && !robot) {
                                System.out.println("♟ Choisir votre pion");
                                choix = sc.nextLine().toLowerCase();
                            } else {
                                choix = IA.selectionPiece();

                            }
                        } while (choix.length() < 2);
                        if (choix.equals("abandon")) {
                            abandon = true;
                        } else {
                            coordX = (Character.getNumericValue(choix.charAt(0)) - 10);
                            coordY = 8 - (Character.getNumericValue(choix.charAt(1)));
                        }
                    } while ((((coordX > 8 || coordX < 0) || (coordY > 8 || coordY < 0)) && !abandon));
                    couleur = plateau[coordY][coordX].charAt(plateau[coordY][coordX].length() - 1); //vérifie la couleur
                } while (!abandon && (plateau[coordY][coordX].equals("N/D") || noirOuBlanc && couleur == 'N' || !noirOuBlanc && couleur == 'B'));


                /*
                SIMULATION DES COUPS, et renvoie dans une liste les coups possibles
                 */
                ArrayList<String> coupSimu = new ArrayList<>();
                if (!abandon) {
                    coupsListe.removeAll(coupsListe);
                    String pion = "N/D";
                    int coordXSimu, coordYSimu;
                    Pion.coupsPossible(plateau, coupSimu, coordX, coordY, onPasBouger);
                    if (!coupSimu.isEmpty()) {
                        for (int i = 0; i < coupSimu.size(); i++) {
                            coordXSimu = (Character.getNumericValue(coupSimu.get(i).charAt(0)) - 10);
                            coordYSimu = 8 - (Character.getNumericValue(coupSimu.get(i).charAt(1)));
                            if (!plateau[coordYSimu][coordXSimu].equals("N/D")) {
                                pion = plateau[coordYSimu][coordXSimu];
                            } else {
                                pion = "N/D";
                            }
                            Pion.deplacePion(plateau, coordX, coordY, coordXSimu, coordYSimu, plateau[coordY][coordX]);
                            boolean couleurPion;
                            couleurPion = !noirOuBlanc;
                            if (!Pion.echec(plateau, couleurPion, onPasBouger)) {
                                coupsListe.add(coupSimu.get(i));
                            }

                            Pion.deplacePion(plateau, coordXSimu, coordYSimu, coordX, coordY, plateau[coordYSimu][coordXSimu]);
                            Pion.ajoutPion(plateau, coordXSimu, coordYSimu, pion);

                        }
                    }
                }
            } while (coupsListe.isEmpty() && !abandon);
            if (!abandon) {


                //PlateauAvantChangement
                //Le plateau avant le deplacement de la piece
                for(int ligne = 0; ligne < plateau.length;ligne++){
                    for(int colonne = 0; colonne < plateau[ligne].length;colonne++){
                        plateauAvantChangement[ligne][colonne] = plateau[ligne][colonne];
                    }
                }



                Pion.affichePlateauCoups(coupsListe, plateau);
                Plateau.afficherPlateau(plateau, couleur1, couleur2); //Affiche le plateau
                System.out.println("♟ Les coups possibles :" + coupsListe); //Affiche les coups possibles
                piece = plateau[coordY][coordX];
                String choixDeplacement = Pion.choixPionEtDeplacement(plateau, coordX, coordY, choix, coupsListe, robot); //Deplace le pion
                Pion.rock(plateau, coordX, coordY, onPasBouger, piece);
                Pion.pieceBouge(onPasBouger, piece, coordX); // boolean true  ->  false si Tour ou Roi bouge
                Plateau.resetCoups(plateau); //Retire les coups possibles
                Plateau.afficherPlateau(plateau, couleur1, couleur2); //Affiche le plateau

                int coordYNeg = coordY - 1;
                int coordYPos = coordY + 1;

                if (piece.equals("PN") && coordYPos == 7) {
                    System.out.println("noir");
                    Pion.promotion(plateau, (Character.getNumericValue(choixDeplacement.charAt(0)) - 10), 8 - (Character.getNumericValue(choixDeplacement.charAt(1))), piece, robot);
                    Plateau.afficherPlateau(plateau, couleur1, couleur2); //Affiche le plateau
                }
                if (piece.equals("PB") && coordYNeg == 0) {
                    System.out.println("blanc");
                    Pion.promotion(plateau, (Character.getNumericValue(choixDeplacement.charAt(0)) - 10), 8 - (Character.getNumericValue(choixDeplacement.charAt(1))), piece, robot);
                    Plateau.afficherPlateau(plateau, couleur1, couleur2); //Affiche le plateau
                }


                if (Pion.echec(plateau, noirOuBlanc, onPasBouger)) {
                    echec = true;
                    System.out.println("Attention vous êtes en echec");
                } else {
                    echec = false;
                }
                if (Pion.echecEtMat(plateau, noirOuBlanc, onPasBouger) && echec) {
                    fin = true;
                }

                if(Pion.draw(plateau,noirOuBlanc,onPasBouger) && !echec){
                   draw = true;
                }
                /*
                Compte le nombre de coup sans prise de piece ou deplacement de pion
                 */
                if (!plateauAvantChangement[(8 - (Character.getNumericValue(choixDeplacement.charAt(1))))][(Character.getNumericValue(choixDeplacement.charAt(0)) - 10)].equals("PN") && !plateauAvantChangement[(8 - (Character.getNumericValue(choixDeplacement.charAt(1))))][(Character.getNumericValue(choixDeplacement.charAt(0)) - 10)].equals("PB")) {
                    if (plateauAvantChangement[(8 - (Character.getNumericValue(choixDeplacement.charAt(1))))][(Character.getNumericValue(choixDeplacement.charAt(0)) - 10)].equals("N/D")) {
                        nbrSansPriseOuAutre++;
                    } else {
                        nbrSansPriseOuAutre = 0;
                    }
                } else {
                    nbrSansPriseOuAutre = 0;
                }

                /*
                Verifie si y a que 2 pièces donc draw
                 */
                int nbrPiece = 0;
                for(int ligne = 0; ligne < plateau.length;ligne++){
                    for(int colonne = 0; colonne < plateau[ligne].length;colonne++){
                        if(!plateau[ligne][colonne].equals("N/D")){
                            nbrPiece++;
                        }
                    }
                }
                if (nbrSansPriseOuAutre == 100 || nbrPiece == 2) {
                    draw = true;
                }


                nbrTours++;

            }
        }
        if (abandon) {
            System.out.println(pseudo + " a abandonné.LE NULLOS");

        }
        if (fin) {
            System.out.println(pseudo + " a gagner (ECHEC ET MAT)");
        }
        if (draw) {
            System.out.print(  "           ╔════════════════════════════════╗\n" +
                               "           ║               Draw             ║\n" +
                               "           ╚════════════════════════════════╝\n");
        }
        Plateau.resetPlateau(plateau);
    }
}
