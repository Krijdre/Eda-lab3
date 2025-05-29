import java.util.*;
import java.io.*;

class Game{
    private String name;
    private String category;
    private int price;
    private int quality;

    public Game(String name, String category, int price, int quality){
        this.name = name;
        this.category = category;
        this.price = price;
        this.quality = quality;
    }
    public String getName(){
      return name;
    }
    public String getCategory(){
      return category;
    }
    public int getPrice(){
      return price;
    }
    public int getQuality(){
      return quality;

   }
}

class GenerateData{
    String[] palabras = {"Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior"};
    String[] categorias = {"Acción", "Aventura", "Estrategia", "RPG", "Deportes", "Simulación"};
    Random randin = new Random();

    public ArrayList<Game> GenerateData(int NumeroJ){
         ArrayList<Game> games = new ArrayList<>();
         for (int i = 0; i < NumeroJ; i++){
             int precio = randin.nextInt(70001);
             int calidad = randin.nextInt(101);
             games.add(new Game(palabras[randin.nextInt(palabras.length)],
                                categorias[randin.nextInt(categorias.length)],
                                precio, calidad));
         }
    return games;
    }

}
class archivo{
    public void saveGamesCVS(ArrayList<Game> games, String fileName){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("GameName,category,price,quality\n");
            for (Game juego : games) {
                bufferedWriter.write(String.format("%s,%s,%d,%d\n",
                        juego.getName(),
                        juego.getCategory(),
                        juego.getPrice(),
                        juego.getQuality()));
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public ArrayList<Game> readGamesCVS(String fileName){
        ArrayList<Game> games = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            scanner.nextLine(); // Saltar la primera línea (encabezados)
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                games.add(new Game(line[0], line[1],
                        Integer.parseInt(line[2]),
                        Integer.parseInt(line[3])));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return games;
    }

}

class Dataset {
    private ArrayList<Game> data;
    private String sortedByAttribute;

    public Dataset(ArrayList<Game> data) {
        this.data = new ArrayList<>(data);
        this.sortedByAttribute = "none";
    }

    public void sortByAlgorithm(String algorithm, String attribute) {
        switch (algorithm) {
            case "bubbleSort":
                switch (attribute) {
                    case "price":
                        bubbleSortByPrice();
                        break;
                    case "category":
                        bubbleSortByCategory();
                        break;
                    case "quality":
                        bubbleSortByQuality();
                        break;
                }
                break;
            case "insertionSort":
                switch (attribute) {
                    case "price":
                        insertionSortByPrice();
                        break;
                    case "category":
                        insertionSortByCategory();
                        break;
                    case "quality":
                        insertionSortByQuality();
                        break;
                }
                break;
            case "selectionSort":
                switch (attribute) {
                    case "price":
                        selectionSortByPrice();
                        break;
                    case "category":
                        selectionSortByCategory();
                        break;
                    case "quality":
                        selectionSortByQuality();
                        break;
                }
                break;
            case "mergeSort":
                switch (attribute) {
                    case "price":
                        mergeSortByPrice();
                        break;
                    case "category":
                        mergeSortByCategory();
                        break;
                    case "quality":
                        mergeSortByQuality();
                        break;
                }
                break;
            case "quickSort":
                switch (attribute) {
                    case "price":
                        quickSortByPrice();
                        break;
                    case "category":
                        quickSortByCategory();
                        break;
                    case "quality":
                        quickSortByQuality();
                        break;
                }
                break;
            default:
                switch (attribute) {
                    case "price":
                        collectionsSortByPrice();
                        break;
                    case "category":
                        collectionsSortByCategory();
                        break;
                    case "quality":
                        collectionsSortByQuality();
                        break;
                }
                break;
        }
    }

    public void bubbleSortByPrice() {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data.get(j).getPrice() > data.get(j + 1).getPrice()) {
                    Game temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);

                }
            }
        }
        sortedByAttribute = "price";
    }

    public void bubbleSortByCategory() {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data.get(j).getCategory().compareTo(data.get(j + 1).getCategory()) > 0) {
                    Game temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);

                }
            }
        }
        sortedByAttribute = "category";
    }

    public void bubbleSortByQuality() {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data.get(j).getQuality() > data.get(j + 1).getQuality()) {
                    Game temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);

                }
            }
        }
        sortedByAttribute = "quality";
    }

    public void collectionsSortByPrice() {
        Collections.sort(data, Comparator.comparingInt(Game::getPrice));
        sortedByAttribute = "price";
    }

    public void collectionsSortByCategory() {
        Collections.sort(data, Comparator.comparing(Game::getCategory));
        sortedByAttribute = "category";
    }

    public void collectionsSortByQuality() {
        Collections.sort(data, Comparator.comparingInt(Game::getQuality));
        sortedByAttribute = "quality";
    }

    public void insertionSortByPrice() {
        int n = data.size();
        for (int i = 1; i < n; i++) {
            Game key = data.get(i);
            int j = i - 1;
            while (j >= 0 && data.get(j).getPrice() > key.getPrice()) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
        sortedByAttribute = "price";
    }

    public void insertionSortByCategory() {
        int n = data.size();
        for (int i = 1; i < n; i++) {
            Game key = data.get(i);
            int j = i - 1;
            while (j >= 0 && data.get(j).getCategory().compareTo(key.getCategory()) > 0) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
        sortedByAttribute = "category";
    }

    public void insertionSortByQuality() {
        int n = data.size();
        for (int i = 1; i < n; i++) {
            Game key = data.get(i);
            int j = i - 1;
            while (j >= 0 && data.get(j).getQuality() > key.getQuality()) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
        sortedByAttribute = "quality";
    }

    public void selectionSortByPrice() {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (data.get(j).getPrice() < data.get(minIdx).getPrice()) {
                    minIdx = j;
                }
            }
            Game temp = data.get(minIdx);
            data.set(minIdx, data.get(i));
            data.set(i, temp);
        }
        sortedByAttribute = "price";
    }

    public void selectionSortByCategory() {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (data.get(j).getCategory().compareTo(data.get(minIdx).getCategory()) < 0) {
                    minIdx = j;
                }
            }
            Game temp = data.get(minIdx);
            data.set(minIdx, data.get(i));
            data.set(i, temp);
        }
        sortedByAttribute = "category";
    }

    public void selectionSortByQuality() {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (data.get(j).getQuality() < data.get(minIdx).getQuality()) {
                    minIdx = j;
                }
            }
            Game temp = data.get(minIdx);
            data.set(minIdx, data.get(i));
            data.set(i, temp);
        }
        sortedByAttribute = "quality";
    }

    public void mergeSortByPrice() {
        ArrayList<Game> temp = new ArrayList<>(data);
        mergeSortByPriceHelper(data, temp, 0, data.size() - 1);
        sortedByAttribute = "price";
    }

    private void mergeSortByPriceHelper(ArrayList<Game> arr, ArrayList<Game> temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortByPriceHelper(arr, temp, left, mid);
            mergeSortByPriceHelper(arr, temp, mid + 1, right);
            mergePriceArrays(arr, temp, left, mid, right);
        }
    }

    private void mergePriceArrays(ArrayList<Game> arr, ArrayList<Game> temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp.set(i, arr.get(i));
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp.get(i).getPrice() <= temp.get(j).getPrice()) {
                arr.set(k, temp.get(i));
                i++;
            } else {
                arr.set(k, temp.get(j));
                j++;
            }
            k++;
        }

        while (i <= mid) {
            arr.set(k, temp.get(i));
            k++;
            i++;
        }
    }

    public void mergeSortByCategory() {
        ArrayList<Game> temp = new ArrayList<>(data);
        mergeSortByCategoryHelper(data, temp, 0, data.size() - 1);
        sortedByAttribute = "category";
    }

    private void mergeSortByCategoryHelper(ArrayList<Game> arr, ArrayList<Game> temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortByCategoryHelper(arr, temp, left, mid);
            mergeSortByCategoryHelper(arr, temp, mid + 1, right);
            mergeCategoryArrays(arr, temp, left, mid, right);
        }
    }

    private void mergeCategoryArrays(ArrayList<Game> arr, ArrayList<Game> temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp.set(i, arr.get(i));
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp.get(i).getCategory().compareTo(temp.get(j).getCategory()) <= 0) {
                arr.set(k, temp.get(i));
                i++;
            } else {
                arr.set(k, temp.get(j));
                j++;
            }
            k++;
        }

        while (i <= mid) {
            arr.set(k, temp.get(i));
            k++;
            i++;
        }
    }

    public void mergeSortByQuality() {
        ArrayList<Game> temp = new ArrayList<>(data);
        mergeSortByQualityHelper(data, temp, 0, data.size() - 1);
        sortedByAttribute = "quality";
    }

    private void mergeSortByQualityHelper(ArrayList<Game> arr, ArrayList<Game> temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortByQualityHelper(arr, temp, left, mid);
            mergeSortByQualityHelper(arr, temp, mid + 1, right);
            mergeQualityArrays(arr, temp, left, mid, right);
        }
    }

    private void mergeQualityArrays(ArrayList<Game> arr, ArrayList<Game> temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp.set(i, arr.get(i));
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp.get(i).getQuality() <= temp.get(j).getQuality()) {
                arr.set(k, temp.get(i));
                i++;
            } else {
                arr.set(k, temp.get(j));
                j++;
            }
            k++;
        }

        while (i <= mid) {
            arr.set(k, temp.get(i));
            k++;
            i++;
        }
    }

    public void quickSortByQuality() {
        quickSortByQualityHelper(data, 0, data.size() - 1);
        sortedByAttribute = "quality";
    }

    private void quickSortByQualityHelper(ArrayList<Game> arr, int low, int high) {
        if (low < high) {
            int pi = partitionQuality(arr, low, high);
            quickSortByQualityHelper(arr, low, pi - 1);
            quickSortByQualityHelper(arr, pi + 1, high);
        }
    }

    private int partitionQuality(ArrayList<Game> arr, int low, int high) {
        int pivot = arr.get(high).getQuality();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr.get(j).getQuality() <= pivot) {
                i++;
                Game temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        Game temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }

    public void quickSortByPrice() {
        quickSortByPriceHelper(data, 0, data.size() - 1);
        sortedByAttribute = "price";
    }

    private void quickSortByPriceHelper(ArrayList<Game> arr, int low, int high) {
        if (low < high) {
            int pi = partitionPrice(arr, low, high);
            quickSortByPriceHelper(arr, low, pi - 1);
            quickSortByPriceHelper(arr, pi + 1, high);
        }
    }

    private int partitionPrice(ArrayList<Game> arr, int low, int high) {
        int pivot = arr.get(high).getPrice();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr.get(j).getPrice() <= pivot) {
                i++;
                Game temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        Game temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }

    public void quickSortByCategory() {
        quickSortByCategoryHelper(data, 0, data.size() - 1);
        sortedByAttribute = "category";
    }

    private void quickSortByCategoryHelper(ArrayList<Game> arr, int low, int high) {
        if (low < high) {
            int pi = partitionCategory(arr, low, high);
            quickSortByCategoryHelper(arr, low, pi - 1);
            quickSortByCategoryHelper(arr, pi + 1, high);
        }
    }

    private int partitionCategory(ArrayList<Game> arr, int low, int high) {
        String pivot = arr.get(high).getCategory();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr.get(j).getCategory().compareTo(pivot) <= 0) {
                i++;
                Game temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        Game temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }


    private int BusquedaporPrecio(int elprecioabuscar) {
        int izquierda = 0;
        int derecha = data.size() - 1;
        while (izquierda <= derecha) {
            int centro = izquierda + (derecha - izquierda) / 2;
            int actual = data.get(centro).getPrice();

            if (actual == elprecioabuscar) {
                return centro;
            } else if (actual > elprecioabuscar) {
                derecha = centro - 1;
            } else {
                izquierda = centro + 1;
            }
        }
        return -1; // no se encuentra en la lista
    }

    private int BusquedaporCategoria(String lacategoria) {
        int izquierda = 0;
        int derecha = data.size() - 1;
        while (izquierda <= derecha) {
            int centro = izquierda + (derecha - izquierda) / 2;
            int actual = data.get(centro).getCategory().compareTo(lacategoria);
            if (actual == 0) {
                return centro;
            } else if (actual < 0) {
                izquierda = centro + 1;
            } else {
                derecha = centro - 1;
            }
        }
        return -1;
    }

    private int BusquedaporCalidad(int lacalidad) {
        int izquierda = 0;
        int derecha = data.size() - 1;
        while (izquierda <= derecha) {
            int centro = izquierda + (derecha - izquierda) / 2;
            int actual = data.get(centro).getQuality();

            if (actual == lacalidad) {
                return centro;
            } else if (actual < lacalidad) {
                izquierda = centro + 1;
            } else {
                derecha = centro - 1;
            }
        }
        return -1;
    }

    private int BusquedaRangoprecio(int menorprecio, int mayorprecio) {
        int izquierda = 0;
        int derecha = data.size() - 1;
        int resultado = -1;

        while (izquierda <= derecha) {
            int centro = izquierda + (derecha - izquierda) / 2;
            int actual = data.get(centro).getPrice();

            if (actual >= menorprecio && actual <= mayorprecio) {
                resultado = centro;
                derecha = centro - 1;
            } else if (actual < menorprecio) {
                izquierda = centro + 1;
            } else {
                derecha = centro - 1;
            }
        }
        return resultado;
    }

    public ArrayList<Game> getGamesByPrice(int price) {
        long timeI = System.nanoTime();
        ArrayList<Game> resultados = new ArrayList<>();

        if (sortedByAttribute.equals("precio")) {
            int indice = BusquedaporPrecio(price);
            if (indice != -1) {
                resultados.add(data.get(indice));

                int izquierda = indice;

                int derecha = indice + 1;

                while (izquierda >= 0 && data.get(izquierda).getPrice() == price) {
                    izquierda--;
                }
                while (derecha < data.size() && data.get(derecha).getPrice() == price) {
                    resultados.add(data.get(derecha));
                    derecha++;
                }

            }
            //busqueda lienal si no estan ordenados
        } else {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getPrice() == price) {
                    resultados.add(data.get(i));
                }
            }
        }
        long timeF = System.nanoTime();
        long duration = (timeF - timeI);
        return resultados;
    }

    public ArrayList<Game> getGamesByPriceRange(int lowrPrice, int higherPrice) {
        long timeI = System.nanoTime();
        ArrayList<Game> resultados = new ArrayList<>();
        if (sortedByAttribute.equals("precio")) {
            int inicio = BusquedaRangoprecio(lowrPrice, higherPrice);
            if (inicio != -1) {
                for (int i = inicio; i < data.size() && data.get(i).getPrice() <= higherPrice; i++) {
                    resultados.add(data.get(i));
                }
            }
            //busqueda lienal
        } else {
            for (int i = 0; i < data.size(); i++) {
                Game juego = data.get(i);
                int precio = juego.getPrice();
                if (precio >= lowrPrice && precio <= higherPrice) {
                    resultados.add(juego);
                }
            }
        }
        long timeF = System.nanoTime();
        long duration = (timeF - timeI);
        return resultados;
    }

    public ArrayList<Game> getGamesByCategory(String category) {
        long timeI = System.nanoTime();
        ArrayList<Game> resultados = new ArrayList<>();

        if (sortedByAttribute.equals("category")) {
            int indice = BusquedaporCategoria(category);
            if (indice != -1) {
                resultados.add(data.get(indice));
            }

            int izquierda = indice - 1;
            int derecha = indice + 1;
            while (izquierda >= 0 && data.get(izquierda).getCategory().equals(category)) {
                resultados.add(data.get(izquierda));
                izquierda--;
            }
            while (derecha < data.size() && data.get(derecha).getCategory().equals(category)) {
                resultados.add(data.get(derecha));
                derecha++;
            }

        } else {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getCategory().equals(category)) {
                    resultados.add(data.get(i));

                }
            }
        }
        long timeF = System.nanoTime();
        long duration = (timeF - timeI);
        return resultados;
    }

    public ArrayList<Game> getData() {
        return data;
    }

}

