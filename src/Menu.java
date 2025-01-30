import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void menuPrincipal() {
        int choix, choixPartie, choixCouleur;
        boolean couleurIA;
        String[][] plateau = Plateau.plateau();
        String couleur1 = "Vert", couleur2 = "Violet";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("           ╔════════════════════════════════╗\n" +
                    " _  _  _   ║ ♟        Jeu d'Échecs       ♟ ║     ^^__     \n" +
                    "| || || |  ╠════════════════════════════════╣    /  - \\_   \n" +
                    "|_______|  ║                                ║  <|    __<            \n" +
                    "\\__ ___ /  ║  1.Personnaliser son échiquier ║  <|    \\   \n" +
                    " |___|_|   ║     2.Afficher l'échiquier     ║  <|     \\  \n" +
                    " |_|___|   ║     3.Joueur contre Joueur     ║  <|______\\\n" +
                    " |___|_|   ║         4.Menu Test            ║   _|____|_ \n" +
                    "(_______)  ║        5.Partie avec IA        ║  (________) \n" +
                    "/_______\\  ║          6.Quitter             ║  /________\\\n" +
                    "           ╚════════════════════════════════╝ ");
            choix = sc.nextInt();
            sc.nextLine();
            Plateau.resetPlateau(plateau);
            switch (choix) {
                case 1:
                    do {
                        System.out.println("Couleur par défaut : Vert et Violet.\nVoici les couleurs disponibles : Rouge, Vert, Jaune, Bleu, Violet, Cyan.\nChoix de la première couleure (écrire la couleure):");
                        couleur1 = sc.nextLine();
                    } while (!couleur1.equals("Rouge") && !couleur1.equals("Vert") && !couleur1.equals("Jaune") && !couleur1.equals("Bleu") && !couleur1.equals("Violet") && !couleur1.equals("Cyan"));
                    do {
                        System.out.println("Choix de la deuxième couleure (écrire la couleure, elle doit être différente de la première):");
                        couleur2 = sc.nextLine();
                    } while (couleur2.equals(couleur1) || !couleur2.equals("Rouge") && !couleur2.equals("Vert") && !couleur2.equals("Jaune") && !couleur2.equals("Bleu") && !couleur2.equals("Violet") && !couleur2.equals("Cyan"));
                    break;
                case 2:
                    Plateau.afficherPlateau(plateau, couleur1, couleur2);
                    break;
                case 3:
                    Partie.partie(couleur1, couleur2, plateau, 0);
                    break;
                case 4:
                    menuTest(couleur1, couleur2, plateau);
                    break;
                case 5:
                    do {
                        System.out.print("    ;      ╔════════════════════════════════╗    ;    \n" +
                                         "   [\"]     ║  1 : Partie Joueur contre IA   ║   [\"] \n" +
                                         "  /[_]\\    ║     2 : Partie IA vs IA        ║  /[_]\\  \n" +
                                         "   ] [     ╚════════════════════════════════╝   ] [   \n");
                        choixPartie = sc.nextInt();
                    } while (choixPartie != 1 && choixPartie != 2);
                    switch (choixPartie) {
                        case 1:
                            Partie.partie(couleur1, couleur2, plateau, 1);
                            break;
                        case 2:
                            Partie.partie(couleur1, couleur2, plateau, 2);
                            break;
                    }
                    break;
            }
        } while (choix != 6);
    }

    /*
    Menu test
     */
    public static void menuTest(String couleur1, String couleur2, String[][] plateau) {
        Scanner sc = new Scanner(System.in);
        int coordX = 0, coordY = 0;
        String position, pion;
        int choix = 0;
        boolean reset = false;
        ArrayList<String> coupsListe = new ArrayList<>();
        ArrayList<String> coupSimu = new ArrayList<>();
        boolean[] onPasBouger = {true, true, true, true, true, true};// [TB GAUCHE][RB][TB DROITE][TN GAUCHE][RN][TN DROITE]

        do {
            Plateau.resetPlateau(plateau);
            do {
                System.out.println("Choisir votre pion (tels que : RoiB, RoiN, TN, TB, FN, FB, PN, PB, CB, CN, ReineB, ReineN)");
                pion = sc.nextLine();
            } while (pion.isEmpty() || (!pion.equals("RoiB") && !pion.equals("RoiN") && !pion.equals("TN") && !pion.equals("TB") && !pion.equals("FN") && !pion.equals("FB") && !pion.equals("PB") && !pion.equals("PN") && !pion.equals("CB") && !pion.equals("CN") && !pion.equals("ReineB") && !pion.equals("ReineN")));
            Plateau.afficherPlateau(plateau, couleur1, couleur2);
            do {
                System.out.println("Ou voulez-vous le positionner ?");
                position = sc.nextLine().toLowerCase();
                coordX = (Character.getNumericValue(position.charAt(0)) - 10);
                coordY = 8 - (Character.getNumericValue(position.charAt(1)));
            } while (Pion.bonCoord(coordX, coordY, position));
            Pion.ajoutPion(plateau, coordX, coordY, pion);
            Plateau.afficherPlateau(plateau, couleur1, couleur2);
            do {
                System.out.println("Choix possible :\n1.Verifier les coups possibles\n2.Reset le plateau\n3.Ajouter un autre pion\n4.Bouger un pion\n5.Quitter");
                choix = sc.nextInt();
                sc.nextLine();
                switch (choix) {
                    case 1: //Afficher les coups possibles
                        do {
                            System.out.println("Quel pion ?");
                            position = sc.nextLine().toLowerCase();
                            coordX = (Character.getNumericValue(position.charAt(0)) - 10);
                            coordY = 8 - (Character.getNumericValue(position.charAt(1)));
                        } while (Pion.bonCoord(coordX, coordY, position));
                        coupsListe.removeAll(coupsListe);
                        pion = "N/D";
                        Pion.coupsPossible(plateau, coupSimu, coordX, coordY, onPasBouger);
                        int coordXSimu, coordYSimu;
                        //Simulation
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
                                couleurPion = false;
                                if (!Pion.echec(plateau, couleurPion, onPasBouger)) {
                                    coupsListe.add(coupSimu.get(i));
                                }

                                Pion.deplacePion(plateau, coordXSimu, coordYSimu, coordX, coordY, plateau[coordYSimu][coordXSimu]);
                                Pion.ajoutPion(plateau, coordXSimu, coordYSimu, pion);

                            }
                        }
                        Pion.affichePlateauCoups(coupsListe, plateau);
                        Plateau.afficherPlateau(plateau, couleur1, couleur2);
                        System.out.println("Les coups possibles :" + coupsListe);
                        Plateau.resetCoups(plateau);
                        break;
                    case 2: //Reset le plateau
                        reset = true;
                        break;
                    case 3: //Placer un pion
                        Plateau.resetCoups(plateau);
                        do {
                            System.out.println("Choisir votre pion (tels que : RoiB, RoiN, TN, TB, FN, FB, PN, PB, CB, CN, ReineB, ReineN)");
                            pion = sc.nextLine();
                        } while (pion.isEmpty() || (!pion.equals("RoiB") && !pion.equals("RoiN") && !pion.equals("TN") && !pion.equals("TB") && !pion.equals("FN") && !pion.equals("FB") && !pion.equals("PB") && !pion.equals("PN") && !pion.equals("CB") && !pion.equals("CN") && !pion.equals("ReineB") && !pion.equals("ReineN")));
                        do {
                            System.out.println("Ou voulez-vous le positionner ?");
                            position = sc.nextLine().toLowerCase();
                            coordX = (Character.getNumericValue(position.charAt(0)) - 10);
                            coordY = 8 - (Character.getNumericValue(position.charAt(1)));
                        } while (Pion.bonCoord(coordX, coordY, position));
                        Pion.ajoutPion(plateau, coordX, coordY, pion);
                        Plateau.afficherPlateau(plateau, couleur1, couleur2);
                        break;
                    case 4: //Deplacer un pion
                        ArrayList<String> coupsEchec = new ArrayList<>();
                        do {
                            do {
                                System.out.println("Quel pion ?");
                                position = sc.nextLine().toLowerCase();
                                coordX = (Character.getNumericValue(position.charAt(0)) - 10);
                                coordY = 8 - (Character.getNumericValue(position.charAt(1)));
                            } while (Pion.bonCoord(coordX, coordY, position) && plateau[coordY][coordX].equals("N/D"));
                            //Simulation de coup
                            coupsListe.removeAll(coupsListe);
                            pion = "N/D";
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
                                    couleurPion = false;
                                    if (!Pion.echec(plateau, couleurPion, onPasBouger)) {
                                        coupsListe.add(coupSimu.get(i));
                                    }

                                    Pion.deplacePion(plateau, coordXSimu, coordYSimu, coordX, coordY, plateau[coordYSimu][coordXSimu]);
                                    Pion.ajoutPion(plateau, coordXSimu, coordYSimu, pion);

                                }
                            }
                        } while (coupsListe.isEmpty());

                        Pion.affichePlateauCoups(coupsListe, plateau);
                        Plateau.afficherPlateau(plateau, couleur1, couleur2); //Affiche le plateau

                        System.out.println("Les coups possibles :" + coupsListe); //Affiche les coups possibles

                        Pion.choixPionEtDeplacement(plateau, coordX, coordY, position, coupsListe,false); //Deplace le pion
                        Plateau.resetCoups(plateau); //Retire les coups possibles

                        //if ((plateau[coordY+1][coordX].equals("PN") || plateau[coordY-1][coordX].equals("PB")) && (coordY+1 == 7 || coordY-1 == 0)) {
                        //    Pion.promotion(plateau, coordX, coordY);
                        //}

                        Plateau.afficherPlateau(plateau, couleur1, couleur2); //Affiche le plateau

                        System.out.println(Pion.echec(plateau, false, onPasBouger)); //Vrai ou faux echec
                        if(Pion.echec(plateau, false, onPasBouger) && Pion.echecEtMat(plateau, false, onPasBouger)){
                            System.out.println("Echec et Mat");
                        }
                        if(Pion.draw(plateau,false,onPasBouger) && !Pion.echec(plateau, false, onPasBouger)){
                            System.out.println("Draw");
                        }
                        Plateau.afficherPlateau(plateau, couleur1, couleur2); //Affiche le plateau


                        break;
                }
            } while (choix != 5 && !reset);
        } while (choix != 5);
    }
}
