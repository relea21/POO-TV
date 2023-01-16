package pages;

public class PageFactory {
    private static PageFactory instance = null;
    private PageFactory() {

    }

    public static PageFactory getPageFactory() {
        if (instance == null) {
            instance = new PageFactory();
        }
        return instance;
    }

    public Page createNewPage(final String pageName) {
        switch (pageName) {
            case "login":
                return new Login();
            case "register":
                return new Register();
            case "movies":
                return  new Movies();
            case "upgrades":
                return new Upgrades();
            default:
                return null;
        }
    }
}
