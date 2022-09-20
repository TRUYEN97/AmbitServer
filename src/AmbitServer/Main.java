/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AmbitServer;

import Control.Engine;

/**
 *
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        try {
            new Engine().run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
