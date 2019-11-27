package com.fileAnalysis;

import com.fileAnalysis.modules.CommonModule;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static Map<String, CommonModule> modulesMap;

    private static String path = "/Users/pavelzaborin/MWA";

    public static void main(String[] args) throws IOException{
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App)ctx.getBean("App");
        while (true){
            System.out.println(" \nBefore you start, make sure your file or directory is in this directory: " + path);
            Scanner in = new Scanner(System.in);
            System.out.print("Input name of your file or directory (with extension)\n");
            String name = in.nextLine();
            try {
                File file = new File(path+ "/" + name);
                while (!file.exists()){
                    System.out.println(" \nIncorrect name! File or directory not exists! Try again!");
                    name = in.nextLine();
                    file = new File(path+ "/" + name);
                }
            }
            catch (Exception e){
                System.out.println(" \nSOMETHING WRONG WITH FINDING YOUR FILE \n");
            }
            CommonModule module  = app.checkExtension(name);
            if(module == null){
                System.out.println(" \nUnfortunately this extension is not supported :( ");
                System.out.println("\nWould you like continue to work with app? y/n\n");
                String y = in.nextLine();
                while (!y.equals("y") && !y.equals("n")){
                    System.out.println("\nWould you like continue to work with app? y/n\n");
                    y = in.nextLine();
                }
                if( y.equals("n")){
                    System.out.println("\nGOODBYE!\n");
                    return;
                }

            }
            else {
                module.chooseFunction(name);
                System.out.println("\nWould you like continue to work with app? y/n\n");
                String y = in.nextLine();
                while (!y.equals("y") && !y.equals("n")){
                    System.out.println("\nWould you like continue to work with app? y/n\n");
                    y = in.nextLine();
                }
                if( y.equals("n")){
                    System.out.println("\nGOODBYE!\n");
                    return;
                }
            }
        }
    }

    public App(){};

    public App(Map<String, CommonModule> modulesMap){
        this.modulesMap = modulesMap;
    }

    private CommonModule checkExtension(String name){
        CommonModule resultModule = null;
        for (String n : modulesMap.keySet()){
            if(modulesMap.get(n).checkExtention(name)){
                resultModule = modulesMap.get(n);
            }
        }
        return resultModule;
    }

}
