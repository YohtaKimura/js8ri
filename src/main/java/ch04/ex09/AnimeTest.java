package ch04.ex09;

import java.util.Random;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimeTest extends Application {
   private static final int MAX_NODES=30;
   private Random random =
      new Random(System.currentTimeMillis());

   private Path createOrbitalPath(double centerX,
         double centerY,double radiusX, double radiusY,
         double rotate) {
      ArcTo arcTo = new ArcTo();
      arcTo.setX(centerX - radiusX + 1);
      arcTo.setY(centerY - radiusY);
      arcTo.setSweepFlag(false);
      arcTo.setLargeArcFlag(true);
      arcTo.setRadiusX(radiusX);
      arcTo.setRadiusY(radiusY);
      arcTo.setXAxisRotation(rotate);
      Path path = new Path();
      path.getElements()
         .add(new MoveTo(centerX - radiusX, centerY -
         radiusY));
      path.getElements().add(arcTo);
      path.getElements().add(new ClosePath());
      path.setVisible(false);
      return path;
   }

   private PathTransition createPathTransition(double second,
         Path path, Node node) {
      PathTransition t = new PathTransition();
      t.setDuration(Duration.seconds(second));
      t.setPath(path);
      t.setNode(node);
      t.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
      t.setCycleCount(Timeline.INDEFINITE);
      t.setAutoReverse(false);
      return t;
   }

   private Node createEllipticShape(double radius) {
      Circle circle = new Circle(radius);
      circle.setFill(Color.rgb(random.nextInt(255),
         random.nextInt(255), random.nextInt(255),.55));
      return circle;
   }

   private void createStage(Stage stage){
      Group root = new Group();
      stage.setResizable(true);
      Scene scene=new Scene(root, 640, 480);
      scene.setFill(Color.BLACK);
      stage.setScene(scene);
      root.getTransforms().add(
         Transform.translate(scene.getWidth()/2,
         scene.getHeight()/2));
      int side=scene.getWidth()<scene.getHeight()?
         (int)scene.getWidth():(int)scene.getHeight();

      for(int i=0;i<MAX_NODES;i++){
         Node n=createEllipticShape(random.nextInt(20));
         root.getChildren().add(n);
         int unit=random.nextInt(side);
         Path p=createOrbitalPath(unit, 0, unit-100,
            unit-100, 0);
         root.getChildren().add(p);
         createPathTransition(random.nextInt(10), p,
            n).play();
      }

   }

   @Override
   public void start(Stage primaryStage) {

      createStage(primaryStage);
      primaryStage.show();
   }

   public static void main(String[] args) {
      Application.launch(args);
   }
}