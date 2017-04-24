/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch18.exam10;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class ConsoleExample {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
//        int keyCode = is.read();

        // how1
        byte[] keycodes = new byte [100];
        int readBytes = is.read(keycodes);
        System.out.println(Arrays.toString(keycodes));
        System.out.println(readBytes);
        String str = new String(keycodes, 0, readBytes-2);
        System.out.println(str);
        is.close();
        
        // how2
        Scanner scanner = new Scanner(System.in);
        String str2 = scanner.nextLine();
        System.out.println(str2);
        
        // how3
        Console console = System.console();
        String str3 = console.readLine();
        System.out.println(str3);
    }
}
