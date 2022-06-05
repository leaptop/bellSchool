package pages;

public interface ExchangePage {
    public void preActions();

    public Double getCource(String currencyName, String currencyType);
}