public class Main {
    public static void main(String[] args) {
        /*GenerateData generador = new GenerateData();
        ArrayList<Game> juegosGenerados = generador.GenerateData(1000000);

        archivo archivo = new archivo();
        String fileName = "10alas6.csv";
        archivo.saveGamesCVS(juegosGenerados, fileName);*/


        archivo archivo = new archivo();
        String fileName = "10alas6.csv";
        ArrayList<Game> games = archivo.readGamesCVS(fileName);
        Dataset datasetOrdenado = new Dataset(games);
        Dataset dataset = new Dataset(games);
        long timeI = System.currentTimeMillis();
        datasetOrdenado.sortByAlgorithm("quickSort", "price");
        long timeF = System.currentTimeMillis();
        long timeT = timeF - timeI;
        System.out.println("Sorting Time: " + timeT + " milisegundos\n");
        timeI = System.currentTimeMillis();
        ArrayList<Game> busquedalineal = dataset.getGamesByPriceRange(5000,10000);
        timeF = System.currentTimeMillis();
        timeT = timeF - timeI;
        System.out.println("Lineal Time : " + timeT + " milisegundos\n");
        timeI = System.currentTimeMillis();
        ArrayList<Game> busquedabinaria = datasetOrdenado.getGamesByPriceRange(10000,15000);
        timeF = System.currentTimeMillis();
        timeT = timeF - timeI;
        System.out.println("Binary Time: " + timeT + " milisegundos\n");
    }
}