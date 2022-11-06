package com.juego;

import com.juego.model.Game;
import com.juego.model.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JuegoController {
   private Game juego;
   private Player jugador;
   private ArrayList<ImageView> imagenesJugador = new ArrayList<>();
   private ArrayList<ImageView> imagenesBank = new ArrayList<>();

    @FXML
    private ImageView imageJugador1, imageJugador2, imageJugador3, imageJugador4, imageJugador5, imageJugador6, imageJugador7, imageJugador8, imageJugador9, imageJugador10;
    @FXML
    private ImageView imabank1, imabank2, imabank3, imabank4, imabank5, imabank6, imabank7, imabank8, imabank9, imabank10;
    @FXML
    private Label puntosJugador, puntosBanck, lbGanador, lbPerdidos, lbGanados, lbNumJuegos, lbNombre;
    @FXML
    private Button btnReset, btnCambiaUsuario, btnStand, btncard;

    @FXML
    protected void onCardButtonClick(Event a) {
        juego.playerTurn();
        cargarImagen();
        if (juego.getGameOver()) {
            btnReset.setVisible(true);
            btnCambiaUsuario.setVisible(true);
            btncard.setDisable(true);
            btnStand.setDisable(true);
            lbGanador.setText(String.format("%s wins, %s loses", juego.getWinner(), juego.getLoser()));
            updPartida();
        }

    }

    @FXML
    protected void onStandButtonClick(ActionEvent actionEvent) {
        juego.bankerTurn();
        cargarImagen();
        if (juego.getGameOver()) {
            btnReset.setVisible(true);
            btnCambiaUsuario.setVisible(true);
            btncard.setDisable(true);
            btnStand.setDisable(true);
            updPartida();
            lbGanador.setText(String.format("%s wins, %s loses", juego.getWinner(), juego.getLoser()));
        }
    }

    @FXML
    protected void onResetButtonClick(ActionEvent actionEvent) {
        for (ImageView a : imagenesBank) {
            a.setImage(null);
        }
        for (ImageView a : imagenesJugador) {
            a.setImage(null);
        }
        btnReset.setVisible(false);
        btnCambiaUsuario.setVisible(false);
        btncard.setDisable(false);
        btnStand.setDisable(false);
        lbGanador.setText("");
        reset();
    }

    @FXML
    protected void onclickCambiaUsuario(ActionEvent event) {
        FXMLLoader loader;
        try {
            Stage stage = (Stage) imabank1.getScene().getWindow();
            loader = new FXMLLoader(LoginController.class.getResource("Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void initialize() {
        imagenesJugador.add(imageJugador1);
        imagenesJugador.add(imageJugador2);
        imagenesJugador.add(imageJugador3);
        imagenesJugador.add(imageJugador4);
        imagenesJugador.add(imageJugador5);
        imagenesJugador.add(imageJugador6);
        imagenesJugador.add(imageJugador7);
        imagenesJugador.add(imageJugador8);
        imagenesJugador.add(imageJugador9);
        imagenesJugador.add(imageJugador10);
        imagenesBank.add(imabank1);
        imagenesBank.add(imabank2);
        imagenesBank.add(imabank3);
        imagenesBank.add(imabank4);
        imagenesBank.add(imabank5);
        imagenesBank.add(imabank6);
        imagenesBank.add(imabank7);
        imagenesBank.add(imabank8);
        imagenesBank.add(imabank9);
        imagenesBank.add(imabank10);
    }
    private void updPartida() {
        lbGanados.setText(String.valueOf(jugador.getWonGames()));
        lbNombre.setText(juego.getPlayerName());
        lbPerdidos.setText(String.valueOf(jugador.getLostGames()));
        lbNumJuegos.setText(String.valueOf(jugador.getPlayedGames()));
        puntosBanck.setText(String.valueOf(juego.getBankerHand().getValue()));
        puntosJugador.setText(String.valueOf(juego.getPlayerHand().getValue()));
    }

    private void cargarImagen() {
        for (int i = 0; i < juego.getPlayerHand().getCards().size(); i++) {
            imagenesJugador.get(i).setImage(new Image(new File(String.format("src/main/resources/com/juego/images/%s.png", juego.getPlayerHand().getCards().get(i).getCardCode())).toURI().toString()));
        }
        for (int i = 0; i < juego.getBankerHand().getCards().size(); i++) {
            imagenesBank.get(i).setImage(new Image(new File(String.format("src/main/resources/com/juego/images/%s.png", juego.getBankerHand().getCards().get(i).getCardCode())).toURI().toString()));
        }
        updPartida();
    }

    public void pedirJugador(Player jugador) {
        this.jugador = jugador;
        System.out.println(jugador.getName());
        reset();
    }

    private void reset() {
        juego = new Game(jugador);
        updPartida();
        cargarImagen();
    }

}