package com.redcatdev86.ui.view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FormationView extends VBox {

    private final Pane pitch = new Pane();

    private final Label instruction = new Label("You must play with the formation");
    private final Button generateFormation = new Button("GENERATE FORMATION");
    private final Label formationLabel = new Label("");
    private final Label selectedRole = new Label("");

    private final List<Formation> formations = List.of(
            Formations.F_41212_NARROW,
            Formations.F_4412,
            Formations.F_4141,
            Formations.F_4213,
            Formations.F_4222,
            Formations.F_4231_NARROW,
            Formations.F_4231_WIDE,
            Formations.F_424,
            Formations.F_4312,
            Formations.F_4321,
            Formations.F_433_1_STANDARD,
            Formations.F_433_2_HOLDING,
            Formations.F_433_3_DEF,
            Formations.F_433_4_ATT,
            Formations.F_433_5_FALSE9,
            Formations.F_4411,
            Formations.F_442_FLAT,
            Formations.F_442_HOLDING,
            Formations.F_451_CLASSIC,
            Formations.F_451_2_3CM,
            Formations.F_3142,
            Formations.F_3412,
            Formations.F_3421,
            Formations.F_343_FLAT,
            Formations.F_352,
            Formations.F_5212,
            Formations.F_5221,
            Formations.F_532,
            Formations.F_541_DIAMOND,
            Formations.F_541_FLAT
    );

    public FormationView() {
        getStyleClass().add("page");
        setAlignment(Pos.TOP_CENTER);
        setSpacing(14);
        setPadding(new Insets(18, 10, 10, 10));

        Label title = new Label("FORMATION");
        title.getStyleClass().add("page-title");

        instruction.getStyleClass().add("home-muted");
        instruction.setAlignment(Pos.CENTER);

        generateFormation.getStyleClass().add("cta-btn");

        formationLabel.getStyleClass().add("result-type");
        formationLabel.setMaxWidth(Double.MAX_VALUE);
        formationLabel.setAlignment(Pos.CENTER);

        pitch.getStyleClass().add("pitch");
        pitch.setPrefSize(720, 430);
        pitch.setMinSize(720, 430);
        pitch.setMaxSize(720, 430);

        selectedRole.getStyleClass().add("result-extra");
        selectedRole.setMaxWidth(Double.MAX_VALUE);
        selectedRole.setAlignment(Pos.CENTER);

        getChildren().addAll(
                title,
                instruction,
                generateFormation,
                formationLabel,
                pitch,
                selectedRole
        );

        generateFormation.setOnAction(e -> generateAndRender());

        // primo render
        generateAndRender();
    }

    private void generateAndRender() {
        int idx = ThreadLocalRandom.current().nextInt(formations.size());
        Formation f = formations.get(idx);

        formationLabel.setText("FORMATION TO USE: " + f.name());
        selectedRole.setText("");

        renderFormation(f);
    }

    private void renderFormation(Formation f) {
        pitch.getChildren().clear();

        // Campo completo (sfondo + linee + porte + aree + cerchi ecc.)
        pitch.getChildren().addAll(buildPitchShapes());

        // Pallini giocatori
        for (RoleSpot spot : f.spots()) {
            StackPane token = createPlayerToken(spot.role(), spot.x(), spot.y());
            pitch.getChildren().add(token);
        }
    }

    private StackPane createPlayerToken(String role, double xRel, double yRel) {
        Circle dot = new Circle(10);
        dot.getStyleClass().add("player-dot");

        Label r = new Label(role);
        r.getStyleClass().add("player-label");

        StackPane sp = new StackPane(dot, r);
        sp.setPickOnBounds(false);

        sp.layoutXProperty().bind(pitch.widthProperty().multiply(xRel).subtract(18));
        sp.layoutYProperty().bind(pitch.heightProperty().multiply(yRel).subtract(18));

        sp.setOnMouseClicked(e -> {
            // deseleziona tutti
            pitch.getChildren().forEach(n -> {
                if (n instanceof StackPane stack) {
                    stack.getStyleClass().remove("selected-player");
                }
            });

            sp.getStyleClass().add("selected-player");
            selectedRole.setText("SELECTED ROLE: " + role);
        });

        return sp;
    }

    private List<Node> buildPitchShapes() {
        double pad = 18;

        // sfondo
        Rectangle bg = new Rectangle();
        bg.widthProperty().bind(pitch.widthProperty());
        bg.heightProperty().bind(pitch.heightProperty());
        bg.setArcWidth(18);
        bg.setArcHeight(18);
        bg.getStyleClass().add("pitch-bg");

        // perimetro campo
        Rectangle outline = new Rectangle();
        outline.setX(pad);
        outline.setY(pad);
        outline.widthProperty().bind(pitch.widthProperty().subtract(pad * 2));
        outline.heightProperty().bind(pitch.heightProperty().subtract(pad * 2));
        outline.setFill(null);
        outline.getStyleClass().add("pitch-outline");

        // metà campo
        Line mid = new Line();
        mid.startXProperty().bind(pitch.widthProperty().multiply(0.50));
        mid.endXProperty().bind(pitch.widthProperty().multiply(0.50));
        mid.setStartY(pad);
        mid.endYProperty().bind(pitch.heightProperty().subtract(pad));
        mid.getStyleClass().add("pitch-line");

        // cerchio centro
        Circle centerCircle = new Circle();
        centerCircle.centerXProperty().bind(pitch.widthProperty().multiply(0.50));
        centerCircle.centerYProperty().bind(pitch.heightProperty().multiply(0.50));
        centerCircle.radiusProperty().bind(Bindings.min(pitch.widthProperty(), pitch.heightProperty()).multiply(0.12));
        centerCircle.setFill(null);
        centerCircle.getStyleClass().add("pitch-circle");

        // punto centro
        Circle centerSpot = new Circle();
        centerSpot.centerXProperty().bind(pitch.widthProperty().multiply(0.50));
        centerSpot.centerYProperty().bind(pitch.heightProperty().multiply(0.50));
        centerSpot.setRadius(2.5);
        centerSpot.getStyleClass().add("pitch-spot");

        // ===== AREE SINISTRA =====
        Rectangle boxL = new Rectangle();
        boxL.setX(pad);
        boxL.yProperty().bind(pitch.heightProperty().multiply(0.50).subtract(pitch.heightProperty().multiply(0.22)));
        boxL.widthProperty().bind(pitch.widthProperty().multiply(0.18));
        boxL.heightProperty().bind(pitch.heightProperty().multiply(0.44));
        boxL.setFill(null);
        boxL.getStyleClass().add("pitch-box");

        Rectangle smallL = new Rectangle();
        smallL.setX(pad);
        smallL.yProperty().bind(pitch.heightProperty().multiply(0.50).subtract(pitch.heightProperty().multiply(0.12)));
        smallL.widthProperty().bind(pitch.widthProperty().multiply(0.08));
        smallL.heightProperty().bind(pitch.heightProperty().multiply(0.24));
        smallL.setFill(null);
        smallL.getStyleClass().add("pitch-box");

        Circle penSpotL = new Circle();
        penSpotL.centerXProperty().bind(pitch.widthProperty().multiply(0.18).add(pad));
        penSpotL.centerYProperty().bind(pitch.heightProperty().multiply(0.50));
        penSpotL.setRadius(2.5);
        penSpotL.getStyleClass().add("pitch-spot");

        // FIX: arco agganciato alla linea area (boxL.x + boxL.width)
        javafx.scene.shape.Arc arcL = new javafx.scene.shape.Arc();
        arcL.centerXProperty().bind(boxL.xProperty().add(boxL.widthProperty()));
        arcL.centerYProperty().bind(pitch.heightProperty().multiply(0.50));
        arcL.radiusXProperty().bind(pitch.heightProperty().multiply(0.14));
        arcL.radiusYProperty().bind(pitch.heightProperty().multiply(0.14));
        arcL.setStartAngle(-60);
        arcL.setLength(120);
        arcL.setFill(null);
        arcL.getStyleClass().add("pitch-arc");

        Rectangle goalL = new Rectangle();
        goalL.setX(pad - 6); // leggermente fuori
        goalL.yProperty().bind(pitch.heightProperty().multiply(0.50).subtract(pitch.heightProperty().multiply(0.08)));
        goalL.setWidth(6);
        goalL.heightProperty().bind(pitch.heightProperty().multiply(0.16));
        goalL.setFill(null);
        goalL.getStyleClass().add("pitch-goal");

        // ===== AREE DESTRA (specchio) =====
        Rectangle boxR = new Rectangle();
        boxR.xProperty().bind(pitch.widthProperty().subtract(pad).subtract(pitch.widthProperty().multiply(0.18)));
        boxR.yProperty().bind(boxL.yProperty());
        boxR.widthProperty().bind(boxL.widthProperty());
        boxR.heightProperty().bind(boxL.heightProperty());
        boxR.setFill(null);
        boxR.getStyleClass().add("pitch-box");

        Rectangle smallR = new Rectangle();
        smallR.xProperty().bind(pitch.widthProperty().subtract(pad).subtract(pitch.widthProperty().multiply(0.08)));
        smallR.yProperty().bind(smallL.yProperty());
        smallR.widthProperty().bind(smallL.widthProperty());
        smallR.heightProperty().bind(smallL.heightProperty());
        smallR.setFill(null);
        smallR.getStyleClass().add("pitch-box");

        Circle penSpotR = new Circle();
        penSpotR.centerXProperty().bind(pitch.widthProperty().subtract(pad).subtract(pitch.widthProperty().multiply(0.18)));
        penSpotR.centerYProperty().bind(pitch.heightProperty().multiply(0.50));
        penSpotR.setRadius(2.5);
        penSpotR.getStyleClass().add("pitch-spot");

        // FIX: arco agganciato alla linea area (boxR.x)
        javafx.scene.shape.Arc arcR = new javafx.scene.shape.Arc();
        arcR.centerXProperty().bind(boxR.xProperty());
        arcR.centerYProperty().bind(pitch.heightProperty().multiply(0.50));
        arcR.radiusXProperty().bind(pitch.heightProperty().multiply(0.14));
        arcR.radiusYProperty().bind(pitch.heightProperty().multiply(0.14));
        arcR.setStartAngle(120);
        arcR.setLength(120);
        arcR.setFill(null);
        arcR.getStyleClass().add("pitch-arc");

        Rectangle goalR = new Rectangle();
        goalR.xProperty().bind(pitch.widthProperty().subtract(pad).subtract(6)); // fuori di 6 anche a destra
        goalR.yProperty().bind(goalL.yProperty());
        goalR.setWidth(6);
        goalR.heightProperty().bind(goalL.heightProperty());
        goalR.setFill(null);
        goalR.getStyleClass().add("pitch-goal");

        return List.of(
                bg,
                outline,
                mid,
                centerCircle,
                centerSpot,
                boxL, smallL, penSpotL, arcL, goalL,
                boxR, smallR, penSpotR, arcR, goalR
        );
    }

    // ----- MODEL -----
    public record RoleSpot(String role, double x, double y) {}
    public record Formation(String name, List<RoleSpot> spots) {
        @Override public String toString() { return name; }
    }

    // ----- FORMATIONS -----
    public static final class Formations {
        private Formations() {}
        private static RoleSpot S(String role, double x, double y) { return new RoleSpot(role, x, y); }

        public static final Formation F_41212_NARROW = new Formation("4-1-2-1-2 (Stretto / Diamond)", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CDM",0.40, 0.50),
                S("LM", 0.50, 0.34), S("RM", 0.50, 0.66),
                S("CAM",0.60, 0.50),
                S("ST", 0.74, 0.44), S("ST", 0.74, 0.56)
        ));

        public static final Formation F_4412 = new Formation("4-4-1-2", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("LM", 0.44, 0.20), S("CM", 0.42, 0.45), S("CM", 0.42, 0.55), S("RM", 0.44, 0.80),
                S("CF", 0.66, 0.50),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_4141 = new Formation("4-1-4-1", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CDM",0.40, 0.50),
                S("LM", 0.54, 0.18), S("CM", 0.52, 0.40), S("CM", 0.52, 0.60), S("RM", 0.54, 0.82),
                S("ST", 0.78, 0.50)
        ));

        public static final Formation F_4213 = new Formation("4-2-1-3", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CDM",0.40, 0.44), S("CDM",0.40, 0.56),
                S("CAM",0.58, 0.50),
                S("LW", 0.74, 0.20), S("ST", 0.78, 0.50), S("RW", 0.74, 0.80)
        ));

        public static final Formation F_4222 = new Formation("4-2-2-2", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CDM",0.40, 0.44), S("CDM",0.40, 0.56),
                S("LAM",0.60, 0.34), S("RAM",0.60, 0.66),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_4231_NARROW = new Formation("4-2-3-1 (Stretto)", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CDM",0.40, 0.44), S("CDM",0.40, 0.56),
                S("CAM",0.58, 0.35), S("CAM",0.60, 0.50), S("CAM",0.58, 0.65),
                S("ST", 0.78, 0.50)
        ));

        public static final Formation F_4231_WIDE = new Formation("4-2-3-1 (Largo)", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CDM",0.40, 0.44), S("CDM",0.40, 0.56),
                S("LAM",0.64, 0.22), S("CAM",0.62, 0.50), S("RAM",0.64, 0.78),
                S("ST", 0.80, 0.50)
        ));

        public static final Formation F_424 = new Formation("4-2-4", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CM", 0.44, 0.45), S("CM", 0.44, 0.55),
                S("LW", 0.70, 0.20), S("ST", 0.78, 0.42), S("ST", 0.78, 0.58), S("RW", 0.70, 0.80)
        ));

        public static final Formation F_4312 = new Formation("4-3-1-2", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CM", 0.44, 0.35), S("CM", 0.40, 0.50), S("CM", 0.44, 0.65),
                S("CAM",0.60, 0.50),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_4321 = new Formation("4-3-2-1", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CM", 0.44, 0.35), S("CDM",0.40, 0.50), S("CM", 0.44, 0.65),
                S("CF", 0.62, 0.40), S("CF", 0.62, 0.60),
                S("ST", 0.78, 0.50)
        ));

        public static final Formation F_433_1_STANDARD = new Formation("4-3-3 (1) Standard", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CM", 0.44, 0.35), S("CM", 0.42, 0.50), S("CM", 0.44, 0.65),
                S("LW", 0.72, 0.20), S("ST", 0.78, 0.50), S("RW", 0.72, 0.80)
        ));

        public static final Formation F_433_2_HOLDING = new Formation("4-3-3 (2) Holding", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CM", 0.44, 0.35), S("CDM",0.40, 0.50), S("CM", 0.44, 0.65),
                S("LW", 0.72, 0.20), S("ST", 0.78, 0.50), S("RW", 0.72, 0.80)
        ));

        public static final Formation F_433_3_DEF = new Formation("4-3-3 (3) Def", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CDM",0.40, 0.50),
                S("CM", 0.46, 0.35), S("CM", 0.46, 0.65),
                S("LW", 0.72, 0.20), S("ST", 0.78, 0.50), S("RW", 0.72, 0.80)
        ));

        public static final Formation F_433_4_ATT = new Formation("4-3-3 (4) Att", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CM", 0.46, 0.32), S("CM", 0.44, 0.50), S("CM", 0.46, 0.68),
                S("LW", 0.74, 0.18), S("ST", 0.80, 0.50), S("RW", 0.74, 0.82)
        ));

        public static final Formation F_433_5_FALSE9 = new Formation("4-3-3 (5) False 9", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("CM", 0.44, 0.35), S("CDM",0.40, 0.50), S("CM", 0.44, 0.65),
                S("LW", 0.74, 0.20), S("CF", 0.70, 0.50), S("RW", 0.74, 0.80)
        ));

        public static final Formation F_4411 = new Formation("4-4-1-1 (Seconda punta)", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("LM", 0.44, 0.20), S("CM", 0.42, 0.45), S("CM", 0.42, 0.55), S("RM", 0.44, 0.80),
                S("CF", 0.64, 0.50),
                S("ST", 0.78, 0.50)
        ));

        public static final Formation F_442_FLAT = new Formation("4-4-2 (Flat)", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("LM", 0.44, 0.20), S("CM", 0.42, 0.45), S("CM", 0.42, 0.55), S("RM", 0.44, 0.80),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_442_HOLDING = new Formation("4-4-2 (Holding)", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("LM", 0.44, 0.20), S("CDM",0.40, 0.48), S("CM", 0.44, 0.58), S("RM", 0.44, 0.80),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_451_CLASSIC = new Formation("4-5-1 (Classico)", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("LM", 0.44, 0.18), S("CM", 0.42, 0.40), S("CAM",0.54, 0.50), S("CM", 0.42, 0.60), S("RM", 0.44, 0.82),
                S("ST", 0.78, 0.50)
        ));

        public static final Formation F_451_2_3CM = new Formation("4-5-1 (2) • 3 CM", List.of(
                S("GK", 0.08, 0.50),
                S("LB", 0.22, 0.18), S("CB", 0.22, 0.40), S("CB", 0.22, 0.60), S("RB", 0.22, 0.82),
                S("LM", 0.44, 0.18), S("CM", 0.44, 0.40), S("CM", 0.50, 0.50), S("CM", 0.44, 0.60), S("RM", 0.44, 0.82),
                S("ST", 0.78, 0.50)
        ));

        public static final Formation F_3142 = new Formation("3-1-4-2", List.of(
                S("GK", 0.08, 0.50),
                S("CB", 0.22, 0.30), S("CB", 0.22, 0.50), S("CB", 0.22, 0.70),
                S("CDM",0.36, 0.50),
                S("LM", 0.50, 0.16), S("CM", 0.48, 0.40), S("CM", 0.48, 0.60), S("RM", 0.50, 0.84),
                S("CF", 0.64, 0.50),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_3412 = new Formation("3-4-1-2", List.of(
                S("GK", 0.08, 0.50),
                S("CB", 0.22, 0.30), S("CB", 0.22, 0.50), S("CB", 0.22, 0.70),
                S("LM", 0.44, 0.14), S("CM", 0.44, 0.42), S("CM", 0.44, 0.58), S("RM", 0.44, 0.86),
                S("CAM",0.60, 0.50),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_3421 = new Formation("3-4-2-1", List.of(
                S("GK", 0.08, 0.50),
                S("CB", 0.22, 0.30), S("CB", 0.22, 0.50), S("CB", 0.22, 0.70),
                S("LM", 0.44, 0.14), S("CM", 0.44, 0.42), S("CM", 0.44, 0.58), S("RM", 0.44, 0.86),
                S("CF", 0.62, 0.40), S("CF", 0.62, 0.60),
                S("ST", 0.78, 0.50)
        ));

        public static final Formation F_343_FLAT = new Formation("3-4-3 (Flat)", List.of(
                S("GK", 0.08, 0.50),
                S("CB", 0.22, 0.30), S("CB", 0.22, 0.50), S("CB", 0.22, 0.70),
                S("LM", 0.44, 0.14), S("CM", 0.44, 0.42), S("CM", 0.44, 0.58), S("RM", 0.44, 0.86),
                S("LW", 0.72, 0.22), S("ST", 0.78, 0.50), S("RW", 0.72, 0.78)
        ));

        public static final Formation F_352 = new Formation("3-5-2", List.of(
                S("GK", 0.08, 0.50),
                S("CB", 0.22, 0.30), S("CB", 0.22, 0.50), S("CB", 0.22, 0.70),
                S("LWB",0.44, 0.14),
                S("CM", 0.46, 0.35), S("CDM",0.42, 0.50), S("CM", 0.46, 0.65),
                S("RWB",0.44, 0.86),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_5212 = new Formation("5-2-1-2", List.of(
                S("GK", 0.08, 0.50),
                S("LWB",0.22, 0.14), S("CB", 0.22, 0.35), S("CB", 0.22, 0.50), S("CB", 0.22, 0.65), S("RWB",0.22, 0.86),
                S("CM", 0.44, 0.45), S("CM", 0.44, 0.55),
                S("CAM",0.60, 0.50),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_5221 = new Formation("5-2-2-1", List.of(
                S("GK", 0.08, 0.50),
                S("LWB",0.22, 0.14), S("CB", 0.22, 0.35), S("CB", 0.22, 0.50), S("CB", 0.22, 0.65), S("RWB",0.22, 0.86),
                S("CM", 0.44, 0.45), S("CM", 0.44, 0.55),
                S("CF", 0.64, 0.40), S("CF", 0.64, 0.60),
                S("ST", 0.80, 0.50)
        ));

        public static final Formation F_532 = new Formation("5-3-2", List.of(
                S("GK", 0.08, 0.50),
                S("LWB",0.22, 0.14), S("CB", 0.22, 0.35), S("CB", 0.22, 0.50), S("CB", 0.22, 0.65), S("RWB",0.22, 0.86),
                S("CM", 0.44, 0.32), S("CDM",0.42, 0.50), S("CM", 0.44, 0.68),
                S("ST", 0.78, 0.44), S("ST", 0.78, 0.56)
        ));

        public static final Formation F_541_DIAMOND = new Formation("5-4-1 (Diamond)", List.of(
                S("GK", 0.08, 0.50),
                S("LWB",0.22, 0.14), S("CB", 0.22, 0.35), S("CB", 0.22, 0.50), S("CB", 0.22, 0.65), S("RWB",0.22, 0.86),
                S("LM", 0.44, 0.18), S("CM", 0.42, 0.45), S("CM", 0.42, 0.55), S("RM", 0.44, 0.82),
                S("ST", 0.78, 0.50)
        ));

        public static final Formation F_541_FLAT = new Formation("5-4-1 (Flat)", List.of(
                S("GK", 0.08, 0.50),
                S("LWB",0.22, 0.14), S("CB", 0.22, 0.35), S("CB", 0.22, 0.50), S("CB", 0.22, 0.65), S("RWB",0.22, 0.86),
                S("LM", 0.44, 0.18), S("CM", 0.42, 0.45), S("CM", 0.42, 0.55), S("RM", 0.44, 0.82),
                S("ST", 0.78, 0.50)
        ));
    }
}