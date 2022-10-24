package Viev;

import java.util.List;

public class Scenario {
    int id;
    List<MyBlock> blocks;
    SubTypes type;

    public Scenario(int id, List<MyBlock> blocks, SubTypes type) {
        this.id = id;
        this.blocks = blocks; //пероверку на уникальность id блоков
        this.type = type;
    }

    public MyBlock getBlockWithId(int id) throws ScenarioException {
        for (MyBlock block :
                blocks) {
            if (block.getId() == id) {
                return block;
            }
        }
        throw new ScenarioException();
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
