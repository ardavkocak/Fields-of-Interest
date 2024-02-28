import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyGraph<T> {
    private int kenarSayi;
    private ArrayListim<ArrayListim<T>> grafListe;

    public MyGraph(int vertexCount) {
        this.kenarSayi = vertexCount;
        this.grafListe = new ArrayListim<>();

        for (int i = 0; i < vertexCount; i++) {
            this.grafListe.add(new ArrayListim<>());
        }
    }

    public void addEdge(T source, T destination) {
        this.grafListe.get((Integer) source).add(destination);
        this.grafListe.get((Integer) destination).add(source);
    }

    public void addVertex() {
        this.kenarSayi++;
        this.grafListe.add(new ArrayListim<>());
    }

    public void removeEdge(T source, T destination) {
        this.grafListe.get((Integer) source).remove(destination);
        this.grafListe.get((Integer) destination).remove(source);
    }

    public void removeVertex(int vertex) {
        this.grafListe.remove(vertex);
        this.kenarSayi--;

        int size = grafListe.size();
        for (int i = 0; i < size; i++) {
            ArrayListim<T> komsu = grafListe.get(i);
            komsu.remove(vertex);
        }

    }

    public void printGraph() {
        for (int i = 0; i < kenarSayi; i++) {
            System.out.print("Vertex " + i + " bağlantıları: ");

            ArrayListim<T> komsular = grafListe.get(i);
            int neighborSize = komsular.size();

            for (int j = 0; j < neighborSize; j++) {
                T neighbor = komsular.get(j);
                System.out.print(neighbor + " ");
            }

            System.out.println();
        }
    }
    private int getIndex(T element) {
        for (int i = 0; i < kenarSayi; i++) {
            ArrayListim<T> komsular = grafListe.get(i);
            for (int j = 0; j < komsular.size(); j++) {
                T neighbor = komsular.get(j);
                if (neighbor.equals(element)) {
                    return i;
                }
            }
        }
        return -1;
    }
    public static void addEdge(User geciciKullanici, User user) {
    }
    public void printGraphToFile(String dosyaYolu) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dosyaYolu))) {
            for (int i = 0; i < kenarSayi; i++) {
                ArrayListim<T> neighbors = grafListe.get(i);
                for (int j = 0; j < neighbors.size(); j++) {
                    T neighbor = neighbors.get(j);
                    bufferedWriter.write("Vertex " + i + " -> Vertex " + getIndex(neighbor));
                    bufferedWriter.newLine();
                }
            }

        } catch (IOException e) {
                System.err.println("Dosyaya yazma hatası: " + e.getMessage());
        }

        }
    }





