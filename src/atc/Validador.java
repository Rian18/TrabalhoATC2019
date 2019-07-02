/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atc;

import java.util.Stack;

/**
 *
 * @author Rian Alves
 */
public class Validador {

    private static Stack pilhaTag = new Stack();

    public static boolean validaLinguagem(char[] tag) {
        pilhaTag.clear();
        for (int i = 0; i < tag.length; i++) {
            if (tag[i] != '+' && tag[i] != '.' && tag[i] != '*') {
                pilhaTag.push(tag[i]);
            } else if (tag[i] == '+') {
                if (pilhaTag.size() > 1) {
                    String a = (String) pilhaTag.pop().toString();
                    String b = (String) pilhaTag.pop().toString();
                    pilhaTag.push(b + "+" + a);
                } else {
                    System.out.print("[WARNING]Tag Inválida!");
                    return false;
                }
            } else if (tag[i] == '.') {
                if (pilhaTag.size() > 1) {
                    String a = (String) pilhaTag.pop().toString();
                    String b = (String) pilhaTag.pop().toString();
                    pilhaTag.push(b + "." + a);
                } else {
                    System.out.print("[WARNING]Tag Inválida!");
                    return false;
                }
            } else if (tag[i] == '*') {
                if (pilhaTag.size() >= 1) {
                    String a = (String) pilhaTag.pop().toString();
                    pilhaTag.push(a + "*");
                } else {
                    System.out.print("[WARNING]Tag Inválida!");
                    return false;
                }
            }
        }

        return true;
    }

}
