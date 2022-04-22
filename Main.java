import ammo.*;
import com.sun.javafx.geom.AreaOp;
import enemies.BarbarianEnemy;
import enemies.GoblinEnemy;
import enemies.GolemEnemy;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import towers.*;

import java.util.ArrayList;

public class Main extends Application {

    BorderPane root;
    Scene scene;
    Stage primaryStage;

    Player player = new Player();
    Monument monument = new Monument();

    int round = 0;

    Text displayMonumentHealth = new Text("");
    Text displayMoney = new Text("$" + player.getMoney());
    Text displayRound = new Text("Round: " + round);

    int numOfEnemiesKilled = 0;
    int damageTaken = 0;
    int totalMoneySpent = 0;

    Image path = new Image("/images/mapPixels1.png", 1280, 680, true, true);
    ImageCursor cursor1 = new ImageCursor(new Image("/images/cursor1.png"));
    ImageCursor cursor2 = new ImageCursor(new Image("/images/cursor2.png"));

    private ArrayList<ArcherTower> archerArray = new ArrayList<>(5);
    private ArrayList<Cannon> cannonArray = new ArrayList<>(5);
    private ArrayList<ClockTower> clockTowerArray = new ArrayList<>(5);
    private ArrayList<InfernoTower> infernoTowerArray = new ArrayList<>(5);
    private ArrayList<Mortar> mortarArray = new ArrayList<>(5);
    private ArrayList<WizardTower> wizardTowerArray = new ArrayList<>(5);

    private ArrayList<BarbarianEnemy> barbarianArray = new ArrayList<>(1);
    private ArrayList<GoblinEnemy> goblinArray = new ArrayList<>(1);
    private ArrayList<GolemEnemy> golemArray = new ArrayList<>(1);

    private ArrayList<Arrow> arrowArray = new ArrayList<>(5);
    private ArrayList<CannonBall> cannonBallArray = new ArrayList<>(5);
    private ArrayList<Fireball> fireballArray = new ArrayList<>(5);
    private ArrayList<PurpleFireball> purpleFireballArray = new ArrayList<>(5);
    private ArrayList<MortarShot> mortarShotArray = new ArrayList<>(5);


    public void start(Stage primaryStage) {
        map(primaryStage);
    }

    public void titleScreen(Stage primaryStage) {
        //Create Panes & Scenes
        root = new BorderPane();
        scene = new Scene(root, 1280, 680);
        primaryStage.setTitle("Clash of Clans Tower Defense");
        root.setBackground(titleScreenImage());
        primaryStage.setScene(scene);
        primaryStage.show();


        //Start and Quit Buttons
        HBox startQuit = new HBox();
        startQuit.setSpacing(100);
        Button start = new Button("Start");
        start.setScaleX(2.5);
        start.setScaleY(2.5);
        Button quit = new Button("Quit");
        quit.setScaleX(2.5);
        quit.setScaleY(2.5);
        startQuit.getChildren().addAll(start, quit);
        startQuit.setAlignment(Pos.CENTER);
        startQuit.setTranslateY(100);
        root.setCenter(startQuit);
        start.setOnAction(e -> {
            infoScreen(primaryStage);
        });
        quit.setOnAction(e -> {
            Platform.exit();
        });
    }

    public void infoScreen(Stage primaryStage) {
        root = new BorderPane();
        scene = new Scene(root, 1280, 680);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Set Background
        root.setBackground(titleScreenImage());

        //Container for Name Input and Difficulty Buttons
        VBox panel = new VBox();
        panel.setPadding(new Insets(10, 0, 20, 0));
        panel.setSpacing(20);
        panel.setAlignment(Pos.CENTER);
        panel.setTranslateY(100);

        //Name Input
        VBox nameBox = new VBox();
        Label nameLabel = new Label("Name: ");
        nameLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.WHITE);
        TextField name = new TextField();
        name.setMaxWidth(300);
        nameLabel.setLabelFor(name);
        nameBox.getChildren().addAll(nameLabel, name);
        nameBox.setAlignment(Pos.CENTER);
        //Difficulty Buttons
        HBox difficultyBox = new HBox();
        difficultyBox.setSpacing(40);
        difficultyBox.setScaleX(1.2);
        difficultyBox.setScaleY(1.2);
        Button easy = new Button("Easy");
        easy.setScaleX(1.5);
        easy.setScaleY(1.5);
        Button medium = new Button("Medium");
        medium.setScaleX(1.5);
        medium.setScaleY(1.5);
        Button hard = new Button("Hard");
        hard.setScaleX(1.5);
        hard.setScaleY(1.5);
        difficultyBox.getChildren().addAll(easy, medium, hard);
        difficultyBox.setAlignment(Pos.CENTER);
        //Display Difficulty Text
        Text displayDifficulty = new Text("Difficulty: ");
        displayDifficulty.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        displayDifficulty.setFill(Color.WHITE);
        //Display Name Text
        Text displayName = new Text("Name: ");
        displayName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        displayName.setFill(Color.WHITE);
        //Continue Button
        VBox container = new VBox();
        container.setSpacing(30);
        Button continueButton = new Button("Continue");
        continueButton.setScaleX(2);
        continueButton.setScaleY(2);
        VBox textContainer = new VBox();
        textContainer.setSpacing(10);
        textContainer.getChildren().addAll(displayName, displayDifficulty);
        container.getChildren().addAll(textContainer, continueButton);
        textContainer.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        //Create Scene
        panel.getChildren().addAll(nameBox, difficultyBox, container);
        root.setCenter(panel);

