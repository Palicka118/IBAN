package com.company;

import java.util.Scanner;

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
            if (BuOrigin.contains("CZ") || BuOrigin.contains("SK")) {
                //iBAN = BuOrigin.concat("00" + suffix).concat(prefix).concat(bBAn);
                if (BAccV()) {
                    String cIban = BuOrigin.concat(GetIban(suffix, prefix, bBAn).concat(suffix).concat(prefix).concat(bBAn));
                    System.out.println(cIban);
                    return cIban;
                } else return "Zadali jste špatné číslo bankovního účtu";
            } else {
                iBAN = "Zadejte prosím CZ nebo SK";
                return iBAN;
            }
        }

        public String GetIban(String suffix, String prefix, String bBan) {
            String cc;
            if (BuOrigin.equals("CZ")) {
                cc = "123500";
            } else {
                cc = "282000";
            }
            String srIban = suffix.concat(prefix).concat(bBan).concat(cc);
            int tmpIban = (mod(srIban));
            tmpIban = 98 - tmpIban;
            srIban = String.valueOf(tmpIban);
            return srIban;

        }

        static int mod(String num) {
            int res = 0;

            for (int i = 0; i < num.length(); i++)
                res = (res * 10 + (int) num.charAt(i) - '0') % 97;

            return res;
        }

        public boolean BAccV() {
            return BU.matches("[0-9]{6}-?[0-9]{10}/?[0-9]{4}");

        }
    }
}