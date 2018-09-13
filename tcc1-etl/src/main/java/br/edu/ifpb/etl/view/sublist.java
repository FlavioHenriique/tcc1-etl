/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.etl.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author flavio
 */
public class sublist {
    public static void main(String[] args) {
        
        List<String> lista = new ArrayList<>();
        lista.add("a");
        lista.add("b");
        lista.add("c");
        lista.add("d");
        lista.add("e");
        lista.add("f");
        lista.add("g");
        
        lista.subList(3, 6).forEach(System.out::println);
    }
}
