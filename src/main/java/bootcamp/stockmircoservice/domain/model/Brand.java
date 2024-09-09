package bootcamp.stockmircoservice.domain.model;

public class Brand {
    private Long id;
    private String name;
    private String description;
    public static final int MAX_DESCRIPTION_LENGTH = 120;
    public static final int MAX_NAME_LENGTH = 50;

    public Brand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Brand(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
