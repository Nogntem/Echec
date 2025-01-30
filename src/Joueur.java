import java.util.Scanner;

public class Joueur {
    public static int ChoixPseudo(String[] pseudos,int nbrIA) {
        Scanner sc = new Scanner(System.in);
        String pseudo = "";
        int emplacementVide = 0, emplacementNonVide = 0;
        pseudos[0] = "";
        pseudos[1] = "";
        System.out.println("♟ Saisir vos pseudos : (Si vous souhaitez un pseudo random, choisissez le 1).");
        do {
            System.out.println("♟ Pseudo Joueur1 :");
            if(nbrIA != 2) {
                pseudo = sc.nextLine();
            }
            if(pseudo.equals("1") || nbrIA == 2){
                pseudo = pseudoRandom();
            }
        } while (pseudo.isEmpty());
        System.out.println("♟ Votre pseudo :" +pseudo);
        emplacementNonVide = (int) (Math.random() * 2);
        pseudos[emplacementNonVide] = pseudo;
        for (int i = 0; i < pseudos.length; i++) {
            if (pseudos[i].isEmpty()) {
                emplacementVide = i;
            } else {
                emplacementNonVide = i;
            }
        }
        do {
            System.out.println("♟ Pseudo Joueur2 :");
            if(nbrIA == 0) {
                pseudo = sc.nextLine();
            }
            if(pseudo.equals("1") || nbrIA == 2 || nbrIA == 1){
                pseudo = pseudoRandom();
            }
        } while (pseudo.equals(pseudos[emplacementNonVide]) || pseudo.isEmpty());
        System.out.println("♟ Votre pseudo :" +pseudo);
        pseudos[emplacementVide] = pseudo;
        return emplacementVide;
    }

    public static String pseudoRandom() {
        String[] prefixes = {"Baron ", "Marquis ", "Duc ", "Comte ", "Chevalier ", "Seigneur ", "Sire ", "Capitaine ", "Archiduc ", "Paladin ", "Écuyer ", "Régent ", "Prince ", "Vizir ", "Gouverneur ", "Alchimiste ", "Maître ", "Commandeur ", "Légat ","Chancelier "};
        String[] entreDeux = {"de/d' ","mangeur de/d' ","avec son/sa ","le/la "};
        String[] suffixes = {"Dragon", "Phénix", "Griffon", "Licorne", "Hippogriffe", "Basilic", "Chimère", "Hydre", "Sphinx", "Serpent Céleste", "Gobelin", "Kobold", "Troll", "Loup Spectral", "Corbeau Éthéré", "Cerf Lumineux"};

        String prefix = prefixes[(int)(Math.random()*20)];
        String entreDe = entreDeux[(int)(Math.random()*4)];
        String suffix = suffixes[(int)(Math.random()*16)];
        return prefix + entreDe + suffix;
    }
}
