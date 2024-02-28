import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



import java.io.FileReader;

public class ArrayListim<T> {

    private static final int defaultCapacity = 10;
    private Object[] elements;
    private int size;

    public ArrayListim() {
        this.elements = new Object[defaultCapacity];
        this.size = 0;
    }

    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    //İstediğimiz spesifik bir endekse eleman eklemek için kullanılır.
    public void add(int index, String value) {
        ensureCapacity();

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Geçersiz index: " + index);
        }

        // Elemanları kaydırmak için bir döngü
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }

        // Yeni elemanı eklemek
        elements[index] = value;
        size++;
    }







    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Shift the elements to the left to fill the gap
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
    }






    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }


    public T get(T kullanici) {

        return kullanici;
    }




    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = element;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = java.util.Arrays.copyOf(elements, newCapacity);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        elements = new Object[defaultCapacity];
        size = 0;
    }

    public void remove(T entry) {
        // Implement removal logic if needed
    }

    public Object[] getElements() {
        return elements;
    }

    public void setElements(Object[] elements) {
        this.elements = elements;
    }
}




