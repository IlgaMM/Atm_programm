package Atm;

    import javax.swing.JOptionPane;

    public class Login {
        User loginUser() {
            String email = JOptionPane.showInputDialog("Enter your email:");
            String pin = JOptionPane.showInputDialog("Enter your PIN code:");

            if (email != null && pin != null) {
                User user = UserRepository.getUserByEmail(email);
                if (user != null && user.getPin().equals(pin)) {
                    return user;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or PIN code. Please try again.");
                }
            }
            return null;
        }

        public User registerUser() {
            String email = JOptionPane.showInputDialog("Enter your email:");
            String pin = JOptionPane.showInputDialog("Enter your PIN code:");

            if (email != null && pin != null) {
                if (UserRepository.emailExists(email)) {
                    JOptionPane.showMessageDialog(null, "Email already exists. Please try again.");
                } else {
                    String name = JOptionPane.showInputDialog("Enter your name:");
                    Gender gender = getGenderInput();

                    return new User(name, gender, email, pin);
                }
            }
            return null;
        }

        private Gender getGenderInput() {
            Gender[] genders = Gender.values();
            StringBuilder genderOptions = new StringBuilder();
            for (int i = 0; i < genders.length; i++) {
                genderOptions.append(i + 1).append(". ").append(genders[i].toString()).append("\n");
            }

            int choice = Integer.parseInt(JOptionPane.showInputDialog("Select your gender:\n" + genderOptions.toString()));
            return genders[choice - 1];
        }
    }

