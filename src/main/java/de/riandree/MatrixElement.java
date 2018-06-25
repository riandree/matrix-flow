package de.riandree;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.function.Function;

@Tag("matrix-element")
@HtmlImport("context://webjars/matrix-webjar/matrix-element.html")
public class MatrixElement<T> extends Component {

    private Function<T, JsonObject> item2JsonMapper;

    public MatrixElement(Function<T, JsonObject> item2JsonMapper) {
        this.item2JsonMapper = Validate.notNull(item2JsonMapper);
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
    }

//    header : {
//        type: Array, value : []
//    },
//    rowHeader : {
//        type: Array, value : []
//    }
}
