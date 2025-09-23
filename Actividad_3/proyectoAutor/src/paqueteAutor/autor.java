package paqueteAutor;

public class autor {
    private String name;
    private String email;
    private char gender; // 'm' or 'f'

    public autor(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public char getGender() { return gender; }

    @Override
    public String toString() {
        return "Autor[nombre=" + name + ",email=" + email + ",genero=" + gender + "]";
    }
}

