package de.riandree;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import elemental.json.JsonNumber;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import elemental.json.impl.JreJsonNumber;
import elemental.json.impl.JreJsonObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Route("")
public class DemoView extends VerticalLayout {

    public DemoView() {
        MatrixElement<Integer> matrixElement= new MatrixElement<Integer>(i -> new JreJsonNumber((double)i));
        add(matrixElement);

        List<List<Integer>> matrix=new LinkedList<>();
        matrix.add(new ArrayList<Integer>(){{
            add(42);
            add(41);
            add(40);
        }});
        matrix.add(new ArrayList<Integer>(){{
            add(12);
            add(21);
            add(30);
        }});
        matrix.add(new ArrayList<Integer>(){{
            add(22);
            add(31);
            add(50);
        }});
        matrixElement.setMatrix(matrix);
    }
}
