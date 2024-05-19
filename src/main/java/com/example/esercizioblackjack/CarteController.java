package com.example.esercizioblackjack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collections;
public class CarteController {
    public Button btnPesca;
    public Label SommaUtente;
    public int sommautenteintero = 0;
    public Label SommaBanco;
    public int sommabancointero = 0;
    private final int nCarte = 9;
    public HBox hboxUtente;
    public HBox hboxBanco;
    public Label DenaroPuntato;
    public int DenaroPuntatoIntero;
    public Button btnPuntaTutto;
    public Button btnPunta3_4;
    public Button btnPunta1_2;
    public Button btnPunta1_4;
    public Label SommaTotale; //del denaro
    public int DenaroIniziale = 5000;
    public int[] sommautentevettore = new int[nCarte];
    public int[] sommabancovettore = new int[nCarte];
    public Button btnResta;
    public Label lblVittoriaUtente;
    public Label lblVittoriaBanco;
    public Button btnnuovapartita;
    private int nuovaCarta;
    private ImageView[] carte;

    public File Carte_Immagine(int i, int[] v) {
        File f = null;

        int[] valori = {11,2,3,4,5,6,7,8,9,10,10,10,10};
        int n = (int) (Math.random() * (52) + 1);
        v[i] = valori[(n - 1) % 13];

        try {
            f = Paths.get(CarteController.class.getResource("images/carte" + n + ".png").toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return f;
    }
    @FXML
    void initialize() {
        btnPesca.setVisible(false);
        btnResta.setVisible(false);
        btnPuntaTutto.setVisible(true);
        btnPunta1_2.setVisible(true);
        btnPunta1_4.setVisible(true);
        btnPunta3_4.setVisible(true);
        sommabancointero = 0;
        sommautenteintero = 0;
        SommaBanco.setText(sommabancointero + "");
        SommaUtente.setText(sommabancointero + "");
        for (int i = 0; i < nCarte; i++) {
            sommabancovettore[i] = 0;
            sommautentevettore[i] = 0;
        }
        btnnuovapartita.setVisible(false);
        lblVittoriaBanco.setText("");
        lblVittoriaUtente.setText("");
    }

    public int calcolasomma(int[] v, int i) {
        int somma = 0;
        for (int j = 0; j < i; j++) {
            somma += v[j];
        }
        return somma;
    }
    void inizializza() {
        FileInputStream file = null;
        File f = null;
        for (int i = 0; i < nCarte; i++) {

            //carte utente
            try {
                f = Carte_Immagine( i, sommautentevettore);
                file = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            Image sfondo = new Image(file);
            carte = new ImageView[nCarte];
            carte[i] = new ImageView();
            carte[i].setImage(sfondo);
            hboxUtente.getChildren().add(carte[i]);

            if (i > 1)
                carte[i].setVisible(false);

            //carte banco
            try {
                f = Carte_Immagine(i, sommabancovettore);
                file = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            sfondo = new Image(file);
            carte = new ImageView[nCarte];
            carte[i] = new ImageView();
            carte[i].setImage(sfondo);
            hboxBanco.getChildren().add(carte[i]);

            if (i > 0)
                carte[i].setVisible(false);

        }
        nuovaCarta = 1;
        btnPesca.setVisible(true);
        btnResta.setVisible(true);
        SommaUtente.setText((sommautentevettore[0] + sommautentevettore[1]) + "");
        sommautenteintero = sommautentevettore[0] + sommautentevettore[1];
        SommaBanco.setText(sommabancovettore[0] + "");

    }

    public void onPescaButtonClick(ActionEvent actionEvent) {
        hboxUtente.getChildren().get(nuovaCarta + 1).setVisible(true);
        nuovaCarta++;
        sommautenteintero = calcolasomma(sommautentevettore, nuovaCarta + 1);
        SommaUtente.setText(sommautenteintero + "");

        if (sommautenteintero >= 22)
            onbtnRestaclick();
    }

    public void onbtnPuntaTuttoClick(ActionEvent actionEvent) {
        DenaroPuntatoIntero = DenaroIniziale;
        DenaroIniziale = 0;
        DenaroPuntato.setText(DenaroPuntatoIntero + "");
        SommaTotale.setText(DenaroIniziale + "");
        inizializza();
        btnPuntaTutto.setVisible(false);
        btnPunta1_2.setVisible(false);
        btnPunta1_4.setVisible(false);
        btnPunta3_4.setVisible(false);
    }

    public void onbtnPunta3_4Click(ActionEvent actionEvent) {
        DenaroPuntatoIntero = DenaroIniziale * 3 / 4;
        DenaroIniziale = DenaroIniziale - DenaroIniziale * 3 / 4;
        DenaroPuntato.setText(DenaroPuntatoIntero + "");
        SommaTotale.setText(DenaroIniziale + "");
        inizializza();
        btnPuntaTutto.setVisible(false);
        btnPunta1_2.setVisible(false);
        btnPunta1_4.setVisible(false);
        btnPunta3_4.setVisible(false);
    }

    public void onbtnPunta1_2Click(ActionEvent actionEvent) {
        DenaroPuntatoIntero = DenaroIniziale - DenaroIniziale / 2;
        DenaroIniziale = DenaroIniziale / 2;
        DenaroPuntato.setText(DenaroPuntatoIntero + "");
        SommaTotale.setText(DenaroIniziale + "");
        inizializza();
        btnPuntaTutto.setVisible(false);
        btnPunta1_2.setVisible(false);
        btnPunta1_4.setVisible(false);
        btnPunta3_4.setVisible(false);
    }

    public void onbtnPunta1_4Click(ActionEvent actionEvent) {
        DenaroPuntatoIntero = DenaroIniziale / 4;
        DenaroIniziale = DenaroIniziale - DenaroIniziale / 4;
        DenaroPuntato.setText(DenaroPuntatoIntero + "");
        SommaTotale.setText(DenaroIniziale + "");
        inizializza();
        btnPuntaTutto.setVisible(false);
        btnPunta1_2.setVisible(false);
        btnPunta1_4.setVisible(false);
        btnPunta3_4.setVisible(false);
    }

    public void onbtnRestaclick() {
        btnPesca.setVisible(false);
        btnResta.setVisible(false);

        hboxBanco.getChildren().get(1).setVisible(true);
        sommabancointero = calcolasomma(sommabancovettore, 2);
        SommaBanco.setText(sommabancointero + "");
        int n = 2;
        if (sommautenteintero >= 22 && sommabancointero <= 21) {
            FinePartita(false);
        }

        while (sommabancointero <= sommautenteintero) {
            hboxBanco.getChildren().get(n).setVisible(true);
            sommabancointero = calcolasomma(sommabancovettore, n + 1);
            SommaBanco.setText(sommabancointero + "");
            n++;
        }

        if (sommabancointero > sommautenteintero && sommabancointero <= 21)
            FinePartita(false);
        if (sommabancointero >= 22 && sommautenteintero <= 21)
            FinePartita(true);
    }

    public void FinePartita(boolean vinto_utente) {
        if (!vinto_utente) {
            //ha vinto banco

            lblVittoriaBanco.setText("HA VINTO IL BANCO");
            DenaroPuntatoIntero = 0;
            DenaroPuntato.setText(DenaroPuntatoIntero + "");

        } else {
            //ha vinto utente

            lblVittoriaUtente.setText("HAI VINTO");
            DenaroIniziale += (DenaroPuntatoIntero * 2);
            DenaroPuntatoIntero = 0;
            DenaroPuntato.setText(DenaroPuntatoIntero + "");
            SommaTotale.setText(DenaroIniziale + "");
        }
        btnnuovapartita.setVisible(true);
    }

    public void onbtnnuovapartitaclick() {
        if (DenaroIniziale < 100)
            DenaroIniziale = 1000;
        SommaTotale.setText(DenaroIniziale + "");
        hboxUtente.getChildren().clear();
        hboxBanco.getChildren().clear();
        initialize();
    }
}
