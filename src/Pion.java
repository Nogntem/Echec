import java.util.ArrayList;
import java.util.Scanner;

public class Pion {
    /*
    Ajout un pion dans le tableau, prend le plateau, coordXY et le pion
     */
    public static void ajoutPion(String[][] plateau, int coordX, int coordY, String pion) {
        plateau[coordY][coordX] = pion;
    }

    /*
    Supprime un pion du tableau, prend le plateau, coordXY
     */
    public static void supprPion(String[][] plateau, int coordX, int coordY) {
        plateau[coordY][coordX] = "N/D";
    }

    /*
    Combinaison de Suppr et ajout
     */
    public static void deplacePion(String[][] plateau, int ancienCoordX, int ancienCoordY, int coordX, int coordY, String pion) {
        supprPion(plateau, ancienCoordX, ancienCoordY);
        ajoutPion(plateau, coordX, coordY, pion);
    }

    /* ╰(*°▽°*)╯
    Prend une liste, et rempli avec les coups possibles avec un premier filtre : Verifie si une piece ennemie ou allie est sur le chemin
     */
    public static void coupsPossible(String[][] plateau, ArrayList<String> coupsPossible, int coordXdeBase, int coordYdeBase, boolean[] onPasBouger) {
        ArrayList<String> coups = Pion.tousLesCoupsPossibles(plateau, coordXdeBase, coordYdeBase, onPasBouger);
        if (!coupsPossible.isEmpty()) {
            coupsPossible.clear();
        }
        boolean noirOuBlanc;
        if (plateau[coordYdeBase][coordXdeBase].charAt(plateau[coordYdeBase][coordXdeBase].length() - 1) == 'B') { //Verifie si c'est au tour des noirs ou blancs
            noirOuBlanc = true;
        } else {
            noirOuBlanc = false;
        }
        for (int i = 0; i < coups.size(); i++) { //Verifie les coups un par un
            int coordX = (Character.getNumericValue(coups.get(i).charAt(0)) - 10);
            int coordY = 8 - (Character.getNumericValue(coups.get(i).charAt(1)));
            boolean pion = false;
            int coordXCoups = coordX;
            int coordYCoups = coordY;
            if (!(plateau[coordYdeBase][coordXdeBase].equals("CB") || plateau[coordYdeBase][coordXdeBase].equals("CN"))) { //exclus les cavaliers
                while (coordXCoups != coordXdeBase || coordYCoups != coordYdeBase) {
                    if (coordXCoups > coordXdeBase) {
                        coordXCoups--;
                    } else if (coordXCoups < coordXdeBase) {
                        coordXCoups++;
                    }
                    if (coordYCoups > coordYdeBase) {
                        coordYCoups--;
                    } else if (coordYCoups < coordYdeBase) {
                        coordYCoups++;
                    }
                    if (!plateau[coordYCoups][coordXCoups].equals("N/D") && !((coordYCoups == coordYdeBase) && (coordXCoups == coordXdeBase))) {
                        pion = true;

                    }
                }
            }
            if (!pion) {
                if (plateau[coordY][coordX].equals("N/D")) {
                    coupsPossible.add(coups.get(i));
                } else {
                    if ((noirOuBlanc && plateau[coordY][coordX].charAt(plateau[coordY][coordX].length() - 1) == 'N') || (!noirOuBlanc && plateau[coordY][coordX].charAt(plateau[coordY][coordX].length() - 1) == 'B')) {
                        coupsPossible.add(coups.get(i));
                    }
                }

            }
        }
    }
    /*
    Change les pieces en pièces grises, permet d'afficher les coups possibles sur le damier
     */
    public static void affichePlateauCoups(ArrayList<String> coupsPossible, String[][] plateau) {
        for (int j = 0; j < coupsPossible.size(); j++) {
            int coordX = (Character.getNumericValue(coupsPossible.get(j).charAt(0)) - 10);
            int coordY = 8 - (Character.getNumericValue(coupsPossible.get(j).charAt(1)));
            switch (plateau[coordY][coordX]) {
                case "RoiB":
                    Pion.ajoutPion(plateau, coordX, coordY, "R?oiB");
                    break;
                case "ReineB":
                    Pion.ajoutPion(plateau, coordX, coordY, "R?eineB");
                    break;
                case "RoiN":
                    Pion.ajoutPion(plateau, coordX, coordY, "R?oiN");
                    break;
                case "ReineN":
                    Pion.ajoutPion(plateau, coordX, coordY, "R?eineN");
                    break;
                case "TB":
                    Pion.ajoutPion(plateau, coordX, coordY, "T?B");
                    break;
                case "TN":
                    Pion.ajoutPion(plateau, coordX, coordY, "T?N");
                    break;
                case "FN":
                    Pion.ajoutPion(plateau, coordX, coordY, "F?N");
                    break;
                case "FB":
                    Pion.ajoutPion(plateau, coordX, coordY, "F?B");
                    break;
                case "CN":
                    Pion.ajoutPion(plateau, coordX, coordY, "C?N");
                    break;
                case "CB":
                    Pion.ajoutPion(plateau, coordX, coordY, "C?B");
                    break;
                case "PN":
                    Pion.ajoutPion(plateau, coordX, coordY, "P?N");
                    break;
                case "PB":
                    Pion.ajoutPion(plateau, coordX, coordY, "P?B");
                    break;
                case "N/D":
                    Pion.ajoutPion(plateau, coordX, coordY, "??");
                    break;
            }
        }
    }

