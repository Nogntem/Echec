public class IA {
    /*
    IA Basique++++++++++, un math random ;(
     */
    public static String selectionPiece() {
        String IA;

        IA = Character.toString(Pion.chiffreALettre((int)(Math.random()*8))) + Integer.toString(Math.abs((int)(Math.random()*8) - 8));

        return IA;
    }
}
