package br.com.softslab.qrcode.products.dto;

public class ProductDTO {
    private String id;
    private String title;
    private String description;

    ProductDTO() {}

    public ProductDTO(String id, String title, String text)
    {
        this.id = id;
        this.title = title;
        this.description = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
