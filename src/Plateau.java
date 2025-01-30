import org.w3c.dom.ls.LSOutput;

public class Plateau {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset
    // Regular Colors
    public static final String BLACK = "\033[30m";   // BLACK
    // Background
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
    //BRIGHT
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    /*
    Crée un plateau remplie de "N/D"
     */
    public static String[][] plateau() {
        String[][] plateau = new String[8][8];
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                plateau[ligne][colonne] = "N/D";
            }
        }
        return plateau;
    }

    /*
    Reset un plateau, ajoute que des "N/D"
     */
    public static void resetPlateau(String[][] plateau) {
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                plateau[ligne][colonne] = "N/D";
            }
        }
    }

    /*
    Enleve les coups possible du plateau
     */
    public static void resetCoups(String[][] plateau) {
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                switch (plateau[ligne][colonne]) {
                    case "R?oiB":
                        plateau[ligne][colonne] = "RoiB";
                        break;
                    case "R?eineB":
                        plateau[ligne][colonne] = "ReineB";
                        break;
                    case "R?oiN":
                        plateau[ligne][colonne] = "RoiN";
                        break;
                    case "R?eineN":
                        plateau[ligne][colonne] = "ReineN";
                        break;
                    case "T?B":
                        plateau[ligne][colonne] = "TB";
                        break;
                    case "T?N":
                        plateau[ligne][colonne] = "TN";
                        break;
                    case "F?N":
                        plateau[ligne][colonne] = "FN";
                        break;
                    case "F?B":
                        plateau[ligne][colonne] = "FB";
                        break;
                    case "C?N":
                        plateau[ligne][colonne] = "CN";
                        break;
                    case "C?B":
                        plateau[ligne][colonne] = "CB";
                        break;
                    case "P?N":
                        plateau[ligne][colonne] = "PN";
                        break;
                    case "P?B":
                        plateau[ligne][colonne] = "PB";
                        break;
                    case "??":
                        plateau[ligne][colonne] = "N/D";
                }
            }
        }
    }

    /*
    Traduis le code couleur
     */
    public static String codeCouleur(String couleur) {
        switch (couleur.toLowerCase()) {
            case "vert":
                couleur = GREEN_BACKGROUND;
                break;
            case "rouge":
                couleur = RED_BACKGROUND;
                break;
            case "jaune":
                couleur = YELLOW_BACKGROUND;
                break;
            case "bleu":
                couleur = BLUE_BACKGROUND;
                break;
            case "violet":
                couleur = PURPLE_BACKGROUND;
                break;
            case "cyan":
                couleur = CYAN_BACKGROUND;
                break;
        }
        return couleur;
    }

    /*
    Affiche le plateau avec les bonnes couleurs, bon pion
     */

    public static void afficherPlateau(String[][] plateau, String couleur1, String couleur2) {
        couleur1 = codeCouleur(couleur1);
        couleur2 = codeCouleur(couleur2);
        int compteur = 8;
        System.out.print("           ╔════════════════════════════════╗\n" +
                "           ║ ♟        Jeu d'Échecs       ♟ ║               \n" +
                "           ╠════════════════════════════════╣               \n" );
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            System.out.print("           ║    ");
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {

                if ((ligne + colonne) % 2 == 0) {
                    if (plateau[ligne][colonne].charAt(1) == '?') {
                        afficherPion(plateau, ligne, colonne, WHITE_BACKGROUND);
                    } else {
                        afficherPion(plateau, ligne, colonne, couleur1);
                    }
                } else {
                    if (plateau[ligne][colonne].charAt(1) == '?') {
                        afficherPion(plateau, ligne, colonne, WHITE_BACKGROUND);
                    } else {
                        afficherPion(plateau, ligne, colonne, couleur2);
                    }
                }

                System.out.print(RESET);


            }

            System.out.print(" " + compteur +"  ║ ");
            compteur--;
            System.out.println();

        }
        System.out.println("           ║    A  B  C  D  E  F  G  H      ║");
        System.out.println("           ╚════════════════════════════════╝               ");
    }

    /*
    Traduis notre écriture en pion, tels que RoiB = ♔
     */
    public static void afficherPion(String[][] plateau, int coord1, int coord2, String couleur) {
        switch (plateau[coord1][coord2]) {
            case "RoiB", "R?oiB":
                System.out.print(couleur + WHITE_BOLD_BRIGHT + " ♔ ");
                break;
            case "ReineB", "R?eineB":
                System.out.print(couleur + WHITE_BOLD_BRIGHT + " ♕ ");
                break;
            case "RoiN", "R?oiN":
                System.out.print(couleur + BLACK + " ♚ ");
                break;
            case "ReineN", "R?eineN":
                System.out.print(couleur + BLACK + " ♛ ");
                break;
            case "TB", "T?B":
                System.out.print(couleur + WHITE_BOLD_BRIGHT + " ♖ ");
                break;
            case "TN", "T?N":
                System.out.print(couleur + BLACK + " ♜ ");
                break;
            case "FN", "F?N":
                System.out.print(couleur + BLACK + " ♝ ");
                break;
            case "FB", "F?B":
                System.out.print(couleur + WHITE_BOLD_BRIGHT + " ♗ ");
                break;
            case "CN", "C?N":
                System.out.print(couleur + BLACK + " ♞ ");
                break;
            case "CB", "C?B":
                System.out.print(couleur + WHITE_BOLD_BRIGHT + " ♘ ");
                break;
            case "PN", "P?N":
                System.out.print(couleur + BLACK + " ♟ ");
                break;
            case "PB", "P?B":
                System.out.print(couleur + WHITE_BOLD_BRIGHT + " ♙ ");
                break;
            case "N/D":
                System.out.print(couleur + "   ");
                break;
            case "??":
                System.out.print(couleur + " ? ");
        }
    }

    /*
    Initialise le plateau avec les pions aux bons endroits
     */
    public static void initialisation(String[][] plateau) {
        for (int colonne = 0; colonne < plateau.length; colonne++) {
            plateau[1][colonne] = "PN";
            plateau[6][colonne] = "PB";
            if (colonne == 0 || colonne == 7) {
                plateau[0][colonne] = "TN";
                plateau[7][colonne] = "TB";
            } else if (colonne == 1 || colonne == 6) {
                plateau[0][colonne] = "CN";
                plateau[7][colonne] = "CB";
            } else if (colonne == 2 || colonne == 5) {
                plateau[0][colonne] = "FN";
                plateau[7][colonne] = "FB";
            } else if (colonne == 3) {
                plateau[0][colonne] = "ReineN";
                plateau[7][colonne] = "ReineB";
            } else if (colonne == 4) {
                plateau[0][colonne] = "RoiN";
                plateau[7][colonne] = "RoiB";
            }
        }

    }

}