    /*
    Renvoie vrai ou faux si echec
     */
    public static boolean echec(String[][] plateau, boolean noirOuBlanc, boolean[] onPasBouger) {
        boolean echec = false;
        ArrayList<String> coups = new ArrayList<>();
        char lastChar;
        String coordRoi;
        lastChar = noirOuBlanc(noirOuBlanc);
        coordRoi = coordRoi(plateau, noirOuBlanc);
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne].charAt(plateau[ligne][colonne].length() - 1) == lastChar) {
                    coupsPossible(plateau, coups, colonne, ligne, onPasBouger);
                    if (coups.contains(coordRoi)) {
                        echec = true;
                    }
                }
            }
        }
        return echec;
    }
    /*
    Cherche les coordonnées du Roi et les renvoies dans un string du type : "a1", "h4"
     */
    public static String coordRoi(String[][] plateau, boolean noirOuBlanc) {
        int coordXRoi = 0, coordYRoi = 0;
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne].equals("RoiB") && !noirOuBlanc) {
                    coordYRoi = ligne;
                    coordXRoi = colonne;
                } else if (plateau[ligne][colonne].equals("RoiN") && noirOuBlanc) {
                    coordYRoi = ligne;
                    coordXRoi = colonne;
                }
            }
        }
        return (chiffreALettre(coordXRoi)) + Integer.toString(Math.abs(coordYRoi - 8));
    }
    /*
    envoie un char représentant si c'est au tour des Noirs ou Blancs
     */
    public static char noirOuBlanc(boolean noirOuBlanc) {
        char lastChar;
        if (noirOuBlanc) {//true = blanc, false = noir
            lastChar = 'B';
        } else {
            lastChar = 'N';
        }
        return lastChar;
    }
    /*
    Renvoie vrai ou faux si Echec et Mat
     */
    public static boolean echecEtMat(String[][] plateau, boolean noirOuBlanc, boolean[] onPasBouger) {
        boolean echetEtMat = true;
        ArrayList<String> coups = new ArrayList<>();
        char lastChar;
        if (noirOuBlanc) {//true = blanc, false = noir
            lastChar = 'N';
        } else {
            lastChar = 'B';
        }
        String pion = "N/D";

        //SIMULATION COUPS
        int coordX, coordY;
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (!plateau[ligne][colonne].equals("N/D") && plateau[ligne][colonne].charAt(plateau[ligne][colonne].length() - 1) == lastChar) {
                    coupsPossible(plateau, coups, colonne, ligne, onPasBouger);
                    if (!coups.isEmpty()) {
                        for (int i = 0; i < coups.size(); i++) {
                            coordX = (Character.getNumericValue(coups.get(i).charAt(0)) - 10);
                            coordY = 8 - (Character.getNumericValue(coups.get(i).charAt(1)));
                            if (!plateau[coordY][coordX].equals("N/D")) {
                                pion = plateau[coordY][coordX];
                            } else {
                                pion = "N/D";
                            }
                            deplacePion(plateau, colonne, ligne, coordX, coordY, plateau[ligne][colonne]);
                            if (!echec(plateau, noirOuBlanc, onPasBouger)) {
                                echetEtMat = false;

                            }


                            deplacePion(plateau, coordX, coordY, colonne, ligne, plateau[coordY][coordX]);
                            ajoutPion(plateau, coordX, coordY, pion);
                        }
                    }
                }
            }
        }
        return echetEtMat;
    }

    /*
    Prends les coordonnées du pion choisi, indique le pion et sa position, demande ensuite la position souhaitez et deplace le pion s'il a le droit
     */
    public static String choixPionEtDeplacement(String[][] plateau, int coordX, int coordY, String choix, ArrayList<String> coups, boolean robot) {
        Scanner sc = new Scanner(System.in);
        int newCoordX, newCoordY;
        switch (plateau[coordY][coordX]) {
            case "RoiB", "RoiN":
                System.out.println("♚ Vous avez choisis le Roi en " + choix);
                break;
            case "ReineB", "ReineN":
                System.out.println("♛ Vous avez choisis la Reine en " + choix);
                break;
            case "TB", "TN":
                System.out.println("♜ Vous avez choisis la Tour en " + choix);
                break;
            case "FN", "FB":
                System.out.println("♝ Vous avez choisis le Fou en " + choix);
                break;
            case "CN", "CB":
                System.out.println("♞ Vous avez choisis le Cavalier en " + choix);
                break;
            case "PN", "PB":
                System.out.println("♟ Vous avez choisis le Pion en " + choix);
                break;
        }
        do {
            do {

                if (!robot) {
                    System.out.println("♟ Position souhaitez");
                    choix = sc.nextLine().toLowerCase();
                } else {
                    choix = IA.selectionPiece();
                }
                newCoordX = (Character.getNumericValue(choix.charAt(0)) - 10);
                newCoordY = 8 - (Character.getNumericValue(choix.charAt(1)));
            } while (bonCoord(coordX, coordY, choix));
        } while (!possible(coups, choix));

        deplacePion(plateau, coordX, coordY, newCoordX, newCoordY, plateau[coordY][coordX]);
        return choix;
    }

    /*
     * Verifie si coups possible | VRAI OU FAUX
     */
    public static boolean possible(ArrayList<String> coups, String coup) {
        boolean possible = false;
        if (coups.contains(coup)) {
            possible = true;
        }
        return possible;
    }

    /*
    Renvoie vrai ou faux, si les coord misent sont correctent, ne dépasse pas le tableau
     */
    public static boolean bonCoord(int coordX, int coordY, String position) {
        return ((coordX > 8 || coordX < 0) || (coordY > 8 || coordY < 0)) || position.length() != 2;
    }

    public static char chiffreALettre(int chiffre) {
        char c = switch (chiffre) {
            case 0 -> 'a';
            case 1 -> 'b';
            case 2 -> 'c';
            case 3 -> 'd';
            case 4 -> 'e';
            case 5 -> 'f';
            case 6 -> 'g';
            case 7 -> 'h';
            default -> 'a';
        };
        return c;
    }


    public static ArrayList<String> tousLesCoupsPossibles(String[][] plateau, int coordDepartX, int coordDepartY, boolean[] onPasBouger) {
        boolean possible = true;
        boolean negatif = false;
        int x = 0;
        int coordArriverXp = coordDepartX; //Xp = X positifs
        int coordArriverYp = coordDepartY;
        int coordArriverXn = coordDepartX; //Xn = X négatifs
        int coordArriverYn = coordDepartY;
        String piece = plateau[coordDepartY][coordDepartX];
        ArrayList<String> coup = new ArrayList<>();

        String convertiEtMoins1ACoordDepartY = Integer.toString(Math.abs(coordDepartY - 1 - 8));
        String convertiEtPlus1ACoordDepartY = Integer.toString(Math.abs(coordDepartY + 1 - 8));
        String convertiEtMoins2ACoordDepartY = Integer.toString(Math.abs(coordDepartY - 2 - 8));
        String convertiEtPlus2ACoordDepartY = Integer.toString(Math.abs(coordDepartY + 2 - 8));
        String converti = Integer.toString(Math.abs(coordDepartY - 8));
        switch (piece) {

            case "PN":

                if (coordDepartX < 7 && coordDepartY < 7 &&!plateau[coordDepartY + 1][coordDepartX + 1].equals("N/D")) {//diagonale droite
                    coup.add((chiffreALettre(coordDepartX + 1)) + convertiEtPlus1ACoordDepartY);
                }
                if (coordDepartX > 0 && coordDepartY < 7 && !plateau[coordDepartY + 1][coordDepartX - 1].equals("N/D")) {//diagonale gauche
                    coup.add((chiffreALettre(coordDepartX - 1)) + convertiEtPlus1ACoordDepartY);
                }
                if (coordDepartY < 7 && plateau[coordDepartY + 1][coordDepartX].equals("N/D")) {//une case avant
                    coup.add((chiffreALettre(coordDepartX)) + convertiEtPlus1ACoordDepartY);


                    if (coordDepartY == 1 && plateau[coordDepartY + 2][coordDepartX].equals("N/D")) {// deux case avant
                        coup.add((chiffreALettre(coordDepartX)) + convertiEtPlus2ACoordDepartY);
                    }
                }
                break;

            case "PB":

                if (coordDepartX < 7 && coordDepartY > 0 && !plateau[coordDepartY - 1][coordDepartX + 1].equals("N/D")) {//diagonale droite
                    coup.add((chiffreALettre(coordDepartX + 1)) + convertiEtMoins1ACoordDepartY);

                }
                if (coordDepartX > 0 && coordDepartY > 0 && !plateau[coordDepartY - 1][coordDepartX - 1].equals("N/D")) {//diagonale gauche
                    coup.add((chiffreALettre(coordDepartX - 1)) + convertiEtMoins1ACoordDepartY);
                }
                if ( coordDepartY > 0 && plateau[coordDepartY - 1][coordDepartX].equals("N/D")) {//une case avant
                    coup.add((chiffreALettre(coordDepartX)) + convertiEtMoins1ACoordDepartY);

                    if (coordDepartY == 6 && plateau[coordDepartY - 2][coordDepartX].equals("N/D")) {// deux case avant
                        coup.add((chiffreALettre(coordDepartX)) + convertiEtMoins2ACoordDepartY);
                    }
                }
                break;

            case "TB", "TN":
                while (coordArriverXp < 8 && coordArriverYp < 8 && coordArriverXn > -1 && coordArriverYn > -1) {
                    if (!negatif) { //Y est X qui augmente
                        if (coordArriverXp < 7) {//horizontale droite
                            coordArriverXp++;
                            coup.add((chiffreALettre(coordArriverXp)) + converti);
                        } else if (coordArriverYp < 7) {//verticale bas
                            coordArriverYp++;
                            coup.add((chiffreALettre(coordDepartX)) + Integer.toString(Math.abs(coordArriverYp - 8)));
                        } else {
                            negatif = true;
                        }

                    } else { //X et Y qui diminue
                        if (coordArriverXn > 0) {//horizontale gauche
                            coordArriverXn--;
                            coup.add((chiffreALettre(coordArriverXn)) + converti);
                        } else if (coordArriverYn > 0) {//verticale haut
                            coordArriverYn--;
                            coup.add((chiffreALettre(coordDepartX)) + Integer.toString(Math.abs(coordArriverYn - 8)));
                        } else {
                            coordArriverXp++;
                        }
                    }
                }
                break;

            case "FN", "FB":
                while (coordArriverXp < 8 && coordArriverYp < 8 && coordArriverXn > -1 && coordArriverYn > -1) {

                    if (!negatif) {
                        if (coordArriverXp < 7) {
                            coordArriverXp++;
                            if (coordArriverYn > 0) {//diagonale haut droit
                                coordArriverYn--;
                                coup.add((chiffreALettre(coordArriverXp)) + Integer.toString(Math.abs(coordArriverYn - 8)));
                            }
                            if (coordArriverYp < 7) {//diagonale bas droit
                                coordArriverYp++;
                                coup.add((chiffreALettre(coordArriverXp)) + Integer.toString(Math.abs(coordArriverYp - 8)));
                            }

                        } else {
                            negatif = true;
                            coordArriverYp = coordDepartY;
                            coordArriverYn = coordDepartY;
                        }
                    } else { //on descend
                        if (coordArriverXn > 0) {
                            coordArriverXn--;
                            if (coordArriverYn > 0) {//diagonale haut gauche
                                coordArriverYn--;
                                coup.add((chiffreALettre(coordArriverXn)) + Integer.toString(Math.abs(coordArriverYn - 8)));
                            }
                            if (coordArriverYp < 7) {// diagonale bas gauche
                                coordArriverYp++;
                                coup.add((chiffreALettre(coordArriverXn)) + Integer.toString(Math.abs(coordArriverYp - 8)));
                            }
                        } else {
                            coordArriverXp++;
                        }
                    }
                }
                break;

            case "RoiN", "RoiB":
                if (coordDepartX < 7) {// droite
                    coup.add((chiffreALettre(coordDepartX + 1)) + converti);
                }
                if (coordDepartX > 0) {// gauche
                    coup.add((chiffreALettre(coordDepartX - 1)) + converti);
                }
                if (coordDepartX < 7 && coordDepartY > 0) {// haut droite
                    coup.add((chiffreALettre(coordDepartX + 1)) + convertiEtMoins1ACoordDepartY);
                }
                if (coordDepartX < 7 && coordDepartY < 7) {// bas droite
                    coup.add((chiffreALettre(coordDepartX + 1)) + convertiEtPlus1ACoordDepartY);
                }
                if (coordDepartX > 0 && coordDepartY < 7) {// bas gauche
                    coup.add((chiffreALettre(coordDepartX - 1)) + convertiEtPlus1ACoordDepartY);
                }
                if (coordDepartX > 0 && coordDepartY > 0) {// haut gauche
                    coup.add((chiffreALettre(coordDepartX - 1)) + convertiEtMoins1ACoordDepartY);
                }
                if (coordDepartY > 0) {// haut
                    coup.add((chiffreALettre(coordDepartX)) + convertiEtMoins1ACoordDepartY);
                }
                if (coordDepartY < 7) {// bas
                    coup.add((chiffreALettre(coordDepartX)) + convertiEtPlus1ACoordDepartY);
                }
                if (piece.equals("RoiN") && onPasBouger[4]) {//rock noir

                    if (plateau[0][7].equals("TN") && onPasBouger[5] && plateau[coordDepartY][coordDepartX + 1].equals("N/D") && plateau[coordDepartY][coordDepartX + 2].equals("N/D")) { // rock droit

                        coup.add((chiffreALettre(coordDepartX + 2)) + converti);
                    } else if (plateau[0][0].equals("TN") && onPasBouger[3] && plateau[coordDepartY][coordDepartX - 1].equals("N/D") && plateau[coordDepartY][coordDepartX - 2].equals("N/D") && plateau[coordDepartY][coordDepartX - 3].equals("N/D")) {// rock gauche

                        coup.add((chiffreALettre(coordDepartX - 2)) + converti);
                    }


                } else if (piece.equals("RoiB") && onPasBouger[1]) {//rock blanc

                    if (plateau[7][7].equals("TN") && onPasBouger[2] && plateau[coordDepartY][coordDepartX + 1].equals("N/D") && plateau[coordDepartY][coordDepartX + 2].equals("N/D")) {// rock droit

                        coup.add((chiffreALettre(coordDepartX + 2)) + converti);

                    } else if (plateau[7][0].equals("TN") && onPasBouger[0] && plateau[coordDepartY][coordDepartX - 1].equals("N/D") && plateau[coordDepartY][coordDepartX - 2].equals("N/D") && plateau[coordDepartY][coordDepartX - 3].equals("N/D")) {// rock gauche

                        coup.add((chiffreALettre(coordDepartX - 2)) + converti);
                    }


                }


                break;

            case "ReineB", "ReineN":
                while (coordArriverXp < 8 && coordArriverYp < 8 && coordArriverXn > -1 && coordArriverYn > -1) {
                    if (x != 1) {//deplacement horizontale vertical comme la tour
                        if (!negatif) {
                            if (coordArriverXp < 7) {
                                coordArriverXp++;
                                if (coordArriverYn > 0) {
                                    coordArriverYn--;
                                    coup.add((chiffreALettre(coordArriverXp)) + Integer.toString(Math.abs(coordArriverYn - 8)));
                                }
                                if (coordArriverYp < 7) {
                                    coordArriverYp++;
                                    coup.add((chiffreALettre(coordArriverXp)) + Integer.toString(Math.abs(coordArriverYp - 8)));
                                }

                            } else {
                                negatif = true;
                                coordArriverYp = coordDepartY;
                                coordArriverYn = coordDepartY;
                            }
                        } else {
                            if (coordArriverXn > 0) {
                                coordArriverXn--;
                                if (coordArriverYn > 0) {
                                    coordArriverYn--;
                                    coup.add((chiffreALettre(coordArriverXn)) + Integer.toString(Math.abs(coordArriverYn - 8)));
                                }
                                if (coordArriverYp < 7) {
                                    coordArriverYp++;
                                    coup.add((chiffreALettre(coordArriverXn)) + Integer.toString(Math.abs(coordArriverYp - 8)));
                                }
                            } else {//horizontale et verticale fini alor on reset pour passer au diagonale
                                negatif = false;
                                x = 1;
                                coordArriverXp = coordDepartX;
                                coordArriverYp = coordDepartY;
                                coordArriverXn = coordDepartX;
                                coordArriverYn = coordDepartY;
                            }
                        }
                    } else if (!negatif) { //déplacement diagonale comme le fou
                        if (coordArriverXp < 7) {
                            coordArriverXp++;
                            coup.add((chiffreALettre(coordArriverXp)) + converti);
                        } else if (coordArriverYp < 7) {
                            coordArriverYp++;
                            coup.add((chiffreALettre(coordDepartX)) + Integer.toString(Math.abs(coordArriverYp - 8)));
                        } else {
                            negatif = true;
                        }

                    } else {
                        if (coordArriverXn > 0) {
                            coordArriverXn--;
                            coup.add((chiffreALettre(coordArriverXn)) + converti);
                        } else if (coordArriverYn > 0) {
                            coordArriverYn--;
                            coup.add((chiffreALettre(coordDepartX)) + Integer.toString(Math.abs(coordArriverYn - 8)));
                        } else {
                            coordArriverXp++;
                        }

                    }
                }

                break;
            case "CB", "CN":

                if (coordDepartX < 7 && coordDepartY < 6) {// bas bas droite
                    coup.add((chiffreALettre(coordDepartX + 1)) + convertiEtPlus2ACoordDepartY);
                }
                if (coordDepartX < 7 && coordDepartY > 1) {// haut haut droite
                    coup.add((chiffreALettre(coordDepartX + 1)) + convertiEtMoins2ACoordDepartY);
                }
                if (coordDepartX > 0 && coordDepartY < 6) {// bas bas gauche
                    coup.add((chiffreALettre(coordDepartX - 1)) + convertiEtPlus2ACoordDepartY);
                }
                if (coordDepartX > 0 && coordDepartY > 1) {// haut haut gauche
                    coup.add((chiffreALettre(coordDepartX - 1)) + convertiEtMoins2ACoordDepartY);
                }
                if (coordDepartY < 7 && coordDepartX < 6) {// bas droite
                    coup.add((chiffreALettre(coordDepartX + 2)) + convertiEtPlus1ACoordDepartY);
                }
                if (coordDepartY < 7 && coordDepartX > 1) {// bas gauche
                    coup.add((chiffreALettre(coordDepartX - 2)) + convertiEtPlus1ACoordDepartY);
                }
                if (coordDepartY > 0 && coordDepartX < 6) {// haut droite
                    coup.add((chiffreALettre(coordDepartX + 2)) + convertiEtMoins1ACoordDepartY);
                }
                if (coordDepartY > 0 && coordDepartX > 1) {// haut gauche
                    coup.add((chiffreALettre(coordDepartX - 2)) + convertiEtMoins1ACoordDepartY);
                }

                break;

        }
        return coup;
    }

    public static void pieceBouge(boolean[] onPasBouger, String piece, int X) {

        switch (piece) {
            case "RoiN":
                onPasBouger[4] = false;
                break;

            case "RoiB":
                onPasBouger[1] = false;
                break;

            case "TN":
                if (X == 7) {
                    onPasBouger[5] = false;
                } else onPasBouger[3] = false;
                break;

            case "TB":
                if (X == 7) {
                    onPasBouger[2] = false;
                } else onPasBouger[0] = false;
                break;

        }
    }

    public static void promotion(String[][] plateau, int coordX, int coordY, String piece, boolean robot) {
        String[] promotionNoir = {"CN", "TN", "ReineN", "FN"};
        String[] promotionBlanc = {"CB", "TB", "ReineB", "FB"};
        Scanner sc = new Scanner(System.in);
        if (piece.equals("PN")) {
            do {
                if (!robot) {
                    System.out.println("Quel piece souhaitez vous ? ");
                    piece = sc.nextLine();
                } else {
                    piece = promotionNoir[(int) (Math.random() * 4)];
                }
                System.out.println(piece);
            } while (!piece.equals("CN") && !piece.equals("TN") && !piece.equals("ReineN") && !piece.equals("FN"));

            plateau[coordY][coordX] = piece;

        } else {
            do {
                if (!robot) {
                    System.out.println("Quel piece souhaitez vous ? ");
                    piece = sc.nextLine();
                } else {
                    piece = promotionBlanc[(int) (Math.random() * 4)];
                }
                System.out.println(piece);
            } while (!piece.equals("CB") && !piece.equals("TB") && !piece.equals("ReineB") && !piece.equals("FB"));
            plateau[coordY][coordX] = piece;
        }


    }

    public static void rock(String[][] plateau, int coordX, int coordY, boolean[] onPasBouger, String piece) {
        if (piece.equals("RoiB") && onPasBouger[1]) {
            if (plateau[coordY][coordX - 2].equals("RoiB")) {
                plateau[7][3] = "TB";
                plateau[7][0] = "N/D";
            } else if (plateau[coordY][coordX + 2].equals("RoiB")) {
                plateau[7][5] = "TB";
                plateau[7][7] = "N/D";
            }
        } else if (piece.equals("RoiN") && onPasBouger[4]) {
            if (plateau[coordY][coordX - 2].equals("RoiN")) {
                plateau[0][3] = "TN";
                plateau[0][0] = "N/D";
            } else if (plateau[coordY][coordX + 2].equals("RoiN")) {
                plateau[0][5] = "TN";
                plateau[0][7] = "N/D";
            }

        }

    }

    /*
    Verifie si y a possibilité de draw, renvoie True ou False
     */
    public static boolean draw(String[][] plateau, boolean noirOuBlanc,boolean[] onPasBouger) {
        ArrayList<String> coupDraw = new ArrayList<>();
        char lastChar;
        boolean draw = true;
        String pion;
        int coordXSimu, coordYSimu;
        if (noirOuBlanc) {
            lastChar = 'N';
        } else {
            lastChar = 'B';
        }
        for (
                int ligne = 0;
                ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne].charAt(plateau[ligne][colonne].length() - 1) == lastChar) {
                    Pion.coupsPossible(plateau, coupDraw, colonne, ligne, onPasBouger);
                    if (!coupDraw.isEmpty()) {
                        for (int i = 0; i < coupDraw.size(); i++) {
                            coordXSimu = (Character.getNumericValue(coupDraw.get(i).charAt(0)) - 10);
                            coordYSimu = 8 - (Character.getNumericValue(coupDraw.get(i).charAt(1)));
                            if (!plateau[coordYSimu][coordXSimu].equals("N/D")) {
                                pion = plateau[coordYSimu][coordXSimu];
                            } else {
                                pion = "N/D";
                            }
                            Pion.deplacePion(plateau, colonne, ligne, coordXSimu, coordYSimu, plateau[ligne][colonne]);
                            boolean couleurPion;
                            couleurPion = !noirOuBlanc;
                            if (!Pion.echec(plateau, couleurPion, onPasBouger)) {
                                draw = false;
                            }

                            Pion.deplacePion(plateau, coordXSimu, coordYSimu, colonne, ligne, plateau[coordYSimu][coordXSimu]);
                            Pion.ajoutPion(plateau, coordXSimu, coordYSimu, pion);

                        }
                    }
                }
            }
        }
        return draw;
    }
}