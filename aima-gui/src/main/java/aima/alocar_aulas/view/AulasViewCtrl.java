package aima.alocar_aulas.view;

import aima.alocar_aulas.env.AulasBoard;
import aima.core.util.datastructure.XYLocation;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

/**
 * Controller class which provides functionality for using a stack pane as a
 * state view for the N-Queens problem.
 * 
 * @author Ruediger Lunde
 */
public class AulasViewCtrl {

	private GridPane gridPane = new GridPane();
	private SquareButton[] aulas = new SquareButton[0];

	/**
	 * Adds a grid pane to the provided pane and creates a controller class instance
	 * which is responsible for aula symbol positioning on the grid.
	 */
	public AulasViewCtrl(StackPane viewRoot) {
		viewRoot.getChildren().add(gridPane);
		viewRoot.setAlignment(Pos.BOTTOM_CENTER);
		gridPane.maxWidthProperty()
				.bind(Bindings.min(viewRoot.widthProperty(), viewRoot.heightProperty()).subtract(20));
		gridPane.maxHeightProperty()
				.bind(Bindings.min(viewRoot.widthProperty(), viewRoot.heightProperty()).subtract(10));
	}

	/** Updates the view. */
	public void update(AulasBoard board) {
		int rows = board.getRows();
		int columns = board.getColumns();
		if (aulas.length != rows * columns) {
			gridPane.getChildren().clear();
			gridPane.getColumnConstraints().clear();
			gridPane.getRowConstraints().clear();

			aulas = new SquareButton[rows * columns];
			RowConstraints c1 = new RowConstraints();
			c1.setPercentHeight(100.0 / rows);
			ColumnConstraints c2 = new ColumnConstraints();
			c2.setPercentWidth(100.0 / columns);
			for (int i = 0; i < rows; i++) {
				gridPane.getRowConstraints().add(c1);
				if (i < columns) {
					gridPane.getColumnConstraints().add(c2);
				}
			}
			int count = 0;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {

	                SquareButton btn = new SquareButton();
	                btn.getLabel().setText("X");
					aulas[count] = btn;
					
					gridPane.add(btn, j, i);
					count++;
				}
			}
		}
		double scale = 0.2 * gridPane.getWidth() / gridPane.getColumnConstraints().size();
		int count = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				SquareButton aula = aulas[count];
				aula.setScaleX(scale);
				aula.setScaleY(scale);
				XYLocation loc = new XYLocation(i, j);
				if (board.AulaExistsAt(loc)) {
					aula.setVisible(true);

				} else {
					aula.setVisible(false);
				}
				count++;
			}
		}
	}
	
    protected static class SquareButton extends Button {
        private Pane pane;
        private Label label;
        private Label idLabel;

        private SquareButton() {
            StackPane sp = new StackPane();
            setGraphic(sp);
            pane = new StackPane();
            label = new Label();
            idLabel = new Label();
            StackPane.setAlignment(idLabel, Pos.TOP_LEFT);
            sp.getChildren().addAll(idLabel, pane, label);
            setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            setMinSize(10, 10);
        }

        public Pane getPane() {
            return pane;
        }

        public Label getLabel() {
            return label;
        }

        public Label getIdLabel() {
            return idLabel;
        }
    }
}
