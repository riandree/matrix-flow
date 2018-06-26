package de.riandree;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ShadowRoot;
import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.function.Function;

@Tag("matrix-element")
@HtmlImport("context://webjars/matrix-webjar/matrix-element.html")
public class MatrixElement<T> extends Component {

    private Function<T, JsonValue> item2JsonMapper;

    public MatrixElement(Function<T, JsonValue> item2JsonMapper) {
        this.item2JsonMapper = Validate.notNull(item2JsonMapper);

        // ToDo use a 'Renderer' here ?
        Element template=new Element("template");
        template.setProperty("innerHTML", "<span>[[item]]</span>");
        this.getElement().appendChild(template);
    }

    public void setMatrix(List<List<T>> matrixRows) {
        JsonArray matrix = Json.createArray();
        matrixRows.stream().map(row -> {
            JsonArray jsonRow = Json.createArray();
            row.stream().forEachOrdered(rowElem ->
                    jsonRow.set(jsonRow.length(), this.item2JsonMapper.apply(rowElem)));
            return jsonRow;
        }).forEachOrdered(row -> matrix.set(matrix.length(), row));
        this.getElement().setPropertyJson("matrix",matrix);
        this.getElement().callFunction("_renderMatrix");
    }

//    header : {
//        type: Array, value : []
//    },
//    rowHeader : {
//        type: Array, value : []
//    }
}
