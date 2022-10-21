import Viev.MyMessage;

import java.util.List;

public class MessageTree {
    MyMessage root;
    List<MessageTree> leafs;

    public MessageTree(MyMessage message, List<MessageTree> leafs) {
        root = message;
        this.leafs = leafs;
    }
}
