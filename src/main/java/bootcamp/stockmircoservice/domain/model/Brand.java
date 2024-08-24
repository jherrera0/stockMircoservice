package bootcamp.stockmircoservice.domain.model;

public class Brand {
    private long id;
    private String name;
    private String description;

    public Brand() {
    }

    public Brand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Brand(long id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
