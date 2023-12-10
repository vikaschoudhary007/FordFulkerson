package FordFulkerson.Graph;

import java.util.Objects;

public class Vertex {
    public int id;
    public double x;
    public double y;

    public Vertex(int id, double x, double y){
        this.id = id;
        this.x = x;
        this.y = y;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
