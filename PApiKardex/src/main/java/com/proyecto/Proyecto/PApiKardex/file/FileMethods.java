package com.proyecto.Proyecto.PApiKardex.file;

import com.proyecto.Proyecto.PApiKardex.dto.TransactionResponse;
import com.proyecto.Proyecto.PApiKardex.entity.Kardex;
import com.proyecto.Proyecto.PApiKardex.entity.KardexItem;
import com.proyecto.Proyecto.PApiKardex.entity.Producto;
import com.proyecto.Proyecto.PApiKardex.entity.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;

import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FileMethods {

    int auxCont = 0;
    public String folderPath;
    public String fileName;

    public FileMethods() {
        this.folderPath = "C:/ReportesGenerales/";
        this.fileName = "document.json";
    }

    public Boolean fileExists(String parFileName) {
        File f = new File(this.folderPath + parFileName);
        if (f.exists()) {
            System.out.println(parFileName + " Exists");
            return true;
        }
        return false;
    }

    public String createFile(String parFileName, List<TransactionResponse> transactionResponses) {
        if (parFileName.isEmpty()) {
            auxCont++;
            parFileName = "test" + auxCont + ".txt";
        }

        String fullPath = folderPath + parFileName;
        if (this.fileExists(parFileName)) {
            return "";
        }

        try {
            // Crear y escribir el archivo con el contenido formateado
            FileWriter writer = new FileWriter(fullPath);

            for (TransactionResponse transactionResponse : transactionResponses) {
                Kardex kardex = transactionResponse.getKardex();
                Usuario usuario = transactionResponse.getUsuario();
                Set<KardexItem> kardexItemsSet = kardex.getKardexItems();
                List<KardexItem> kardexItems = new ArrayList<>(kardexItemsSet); // Lista de productos

                // Formato del encabezado de la orden
                writer.write("Número de orden:\t" + kardex.getKardexId() + "\n");
                writer.write("Documento cliente:\t" + usuario.getNumeroDocUsu() + "\n");
                writer.write("Nombre Cliente:\t" + usuario.getNombreUsu() + "\n");
                writer.write("Fecha de Recepción:\t" + kardex.getKardexDate() + "\n");
                writer.write("Estado de Pedido:\t" + kardex.getEstadoPedido() + "\n");
                writer.write("Estado de Retiro:\t" + kardex.getEstadoRetiro() + "\n\n");

                writer.write("Productos de orden:\n\n");

                // Formato de los productos (KardexItems)
                for (KardexItem item : kardexItems) {
                    Producto producto = transactionResponse.getProductos().stream()
                            .filter(p -> p.getProductoSK().equals(item.getProductoSK()))
                            .findFirst()
                            .orElse(null);

                    writer.write("Número de Item:\t" + item.getKardexItemid() + "\n");
                    writer.write("Cantidad:\t\t" + item.getCantidad() + "\n");
                    writer.write("Nombre de Producto:\t" + (producto != null ? producto.getNombreProd() : "Desconocido") + "\n");
                    writer.write("ProductoSK:\t\t" + item.getProductoSK() + "\n\n");
                }

                writer.write("/////////////////////////////////////////////////////////////////////////////////\n\n");
            }

            writer.close();
            System.out.println("Archivo de texto creado exitosamente: " + fullPath);
            return fullPath;

        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
            return "";
        }

    }

    public void readFile(String parFileName) {
        if (parFileName.isEmpty()) {
            parFileName = "test" + auxCont + ".txt";
        }
        if (!this.fileExists(parFileName)) {
            System.out.println("The file doesn't exist");
            return;
        }
        String fullPath = folderPath + parFileName;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fullPath));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public String modifyFile(String parFileName) {
        if (parFileName.isEmpty()) {
            auxCont++;
            parFileName = "test" + auxCont + ".txt";
        }
        String fullPath = folderPath + parFileName;
        if (!this.fileExists(parFileName)) {
            System.out.println("The file doesn't exist");
            return "";
        }
        try {
            FileWriter writer = new FileWriter(fullPath, true);
            writer.write("\nHola mundo, estoy modificando el archivo de texto");
            writer.close();
            System.out.println("Archivo modificado existosamente!!");
            return fullPath;
        } catch (IOException e) {
            System.out.println("Error al modificar el archivo: " + e.getMessage());
            return "";
        }
    }

    public Boolean copyFile(String parFileName) {
        if (parFileName.isEmpty()) {
            parFileName = "text" + auxCont + ".txt";
        }
        String fullPath = folderPath + parFileName;

        try {
            Path srcPath = Paths.get(fullPath);
            Path destinationPath = Paths.get(folderPath + "Other/" + srcPath.getFileName());

            Files.copy(srcPath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado exitosamente");
            return true;
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
            return false;
        }
    }

    public Boolean createFolder(String parFolderName) {
        if (parFolderName.isEmpty()) {
            parFolderName = "Other";
        }
        String fullPath = "C:/" + parFolderName;
        Path path = Paths.get(fullPath);
        if (Files.isDirectory(path)) {
            System.out.println("Folder exists, cannot be created");
            return false;
        }
        try {
            File directory = new File(fullPath);
            if (!directory.exists()) {
                if (directory.mkdir()) {
                    System.out.println("Folder creado!");
                    return true;
                } else {
                    System.out.println("Error al crear Folder");
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
            return false;
        }
    }

    public String createFileJson() {

        String fullPath = "Reportes generales" + fileName;
        if (this.fileExists(fileName)) {
            return "";
        }
        try {
            FileWriter writer = new FileWriter(fullPath);
            writer.write("");
            writer.close();
            System.out.println("Archivo creado exitosamente");
            return fullPath;
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
            return "";
        }
    }

}
