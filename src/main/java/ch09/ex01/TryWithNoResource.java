package ch09.ex01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TryWithNoResource {

    /*
    public static void main(String[] args) {
        try (Scanner in
                = new Scanner(Paths.get("alice.txt"));
                PrintWriter out = new PrintWriter("/tmp/out.txt")) {
            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }
        } catch (IOException ex) {
            Logger.getLogger(TryWithNoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    public static void main(String[] args) {
        Scanner in;
        try {
            in = new Scanner(Paths.get("ch09/ex01/alice.txt"));
        } catch (IOException ex) {
            Logger.getLogger(TryWithNoResource.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        PrintWriter out;
        try {
            out = new PrintWriter("ch09/ex01/out.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TryWithNoResource.class.getName()).log(Level.SEVERE, null, ex);
            try {
                in.close();
            } catch (IllegalStateException e) {
                Logger.getLogger(TryWithNoResource.class.getName()).log(Level.SEVERE, null, e);
            }
            return;
        }

        try {
            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }
        } catch (IllegalStateException | NoSuchElementException e) {

        }

        try {
            in.close();
        } catch (IllegalStateException e) {

        }

        try {
            out.close();
        } catch (Exception e) {
        }
    }
}
