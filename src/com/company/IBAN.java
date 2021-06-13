package com.company;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.Scanner;
import java.io.*;

public class IBAN {

    public static void main(String[] args) {
        System.out.println("Zadejte prosím Vaše číslo účtu");
        BUM A = new BUM();
        System.out.println("Číslo banokvního účtu: " + A.BU);
        System.out.println(A.getDetails());
    }

    public static class BUM {
        String BU;
        String BuOrigin;

        public BUM() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your bank account number: " + "e.g. 000000-123456789/0300 or 000000-123456789/7500");
            BU = sc.next();
            System.out.println("Are you from CZ or SK? ");
            BuOrigin = sc.next();

        }

        public String getDetails() {
            String[] splitBaCC = BU.split("-|/");
            String prefix = splitBaCC[0]; //Předčíslí
            String bBAn = splitBaCC[1]; // číslo účtu v bance
            String suffix = splitBaCC[2];//Kód banky
            String iBAN;
            String ibanWOcc = "00".concat(suffix).concat(prefix).concat(bBAn);
            if (BuOrigin.contains("CZ") || BuOrigin.contains("SK")) {
                iBAN = BuOrigin.concat("00" + suffix).concat(prefix).concat(bBAn);
            } else {
                iBAN = "Zadejte prosím CZ nebo SK";
                System.out.println(iBAN);
            }
            String cIban = "CZ".concat(GetIban(ibanWOcc, suffix, prefix, bBAn).concat(suffix).concat(prefix).concat(bBAn));
            System.out.println(cIban);
            return cIban;
        }

        public String GetIban(String ibanWOcc, String suffix, String prefix, String bBan) {
            String cc;
            if (BuOrigin.equals("CZ")) {
                cc = "123500";
            } else {
                cc = "282000";
            }
            String srIban = suffix.concat(prefix).concat(bBan).concat(cc);
            int tmpIban = (mod(srIban, 97));
            tmpIban = 98 - tmpIban;
            srIban = String.valueOf(tmpIban);
            return srIban;

        }

        static int mod(String num, int a) {
            int res = 0;

            for (int i = 0; i < num.length(); i++)
                res = (res * 10 + (int) num.charAt(i) - '0') % a;

            return res;
        }

    }
}