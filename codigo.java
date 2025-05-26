import java.util.ArrayList;

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

 class Dataset{
    private ArrayList<Game> data;
    private String sortedByAttribute;

    public Dataset(ArrayList<Game> data){
        this.data = new ArrayList<>(data);
        this.sortedByAttribute = "none";
    }

        private int BusquedaporPrecio(int elprecioabuscar){
            int izquierda = 0;
            int derecha = data.size()-1;
            while (izquierda <= derecha){
                int centro = izquierda + (derecha - izquierda)/2;
                int actual = data.get(centro).getPrice();

                if (actual == elprecioabuscar){
                    return centro;
                }else if (actual > elprecioabuscar){
                    derecha = centro-1;
                }else{
                    izquierda = centro+1;
                }
            }
            return -1; // no se encuentra en la lista
        }

        private int BusquedaporCategoria(String lacategoria){
        int izquierda = 0;
        int derecha = data.size()-1;
        while (izquierda <= derecha){
            int centro = izquierda + (derecha - izquierda)/2;
            int actual = data.get(centro).getCategory().compareTo(lacategoria);
            if(actual == 0){
                return centro;
            }else if(actual < 0){
                izquierda = centro+1;
            }else{
                derecha = centro-1;
            }
        }
            return -1;
    }

    private int BusquedaporCalidad(int lacalidad){
        int izquierda = 0;
        int derecha = data.size()-1;
        while (izquierda <= derecha){
            int centro = izquierda + (derecha - izquierda)/2;
            int actual = data.get(centro).getQuality();

            if(actual == lacalidad){
                return centro;
            }else if(actual < lacalidad){
                izquierda = centro+1;
            }else{
                derecha = centro-1;
            }
        }
        return -1;
    }

    private int BusquedaRangoprecio(int menorprecio , int mayorprecio){
        int izquierda =0;
        int derecha = data.size()-1;
        int resultado =-1;

        while (izquierda <= derecha){
            int centro = izquierda + (derecha - izquierda)/2;
            int actual = data.get(centro).getPrice();

            if(actual  >= menorprecio && actual <= mayorprecio){
                resultado = centro;
                derecha = centro-1;
            }else if(actual < menorprecio ){
                izquierda = centro+1;
            }else {
                derecha = centro-1;
            }
        }
        return resultado;
    }

    public ArrayList<Game> getGamesByPrice(int price){
        long timeI = System.nanoTime();
        ArrayList<Game> resultados = new ArrayList<>();

        if (sortedByAttribute.equals("precio")){
            int indice = BusquedaporPrecio(price);
            if (indice != -1){
                resultados.add(data.get(indice));

                int izquierda = indice;

                int derecha = indice+1;

                while (izquierda >= 0 && data.get(izquierda).getPrice() == price){
                    izquierda--;
                }
                while (derecha < data.size() && data.get(derecha).getPrice() == price){
                    resultados.add(data.get(derecha));
                    derecha++;
                }

            }
        //busqueda lienal si no estan ordenados
        }else{
          for (int i = 0; i < data.size(); i++){
              if (data.get(i).getPrice() == price){
                  resultados.add(data.get(i));
              }
          }
        }
        long timeF = System.nanoTime();
        long duration = (timeF - timeI);
        return resultados;
    }

    public ArrayList<Game> getGamesByPriceRange(int lowrPrice, int higherPrice){
        long timeI = System.nanoTime();
        ArrayList<Game> resultados = new ArrayList<>();
        if (sortedByAttribute.equals("precio")){
         int inicio = BusquedaRangoprecio(lowrPrice, higherPrice);
         if (inicio != -1){
             for (int i = inicio; i < data.size() && data.get(i).getPrice() <= higherPrice; i++){
                 resultados.add(data.get(i));
             }
         }
         //busqueda lienal
        }else{
            for (int i = 0; i < data.size(); i++){
                Game juego = data.get(i);
                int precio= juego.getPrice();
                if (precio >= lowrPrice && precio <= higherPrice){
                    resultados.add(juego);
                }
            }
        }
        long timeF = System.nanoTime();
        long duration = (timeF - timeI);
        return resultados;
    }

    public ArrayList<Game> getGamesByCategory(String category){
        long timeI = System.nanoTime();
        ArrayList<Game> resultados = new ArrayList<>();

        if (sortedByAttribute.equals("category")){
            int indice = BusquedaporCategoria(category);
            if (indice != -1){
             resultados.add(data.get(indice));
            }

            int izquierda = indice-1;
            int derecha = indice+1;
            while (izquierda >= 0 && data.get(izquierda).getCategory().equals(category)){
                resultados.add(data.get(izquierda));
                izquierda--;
            }
            while (derecha < data.size() && data.get(derecha).getCategory().equals(category)){
                resultados.add(data.get(derecha));
                derecha++;
            }

        }else {
            for (int i = 0; i < data.size(); i++){
                if (data.get(i).getCategory().equals(category)){
                    resultados.add(data.get(i));

                }
            }
        }
        long timeF = System.nanoTime();
        long duration = (timeF - timeI);
        return resultados;
}

 

}
