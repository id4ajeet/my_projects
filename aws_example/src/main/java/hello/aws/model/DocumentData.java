
package hello.aws.model;

import org.springframework.util.InvalidMimeTypeException;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class DocumentData {

    private String type;
    private String name;
    private long size;
    private String checksum;

    public DocumentData(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getChecksum() {
        return checksum;
    }
}
