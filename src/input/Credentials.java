package input;

public final class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    public  Credentials() {
    };
    public Credentials(final Credentials credentials) {
        this.accountType = credentials.accountType;
        this.balance = credentials.balance;
        this.name = credentials.name;
        this.password = credentials.password;
        this.country = credentials.country;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }
}
