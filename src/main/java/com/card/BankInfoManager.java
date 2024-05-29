package com.card;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class BankInfoManager {
    private static final String BANK_FILE_PATH = "assets/banks.json";
    private List<Bank> banks;
    private Gson gson;
    PaymentInfoManager paymentInfoManager = new PaymentInfoManager();

    public BankInfoManager(){
        gson = new Gson();
        loadBanks();
    }
    private synchronized void loadBanks() {
        try (Reader reader = new FileReader(BANK_FILE_PATH)) {
            Type bankListType = new TypeToken<List<Bank>>(){}.getType();
            banks = gson.fromJson(reader, bankListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private synchronized void saveBanks() {
        try (Writer writer = new FileWriter(BANK_FILE_PATH)) {
            gson.toJson(banks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean updateBankMaintenance(String bankName, boolean maintenance) {
        for (Bank bank : banks) {
            if (bank.getBankName().equals(bankName)) {
                bank.setMaintenance(maintenance);
                saveBanks();
                return true;
            }
        }
        System.out.println("해당 이름의 은행이 없습니다.");
        return false;
    }

    public Bank getBankInfo(String bankName) {
        if (banks == null) {
            loadBanks();
        }
        for (Bank bank : banks) {
            if (bank.getBankName().equals(bankName)) {
                return bank;
            }
        }
        System.out.println("해당 이름의 은행이 없습니다.???");
        return null;
    }

    public class Bank {
        private String bankName;
        private boolean maintenance;

        public String getBankName() {
            return bankName;
        }

        public boolean isMaintenance() {
            return maintenance;
        }

        public void setMaintenance(boolean maintenance) {
            this.maintenance = maintenance;
        }
    }
}