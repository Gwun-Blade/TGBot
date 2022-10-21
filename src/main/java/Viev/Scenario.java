package Viev;

import java.util.List;

public class Scenario {
    int id;
    List<MyBlock> blocks;
    SubTypes type;

    public Scenario(int id, List<MyBlock> blocks, SubTypes type) {
        this.id = id;
        this.blocks = blocks;
        this.type = type;
    }

    public String getSQLToWriteIt() {
        return "Insert into 'Scenaries' values('" + id + "', '" + this.toString() + "', '" + type.toString() + "')";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<scenario id=\"" + id + "\">\n");
        for (MyBlock block :
                blocks) {
            builder.append(block.toString() + "\n");
        }
        builder.append("</scenario>");
        return builder.toString();
    }
}
