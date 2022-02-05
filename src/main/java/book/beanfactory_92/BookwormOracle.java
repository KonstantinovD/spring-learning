package book.beanfactory_92;

// Допустим, имеется следующая реализация данного интерфейса, в которой
// имитируется оракул, вещающий о смысле жизни:
public class BookwormOracle implements Oracle {
    private String encyclopediaUrl;

    public void setEncyclopediaUrl(String encyclopediaUrl) {
        this.encyclopediaUrl = encyclopediaUrl;
    }

    @Override
    public String defineMeaningOfLife() {
        return "Encyclopedias are а waste of money - go "
                + "see the world instead";
    }
}
