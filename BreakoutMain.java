import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

public class BreakoutMain extends Application {
  private BreakoutThread breakoutThread;

  public static void main(String args[]){
    launch(args);
  }

  @Override
  public void start(Stage stage){
    stage.setTitle("BREAKOUT");
    Pane pane = new Pane();
    Scene scene = new Scene(pane);
    stage.setScene(scene);

    Canvas canvas = new Canvas(640, 480);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    pane.getChildren().add(canvas);

    breakoutThread = new BreakoutThread(gc);
    breakoutThread.start();

    stage.show();
  }
}

class BreakoutThread extends AnimationTimer{
  private GraphicsContext gc;

  /*変数宣言*/
  private int ball_x;
  private int ball_y;
  private int x_speed;
  private int y_speed;

  BreakoutThread(GraphicsContext gc){
    this.gc = gc;

    /*コンストラクタ*/
    /*最初の一回だけ実行 -> 初期化を書く*/

    ball_x = 0;
    ball_y = 0;
    x_speed = 5;
    y_speed = 5;
  }

  @Override
  public void handle(long time){
    gc.clearRect(0, 0, 640, 480);

    /*ゲームループ : メインプログラム*/

    gc.setFill(Color.BLACK);                    //黒のボールを生成
    gc.fillOval(ball_x - 5, ball_y - 5, 10, 10);//ボールの位置の更新
    //gc.fillOval(円の左上のx座標,円の左上のy座標,円の右下のx座標,円の右下のy座標);

    ball_x += x_speed;
    ball_y += y_speed;
    if(ball_x >= 640){
      ball_x = 640 - 5;
      x_speed = x_speed * -1;
    }
    if(ball_x <= 0){
      ball_x = 5;
      x_speed = x_speed * -1;
    }

    if(ball_y >= 480){
      ball_y = 480 - 5;
      y_speed = y_speed * -1;
    }
    if(ball_y <= 0){
      ball_y = 5;
      y_speed = y_speed * -1;
    }
  }

  /*追加メソッドを書く*/
}
