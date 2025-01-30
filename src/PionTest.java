import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PionTest {

    @org.junit.jupiter.api.Test
    void echec() {
        boolean[] onPasBouger = {true,true,true,true,true,true};

        String[][] plateauVide = {
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc = true;
        assertFalse(Pion.echec(plateauVide,noirOuBlanc,onPasBouger));

        String[][] plateauSansEchec = {
                {"N/D", "N/D", "N/D", "RoiN", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc1 = true;
        assertFalse(Pion.echec(plateauSansEchec,noirOuBlanc1,onPasBouger));

        String[][] plateauAvecEchec = {
                {"N/D", "N/D", "N/D", "RoiN", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "TB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc2 = true;
        assertTrue(Pion.echec(plateauAvecEchec,noirOuBlanc2,onPasBouger));

        String[][] plateauSansEchecPieceAllieBloque = {
                {"N/D", "N/D", "N/D", "RoiN", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "PB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "TB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc3 = true;
        assertFalse(Pion.echec(plateauSansEchecPieceAllieBloque,noirOuBlanc3,onPasBouger));

        String[][] plateauSansEchecPieceEnnemieBloque = {
                {"N/D", "N/D", "N/D", "RoiN", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "PN", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "TB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc4 = true;
        assertFalse(Pion.echec(plateauSansEchecPieceEnnemieBloque,noirOuBlanc4,onPasBouger));

    }

    @org.junit.jupiter.api.Test
    void echecEtMat() {
        boolean[] onPasBouger = {true,true,true,true,true,true};

        String[][] plateauVide = {
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc = true;
        assertFalse(Pion.echecEtMat(plateauVide,noirOuBlanc,onPasBouger));

        String[][] plateauSansEchecEtMat = {
                {"N/D", "N/D", "N/D", "RoiN", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc1 = true;
        assertFalse(Pion.echecEtMat(plateauSansEchecEtMat,noirOuBlanc1,onPasBouger));

        String[][] plateauAvecEchecEtMat = {
                {"N/D", "N/D", "PN", "RoiN", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "PN", "N/D", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "PN", "N/D", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "TB", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc2 = true;
        assertTrue(Pion.echecEtMat(plateauAvecEchecEtMat,noirOuBlanc2,onPasBouger));

        String[][] plateauSansEchecEtMatPieceAllieBloque = {
                {"N/D", "N/D", "PN", "RoiN", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "PN", "N/D", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "PN", "N/D", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "PB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "TB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc3 = true;
        assertFalse(Pion.echecEtMat(plateauSansEchecEtMatPieceAllieBloque,noirOuBlanc3,onPasBouger));

        String[][] plateauSansEchecPieceEnnemieBloque = {
                {"N/D", "N/D", "PN", "RoiN", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "PN", "N/D", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "PB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "TB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc4 = true;
        assertFalse(Pion.echecEtMat(plateauSansEchecPieceEnnemieBloque,noirOuBlanc4,onPasBouger));

        String[][] plateauSansEchecEtMatPiecePeutEtreManger = {
                {"N/D", "N/D", "PN", "RoiN", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "PN", "N/D", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "PN", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "TB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc5 = true;
        assertFalse(Pion.echecEtMat(plateauSansEchecEtMatPiecePeutEtreManger,noirOuBlanc5,onPasBouger));

        String[][] plateauSansEchecEtMatRoiBouge = {
                {"N/D", "N/D", "N/D", "RoiN", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "TB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };
        boolean noirOuBlanc6 = true;
        assertFalse(Pion.echecEtMat(plateauSansEchecEtMatPiecePeutEtreManger,noirOuBlanc6,onPasBouger));
    }
    @org.junit.jupiter.api.Test
    void deplacement() {

        boolean noirOuBlanc = true;
        boolean[] onPasBouger = {true, true, true, true, true, true};

        String[][] plateauAvecTour = {
                {"TB", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };

        ArrayList<String>coupTour=new ArrayList<>();
        coupTour.add("b8");
        coupTour.add("c8");
        coupTour.add("d8");
        coupTour.add("e8");
        coupTour.add("f8");
        coupTour.add("g8");
        coupTour.add("h8");
        coupTour.add("a7");
        coupTour.add("a6");
        coupTour.add("a5");
        coupTour.add("a4");
        coupTour.add("a3");
        coupTour.add("a2");
        coupTour.add("a1");

        ArrayList<String> coupsTour = Pion.tousLesCoupsPossibles(plateauAvecTour,0,0,onPasBouger);
        Pion.coupsPossible(plateauAvecTour,coupsTour,0,0,onPasBouger);
        assertEquals(coupTour,coupsTour,"même");

        String[][] plateauAvecPion = {
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "PN", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "PB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };

        ArrayList<String>coupPionBlanc=new ArrayList<>();

        coupPionBlanc.add("e3");
        coupPionBlanc.add("d3");
        coupPionBlanc.add("d4");

        ArrayList<String> coupsPion = Pion.tousLesCoupsPossibles(plateauAvecPion,3,6,onPasBouger);
        Pion.coupsPossible(plateauAvecPion,coupsPion,3,6,onPasBouger);
        assertEquals(coupPionBlanc,coupsPion,"même");

        String[][] plateauAvecCavalier = {
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "CB", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "PN", "N/D", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "PB", "N/D", "N/D", "N/D", "N/D"},
                {"N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D", "N/D"}
        };

        ArrayList<String>coupCavalier=new ArrayList<>();

        coupCavalier.add("f2");
        coupCavalier.add("f6");
        coupCavalier.add("d6");
        coupCavalier.add("g3");
        coupCavalier.add("c3");
        coupCavalier.add("g5");
        coupCavalier.add("c5");


        ArrayList<String> coupsCavalier = Pion.tousLesCoupsPossibles(plateauAvecCavalier,4,4,onPasBouger);
        Pion.coupsPossible(plateauAvecCavalier,coupsCavalier,4,4,onPasBouger);
        assertEquals(coupCavalier,coupsCavalier,"même");


    }
}