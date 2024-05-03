package expression.exceptions;

import java.util.List;

public class OpertypeBuffer{
    private int pos = 0;
    private final List<DivideParser> list;
    public OpertypeBuffer(List<DivideParser> list) {
        this.list = list;
    }
    public DivideParser next() {
        return list.get(pos++);
    }
    public void previous() {
        list.get(--pos);
    }
    public int getPos() {
        return pos;
    }
    public List<DivideParser> getList(){
        return list;
    }
}

