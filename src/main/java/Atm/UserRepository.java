package Atm;

import java.util.HashMap;
import java.io.*;

    public class UserRepository {
        private static HashMap<String, User> users;
        private static HashMap<String, String> loginData;

        public UserRepository() {
            users = new HashMap<>();
            loginData = new HashMap<>();
        }

        public static void addUser(User user) {
            users.put(user.getAccountNumber(), user);
            loginData.put(user.getEmail(), user.getPin());
            saveLoginData();
        }

        public static User getUserByEmail(String email) {
            for (User user : users.values()) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
            return null;
        }

        public static boolean emailExists(String email) {
            return loginData.containsKey(email);
        }

        public void loadLoginData() {
            try {
                File file = new File("loginData.txt");
                if (file.exists()) {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    loginData = (HashMap<String, String>) ois.readObject();
                    ois.close();
                    fis.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private static void saveLoginData() {
            try {
                FileOutputStream fos = new FileOutputStream("loginData.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(loginData);
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
