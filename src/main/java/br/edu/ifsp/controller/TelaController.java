package br.edu.ifsp.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.model.Triangulo;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TelaController {

    @FXML
    private ComboBox<String> cbTipoLinha;

    @FXML
    private Canvas canvasDesenho;

    @FXML
    private Button btAdicionarTriangulo;

    @FXML
    private Button btLimpar;

    @FXML
    private ColorPicker cpCor;

    @FXML
    private Button btTodosTriangulos;

    // não é possível injetar o GraphicsContext diretamente
    // então, é necessário criar um atributo para armazenar o contexto
    private GraphicsContext gc;

    private List<Triangulo> triangulos = new ArrayList<>();
    private List<Point2D> pontos = new ArrayList<>();

    @FXML
    private void initialize() {
        System.out.println(">>> Inicializando TelaController...");
        cbTipoLinha.getItems().clear();
        cbTipoLinha.getItems().addAll("Sólida", "Pontilhada");
        cbTipoLinha.getSelectionModel().select("Sólida");

        gc = canvasDesenho.getGraphicsContext2D();
        clear();
    }

    @FXML
    private void handleMouseClicked(MouseEvent evento) {
        if (pontos.size() < 3) {
            pontos.add(new Point2D(evento.getX(), evento.getY()));
            gc.setFill(Color.RED);
            gc.fillOval(evento.getX(), evento.getY(), 5, 5); // desenha um círculo de raio 5
        }

        if (pontos.size() == 3) {
            Triangulo temp = new Triangulo(pontos);
            temp.setCor(cpCor.getValue());
            temp.setLinha(cbTipoLinha.getSelectionModel().getSelectedItem());
            temp.desenhar(gc);
            btAdicionarTriangulo.setDisable(false);
        }
    }

    @FXML
    private void handleAdicionarTriangulo() {
        Triangulo triangulo = new Triangulo(pontos, cpCor.getValue(), cbTipoLinha.getSelectionModel().getSelectedItem());
        triangulos.add(triangulo);
        reset();
        if (btTodosTriangulos.isDisable()) {
            btTodosTriangulos.setDisable(false);
        }
    }

    private void reset() {
        clear();
        pontos.clear();
        btAdicionarTriangulo.setDisable(true);
    }

    private void clear(){
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvasDesenho.getWidth(), canvasDesenho.getHeight());
    }

    @FXML
    private void handleTodosTriangulos() {
        System.out.println("=".repeat(5));
        for (Triangulo triangulo : triangulos) {
            triangulo.desenhar(gc);
            System.out.println(triangulo); // debug
        }
    }

    @FXML
    private void handleLimpar() {
        reset();
    }

}
