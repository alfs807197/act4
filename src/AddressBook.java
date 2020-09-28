import java.io.*;
import java.util.HashMap;
import java.util.Iterator;


public class AddressBook {
    public static BufferedReader entrada=new BufferedReader(new InputStreamReader(System.in)); // Se crea el buffer para lectura de datos del teclado

    public static void main(String[]args) throws IOException {
        HashMap<String, String> contactos= new HashMap<String, String>();// se crea el hashmap
        FileWriter archivo = new FileWriter("c:/Users/Usuario/Desktop/agenda.txt",true);
        boolean d=true;
        int sel;
        String key, nombre;
        while(d){
            nombre = null;
            key = null;
            System.out.println("**MENU**\n1.list\n2.create\n3.delete\n4.load\n5.save\n0.Salir");

            sel = Integer.parseInt(entrada.readLine());
            switch (sel){
                case 1:
                    list((HashMap<String, String>)contactos);
                    break;
                case 2:
                    System.out.println("Introduce el nuemro telefonico: ");
                    key= entrada.readLine();
                    System.out.println("Introduce el nombre: ");
                    nombre= entrada.readLine();
                    create((HashMap<String,String>)contactos,key, nombre);
                    System.out.println("Contacto creado");

                    break;
                case 3:
                    delete((HashMap<String, String>)contactos);
                    break;
                case 4://load
                    load((HashMap<String,String>)contactos,key, nombre);
                    break;
                case 5://save
                    break;
                case 0:
                    d=false;
                    break;
                default:
                    System.out.println("Opción incorrecta, vuelve a intentar ");
            }
        }




        archivo.close();
    }//fin de main
    private static HashMap<String, String> create(HashMap<String, String> contactos,String key, String nombre) throws IOException {



        contactos.put(key, nombre); //Se guarda el contacto en el hasmap usando el numero telefonico como clave

        return (HashMap<String, String>)contactos;

    }
    //metodo para imprimir contactos de hashmap
    private static void list(HashMap<String, String>contactos){
        if(contactos.isEmpty()) {
            System.out.println("No existen contactos");
        }else {
            Iterator num = contactos.keySet().iterator();//se declara un marcador para recorrer el hashmap
            System.out.println("Contactos: ");
            while (num.hasNext()) {
                String key = (String) num.next();
                System.out.println("{" + key + "} : {" + contactos.get(key) + "}");//se imprime el contenido del hashmap marcado por el marcador

            }
        }
    }
    private static HashMap<String, String> delete(HashMap<String, String> contactos)throws IOException{
        list((HashMap<String, String>)contactos);
        System.out.println("Introduce el numero del contacto a borrar");
        String key= entrada.readLine();
        contactos.remove(key);
        System.out.println("Contacto borrado");


        return (HashMap<String, String>)contactos;
    }
    private static HashMap<String, String> load(HashMap<String, String> contactos,String key, String nombre){

        try{
            FileReader info =new FileReader("C:/Users/Usuario/Desktop/agenda.txt");
            BufferedReader inf= new BufferedReader(info); //buffer para leer archivos
            String line="";

            while (line != null) {
                line = inf.readLine();
                if (line != null) {
                    String[] arrSplit = line.split(" ");//Divide el string por espacios
                    key = arrSplit[0];
                    nombre = arrSplit[1];
                    if (contactos.containsKey(key) == false) {
                        create((HashMap<String, String>) contactos, key, nombre);
                    }

                }

            }
            info.close();



        }catch(IOException e){
            System.out.println("No se ha encontrado el archivo");
        }
        if(contactos.isEmpty()) {
            System.out.println("No hay contactos para cargar");
        }
        else {
            System.out.println("Contactos cargados");
        }

        return (HashMap<String, String>)contactos;
    }
    private static void save(HashMap<String, String> contactos,String key, String nombre) {
        if(contactos.isEmpty()){
            System.out.println("No existen contactos para guardar");
        }else{
            try{
                Iterator num = contactos.keySet().iterator();//se declara un marcador para recorrer el hashmap
                String txt;
                FileWriter archivo = new FileWriter("c:/Users/Usuario/Desktop/agenda.txt");
                while(num.hasNext()){
                    key = (String) num.next();
                    txt=key+" "+contactos.get(key)+"\n";
                    archivo.write(txt);
                }
                archivo.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Contactos Actualizados");

        }

    }


}

