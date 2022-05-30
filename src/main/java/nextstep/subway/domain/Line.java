package nextstep.subway.domain;

import javax.persistence.*;

@Entity
public class Line {

    @Embedded
    private final Sections sections = new Sections();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    protected Line() {

    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public static Line of(String name, String color, Section section) {
        Line line = new Line(name, color);
        line.addSections(section);
        return line;
    }

    public void addSections(Section section) {
        this.sections.addSections(section);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Sections getSections() {
        return sections;
    }
}
