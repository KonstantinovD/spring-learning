package ch6_hibernate.p188_embeddable_components_collections.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@Accessors(chain = true)
public class Image {

    @Column(nullable = false)
    protected String title;
    @Column(nullable = false)
    protected String filename;
    protected int width;
    protected int height;

}