        //Set Difficulty
        easy.setOnAction(e -> {
            displayName.setText("Name: " + name.getText());
            displayDifficulty.setText("Difficulty: Easy");
            player.setDifficulty("Easy");
        });
        medium.setOnAction(e -> {
            displayName.setText("Name: " + name.getText());
            displayDifficulty.setText("Difficulty: Medium");
            player.setDifficulty("Medium");
        });
        hard.setOnAction(e -> {
            displayName.setText("Name: " + name.getText());
            displayDifficulty.setText("Difficulty: Hard");
            player.setDifficulty("Hard");
        });

        continueButton.setOnAction(e -> {
            if (name.getText().isEmpty() || name.getText().contains(" ")) {
                return;
            }
            if (player.getDifficulty().equals("Easy")) {
                player.setMonumentMultiplier(0);
                player.setTowerMultiplier(0);
                map(primaryStage);
            } else if (player.getDifficulty().equals("Medium")) {
                player.setMoney(player.getMoney() - player.getTowerMultiplier());
                monument.setHealth(monument.getHealth() - player.getMonumentMultiplier());
                map(primaryStage);
            } else if (player.getDifficulty().equals("Hard")) {
                player.setTowerMultiplier(player.getTowerMultiplier() * 2);
                player.setMonumentMultiplier(player.getMonumentMultiplier() * 2);
                player.setMoney(player.getMoney() - player.getTowerMultiplier());
                monument.setHealth(monument.getHealth() - player.getMonumentMultiplier());
                map(primaryStage);
            }
        });
    }

    public void buyTowerMenu(Stage primaryStage) {
        //UI and Buy Menu
        VBox uiBox = new VBox();
        uiBox.setSpacing(10);
        uiBox.setTranslateY(10);

        displayMoney.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        displayRound.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));

        Button startRoundButton = new Button("Start Round");
        startRoundButton.setScaleX(1.2);
        startRoundButton.setScaleY(1.2);
        startRoundButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        startRoundButton.setAlignment(Pos.CENTER);
        uiBox.getChildren().addAll(displayRound, displayMoney, startRoundButton);

        startRoundButton.setOnAction(e -> {
            startRound();
            if (goblinArray.size() == 0 && barbarianArray.size() == 0 && golemArray.size() == 0) {

            }
        });

        //Buy Tower Menu
        ArcherTower baseArcherTower = new ArcherTower();
        Cannon baseCannon = new Cannon();
        ClockTower baseClockTower = new ClockTower();
        InfernoTower baseInfernoTower = new InfernoTower();
        Mortar baseMortar = new Mortar();
        WizardTower baseWizardTower = new WizardTower();

        //Buttons for Buy Menu
        Button buyArcherTowerButton = new Button();
        Button buyCannonButton = new Button();
        Button buyClockTowerButton = new Button();
        Button buyInfernoTowerButton = new Button();
        Button buyMortarButton = new Button();
        Button buyWizardTowerButton = new Button();

        buyArcherTowerButton.setGraphic(new ImageView(new Image("/images/archertower.png")));
        buyArcherTowerButton.setText("$" + (baseArcherTower.getCost()
                + player.getTowerMultiplier()));
        buyCannonButton.setGraphic(new ImageView(new Image("/images/cannon.png")));
        buyCannonButton.setText("$" + (baseCannon.getCost() + player.getTowerMultiplier()));
        buyClockTowerButton.setGraphic(new ImageView(new Image("/images/clocktower.png")));
        buyClockTowerButton.setText("$" + (baseClockTower.getCost() + player.getTowerMultiplier()));
        buyInfernoTowerButton.setGraphic(new ImageView(new Image("/images/infernotower.png")));
        buyInfernoTowerButton.setText("$" + (baseInfernoTower.getCost()
                + player.getTowerMultiplier()));
        buyMortarButton.setGraphic(new ImageView(new Image("/images/mortar.png")));
        buyMortarButton.setText("$" + (baseMortar.getCost() + player.getTowerMultiplier()));
        buyWizardTowerButton.setGraphic(new ImageView(new Image("/images/wizardtower.png")));
        buyWizardTowerButton.setText("$" + (baseWizardTower.getCost()
                + player.getTowerMultiplier()));

        ListView<Object> list = new ListView<Object>();
        ObservableList<Object> items = FXCollections.observableArrayList(
                buyArcherTowerButton, buyCannonButton, buyMortarButton,
                buyWizardTowerButton, buyClockTowerButton, buyInfernoTowerButton);
        list.setItems(items);
        list.setMaxHeight(300);
        list.setMaxWidth(200);


        //Box for Top Row
        HBox row0 = new HBox();
        row0.getChildren().addAll(uiBox, list);
        row0.setSpacing(40);
        root.setRight(row0);


        buyArcherTowerButton.setOnAction(e -> {
            buyArcherTower(baseArcherTower);
        });
        buyCannonButton.setOnAction(e -> {
            buyCannon(baseCannon);
        });
        buyClockTowerButton.setOnAction(e -> {
            buyClockTower(baseClockTower);
        });
        buyInfernoTowerButton.setOnAction(e -> {
            buyInfernoTower(baseInfernoTower);
        });
        buyMortarButton.setOnAction(e -> {
            buyMortar(baseMortar);
        });
        buyWizardTowerButton.setOnAction(e -> {
            buyWizardTower(baseWizardTower);
        });
    }

    public void map(Stage primaryStage) {
        root = new BorderPane();
        root.setBackground(mapImage());
        scene = new Scene(root, 1280, 680);
        primaryStage.setScene(scene);
        primaryStage.show();

        monument.setHealth(monument.getHealth() - player.getMonumentMultiplier());
        displayMonumentHealth.setText("♥" + monument.getHealth());
        displayMonumentHealth.setFont(Font.font("Times New Roman", FontWeight.BOLD, 17));
        monument.getImageView().setX(1170);
        monument.getImageView().setY(510);
        displayMonumentHealth.setX(1195);
        displayMonumentHealth.setY(625);

        root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                int mouseX = ((int) event.getX());
                int mouseY = ((int) event.getY());
                PixelReader placePixels = path.getPixelReader();
                try {
                    if ((1280 - mouseX) < 1280 && (680 - mouseY) < 680) {
                        if (!placePixels.getColor(mouseX, mouseY).equals(Color.BLACK)) {
                            if (!placePixels.getColor(mouseX + 5, mouseY + 5).equals(Color.BLACK)) {
                                if (!placePixels.getColor(mouseX, mouseY + 5).equals(Color.BLACK)) {
                                    if (!placePixels.getColor(mouseX
                                            + 5, mouseY).equals(Color.BLACK)) {
                                        root.setCursor(cursor1);
                                    }
                                }
                            }
                        } else {
                            root.setCursor(cursor2);
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Out of bounds");
                }
            }
        });
        root.getChildren().addAll(monument.getImageView(), displayMonumentHealth);

        buyTowerMenu(primaryStage);
        gameLoop(primaryStage);
    }

    public void gameLoop(Stage primaryStage) {
        AnimationTimer loop = new AnimationTimer() {
            int goblinTimer = 45;
            int barbTimer = 75;
            int golemTimer = 105;

            int archerTimer = 120;
            int cannonTimer = 180;
            int clockTowerTimer = 360;
            int infernoTowerTimer = 60;
            int mortarTimer = 240;
            int wizardTowerTimer = 75;
            @Override
            public void handle(long l) {
                goblinArray.forEach(GoblinEnemy::move);
                goblinArray.forEach(GoblinEnemy::updateUI);
                barbarianArray.forEach(BarbarianEnemy::move);
                barbarianArray.forEach(BarbarianEnemy::updateUI);
                golemArray.forEach(GolemEnemy::move);
                golemArray.forEach(GolemEnemy::updateUI);

                arrowArray.forEach(Arrow::move);
                arrowArray.forEach(Arrow::updateUI);
                cannonBallArray.forEach(CannonBall::move);
                cannonBallArray.forEach(CannonBall::updateUI);
                fireballArray.forEach(Fireball::move);
                fireballArray.forEach(Fireball::updateUI);
                purpleFireballArray.forEach(PurpleFireball::move);
                purpleFireballArray.forEach(PurpleFireball::updateUI);
                mortarShotArray.forEach(MortarShot::move);
                mortarShotArray.forEach(MortarShot::updateUI);

                for (int i = 0; i < archerArray.size(); i++) {
                    for (int j = 0; j < goblinArray.size(); j++) {
                        if (Math.abs(archerArray.get(i).getX() - goblinArray.get(j).getX()) < 250
                                && Math.abs(archerArray.get(i).getY() - goblinArray.get(j).getY()) < 250) {
                            if (archerTimer % 120 == 0) {
                                archerGoblinAttack(i, j);
                            }
                            archerTimer++;
                        }
                    }
                    for (int j = 0; j < barbarianArray.size(); j++) {
                        if (Math.abs(archerArray.get(i).getX() - barbarianArray.get(j).getX()) < 250
                                && Math.abs(archerArray.get(i).getY() - barbarianArray.get(j).getY()) < 250) {
                            if (archerTimer % 120 == 0) {
                                archerBarbAttack(i, j);
                            }
                            archerTimer++;
                        }
                    }
                    for (int j = 0; j < golemArray.size(); j++) {
                        if (Math.abs(archerArray.get(i).getX() - golemArray.get(j).getX()) < 250
                                && Math.abs(archerArray.get(i).getY() - golemArray.get(j).getY()) < 250) {
                            if (archerTimer % 120 == 0) {
                                archerGolemAttack(i, j);
                            }
                            archerTimer++;
                        }
                    }
                }
                for (int i = 0; i < cannonArray.size(); i++) {
                    for (int j = 0; j < goblinArray.size(); j++) {
                        if (Math.abs(cannonArray.get(i).getX() - goblinArray.get(j).getX()) < 250
                                && Math.abs(cannonArray.get(i).getY() - goblinArray.get(j).getY()) < 250) {
                            if (cannonTimer % 180 == 0) {
                                cannonGoblinAttack(i, j);
                            }
                            cannonTimer++;
                        }
                    }
                    for (int j = 0; j < barbarianArray.size(); j++) {
                        if (Math.abs(cannonArray.get(i).getX() - barbarianArray.get(j).getX()) < 250
                                && Math.abs(cannonArray.get(i).getY() - barbarianArray.get(j).getY()) < 250) {
                            if (cannonTimer % 180 == 0) {
                                cannonBarbAttack(i, j);
                            }
                            cannonTimer++;
                        }
                    }
                    for (int j = 0; j < golemArray.size(); j++) {
                        if (Math.abs(cannonArray.get(i).getX() - golemArray.get(j).getX()) < 250
                                && Math.abs(cannonArray.get(i).getY() - golemArray.get(j).getY()) < 250) {
                            if (cannonTimer % 180 == 0) {
                                cannonGolemAttack(i, j);
                            }
                            cannonTimer++;
                        }
                    }
                }
                for (int i = 0; i < clockTowerArray.size(); i++) {

                }
                for (int i = 0; i < infernoTowerArray.size(); i++) {
                    for (int j = 0; j < goblinArray.size(); j++) {
                        if (Math.abs(infernoTowerArray.get(i).getX() - goblinArray.get(j).getX()) < 250
                                && Math.abs(infernoTowerArray.get(i).getY() - goblinArray.get(j).getY()) < 250) {
                            if (infernoTowerTimer % 60 == 0) {
                                infernoTowerGoblinAttack(i, j);
                            }
                            infernoTowerTimer++;
                        }
                    }
                    for (int j = 0; j < barbarianArray.size(); j++) {
                        if (Math.abs(infernoTowerArray.get(i).getX() - barbarianArray.get(j).getX()) < 250
                                && Math.abs(infernoTowerArray.get(i).getY() - barbarianArray.get(j).getY()) < 250) {
                            if (infernoTowerTimer % 60 == 0) {
                                infernoTowerBarbAttack(i, j);
                            }
                            infernoTowerTimer++;
                        }
                    }
                    for (int j = 0; j < golemArray.size(); j++) {
                        if (Math.abs(infernoTowerArray.get(i).getX() - golemArray.get(j).getX()) < 250
                                && Math.abs(infernoTowerArray.get(i).getY() - golemArray.get(j).getY()) < 250) {
                            if (infernoTowerTimer % 60 == 0) {
                                infernoTowerGolemAttack(i, j);
                            }
                            infernoTowerTimer++;
                        }
                    }
                }
                for (int i = 0; i < mortarArray.size(); i++) {
                    for (int j = 0; j < goblinArray.size(); j++) {
                        if (Math.abs(mortarArray.get(i).getX() - goblinArray.get(j).getX()) < 250
                                && Math.abs(mortarArray.get(i).getY() - goblinArray.get(j).getY()) < 250) {
                            if (mortarTimer % 240 == 0) {
                                mortarGoblinAttack(i, j);
                            }
                            mortarTimer++;
                        }
                    }
                    for (int j = 0; j < barbarianArray.size(); j++) {
                        if (Math.abs(mortarArray.get(i).getX() - barbarianArray.get(j).getX()) < 250
                                && Math.abs(mortarArray.get(i).getY() - barbarianArray.get(j).getY()) < 250) {
                            if (mortarTimer % 240 == 0) {
                                mortarBarbAttack(i, j);
                            }
                            mortarTimer++;
                        }
                    }
                    for (int j = 0; j < golemArray.size(); j++) {
                        if (Math.abs(mortarArray.get(i).getX() - golemArray.get(j).getX()) < 250
                                && Math.abs(mortarArray.get(i).getY() - golemArray.get(j).getY()) < 250) {
                            if (mortarTimer % 240 == 0) {
                                mortarGolemAttack(i, j);
                            }
                            mortarTimer++;
                        }
                    }
                }
                for (int i = 0; i < wizardTowerArray.size(); i++) {
                    for (int j = 0; j < goblinArray.size(); j++) {
                        if (Math.abs(wizardTowerArray.get(i).getX() - goblinArray.get(j).getX()) < 250
                                && Math.abs(wizardTowerArray.get(i).getY() - goblinArray.get(j).getY()) < 250) {
                            if (wizardTowerTimer % 75 == 0) {
                                wizardTowerGoblinAttack(i, j);
                            }
                            wizardTowerTimer++;
                        }
                    }
                    for (int j = 0; j < barbarianArray.size(); j++) {
                        if (Math.abs(wizardTowerArray.get(i).getX() - barbarianArray.get(j).getX()) < 250
                                && Math.abs(wizardTowerArray.get(i).getY() - barbarianArray.get(j).getY()) < 250) {
                            if (wizardTowerTimer % 75 == 0) {
                                wizardTowerBarbAttack(i, j);
                            }
                            wizardTowerTimer++;
                        }
                    }
                    for (int j = 0; j < golemArray.size(); j++) {
                        if (Math.abs(wizardTowerArray.get(i).getX() - golemArray.get(j).getX()) < 250
                                && Math.abs(wizardTowerArray.get(i).getY() - golemArray.get(j).getY()) < 250) {
                            if (wizardTowerTimer % 75 == 0) {
                                wizardTowerGolemAttack(i, j);
                            }
                            wizardTowerTimer++;
                        }
                    }
                }

                for (GoblinEnemy goblin : goblinArray) {
                    if (goblin.getX() >= 1110) {
                        if (goblinTimer % goblin.getAttackSpeed() == 0) {
                            monument.setHealth(monument.getHealth() - goblin.getAttack());
                            displayMonumentHealth.setText("♥" + monument.getHealth());
                        }
                        goblinTimer++;
                    }
                }
                for (BarbarianEnemy barb : barbarianArray) {
                    if (barb.getX() >= 1110) {
                        if (barbTimer % barb.getAttackSpeed() == 0) {
                            monument.setHealth(monument.getHealth() - barb.getAttack());
                            displayMonumentHealth.setText("♥" + monument.getHealth());
                        }
                        barbTimer++;
                    }
                }
                for (GolemEnemy golem : golemArray) {
                    if (golem.getX() >= 1110) {
                        if (golemTimer % golem.getAttackSpeed() == 0) {
                            monument.setHealth(monument.getHealth() - golem.getAttack());
                            displayMonumentHealth.setText("♥" + monument.getHealth());
                        }
                        golemTimer++;
                    }
                }

                if (monument.getHealth() <= 0) {
                    this.stop();
                    endGame(primaryStage);
                }
            }
        };
        loop.start();
    }

    public void startRound() {
        round++;
        displayRound.setText("Round: " + round);
        if (round < 5) {
            GoblinEnemy goblin = new GoblinEnemy();
            BarbarianEnemy barb = new BarbarianEnemy();
            goblinArray.add(goblin);
            barbarianArray.add(barb);
            root.getChildren().addAll(goblin.getImageView(), barb.getImageView());
        } else if (round >= 5 && round < 15) {
            GoblinEnemy goblin = new GoblinEnemy();
            BarbarianEnemy barb = new BarbarianEnemy();
            GolemEnemy golem = new GolemEnemy();
            goblinArray.add(goblin);
            barbarianArray.add(barb);
            golemArray.add(golem);
            root.getChildren().addAll(goblin.getImageView(), barb.getImageView(), golem.getImageView());
        } else {
            //Boss
        }
    }

    public void archerGoblinAttack(int i, int j) {
        if (goblinArray.get(j).getHealth() > 0) {
            Arrow arrow = new Arrow();
            arrow.setX(archerArray.get(i).getX());
            arrow.setY(archerArray.get(i).getY());
            arrow.setDx((goblinArray.get(j).getX() - archerArray.get(i).getX()) / 10);
            arrow.setDy((goblinArray.get(j).getY() - archerArray.get(i).getY()) / 10);

            arrowArray.add(arrow);
            root.getChildren().addAll(arrow.getImageView());

            goblinArray.get(j).setHealth(goblinArray.get(j).getHealth() - archerArray.get(i).getAttack());
        } else {
            root.getChildren().remove(goblinArray.get(j).getImageView());
            root.getChildren().remove(goblinArray.get(j).getImageView());
            goblinArray.remove(j);
            player.setMoney(player.getMoney() + 25);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void archerBarbAttack(int i, int j) {
        if (barbarianArray.get(j).getHealth() > 0) {
            Arrow arrow = new Arrow();
            arrow.setX(archerArray.get(i).getX());
            arrow.setY(archerArray.get(i).getY());
            arrow.setDx((barbarianArray.get(j).getX() - archerArray.get(i).getX()) / 10);
            arrow.setDy((barbarianArray.get(j).getY() - archerArray.get(i).getY()) / 10);

            arrowArray.add(arrow);
            root.getChildren().addAll(arrow.getImageView());

            barbarianArray.get(j).setHealth(barbarianArray.get(j).getHealth() - archerArray.get(i).getAttack());
        } else {
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            barbarianArray.remove(j);
            player.setMoney(player.getMoney() + 50);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void archerGolemAttack(int i, int j) {
        if (golemArray.get(j).getHealth() > 0) {
            Arrow arrow = new Arrow();
            arrow.setX(archerArray.get(i).getX());
            arrow.setY(archerArray.get(i).getY());
            arrow.setDx((golemArray.get(j).getX() - archerArray.get(i).getX()) / 10);
            arrow.setDy((golemArray.get(j).getY() - archerArray.get(i).getY()) / 10);

            arrowArray.add(arrow);
            root.getChildren().addAll(arrow.getImageView());

            golemArray.get(j).setHealth(golemArray.get(j).getHealth() - archerArray.get(i).getAttack());
        } else {
            root.getChildren().remove(golemArray.get(j).getImageView());
            root.getChildren().remove(golemArray.get(j).getImageView());
            golemArray.remove(j);
            player.setMoney(player.getMoney() + 100);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }

    public void cannonGoblinAttack(int i, int j) {
        if (goblinArray.get(j).getHealth() > 0) {
            CannonBall cannonBall = new CannonBall();
            cannonBall.setX(cannonArray.get(i).getX());
            cannonBall.setY(cannonArray.get(i).getY());
            cannonBall.setDx((goblinArray.get(j).getX() - cannonArray.get(i).getX()) / 10);
            cannonBall.setDy((goblinArray.get(j).getY() - cannonArray.get(i).getY()) / 10);

            cannonBallArray.add(cannonBall);
            root.getChildren().addAll(cannonBall.getImageView());

            goblinArray.get(j).setHealth(goblinArray.get(j).getHealth() - cannonArray.get(i).getAttack());
        } else {
            root.getChildren().remove(goblinArray.get(j).getImageView());
            root.getChildren().remove(goblinArray.get(j).getImageView());
            goblinArray.remove(j);
            player.setMoney(player.getMoney() + 25);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void cannonBarbAttack(int i, int j) {
        if (barbarianArray.get(j).getHealth() > 0) {
            CannonBall cannonBall = new CannonBall();
            cannonBall.setX(cannonArray.get(i).getX());
            cannonBall.setY(cannonArray.get(i).getY());
            cannonBall.setDx((barbarianArray.get(j).getX() - cannonArray.get(i).getX()) / 10);
            cannonBall.setDy((barbarianArray.get(j).getY() - cannonArray.get(i).getY()) / 10);

            cannonBallArray.add(cannonBall);
            root.getChildren().addAll(cannonBall.getImageView());

            barbarianArray.get(j).setHealth(barbarianArray.get(j).getHealth() - cannonArray.get(i).getAttack());
        } else {
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            barbarianArray.remove(j);
            player.setMoney(player.getMoney() + 50);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void cannonGolemAttack(int i, int j) {
        if (golemArray.get(j).getHealth() > 0) {
            CannonBall cannonBall = new CannonBall();
            cannonBall.setX(cannonArray.get(i).getX());
            cannonBall.setY(cannonArray.get(i).getY());
            cannonBall.setDx((golemArray.get(j).getX() - cannonArray.get(i).getX()) / 10);
            cannonBall.setDy((golemArray.get(j).getY() - cannonArray.get(i).getY()) / 10);

            cannonBallArray.add(cannonBall);
            root.getChildren().addAll(cannonBall.getImageView());

            golemArray.get(j).setHealth(golemArray.get(j).getHealth() - cannonArray.get(i).getAttack());
        } else {
            root.getChildren().remove(golemArray.get(j).getImageView());
            root.getChildren().remove(golemArray.get(j).getImageView());
            golemArray.remove(j);
            player.setMoney(player.getMoney() + 100);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }

    public void infernoTowerGoblinAttack(int i, int j) {
        if (goblinArray.get(j).getHealth() > 0) {
            Fireball fireball = new Fireball();
            fireball.setX(infernoTowerArray.get(i).getX());
            fireball.setY(infernoTowerArray.get(i).getY());
            fireball.setDx((goblinArray.get(j).getX() - infernoTowerArray.get(i).getX()) / 10);
            fireball.setDy((goblinArray.get(j).getY() - infernoTowerArray.get(i).getY()) / 10);

            fireballArray.add(fireball);
            root.getChildren().addAll(fireball.getImageView());

            goblinArray.get(j).setHealth(goblinArray.get(j).getHealth() - infernoTowerArray.get(i).getAttack());
        } else {
            root.getChildren().remove(goblinArray.get(j).getImageView());
            root.getChildren().remove(goblinArray.get(j).getImageView());
            goblinArray.remove(j);
            player.setMoney(player.getMoney() + 25);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void infernoTowerBarbAttack(int i, int j) {
        if (barbarianArray.get(j).getHealth() > 0) {
            Fireball fireball = new Fireball();
            fireball.setX(infernoTowerArray.get(i).getX());
            fireball.setY(infernoTowerArray.get(i).getY());
            fireball.setDx((barbarianArray.get(j).getX() - infernoTowerArray.get(i).getX()) / 10);
            fireball.setDy((barbarianArray.get(j).getY() - infernoTowerArray.get(i).getY()) / 10);

            fireballArray.add(fireball);
            root.getChildren().addAll(fireball.getImageView());

            barbarianArray.get(j).setHealth(barbarianArray.get(j).getHealth() - infernoTowerArray.get(i).getAttack());
        } else {
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            barbarianArray.remove(j);
            player.setMoney(player.getMoney() + 50);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void infernoTowerGolemAttack(int i, int j) {
        if (golemArray.get(j).getHealth() > 0) {
            Fireball fireball = new Fireball();
            fireball.setX(infernoTowerArray.get(i).getX());
            fireball.setY(infernoTowerArray.get(i).getY());
            fireball.setDx((golemArray.get(j).getX() - infernoTowerArray.get(i).getX()) / 10);
            fireball.setDy((golemArray.get(j).getY() - infernoTowerArray.get(i).getY()) / 10);

            fireballArray.add(fireball);
            root.getChildren().addAll(fireball.getImageView());

            golemArray.get(j).setHealth(golemArray.get(j).getHealth() - infernoTowerArray.get(i).getAttack());
        } else {
            root.getChildren().remove(golemArray.get(j).getImageView());
            root.getChildren().remove(golemArray.get(j).getImageView());
            golemArray.remove(j);
            player.setMoney(player.getMoney() + 100);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }

    public void mortarGoblinAttack(int i, int j) {
        if (goblinArray.get(j).getHealth() > 0) {
            MortarShot mortarShot = new MortarShot();
            mortarShot.setX(mortarArray.get(i).getX());
            mortarShot.setY(mortarArray.get(i).getY());
            mortarShot.setDx((goblinArray.get(j).getX() - mortarArray.get(i).getX()) / 10);
            mortarShot.setDy((goblinArray.get(j).getY() - mortarArray.get(i).getY()) / 10);

            mortarShotArray.add(mortarShot);
            root.getChildren().addAll(mortarShot.getImageView());

            goblinArray.get(j).setHealth(goblinArray.get(j).getHealth() - mortarArray.get(i).getAttack());
        } else {
            root.getChildren().remove(goblinArray.get(j).getImageView());
            root.getChildren().remove(goblinArray.get(j).getImageView());
            goblinArray.remove(j);
            player.setMoney(player.getMoney() + 25);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void mortarBarbAttack(int i, int j) {
        if (barbarianArray.get(j).getHealth() > 0) {
            MortarShot mortarShot = new MortarShot();
            mortarShot.setX(mortarArray.get(i).getX());
            mortarShot.setY(mortarArray.get(i).getY());
            mortarShot.setDx((barbarianArray.get(j).getX() - mortarArray.get(i).getX()) / 10);
            mortarShot.setDy((barbarianArray.get(j).getY() - mortarArray.get(i).getY()) / 10);

            mortarShotArray.add(mortarShot);
            root.getChildren().addAll(mortarShot.getImageView());

            barbarianArray.get(j).setHealth(barbarianArray.get(j).getHealth() - mortarArray.get(i).getAttack());
        } else {
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            barbarianArray.remove(j);
            player.setMoney(player.getMoney() + 50);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void mortarGolemAttack(int i, int j) {
        if (golemArray.get(j).getHealth() > 0) {
            MortarShot mortarShot = new MortarShot();
            mortarShot.setX(mortarArray.get(i).getX());
            mortarShot.setY(mortarArray.get(i).getY());
            mortarShot.setDx((golemArray.get(j).getX() - mortarArray.get(i).getX()) / 10);
            mortarShot.setDy((golemArray.get(j).getY() - mortarArray.get(i).getY()) / 10);

            mortarShotArray.add(mortarShot);
            root.getChildren().addAll(mortarShot.getImageView());

            golemArray.get(j).setHealth(golemArray.get(j).getHealth() - mortarArray.get(i).getAttack());
        } else {
            root.getChildren().remove(golemArray.get(j).getImageView());
            root.getChildren().remove(golemArray.get(j).getImageView());
            golemArray.remove(j);
            player.setMoney(player.getMoney() + 100);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }

    public void wizardTowerGoblinAttack(int i, int j) {
        if (goblinArray.get(j).getHealth() > 0) {
            PurpleFireball purpleFireball = new PurpleFireball();
            purpleFireball.setX(wizardTowerArray.get(i).getX());
            purpleFireball.setY(wizardTowerArray.get(i).getY());
            purpleFireball.setDx((goblinArray.get(j).getX() - wizardTowerArray.get(i).getX()) / 30);
            purpleFireball.setDy((goblinArray.get(j).getY() - wizardTowerArray.get(i).getY()) / 30);

            purpleFireballArray.add(purpleFireball);
            root.getChildren().addAll(purpleFireball.getImageView());

            goblinArray.get(j).setHealth(goblinArray.get(j).getHealth() - wizardTowerArray.get(i).getAttack());
        } else {
            root.getChildren().remove(goblinArray.get(j).getImageView());
            root.getChildren().remove(goblinArray.get(j).getImageView());
            goblinArray.remove(j);
            player.setMoney(player.getMoney() + 25);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void wizardTowerBarbAttack(int i, int j) {
        if (barbarianArray.get(j).getHealth() > 0) {
            PurpleFireball purpleFireball = new PurpleFireball();
            purpleFireball.setX(wizardTowerArray.get(i).getX());
            purpleFireball.setY(wizardTowerArray.get(i).getY());
            purpleFireball.setDx((barbarianArray.get(j).getX() - wizardTowerArray.get(i).getX()) / 30);
            purpleFireball.setDy((barbarianArray.get(j).getY() - wizardTowerArray.get(i).getY()) / 30);

            purpleFireballArray.add(purpleFireball);
            root.getChildren().addAll(purpleFireball.getImageView());

            barbarianArray.get(j).setHealth(barbarianArray.get(j).getHealth() - wizardTowerArray.get(i).getAttack());
        } else {
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            root.getChildren().remove(barbarianArray.get(j).getImageView());
            barbarianArray.remove(j);
            player.setMoney(player.getMoney() + 50);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }
    public void wizardTowerGolemAttack(int i, int j) {
        if (golemArray.get(j).getHealth() > 0) {
            PurpleFireball purpleFireball = new PurpleFireball();
            purpleFireball.setX(wizardTowerArray.get(i).getX());
            purpleFireball.setY(wizardTowerArray.get(i).getY());
            purpleFireball.setDx((golemArray.get(j).getX() - wizardTowerArray.get(i).getX()) / 30);
            purpleFireball.setDy((golemArray.get(j).getY() - wizardTowerArray.get(i).getY()) / 30);

            purpleFireballArray.add(purpleFireball);
            root.getChildren().addAll(purpleFireball.getImageView());

            golemArray.get(j).setHealth(golemArray.get(j).getHealth() - wizardTowerArray.get(i).getAttack());
        } else {
            root.getChildren().remove(golemArray.get(j).getImageView());
            root.getChildren().remove(golemArray.get(j).getImageView());
            golemArray.remove(j);
            player.setMoney(player.getMoney() + 100);
            displayMoney.setText("$" + player.getMoney());
            numOfEnemiesKilled++;
        }
    }

    public void buyArcherTower(ArcherTower baseArcherTower) {
        if (player.getMoney() >= (baseArcherTower.getCost() + player.getTowerMultiplier())) {
            root.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    int mouseX = ((int) event.getX());
                    int mouseY = ((int) event.getY());
                    if (player.getMoney()
                            >= (baseArcherTower.getCost() + player.getTowerMultiplier())
                            && root.getCursor().equals(cursor2)) {
                        ArcherTower archerTower = new ArcherTower();
                        archerTower.getImageView().setX(mouseX - 30);
                        archerTower.getImageView().setY(mouseY - 45);
                        archerTower.setX(mouseX - 30);
                        archerTower.setY(mouseY - 45);
                        archerArray.add(archerTower);
                        root.getChildren().addAll(archerTower.getImageView());
                        player.setMoney(player.getMoney() - (baseArcherTower.getCost() + player.getTowerMultiplier()));
                        displayMoney.setText("$" + player.getMoney());
                        root.setOnMouseClicked(null);
                    }
                }
            });
        }
    }
    public void buyCannon(Cannon baseCannon) {
        if (player.getMoney() >= (baseCannon.getCost() + player.getTowerMultiplier())) {
            root.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    int mouseX = ((int) event.getX());
                    int mouseY = ((int) event.getY());
                    if (player.getMoney() >= (baseCannon.getCost() + player.getTowerMultiplier())
                            && root.getCursor().equals(cursor2)) {
                        Cannon cannon = new Cannon();
                        cannon.getImageView().setX(mouseX - 37);
                        cannon.getImageView().setY(mouseY - 32);
                        cannon.setX(mouseX - 37);
                        cannon.setY(mouseY - 32);
                        cannonArray.add(cannon);
                        root.getChildren().addAll(cannon.getImageView());
                        player.setMoney(player.getMoney() - (baseCannon.getCost() + player.getTowerMultiplier()));
                        displayMoney.setText("$" + player.getMoney());
                        root.setOnMouseClicked(null);
                    }
                }
            });
        }
    }
    public void buyClockTower(ClockTower baseClockTower) {
        if (player.getMoney() >= (baseClockTower.getCost() + player.getTowerMultiplier())) {
            root.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    int mouseX = ((int) event.getX());
                    int mouseY = ((int) event.getY());
                    if (player.getMoney()
                            >= (baseClockTower.getCost() + player.getTowerMultiplier())
                            && root.getCursor().equals(cursor2)) {
                        ClockTower clockTower = new ClockTower();
                        clockTower.getImageView().setX(mouseX - 37);
                        clockTower.getImageView().setY(mouseY - 46);
                        clockTower.setX(mouseX - 37);
                        clockTower.setY(mouseY - 46);
                        clockTowerArray.add(clockTower);
                        root.getChildren().addAll(clockTower.getImageView());
                        player.setMoney(player.getMoney() - (baseClockTower.getCost() + player.getTowerMultiplier()));
                        displayMoney.setText("$" + player.getMoney());
                        root.setOnMouseClicked(null);
                    }
                }
            });
        }
    }
    public void buyInfernoTower(InfernoTower baseInfernoTower) {
        if (player.getMoney() >= (baseInfernoTower.getCost() + player.getTowerMultiplier())) {
            root.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    int mouseX = ((int) event.getX());
                    int mouseY = ((int) event.getY());
                    if (player.getMoney() >= (baseInfernoTower.getCost()
                            + player.getTowerMultiplier())
                            && root.getCursor().equals(cursor2)) {
                        InfernoTower infernoTower = new InfernoTower();
                        infernoTower.getImageView().setX(mouseX - 26);
                        infernoTower.getImageView().setY(mouseY - 46);
                        infernoTower.setX(mouseX - 26);
                        infernoTower.setY(mouseY - 46);
                        infernoTowerArray.add(infernoTower);
                        root.getChildren().addAll(infernoTower.getImageView());
                        player.setMoney(player.getMoney() - (baseInfernoTower.getCost() + player.getTowerMultiplier()));
                        displayMoney.setText("$" + player.getMoney());
                        root.setOnMouseClicked(null);
                    }
                }
            });
        }
    }
    public void buyMortar(Mortar baseMortar) {
        if (player.getMoney() >= (baseMortar.getCost() + player.getTowerMultiplier())) {
            root.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    int mouseX = ((int) event.getX());
                    int mouseY = ((int) event.getY());
                    if (player.getMoney() >= (baseMortar.getCost() + player.getTowerMultiplier())
                            && root.getCursor().equals(cursor2)) {
                        Mortar mortar = new Mortar();
                        mortar.getImageView().setX(mouseX - 30);
                        mortar.getImageView().setY(mouseY - 32);
                        mortar.setX(mouseX - 30);
                        mortar.setY(mouseY - 32);
                        mortarArray.add(mortar);
                        root.getChildren().addAll(mortar.getImageView());
                        player.setMoney(player.getMoney() - (baseMortar.getCost() + player.getTowerMultiplier()));
                        displayMoney.setText("$" + player.getMoney());
                        root.setOnMouseClicked(null);
                    }
                }
            });
        }
    }
    public void buyWizardTower(WizardTower baseWizardTower) {
        if (player.getMoney() >= (baseWizardTower.getCost() + player.getTowerMultiplier())) {
            root.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                    int mouseX = ((int) event.getX());
                    int mouseY = ((int) event.getY());
                    if (player.getMoney()
                            >= (baseWizardTower.getCost() + player.getTowerMultiplier())
                            && root.getCursor().equals(cursor2)) {
                        WizardTower wizardTower = new WizardTower();
                        wizardTower.getImageView().setX(mouseX - 39);
                        wizardTower.getImageView().setY(mouseY - 46);
                        wizardTower.setX(mouseX - 39);
                        wizardTower.setY(mouseY - 46);
                        wizardTowerArray.add(wizardTower);
                        root.getChildren().addAll(wizardTower.getImageView());
                        player.setMoney(player.getMoney() - (baseWizardTower.getCost() + player.getTowerMultiplier()));
                        displayMoney.setText("$" + player.getMoney());
                        root.setOnMouseClicked(null);
                    }
                }
            });
        }
    }

    public void endGame(Stage primaryStage) {
        root = new BorderPane();
        scene = new Scene(root, 1280, 680);
        root.setBackground(titleScreenImage());
        primaryStage.setScene(scene);
        primaryStage.show();

        VBox endBox = new VBox();
        VBox winnerBox = new VBox();
        HBox restartQuit = new HBox();
        endBox.setSpacing(30);
        winnerBox.setSpacing(30);
        winnerBox.setAlignment(Pos.CENTER);
        endBox.setAlignment(Pos.CENTER);
        restartQuit.setSpacing(100);
        restartQuit.setAlignment(Pos.CENTER);
        endBox.setTranslateY(50);

        /*
        Text winner = new Text("Winner!");
        winner.setFont(Font.font("Times New Roman", FontWeight.BOLD, 90));
        winner.setFill(Color.RED);
        Text numEnemiesKill = new Text("Number of Enemies Killed: " + numOfEnemiesKilled);
        numEnemiesKill.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        numEnemiesKill.setFill(Color.RED);
        Text damage = new Text("Damage Taken: " + (damageTaken - monument.getHealth()));
        damage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        damage.setFill(Color.RED);
        Text money = new Text("Money Spent: " + totalMoneySpent);
        money.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        money.setFill(Color.RED);
         */

        Text gameOver = new Text("GAME OVER.");
        gameOver.setFont(Font.font("Times New Roman", FontWeight.BOLD, 90));
        gameOver.setFill(Color.RED);

        Button restart = new Button("Restart");
        restart.setScaleX(2.5);
        restart.setScaleY(2.5);

        Button quit = new Button("Quit");
        quit.setScaleX(2.5);
        quit.setScaleY(2.5);

        restartQuit.getChildren().addAll(restart, quit);
        endBox.getChildren().addAll(gameOver, restartQuit);
        //winnerBox.getChildren().addAll(winner, numEnemiesKill, damage, money, restartQuit);
        if (monument.getHealth() > 0) {
            root.setCenter(winnerBox);
        } else {
            root.setCenter(endBox);
        }
        restart.setOnAction(e -> {
            infoScreen(primaryStage);
        });
        quit.setOnAction(e -> {
            Platform.exit();
        });
    }

    public Background titleScreenImage() {
        Image img = new Image("/images/titlescreen.jpeg");
        BackgroundImage backgroundImage = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }
    public Background mapImage() {
        Image imgMap = new Image("/images/map1.png");
        BackgroundImage backgroundImageMap = new BackgroundImage(imgMap,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        return new Background(backgroundImageMap);
    }

    public static void main(String[] args) {
        launch(args);
    }
}