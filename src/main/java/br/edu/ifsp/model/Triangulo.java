package br.edu.ifsp.model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangulo {

    private List<Point2D> pontos;
    private Color cor;
    private String linha;

    public Triangulo(List<Point2D> pontos) {
        if (pontos.size() == 3) {
            // garante que os pontos não serão alterados
            this.pontos = new ArrayList<>(pontos);
        } else {
            throw new IllegalArgumentException("O triângulo deve ser formado por 3 pontos");
        }
    }

    public Triangulo(List<Point2D> pontos, Color cor, String linha) {
        this(pontos);
        this.cor = cor;
        this.linha = linha;
    }

    public Color getCor() {
        return cor;
    }

    public String getLinha() {
        return linha;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public void desenhar(GraphicsContext gc) {
        gc.setStroke(cor);
        gc.setLineWidth(2.0);

        if(linha.equals("Pontilhada")) {
            gc.setLineDashes(5);
        } else {
            gc.setLineDashes(0);
        }

        for(int i = 0; i < pontos.size(); i++) {
            Point2D inicio = pontos.get(i);
            Point2D fim = pontos.get((i + 1) % pontos.size());
            gc.strokeLine(inicio.getX(), inicio.getY(), fim.getX(), fim.getY());
        }
    }

    @Override
    public String toString() {
        return "Triangulo [cor=" + cor + ", linha=" + linha + ",\n\tpontos=" + pontos + "]";
    }
}
